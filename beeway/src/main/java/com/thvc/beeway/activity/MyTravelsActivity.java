package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.KeyEvent;
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
import com.thvc.beeway.adapter.TravelAdapter;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.TravelBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.view.ExpandTabView;
import com.thvc.beeway.view.FilterDataSource;
import com.thvc.beeway.view.LeftFilterView;
import com.thvc.beeway.view.RightFilterView;

import java.util.ArrayList;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/9/9.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class MyTravelsActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<ListView> {
    private TextView address_city;
    private TextView name,nickname;
    private PullToRefreshListView listView;
    private ImageView imageView;
    private ArrayList<TravelBean.DataEntity.ListEntity2> lists = new ArrayList<>();
    private Context context;
    private String tag = "", frkey = "", ismy = "3", userid2 = "", userid = "", str = "", keywords = "", days = "", money = "", count = "",nickName="";
    private ExpandTabView expandTabView;
    private String[] TITLE = {"全部", "他的", "我的"};
    private String[] TITLE1 = {"全部", "我的"};
    private String[] KEY1 = {"0", "1", "2"};
    private String[] TITLE2 = {"全部", "实用", "美图", "典藏", "精华"};
    private String[] KEY2 = {"0", "1", "2", "3", "4"};
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private int p = 0, pageInde = 0;
    private LeftFilterView viewLeft;
    private RightFilterView viewRight;
    private TravelAdapter travelAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mytravels);
        str = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_NO_NOON);
        context = this;

        init();

    }

    public void init() {

        address_city = (TextView) findViewById(R.id.head2_address);
        imageView = (ImageView) findViewById(R.id.activity_mytravel_image);
        name = (TextView) findViewById(R.id.head2_title);
        nickname = (TextView) findViewById(R.id.head2_title2);
        listView = (PullToRefreshListView) findViewById(R.id.activity_mytravel_listview);
        address_city.setText("发布");
        address_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(context, AddTravelActivity.class);
                startActivity(intent2);
            }
        });
        name.setText("游记");
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(this);
        travelAdapter = new TravelAdapter(lists, MyTravelsActivity.this);
        listView.setAdapter(travelAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, TravelsDetailsActivity.class);
                intent.putExtra("id", lists.get(i - 1).getId());
                intent.putExtra("userid", lists.get(i - 1).getUserid());
                intent.putExtra("title", lists.get(i - 1).getTitle());
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        count = intent.getStringExtra("count");
        if (count != null && count.equals("customization")) {
            keywords = intent.getStringExtra("keywords");
            days = intent.getStringExtra("days");
            money = intent.getStringExtra("money");
        } else if (count != null && count.equals(UserDetailsActivity.JUMP)) {
            ismy = "1";
            userid2 = intent.getStringExtra("userid");
            userid = intent.getStringExtra("userid");
            nickName = intent.getStringExtra("nickname");
            nickname.setVisibility(View.VISIBLE);
            nickname.setText("("+nickName+")");
        }else if (count!=null&& count.equals(MemberCenterActivity.JUMP)){
            ismy = "1";
            userid2 = GlobalParams.loginUserId;
        }
        showDialog(LOADING_DIALOG);
        loadData2();

        initView();
        initVaule();
        initListener();

    }

    //获取游记数据
    public void loadData2() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                String keyword = "";
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("p", p + "");
                params.addQueryStringParameter("tag", tag);
                params.addQueryStringParameter("userid", userid2);
                params.addQueryStringParameter("ismy", ismy + "");
                params.addQueryStringParameter("keywords", keywords);
                params.addQueryStringParameter("days", days);
                params.addQueryStringParameter("money", money);
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
                                listView.onRefreshComplete();
                                listView.setVisibility(View.VISIBLE);
                                imageView.setVisibility(View.GONE);
                            } else {
                                if (p == 0) {
                                    listView.setVisibility(View.GONE);
                                    imageView.setVisibility(View.VISIBLE);
                                }
                                Toast.makeText(context, "该选项无数据！", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            listView.setVisibility(View.GONE);
                            imageView.setVisibility(View.VISIBLE);
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
        }.start();

    }

    public TravelBean paraseDatatravel(String result) {
        Gson gson = new Gson();
        TravelBean travelBean = gson.fromJson(result, TravelBean.class);
        return travelBean;
    }

    /**
     * 初始化选项列表
     */
    private void initView() {

        expandTabView = (ExpandTabView) findViewById(R.id.expandtab_view);

        viewLeft = new LeftFilterView(this, FilterDataSource.travleleftFilterItems(), "left");

        if (count != null && count.equals(UserDetailsActivity.JUMP)) {
            viewRight = new RightFilterView(this, FilterDataSource.travlecreateSortFilterItems2());
        }else {
            viewRight = new RightFilterView(this, FilterDataSource.travlecreateSortFilterItems());
        }
    }

    private void initVaule() {

        mViewArray.add(viewLeft);
        mViewArray.add(viewRight);

        if (count != null && count.equals(UserDetailsActivity.JUMP)) {
            ArrayList<String> mTextArray = new ArrayList<String>();
            mTextArray.add("全部");
            mTextArray.add(TITLE[1]);
            expandTabView.setValue(mTextArray, mViewArray);
            expandTabView.setTitle("全部", 0);
            expandTabView.setTitle(TITLE[1], 1);
        } else if (count!=null&& count.equals(MemberCenterActivity.JUMP)){
            ArrayList<String> mTextArray = new ArrayList<String>();
            mTextArray.add("全部");
            mTextArray.add(TITLE1[1]);
            expandTabView.setValue(mTextArray, mViewArray);
            expandTabView.setTitle("全部", 0);
            expandTabView.setTitle(TITLE1[1], 1);
        } else {
            ArrayList<String> mTextArray = new ArrayList<String>();
            mTextArray.add("全部");
            mTextArray.add("全部");
            expandTabView.setValue(mTextArray, mViewArray);
            expandTabView.setTitle("全部", 0);
            expandTabView.setTitle("全部", 1);
        }


    }

    /**
     * 选项列表点击事件
     */
    private void initListener() {

        viewLeft.setOnSelectListener(new LeftFilterView.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewLeft, showText, "address");

            }
        });


        viewRight.setOnSelectListener(new RightFilterView.OnSelectListener() {
            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewRight, showText, "me");

            }
        });

    }

    /**
     * 点击列表返回值
     *
     * @param view     控件
     * @param showText 所返回的值
     * @param type     类型
     */
    private void onRefresh(View view, String showText, String type) {

        expandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
            expandTabView.setTitle(showText, position);
        }
        if (type.equals("address")) {
            if (!"".equals(showText) && showText.length() != 0) {
                for (int i = 0; i < TITLE2.length; i++) {
                    if (showText.equals(TITLE2[i])) {
                        tag = KEY2[i];
                    }
                }
            } else {
                tag = "";
            }

        } else if (type.equals("me")) {
            if (!"".equals(showText) && showText != null) {
                if (count != null && count.equals(UserDetailsActivity.JUMP)) {
                    for (int i = 0; i < TITLE.length; i++) {
                        if (showText.equals(TITLE[i])) {
                            frkey = KEY1[i];
                        }
                    }
                    if (frkey.equals("1")) {
                        ismy = "1";
                        userid2 = userid;
                    } else if (frkey.equals("2")) {
                        ismy = "1";
                        userid2 = GlobalParams.loginUserId;
                    } else if (frkey.equals("0")) {
                        ismy = "";
                        userid2 = "";
                    }
                } else {
                    for (int i = 0; i < TITLE1.length; i++) {
                        if (showText.equals(TITLE1[i])) {
                            frkey = KEY1[i];
                        }
                    }
                    if (frkey.equals("1")) {
                        ismy = "1";
                        userid2 = GlobalParams.loginUserId;
                    } else if (frkey.equals("0")) {
                        ismy = "";
                        userid2 = "";
                    }
                }
            } else {
                ismy = "";
                userid2 = "";
            }
        }
        p = 0;
        lists.clear();
        showDialog(LOADING_DIALOG);
        loadData2();

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
            loadData2();
            finish();

        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            this.finish();
        }
        return false;
    }

    /**
     * 下拉刷新
     *
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        listView.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        listView.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        listView.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("最后更新时间:" + str);
        p = 0;
        lists.clear();
        loadData2();
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
            listView.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
            listView.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
            listView.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
                    "最后加载时间:" + str);
            loadData2();
        } else {
            p = pageInde - 1;
            listView.onRefreshComplete();
            Toast.makeText(context, "数据已加载完毕", Toast.LENGTH_SHORT).show();
        }
    }
}
