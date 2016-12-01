package com.thvc.beeway.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
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
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.LocationSource;
import com.amap.api.navi.AMapNavi;
import com.easemob.chat.EMGroupManager;
import com.easemob.chatui.activity.ChatActivity;
import com.easemob.exceptions.EaseMobException;
import com.thvc.beeway.R;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyStatic;
import com.thvc.beeway.utils.ScenicJavaScript;
import com.thvc.beeway.utils.TTSController;

//import com.baidu.navisdk.BNaviEngineManager;
//import com.baidu.navisdk.BaiduNaviManager;
//import com.baidu.navisdk.util.verify.BNKeyVerifyListener;


/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/9/16.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class ScenicDetailsActivity extends BaseActivity implements
        AMapLocationListener {
    private Context context = this;
    private WebView webView;
    private ImageView back, trackdetails_other;
    private String id, userid, name = "";
    private TextView error, trackdetails_title;


    //定位管理服务
    private LocationManagerProxy mLocationManagerProxy;
    private LocationSource.OnLocationChangedListener mListener;
    private LocationManagerProxy mAMapLocationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tarckdetails);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        userid = GlobalParams.loginUserId;
        name = intent.getStringExtra("name");
        init();
        inits();

        TTSController ttsManager = TTSController.getInstance(this);// 初始化语音模块
        ttsManager.init();
        AMapNavi.getInstance(this).setAMapNaviListener(ttsManager);// 设置语音模块播报
    }

    public void init() {
        //进度条
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
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
        webView = (WebView) findViewById(R.id.trackdetails_webview);
        if (name != null && name.length() != 0) {
            trackdetails_title.setText(name);
        } else {
            trackdetails_title.setText("景点");
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
        webView.addJavascriptInterface(new ScenicJavaScript(this, handler), "MyJavaScript");
        //加载assets目录下的文件
        String url = "http://www.hlfyb.com/wechat.php/Android/scenic_detail?id=" + id;
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
                new PopupWindows(ScenicDetailsActivity.this, trackdetails_other);
            }
        });
    }

    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    webView.loadUrl("javascript:mapNav()");
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
                case 6:
                    String groupid = (String) msg.obj;
                    addToGroup(groupid);
                    break;
            }
        }
    };

    /**
     * 进去群聊
     *
     * @param groupid
     */
    public void enterGroupChat(String groupid) {
        Intent intent = new Intent(context, ChatActivity.class);
        // it is group chat
        intent.putExtra("chatType", ChatActivity.CHATTYPE_GROUP);
        intent.putExtra("groupId", groupid);
        context.startActivity(intent);
    }

    /**
     * 加入群聊
     *
     * @param groupid
     */
    public void addToGroup(final String groupid) {
        String st1 = getResources().getString(R.string.Is_sending_a_request);
        final String st2 = getResources().getString(R.string.Request_to_join);
        final String st3 = getResources().getString(R.string.send_the_request_is);
        final String st4 = getResources().getString(R.string.Join_the_group_chat);
        final String st5 = getResources().getString(R.string.Failed_to_join_the_group_chat);
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage(st1);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        new Thread(new Runnable() {
            public void run() {
                try {
                    EMGroupManager.getInstance().joinGroup(groupid);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            pd.dismiss();
//                            Toast.makeText(context, st4, Toast.LENGTH_SHORT).show();
                            enterGroupChat(groupid);
                        }
                    });
                } catch (final EaseMobException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            pd.dismiss();
                            Toast.makeText(context, st5 + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    /**
     * 初始化定位
     */
    private void inits() {
        mLocationManagerProxy = LocationManagerProxy.getInstance(this);
        //此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        //注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
        //在定位结束后，在合适的生命周期调用destroy()方法
        //其中如果间隔时间为-1，则定位只定一次
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, 60*1000, 15, this);
        mLocationManagerProxy.setGpsEnable(false);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if(aMapLocation != null && aMapLocation.getAMapException().getErrorCode() == 0){
            //获取位置信息
            MyStatic.geoLat  = aMapLocation.getLatitude();
            MyStatic.geoLng = aMapLocation.getLongitude();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

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

            navigation.setOnClickListener(new View.OnClickListener() {
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

    /**
     * 返回键处理事件
     * */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            BeewayApplication.getInstance().exit();
            finish();
            System.exit(0);// 退出程序
        }
        return super.onKeyDown(keyCode, event);
    }

}
