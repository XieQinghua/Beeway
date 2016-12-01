package com.thvc.beeway.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.chatui.activity.AddContactActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.thvc.beeway.R;
import com.thvc.beeway.Release.MessageCenterDao;
import com.thvc.beeway.activity.IntroduceActivity;
import com.thvc.beeway.activity.MainActivity;
import com.thvc.beeway.activity.MyTrackActivity;
import com.thvc.beeway.activity.MyTravelsActivity;
import com.thvc.beeway.activity.ScenicActivity;
import com.thvc.beeway.activity.ScenicDetailsActivity;
import com.thvc.beeway.activity.SearchActivity;
import com.thvc.beeway.activity.TravelsDetailsActivity;
import com.thvc.beeway.adapter.HomeTrackAdapter;
import com.thvc.beeway.adapter.HomeTravelAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.HomeBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.ExampleUtil;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;
import com.thvc.beeway.view.MyMessagePerson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * com.thvc.beeway.fragment
 * 创建日期： 2015/9/22.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class HomeActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener<ScrollView>, View.OnClickListener {
    private ImageView search, home_image1, home_image2, home_image3;
    private TextView nickname;
    private ListView home_track_listView, home_travel_listView;
    private TextView home_bigTitile, home_smallTitile, title1, title2, title3, ctitle1, ctitle2, ctitle3, home_more, home_more2;
    private ImageLoader imageLoader;
    private HomeTrackAdapter homeTrackAdapter;
    private HomeTravelAdapter homeTravelAdapter;
    private LinearLayout layout1, layout2, layout3, layout4, home_linear, home_linear1, home_linear2, home_linear3;
    private HomeBean homeBean;

    private Context context = this;
    private CircleImageView imageView;
    private PullToRefreshScrollView scrview;
    private String str;

    private ViewPager viewPager;
    private FrameLayout fl_banner;//轮播图布局
    private LinearLayout v_dot;
    private List<View> dots; // 图片标题正文的那些点
    private int currentItem = 0; // 当前图片的索引号
    private ScheduledExecutorService scheduledExecutorService;
    DisplayImageOptions options;
    List<String> res;
    private List<String> urls;
    private List<String> title;
    private ImageView im_prompt;
    private String status;
    private String  userid;
    private String userids;

    // 切换当前显示的图片
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        userids = BeewayApplication.getInstance().getmUserid(this);//拿到当前用户的用户名
        loadData();
        setAlias();
    }

    public void init() {
        layout1 = (LinearLayout) findViewById(R.id.home_layout);
        layout2 = (LinearLayout) findViewById(R.id.home_layout2);
        layout3 = (LinearLayout) findViewById(R.id.home_layout3);
        layout4 = (LinearLayout) findViewById(R.id.home_layout4);
        home_linear = (LinearLayout) findViewById(R.id.home_linear);
        home_linear1 = (LinearLayout) findViewById(R.id.home_linear1);
        home_linear2 = (LinearLayout) findViewById(R.id.home_linear2);
        home_linear3 = (LinearLayout) findViewById(R.id.home_linear3);
        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);
        home_linear1.setOnClickListener(this);
        home_linear2.setOnClickListener(this);
        home_linear3.setOnClickListener(this);
        home_track_listView = (ListView) findViewById(R.id.home_track_listView);
        home_travel_listView = (ListView) findViewById(R.id.home_travel_listView);
        title1 = (TextView) findViewById(R.id.home_title1);
        title2 = (TextView) findViewById(R.id.home_title2);
        title3 = (TextView) findViewById(R.id.home_title3);
        ctitle1 = (TextView) findViewById(R.id.home_ctitle1);
        ctitle2 = (TextView) findViewById(R.id.home_ctitle2);
        ctitle3 = (TextView) findViewById(R.id.home_ctitle3);
        home_more = (TextView) findViewById(R.id.home_more);
        home_more2 = (TextView) findViewById(R.id.home_more2);
        home_more.setOnClickListener(this);
        home_more2.setOnClickListener(this);
        home_image1 = (ImageView) findViewById(R.id.home_image1);
        home_image2 = (ImageView) findViewById(R.id.home_image2);
        home_image3 = (ImageView) findViewById(R.id.home_image3);
        home_smallTitile = (TextView) findViewById(R.id.home_smallTitile);
        home_more = (TextView) findViewById(R.id.home_more);
        home_bigTitile = (TextView) findViewById(R.id.home_bigTitile);
        imageLoader = ImageLoader.getInstance();

        fl_banner = (FrameLayout) findViewById(R.id.fl_banner);

        home_track_listView = (ListView) findViewById(R.id.home_track_listView);
        v_dot = (LinearLayout) findViewById(R.id.v_dot);
        viewPager = (ViewPager) findViewById(R.id.vp);

        search = (ImageView) findViewById(R.id.iv_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SearchActivity.class);
                startActivity(intent);
            }
        });
        imageView = (CircleImageView) findViewById(R.id.iv_user_img);
        imageView.setImageUrl(UrlPools.getAvatarUrl(this), VolleyHepler.getInstance().getImageLoader());
        nickname = (TextView) findViewById(R.id.tv_title);

        //打开侧滑菜单
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mMenu();
            }
        });
        scrview = (PullToRefreshScrollView) findViewById(R.id.sv_track);

