package com.thvc.beeway.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.thvc.beeway.R;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.CustomerJavaScript;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/9/18.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class CustomerDetailsActivity extends BaseActivity {
    private Context context = this;
    private WebView webView;
    private ImageView back, trackdetails_other;
    private String id, userid;
    private TextView error, trackdetails_title;
    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    over();
                    break;
                case 2:
                    webView.loadUrl("javascript:sendShare()");
                    break;
                case 3:
                    webView.loadUrl("javascript:wantTo()");
                    break;
                case 4:
                    webView.loadUrl("javascript:collect()");
                    break;
                case 5:
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
        id = intent.getStringExtra("id");
        userid = intent.getStringExtra("userid");

        init();

    }

    public void init() {
        //进度条
        showDialog(LOADING_DIALOG);
        webView = (WebView) findViewById(R.id.trackdetails_webview);
        back = (ImageView) findViewById(R.id.trackdetails_back);
        trackdetails_other = (ImageView) findViewById(R.id.trackdetails_other);
        error = (TextView) findViewById(R.id.trackdetails_error);
        trackdetails_title = (TextView) findViewById(R.id.trackdetails_title);
        webView = (WebView) findViewById(R.id.trackdetails_webview);
        trackdetails_title.setText("众筹");

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
        webView.addJavascriptInterface(new CustomerJavaScript(this, handler), "MyJavaScript");
        //加载assets目录下的文件
        String url = "http://www.hlfyb.com/wechat.php/Android/want_detail?id=" + id + "&&userid=" + userid;
        webView.loadUrl(url);
//        webView.loadUrl("file:///android_asset/demo.html");
        webView.setWebViewClient(new WebViewClient() {
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
                new PopupWindows(CustomerDetailsActivity.this, trackdetails_other);
            }
        });

    }

    public void over() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.alertdialog_customer, null);
        dialog.setView(view);
        dialog.setPositiveButton("结束", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                httpOver();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.create().show();

    }

    public void httpOver() {
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("noteid", id);
        params.addQueryStringParameter("userid", userid);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.OVERCUSTOMER + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //获得访问解析出来的json字符串
                String result = responseInfo.result;
                System.out.println("取消众筹    result====" + result);

            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    public class PopupWindows extends PopupWindow {

        public PopupWindows(Context mContext, View parent) {

            View view = View.inflate(mContext, R.layout.item_popupwindows, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_ins));
            LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
            LinearLayout ll_popu_layout = (LinearLayout) view.findViewById(R.id.ll_popu_layout);
            LinearLayout ll_popu_dismiss = (LinearLayout) view.findViewById(R.id.ll_popu_dismiss);
            LinearLayout share = (LinearLayout) view.findViewById(R.id.item_popupwindows_share);
            LinearLayout end = (LinearLayout) view.findViewById(R.id.item_popupwindows_end);
            LinearLayout navigation = (LinearLayout) view.findViewById(R.id.item_popupwindows_navigation);
            LinearLayout customer = (LinearLayout) view.findViewById(R.id.item_popupwindows_customer);
            LinearLayout collect = (LinearLayout) view.findViewById(R.id.item_popupwindows_collect);
            if (userid.equals(GlobalParams.loginUserId)) {
                end.setVisibility(View.VISIBLE);
            }
            navigation.setVisibility(View.GONE);
            ll_popup.setVisibility(View.GONE);
            ll_popu_layout.setVisibility(View.VISIBLE);
            ll_popu_layout.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_bottom_in_2));

            setWidth(ViewGroup.LayoutParams.FILL_PARENT);
            //修改高度显示，解决被手机底部虚拟键挡住的问题
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            //实例化一个ColorDrawable颜色为半透明
            setBackgroundDrawable(new ColorDrawable(0xb0000000));
            //menuview添加ontouchlistener监听判断获取触屏位置如果在选择框外面则销毁弹出框
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int height = view.findViewById(R.id.ll_popup).getTop();
                    int y = (int) motionEvent.getY();
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        if (y < height) {
                            dismiss();
                        }
                    }
                    return true;
                }
            });
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            update();
            end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            Message msg = Message.obtain();
                            msg.what = 1;
                            handler.sendMessage(msg);
                        }
                    }.start();
                    dismiss();
                }
            });
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            Message msg = Message.obtain();
                            msg.what = 2;
                            handler.sendMessage(msg);
                        }
                    }.start();
                    dismiss();
                }
            });
            customer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            Message msg = Message.obtain();
                            msg.what = 3;
                            handler.sendMessage(msg);
                        }
                    }.start();
                    dismiss();
                }
            });
            collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            Message msg = Message.obtain();
                            msg.what = 4;
                            handler.sendMessage(msg);
                        }
                    }.start();
                    dismiss();
                }
            });
            ll_popu_dismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }
    }
}
