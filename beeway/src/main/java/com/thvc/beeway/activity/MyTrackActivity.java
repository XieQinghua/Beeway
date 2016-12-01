package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
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
import com.thvc.beeway.adapter.TrackAdapter;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.ScenicAreaBean;
import com.thvc.beeway.bean.TrackFragmengBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.view.ExpandTabView;
import com.thvc.beeway.view.FilterDataSource;
import com.thvc.beeway.view.LeftFilterView;
import com.thvc.beeway.view.MiddleFilterView;
import com.thvc.beeway.view.RightFilterView;

import java.util.ArrayList;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/8/19.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class MyTrackActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<ListView> {
    private TextView publish;
    private TextView name,nickname;
    private PullToRefreshListView listView;
    private ImageView imageView, back;
    private static ArrayList<ScenicAreaBean.DataEntity.ProvinceEntity> list;
    private ArrayList<TrackFragmengBean.DataEntity.ListEntity> lists = new ArrayList<TrackFragmengBean.DataEntity.ListEntity>();
    private Context context;
    private String area = "", istype = "", frkey = "", iscom = "", ismy = "", userid = "", userid2 = "", str = "",count="",nickName="";
    private ExpandTabView expandTabView;
    private String[] TITLE1 = {"全部", "吃货", "风景", "购物", "约伴", "活动", "吐槽", "摄影", "感慨", "其他"};
    private String[] KEY1 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String[] TITLE2 = {"全部", "我的", "精品"};
    private String[] TITLE3 = {"全部", "他的","我的", "精品"};
    private String[] KEY2 = {"0", "1", "2","3"};
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private int p = 0, type = 0;
    private int pageInde = 0;
    private String id = "0";
    private MiddleFilterView viewLeft;
    private LeftFilterView viewMiddle;
    private RightFilterView viewRight;
    private TrackAdapter trackAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mytrack);
        context = this;
        str = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_NO_NOON);

        findArea();
        init();

    }

    public void init() {
        publish = (TextView) findViewById(R.id.head2_address);
        imageView = (ImageView) findViewById(R.id.activity_mytrack_image);
        back = (ImageView) findViewById(R.id.head2_back);
        name = (TextView) findViewById(R.id.head2_title);
        nickname = (TextView) findViewById(R.id.head2_title2);
        listView = (PullToRefreshListView) findViewById(R.id.activity_mytrack_listview);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(this);
        listView.setVisibility(View.VISIBLE);
        publish.setText("发布");
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SelectLabelPopupWindow(MyTrackActivity.this, publish);

            }
        });
        name.setText("足迹");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        trackAdapter = new TrackAdapter(lists, MyTrackActivity.this);
        listView.setAdapter(trackAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, TrackDetailsActivity.class);
                intent.putExtra("id", lists.get(i - 1).getId());
                intent.putExtra("userid", lists.get(i - 1).getUserid());
                startActivity(intent);
            }
        });


    }

    //发布足迹标签的PopupWindow
    public class SelectLabelPopupWindow extends PopupWindow {
        public SelectLabelPopupWindow(Context context, View parent) {
            super(context);
            View view = View.inflate(context, R.layout.popup_select_label, null);
            view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_ins));
            LinearLayout ll_label = (LinearLayout) view.findViewById(R.id.ll_label);
            ll_label.startAnimation(AnimationUtils.loadAnimation(context, R.anim.push_bottom_in_2));
            TextView tv_label_cancel = (TextView) view.findViewById(R.id.tv_label_cancel);
            tv_label_cancel.startAnimation(AnimationUtils.loadAnimation(context, R.anim.push_bottom_in_2));
            setWidth(ViewGroup.LayoutParams.FILL_PARENT);
            //修改高度显示，解决被手机底部虚拟键挡住的问题
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            //实例化一个ColorDrawable颜色为半透明
            setBackgroundDrawable(new ColorDrawable(0xD5000000));
