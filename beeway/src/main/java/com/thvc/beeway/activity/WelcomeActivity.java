package com.thvc.beeway.activity;

import android.content.Intent;
import android.os.Bundle;

import com.easemob.chatui.activity.SplashActivity;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.utils.SharedPreferencesUtil;

/**
 * 项目名称：Beeway
 * 类描述：程序的欢迎界面，此Acticity用于判断用户是否第一次登录程序
 * 创建人：谢庆华.
 * 创建时间：2015/8/13 9:53
 * 修改人：Administrator
 * 修改时间：2015/8/13 9:53
 * 修改备注：
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
        boolean b = (boolean) SharedPreferencesUtil.getData(BeewayApplication.getContext(), "is_first", true);
        Intent intent;
        if (b) {
            // 第一次进入应用，进入引导页面
            intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            SharedPreferencesUtil.saveData(BeewayApplication.getContext(), "is_first", false);
            finish();
        } else {
            // 不是第一次进入，进入应用程序的开屏页面
            intent = new Intent(this, SplashActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
