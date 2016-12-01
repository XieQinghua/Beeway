package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.chatui.activity.AddContactActivity;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.thvc.beeway.R;
import com.thvc.beeway.adapter.CustomerAdapter;
import com.thvc.beeway.adapter.ScenicAdapter;
import com.thvc.beeway.adapter.TrackAdapter;
import com.thvc.beeway.adapter.TravelAdapter;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.CustomerBean;
import com.thvc.beeway.bean.ScenicBean;
import com.thvc.beeway.bean.TrackFragmengBean;
import com.thvc.beeway.bean.TravelBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;

import java.util.ArrayList;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/8/24.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class SearchActivity extends BaseActivity {
    private TextView t1, t2, t3, t4, t5, title;
    private ListView listView;
    private Button bt;
    private EditText editText;
    private ImageView back;
    private LinearLayout layout;
    private Context context;
    private String ismy = "track";
    private String keyword="";
    private int p = 0;
    private TrackAdapter trackAdapter;
    private TravelAdapter travelAdapter;
    private CustomerAdapter customerAdapter;
    private ScenicAdapter scenicAdapter;
    private ArrayList<TrackFragmengBean.DataEntity.ListEntity> tracklist = new ArrayList<>();
    private ArrayList<TravelBean.DataEntity.ListEntity2> travellist = new ArrayList<>();
    private ArrayList<CustomerBean.DataEntity.ListEntity> customerlist = new ArrayList<>();
    private ArrayList<ScenicBean.DataEntity.DatasEntity> sceniclist = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);
        init();
        context = this;
    }

    public void search() {

        if (ismy.equals("track")) {
            trackAdapter = new TrackAdapter(tracklist, context);
            listView.setAdapter(trackAdapter);
            trackloadData();
        } else if (ismy.equals("travel")) {
            travelAdapter = new TravelAdapter(travellist, context);
            listView.setAdapter(travelAdapter);
            travelloadData(3);
        } else if (ismy.equals("scenic")) {
            scenicAdapter = new ScenicAdapter(sceniclist, context);
            listView.setAdapter(scenicAdapter);
            scenic();
        } else if (ismy.equals("crowdfunding")) {
            customerAdapter = new CustomerAdapter(customerlist, context);
            listView.setAdapter(customerAdapter);
            CustomerData();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                if (ismy.equals("track")) {
                    intent = new Intent(context, TrackDetailsActivity.class);
                    System.out.println("---id---" + tracklist.get(i).getId() + "---userid---" + tracklist.get(i).getUserid());
                    intent.putExtra("id", tracklist.get(i).getId());
                    intent.putExtra("userid", tracklist.get(i).getUserid());
                    startActivity(intent);
                } else if (ismy.equals("travel")) {
                    intent = new Intent(context, TravelsDetailsActivity.class);
                    intent.putExtra("id", travellist.get(i).getId());
                    intent.putExtra("userid", travellist.get(i).getUserid());
                    startActivity(intent);
                } else if (ismy.equals("scenic")) {
                    intent = new Intent(context, ScenicDetailsActivity.class);
                    intent.putExtra("id", sceniclist.get(i).getId());
                    intent.putExtra("name", sceniclist.get(i).getTitle());
                    startActivity(intent);
                } else if (ismy.equals("crowdfunding")) {
                    intent = new Intent(context, CustomerDetailsActivity.class);
                    intent.putExtra("id", customerlist.get(i).getId());
                    intent.putExtra("userid", customerlist.get(i).getUserid());
                    startActivity(intent);
                }

            }
        });
    }

    /**
     * 搜索足迹列表数据
     */
    public void trackloadData() {
        showDialog(LOADING_DIALOG);
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("p", p + "");
        params.addQueryStringParameter("keywords", keyword);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.TRACKALL + "?" + url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                //获得访问解析出来的json字符串
                String result = responseInfo.result;
                if (!"".equals(result) && result != null) {
                    TrackFragmengBean trackFragmengBean = paraseData(result);
                    if (trackFragmengBean.getStatus() == 1) {
                        ArrayList<TrackFragmengBean.DataEntity.ListEntity> list = (ArrayList<TrackFragmengBean.DataEntity.ListEntity>) trackFragmengBean.getData().getList();
                        if (list != null && list.size() != 0) {
                            tracklist.addAll(list);
                            trackAdapter.notifyDataSetChanged();

                        }

                    } else {
                        Toast.makeText(context, trackFragmengBean.getInfo(), Toast.LENGTH_SHORT).show();
                    }
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

    /**
     * 搜索游记列表数据
     */
    public void travelloadData(int ismy) {
//        startDialog();
        showDialog(LOADING_DIALOG);
        HttpUtils httpUtils = new HttpUtils();
        String userid = GlobalParams.loginUserId;
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("p", p + "");
        params.addQueryStringParameter("area", "");
        params.addQueryStringParameter("userid", userid);
        params.addQueryStringParameter("ismy", ismy + "");
        params.addQueryStringParameter("keywords", keyword);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.TRACKALL2_3 + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                //获得访问解析出来的json字符串
                String result = responseInfo.result;
                System.out.println("------------result----------" + result);
                TravelBean travelBean = paraseDatatravel(result);
                if (travelBean.getStatus() == 1) {
                    ArrayList<TravelBean.DataEntity.ListEntity2> list = (ArrayList<TravelBean.DataEntity.ListEntity2>) travelBean.getData().getList();
                    if (list != null && list.size() != 0) {
                        travellist.addAll(list);
                        travelAdapter.notifyDataSetChanged();

                    }

                } else {
                    Toast.makeText(context, travelBean.getInfo(), Toast.LENGTH_SHORT).show();
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

    /**
     * 搜索众筹列表数据
     */
    public void CustomerData() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", "");
                params.addQueryStringParameter("keywords", keyword);
                params.addQueryStringParameter("p", p + "");
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.CUSTOMER + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        CustomerBean customerBean = customerData(result);
                        if (customerBean.getStatus() == 1) {
                            ArrayList<CustomerBean.DataEntity.ListEntity> list = (ArrayList<CustomerBean.DataEntity.ListEntity>) customerBean.getData().getList();
                            if (list != null && list.size() != 0) {
                                customerlist.addAll(list);
                                customerAdapter.notifyDataSetChanged();
                            } else {
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

    /**
     * 搜索景点列表数据
     */
    public void scenic() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                String latitude = GlobalParams.latitude;
                String longitude = GlobalParams.longitude;
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("latitude", latitude);
                params.addQueryStringParameter("longitude", longitude);
                params.addQueryStringParameter("area", "");
                params.addQueryStringParameter("keywords", keyword);
                params.addQueryStringParameter("p", String.valueOf(p));
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.SCENIC + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        ScenicBean scenicBean = scenicData(result);
                        System.out.println("result====" + result);
                        if (scenicBean.getStatus() == 1) {
                            ArrayList<ScenicBean.DataEntity.DatasEntity> list = (ArrayList<ScenicBean.DataEntity.DatasEntity>) scenicBean.getData().getDatas();
                            if (list != null && list.size() != 0) {
                                sceniclist.addAll(list);
                                scenicAdapter.notifyDataSetChanged();
                            } else {
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

    public CustomerBean customerData(String result) {
        Gson gson = new Gson();
        CustomerBean customerBean = gson.fromJson(result, CustomerBean.class);
        return customerBean;
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

    public void init() {
        listView = (ListView) findViewById(R.id.activity_search_listview);
        t1 = (TextView) findViewById(R.id.activity_search_t1);
        t2 = (TextView) findViewById(R.id.activity_search_t2);
        t3 = (TextView) findViewById(R.id.activity_search_t3);
        t4 = (TextView) findViewById(R.id.activity_search_t4);
        t5 = (TextView) findViewById(R.id.activity_search_t5);
        title = (TextView) findViewById(R.id.head2_title);
        bt = (Button) findViewById(R.id.activity_search_bt);
        editText = (EditText) findViewById(R.id.activity_search_edit);
        back = (ImageView) findViewById(R.id.head2_back);
        layout = (LinearLayout) findViewById(R.id.head2_layout);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyword = editText.getText().toString().trim();
                search();
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_view_rounded_bottom));
                t2.setBackgroundColor(getResources().getColor(R.color.transparent));
                t3.setBackgroundColor(getResources().getColor(R.color.transparent));
                t4.setBackgroundColor(getResources().getColor(R.color.transparent));
                t5.setBackgroundColor(getResources().getColor(R.color.transparent));
                t1.setTextColor(Color.WHITE);
                t2.setTextColor(Color.BLACK);
                t3.setTextColor(Color.BLACK);
                t4.setTextColor(Color.BLACK);
                t5.setTextColor(Color.BLACK);
                ismy = "track";
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setBackgroundColor(getResources().getColor(R.color.transparent));
                t2.setBackgroundColor(getResources().getColor(R.color.beeway_title_bule));
                t3.setBackgroundColor(getResources().getColor(R.color.transparent));
                t4.setBackgroundColor(getResources().getColor(R.color.transparent));
                t5.setBackgroundColor(getResources().getColor(R.color.transparent));
                t1.setTextColor(Color.BLACK);
                t2.setTextColor(Color.WHITE);
                t3.setTextColor(Color.BLACK);
                t4.setTextColor(Color.BLACK);
                t5.setTextColor(Color.BLACK);
                ismy = "travel";

            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setBackgroundColor(getResources().getColor(R.color.transparent));
                t2.setBackgroundColor(getResources().getColor(R.color.transparent));
                t3.setBackgroundColor(getResources().getColor(R.color.beeway_title_bule));
                t4.setBackgroundColor(getResources().getColor(R.color.transparent));
                t5.setBackgroundColor(getResources().getColor(R.color.transparent));
                t1.setTextColor(Color.BLACK);
                t2.setTextColor(Color.BLACK);
                t3.setTextColor(Color.WHITE);
                t4.setTextColor(Color.BLACK);
                t5.setTextColor(Color.BLACK);
                ismy = "scenic";
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setBackgroundColor(getResources().getColor(R.color.transparent));
                t2.setBackgroundColor(getResources().getColor(R.color.transparent));
                t3.setBackgroundColor(getResources().getColor(R.color.transparent));
                t4.setBackgroundColor(getResources().getColor(R.color.beeway_title_bule));
                t5.setBackgroundColor(getResources().getColor(R.color.transparent));
                t1.setTextColor(Color.BLACK);
                t2.setTextColor(Color.BLACK);
                t3.setTextColor(Color.BLACK);
                t4.setTextColor(Color.WHITE);
                t5.setTextColor(Color.BLACK);
                ismy = "crowdfunding";
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ismy = "friend";
                Intent intent = new Intent(SearchActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });
        title.setText("搜索");
        layout.setVisibility(View.GONE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
