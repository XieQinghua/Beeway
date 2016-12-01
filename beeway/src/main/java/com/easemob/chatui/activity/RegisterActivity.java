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

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.thvc.beeway.R;
import com.thvc.beeway.activity.ChangeDataActivity;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.IssueCutomerBean;
import com.thvc.beeway.bean.RegisterBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.utils.TimeCountUtil;


/**
 * 注册页
 */
public class RegisterActivity extends BaseActivity {
    private EditText userNameEditText;
    private EditText passwordEditText;
    private EditText codeEditText;
    private ImageView iv_username, iv_code, iv_password;
    private Button mButton, register_mButton;
    private int forget = 0;
    private TextView register_password, register_login;
    public static final String REGISTER_JUMP = "register_jump";

    private String username, pwd, code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        Intent intent = getIntent();
        forget = intent.getIntExtra("forget", 0);
        if (forget == 1) {
            userNameEditText.setHint("请输入注册时的手机号码");
            passwordEditText.setHint("请输入6—16位新密码");
            register_password.setVisibility(View.GONE);
            register_mButton.setText("确认找回");
            register_mButton.setBackgroundResource(R.drawable.button_blue_shape);
        }
    }

    /**
     * 注册逻辑的实现
     *
     * @param view
     */
    public void register(View view) {
        username = userNameEditText.getText().toString().trim();
        pwd = passwordEditText.getText().toString().trim();
        code = codeEditText.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
            userNameEditText.requestFocus();
            return;
        } else if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, getResources().getString(R.string.Password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            passwordEditText.requestFocus();
            return;
        } else if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, getResources().getString(R.string.Confirm_password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            codeEditText.requestFocus();
            return;
        }
        if (forget == 0) {
            register();
        } else if (forget == 1) {
            forgetpossword();
        }
    }

    /**
     * 服务端注册
     *
     * @param
     * @param
     * @param
     */
    private void register() {
        showDialog(LOADING_DIALOG);
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("username", username);
        params.addQueryStringParameter("password", pwd);
        params.addQueryStringParameter("code", code);
        params.addQueryStringParameter("mobile", username);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.LOGIN_ADD + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //访问成功
                String result = responseInfo.result;
                RegisterBean registerbean = paraseData(result);
                if (registerbean.getStatus() == 1) {
                    boolean isauto = true;
                    String userid = registerbean.getData().getSolevar();
                    String mNickname = registerbean.getData().getNickname();
                    String sex = 1 + "";
                    String content = "";
                    String realname = "";
                    String birthday = "";
                    String email = "";
                    String wechat = "";
                    String qq = "";
                    String weibo = "";
                    BeewayApplication.getInstance().saveParam(RegisterActivity.this,
                            username,
                            pwd,
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
                    Intent intent = new Intent(RegisterActivity.this, ChangeDataActivity.class);
                    intent.putExtra("label", REGISTER_JUMP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, registerbean.getInfo(), Toast.LENGTH_SHORT).show();
                }
                removeDialog(LOADING_DIALOG);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //访问失败
                Toast.makeText(RegisterActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 服务端忘记密码
     *
     * @param
     * @param
     * @param
     */
    private void forgetpossword() {
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("username", username);
        params.addQueryStringParameter("code", code);
        params.addQueryStringParameter("password", pwd);
        String url = params.myRequestParams(params);
        System.out.println("----String url" + UrlPools.FORGETPOSSWORD + "?" + url);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.FORGETPOSSWORD + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //访问成功
                //获得访问解析出来的json字符串
                String result = responseInfo.result;
                IssueCutomerBean forgetpossword = forgetpossword(result);
                if ("1".equals(forgetpossword.getStatus())) {
                    Toast.makeText(RegisterActivity.this, "密码已修改！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(RegisterActivity.this, forgetpossword.getInfo(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //访问失败
                Toast.makeText(RegisterActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public RegisterBean paraseData(String result) {
        Gson gson = new Gson();
        RegisterBean registerbean = gson.fromJson(result, RegisterBean.class);
        return registerbean;
    }

    public IssueCutomerBean forgetpossword(String result) {
        Gson gson = new Gson();
        IssueCutomerBean forgetpossword = gson.fromJson(result, IssueCutomerBean.class);
        return forgetpossword;
    }

    public void init() {
        userNameEditText = (EditText) findViewById(R.id.username);
        codeEditText = (EditText) findViewById(R.id.code);
        passwordEditText = (EditText) findViewById(R.id.confirm_password);
        iv_username = (ImageView) findViewById(R.id.iv_username);
        iv_code = (ImageView) findViewById(R.id.iv_code);
        iv_password = (ImageView) findViewById(R.id.iv_password);

        userNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    iv_username.setImageResource(R.drawable.icon_account_black);
                } else {
                    iv_username.setImageResource(R.drawable.icon_account);
                }
            }
        });
        codeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    iv_code.setImageResource(R.drawable.icon_code_black);
                } else {
                    iv_code.setImageResource(R.drawable.icon_code);
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

        register_mButton = (Button) findViewById(R.id.register_mButton);
        mButton = (Button) findViewById(R.id.iv_button);
        register_password = (TextView) findViewById(R.id.register_password);
        register_login = (TextView) findViewById(R.id.register_login);
        //获取验证码
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = userNameEditText.getText().toString().trim();
                Log.e("username", username);
                if (!username.equals("") && username != null) {
                    registercode(username);
                } else {
                    Toast.makeText(RegisterActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //跳转找回密码
        register_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, RegisterActivity.class);
                intent.putExtra("forget", 1);
                startActivity(intent);
                finish();
            }
        });
        //跳转登录
        register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void registercode(String username) {

        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("mobile", username);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.CODE + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //访问失败
                Toast.makeText(RegisterActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });

        TimeCountUtil timeCountUtil = new TimeCountUtil(this, 60000, 1000, mButton);
        timeCountUtil.start();


    }

}
