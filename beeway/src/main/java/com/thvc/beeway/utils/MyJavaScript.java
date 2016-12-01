package com.thvc.beeway.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.thvc.beeway.activity.IssueCutomerActivity;
import com.thvc.beeway.activity.JSImageActivity;
import com.thvc.beeway.activity.ScenicDetailsActivity;
import com.thvc.beeway.activity.TrackDetailsActivity;
import com.thvc.beeway.activity.UserDetailsActivity;
import com.thvc.beeway.bean.CheckScenicBean;
import com.thvc.beeway.bean.CommentBean;
import com.thvc.beeway.bean.ImageBean;
import com.thvc.beeway.bean.IsCollectBean;
import com.thvc.beeway.bean.IsCollectDataBean;
import com.thvc.beeway.bean.IsGoodBean;
import com.thvc.beeway.bean.IsGoodDataBean;
import com.thvc.beeway.bean.LoveToBean;
import com.thvc.beeway.bean.PersonalDataBean;
import com.thvc.beeway.bean.RepleBean;
import com.thvc.beeway.bean.ShowShareBean;
import com.thvc.beeway.cn.sharesdk.onekeyshare.OnekeyShare;
import com.thvc.beeway.http.UrlPools;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;

/**
 * com.thvc.beeway.utils
 * 创建日期： 2015/9/14.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class MyJavaScript extends TrackDetailsActivity {
    private Handler handler = null;
    private Context context;
    private String userid;

    public MyJavaScript(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
        userid = getUserId();
    }

    /**
     * 分享
     */
    @JavascriptInterface
    public void showShare(String json) {
        System.out.println("分享    json====" + json);
        ShowShareBean shareBean = shareBean(json);
        if (shareBean.getStatus().equals("1")) {
            ShareSDK.initSDK(context);
            OnekeyShare oks = new OnekeyShare();

            //关闭sso授权
            oks.disableSSOWhenAuthorize();

            // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
            //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
            oks.setTitle(shareBean.getData().getTitle());
            // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
            oks.setTitleUrl(shareBean.getData().getUrl());
            // text是分享文本，所有平台都需要这个字段
            oks.setText(shareBean.getData().getContent());
            //网络获取图片
            oks.setImageUrl(shareBean.getData().getSharethumb());
//            oks.setImageUrl("http://wiki.mob.com/wp-content/uploads/2014/09/ssdk_qig_qi_win.png");
            // imagePath是图片的本地路径，Linked-In以外的a平台都支持此参数
//	        oks.setImagePath("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");//确保SDcard下面存在此张图片
            // url仅在微信（包括好友和朋友圈）中使用
           oks.setUrl(shareBean.getData().getUrl());
            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
            oks.setComment(shareBean.getData().getDefaultcontent());
            // site是分享此内容的网站名称，仅在QQ空间使用
            oks.setSite("Beeway");
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
            oks.setSiteUrl(shareBean.getData().getUrl());

            // 启动分享GUI
            oks.show(context);
        } else {
            Toast.makeText(context, "无内容分享！", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 分享数据解析
     */
    public ShowShareBean shareBean(String result) {
        Gson gson = new Gson();
        ShowShareBean showShareBean = gson.fromJson(result, ShowShareBean.class);
        return showShareBean;
    }

    /**
     * 点赞
     */
    @JavascriptInterface
    public void isGood(String json) {
        System.out.println("点赞    json====" + json);
        HttpUtils httpUtils = new HttpUtils();
        IsGoodBean isGoodBean = isGoodBean(json);
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("noteid", isGoodBean.getData().getNoteid());
        params.addQueryStringParameter("userid", GlobalParams.loginUserId);
        params.addQueryStringParameter("good", String.valueOf(isGoodBean.getData().getGood()));
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.ISGOOD + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //获得访问解析出来的json字符串
                String result = responseInfo.result;
                IsGoodDataBean isGoodDataBean = isGoodDataBean(result);
                if (isGoodDataBean.getStatus() == 1) {
                    System.out.println("点赞 json====" + isGoodDataBean.getData());
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }

    /**
     * 点赞数据解析
     */
    public IsGoodBean isGoodBean(String result) {
        Gson gson = new Gson();
        IsGoodBean isGoodBean = gson.fromJson(result, IsGoodBean.class);
        return isGoodBean;
    }

    /**
     * 点赞网络获取数据解析
     */
    public IsGoodDataBean isGoodDataBean(String result) {
        Gson gson = new Gson();
        IsGoodDataBean isGoodDataBean = gson.fromJson(result, IsGoodDataBean.class);
        return isGoodDataBean;
    }

    /**
     * 评论
     */
    @JavascriptInterface
    public void commed(String json) {
        System.out.println("评论    json====" + json);
        final CommentBean commentBean = commentBean(json);
        final String comment[] = {"comment", commentBean.getData().getDataid(), String.valueOf(commentBean.getData().getIsdata())};
        new Thread() {
            @Override
            public void run() {
                super.run();
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = comment;//将集合传递给主线程
                handler.sendMessage(msg);
            }
        }.start();
    }

    /**
     * 回复数据解析
     */
    public CommentBean commentBean(String result) {
        Gson gson = new Gson();
        CommentBean commentBean = gson.fromJson(result, CommentBean.class);
        return commentBean;
    }

    /**
     * 回复
     */
    @JavascriptInterface
    public void reple(String json) {
        System.out.println("回复    json====" + json);
        RepleBean repleBean = repleBean(json);
        final String reple[] = {"reple", repleBean.getData().getCid(), repleBean.getData().getCommid(), repleBean.getData().getCommnick(),
                String.valueOf(repleBean.getData().getIsdata()), repleBean.getData().getDataid()};
        new Thread() {
            @Override
            public void run() {
                super.run();
                Message msg = Message.obtain();
                msg.what = 2;
                msg.obj = reple;//将集合传递给主线程
                handler.sendMessage(msg);
            }
        }.start();

    }

    /**
     * 回复数据解析
     */
    public RepleBean repleBean(String result) {
        Gson gson = new Gson();
        RepleBean repleBean = gson.fromJson(result, RepleBean.class);
        return repleBean;
    }

    /**
     * 跳转个人资料
     */
    @JavascriptInterface
    public void personalData(String json) {
        System.out.println("跳转个人资料    json====" + json);
        PersonalDataBean personalDataBean = personalDataBean(json);
        if (personalDataBean.getStatus().equals("1")) {
            if (personalDataBean.getData().getSolevar().equals("null")){
                Toast.makeText(context,"该用户不存在！",Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(context, UserDetailsActivity.class);
                intent.putExtra("friendid", personalDataBean.getData().getSolevar());
                context.startActivity(intent);
            }
        }
    }

    /**
     * 跳转个人资料数据解析
     */
    public PersonalDataBean personalDataBean(String result) {
        Gson gson = new Gson();
        PersonalDataBean personalDataBean = gson.fromJson(result, PersonalDataBean.class);
        return personalDataBean;
    }

    /**
     * 查看景点
     */
    @JavascriptInterface
    public void checkScenic(String json) {
        System.out.println("查看景点    json====" + json);
        CheckScenicBean checkScenicBean = checkScenicBean(json);
        Intent intent = new Intent(context, ScenicDetailsActivity.class);
        intent.putExtra("id", checkScenicBean.getData().getId());
        context.startActivity(intent);
    }

    /**
     * 查看景点数据解析
     */
    public CheckScenicBean checkScenicBean(String result) {
        Gson gson = new Gson();
        CheckScenicBean checkScenicBean = gson.fromJson(result, CheckScenicBean.class);
        return checkScenicBean;
    }

    /**
     * 我也想去
     */
    @JavascriptInterface
    public void loveTo(String json) {
        System.out.println("我也想去    json====" + json);
        LoveToBean loveToBean = loveToBean(json);
        Intent intent = new Intent(context, IssueCutomerActivity.class);
        intent.putExtra("Title", loveToBean.getData().getTitle());
        intent.putExtra("Company", loveToBean.getData().getCompany());
        context.startActivity(intent);
    }

    /**
     * 我也想去数据解析
     */
    public LoveToBean loveToBean(String result) {
        Gson gson = new Gson();
        LoveToBean loveToBean = gson.fromJson(result, LoveToBean.class);
        return loveToBean;
    }

    /**
     * 图像
     */
    @JavascriptInterface
    public void image(String json) {
        System.out.println("图像    json====" + json);
        ImageBean imageBean = imageBean(json);
        ArrayList<String> lists = new ArrayList<>();
        ArrayList<String> id = new ArrayList<>();
        int size = imageBean.getData().getData().size();
        for (int i = 0; i < size; i++) {
            lists.add(imageBean.getData().getData().get(i).getFileurl());
            id.add(imageBean.getData().getData().get(i).getId());
        }
        Intent intent = new Intent(context, JSImageActivity.class);
        intent.putExtra("url", lists);
        intent.putExtra("id", id);
        intent.putExtra("TYPE", "photo");
        context.startActivity(intent);
    }

    /**
     * 图像数据解析
     */
    public ImageBean imageBean(String result) {
        Gson gson = new Gson();
        ImageBean imageBean = gson.fromJson(result, ImageBean.class);
        return imageBean;
    }

    /**
     * 是否收藏
     */
    @JavascriptInterface
    public void isCollect(String json) {
        System.out.println("收藏    json====" + json);
        final IsCollectBean isCollectBean = isCollectBean(json);
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("dataid", isCollectBean.getData().getDataid());
        params.addQueryStringParameter("userid", GlobalParams.loginUserId);
        params.addQueryStringParameter("tableid", isCollectBean.getData().getTableid());
        params.addQueryStringParameter("status", "2");
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.COLLECTION + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //获得访问解析出来的json字符串
                String result = responseInfo.result;
                System.out.println("收藏    result====" + result);
                IsCollectDataBean isCollectDataBean = isCollectDataBean(result);
                if (isCollectDataBean.getStatus() == 1) {
                    Toast.makeText(context, isCollectDataBean.getData().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    /**
     * 收藏数据解析
     */
    public IsCollectBean isCollectBean(String result) {
        Gson gson = new Gson();
        IsCollectBean isCollectBean = gson.fromJson(result, IsCollectBean.class);
        return isCollectBean;
    }

    /**
     * 收藏数据解析
     */
    public IsCollectDataBean isCollectDataBean(String result) {
        Gson gson = new Gson();
        IsCollectDataBean isCollectDataBean = gson.fromJson(result, IsCollectDataBean.class);
        return isCollectDataBean;
    }

    /**
     * 是否加载
     */
    @JavascriptInterface
    public void remove() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Message msg = Message.obtain();
                msg.what = 5;
                handler.sendMessage(msg);
            }
        }.start();
    }
}
