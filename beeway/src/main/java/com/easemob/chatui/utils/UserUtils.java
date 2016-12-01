package com.easemob.chatui.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.chatui.domain.User;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.squareup.picasso.Picasso;
import com.thvc.beeway.R;
import com.thvc.beeway.activity.UserDetailsActivity;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.bean.UserDetailBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;

import java.text.SimpleDateFormat;

public class UserUtils {

    private static com.nostra13.universalimageloader.core.ImageLoader imageLoader;


    /**
     * 根据username获取相应user，由于demo没有真实的用户数据，这里给的模拟的数据；
     *
     * @param username
     * @return
     */
    public static User getUserInfo(String username) {
        User user = BeewayApplication.getInstance().getContactList().get(username);
        if (user == null) {
            user = new User(GlobalParams.nickname);
        }

        if (user != null) {
            //demo没有这些数据，临时填充
            user.setNick(GlobalParams.nickname);
        }
        return user;
    }

    /**
     * 设置用户头像，如果设置头像方形图像1小时后更新
     *
     * @param username
     */
    public static void setUserAvatar(Context context, String username, ImageView imageView) {
        User user = getUserInfo(username);
        imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();//加载矩形图像
        if (user != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");
            String time = sdf.format(new java.util.Date());
            String url = UrlPools.QINIU + "avatar/" + username + ".jpg?" + time;
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.head_portraits)         //加载开始默认的图片
                    .showImageForEmptyUri(R.drawable.head_portraits)     //url爲空會显示该图片，自己放在drawable里面的
                    .showImageOnFail(R.drawable.head_portraits)                //加载图片出现问题，会显示该图片
                    .cacheInMemory()                                               //缓存用
                    .cacheOnDisc()                                                    //缓存用
                    .build();
            imageLoader.displayImage(url, imageView, options);
        } else {
            Picasso.with(context).load(R.drawable.head_portraits).into(imageView);
        }
    }

    /**
     * 设置用户详情封面，该方法修改完封面实时更新
     *
     * @param context
     * @param username
     * @param imageView
     */
    public static void setUserCover(Context context, String username, ImageView imageView) {
        User user = getUserInfo(username);
        imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();//加载矩形图像
        if (user != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            String time = sdf.format(new java.util.Date());
            String url = UrlPools.QINIU + "membg/" + username + "-back.jpg?" + time;
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.img_default_cover)         //加载开始默认的图片
                    .showImageForEmptyUri(R.drawable.img_default_cover)     //url爲空會显示该图片，自己放在drawable里面的
                    .showImageOnFail(R.drawable.img_default_cover)                //加载图片出现问题，会显示该图片
                    .cacheInMemory()                                               //缓存用
                    .cacheOnDisc()                                                    //缓存用
                    .build();
            imageLoader.displayImage(url, imageView, options);
        } else {
            Picasso.with(context).load(R.drawable.img_default_cover).into(imageView);
        }
    }

    /**
     * 设置用户详情封面,该方法按小时缓存刷新
     *
     * @param context
     * @param username
     * @param imageView
     */
    public static void setUserCoverByday(Context context, String username, ImageView imageView) {
        User user = getUserInfo(username);
        imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();//加载矩形图像
        if (user != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");
            String time = sdf.format(new java.util.Date());
            String url = UrlPools.QINIU + "membg/" + username + "-back.jpg?" + time;
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.img_default_cover)         //加载开始默认的图片
                    .showImageForEmptyUri(R.drawable.img_default_cover)     //url爲空會显示该图片，自己放在drawable里面的
                    .showImageOnFail(R.drawable.img_default_cover)                //加载图片出现问题，会显示该图片
                    .cacheInMemory()                                               //缓存用
                    .cacheOnDisc()                                                    //缓存用
                    .build();
            imageLoader.displayImage(url, imageView, options);
        } else {
            Picasso.with(context).load(R.drawable.img_default_cover).into(imageView);
        }
    }

    /**
     * 封装一个获得蜂友昵称的静态方法，传环信userid返回蜂友昵称
     *
     * @param context
     * @param userid
     * @param textView
     * @return
     */

    public static void getBeewayNickname(Context context, String userid, final TextView textView) {
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addBodyParameter("userid", BeewayApplication.getInstance().getmUserid(context));
        params.addBodyParameter("friendid", userid);
        params.addBodyParameter("track", 1 + "");
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.USER_DETAIL + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                UserDetailBean userDetailBean = UserDetailsActivity.paraseUserDetail(result);
                String beewayNickname = userDetailBean.getData().getFriend().getNickname();
                textView.setText(beewayNickname);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                textView.setText("");
                LogUtils.i(s);
            }
        });
    }


}


