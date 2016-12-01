package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.thvc.beeway.adapter.ScenicAdapter;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.ScenicAreaBean;
import com.thvc.beeway.bean.ScenicBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.view.ExpandTabView;
import com.thvc.beeway.view.FilterDataSource;
import com.thvc.beeway.view.MiddleFilterView;
import com.thvc.beeway.view.RightFilterView;

import java.util.ArrayList;

/**
 * 项目名称：Beeway
 * 类描述：景点显示页面
 * 创建人：谢庆华.
 * 创建时间：2015/8/25 12:07
 * 修改人：Administrator
 * 修改时间：2015/8/25 12:07
 * 修改备注：
 */
public class ScenicActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<ListView> {

    private ImageView nullimage;
    private PullToRefreshListView lv_scenic;
    private Context context;
    private ScenicAdapter scenicAdapter;
    private ArrayList<ScenicBean.DataEntity.DatasEntity> listEntities;
    private ArrayList<ScenicBean.DataEntity.DatasEntity> lists = new ArrayList<>();
    private static ArrayList<ScenicAreaBean.DataEntity.ProvinceEntity> list;
    private String tv_track, tv__time, mark = "", et_text;
    private MiddleFilterView viewMiddle;
    private RightFilterView viewRight;
    private ExpandTabView expandTabView;
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private String area = "", order = "", str = "";
    private int p = 0, pageInde = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic);
        context = this;
        init();
        tv_track = getIntent().getStringExtra("tv_track");
        tv__time = getIntent().getStringExtra("tv__time");
        et_text = getIntent().getStringExtra("et_text");
        mark = getIntent().getStringExtra("mark");
        str = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_NO_NOON);
    }

    private void init() {
        nullimage = (ImageView) findViewById(R.id.activity_scenic_image);

        lv_scenic = (PullToRefreshListView) findViewById(R.id.lv_scenic);

        lv_scenic.setMode(PullToRefreshBase.Mode.BOTH);
        lv_scenic.setOnRefreshListener(this);

        scenicAdapter = new ScenicAdapter(lists, ScenicActivity.this);
        lv_scenic.setAdapter(scenicAdapter);
        lv_scenic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mark.equals("选择")) {
                    String tv__place = lists.get(position - 1).getTitle() + "-" + lists.get(position - 1).getAddress();
                    String area = lists.get(position - 1).getArea();
                    String company = lists.get(position - 1).getId();
                    String detail = lists.get(position - 1).getDetail();
                    String latitude = lists.get(position - 1).getLatitude();
                    String longitude = lists.get(position - 1).getLongitude();

                    Intent intent = new Intent(ScenicActivity.this, AddTrackActivity.class);
                    intent.putExtra("tv__place", tv__place);
                    intent.putExtra("tv_track", tv_track);
                    intent.putExtra("tv__time", tv__time);
                    intent.putExtra("et_text", et_text);
                    intent.putExtra("area", area);
                    intent.putExtra("company", company);
                    intent.putExtra("detail", detail);
                    intent.putExtra("latitude", latitude);
                    intent.putExtra("longitude", longitude);
                    startActivity(intent);
                    ScenicActivity.this.finish();
                } else if (mark.equals("Details")) {
                    Intent intent = new Intent(context, ScenicDetailsActivity.class);
                    intent.putExtra("id", lists.get(position - 1).getId());
                    intent.putExtra("name", lists.get(position - 1).getTitle());
                    startActivity(intent);
                } else if (mark.equals("众筹")) {
                    String company = lists.get(position - 1).getId();
                    String address = lists.get(position - 1).getAddress().toString();
                    String titl = lists.get(position - 1).getTitle().toString();
                    String Title = titl + "-" + address;
                    Intent intent = new Intent(ScenicActivity.this, IssueCutomerActivity.class);
                    intent.putExtra("company", company);
                    intent.putExtra("Title", Title);
                    startActivity(intent);
                    ScenicActivity.this.finish();
                } else if (mark.equals("baozang")) {
                    String address = lists.get(position - 1).getAddress().toString();
                    String titl = lists.get(position - 1).getTitle().toString();
                    String result = titl + "-" + address;
                    String area = lists.get(position - 1).getArea();
                    String company = lists.get(position - 1).getId();
                    String detail = lists.get(position - 1).getDetail();
                    String latitude = lists.get(position - 1).getLatitude();
                    String longitude = lists.get(position - 1).getLongitude();

                    Intent intent = new Intent();
                    intent.putExtra("result", result);
                    intent.putExtra("area", area);
                    intent.putExtra("company", company);
                    intent.putExtra("address", address);
                    intent.putExtra("detail", detail);
                    intent.putExtra("latitude", latitude);
                    intent.putExtra("longitude", longitude);
                    ScenicActivity.this.setResult(RESULT_OK, intent);
                    ScenicActivity.this.finish();
                }
            }
        });
        findArea();


    }

    public void findArea() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
