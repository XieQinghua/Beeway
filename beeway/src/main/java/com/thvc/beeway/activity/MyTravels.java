package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.Window;
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
import com.thvc.beeway.adapter.MyTravelsAdapter;
import com.thvc.beeway.adapter.TravelAdapter;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.AddFootprintbean;
import com.thvc.beeway.bean.MyTravelsBean;
import com.thvc.beeway.bean.TravelBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/26.
 * 我的游记列表
 */
public class MyTravels extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<ListView>, Serializable {
    private int p = 0, pageInde = 0;
    private ImageView back, activity_mytravels_image;
    private TextView tv_publish, tv_no_mytravels;
    private PullToRefreshListView activity_mytravels_listview;

    private String str, userid;
    private Context context;
    private ArrayList<TravelBean.DataEntity.ListEntity2> lists = new ArrayList<>();
    private MyTravelsAdapter travelAdapter;
    private List<MyTravelsBean> list;
    private String resumeId;

    private String tv_track, tv__place, et_text, tv__time;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mytravels);
        str = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_NO_NOON);
        context = this;
        userid = GlobalParams.loginUserId;

        tv_track = getIntent().getStringExtra("tv_track");
        tv__place = getIntent().getStringExtra("tv__place");
        et_text = getIntent().getStringExtra("et_text");
        tv__time = getIntent().getStringExtra("tv__time");

        inits();
    }

    /**
     * 初始化控件
     */
    public void inits() {
        back = (ImageView) this.findViewById(R.id.back);
        tv_publish = (TextView) this.findViewById(R.id.tv_publish);
        tv_no_mytravels = (TextView) this.findViewById(R.id.tv_no_mytravels);
        activity_mytravels_image = (ImageView) findViewById(R.id.activity_mytravels_image);
        activity_mytravels_listview = (PullToRefreshListView) this.findViewById(R.id.activity_mytravels_listview);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(back);
            }
        });
        travelAdapter = new MyTravelsAdapter(lists, MyTravels.this);
        activity_mytravels_listview.setAdapter(travelAdapter);
        list = new ArrayList<MyTravelsBean>();
        tv_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getmyfootschecbox();
                Intent intent = new Intent(MyTravels.this, AddTrackActivity.class);
                intent.putExtra("resumeId", resumeId);
                intent.putExtra("tv_track", tv_track);
                intent.putExtra("tv__place", tv__place);
                intent.putExtra("et_text", et_text);
                intent.putExtra("tv__time", tv__time);
                startActivity(intent);
                list.clear();
                MyTravels.this.finish();
            }
        });
        loadData();
    }

    public void getmyfootschecbox() {
        Map<Integer, Boolean> map = travelAdapter.getCheckMap();
        int count = travelAdapter.getCount();
        // 进行遍历
        for (int i = 0; i < map.size(); i++) {
            int position = i - (count - travelAdapter.getCount());
            if (map.get(i) != null && map.get(i)) {
                resumeId = lists.get(position).getId();
            }
        }
    }

    //获取游记数据
    public void loadData() {
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("p", p + "");
        params.addQueryStringParameter("tag", "");
        params.addQueryStringParameter("userid", userid);
        params.addQueryStringParameter("ismy", "1");
        params.addQueryStringParameter("keywords", "");
        params.addQueryStringParameter("days", "");
        params.addQueryStringParameter("money", "");
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.TRACKALL2_3 + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                //获得访问解析出来的json字符串
                String result = responseInfo.result;
                TravelBean travelBean = paraseDatatravel(result);
                if (travelBean.getStatus() == 1) {
                    pageInde = travelBean.getData().getTotalPage();
                    ArrayList<TravelBean.DataEntity.ListEntity2> listEntities2 = (ArrayList<TravelBean.DataEntity.ListEntity2>) travelBean.getData().getList();
                    if (listEntities2 != null && listEntities2.size() != 0) {
                        lists.addAll(listEntities2);
                        travelAdapter.notifyDataSetChanged();
                        activity_mytravels_listview.onRefreshComplete();
                        activity_mytravels_listview.setVisibility(View.VISIBLE);
                        activity_mytravels_image.setVisibility(View.GONE);
                    } else {
                        if (p == 0) {
                            activity_mytravels_listview.setVisibility(View.GONE);
                            activity_mytravels_image.setVisibility(View.VISIBLE);
                        }
                        Toast.makeText(context, "该选项无数据！", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    activity_mytravels_listview.setVisibility(View.GONE);
                    activity_mytravels_image.setVisibility(View.VISIBLE);
                    Toast.makeText(context, "该选项无数据！", Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //访问失败
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public TravelBean paraseDatatravel(String result) {
        Gson gson = new Gson();
        TravelBean travelBean = gson.fromJson(result, TravelBean.class);
        return travelBean;
    }

    /**
     * 下拉刷新
     *
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        activity_mytravels_listview.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        activity_mytravels_listview.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        activity_mytravels_listview.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("最后更新时间:" + str);
        p = 0;
        lists.clear();
        loadData();
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
            activity_mytravels_listview.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
            activity_mytravels_listview.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
            activity_mytravels_listview.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
                    "最后加载时间:" + str);
            loadData();
        } else {
            p = pageInde - 1;
            activity_mytravels_listview.onRefreshComplete();
            Toast.makeText(context, "数据已加载完毕", Toast.LENGTH_SHORT).show();
        }
    }
}
