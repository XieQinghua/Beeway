package com.thvc.beeway.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.thvc.beeway.R;
import com.thvc.beeway.activity.TrackDetailsActivity;
import com.thvc.beeway.activity.TravelsDetailsActivity;
import com.thvc.beeway.adapter.TrackAdapter;
import com.thvc.beeway.adapter.TravelAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.bean.TrackFragmengBean;
import com.thvc.beeway.bean.TrackFragmengBean.DataEntity.ListEntity;
import com.thvc.beeway.bean.TrackImageBean;
import com.thvc.beeway.bean.TravelBean;
import com.thvc.beeway.bean.TravelBean.DataEntity.ListEntity2;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.view.CustomProgressDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * com.thvc.beeway.fragment
 * 创建日期： 2015/8/13.
 * 版权：天合融创
 * 作者：yuyalong.
 * 版本号：1.0.
 * 修改历史：
 */
public class MainTrackFragment extends Fragment {
    private int p = 0, pageInde = 0, page = 0, tabIndex = 0, tab = 0;
    private View view;
    private ImageView imageView;
    private ListView listView;
    private int ismy = 0;
    private String str;
    private ViewPager viewPager;
    private FrameLayout fl_banner;//轮播图布局
    private List<ImageView> imageViews; // 滑动的图片集合
    private TextView tv_title;
    private LinearLayout v_dot;
    private List<View> dots; // 图片标题正文的那些点
    private int currentItem = 0; // 当前图片的索引号
    private Handler mHandler;
    private boolean mBoolean = true;
    private ArrayList<ListEntity> lists = new ArrayList<>();
    private ArrayList<ListEntity2> list2 = new ArrayList<>();
    private ArrayList<ListEntity2> list3 = new ArrayList<>();
    private TrackAdapter trackAdapter;
    private TravelAdapter travelAdapter;
    private ScrollView scrview;

    private ScheduledExecutorService scheduledExecutorService;
    DisplayImageOptions options;
    List<String> res;

    // 切换当前显示的图片
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_track_item, container, false);
        fl_banner = (FrameLayout) view.findViewById(R.id.fl_banner);

        listView = (ListView) view.findViewById(R.id.track_item_lv);
        imageView = (ImageView) view.findViewById(R.id.track_item_image);
        v_dot = (LinearLayout) view.findViewById(R.id.v_dot);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        viewPager = (ViewPager) view.findViewById(R.id.vp);
        scrview = (ScrollView) view.findViewById(R.id.sv_track);
//        listView.setPullLoadEnable(true);// 设置让它上拉，FALSE为不让上拉，便不加载更多数据
//        listView.setXListViewListener(this);
        mHandler = new Handler();
        str = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_NO_NOON);
