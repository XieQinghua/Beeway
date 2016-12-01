package com.thvc.beeway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.thvc.beeway.R;
import com.thvc.beeway.base.BaseActivity;

/**
 * 项目名称：Beeway
 * 类描述：蜂友介绍
 * 创建人：谢庆华.
 * 创建时间：2015/9/28 14:39
 * 修改人：Administrator
 * 修改时间：2015/9/28 14:39
 * 修改备注：
 */
public class IntroduceActivity extends BaseActivity {
    private ImageView iv_back;
    private WebView web_introduce;
    private TextView tv_introduce_error,tv_title;
    private String url="",title="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        web_introduce = (WebView) findViewById(R.id.web_introduce);
        tv_introduce_error = (TextView) findViewById(R.id.tv_introduce_error);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(iv_back);
            }
        });
        //设置字符集编码
        web_introduce.getSettings().setDefaultTextEncodingName("UTF-8");
        //开启JavaScript支持
        web_introduce.getSettings().setJavaScriptEnabled(true);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        if (url!=null&&url.length()!=0){
            title = intent.getStringExtra("title");
            tv_title.setText(title);
            web_introduce.loadUrl(url);
        }else {
            web_introduce.loadUrl("http://www.hlfyb.com/wechat.php/Artonce/content_intro.html");
        }

        web_introduce.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                tv_introduce_error.setText("404 error");
                web_introduce.setVisibility(View.GONE);
            }
        });
    }
}
