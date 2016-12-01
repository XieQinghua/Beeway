package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
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
import com.thvc.beeway.adapter.MyWalletAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.MyWalletBean;
import com.thvc.beeway.fragment.CustomerFragment;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;

import java.util.ArrayList;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/9/24.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class MyWalletActivity extends BaseActivity implements View.OnClickListener {
    private TextView head2_title, head2_address, myWallet_balance, myWallet_already, myWallet_part,
            myWallet_on, myWallet_more, myWallet_topUp, myWallet_withdraw, myWallet_bankCard;
    private ImageView head2_back, activity_mytrack_image;
    private LinearLayout myWallet_applyFor, myWallet_details;
    private ListView myWallet_listview;
    private String userid = "", Usefee = "";
    private Context context = this;
    private MyWalletAdapter myWalletAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywallet);
        init();
        WalletData();
    }

    /**
     * 初始化所有控件
     */
    public void init() {
        head2_title = (TextView) findViewById(R.id.head2_title);
        head2_address = (TextView) findViewById(R.id.head2_address);
        myWallet_part = (TextView) findViewById(R.id.myWallet_part);
        myWallet_balance = (TextView) findViewById(R.id.myWallet_balance);
        myWallet_already = (TextView) findViewById(R.id.myWallet_already);
        myWallet_on = (TextView) findViewById(R.id.myWallet_on);
        myWallet_more = (TextView) findViewById(R.id.myWallet_more);
        myWallet_topUp = (TextView) findViewById(R.id.myWallet_topUp);
        myWallet_withdraw = (TextView) findViewById(R.id.myWallet_withdraw);
        myWallet_bankCard = (TextView) findViewById(R.id.myWallet_bankCard);
        head2_back = (ImageView) findViewById(R.id.head2_back);
        activity_mytrack_image = (ImageView) findViewById(R.id.activity_mytrack_image);
        myWallet_details = (LinearLayout) findViewById(R.id.myWallet_details);
        myWallet_applyFor = (LinearLayout) findViewById(R.id.myWallet_applyFor);
        myWallet_listview = (ListView) findViewById(R.id.myWallet_listview);

        head2_address.setVisibility(View.GONE);
        head2_back.setOnClickListener(this);
        myWallet_on.setOnClickListener(this);
        myWallet_more.setOnClickListener(this);
        myWallet_topUp.setOnClickListener(this);
        myWallet_withdraw.setOnClickListener(this);
        myWallet_bankCard.setOnClickListener(this);
        myWallet_details.setOnClickListener(this);
        myWallet_applyFor.setOnClickListener(this);
        head2_title.setText("我的余额");

    }

    /**
     * 加载网络数据
     */
    public void WalletData() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                userid = new BeewayApplication().getmUserid(context);
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", userid);
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.MYWALLET + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        MyWalletBean myWalletBean = myWalletBean(result);
                        System.out.println("myWalletBean   " + result);
                        if (myWalletBean.getStatus() == 1) {
                            Usefee = myWalletBean.getData().getUsefee();
                            myWallet_balance.setText(myWalletBean.getData().getUsefee() + "元");
                            myWallet_already.setText(myWalletBean.getData().getCan_wantfee());
                            myWallet_part.setText(myWalletBean.getData().getNow_poornum());
                            ArrayList<MyWalletBean.DataEntity.MoneyListEntity.ListEntity> listEntities = (ArrayList<MyWalletBean.DataEntity.MoneyListEntity.ListEntity>) myWalletBean.getData().getMoneyList().getList();
                            if (listEntities != null && listEntities.size() != 0) {
                                myWalletAdapter = new MyWalletAdapter(listEntities, context);
                                myWallet_listview.setAdapter(myWalletAdapter);
                                myWalletAdapter.notifyDataSetChanged();
                                setListViewHeight(myWallet_listview);
                            } else {
                                activity_mytrack_image.setVisibility(View.VISIBLE);
                                myWallet_listview.setVisibility(View.GONE);
                            }
                        }
                        removeDialog();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
            }
        }.start();
    }

    public MyWalletBean myWalletBean(String result) {
        Gson gson = new Gson();
        MyWalletBean myWalletBean = gson.fromJson(result, MyWalletBean.class);
        return myWalletBean;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head2_back:  //返回
                finish();
                break;
            case R.id.myWallet_details:   //了解详情
                Toast.makeText(context, "正在维护！！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.myWallet_applyFor:    //申请提取
                Intent customer = new Intent(this, CustomerFragment.class);
                startActivity(customer);
                break;
            case R.id.myWallet_on:      //关于余额
                Intent on = new Intent(this, OnWalletActivity.class);
                startActivity(on);
                break;
            case R.id.myWallet_more:     //更多
                Intent more = new Intent(this, MoreOtherActivity.class);
                startActivity(more);
                break;
            case R.id.myWallet_topUp:     //充值
                Toast.makeText(context, "正在维护！！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.myWallet_withdraw:   //提现
                Intent moneyPick = new Intent(this, MoneyPickActivity.class);
                moneyPick.putExtra("Usefee", Usefee);
                startActivity(moneyPick);
                break;
            case R.id.myWallet_bankCard:    //银行卡
                Intent bankCard = new Intent(this, BankCardActivity.class);
                startActivity(bankCard);
                break;
        }
    }

    /**
     * 重新计算ListView的高度，解决ScrollView和ListView两个View都有滚动的效果，在嵌套使用时起冲突的问题
     *
     * @param listView
     */
    public void setListViewHeight(ListView listView) {

        // 获取ListView对应的Adapter

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        params.height += 40;
        listView.setLayoutParams(params);
    }

}
