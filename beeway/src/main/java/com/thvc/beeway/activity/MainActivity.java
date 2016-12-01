package com.thvc.beeway.activity;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.EMEventListener;
import com.easemob.EMNotifierEvent;
import com.easemob.chatui.activity.AlertDialog;
import com.easemob.chatui.activity.LoginActivity;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.thvc.beeway.R;
import com.thvc.beeway.Release.MessageCenterDao;
import com.thvc.beeway.Zing.MyErweimaActivity;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.SetGeohash;
import com.thvc.beeway.fragment.DialogFragment;
import com.thvc.beeway.fragment.FindFragment;
import com.thvc.beeway.fragment.HomeActivity;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.AppManager;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;
import com.thvc.beeway.view.DragLayout;
import com.thvc.beeway.view.MainContentLayout;
import com.thvc.beeway.view.MyMessagePerson;

import java.util.List;

/**
 * 项目名称：Beeway
 * 类描述：主界面
 * 创建人：谢庆华
 * 创建时间：2015/8/12 10:07
 * 修改人：Administrator
 * 修改时间：2015/8/12 10:07
 * 修改备注：
 */
public class MainActivity extends TabActivity implements EMEventListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    public static RadioGroup radioGroup;
    private RelativeLayout left_menu_layout1, left_menu_layout2,
            left_menu_layout3, left_menu_layout4, left_menu_layout5, left_menu_layout6;
    private ImageView left_menu_img, left_menu_erweima;
    private CircleImageView hendimage;
    public static TabHost tabHost;
    private MainContentLayout mMainContent;
    private static DragLayout mDragLayout;
    private TextView nickname;
    private Context context;
    // 未读消息textview
    private TextView unreadLabel;
    private Button mainTabs_radio_add, mainTabs_radio_more;
    private static RadioButton mainTabs_radio_home, mainTabs_radio_performance, mainTabs_radio_account;
    public static final int CANCEL_LOGIN = 1;
    public static final String HOME_JUMP = "home";
    public static final String LEFT_JUMP = "left";

    private ImageView im_prompt;
    private String status;
    private String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        AppManager.getAppManager().addActivity(this);
        context = getApplication();
        initView();
        setGeohash();

    }

    public void initView() {

        im_prompt = (ImageView) this.findViewById(R.id.im_prompt);//推送消息提示图片
        userid = BeewayApplication.getInstance().getmUserid(this);//拿到当前用户的用户名

        tabHost = this.getTabHost();
        // 跳转首页
        tabHost.addTab(tabHost.newTabSpec("1").setIndicator("1")
                .setContent(new Intent(this, HomeActivity.class)));
        //跳转聊天
        tabHost.addTab(tabHost.newTabSpec("2").setIndicator("2")
                .setContent(new Intent(this, DialogFragment.class).putExtra("label", HOME_JUMP)));
        //跳转发布onClick实现
        //跳转发现
        tabHost.addTab(tabHost.newTabSpec("4").setIndicator("4")
                .setContent(new Intent(this, FindFragment.class)));
        //跳转我的onClick实现
        radioGroup = (RadioGroup) findViewById(R.id.main_radio);
        mainTabs_radio_add = (Button) findViewById(R.id.mainTabs_radio_add);
        mainTabs_radio_more = (Button) findViewById(R.id.mainTabs_radio_more);
        hendimage = (CircleImageView) findViewById(R.id.left_menu_img);
        hendimage.setImageUrl(UrlPools.getAvatarUrl(this), VolleyHepler.getInstance().getImageLoader());
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.mainTabs_radio_home);// 默认第一个按钮
        ViewTreeObserver vto2 = radioGroup.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                radioGroup.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        mainTabs_radio_add.setOnClickListener(this);
        mainTabs_radio_more.setOnClickListener(this);

