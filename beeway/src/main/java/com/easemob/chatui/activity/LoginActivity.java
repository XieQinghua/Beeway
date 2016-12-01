/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.easemob.chatui.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.easemob.chatui.Constant;
import com.easemob.chatui.db.FriendDao;
import com.easemob.chatui.db.UserDao;
import com.easemob.chatui.domain.User;
import com.easemob.chatui.utils.CommonUtils;
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
import com.thvc.beeway.bean.IssueCutomerBean;
import com.thvc.beeway.bean.MyFriendBean;
import com.thvc.beeway.bean.UserInfo;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.ExampleUtil;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.utils.SharedPreferencesUtil;
import com.thvc.beeway.utils.TimeCountUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登陆页面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private EditText usernameEditText;
    private EditText passwordEditText;
    private ImageView iv_username, iv_password;
    private boolean progressShow;
    private boolean autoLogin = false;
    private boolean isauto = false;// 自动登录的标识
    private String currentUsername;
    private String currentPassword;
    private Context context;
    private TextView forget_password, login_register, login_number, login_Code;
    private String type = "number";
    private int forget = 1;
    private static List<Friend> friendlist;
    private Button iv_code;


    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static boolean isForeground = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果用户名密码都有，直接进入主页面
//        if (DemoHXSDKHelper.getInstance().isLogined()) {
//            autoLogin = true;
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            return;
//        }
        setContentView(R.layout.activity_login);
        registerMessageReceiver();
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        usernameEditText.setSelection(usernameEditText.getText().length());
        passwordEditText.setSelection(passwordEditText.getText().length());
        iv_code = (Button) findViewById(R.id.iv_code);
        iv_code.setOnClickListener(this);
        iv_username = (ImageView) findViewById(R.id.iv_username);
        iv_password = (ImageView) findViewById(R.id.iv_password);
        usernameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    iv_username.setImageResource(R.drawable.icon_account_black);
                } else {
                    iv_username.setImageResource(R.drawable.icon_account);
                }
            }
        });
        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    iv_password.setImageResource(R.drawable.icon_pwd_black);
                } else {
                    iv_password.setImageResource(R.drawable.icon_pwd);
                }
            }
        });
        // 如果用户名改变，清空密码
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordEditText.setText(null);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        if (BeewayApplication.getInstance().getUserName() != null) {
            usernameEditText.setText(BeewayApplication.getInstance().getUserName());
        }
        context = this;
        if (BeewayApplication.getInstance().getUserName() != null) {
            usernameEditText.setText(BeewayApplication.getInstance().getUserName());
        }
        isauto = BeewayApplication.getInstance().getAutologin(context);
        forget_password = (TextView) findViewById(R.id.forget_password);
        login_Code = (TextView) findViewById(R.id.login_Code);
        login_number = (TextView) findViewById(R.id.login_number);
        login_number.setOnClickListener(this);
        login_Code.setOnClickListener(this);
        login_register = (TextView) findViewById(R.id.login_register);
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("forget", forget);
                startActivity(intent);
                finish();
            }
        });
        //注册
        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(context, com.easemob.chatui.activity.RegisterActivity.class), 0);
                finish();
            }
        });
    }


    /**
     * 登录
     *
     * @param view
     */
    public void login(View view) {
        if (!CommonUtils.isNetWorkConnected(this)) {
            //判断网络是否可用
            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }
        currentUsername = usernameEditText.getText().toString().trim();
        currentPassword = passwordEditText.getText().toString().trim();
        /**
         * 判断用户 名或者密码是否有为空
         */
        if (TextUtils.isEmpty(currentUsername)) {
            Toast.makeText(this, R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(currentPassword)) {
            Toast.makeText(this, R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }


        progressShow = true;
        final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                progressShow = false;
            }
        });
