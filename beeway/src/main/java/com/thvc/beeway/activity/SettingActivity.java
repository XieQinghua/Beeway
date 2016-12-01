package com.thvc.beeway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thvc.beeway.R;
import com.thvc.beeway.base.BaseActivity;

/**
 * 项目名称：Beeway
 * 类描述：系统设置页面
 * 创建人：谢庆华.
 * 创建时间：2015/9/28 10:11
 * 修改人：Administrator
 * 修改时间：2015/9/28 10:11
 * 修改备注：
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_binding_email, tv_unbundling_email,
            tv_feedback, tv_beeway_introduce, tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    private void init() {
        tv_binding_email = (TextView) findViewById(R.id.tv_binding_email);
        tv_unbundling_email = (TextView) findViewById(R.id.tv_unbundling_email);

        tv_feedback = (TextView) findViewById(R.id.tv_feedback);
        tv_beeway_introduce = (TextView) findViewById(R.id.tv_beeway_introduce);
        tv_version = (TextView) findViewById(R.id.tv_version);
        tv_binding_email.setOnClickListener(this);
        tv_unbundling_email.setOnClickListener(this);

        tv_feedback.setOnClickListener(this);
        tv_beeway_introduce.setOnClickListener(this);
        tv_version.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_binding_email:

                break;
            case R.id.tv_unbundling_email:

                break;
            case R.id.tv_feedback:
                intent = new Intent(SettingActivity.this, FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_beeway_introduce:
                intent = new Intent(SettingActivity.this, IntroduceActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_version:

                break;
        }
    }
}
