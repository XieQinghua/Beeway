package com.thvc.beeway.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.thvc.beeway.R;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;

/**
 * Created by Administrator on 2015/9/30.
 * 兑换页面
 */
public class ExchangeActivity extends BaseActivity {

    private ImageView iv_exchang_back;
    private WebView exchange_wabview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        iv_exchang_back = (ImageView)this.findViewById(R.id.iv_exchang_back);
        iv_exchang_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExchangeActivity.this.finish();
            }
        });

        String userid =  new BeewayApplication().getmUserid(this);
        String Integralid = getIntent().getStringExtra("id");

        exchange_wabview = (WebView) this.findViewById(R.id.exchange_wabview);
        WebSettings webSettings = exchange_wabview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        exchange_wabview.loadUrl("http://www.hlfyb.com/wechat.php/Android/Integral?"+"id"+"="+Integralid+"&&userid"+"="+userid);
        exchange_wabview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });

    }
}