//        scrview.setMode(PullToRefreshBase.Mode.BOTH);
//        scrview.setOnRefreshListener(this);
        scrview.setVisibility(View.VISIBLE);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    scrview.requestDisallowInterceptTouchEvent(false);
                } else {
                    scrview.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        init();
        return view;
    }

    //跳转
    private void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            tabIndex = bundle.getInt("tabIndex", 0);
            switch (tabIndex) {
                case 0:
                    p = 0;
                    httplunbo();
                    trackAdapter = new TrackAdapter(lists, getActivity());
                    listView.setAdapter(trackAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity(), TrackDetailsActivity.class);
                            intent.putExtra("id", lists.get(i).getId());
                            intent.putExtra("userid", lists.get(i).getUserid());
//                                            intent.putExtra("listEntities",listEntities);
                            startActivity(intent);
                        }
                    });
                    break;
                case 1:
                    ismy = 3;
                    tab = 0;

                    travelAdapter = new TravelAdapter(list2, getActivity());
                    listView.setAdapter(travelAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity(), TravelsDetailsActivity.class);
                            intent.putExtra("id", list2.get(i).getId());
                            intent.putExtra("userid", list2.get(i).getUserid());
                            startActivity(intent);
                        }
                    });
                    loadData2();
                    fl_banner.setVisibility(View.GONE);
                    break;
                case 2:
                    ismy = 2;
                    tab = 0;
                    loadData2();
                    travelAdapter = new TravelAdapter(list3, getActivity());
                    listView.setAdapter(travelAdapter);
                    fl_banner.setVisibility(View.GONE);
                    break;
            }
        }

    }

    /**
     * 网络获取足迹列表数据
     */
    public void loadData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("p", p + "");
                params.addQueryStringParameter("keyword", "");
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.TRACKALL + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {

                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        if (!"".equals(result) && result != null) {
                            TrackFragmengBean trackFragmengBean = paraseData(result);
                            if (trackFragmengBean.getStatus() == 1) {
                                pageInde = trackFragmengBean.getData().getTotalPage();
                                final ArrayList<ListEntity> listEntities = (ArrayList<ListEntity>) trackFragmengBean.getData().getList();
                                if (listEntities != null && listEntities.size() != 0) {
                                    lists.addAll(listEntities);
                                    trackAdapter.notifyDataSetChanged();
                                    setListViewHeight(listView);
//                                    scrview.onRefreshComplete();

                                } else {
                                    if (p == 0) {
                                        listView.setVisibility(View.GONE);
                                        imageView.setVisibility(View.VISIBLE);
                                    }
                                    Toast.makeText(getActivity(), "该选项无数据！", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                listView.setVisibility(View.GONE);
                                imageView.setVisibility(View.VISIBLE);
                                Toast.makeText(getActivity(), trackFragmengBean.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        removeDialog();
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        //访问失败
                        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }.start();

    }

    /**
     * 网络获取游记、蜜蜂列表数据
     */
    public void loadData2() {
        if (mBoolean) {
            mBoolean = false;
        } else {
            mBoolean = true;
        }
        onCreateDialog(mBoolean);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                String userid = "";
                if (ismy == 2) {
                    userid = new BeewayApplication().getmUserid(getActivity());
                }
                String keyword = "";
                MyRequestParams params = new MyRequestParams();//定义访问服务器参数
                params.addQueryStringParameter("p", tab + "");
                params.addQueryStringParameter("userid", userid);
                params.addQueryStringParameter("ismy", ismy + "");
                params.addQueryStringParameter("keywords", keyword);
                String url = params.myRequestParams(params);
                System.out.println("main p= " + p + " userid= " + userid + " ismy= " + ismy + " keyword= " + keyword + " tag= " + tab);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.TRACKALL2_3 + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {

                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        TravelBean travelBean = paraseDatatravel(result);
                        if (travelBean.getStatus() == 1) {
                            tab = travelBean.getData().getTotalPage();
                            ArrayList<ListEntity2> listEntities2 = (ArrayList<ListEntity2>) travelBean.getData().getList();
                            if (listEntities2 != null && listEntities2.size() != 0) {
                                if (ismy == 2) {
                                    list3.addAll(listEntities2);
                                } else if (ismy == 3) {
                                    list2.addAll(listEntities2);
                                }
                                setListViewHeight(listView);
//                                scrview.onRefreshComplete();
                                travelAdapter.notifyDataSetChanged();
                                listView.setVisibility(View.VISIBLE);
                                imageView.setVisibility(View.GONE);
                            } else {
                                if (p == 0) {
                                    listView.setVisibility(View.GONE);
                                    imageView.setVisibility(View.VISIBLE);

                                }
                                Toast.makeText(getActivity(), "该选项无数据！", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            listView.setVisibility(View.GONE);
                            imageView.setVisibility(View.VISIBLE);
                        }
                        removeDialog();
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        //访问失败
                        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }.start();

    }

    @Override
    public void onStart() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每五秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 5, TimeUnit.SECONDS);
        super.onStart();
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
        public Object instantiateItem(View arg0, int arg1) {
            ImageView imageview = new ImageView(getActivity());
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.getInstance().displayImage(res.get(arg1), imageview, options);
            ((ViewPager) arg0).addView(imageview);
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

    /**
     * 网络获取轮播图数据
     */
    public void httplunbo() {

        onCreateDialog(mBoolean);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("cateid", 2 + "");
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.TUPIANLUNBO + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        System.out.println("result[i]====" + result);
                        TrackImageBean trackImageBean = paraseDataimage(result);
                        if (trackImageBean.getStatus() == 1) {
                            res = new ArrayList<String>();
                            dots = new ArrayList<View>();
                            for (int i = 0; i < trackImageBean.getData().size(); i++) {
                                res.add(UrlPools.QINIU +
                                        trackImageBean.getData().get(i).getSingle().getFileurl());

//                                dots.add(view.findViewById(R.id.v_dot0));
                                View view = new View(getActivity());
                                LayoutParams layoutParams = new LayoutParams(14, 14);
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
                        loadData();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
            }
        }.start();

    }

    public TrackImageBean paraseDataimage(String result) {
        Gson gson = new Gson();
        TrackImageBean trackImageBean = gson.fromJson(result, TrackImageBean.class);
        return trackImageBean;
    }

    public TrackFragmengBean paraseData(String result) {
        Gson gson = new Gson();
        TrackFragmengBean trackFragmengBean = gson.fromJson(result, TrackFragmengBean.class);
        return trackFragmengBean;
    }

    public TravelBean paraseDatatravel(String result) {
        Gson gson = new Gson();
        TravelBean travelBean = gson.fromJson(result, TravelBean.class);
        return travelBean;
    }

    private CustomProgressDialog pd;

    /**
     * 加载窗口
     *
     * @param bl 是否正在加载中
     */
    protected void onCreateDialog(boolean bl) {
        if (bl) {
            pd = CustomProgressDialog.createDialog(getActivity());
            pd.show();
            pd.setCancelable(true);
            mBoolean = false;
        }
    }

    public void removeDialog() {
        if (pd != null && pd.isShowing()) {
            pd.cancel();
        }
    }


//    /**
//     * 下拉刷新
//     * @param refreshView
//     */
//    @Override
//    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//        scrview.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
//        scrview.getLoadingLayoutProxy().setPullLabel("下拉刷新");
//        scrview.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
//        refreshView.getLoadingLayoutProxy()
//                .setLastUpdatedLabel("最后更新时间:" + str);
//
//        mBoolean = false;
//        if (tabIndex == 0) {
//            p=0;
//            httplunbo();
//        } else if (tabIndex == 1) {
//            tab = 0;
//            ismy = 3;
//            loadData2();
//
//        } else if (tabIndex == 2) {
//            tab = 0;
//            ismy = 2;
//            loadData2();
//        }
//        scrview.onRefreshComplete();
//
//    }
//
//    /**
//     * 上拉加载
//     * @param refreshView
//     */
//    @Override
//    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//
//        mBoolean = false;
//        if (tabIndex == 0) {
//            if (p < pageInde-1) {
//                p++;
//                scrview.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
//                scrview.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
//                scrview.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
//                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
//                        "最后加载时间:" + str);
//                loadData();
//            } else {
//                p=pageInde-1;
//                loadData();
//                Toast.makeText(getActivity(), "数据已加载完毕", Toast.LENGTH_SHORT).show();
//            }
//        } else if (tabIndex == 1) {
//            if (tab < page-1) {
//                tab++;
//                ismy = 3;
//                scrview.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
//                scrview.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
//                scrview.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
//                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
//                        "最后加载时间:" + str);
//                loadData2();
//            } else {
//                tab=page-1;
//                loadData2();
//                Toast.makeText(getActivity(), "数据已加载完毕", Toast.LENGTH_SHORT).show();
//
//            }
//
//        } else if (tabIndex == 2) {
//            if (tab < page-1) {
//                tab++;
//                ismy = 2;
//                scrview.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
//                scrview.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
//                scrview.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
//                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
//                        "最后加载时间:" + str);
//                loadData2();
//            } else {
//                tab=page-1;
//                loadData2();
//                Toast.makeText(getActivity(), "数据已加载完毕", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//
//    }

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
        listView.setLayoutParams(params);
    }


}
