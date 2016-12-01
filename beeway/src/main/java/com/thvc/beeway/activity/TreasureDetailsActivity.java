package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.thvc.beeway.R;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.TreasureJavaScript;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/9/21.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class TreasureDetailsActivity extends BaseActivity{
    private Context context =this;
    private WebView webView;
    private ImageView back,trackdetails_other;
    private String id ,userid,name="",TYPE="",url="";
    private TextView error,trackdetails_title;
    public Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what){
                case 1:
                    webView.loadUrl("javascript:initLocation("+GlobalParams.latitude+GlobalParams.longitude+")");
                    String[] str =  (String[]) msg.obj;
                    String load = str[0]+"&&userid=" + userid + "&&geohash="
                            + GlobalParams.Geohash + "&&area=" + GlobalParams.country_code + "&&longitude="
                            + GlobalParams.longitude + "&&latitude=" + GlobalParams.latitude;
                    System.out.println("--------load"+load);
                    webView.loadUrl("javascript:showDistance("+str[1]+")");
                    webView.loadUrl(load);
                    break;
                case 2:
                    removeDialog();
                    break;
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tarckdetails);
        Intent intent = getIntent();
        TYPE = intent.getStringExtra("TYPE");
//        url = intent.getStringExtra("url");
        id = intent.getStringExtra("id");
        userid = GlobalParams.loginUserId;

        name = intent.getStringExtra("name");

        init();
        

    }
    public void init(){
        //进度条
        showDialog(LOADING_DIALOG);
        webView = (WebView) findViewById(R.id.trackdetails_webview);
        back = (ImageView) findViewById(R.id.trackdetails_back);
        trackdetails_other = (ImageView) findViewById(R.id.trackdetails_other);
        trackdetails_other.setImageResource(R.drawable.icon_maibao);
        error = (TextView) findViewById(R.id.trackdetails_error);
        trackdetails_title = (TextView) findViewById(R.id.trackdetails_title);
        webView = (WebView)findViewById(R.id.trackdetails_webview);
        if (name!=null&&name.length()!=0) {
            trackdetails_title.setText(name);
        }else {
            trackdetails_title.setText("寻宝");
        }

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
        //传递一个Java对象，同时给他命名，这个对象可以在js中调用这个对象的方法
        webView.addJavascriptInterface(new TreasureJavaScript(this,handler), "MyJavaScript");
        //加载assets目录下的文件
        if ("Details".equals(TYPE)) {
            String url = "http://www.hlfyb.com/wechat.php/Android/treasure_index?userid=" + userid + "&&geohash="
                    + GlobalParams.Geohash + "&&area=" + GlobalParams.country_code + "&&longitude="
                    + GlobalParams.longitude + "&&latitude=" + GlobalParams.latitude;
            webView.loadUrl(url);
        }
//        webView.loadUrl("file:///android_asset/demo.html");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                error.setText("404 error");
                webView.setVisibility(View.GONE);
            }
        });
        trackdetails_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new PopupWindows(TreasureDetailsActivity.this, trackdetails_other);
                Intent intent = new Intent(TreasureDetailsActivity.this,ReleaseTreasureActivity.class);
                startActivity(intent);
            }
        });
    }

}