//                String url = String.format(UrlPools.SCENICAREA);
                MyRequestParams params = new MyRequestParams();//定义访问服务器参数
                params.addQueryStringParameter("", "");
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.SCENICAREA + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        ScenicAreaBean scenicAreaBean = scenicAreaData(result);
                        if (scenicAreaBean.getStatus() == 1) {
                            list = (ArrayList<ScenicAreaBean.DataEntity.ProvinceEntity>) scenicAreaBean.getData().getProvince();
                            area = list.get(0).getCitys().get(0).getId();
                        } else {
                            Toast.makeText(context, scenicAreaBean.getInfo(), Toast.LENGTH_SHORT).show();
                        }
                        lodeData();
                        initView();
                        initVaule();
                        initListener();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(context, "网络获取失败！", Toast.LENGTH_SHORT).show();
                    }

                });


            }
        }.start();
    }

    public static ArrayList<ScenicAreaBean.DataEntity.ProvinceEntity> createPricegroupsItems() {
        return list;
    }

    public void lodeData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                String latitude = GlobalParams.latitude;
                String longitude = GlobalParams.longitude;
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("area", area);
                params.addQueryStringParameter("latitude", latitude);
                params.addQueryStringParameter("longitude", longitude);
                params.addQueryStringParameter("order", order);
                params.addQueryStringParameter("p", String.valueOf(p));
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.SCENIC + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        ScenicBean scenicBean = scenicData(result);
                        pageInde = scenicBean.getData().getTotalPage();
                        if (scenicBean.getStatus() == 1) {
                            listEntities = (ArrayList<ScenicBean.DataEntity.DatasEntity>) scenicBean.getData().getDatas();
                            lists.addAll(listEntities);
                            if (listEntities != null && listEntities.size() != 0) {
                                lv_scenic.onRefreshComplete();
                                scenicAdapter.notifyDataSetChanged();
                                lv_scenic.setVisibility(View.VISIBLE);
                                nullimage.setVisibility(View.GONE);
                            } else {
                                if (p == 0) {
                                    lv_scenic.setVisibility(View.GONE);
                                    nullimage.setVisibility(View.VISIBLE);
                                }
                                Toast.makeText(context, "该选项无数据！", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, scenicBean.getInfo(), Toast.LENGTH_SHORT).show();
                        }
                        removeDialog(LOADING_DIALOG);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(context, "网络获取失败！", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        }.start();


    }


    public ScenicBean scenicData(String result) {
        Gson gson = new Gson();
        ScenicBean scenicBean = gson.fromJson(result, ScenicBean.class);
        return scenicBean;
    }

    public ScenicAreaBean scenicAreaData(String result) {
        Gson gson = new Gson();
        ScenicAreaBean scenicAreaBean = gson.fromJson(result, ScenicAreaBean.class);
        return scenicAreaBean;
    }

    private void initView() {

        expandTabView = (ExpandTabView) findViewById(R.id.expandtab_view);

        viewMiddle = new MiddleFilterView(this, "scenic");

        viewRight = new RightFilterView(this, FilterDataSource.createSortItems());

    }

    private void initVaule() {

        mViewArray.add(viewMiddle);
        mViewArray.add(viewRight);

        ArrayList<String> mTextArray = new ArrayList<String>();
        mTextArray.add(list.get(0).getCitys().get(0).getTitle());
        mTextArray.add("默认排序");

        expandTabView.setValue(mTextArray, mViewArray);

        expandTabView.setTitle(list.get(0).getCitys().get(0).getTitle(), 0);
        expandTabView.setTitle("默认排序", 1);

    }

    private void initListener() {

        viewMiddle.setOnSelectListener(new MiddleFilterView.OnItemSelectListener() {

            @Override
            public void getValue(String showText) {
                onRefresh(viewMiddle, showText, "address");

            }
        });

        viewRight.setOnSelectListener(new RightFilterView.OnSelectListener() {
            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewRight, showText, "paixu");

            }
        });

    }

    private void onRefresh(View view, String showText, String type) {

        expandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
            expandTabView.setTitle(showText, position);
        }
        if ("address".equals(type)) {
            if (list != null && !"".equals(list)) {
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.get(i).getCitys().size(); j++) {
                        if (showText.equals(list.get(i).getCitys().get(j).getTitle())) {
                            area = list.get(i).getCitys().get(j).getId();
                        }
                    }
                }
            }
        } else if ("paixu".equals(type)) {
            String names[] = new String[]{"默认排序", "关注优先", "人气优先", "最新发布"};
            String key[] = new String[]{"", "collect", "score", "addtime"};
            for (int j = 0; j < names.length; j++) {
                if (showText.equals(names[j])) {
                    order = key[j];
                }
            }
        }
        p = 0;
        lists.clear();
        showDialog(LOADING_DIALOG);
        lodeData();
        Toast.makeText(ScenicActivity.this, showText, Toast.LENGTH_SHORT).show();

    }

    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void onBackPressed() {

        if (!expandTabView.onPressBack()) {
            finish();

        }

    }

    /**
     * 下拉刷新
     *
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        lv_scenic.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        lv_scenic.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        lv_scenic.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("最后更新时间:" + str);
        p = 0;
        lists.clear();
        lodeData();


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
            System.out.println("p====" + pageInde);
            lv_scenic.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
            lv_scenic.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
            lv_scenic.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
                    "最后加载时间:" + str);
            lodeData();
        } else {
            p = pageInde - 1;
            lv_scenic.onRefreshComplete();
            Toast.makeText(context, "数据已加载完毕", Toast.LENGTH_SHORT).show();
        }
    }

}