//        pd.setMessage(getString(R.string.Is_landing));
//        pd.show();
        showDialog(LOADING_DIALOG);
        final long start = System.currentTimeMillis();
        if (type.length() != 0 && type != null && type.equals("number")) {
            numberLogin();

        } else if (type.length() != 0 && type != null && type.equals("code")) {
            codeLogin();
        }
    }


    /**
     * 手机号码登录
     */
    public void numberLogin() {
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
        //这里调用了环信的注册的功能，如果在服务端调用，可以更改  下面的逻辑功能主要是与服务端接口的对接的功能
        //引入httputil 网络访问的帮助类
        HttpUtils httpUtils = new HttpUtils();
//                String url = UrlPools.LOGIN_HOME+"?"+strin+_sign;
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("username", currentUsername);
        params.addQueryStringParameter("password", currentPassword);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.LOGIN_HOME + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //访问成功
                //得到用户的相关信息,并且将用户信息保存
                SharedPreferencesUtil.saveData(LoginActivity.this, "userinfo", responseInfo.result);
                String result = responseInfo.result;
                System.out.println("lagin result " + result);
                LogUtils.i(responseInfo.result);
                UserInfo userInfo = paraseData(result);
                String ss = userInfo.getInfo();
                //记录登录的用户的详细信息
                GlobalParams.mUserInfo = paraseData(result);
                if (userInfo.getStatus() == 1) {

                    //判断状态码是否为登录成功，如果成功，既可以登录环信操作
                    //在这里记录登录的用户UserId
                    GlobalParams.loginUserId = userInfo.getData().getUserid();
                    GlobalParams.nickname = userInfo.getData().getNickname();
                    Log.e("userid", GlobalParams.loginUserId);
                    Log.e("nickname", GlobalParams.nickname);
                    Toast.makeText(LoginActivity.this, GlobalParams.nickname, Toast.LENGTH_SHORT).show();
                    //获取用户的相关GEO数据
                    GlobalParams.geohash = userInfo.getData().getGeohash();
                    //存储用户名、密码和自动登录标示
                    isauto = true;
                    String userid = userInfo.getData().getUserid();
                    String mNickname = userInfo.getData().getNickname();
                    String sex = userInfo.getData().getSex();
                    String content = userInfo.getData().getContent();
                    String realname = userInfo.getData().getRealname();
                    String birthday = userInfo.getData().getBirthday();
                    String email = userInfo.getData().getEmail();
                    String wechat = userInfo.getData().getWechat();
                    String qq = userInfo.getData().getQq();
                    String weibo = userInfo.getData().getWeibo();
                    BeewayApplication.getInstance().saveParam(LoginActivity.this,
                            currentUsername,
                            currentPassword,
                            isauto,
                            userid,
                            mNickname,
                            sex,
                            content,
                            realname,
                            birthday,
                            email,
                            wechat,
                            qq,
                            weibo);
                    /**
                     * 获取附近的好友
                     */
                    getNearFrenid();
                    /**
                     * 获取蜂友
                     */
                    getFriend();
                    //如果服务端处理就不需要这段处理
                    loginHuanXin();
                } else {
                    Toast.makeText(LoginActivity.this, userInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    removeDialog();
                }

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //访问失败
                LogUtils.i(msg);
            }
        });
//            }
//        }.start();
    }

    /**
     * 验证码登录
     */
    public void codeLogin() {
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
        //这里调用了环信的注册的功能，如果在服务端调用，可以更改  下面的逻辑功能主要是与服务端接口的对接的功能
        //引入httputil 网络访问的帮助类
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("username", currentUsername);
        params.addQueryStringParameter("code", currentPassword);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.LOGIN_CODE + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //访问成功
                //得到用户的相关信息,并且将用户信息保存
                SharedPreferencesUtil.saveData(LoginActivity.this, "userinfo", responseInfo.result);
                String result = responseInfo.result;
                System.out.println("lagin result " + result);
                LogUtils.i(responseInfo.result);
                UserInfo userInfo = paraseData(result);
                if (userInfo.getStatus() == 1) {
                    //判断状态码是否为登录成功，如果成功，既可以登录环信操作
                    //在这里记录登录的用户UserId
                    GlobalParams.loginUserId = userInfo.getData().getUserid();
                    GlobalParams.nickname = userInfo.getData().getNickname();
                    Log.e("userid", GlobalParams.loginUserId);
                    Log.e("nickname", GlobalParams.nickname);
                    Toast.makeText(LoginActivity.this, GlobalParams.nickname, Toast.LENGTH_SHORT).show();
                    //获取用户的相关GEO数据
                    GlobalParams.geohash = userInfo.getData().getGeohash();
                    //存储用户名、密码和自动登录标示
                    isauto = true;
                    String userid = userInfo.getData().getUserid();
                    String mNickname = userInfo.getData().getNickname();
                    String sex = userInfo.getData().getSex();
                    String content = userInfo.getData().getContent();
                    String realname = userInfo.getData().getRealname();
                    String birthday = userInfo.getData().getBirthday();
                    String email = userInfo.getData().getEmail();
                    String wechat = userInfo.getData().getWechat();
                    String qq = userInfo.getData().getQq();
                    String weibo = userInfo.getData().getWeibo();
                    BeewayApplication.getInstance().saveParam(LoginActivity.this,
                            currentUsername,
                            currentPassword,
                            isauto,
                            userid,
                            mNickname,
                            sex,
                            content,
                            realname,
                            birthday,
                            email,
                            wechat,
                            qq,
                            weibo);
                    /**
                     * 获取附近的好友
                     */
                    getNearFrenid();
                    /**
                     * 获取蜂友
                     */
                    getFriend();

                    //如果服务端处理就不需要这段处理
                    loginHuanXin();
                } else {
                    Toast.makeText(LoginActivity.this, userInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    removeDialog();
                }

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //访问失败
                LogUtils.i(msg);
            }
        });
