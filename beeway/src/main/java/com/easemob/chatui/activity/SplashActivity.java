package com.easemob.chatui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.easemob.chatui.Constant;
import com.easemob.chatui.DemoHXSDKHelper;
import com.easemob.chatui.db.FriendDao;
import com.easemob.chatui.db.UserDao;
import com.easemob.chatui.domain.User;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.thvc.beeway.R;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.Friend;
import com.thvc.beeway.bean.MyFriendBean;
import com.thvc.beeway.bean.UserInfo;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 开屏页
 */
public class SplashActivity extends BaseActivity {
    private RelativeLayout rootLayout;
    private TextView versionText;
    private Boolean isAuto = false;
    private String mUsername, mPassword, mUserid;
    private Context context;
    private static final int sleepTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        super.onCreate(savedInstanceState);

        rootLayout = (RelativeLayout) findViewById(R.id.splash_root);
        versionText = (TextView) findViewById(R.id.tv_version);

//        versionText.setText(getVersion());
        //设置淡进淡出动画
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        rootLayout.startAnimation(animation);
        context = this;
        isAuto = BeewayApplication.getInstance().getAutologin(context);
        mUsername = BeewayApplication.getInstance().getmUserName(context);
        mPassword = BeewayApplication.getInstance().getUserPassword(context);
        mUserid = BeewayApplication.getInstance().getmUserid(context);
    }


    private void login(String mUsername, String mPassword) {
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("username", mUsername);
        params.addQueryStringParameter("password", mPassword);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.LOGIN_HOME + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                UserInfo userInfo = paraseData(result);
                /**
                 * 获取附近的好友
                 */
                // LoginActivity.getNearFrenid();
                //判断状态码是否为登录成功，如果成功，既可以登录环信操作
                if (userInfo.getStatus() == 1) {
                    //如果服务端处理就不需要这段处理
//                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    //在这里记录登录的用户UserId
                    GlobalParams.loginUserId = userInfo.getData().getUserid();
                    //获取用户的相关GEO数据
                    GlobalParams.geohash = userInfo.getData().getGeohash();
                    /**
                     * 获取服务器保存的好友(蜂友)
                     */
                    HttpUtils httpUtils = new HttpUtils();
                    MyRequestParams params = new MyRequestParams();
                    params.addQueryStringParameter("userid", mUserid);
                    String url = params.myRequestParams(params);
                    httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.MY_FRIEND + "?" + url, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            String result = responseInfo.result;
                            if (paraseMyFriendDate(result).getStatus() == 1) {
                                List<Friend> friendlist = paraseMyFriendDate(result).getData().getList();
                                //存入内存
                                BeewayApplication.getInstance().setFriendList(friendlist);
                                //存入db
                                FriendDao friendDao = new FriendDao(SplashActivity.this);
                                friendDao.setFriendList(friendlist);
                            }
                        }

                        @Override
                        public void onFailure(HttpException error, String msg) {
                            //访问失败
                            LogUtils.i(msg);
                        }
                    });
                    loginHuanXin();
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    Toast.makeText(SplashActivity.this, userInfo.getInfo(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //访问失败
                LogUtils.i(msg);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("isAuto====" + isAuto);
        if (isAuto) {
            login(mUsername, mPassword);
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }

    }

//    private void loginHuanXin() {
//        // 调用sdk登陆方法登陆聊天服务器，需要参数用户UseId+密码123456
//        EMChatManager.getInstance().login(mUserid, "123456", new EMCallBack() {
//            @Override
//            public void onSuccess() {
//                try {
//                    // ** 第一次登录或者之前logout后再登录，加载所有本地群和回话
//                    // ** manually load all local groups and
//                    EMGroupManager.getInstance().loadAllGroups();
//                    EMChatManager.getInstance().loadAllConversations();
//                    // 处理好友和群组
////                    initializeContacts();
//                    LogUtils.i("登录成功");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    // 取好友或者群聊失败，不让进入主页面
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            BeewayApplication.getInstance().logout(null);
//                            Toast.makeText(getApplicationContext(), R.string.login_failure_failed, Toast.LENGTH_LONG).show();
//                        }
//                    });
//                    return;
//                }
//                // 更新当前用户的nickname 此方法的作用是在ios离线推送时能够显示用户nick
//                boolean updatenick = EMChatManager.getInstance().updateCurrentUserNick(
//                        BeewayApplication.currentUserNick.trim());
//                if (!updatenick) {
//                    Log.e("LoginActivity", "update current user nick fail");
//                }
//
//                removeDialog(LOADING_DIALOG);
//                // 进入主页面
//                startActivity(new Intent(SplashActivity.this, com.thvc.beeway.activity.MainActivity.class));
//                finish();
//            }
//
//            @Override
//            public void onProgress(int progress, String status) {
//            }
//
//            @Override
//            public void onError(final int code, final String message) {
//
//                runOnUiThread(new Runnable() {
//                    public void run() {
////                               Toast.makeText(getApplicationContext(), getString(R.string.Login_failed) + message,
////                                        Toast.LENGTH_SHORT).show();
//                        Toast.makeText(SplashActivity.this, "请重新登录", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                        finish();
//                    }
//                });
//            }
//        });
//    }

    private void initializeContacts() {
        Map<String, User> userlist = new HashMap<String, User>();
        // 添加user"申请与通知"
        User newFriends = new User();
        newFriends.setUsername(Constant.NEW_FRIENDS_USERNAME);
        String strChat = getResources().getString(
                R.string.Application_and_notify);
        newFriends.setNick(strChat);

        userlist.put(Constant.NEW_FRIENDS_USERNAME, newFriends);
        // 添加"群聊"
        User groupUser = new User();
        String strGroup = getResources().getString(R.string.group_chat);
        groupUser.setUsername(Constant.GROUP_USERNAME);
        groupUser.setNick(strGroup);
        groupUser.setHeader("");
        userlist.put(Constant.GROUP_USERNAME, groupUser);

        // 存入内存
        BeewayApplication.getInstance().setContactList(userlist);
        // 存入db
        UserDao dao = new UserDao(SplashActivity.this);
        List<User> users = new ArrayList<User>(userlist.values());
        dao.saveContactList(users);
    }

    /**
     * 登录环信
     */
    private void loginHuanXin() {
        //判断是否已经登录过环信
        new Thread(new Runnable() {
            public void run() {
                boolean isLogin = DemoHXSDKHelper.getInstance().isLogined();
                System.out.print("" + isLogin);
                if (DemoHXSDKHelper.getInstance().isLogined()) {
                    // ** 免登陆情况 加载所有本地群和会话
                    //不是必须的，不加sdk也会自动异步去加载(不会重复加载)；
                    //加上的话保证进了主页面会话和群组都已经load完毕
                    long start = System.currentTimeMillis();
                    EMGroupManager.getInstance().loadAllGroups();
                    EMChatManager.getInstance().loadAllConversations();
                    long costTime = System.currentTimeMillis() - start;
                    //等待sleeptime时长
                    if (sleepTime - costTime > 0) {
                        try {
                            Thread.sleep(sleepTime - costTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //进入主页面
                    startActivity(new Intent(SplashActivity.this, com.thvc.beeway.activity.MainActivity.class));
                    finish();
                } else {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                    }
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }).start();

    }

    /**
     * 获取当前应用程序的版本号
     */
    private String getVersion() {
        String st = getResources().getString(R.string.Version_number_is_wrong);
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packinfo = pm.getPackageInfo(getPackageName(), 0);
            String version = packinfo.versionName;
            return version;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return st;
        }
    }

    private UserInfo paraseData(String result) {
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(result, UserInfo.class);
        return userInfo;
    }

    /**
     * 我的蜂友json字段解析
     *
     * @param result
     * @return
     */
    private MyFriendBean paraseMyFriendDate(String result) {
        Gson gson = new Gson();
        MyFriendBean myFriendBean = gson.fromJson(result, MyFriendBean.class);
        return myFriendBean;
    }

}
