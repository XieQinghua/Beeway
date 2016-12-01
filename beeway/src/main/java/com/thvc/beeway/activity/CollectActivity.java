package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
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
import com.thvc.beeway.adapter.CollectAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.CollectBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;

import java.util.ArrayList;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/10/12.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class CollectActivity extends BaseActivity implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {
    private Context context = this;
    private TextView head2_title, head2_address;
    private ImageView head2_back, imageView;
    private PullToRefreshListView more_listview;
    private int p = 0, page = 0;
    private String str;
    private ArrayList<CollectBean.DataEntity.ListEntity> lists = new ArrayList<>();
    private CollectAdapter collectAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_moreother);
        init();
        collect();
    }

    public void init() {
        str = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_NO_NOON);
        head2_title = (TextView) findViewById(R.id.head2_title);
        head2_address = (TextView) findViewById(R.id.head2_address);
        head2_back = (ImageView) findViewById(R.id.head2_back);
        imageView = (ImageView) findViewById(R.id.more_image);
        head2_address.setText("");
        head2_title.setText("我的收藏");
        head2_back.setOnClickListener(this);
        more_listview = (PullToRefreshListView) findViewById(R.id.more_listview);
        more_listview.setMode(PullToRefreshBase.Mode.BOTH);
        more_listview.setOnRefreshListener(this);

        collectAdapter = new CollectAdapter(lists, context);
        more_listview.setAdapter(collectAdapter);
        more_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if ("TrackNote".equals(lists.get(i - 1).getTableid())) {
                    Intent intent = new Intent(context, TrackDetailsActivity.class);
                    intent.putExtra("id", lists.get(i - 1).getDataid());
                    intent.putExtra("userid", lists.get(i - 1).getData().getUserid());
                    startActivity(intent);
                } else if ("TrackTravel".equals(lists.get(i - 1).getTableid())) {
                    Intent intent = new Intent(context, TravelsDetailsActivity.class);
                    intent.putExtra("id", lists.get(i - 1).getDataid());
                    intent.putExtra("userid", lists.get(i - 1).getData().getUserid());
                    intent.putExtra("title", lists.get(i - 1).getData().getTitle());
                    startActivity(intent);
                } else if ("Scenic".equals(lists.get(i - 1).getTableid())) {
                    Intent intent = new Intent(context, ScenicDetailsActivity.class);
                    intent.putExtra("id", lists.get(i - 1).getDataid());
                    intent.putExtra("name", lists.get(i - 1).getData().getTitle());
                    startActivity(intent);
                } else if ("WantNote".equals(lists.get(i - 1).getTableid())) {
                    Intent intent = new Intent(context, CustomerDetailsActivity.class);
                    intent.putExtra("id", lists.get(i - 1).getDataid());
                    intent.putExtra("userid", lists.get(i - 1).getData().getUserid());
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head2_back:
                finish();
                break;
        }
    }

    /**
     * 加载网络数据
     */
    public void collect() {
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
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.COLLECT + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        CollectBean collectBean = collectBean(result);
                        if (collectBean.getStatus() == 1) {
                            ArrayList<CollectBean.DataEntity.ListEntity> listEntities = (ArrayList<CollectBean.DataEntity.ListEntity>) collectBean.getData().getList();
                            page = collectBean.getData().getTotalPage();
                            if (listEntities != null && listEntities.size() != 0) {
                                lists.addAll(listEntities);
                                collectAdapter.notifyDataSetChanged();
                                more_listview.onRefreshComplete();
                                more_listview.setVisibility(View.VISIBLE);
                                imageView.setVisibility(View.GONE);
                            } else {
                                if (p == 0) {
                                    more_listview.setVisibility(View.GONE);
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

    public CollectBean collectBean(String result) {
        Gson gson = new Gson();
        CollectBean collectBean = gson.fromJson(result, CollectBean.class);
        return collectBean;
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        more_listview.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        more_listview.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        more_listview.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("最后更新时间:" + str);
        lists.clear();
        p = 0;
        collect();
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
            collect();
        } else {
            p = page - 1;
            more_listview.onRefreshComplete();
            Toast.makeText(context, "数据已加载完毕", Toast.LENGTH_SHORT).show();
        }
    }
}
