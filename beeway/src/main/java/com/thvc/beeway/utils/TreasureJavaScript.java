package com.thvc.beeway.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.thvc.beeway.R;
import com.thvc.beeway.activity.TreasureDetailsActivity;
import com.thvc.beeway.bean.IsCollectBean;

/**
 * com.thvc.beeway.utils
 * 创建日期： 2015/9/21.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class TreasureJavaScript extends TreasureDetailsActivity{
    private Handler handler = null;
    private WebView webView = null;
    private Context context;
    private String userid;

    public TreasureJavaScript(Context context,Handler handler) {
        this.webView = (WebView)( (Activity)context).findViewById(R.id.trackdetails_webview);
        this.context = context;
        this.handler = handler;
    }
    /**
     * 评论
     */
    @JavascriptInterface
    public void gift(String json){
        System.out.println("礼物    json====" + json);
        final IsCollectBean isCollectBean = isCollectBean(json);
        new Thread(){
            @Override
            public void run() {
                super.run();
                String str[] = {isCollectBean.getUrl(),isCollectBean.getId()};
                Message msg= Message.obtain();
                msg.what= 1;
                msg.obj = str;
                handler.sendMessage(msg);
            }
        }.start();
//        Intent intent = new Intent(context,TreasureDetailsActivity.class);
//        intent.putExtra("TYPE","gift");
//        intent.putExtra("url",isCollectBean.getUrl());
//        startActivity(intent);

    }
    public IsCollectBean isCollectBean(String result) {
        Gson gson = new Gson();
        IsCollectBean isCollectBean = gson.fromJson(result, IsCollectBean.class);
        return isCollectBean;
    }
    /**
     * 是否加载
     */
    @JavascriptInterface
    public void remove(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                Message msg= Message.obtain();
                msg.what= 2;
                handler.sendMessage(msg);
            }
        }.start();
    }
}
