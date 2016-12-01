package com.thvc.beeway.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.thvc.beeway.R;
import com.thvc.beeway.Zing.MipcaActivityCapture;
import com.thvc.beeway.Zing.MyErweimaActivity;
import com.thvc.beeway.activity.CollectActivity;
import com.thvc.beeway.activity.CustomizationActivity;
import com.thvc.beeway.activity.IntegralActivity;
import com.thvc.beeway.activity.ScenicActivity;
import com.thvc.beeway.activity.TreasureDetailsActivity;
import com.thvc.beeway.base.BaseActivity;

/**
 * com.thvc.fragment
 * 创建日期： 2015/8/12.
 * 版权：天合融创
 * 作者：.
 * 版本号：1.0.
 * 修改历史：
 */
public class FindFragment extends BaseActivity implements View.OnClickListener {
    public TextView tv_jingdian;
    public TextView tv_dingzhi;
    public TextView tv_shoucang;
    public TextView tv_saoyisao;
    public TextView tv_erweima;
    public TextView tv_xunbao;
    private TextView tv_fengmiduihuan;

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_find);
        tv_jingdian = (TextView) findViewById(R.id.tv_jingdian);
        tv_dingzhi = (TextView) findViewById(R.id.tv_dingzhi);
        tv_shoucang = (TextView) findViewById(R.id.tv_shoucang);
        tv_saoyisao = (TextView) findViewById(R.id.tv_saoyisao);
        tv_erweima = (TextView) findViewById(R.id.tv_erweima);
        tv_xunbao = (TextView) findViewById(R.id.tv_xunbao);
        tv_fengmiduihuan = (TextView) this.findViewById(R.id.tv_fengmiduihuan);
        //为TextView设置点击事件
        tv_jingdian.setOnClickListener(this);
        tv_dingzhi.setOnClickListener(this);
        tv_shoucang.setOnClickListener(this);
        tv_saoyisao.setOnClickListener(this);
        tv_erweima.setOnClickListener(this);
        tv_xunbao.setOnClickListener(this);
        tv_fengmiduihuan.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_jingdian:
                intent = new Intent(this, ScenicActivity.class);
                intent.putExtra("mark", "Details");
                startActivity(intent);
                break;
            case R.id.tv_dingzhi:
                intent = new Intent(this, CustomizationActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_shoucang:
                intent = new Intent(this, CollectActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_saoyisao:
                intent = new Intent(this, MipcaActivityCapture.class);
                startActivity(intent);
                break;
            case R.id.tv_erweima:
                intent = new Intent(this, MyErweimaActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_xunbao:
                intent = new Intent(this, TreasureDetailsActivity.class);
                intent.putExtra("TYPE", "Details");
                startActivity(intent);
                break;
            case R.id.tv_fengmiduihuan:
                intent = new Intent(this, IntegralActivity.class);
                startActivity(intent);
                break;
        }
    }
}
