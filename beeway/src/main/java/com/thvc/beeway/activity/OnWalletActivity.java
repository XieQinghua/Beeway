package com.thvc.beeway.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.thvc.beeway.R;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.http.UrlPools;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/9/29.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class OnWalletActivity extends BaseActivity{
    private Context context =this;
    private WebView webView;
    private ImageView back,trackdetails_other;
    private TextView error,trackdetails_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tarckdetails);
        init();

    }
    public void init(){
        //进度条
        showDialog(LOADING_DIALOG);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                    removeDialog();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        webView = (WebView) findViewById(R.id.trackdetails_webview);
        back = (ImageView) findViewById(R.id.trackdetails_back);
        trackdetails_other = (ImageView) findViewById(R.id.trackdetails_other);
        error = (TextView) findViewById(R.id.trackdetails_error);
        trackdetails_title = (TextView) findViewById(R.id.trackdetails_title);
        webView = (WebView)findViewById(R.id.trackdetails_webview);
        trackdetails_title.setText("关于蜂友余额");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //设置字符集编码
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        //开启JavaScript支持
        webView.getSettings().setJavaScriptEnabled(true);

        //加载assets目录下的文件
        webView.loadUrl(UrlPools.ONWALLET);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                error.setText("404 error");
                webView.setVisibility(View.GONE);
            }
        });
    }
}
