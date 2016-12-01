package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.thvc.beeway.adapter.IntegralAdapter;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.IntegralBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


/**
 * Created by Administrator on 2015/9/28.
 * 峰蜜
 */
public class IntegralActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<ListView> {

    private   ImageView iv_integeral_back;
    private LinearLayout tv_title_exchange_record;
    private PullToRefreshListView activity_integeral_listview;
    private HttpUtils httpUtils;
    private int p = 0,pageInde = 0;
    private Context context=this;
    private String str;
    private ArrayList<IntegralBean.DataEntity.ListEntity> lists = new ArrayList<>();
    private IntegralAdapter interaladApter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        init();
        GetIntegralList();
        str = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_NO_NOON);

    }

    /**
     * 初始化控件
     */
    public void init(){
         iv_integeral_back = (ImageView) this.findViewById(R.id.iv_integeral_back);
         iv_integeral_back.setOnClickListener(new MyOnClickListener());//返回
         tv_title_exchange_record = (LinearLayout)this.findViewById(R.id.tv_title_exchange_record);
         tv_title_exchange_record.setOnClickListener(new MyOnClickListener());//兑换记录

        activity_integeral_listview =(PullToRefreshListView)findViewById(R.id.activity_integeral_listview);//积分列表
        activity_integeral_listview.setMode(PullToRefreshBase.Mode.BOTH);
        activity_integeral_listview.setOnRefreshListener(this);
        activity_integeral_listview.setVisibility(View.VISIBLE);

        interaladApter = new IntegralAdapter(lists,context);
        activity_integeral_listview.setAdapter(interaladApter);


    }


    /**
     * 点击事件
     */
    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_integeral_back://点击返回按钮
                    IntegralActivity.this.finish();
                    break;
                case R.id.tv_title_exchange_record://兑换记录按钮
                    Intent intent = new Intent(IntegralActivity.this,ExchangeRecordActivity.class);
                    startActivity(intent);
                    break;
            }

        }
    }
    public void GetIntegralList(){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                     httpUtils = new HttpUtils();
                    MyRequestParams params = new MyRequestParams();
                    params.addQueryStringParameter("p", p + "");
                    String  url= params.myRequestParams(params);
                    httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.INTEGRAlMALL+"?"+url, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            //获得访问解析出来的json字符串
                            String result = responseInfo.result;
                            activity_integeral_listview.setVisibility(View.VISIBLE);
                            IntegralBean integralBean = paraseData(result);
                            if (integralBean.getStatus() == 1) {
                                ArrayList<IntegralBean.DataEntity.ListEntity> listEntities = (ArrayList<IntegralBean.DataEntity.ListEntity>) integralBean.getData().getList();
                                if (listEntities != null && listEntities.size() > 0) {
                                    lists.addAll(listEntities);
                                    interaladApter.notifyDataSetChanged();
                                    activity_integeral_listview.onRefreshComplete();
                                }else {
                                    if (p==0) {
                                        activity_integeral_listview.setVisibility(View.GONE);
                                    }
                                    Toast.makeText(context, "该选项无数据！", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(context, integralBean.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                            removeDialog(LOADING_DIALOG);
                            }

                        @Override
                        public void onFailure(HttpException e, String s) {
                        }
                    });
                }
            }.start();
        }


    public IntegralBean paraseData(String result) {
        Gson gson = new Gson();
        IntegralBean integralBean = gson.fromJson(result, IntegralBean.class);
        return integralBean;
    }



    /**
     * 下拉刷新
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        activity_integeral_listview.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        activity_integeral_listview.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        activity_integeral_listview.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("最后更新时间:" + str);
        p=0;
        lists.clear();
        GetIntegralList();

    }
    /**
     * 上拉加载
     * @param refreshView
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        if (p < pageInde-1) {
            p++;
            activity_integeral_listview.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
            activity_integeral_listview.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
            activity_integeral_listview.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
                    "最后加载时间:" + str);
            GetIntegralList();

        } else {
            p = pageInde -1;
//            GetIntegralList();
            activity_integeral_listview.onRefreshComplete();
            Toast.makeText(context, "数据已加载完毕", Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * java语言md5加密
     * @param info
     * @return
     */
    public String getMD5(String info)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++)
            {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1)
                {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                }
                else
                {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            return "";
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }


}
