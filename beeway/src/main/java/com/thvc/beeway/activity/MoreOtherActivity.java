package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.thvc.beeway.R;
import com.thvc.beeway.adapter.MyWalletAdapter;
import com.thvc.beeway.adapter.PickRecordAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.MyWalletBean;
import com.thvc.beeway.bean.PickRecordBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;

import java.util.ArrayList;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/9/28.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class MoreOtherActivity extends BaseActivity implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {
    private PullToRefreshListView more_listview;
    private TextView head2_title, head2_address;
    private ImageView head2_back, more_image;
    private Context context = this;
    private int p, page;
    private String str, record = "";
    private MyWalletAdapter myWalletAdapter;
    private PickRecordAdapter pickRecordAdapter;
    private ArrayList<MyWalletBean.DataEntity.MoneyListEntity.ListEntity> lists = new ArrayList<>();
    private ArrayList<PickRecordBean.DataEntity.ListEntity> entities = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreother);

        str = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_NO_NOON);
        Intent intent = getIntent();
        record = intent.getStringExtra("record");
        init();

    }

    public void init() {
        more_listview = (PullToRefreshListView) findViewById(R.id.more_listview);
        more_listview.setMode(PullToRefreshBase.Mode.BOTH);
        more_listview.setOnRefreshListener(this);
        head2_title = (TextView) findViewById(R.id.head2_title);
        head2_address = (TextView) findViewById(R.id.head2_address);
        head2_back = (ImageView) findViewById(R.id.head2_back);
        more_image = (ImageView) findViewById(R.id.more_image);
        if (record != null && record.equals("RECORD")) {
            head2_title.setText("提现记录");
            pickRecordAdapter = new PickRecordAdapter(entities, context);
            more_listview.setAdapter(pickRecordAdapter);
            pickRecord();
        } else {
            head2_title.setText("收支明细");
            myWalletAdapter = new MyWalletAdapter(lists, context);
            more_listview.setAdapter(myWalletAdapter);
            WalletData();
        }
        head2_address.setText("");
        head2_back.setOnClickListener(this);

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
                String userid = new BeewayApplication().getmUserid(context);
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", userid);
                params.addQueryStringParameter("p", String.valueOf(p));
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.MYWALLET + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        MyWalletBean myWalletBean = myWalletBean(result);
                        System.out.println("myWalletBean   " + result);
                        if (myWalletBean.getStatus() == 1) {
                            page = myWalletBean.getData().getMoneyList().getTotalPage();
                            ArrayList<MyWalletBean.DataEntity.MoneyListEntity.ListEntity> listEntities = (ArrayList<MyWalletBean.DataEntity.MoneyListEntity.ListEntity>) myWalletBean.getData().getMoneyList().getList();
                            if (listEntities != null && listEntities.size() != 0) {

                                lists.addAll(listEntities);
                                myWalletAdapter.notifyDataSetChanged();
                                more_listview.onRefreshComplete();


                            } else {
                                more_image.setVisibility(View.VISIBLE);
                                more_listview.setVisibility(View.GONE);
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

    /**
     * 加载网络数据
     */
    public void pickRecord() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                String userid = new BeewayApplication().getmUserid(context);
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", userid);
                params.addQueryStringParameter("p", String.valueOf(p));
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.PICKRECORD + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        PickRecordBean pickRecordBean = pickRecordBean(result);
                        System.out.println("pickRecord   " + result);
                        if (pickRecordBean.getStatus() == 1) {
                            page = pickRecordBean.getData().getTotalPage();
                            ArrayList<PickRecordBean.DataEntity.ListEntity> listEntities = (ArrayList<PickRecordBean.DataEntity.ListEntity>) pickRecordBean.getData().getList();
                            if (listEntities != null && listEntities.size() != 0) {

                                entities.addAll(listEntities);
                                pickRecordAdapter.notifyDataSetChanged();
                                more_listview.onRefreshComplete();


                            } else {
                                more_image.setVisibility(View.VISIBLE);
                                more_listview.setVisibility(View.GONE);
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

    public PickRecordBean pickRecordBean(String result) {
        Gson gson = new Gson();
        PickRecordBean pickRecordBean = gson.fromJson(result, PickRecordBean.class);
        return pickRecordBean;
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        more_listview.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        more_listview.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        more_listview.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("最后更新时间:" + str);

        p = 0;

        if (record != null && record.equals("record")) {
            entities.clear();
            pickRecord();
        } else {
            lists.clear();
            WalletData();
        }
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        if (p < page - 1) {
            p++;
            more_listview.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
            more_listview.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
            more_listview.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
                    "最后加载时间:" + str);
            if (record != null && record.equals("RECORD")) {
                pickRecord();
            } else {
                WalletData();
            }
        } else {
            p = page - 1;
            more_listview.onRefreshComplete();
            Toast.makeText(context, "数据已加载完毕", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head2_back:
                finish();
                break;

        }
    }
}
