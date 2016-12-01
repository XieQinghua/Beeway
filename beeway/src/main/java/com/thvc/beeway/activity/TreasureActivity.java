package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.thvc.beeway.adapter.TreasureAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.TreasureBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;

import java.util.ArrayList;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/10/15.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class TreasureActivity extends BaseActivity implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {
    private Context context = this;
    private TextView head2_title, head2_address, treasure_ismy0, treasure_ismy1;
    private LinearLayout treasure_layout0, treasure_layout1;
    private ImageView head2_back, imageView, head2_search;
    private PullToRefreshListView treasure_lv;
    private int p = 0, page = 0;
    private String str, ismy = "0";
    private ArrayList<TreasureBean.DataEntity.ListEntity> lists = new ArrayList<>();
    private TreasureAdapter treasureAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_treasure);
        init();
        treasure();
    }

    public void init() {
        str = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_NO_NOON);
        treasure_layout0 = (LinearLayout) findViewById(R.id.treasure_layout0);
        treasure_layout1 = (LinearLayout) findViewById(R.id.treasure_layout1);
        treasure_ismy0 = (TextView) findViewById(R.id.treasure_ismy0);
        treasure_ismy1 = (TextView) findViewById(R.id.treasure_ismy1);
        treasure_layout0.setOnClickListener(this);
        treasure_layout1.setOnClickListener(this);
        head2_title = (TextView) findViewById(R.id.head2_title);
        head2_address = (TextView) findViewById(R.id.head2_address);
        head2_back = (ImageView) findViewById(R.id.head2_back);
        head2_search = (ImageView) findViewById(R.id.head2_search);
        imageView = (ImageView) findViewById(R.id.more_image);
        head2_address.setVisibility(View.GONE);
        head2_search.setVisibility(View.VISIBLE);
        head2_search.setImageResource(R.drawable.icon_maibao);
        head2_title.setText("我的宝藏");
        head2_back.setOnClickListener(this);
        head2_search.setOnClickListener(this);
        treasure_lv = (PullToRefreshListView) findViewById(R.id.treasure_lv);
        treasure_lv.setMode(PullToRefreshBase.Mode.BOTH);
        treasure_lv.setOnRefreshListener(this);

        treasureAdapter = new TreasureAdapter(lists, context);
        treasure_lv.setAdapter(treasureAdapter);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.head2_back:
                finish();
                break;
            case R.id.head2_search:
                intent = new Intent(TreasureActivity.this, ReleaseTreasureActivity.class);
                startActivity(intent);
                break;
            case R.id.treasure_layout0:
                treasure_layout0.setBackgroundResource(R.color.white);
                treasure_layout1.setBackgroundResource(R.color.gainsboro);
                treasure_ismy0.setBackgroundResource(R.color.beeway_title_bule);
                treasure_ismy1.setBackgroundResource(R.color.white);
                lists.clear();
                ismy = "0";
                treasure();
                break;
            case R.id.treasure_layout1:
                treasure_layout0.setBackgroundResource(R.color.gainsboro);
                treasure_layout1.setBackgroundResource(R.color.white);
                treasure_ismy0.setBackgroundResource(R.color.white);
                treasure_ismy1.setBackgroundResource(R.color.beeway_title_bule);
                lists.clear();
                ismy = "1";
                treasure();
                break;

        }
    }

    public void treasure() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", BeewayApplication.getInstance().getmUserid(TreasureActivity.this));
                params.addQueryStringParameter("ismy", ismy);
                params.addQueryStringParameter("p", String.valueOf(p));
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.MYTREASURE + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        TreasureBean treasureBean = treasureBean(result);
                        if (treasureBean.getStatus() == 1) {
                            ArrayList<TreasureBean.DataEntity.ListEntity> listEntities = (ArrayList<TreasureBean.DataEntity.ListEntity>) treasureBean.getData().getList();
                            page = treasureBean.getData().getTotalPage();
                            if (listEntities != null && listEntities.size() != 0) {
                                lists.addAll(listEntities);
                                treasureAdapter.notifyDataSetChanged();
                                treasure_lv.onRefreshComplete();
                                treasure_lv.setVisibility(View.VISIBLE);
                                imageView.setVisibility(View.GONE);
                            } else {
                                if (p == 0) {
                                    treasure_lv.setVisibility(View.GONE);
                                    imageView.setVisibility(View.VISIBLE);
                                }
                                Toast.makeText(context, "该选项无数据！", Toast.LENGTH_SHORT).show();
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

    public TreasureBean treasureBean(String result) {
        Gson gson = new Gson();
        TreasureBean treasureBean = gson.fromJson(result, TreasureBean.class);
        return treasureBean;
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        treasure_lv.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        treasure_lv.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        treasure_lv.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("最后更新时间:" + str);
        lists.clear();
        p = 0;
        treasure();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        if (p < page - 1) {
            p++;
            treasure_lv.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
            treasure_lv.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
            treasure_lv.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
                    "最后加载时间:" + str);
        } else {
            p = page - 1;
            treasure_lv.onRefreshComplete();
            Toast.makeText(context, "数据已加载完毕", Toast.LENGTH_SHORT).show();
        }
    }
}