//        unreadLabel = (TextView) findViewById(R.id.unread_msg_number);
//        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        lp.setMargins(wm.getDefaultDisplay().getWidth() * 1 / 3, wm.getDefaultDisplay().getHeight() - radioGroup.getHeight(), 0, 0);
//        unreadLabel.setLayoutParams(lp);

        nickname = (TextView) findViewById(R.id.left_menu_title);
        nickname.setText(BeewayApplication.getInstance().getmNickname(this));
        left_menu_img = (ImageView) findViewById(R.id.left_menu_img);
        left_menu_erweima = (ImageView) findViewById(R.id.left_menu_erweima);
        left_menu_layout1 = (RelativeLayout) findViewById(R.id.left_menu_layout1);
        left_menu_layout2 = (RelativeLayout) findViewById(R.id.left_menu_layout2);
        left_menu_layout3 = (RelativeLayout) findViewById(R.id.left_menu_layout3);
        left_menu_layout4 = (RelativeLayout) findViewById(R.id.left_menu_layout4);
        left_menu_layout5 = (RelativeLayout) findViewById(R.id.left_menu_layout5);
        left_menu_layout6 = (RelativeLayout) findViewById(R.id.left_menu_layout6);
        mainTabs_radio_home = (RadioButton) findViewById(R.id.mainTabs_radio_home);
        mainTabs_radio_performance = (RadioButton) findViewById(R.id.mainTabs_radio_performance);
        mainTabs_radio_account = (RadioButton) findViewById(R.id.mainTabs_radio_account);
        mMainContent = (MainContentLayout) findViewById(R.id.mainContent);
        mDragLayout = (DragLayout) findViewById(R.id.Draglayout);
        mMainContent.setDragLayout(mDragLayout);
        mDragLayout.setOnLayoutDragingListener(new DragLayout.OnLayoutDragingListener() {

            @Override
            public void onOpen() {
                //打开
            }

            @Override
            public void onDraging(float percent) {
                //滑动中
//                ViewHelper.setAlpha(imageView, 1 - percent);
            }

            @Override
            public void onClose() {
                //关闭
            }
        });
        //蜂友头像
        left_menu_img.setOnClickListener(this);
        //蜂友二维码
        left_menu_erweima.setOnClickListener(this);
        //蜂友首页
        left_menu_layout1.setOnClickListener(this);
        //会员中心
        left_menu_layout2.setOnClickListener(this);
        //我的消息
        left_menu_layout3.setOnClickListener(this);
        //我的足迹
        left_menu_layout4.setOnClickListener(this);
        //我的游记
        left_menu_layout5.setOnClickListener(this);
        //注销登录
        left_menu_layout6.setOnClickListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.mainTabs_radio_home:
                tabHost.setCurrentTabByTag("1");
                break;

            case R.id.mainTabs_radio_performance:
                tabHost.setCurrentTabByTag("2");
                break;

            case R.id.mainTabs_radio_account:
                tabHost.setCurrentTabByTag("4");
                break;

        }
    }


    //当ViewPager切换时，只要给DragLayout设置是否允许侧滑
    public static void mOpen(int position) {
        switch (position) {
            case 0:
                mDragLayout.setDrag(true);
                break;
            case 1:
                mDragLayout.setDrag(false);
                break;
            case 2:
                mDragLayout.setDrag(false);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //点击Alert确定则执行注销操作
        if (requestCode == CANCEL_LOGIN && resultCode == RESULT_OK) {
            BeewayApplication.getInstance().clearsaveParam(MainActivity.this);
            BeewayApplication.getInstance().logout(new EMCallBack() {

                @Override
                public void onSuccess() {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            // 重新显示登陆页面
                            finish();
                            startActivity(new Intent(context, LoginActivity.class));
                        }
                    });
                }

                @Override
                public void onProgress(int progress, String status) {
                }

                @Override
                public void onError(int code, String message) {
                }
            });
        }

    }

    public static void mMenu() {
        mDragLayout.open();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //getMenuInflater().inflate(R.menu.context_tab_contact, menu);
    }

    @Override
    public void onEvent(EMNotifierEvent emNotifierEvent) {

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.mainTabs_radio_add:
                new SelectAddPopupWindow(MainActivity.this, mainTabs_radio_add);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                break;
            case R.id.mainTabs_radio_more:
                startActivity(new Intent(this, MemberCenterActivity.class));
                break;
            case R.id.left_menu_img:
                startActivity((new Intent(MainActivity.this, MemberCenterActivity.class)));
                break;
            case R.id.left_menu_erweima:
                startActivity(new Intent(MainActivity.this, MyErweimaActivity.class));
                break;
            case R.id.left_menu_layout1:
                mDragLayout.close();
                tabHost.setCurrentTabByTag("1");
                mainTabs_radio_home.setChecked(true);
                mainTabs_radio_account.setChecked(false);
                mainTabs_radio_performance.setChecked(false);
                break;
            case R.id.left_menu_layout2:
                startActivity((new Intent(MainActivity.this, MemberCenterActivity.class)).putExtra("label", LEFT_JUMP));
                break;
            case R.id.left_menu_layout3:
                startActivity(new Intent(MainActivity.this, MyMessageActivity.class));
                break;
            case R.id.left_menu_layout4:
                startActivity(new Intent(MainActivity.this, MyTrackActivity.class));
                break;
            case R.id.left_menu_layout5:
                startActivity(new Intent(MainActivity.this, MyTravelsActivity.class));
                break;
            case R.id.left_menu_layout6:
                //弹出提示框，是否注销登录操作
                intent = new Intent(MainActivity.this, AlertDialog.class);
                intent.putExtra("msg", "是否继续注销登录操作?");
                intent.putExtra("cancel", true);
                intent.putExtra("position", 1);
                startActivityForResult(intent, CANCEL_LOGIN);
                break;
        }
    }


    /**
     * 选择添加足迹、添加游记的PopupWindow
     */
    public class SelectAddPopupWindow extends PopupWindow {
        public SelectAddPopupWindow(Context context, View parent) {
            super(context);
            View view = View.inflate(context, R.layout.popup_select_add, null);
            view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_ins));
            LinearLayout ll_add_popup = (LinearLayout) view.findViewById(R.id.ll_add_popup);
            ll_add_popup.startAnimation(AnimationUtils.loadAnimation(context, R.anim.push_bottom_in_2));

            setWidth(ViewGroup.LayoutParams.FILL_PARENT);
            //修改高度显示，解决被手机底部虚拟键挡住的问题
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            //实例化一个ColorDrawable颜色为半透明背景
            setBackgroundDrawable(new ColorDrawable(0xD0000000));
            //设置背景模糊效果
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                    WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
            //menuview添加ontouchlistener监听判断获取触屏位置如果在选择框外面则销毁弹出框
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int top_height = view.findViewById(R.id.ll_add_popup).getTop();
                    int bottom_height = view.findViewById(R.id.ll_add_popup).getBottom();
                    int left_width = view.findViewById(R.id.ll_add_popup).getLeft();
                    int right_width = view.findViewById(R.id.ll_add_popup).getRight();
                    int y = (int) motionEvent.getY();
                    int x = (int) motionEvent.getX();
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        if (y < top_height || y > bottom_height || x < left_width || x > right_width) {
                            dismiss();
                        }
                    }
                    return true;
                }
            });
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.CENTER, 0, 0);
            update();

            Button bt_add_track = (Button) view.findViewById(R.id.bt_add_track);//发布足迹按钮
            Button bt_add_travel = (Button) view.findViewById(R.id.bt_add_travel);//发布游记按钮

            bt_add_track.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    new SelectLabelPopupWindow(MainActivity.this, mainTabs_radio_add);
                    dismiss();
                }
            });
            bt_add_travel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AddTravelActivity.class);
                    startActivity(intent);
                    dismiss();
                }
            });
        }
    }

    //发布足迹标签的PopupWindow
    public class SelectLabelPopupWindow extends PopupWindow {
        public SelectLabelPopupWindow(Context context, View parent) {
            super(context);
            View view = View.inflate(context, R.layout.popup_select_label, null);
            view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_ins));
            LinearLayout ll_label = (LinearLayout) view.findViewById(R.id.ll_label);
            ll_label.startAnimation(AnimationUtils.loadAnimation(context, R.anim.push_bottom_in_2));
            TextView tv_label_cancel = (TextView) view.findViewById(R.id.tv_label_cancel);
            tv_label_cancel.startAnimation(AnimationUtils.loadAnimation(context, R.anim.push_bottom_in_2));
            setWidth(ViewGroup.LayoutParams.FILL_PARENT);
            //修改高度显示，解决被手机底部虚拟键挡住的问题
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            //实例化一个ColorDrawable颜色为半透明
            setBackgroundDrawable(new ColorDrawable(0xD0000000));
            //menuview添加ontouchlistener监听判断获取触屏位置如果在选择框外面则销毁弹出框
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int top_height = view.findViewById(R.id.ll_label).getTop();
                    int bottom_height = view.findViewById(R.id.ll_label).getBottom();
                    int left_width = view.findViewById(R.id.ll_label).getLeft();
                    int right_width = view.findViewById(R.id.ll_label).getRight();
                    int y = (int) motionEvent.getY();
                    int x = (int) motionEvent.getX();
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        if (y < top_height || y > bottom_height || x < left_width || x > right_width) {
                            dismiss();
                        }
                    }
                    return true;
                }
            });
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.CENTER, 0, 0);
            update();
            TextView tv_label_chihuo = (TextView) view.findViewById(R.id.tv_label_chihuo);
            TextView tv_label_jingse = (TextView) view.findViewById(R.id.tv_label_jingse);
            TextView tv_label_gouwu = (TextView) view.findViewById(R.id.tv_label_gouwu);

            TextView tv_label_yueban = (TextView) view.findViewById(R.id.tv_label_yueban);
            TextView tv_label_huodong = (TextView) view.findViewById(R.id.tv_label_huodong);
            TextView tv_label_tucao = (TextView) view.findViewById(R.id.tv_label_tucao);


            TextView tv_label_sheying = (TextView) view.findViewById(R.id.tv_label_sheying);
            TextView tv_label_gankai = (TextView) view.findViewById(R.id.tv_label_gankai);
            TextView tv_label_qita = (TextView) view.findViewById(R.id.tv_label_qita);


            intentAddTrack(tv_label_chihuo);
            intentAddTrack(tv_label_jingse);
            intentAddTrack(tv_label_gouwu);

            intentAddTrack(tv_label_yueban);
            intentAddTrack(tv_label_huodong);
            intentAddTrack(tv_label_tucao);

            intentAddTrack(tv_label_sheying);
            intentAddTrack(tv_label_gankai);
            intentAddTrack(tv_label_qita);

        }

        public void intentAddTrack(final TextView textview) {
            textview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AddTrackActivity.class);
                    intent.putExtra("label", textview.getText());
                    startActivity(intent);
                    dismiss();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //查询数据库 显示推送消息提示
        MessageCenterDao messageCenterDao = new MessageCenterDao(getApplication());
        List<MyMessagePerson> persons = messageCenterDao.select(userid);
        for (MyMessagePerson person : persons) {
            status = person.getStatuss();//拿到数据库里的标示
            if (persons.size() > 0 && status.equals("0")) {
                im_prompt.setVisibility(View.VISIBLE);
            } else {
                im_prompt.setVisibility(View.GONE);
            }
        }
    }

    public void setGeohash() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                String latitude = GlobalParams.latitude;
                String longitude = GlobalParams.longitude;
                String userid = GlobalParams.loginUserId;
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("latitude", latitude);
                params.addQueryStringParameter("longitude", longitude);
                params.addQueryStringParameter("userid", userid);
                String url = params.myRequestParams(params);
                Log.e("setgeohash=", UrlPools.SETGEOHASH + "?" + url);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.SETGEOHASH + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
