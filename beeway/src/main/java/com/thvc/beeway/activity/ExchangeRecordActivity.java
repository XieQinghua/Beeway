package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
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
import com.thvc.beeway.adapter.ExchangeAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.ExChangeRecordBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.utils.MyStatic;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/29.
 * 兑换记录
 */
public class ExchangeRecordActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<ListView> {

    private ImageView iv_excha_back,image;

    private HttpUtils httpUtils;
    private int p = 0, pageInde = 0;
    private String userid;

    private Context context = this;

    private PullToRefreshListView activity_exch_listview;

    private ArrayList<ExChangeRecordBean.DataEntity.ListEntity> lists = new ArrayList<>();
    private ExchangeAdapter exchangeAdapter;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_record);
        userid = new BeewayApplication().getmUserid(this);
        inint();
        str = DateUtils.formatDateTime(ExchangeRecordActivity.this, System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_NO_NOON);
        getExchangeRecord();
    }

    public void inint() {
        activity_exch_listview = (PullToRefreshListView) this.findViewById(R.id.activity_exch_listview);
        activity_exch_listview.setMode(PullToRefreshBase.Mode.BOTH);
        activity_exch_listview.setOnRefreshListener(this);
        activity_exch_listview.setVisibility(View.VISIBLE);


        iv_excha_back = (ImageView) this.findViewById(R.id.iv_excha_back);//返回按钮
        image = (ImageView) this.findViewById(R.id.image);//返回按钮
        iv_excha_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExchangeRecordActivity.this.finish();
            }
        });
        exchangeAdapter = new ExchangeAdapter(lists, context);
        activity_exch_listview.setAdapter(exchangeAdapter);

        activity_exch_listview.setOnItemClickListener(new MyOnItemClickListener());

    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ExchangeRecordActivity.this, HistoricalDetailsActivity.class);
            intent.putExtra("pid", MyStatic.pid);
            intent.putExtra("logid", MyStatic.logid);
            startActivity(intent);
        }
    }


    public void getExchangeRecord() {
        httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("userid", userid);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.EXCHANRECORD + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //获得访问解析出来的json字符串
                String result = responseInfo.result;
                activity_exch_listview.setVisibility(View.VISIBLE);
                ExChangeRecordBean exChangeRecordBean = paraseData(result);
                if (exChangeRecordBean.getStatus() == 1) {
                    ArrayList<ExChangeRecordBean.DataEntity.ListEntity> listEntities = (ArrayList<ExChangeRecordBean.DataEntity.ListEntity>) exChangeRecordBean.getData().getList();
                    if (listEntities != null && listEntities.size() > 0) {
                        lists.addAll(listEntities);
                        exchangeAdapter.notifyDataSetChanged();
                        activity_exch_listview.onRefreshComplete();
                        activity_exch_listview.setVisibility(View.VISIBLE);
                        image.setVisibility(View.GONE);
                    } else {
                        if (p == 0) {
                            activity_exch_listview.setVisibility(View.GONE);
                            image.setVisibility(View.VISIBLE);
                        }
                        Toast.makeText(context, "该选项无数据！", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ExchangeRecordActivity.this, exChangeRecordBean.getInfo(), Toast.LENGTH_SHORT).show();
                }
                removeDialog(LOADING_DIALOG);
            }

            @Override
            public void onFailure(HttpException e, String s) {
            }
        });
    }


    public ExChangeRecordBean paraseData(String result) {
        Gson gson = new Gson();
        ExChangeRecordBean exChangeRecordBean = gson.fromJson(result, ExChangeRecordBean.class);
        return exChangeRecordBean;
    }


    /**
     * 下拉刷新
     *
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        activity_exch_listview.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        activity_exch_listview.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        activity_exch_listview.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("最后更新时间:" + str);
        p = 0;
        lists.clear();
        getExchangeRecord();

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
            activity_exch_listview.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
            activity_exch_listview.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
            activity_exch_listview.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
                    "最后加载时间:" + str);
            getExchangeRecord();

        } else {
            p = pageInde - 1;
            activity_exch_listview.onRefreshComplete();
            Toast.makeText(ExchangeRecordActivity.this, "数据已加载完毕", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * java语言md5加密
     *
     * @param info
     * @return
     */
    public String getMD5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
