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
 * 蜂蜜兑换
 */
public class HistoricalDetailsActivity extends BaseActivity {
    private ImageView iv_his_back;
    private WebView his_wabview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_details);
        iv_his_back = (ImageView) this.findViewById(R.id.iv_his_back);
        iv_his_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoricalDetailsActivity.this.finish();
            }
        });

        String pid = getIntent().getStringExtra("pid");
        String userid = new BeewayApplication().getmUserid(this);
        String logid = getIntent().getStringExtra("logid");

        his_wabview = (WebView) this.findViewById(R.id.his_wabview);
        WebSettings webSettings = his_wabview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        his_wabview.loadUrl("http://www.hlfyb.com/wechat.php/Android/convert?" + "id" + "=" + pid + "&&userid" + "=" + userid + "&&logid"+"="+logid);
                his_wabview.setWebViewClient(new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView view, String url) { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                        view.loadUrl(url);
                        return true;
                    }
                });

    }
}