//            setBackgroundDrawable(getResources().getDrawable(R.drawable.lake));
            //menuview添加ontouchlistener监听判断获取触屏位置如果在选择框外面则销毁弹出框
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int top_height = view.findViewById(R.id.ll_label).getTop();
                    int bottom_height = view.findViewById(R.id.ll_label).getBottom();
                    int left_width = view.findViewById(R.id.ll_label).getLeft();
                    int right_width = view.findViewById(R.id.ll_label).getRight();
                    int y = (int) motionEvent.getY();
                    int x = (int) motionEvent.getX();
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        if (y < top_height || y > bottom_height || x < left_width || x > right_width) {
                            dismiss();
                        }
                    }
                    return true;
                }
            });
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.CENTER, 0, 0);
            update();
            TextView tv_label_chihuo = (TextView) view.findViewById(R.id.tv_label_chihuo);
            TextView tv_label_jingse = (TextView) view.findViewById(R.id.tv_label_jingse);
            TextView tv_label_gouwu = (TextView) view.findViewById(R.id.tv_label_gouwu);

            TextView tv_label_yueban = (TextView) view.findViewById(R.id.tv_label_yueban);
            TextView tv_label_huodong = (TextView) view.findViewById(R.id.tv_label_huodong);
            TextView tv_label_tucao = (TextView) view.findViewById(R.id.tv_label_tucao);


            TextView tv_label_sheying = (TextView) view.findViewById(R.id.tv_label_sheying);
            TextView tv_label_gankai = (TextView) view.findViewById(R.id.tv_label_gankai);
            TextView tv_label_qita = (TextView) view.findViewById(R.id.tv_label_qita);

            intentAddTrack(tv_label_chihuo);
            intentAddTrack(tv_label_jingse);
            intentAddTrack(tv_label_gouwu);

            intentAddTrack(tv_label_yueban);
            intentAddTrack(tv_label_huodong);
            intentAddTrack(tv_label_tucao);

            intentAddTrack(tv_label_sheying);
            intentAddTrack(tv_label_gankai);
            intentAddTrack(tv_label_qita);

        }

        public void intentAddTrack(final TextView textview) {
            textview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyTrackActivity.this, AddTrackActivity.class);
                    intent.putExtra("label", textview.getText());
                    startActivity(intent);
                    dismiss();
                }
            });
        }
    }

    //获取足迹数据
    public void TrackHttp() {

        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();//定义访问服务器参数
                params.addQueryStringParameter("area", area);
                params.addQueryStringParameter("istype", istype);
                params.addQueryStringParameter("iscom", iscom);
                params.addQueryStringParameter("ismy", ismy);
                params.addQueryStringParameter("userid", userid2);
                params.addQueryStringParameter("p", p + "");
                String url = params.myRequestParams(params);
                System.out.println(" UrlPools.MYTRACKALL  + url"+ UrlPools.MYTRACKALL + "?" + url);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.MYTRACKALL + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        TrackFragmengBean trackFragmengBean = paraseData(result);
                        if (trackFragmengBean.getStatus() == 1) {
                            pageInde = trackFragmengBean.getData().getTotalPage();
                            ArrayList<TrackFragmengBean.DataEntity.ListEntity> listEntities = (ArrayList<TrackFragmengBean.DataEntity.ListEntity>) trackFragmengBean.getData().getList();
                            if (listEntities != null && listEntities.size() != 0) {
                                lists.addAll(listEntities);
                                trackAdapter.notifyDataSetChanged();
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
                            Toast.makeText(context, trackFragmengBean.getInfo(), Toast.LENGTH_SHORT).show();
                        }

                        removeDialog(LOADING_DIALOG);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(context, "网络错误！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();

    }

    /**
     * 获取地理位置列表
     */
    public void findArea() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
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
                        initent();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(context, "网络获取失败！", Toast.LENGTH_SHORT).show();
                    }

                });

            }
        }.start();
    }
    public void initent(){
        Intent intent = getIntent();
        id = intent.getStringExtra("Homeid");
        count = intent.getStringExtra("count");
        istype = String.valueOf(id);
        if (id != null && id.length() != 0) {
            type = Integer.parseInt(id);
        }
        if (count!=null&& count.equals(UserDetailsActivity.JUMP)){
            ismy = "1";
            iscom = "";
            userid2 = intent.getStringExtra("userid");
            userid = intent.getStringExtra("userid");
            nickName = intent.getStringExtra("nickname");
            nickname.setVisibility(View.VISIBLE);
            nickname.setText("("+nickName+")");
        }else if (count!=null&& count.equals(MemberCenterActivity.JUMP)){
            ismy = "1";
            iscom = "";
            userid2 = GlobalParams.loginUserId;
        }
        area =  list.get(0).getCitys().get(0).getId();
        TrackHttp();
        initView();
        initVaule();
        initListener();

    }
    public static ArrayList<ScenicAreaBean.DataEntity.ProvinceEntity> createPricegroupsItems() {
        return list;
    }

    public ScenicAreaBean scenicAreaData(String result) {
        Gson gson = new Gson();
        ScenicAreaBean scenicAreaBean = gson.fromJson(result, ScenicAreaBean.class);
        return scenicAreaBean;
    }

    public TrackFragmengBean paraseData(String result) {
        Gson gson = new Gson();
        TrackFragmengBean trackFragmengBean = gson.fromJson(result, TrackFragmengBean.class);
        return trackFragmengBean;
    }

    /**
     * 初始化选项列表
     */
    private void initView() {

        expandTabView = (ExpandTabView) findViewById(R.id.expandtab_view);

        viewLeft = new MiddleFilterView(this, "track");

        viewMiddle = new LeftFilterView(this, FilterDataSource.createPriceFilterItems(), "middle");
        if (count != null && count.equals(UserDetailsActivity.JUMP)) {
            viewRight = new RightFilterView(this, FilterDataSource.createSortFilterItems2());
        }else {
            viewRight = new RightFilterView(this, FilterDataSource.createSortFilterItems());
        }
    }

    private void initVaule() {

        mViewArray.add(viewLeft);
        mViewArray.add(viewMiddle);
        mViewArray.add(viewRight);
        ArrayList<String> mTextArray = new ArrayList<String>();

        if (type != 0) {
            mTextArray.add(list.get(0).getCitys().get(0).getTitle());
            mTextArray.add(TITLE1[type]);
            mTextArray.add("全部");

            expandTabView.setValue(mTextArray, mViewArray);

            expandTabView.setTitle(list.get(0).getCitys().get(0).getTitle(), 0);
            expandTabView.setTitle(TITLE1[type], 1);
            expandTabView.setTitle("全部", 2);
        } else {
            if (count != null && count.equals(UserDetailsActivity.JUMP)) {
                mTextArray.add(list.get(0).getCitys().get(0).getTitle());
                mTextArray.add("类型");
                mTextArray.add(TITLE3[1]);

                expandTabView.setValue(mTextArray, mViewArray);

                expandTabView.setTitle(list.get(0).getCitys().get(0).getTitle(), 0);
                expandTabView.setTitle("类型", 1);
                expandTabView.setTitle(TITLE3[1], 2);
            } else if (count!=null&& count.equals(MemberCenterActivity.JUMP)){
                mTextArray.add(list.get(0).getCitys().get(0).getTitle());
                mTextArray.add("类型");
                mTextArray.add(TITLE2[1]);

                expandTabView.setValue(mTextArray, mViewArray);

                expandTabView.setTitle(list.get(0).getCitys().get(0).getTitle(), 0);
                expandTabView.setTitle("类型", 1);
                expandTabView.setTitle(TITLE2[1], 2);
            }else {
                mTextArray.add(list.get(0).getCitys().get(0).getTitle());
                mTextArray.add("类型");
                mTextArray.add("全部");

                expandTabView.setValue(mTextArray, mViewArray);

                expandTabView.setTitle(list.get(0).getCitys().get(0).getTitle(), 0);
                expandTabView.setTitle("类型", 1);
                expandTabView.setTitle("全部", 2);
            }
        }
    }

    /**
     * 选项列表点击事件
     */
    private void initListener() {

        viewLeft.setOnSelectListener(new MiddleFilterView.OnItemSelectListener() {

            @Override
            public void getValue(String showText) {
                onRefresh(viewLeft, showText, "address");

            }
        });
        viewMiddle.setOnSelectListener(new LeftFilterView.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewMiddle, showText, "type");

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
            if (list != null && !"".equals(list)) {
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.get(i).getCitys().size(); j++) {
                        if (showText.equals(list.get(i).getCitys().get(j).getTitle())) {
                            area = list.get(i).getCitys().get(j).getId();
                        }
                    }
                }
            }
        } else if (type.equals("type")) {
            if (!"".equals(showText) && showText != null) {
                for (int i = 0; i < TITLE1.length; i++) {
                    if (showText.equals(TITLE1[i])) {
                        istype = KEY1[i];
                    }
                }
            } else {
                istype = "";
            }
        } else if (type.equals("me")) {
            if (!"".equals(showText) && showText != null) {
                if (count != null && count.equals(UserDetailsActivity.JUMP)) {
                    for (int i = 0; i < TITLE3.length; i++) {
                        if (showText.equals(TITLE3[i])) {
                            frkey = KEY2[i];
                        }
                    }
                    if (frkey.equals("1")) {
                        ismy = "1";
                        iscom = "";
                        userid2 = userid;
                    } else if (frkey.equals("2")) {
                        ismy = "1";
                        iscom = "";
                        userid2 = GlobalParams.loginUserId;
                    } else if (frkey.equals("0")) {
                        ismy = "";
                        iscom = "";
                        userid2 = "";
                    } else if (frkey.equals("3")) {
                        ismy = "";
                        iscom = "1";
                        userid2 = "";
                    }
                }else {
                    for (int i = 0; i < TITLE2.length; i++) {
                        if (showText.equals(TITLE2[i])) {
                            frkey = KEY2[i];
                        }
                    }
                    if (frkey.equals("1")) {
                        ismy = "1";
                        iscom = "";
                        userid2 = GlobalParams.loginUserId;
                    } else if (frkey.equals("2")) {
                        ismy = "";
                        iscom = "1";
                        userid2 = "";
                    } else if (frkey.equals("0")) {
                        ismy = "";
                        iscom = "";
                        userid2 = "";
                    }
                }
            } else {
                ismy = "";
                iscom = "";
                userid2 = "";
            }
        }
        lists.clear();
        p = 0;
        showDialog(LOADING_DIALOG);
        TrackHttp();
        Toast.makeText(MyTrackActivity.this, showText, Toast.LENGTH_SHORT).show();

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
            TrackHttp();
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
        TrackHttp();


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
            TrackHttp();
        } else {
            p = pageInde - 1;
//            TrackHttp();
            listView.onRefreshComplete();
            Toast.makeText(context, "数据已加载完毕", Toast.LENGTH_SHORT).show();
        }
    }
}
