package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.thvc.beeway.R;
import com.thvc.beeway.adapter.MyHoneyAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.MyHoneyBean;
import com.thvc.beeway.bean.UserLevelBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/29.
 * 我的蜂蜜
 */
public class MyHoneyActivity extends BaseActivity {
    private Context context = this;
    private TextView tv_convert, tv_honey_rule, tv_guanyu, tv_honey_zong, tv_myhoney_exchange_record;
    private HttpUtils httpUtils;
    private int p = 0, pageInde = 0;
    private ArrayList<MyHoneyBean.DataEntity.ListEntity> listEntities;
    private ListView activity_myhoney_listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myhoney);
        init();
        getHoney();
    }

    /**
     * 初始化控件，并且设置点击事件
     */
    public void init() {
        tv_convert = (TextView) this.findViewById(R.id.tv_convert);
        tv_convert.setOnClickListener(new MyOnClickListener());
        tv_honey_rule = (TextView) this.findViewById(R.id.tv_honey_rule);
        tv_honey_rule.setOnClickListener(new MyOnClickListener());
        activity_myhoney_listview = (ListView) this.findViewById(R.id.activity_myhoney_listview);
        tv_guanyu = (TextView) this.findViewById(R.id.tv_guanyu);
        tv_guanyu.setOnClickListener(new MyOnClickListener());
        tv_myhoney_exchange_record = (TextView) this.findViewById(R.id.tv_myhoney_exchange_record);
        tv_myhoney_exchange_record.setOnClickListener(new MyOnClickListener());
        tv_honey_zong = (TextView) this.findViewById(R.id.tv_honey_zong);

    }

    //点击事件
    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.tv_convert://跳转到峰蜜页面
                    intent = new Intent(MyHoneyActivity.this, IntegralActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_honey_rule://跳转到积分规则页面
                    intent = new Intent(MyHoneyActivity.this, HoneyRuleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_guanyu://跳转到积分规则页面
                    intent = new Intent(MyHoneyActivity.this, HoneyRuleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_myhoney_exchange_record://跳转到兑换记录页面
                    intent = new Intent(MyHoneyActivity.this, ExchangeRecordActivity.class);
                    startActivity(intent);
                    break;
            }

        }
    }

    public void getHoney() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", BeewayApplication.getInstance().getmUserid(MyHoneyActivity.this));
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.INCREASEINTEGRAL + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        MyHoneyBean myHoneyBean = paraseData(result);
                        if (myHoneyBean.getStatus() == 1) {
                            pageInde = myHoneyBean.getData().getTotalPage();
                            listEntities = (ArrayList<MyHoneyBean.DataEntity.ListEntity>) myHoneyBean.getData().getList();
                            MyHoneyAdapter myHoneyAdapter = new MyHoneyAdapter(listEntities, MyHoneyActivity.this);
                            activity_myhoney_listview.setAdapter(myHoneyAdapter);
                        }
                        usejifen();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(MyHoneyActivity.this, "网络获取失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();


    }

    /**
     * 解析我的蜂蜜数据
     *
     * @param result
     * @return
     */
    public MyHoneyBean paraseData(String result) {
        Gson gson = new Gson();
        MyHoneyBean myHoneyBean = gson.fromJson(result, MyHoneyBean.class);
        return myHoneyBean;
    }

    /**
     * 加载积分数据
     */
    public void usejifen() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", BeewayApplication.getInstance().getmUserid(MyHoneyActivity.this));
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.USERJIFEN + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        UserLevelBean userLevelBean = userLevelBean(result);
                        System.out.println("myWalletBean   " + result);
                        if (userLevelBean.getStatus() == 1) {
                            tv_honey_zong.setText(userLevelBean.getData().getUsejifen() + "蜂蜜");
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
                removeDialog();
            }
        }.start();
    }

    public UserLevelBean userLevelBean(String result) {
        Gson gson = new Gson();
        UserLevelBean userLevelBean = gson.fromJson(result, UserLevelBean.class);
        return userLevelBean;
    }
}
