package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
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

import com.thvc.beeway.R;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.utils.MyJavaScript;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/9/14.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class TrackDetailsActivity extends BaseActivity {
    private Context context = this;
    private WebView webView;
    private ImageView trackdetails_other;

    private String id, userid;
    private TextView error;
    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    String commemt[] = (String[]) msg.obj;
                    forresult(commemt[0], commemt[1], commemt[2]);
                    break;
                case 2:
                    String reple[] = (String[]) msg.obj;
                    repleresult(reple[0], reple[1], reple[2], reple[3], reple[4], reple[5]);
                    break;
                case 3:
                    webView.loadUrl("javascript:sendShare()");
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

    //    private ArrayList<TrackFragmengBean.DataEntity.ListEntity> listEntities = new ArrayList<>();
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
        error = (TextView) findViewById(R.id.trackdetails_error);
        webView = (WebView) findViewById(R.id.trackdetails_webview);
        trackdetails_other = (ImageView) findViewById(R.id.trackdetails_other);

        //设置字符集编码
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        //开启JavaScript支持
        webView.getSettings().setJavaScriptEnabled(true);


        //传递一个Java对象，同时给他命名，这个对象可以在js中调用这个对象的方法
        webView.addJavascriptInterface(new MyJavaScript(this, handler), "MyJavaScript");
        //加载assets目录下的文件
        String url = "http://www.hlfyb.com/wechat.php/Android/track_detail.html?id=" + id + "&&userid=" + userid;
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
                new PopupWindows(TrackDetailsActivity.this, trackdetails_other);
            }
        });

    }
    public String getUserId() {
        return userid;
    }

    public void forresult(String type, String dataid, String isdata) {

        System.out.println("type==" + type + " dataid=" + dataid + " isdata=" + isdata);
        int data = Integer.parseInt(isdata);
        Intent intent = new Intent(TrackDetailsActivity.this, JSRepleActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("dataid", dataid);
        intent.putExtra("isdata", data);
        startActivityForResult(intent, 1);
    }

    public void repleresult(String type, String cid, String commid, String commnick, String isdata, String dataid) {

        System.out.println("type==" + type + " dataid=" + dataid + " isdata=" + isdata);
        int data = Integer.parseInt(isdata);
        Intent intent = new Intent(TrackDetailsActivity.this, JSRepleActivity.class);
        intent.putExtra("type", "reple");
        intent.putExtra("cid", cid);
        intent.putExtra("commid", commid);
        intent.putExtra("commnick", commnick);
        intent.putExtra("isdata", isdata);
        intent.putExtra("dataid", dataid);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            String TYPE = data.getStringExtra("TYPE");
            if (TYPE.equals("success")) {
                webView.loadUrl("javascript:commonData(" + id + ")");
            }
        } else if (requestCode == 2 && resultCode == 2) {
            String TYPE = data.getStringExtra("TYPE");
            if (TYPE.equals("success")) {
                webView.loadUrl("javascript:commonData(" + id + ")");
            }
        }

    }

    public class PopupWindows extends PopupWindow {

        public PopupWindows(Context mContext, View parent) {

            View view = View.inflate(mContext, R.layout.item_popupwindows, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_ins));
            LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
            LinearLayout ll_popu_layout = (LinearLayout) view.findViewById(R.id.ll_popu_layout);
            LinearLayout ll_popu_dismiss = (LinearLayout) view.findViewById(R.id.ll_popu_dismiss);
            LinearLayout share = (LinearLayout) view.findViewById(R.id.item_popupwindows_share);
            LinearLayout navigation = (LinearLayout) view.findViewById(R.id.item_popupwindows_navigation);
            LinearLayout customer = (LinearLayout) view.findViewById(R.id.item_popupwindows_customer);
            LinearLayout collect = (LinearLayout) view.findViewById(R.id.item_popupwindows_collect);
            ll_popup.setVisibility(View.GONE);
            navigation.setVisibility(View.GONE);
            customer.setVisibility(View.GONE);
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
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            Message msg = Message.obtain();
                            msg.what = 3;
//                            msg.obj=comment;//将集合传递给主线程
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
//                            msg.obj=comment;//将集合传递给主线程
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