//            }
//        }.start();
    }

    /**
     * 获取数据
     */
    public void getNearFrenid() {

    }

    /**
     * 获取服务器保存的好友(蜂友)
     */
    public void getFriend() {
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("userid", GlobalParams.loginUserId);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.MY_FRIEND + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
//                Log.e(TAG, result);
                if (paraseMyFriendDate(result).getStatus() == 1) {
                    friendlist = paraseMyFriendDate(result).getData().getList();
//                    Log.e(TAG, friendlist.get(0).getTitle());//测试是否获得数据
                    //存入内存
                    BeewayApplication.getInstance().setFriendList(friendlist);
                    //存入db
                    FriendDao friendDao = new FriendDao(LoginActivity.this);
                    friendDao.setFriendList(friendlist);
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //访问失败
                LogUtils.i(msg);
            }
        });

    }

    /**
     * json字段的解析
     *
     * @param result
     */
    private UserInfo paraseData(String result) {
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(result, UserInfo.class);
        LogUtils.i(userInfo.getStatus() + "");
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

    private void loginHuanXin() {
        // 调用sdk登陆方法登陆聊天服务器，需要参数用户UseId+密码123456
        EMChatManager.getInstance().login(GlobalParams.loginUserId, "123456", new EMCallBack() {

            @Override
            public void onSuccess() {
                if (!progressShow) {
                    return;
                }
                // 登陆成功，保存用户名、密码、UserId
                BeewayApplication.getInstance().setUserName(currentUsername);
                BeewayApplication.getInstance().setPassword(currentPassword);
                try {
                    // ** 第一次登录或者之前logout后再登录，加载所有本地群和回话
                    // ** manually load all local groups and
                    EMGroupManager.getInstance().loadAllGroups();
                    EMChatManager.getInstance().loadAllConversations();
                    // 处理好友和群组
                    initializeContacts();
                    LogUtils.i("登录成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    // 取好友或者群聊失败，不让进入主页面
                    runOnUiThread(new Runnable() {
                        public void run() {
                            BeewayApplication.getInstance().logout(null);
                            Toast.makeText(getApplicationContext(), R.string.login_failure_failed, Toast.LENGTH_LONG).show();
                        }
                    });
                    return;
                }
                // 更新当前用户的nickname 此方法的作用是在ios离线推送时能够显示用户nick
                boolean updatenick = EMChatManager.getInstance().updateCurrentUserNick(
                        BeewayApplication.currentUserNick.trim());
                if (!updatenick) {
                    Log.e("LoginActivity", "update current user nick fail");
                }
                if (!LoginActivity.this.isFinishing()) {

                }
                removeDialog(LOADING_DIALOG);
                // 进入主页面
                Intent intent = new Intent(LoginActivity.this, com.thvc.beeway.activity.MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(final int code, final String message) {
                if (!progressShow) {
                    return;
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), getString(R.string.Login_failed) + message,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

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
        UserDao dao = new UserDao(LoginActivity.this);
        List<User> users = new ArrayList<User>(userlist.values());
        dao.saveContactList(users);
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
        if (autoLogin) {
            return;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_number:
                iv_code.setVisibility(View.GONE);
                iv_password.setImageResource(R.drawable.icon_pwd);
                login_number.setBackgroundResource(R.drawable.background_view_green_left);
                login_Code.setBackgroundColor(getResources().getColor(R.color.transparent));
                login_number.setTextColor(getResources().getColor(R.color.white));
                login_Code.setTextColor(getResources().getColor(R.color.black));
                type = "number";
                break;
            case R.id.login_Code:
                iv_code.setVisibility(View.VISIBLE);
                iv_password.setImageResource(R.drawable.icon_code);
                login_Code.setBackgroundResource(R.drawable.background_view_green_right);
                login_number.setBackgroundColor(getResources().getColor(R.color.transparent));
                login_number.setTextColor(getResources().getColor(R.color.black));
                login_Code.setTextColor(getResources().getColor(R.color.white));
                passwordEditText.setHint("验证码为4位数字");
                type = "code";
                break;
            case R.id.iv_code:
                currentUsername = usernameEditText.getText().toString().trim();
                Log.e("username", currentUsername);
                if (!currentUsername.equals("") && currentUsername != null) {
                    registercode(currentUsername);
                } else {
                    Toast.makeText(context, "请输入手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void registercode(String username) {
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("mobile", username);
        params.addQueryStringParameter("mobile", username);
        params.addQueryStringParameter("exist", "1");
        params.addQueryStringParameter("userid", "0");
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.CODE + "?" + url, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                IssueCutomerBean issueCutomerBean = issueCutomerBean(result);
                if (issueCutomerBean.getStatus().equals("1")) {
                    Toast.makeText(context, issueCutomerBean.getInfo(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, issueCutomerBean.getInfo(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //访问失败
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
            }

        });
        TimeCountUtil timeCountUtil = new TimeCountUtil(this, 60000, 1000, iv_code);
        timeCountUtil.start();
    }

    public IssueCutomerBean issueCutomerBean(String result) {
        Gson gson = new Gson();
        IssueCutomerBean issueCutomerBean = gson.fromJson(result, IssueCutomerBean.class);
        return issueCutomerBean;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exitApp();
        }
        return false;
    }

    /**
     * 极光推送
     */
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
                //setCostomMsg(showMsg.toString());
            }
        }
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }


}
