package com.thvc.beeway.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.thvc.beeway.R;
import com.thvc.beeway.activity.GeoCoderActivity;
import com.thvc.beeway.activity.IssueCutomerActivity;
import com.thvc.beeway.activity.ScenicDetailsActivity;
import com.thvc.beeway.activity.SimpleNaviRouteActivity;
import com.thvc.beeway.activity.TrackDetailsActivity;
import com.thvc.beeway.activity.UserDetailsActivity;
import com.thvc.beeway.bean.GeographicalBean;
import com.thvc.beeway.bean.IsCollectBean;
import com.thvc.beeway.bean.IsCollectDataBean;
import com.thvc.beeway.bean.JoinChatGroupBean;
import com.thvc.beeway.bean.LoveToBean;
import com.thvc.beeway.bean.PersonalDataBean;
import com.thvc.beeway.bean.ScenicToTrackDetailBean;
import com.thvc.beeway.bean.ShowShareBean;
import com.thvc.beeway.cn.sharesdk.onekeyshare.OnekeyShare;
import com.thvc.beeway.http.UrlPools;

import cn.sharesdk.framework.ShareSDK;

/**
 * com.thvc.beeway.utils
 * 创建日期： 2015/9/17.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class ScenicJavaScript extends ScenicDetailsActivity {
    public Context context;
    public WebView webView;
    public Handler handler;
    private static final String TAG = "Scenic";




    public ScenicJavaScript(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
        webView = (WebView) ((Activity) context).findViewById(R.id.trackdetails_webview);
    }

    /**
     * 地理位置
     *
     * @param json html传过来的json串
     */
    @JavascriptInterface
    public void address(String json) {
        System.out.println("地理位置 json" + json);
        GeographicalBean geographicalBean = geographicalBean(json);
        if (geographicalBean.getStatus().equals("1")) {
            Intent intent = new Intent(context, GeoCoderActivity.class);
            intent.putExtra("latitude", geographicalBean.getData().getLatitude());
            intent.putExtra("longitude", geographicalBean.getData().getLongitude());
            context.startActivity(intent);
        }
    }

    /**
     * 一键导航
     *
     * @param json html传过来的json串
     */
    @JavascriptInterface
    public void navigation(String json) {
        System.out.println("一键导航 json" + json);
        GeographicalBean geographicalBean = geographicalBean(json);
        if (geographicalBean.getStatus().equals("1")) {
            Intent routeIntent=new Intent(context,SimpleNaviRouteActivity.class);
            routeIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            routeIntent.putExtra("latitude", geographicalBean.getData().getLatitude());
            routeIntent.putExtra("longitude", geographicalBean.getData().getLongitude());
            context.startActivity(routeIntent);
        }
    }

    /**
     * 本地足迹头像
     *
     * @param json html传过来的json串
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
     * 地理位置数据解析
     */
    public GeographicalBean geographicalBean(String result) {
        Gson gson = new Gson();
        GeographicalBean geographicalBean = gson.fromJson(result, GeographicalBean.class);
        return geographicalBean;
    }

    /**
     * 足迹详情
     *
     * @param json html传过来的json串
     */
    @JavascriptInterface
    public void trackDetails(String json) {
        System.out.println("足迹详情 json" + json);
        ScenicToTrackDetailBean scenicToTrackDetailBean = scenicToTrackDetailBean(json);
        Intent intent = new Intent(context, TrackDetailsActivity.class);
        intent.putExtra("id", scenicToTrackDetailBean.getData().getId());
        intent.putExtra("userid", scenicToTrackDetailBean.getData().getUserid());
        context.startActivity(intent);

    }

    /**
     * 足迹详情数据解析
     */
    public ScenicToTrackDetailBean scenicToTrackDetailBean(String result) {
        Gson gson = new Gson();
        ScenicToTrackDetailBean scenicToTrackDetailBean = gson.fromJson(result, ScenicToTrackDetailBean.class);
        return scenicToTrackDetailBean;
    }

    /**
     * 景区聊天室
     *
     * @param json html传过来的json串
     */
    private String groupid;

    @JavascriptInterface
    public void chatRoom(String json) {
        JoinChatGroupBean joinChatGroupBean = parseJoinChatGroupBean(json);
        groupid = joinChatGroupBean.getData().getDataid();
        new Thread() {
            @Override
            public void run() {
                super.run();
                Message msg = Message.obtain();
                msg.what = 6;
                msg.obj = groupid;
                handler.sendMessage(msg);
            }
        }.start();
    }


    /**
     * 景点列表加入群json解析，传json字符串
     *
     * @param result
     * @return
     */
    public JoinChatGroupBean parseJoinChatGroupBean(String result) {
        Gson gson = new Gson();
        JoinChatGroupBean joinChatGroupBean = gson.fromJson(result, JoinChatGroupBean.class);
        return joinChatGroupBean;
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
