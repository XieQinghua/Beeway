package com.thvc.beeway.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
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
import com.thvc.beeway.activity.CustomerDetailsActivity;
import com.thvc.beeway.activity.IssueCutomerActivity;
import com.thvc.beeway.activity.MemberCenterActivity;
import com.thvc.beeway.activity.UserDetailsActivity;
import com.thvc.beeway.adapter.CustomerAdapter;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.CustomerBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;

import java.util.ArrayList;

/**
 * com.thvc.fragment
 * 创建日期： 2015/8/12.
 * 版权：天合融创
 * 作者：.
 * 版本号：1.0.
 * 修改历史：
 */
public class CustomerFragment extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<ListView> {
    private TextView custom_all, custom_my, custom_fabu;
    private PullToRefreshListView custom_listview;
    private ArrayList<CustomerBean.DataEntity.ListEntity> lists = new ArrayList<>();
    private ImageView imageView, custom_back;
    private int p = 0, pageInde = 0;
    private String userid = "",userid2 = "", str = "";
    private CustomerAdapter customerAdapter;
    private boolean mBoolean = true;
    private Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_customer);

        init();

    }

    /**
     * 获取列表数据
     */
    public void loadData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", userid);
                params.addQueryStringParameter("p", p + "");
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.CUSTOMER + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        CustomerBean customerBean = customerData(result);
                        if (customerBean.getStatus() == 1) {
                            pageInde = customerBean.getData().getTotalPage();
                            ArrayList<CustomerBean.DataEntity.ListEntity> listEntities = (ArrayList<CustomerBean.DataEntity.ListEntity>) customerBean.getData().getList();
                            if (listEntities != null && listEntities.size() != 0) {
                                lists.addAll(listEntities);
                                customerAdapter.notifyDataSetChanged();
                                custom_listview.onRefreshComplete();
                                custom_listview.setVisibility(View.VISIBLE);
                                imageView.setVisibility(View.GONE);
                            } else {
                                if (p == 0) {
                                    custom_listview.setVisibility(View.GONE);
                                    imageView.setVisibility(View.VISIBLE);
                                }
                                Toast.makeText(context, "该选项无数据！", Toast.LENGTH_SHORT).show();
                            }
                            removeDialog();

                        } else {
                            Toast.makeText(context, customerBean.getInfo(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(context, "网络获取失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();

    }

    public CustomerBean customerData(String result) {
        Gson gson = new Gson();
        CustomerBean customerBean = gson.fromJson(result, CustomerBean.class);
        return customerBean;
    }

    public void init() {
        custom_all = (TextView) findViewById(R.id.custom_all);
        custom_my = (TextView) findViewById(R.id.custom_my);
        custom_fabu = (TextView) findViewById(R.id.custom_fabu);
        custom_listview = (PullToRefreshListView) findViewById(R.id.custom_listview);
        imageView = (ImageView) findViewById(R.id.custom_image);
        custom_back = (ImageView) findViewById(R.id.custom_back);
        custom_listview.setMode(PullToRefreshBase.Mode.BOTH);
        custom_listview.setOnRefreshListener(this);
        str = DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_NO_NOON);
        custom_listview.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        final String Member = intent.getStringExtra("label");
        if (Member != null && Member.equals(MemberCenterActivity.JUMP)) {
            custom_my.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_view_rounded_right));
            custom_my.setTextColor(getResources().getColor(R.color.beeway_title_bule));
            custom_all.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_view_rounded_transparent));
            custom_all.setTextColor(getResources().getColor(R.color.white));
            userid = GlobalParams.loginUserId;
            p = 0;
            lists.clear();
        } else if (Member != null && Member.equals(UserDetailsActivity.JUMP)) {
            custom_my.setText("他的");
            custom_my.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_view_rounded_right));
            custom_my.setTextColor(getResources().getColor(R.color.beeway_title_bule));
            custom_all.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_view_rounded_transparent));
            custom_all.setTextColor(getResources().getColor(R.color.white));
            userid = intent.getStringExtra("userid");
            userid2 = intent.getStringExtra("userid");
            p = 0;
            lists.clear();
        }
        //加载数据
        showDialog(LOADING_DIALOG);
        loadData();

        custom_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                custom_all.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_view_rounded_left));
                custom_all.setTextColor(getResources().getColor(R.color.beeway_title_bule));
                custom_my.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_view_rounded_transparent));
                custom_my.setTextColor(getResources().getColor(R.color.white));
                userid = "";
                p = 0;
                lists.clear();
                showDialog(LOADING_DIALOG);
                loadData();
            }
        });
        custom_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                custom_my.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_view_rounded_right));
                custom_my.setTextColor(getResources().getColor(R.color.beeway_title_bule));
                custom_all.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_view_rounded_transparent));
                custom_all.setTextColor(getResources().getColor(R.color.white));
                if (Member != null && Member.equals(UserDetailsActivity.JUMP)) {
                    userid = userid2;
                }else {
                    userid = GlobalParams.loginUserId;
                }
                p = 0;
                lists.clear();
                showDialog(LOADING_DIALOG);
                loadData();
            }
        });
        custom_fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, IssueCutomerActivity.class);
                String Title = "发布众筹";
                intent.putExtra("Title", Title);
                startActivity(intent);
            }
        });
        custom_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        customerAdapter = new CustomerAdapter(lists, this);
        custom_listview.setAdapter(customerAdapter);
        custom_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(context, CustomerDetailsActivity.class);
                intent.putExtra("id", lists.get(position - 1).getId());
                intent.putExtra("userid", lists.get(position - 1).getUserid());
                startActivity(intent);

            }
        });

    }

    /**
     * 下拉刷新
     *
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        mBoolean = false;
        p = 0;
        custom_listview.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        custom_listview.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        custom_listview.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("最后更新时间:" + str);
        lists.clear();
        loadData();
        customerAdapter.notifyDataSetChanged();
    }

    /**
     * 上拉加载
     *
     * @param refreshView
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        if (p < pageInde - 1) {
            p++;
            custom_listview.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
            custom_listview.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
            custom_listview.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
                    "最后加载时间:" + str);
            mBoolean = false;
            loadData();
            customerAdapter.notifyDataSetChanged();
        } else {
            p = pageInde - 1;
            custom_listview.onRefreshComplete();
            Toast.makeText(context, "数据已加载完毕", Toast.LENGTH_SHORT).show();
        }
    }
}
