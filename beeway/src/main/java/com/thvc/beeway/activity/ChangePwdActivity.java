package com.thvc.beeway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chatui.activity.LoginActivity;
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
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;

/**
 * 项目名称：Beeway
 * 类描述：修改密码页面
 * 创建人：谢庆华.
 * 创建时间：2015/9/28 15:29
 * 修改人：Administrator
 * 修改时间：2015/9/28 15:29
 * 修改备注：
 */
public class ChangePwdActivity extends BaseActivity {
    private final static String TAG = "ChangePwdActivity";
    private EditText et_old_pwd, et_new_pwd, et_again_new_pwd;
    String password, newpwd, again_newpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        init();
    }

    private void init() {
        et_old_pwd = (EditText) findViewById(R.id.et_old_pwd);
        et_new_pwd = (EditText) findViewById(R.id.et_new_pwd);
        et_again_new_pwd = (EditText) findViewById(R.id.et_again_new_pwd);

    }

    public void confirm(View v) {
        password = et_old_pwd.getText().toString().trim();
        newpwd = et_new_pwd.getText().toString().trim();
        again_newpwd = et_again_new_pwd.getText().toString().trim();
        if (password.equals(BeewayApplication.getInstance().getPassword().toString())) {
            if (newpwd.length() < 6 || again_newpwd.length() < 6) {
                Toast.makeText(ChangePwdActivity.this, "请输入6-16位新密码", Toast.LENGTH_SHORT).show();
            } else {
                if (!newpwd.equals(again_newpwd)) {
                    Toast.makeText(ChangePwdActivity.this, "前后两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
                } else {
                    confirmChange();
                }
            }
        } else {
            Toast.makeText(ChangePwdActivity.this, "请输入原始密码", Toast.LENGTH_SHORT).show();
        }
    }

    private void confirmChange() {
        showDialog(LOADING_DIALOG);
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("userid", BeewayApplication.getInstance().getmUserid(this));
        params.addQueryStringParameter("password", password);
        params.addQueryStringParameter("newpwd", newpwd);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.CHANGE_PWD + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Log.e(TAG, result);
                ChangePwdBean changePwdBean = paraseChangePwdBean(result);
                if (changePwdBean.getStatus() == 1) {
                    Toast.makeText(ChangePwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    //直接执行注销登录操作不提示弹出框
                    new BeewayApplication().clearsaveParam(ChangePwdActivity.this);
                    BeewayApplication.getInstance().logout(new EMCallBack() {

                        @Override
                        public void onSuccess() {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    // 重新显示登陆页面
                                    finish();
                                    startActivity(new Intent(ChangePwdActivity.this, LoginActivity.class));
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
                removeDialog(LOADING_DIALOG);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtils.i(s);
            }
        });
    }

    /**
     * 修改密码成功返回bean文件
     */
    public class ChangePwdBean {
        /**
         * data : 60
         * status : 1
         * info : 修改密码成功
         */
        private String data;
        private int status;
        private String info;

        public void setData(String data) {
            this.data = data;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getData() {
            return data;
        }

        public int getStatus() {
            return status;
        }

        public String getInfo() {
            return info;
        }
    }

    /**
     * 修改密码成功返回json字段解析
     *
     * @param result
     */
    private ChangePwdBean paraseChangePwdBean(String result) {
        Gson gson = new Gson();
        ChangePwdBean changePwdBean = gson.fromJson(result, ChangePwdBean.class);
        LogUtils.i(changePwdBean.getStatus() + "");
        return changePwdBean;
    }
}

