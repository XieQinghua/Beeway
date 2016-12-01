package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.thvc.beeway.R;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.AddFootprintbean;
import com.thvc.beeway.bean.TrackFragmengBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/9/16.
 */
public class MyFootstepsActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<ListView> {

    private PullToRefreshListView footprint_listview;
    private TextView tv_publish, tv_no_myfoosteps;
    private int p = 0, pageInde = 0;
    private ImageView myfoosteps_image;
    private Context context;
    private String str, title, day, money, description;
    private MyFootsepsActivityAdapter myFootsepsActivityAdapter;
    private ArrayList<TrackFragmengBean.DataEntity.ListEntity> listEntities;
    private ArrayList<AddFootprintbean> list;

    private AddFootprintbean addFootprintbean;

    private String sourcePath, titles, cost, feel;

    private HashMap<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_foosteps);
        context = this;

        //接受intent数据
        sourcePath = getIntent().getStringExtra("sourcePath");
        titles = getIntent().getStringExtra("title");
        cost = getIntent().getStringExtra("cost");
        feel = getIntent().getStringExtra("feel");

        str = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_NO_NOON);

        tv_publish = (TextView) this.findViewById(R.id.tv_publish);

        footprint_listview = (PullToRefreshListView) this.findViewById(R.id.activity_myfoosteps_listview);//我的足迹控件
        myfoosteps_image = (ImageView) this.findViewById(R.id.activity_myfoosteps_image);
        tv_no_myfoosteps = (TextView) this.findViewById(R.id.tv_no_myfoosteps);

        title = getIntent().getStringExtra("title");
        day = getIntent().getStringExtra("day");
        money = getIntent().getStringExtra("money");
        description = getIntent().getStringExtra("description");
        list = new ArrayList<AddFootprintbean>();
        tv_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getmyfootschecbox();
                Intent intent = new Intent(MyFootstepsActivity.this, AddTravelActivity.class);
                Bundle bundleObject = new Bundle();
                bundleObject.putSerializable("list", list);
                intent.putExtras(bundleObject);
                intent.putExtra("sourcePath", sourcePath);
                intent.putExtra("title", titles);
                intent.putExtra("cost", cost);
                intent.putExtra("feel", feel);
                startActivity(intent);
                list.clear();
                MyFootstepsActivity.this.finish();
            }
        });
        TrackHttp();

    }

    public void getmyfootschecbox() {
//        // 进行遍历
        java.util.Iterator it = isCheckMap.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
            int position = (int) entry.getKey(); //返回与此项对应的键
            String resumeId = listEntities.get(position).getId();
            String time = listEntities.get(position).getAddtime();
            String address = listEntities.get(position).getAddress();
            String content = listEntities.get(position).getContent();
            String url = UrlPools.QINIU + listEntities.get(position).getAtlas().getData().get(0).getFileurl() + "-Thumb640";
            addFootprintbean = new AddFootprintbean(resumeId, time, address, content, url);
            list.add(addFootprintbean);
        }

    }

    //获取足迹数据
    public void TrackHttp() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String ismy = String.valueOf(1);
                String userid = new BeewayApplication().getmUserid(MyFootstepsActivity.this);
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("company", "");
                params.addQueryStringParameter("istype", "");
                params.addQueryStringParameter("iscom", "");
                params.addQueryStringParameter("ismy", ismy);
                params.addQueryStringParameter("userid", userid);
                params.addQueryStringParameter("p", p + "");
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.MYTRACKALL + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        TrackFragmengBean trackFragmengBean = paraseData(result);
                        if (trackFragmengBean.getStatus() == 1) {
                            pageInde = trackFragmengBean.getData().getTotalPage();
                            listEntities = (ArrayList<TrackFragmengBean.DataEntity.ListEntity>) trackFragmengBean.getData().getList();
                            if (listEntities != null && listEntities.size() != 0) {
                                myFootsepsActivityAdapter = new MyFootsepsActivityAdapter(listEntities, context);
                                footprint_listview.setAdapter(myFootsepsActivityAdapter);
                                myFootsepsActivityAdapter.notifyDataSetChanged();
                                footprint_listview.onRefreshComplete();
                                footprint_listview.setVisibility(View.VISIBLE);
                                myfoosteps_image.setVisibility(View.GONE);
                                tv_no_myfoosteps.setVisibility(View.GONE);
                            } else {
                                if (p == 0) {
                                    footprint_listview.setVisibility(View.GONE);
                                    myfoosteps_image.setVisibility(View.VISIBLE);
                                    tv_no_myfoosteps.setVisibility(View.VISIBLE);
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

    public TrackFragmengBean paraseData(String result) {
        Gson gson = new Gson();
        TrackFragmengBean trackFragmengBean = gson.fromJson(result, TrackFragmengBean.class);
        return trackFragmengBean;
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
        footprint_listview.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        footprint_listview.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        footprint_listview.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("最后更新时间:" + str);
        p = 0;
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
            footprint_listview.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
            footprint_listview.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
            footprint_listview.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
                    "最后加载时间:" + str);
            TrackHttp();
        } else {
            p = pageInde - 1;
            TrackHttp();
            Toast.makeText(context, "数据已加载完毕", Toast.LENGTH_SHORT).show();
        }
    }


    class MyFootsepsActivityAdapter extends BaseAdapter {
        private List<TrackFragmengBean.DataEntity.ListEntity> list;
        private Context context;

        private BitmapUtils bitmapUtils;
        private ImageLoader imageLoader;
        private com.nostra13.universalimageloader.core.ImageLoader imageLoader2;

        public MyFootsepsActivityAdapter(List<TrackFragmengBean.DataEntity.ListEntity> list, Context context) {
            this.list = list;
            this.context = context;
            bitmapUtils = new BitmapUtils(context);
            imageLoader = VolleyHepler.getInstance().getImageLoader();
            imageLoader2 = com.nostra13.universalimageloader.core.ImageLoader.getInstance();

        }

//        public Map<Integer, Boolean> getCheckMap() {
//            return this.isCheckMap;
//        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = LayoutInflater.from(MyFootstepsActivity.this).inflate(R.layout.item_my_track, null);
                vh.time = (TextView) convertView.findViewById(R.id.tv_myfoot_time);
                vh.content = (TextView) convertView.findViewById(R.id.tv_myfoot_con);
                vh.address = (TextView) convertView.findViewById(R.id.tv_myfoot_address);
                vh.imageView = (CircleImageView) convertView.findViewById(R.id.im_myfootsepsa_image);
                vh.myfoots_chec_box = (CheckBox) convertView.findViewById(R.id.myfoots_chec_box);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.time.setText(getStringTime(list.get(position).getAddtime()));
            vh.address.setText(list.get(position).getAddress());
            vh.content.setText(list.get(position).getContent());
            String url = UrlPools.QINIU + list.get(position).getAtlas().getData().get(0).getFileurl() + "-Thumb640";
            vh.imageView.setImageUrl(url, VolleyHepler.getInstance().getImageLoader());
//            DisplayImageOptions options = new DisplayImageOptions.Builder()
//                    .displayer(new RoundedBitmapDisplayer(10))       //图片圆角显示，值为整数
//                    .showStubImage(R.drawable.imgurl)         //加载开始默认的图片
//                    .showImageForEmptyUri(R.drawable.imgurl)     //url爲空會显示该图片，自己放在drawable里面的
//                    .showImageOnFail(R.drawable.imgurl)                //加载图片出现问题，会显示该图片
//                    .cacheInMemory()                                               //缓存用
//                    .cacheOnDisc()                                                    //缓存用
//                    .build();
//            imageLoader2.displayImage(url, vh.imageView, options);

            vh.myfoots_chec_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(
                        CompoundButton buttonView, boolean isChecked) {
                                /*
                                 * 将选择项加载到map里面寄存
								 */
                    isCheckMap.put(position, isChecked);
                }
            });


            return convertView;
        }


        public String getStringTime(String cc_time) {
            String re_Strtime = null;
            SimpleDateFormat sdf = new SimpleDateFormat(" MM-dd HH:mm");
            long lcc_time = Long.valueOf(cc_time);
            re_Strtime = sdf.format(new Date(lcc_time * 1000L));
            return re_Strtime;
        }

        class ViewHolder {
            TextView time, content, address;
            CircleImageView imageView;
            CheckBox myfoots_chec_box;
        }

    }
}