//                        Log.e("setgeohash=", result);
                        SetGeohash setGeohash = setGeohash(result);
                        if (setGeohash.getStatus() == 1) {
                            GlobalParams.Geohash = setGeohash.getData().getGeohash();
                            GlobalParams.country_code = setGeohash.getData().getArea().getAddressComponent().getCountry_code();

                        } else {
                            Toast.makeText(context, setGeohash.getInfo(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(context, "网络连接失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
    }

    public SetGeohash setGeohash(String result) {
        Gson gson = new Gson();
        SetGeohash setGeohash = gson.fromJson(result, SetGeohash.class);
        return setGeohash;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            Toast.makeText(MainActivity.this, "监听到了木有？", Toast.LENGTH_SHORT).show();
            new ExitPopupWindows(MainActivity.this, mainTabs_radio_home);
        }
        return false;
    }

    public class ExitPopupWindows extends PopupWindow {

        public ExitPopupWindows(Context mContext, View parent) {

            View view = View.inflate(mContext, R.layout.item_popupwindows, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_ins));
            LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
            ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_bottom_in_2));
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
            Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);//拍照按钮
            Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);//选取照片按钮
            Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);//取消按钮
            bt1.setText("意见反馈");
            bt2.setText("退出蜂友");
            bt1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, FeedbackActivity.class);
                    startActivity(intent);
                    dismiss();
                }
            });
            bt2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    BaseActivity.exitApp();
                    dismiss();
                }
            });

            bt3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }


}
