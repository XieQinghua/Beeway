package com.thvc.beeway.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.thvc.beeway.R;
import com.thvc.beeway.base.BaseActivity;

/**
 * Created by Administrator on 2015/9/29.
 * 积分规则
 */
public class HoneyRuleActivity extends BaseActivity {
    private ImageView iv_honey_back;
    private WebView honey_wabview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honeyrule);
         iv_honey_back = (ImageView)this.findViewById(R.id.iv_honey_back);//放回按钮
        iv_honey_back.setOnClickListener(new MyOnClickListener());
        honey_wabview = (WebView)this.findViewById(R.id.honey_wabview);
        WebSettings webSettings = honey_wabview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        honey_wabview.loadUrl("http://www.hlfyb.com/wechat.php/Artonce/content_rule");
        honey_wabview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });

    }

    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            HoneyRuleActivity.this.finish();
        }
    }
}