//        scrview.setPullLoadEnable(false);// 设置让它上拉，FALSE为不让上拉，便不加载更多数据
//        listView.setXListViewListener(this);

        str = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_NO_NOON);
        scrview.setMode(PullToRefreshBase.Mode.BOTH);
        scrview.setOnRefreshListener(this);
        scrview.setVisibility(View.INVISIBLE);
        scrview.setPullToRefreshOverScrollEnabled(false);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    scrview.requestDisallowInterceptTouchEvent(true);
                } else {
                    scrview.requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });
        home_travel_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, TravelsDetailsActivity.class);
                intent.putExtra("id", homeBean.getData().getTravel().get(i).getId());
                intent.putExtra("userid", homeBean.getData().getTravel().get(i).getUserid());
                startActivity(intent);
            }
        });

        im_prompt = (ImageView)this.findViewById(R.id.im_prompt);//推送消息提示图片
        userid = BeewayApplication.getInstance().getmUserid(this);//拿到当前用户的用户名
    }

    @Override
    public void onStart() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每五秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 5, TimeUnit.SECONDS);
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //查询数据库 显示推送消息提示
        MessageCenterDao messageCenterDao = new MessageCenterDao(getApplication());
        List<MyMessagePerson> persons = messageCenterDao.select(userid);
        for (MyMessagePerson person : persons) {
            status = person.getStatuss();//拿到数据库里的标示
            if (persons.size() > 0 && status.equals("0")) {
                im_prompt.setVisibility(View.VISIBLE);
            }else {
                im_prompt.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onStop() {
        // 当Activity不可见的时候停止切换
        scheduledExecutorService.shutdown();
        super.onStop();
    }


    /**
     * 换行切换任务
     *
     * @author Administrator
     */
    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (viewPager) {
                currentItem = (currentItem + 1) % res.size();
                handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
            }
        }

    }

    /**
     * 当ViewPager中页面的状态发生改变时调用
     *
     * @author xieqinghua
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        private int oldPosition = 0;

        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(int position) {
            currentItem = position;
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }

        public void onPageScrollStateChanged(int arg0) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    }

    /**
     * 填充ViewPager页面的适配器
     *
     * @author Administrator
     */
    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return res.size();
        }

        @Override
        public Object instantiateItem(View arg0, final int arg1) {
            ImageView imageview = new ImageView(context);
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.getInstance().displayImage(res.get(arg1), imageview, options);
            ((ViewPager) arg0).addView(imageview);
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, IntroduceActivity.class);
                    intent.putExtra("url", urls.get(arg1));
                    intent.putExtra("title", title.get(arg1));
                    startActivity(intent);
                }
            });
            return imageview;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }

    public void loadData() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", GlobalParams.loginUserId);
                String  url= params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.HOME+"?"+url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        System.out.println("home_result====" + GlobalParams.loginUserId);

                        homeBean = homeBean(result);
                        if (homeBean.getStatus() == 1) {
                            //轮播图
                            if (homeBean.getData().getBanner() != null) {
                                res = new ArrayList<String>();
                                dots = new ArrayList<View>();
                                urls = new ArrayList<String>();
                                title = new ArrayList<String>();
                                for (int i = 0; i < homeBean.getData().getBanner().size(); i++) {
                                    res.add(UrlPools.QINIU +
                                            homeBean.getData().getBanner().get(i).getSingle().getFileurl() + "-Thumb640");
                                    urls.add(homeBean.getData().getBanner().get(i).getUrl());
                                    title.add(homeBean.getData().getBanner().get(i).getTitle());

                                    View view = new View(context);
                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(14, 14);
                                    layoutParams.setMargins(3, 0, 3, 0);
                                    view.setLayoutParams(layoutParams);
                                    view.setBackgroundResource(R.drawable.dot_normal);
                                    v_dot.addView(view);
                                    dots.add(view);
                                }
                                options = new DisplayImageOptions.Builder()
                                        .cacheInMemory(true) // default
                                        .cacheOnDisk(true) // default
                                        .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                                        .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                                        .build();

                                viewPager.setAdapter(new MyAdapter());
                                // 设置一个监听器，当ViewPager中的页面改变时调用
                                viewPager.setOnPageChangeListener(new MyPageChangeListener());
                            }
                            //景点
                            if (homeBean.getData().getScenic() != null) {
                                DisplayImageOptions options = new DisplayImageOptions.Builder()
                                        .showStubImage(R.drawable.imgurl)         //加载开始默认的图片
                                        .showImageForEmptyUri(R.drawable.imgurl)     //url爲空會显示该图片，自己放在drawable里面的
                                        .showImageOnFail(R.drawable.imgurl)                //加载图片出现问题，会显示该图片
                                        .cacheInMemory()                                               //缓存用
                                        .cacheOnDisc()                                                    //缓存用
                                        .build();
                                home_bigTitile.setText(homeBean.getData().getScenic().getBigTitile());
                                home_smallTitile.setText(homeBean.getData().getScenic().getSmallTitile());
                                if (homeBean.getData().getScenic().getList().size() > 0) {

                                    String url = UrlPools.QINIU + homeBean.getData().getScenic().getList().get(0).getImage().getSingle().getFileurl() + "-Thumb640";
                                    imageLoader.displayImage(url, home_image1, options);
                                    title1.setText(homeBean.getData().getScenic().getList().get(0).getTitle());
                                    ctitle1.setText(homeBean.getData().getScenic().getList().get(0).getCtitle());
                                    if (homeBean.getData().getScenic().getList().size() > 1) {
                                        String url1 = UrlPools.QINIU + homeBean.getData().getScenic().getList().get(1).getImage().getSingle().getFileurl() + "-Thumb640";
                                        imageLoader.displayImage(url1, home_image2, options);
                                        title2.setText(homeBean.getData().getScenic().getList().get(1).getTitle());
                                        ctitle2.setText(homeBean.getData().getScenic().getList().get(1).getCtitle());
                                        if (homeBean.getData().getScenic().getList().size() > 2) {
                                            String url2 = UrlPools.QINIU + homeBean.getData().getScenic().getList().get(2).getImage().getSingle().getFileurl() + "-Thumb640";
                                            imageLoader.displayImage(url2, home_image3, options);
                                            title3.setText(homeBean.getData().getScenic().getList().get(2).getTitle());
                                            ctitle3.setText(homeBean.getData().getScenic().getList().get(2).getCtitle());
                                        } else {
                                            home_linear3.setVisibility(View.GONE);
                                        }
                                    } else {
                                        home_linear2.setVisibility(View.GONE);
                                        home_linear3.setVisibility(View.GONE);
                                    }
                                } else {
                                    home_linear.setVisibility(View.GONE);
                                }

                            }
                            //精选足迹
                            if (homeBean.getData().getTrack() != null) {
                                ArrayList<HomeBean.DataEntity.TrackEntity> lists = (ArrayList<HomeBean.DataEntity.TrackEntity>) homeBean.getData().getTrack();
                                if (lists.size() != 0 && lists != null) {
                                    homeTrackAdapter = new HomeTrackAdapter(lists, context);
                                    home_track_listView.setAdapter(homeTrackAdapter);
                                    homeTrackAdapter.notifyDataSetChanged();
                                    setListViewHeight(home_track_listView);

                                }
                            }
                            //精选游记
                            if (homeBean.getData().getTravel() != null) {
                                ArrayList<HomeBean.DataEntity.TravelEntity> travellists = (ArrayList<HomeBean.DataEntity.TravelEntity>) homeBean.getData().getTravel();
                                if (travellists.size() != 0 && travellists != null) {
                                    homeTravelAdapter = new HomeTravelAdapter(travellists, context);
                                    home_travel_listView.setAdapter(homeTravelAdapter);
                                    homeTravelAdapter.notifyDataSetChanged();
                                    setListViewHeight(home_travel_listView);
                                }
                            }


                            viewPager.setFocusable(true);
                            viewPager.setFocusableInTouchMode(true);
                            viewPager.requestFocus();
                            scrview.onRefreshComplete();
                        }
                        scrview.setVisibility(View.VISIBLE);
                        removeDialog();

                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(HomeActivity.this, "网络连接失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
    }

    public HomeBean homeBean(String result) {
        Gson gson = new Gson();
        HomeBean homeBean = gson.fromJson(result, HomeBean.class);
        return homeBean;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_more:
                Intent intent = new Intent(this, ScenicActivity.class);
                intent.putExtra("mark", "Details");
                startActivity(intent);
                break;
            case R.id.home_more2:
                Intent intent2 = new Intent(this, MyTravelsActivity.class);
                intent2.putExtra("mark", "Details");
                startActivity(intent2);
                break;
            case R.id.home_layout:
                Intent intent1 = new Intent(getApplicationContext(), MyTrackActivity.class);
                startActivity(intent1);
                break;
            case R.id.home_layout2:
                Intent intent3 = new Intent(getApplicationContext(), MyTravelsActivity.class);
                startActivity(intent3);
                break;
            case R.id.home_layout3:
                Intent intent4 = new Intent(getApplicationContext(), CustomerFragment.class);
                startActivity(intent4);
                break;
            case R.id.home_layout4:
                Intent intent5 = new Intent(getApplicationContext(), AddContactActivity.class);
                startActivity(intent5);
                break;
            case R.id.home_linear1:
                Intent intent6 = new Intent(getApplicationContext(), ScenicDetailsActivity.class);
                intent6.putExtra("id", homeBean.getData().getScenic().getList().get(0).getId());
                startActivity(intent6);
                break;
            case R.id.home_linear2:
                Intent intent7 = new Intent(getApplicationContext(), ScenicDetailsActivity.class);
                intent7.putExtra("id", homeBean.getData().getScenic().getList().get(1).getId());
                startActivity(intent7);
                break;
            case R.id.home_linear3:
                Intent intent8 = new Intent(getApplicationContext(), ScenicDetailsActivity.class);
                intent8.putExtra("id", homeBean.getData().getScenic().getList().get(2).getId());
                startActivity(intent8);
                break;

        }
    }

    @Override
    public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
        scrview.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        scrview.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        scrview.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("最后更新时间:" + str);
        removeView();

        loadData();
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
        params.height += 10;
        listView.setLayoutParams(params);
    }

    private void removeView() {
        //获取linearlayout子view的个数
        int count = v_dot.getChildCount();
        //研究整个LAYOUT布局，第0位的是含add和remove两个button的layout
        //第count-1个是那个文字被置中的textview
        //因此，在remove的时候，只能操作的是0<location<count-1这个范围的
        //在执行每次remove时，我们从count-2的位置即textview上面的那个控件开始删除~
        for (int i = 0; i < count; i++) {
            v_dot.removeViewAt(count - 2);
        }
    }


    /**
     * 极光推送设置别名
     */
// 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    private void setAlias() {
        String alias = userids;
        if (TextUtils.isEmpty(alias)) {
            //Toast.makeText(PushSetActivity.this,R.string.error_alias_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!ExampleUtil.isValidTagAndAlias(alias)) {
           // Toast.makeText(PushSetActivity.this,R.string.error_tag_gs_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
//                    logs = "Set tag and alias success";
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
//                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
//                    logs = "Failed with errorCode = " + code;
            }
//            ExampleUtil.showToast(logs, getApplicationContext());
        }
    };
    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
            }
        }
    };


}
