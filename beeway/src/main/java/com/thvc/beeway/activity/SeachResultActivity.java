package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
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
import com.thvc.beeway.adapter.AddFriendAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.AddFriendBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;

import java.util.ArrayList;

/**
 * 项目名称：Beeway
 * 类描述：蜂友搜索结果页面
 * 创建人：谢庆华.
 * 创建时间：2015/9/11 15:38
 * 修改人：Administrator
 * 修改时间：2015/9/11 15:38
 * 修改备注：
 */
public class SeachResultActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<ListView> {
    private static final String TAG = "SeachResultActivity";
    private Context context = this;
    private PullToRefreshListView lv_seach_result;
    private ImageView nullpage;
    private TextView nulltext;
    private ArrayList<AddFriendBean.DataEntity.ListEntity> lists = new ArrayList<>();
    private AddFriendAdapter myAddFriendAdapter;
    private String userid, nickname,str;
    private int totalPage, p = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach_fengyou_result);
        init();
        showDialog(LOADING_DIALOG);
        lodeData();
    }
    public void init(){
        lv_seach_result = (PullToRefreshListView) findViewById(R.id.lv_seach_result);
        nullpage = (ImageView) findViewById(R.id.iv_nullpage);
        nulltext = (TextView) findViewById(R.id.tv_nulltext);
        userid = new BeewayApplication().getmUserid(this);
        nickname = getIntent().getStringExtra("msg");
        Log.i(TAG, nickname);
        str = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_NO_NOON);
        myAddFriendAdapter = new AddFriendAdapter(lists, SeachResultActivity.this);
        lv_seach_result.setAdapter(myAddFriendAdapter);
        lv_seach_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**进入详情界面**/
                Intent intent = new Intent(SeachResultActivity.this, UserDetailsActivity.class);
                intent.putExtra("friendid", lists.get(position-1).getSolevar());
                startActivity(intent);
            }
        });
    }
    /**
     * 向服务器请求蜂友数据
     */
    private void lodeData() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", userid);
                params.addQueryStringParameter("nickname", nickname);
                params.addQueryStringParameter("p", p + "");
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.FIND_FRIEND + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        AddFriendBean addFriendBean = addFriendDate(result);
                        if (addFriendBean.getStatus() == 1) {
                            totalPage = addFriendBean.getData().getTotalPage();
                            ArrayList<AddFriendBean.DataEntity.ListEntity> listEntity = (ArrayList<AddFriendBean.DataEntity.ListEntity>) addFriendBean.getData().getList();
                            if (listEntity != null && listEntity.size() != 0) {
                                lists.addAll(listEntity);
                                myAddFriendAdapter.notifyDataSetChanged();
                                lv_seach_result.onRefreshComplete();
                                lv_seach_result.setVisibility(View.VISIBLE);
                                nullpage.setVisibility(View.GONE);//显示空图片
                                nulltext.setVisibility(View.GONE);
                            } else {
                                nullpage.setVisibility(View.VISIBLE);//显示空图片
                                nulltext.setVisibility(View.VISIBLE);
                            }
                            removeDialog();
                        } else {
                            Log.e(TAG, addFriendBean.getInfo());
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(SeachResultActivity.this, "网络获取失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();

    }


    public AddFriendBean addFriendDate(String result) {
        Gson gson = new Gson();
        AddFriendBean addFriendBean = gson.fromJson(result, AddFriendBean.class);
        return addFriendBean;
    }

    /**
     * 下拉刷新
     *
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        lv_seach_result.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        lv_seach_result.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        lv_seach_result.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
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
        if (p < totalPage - 1) {
            p++;
            lv_seach_result.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
            lv_seach_result.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
            lv_seach_result.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
                    "最后加载时间:" + str);
            lodeData();
        } else {
            p = totalPage - 1;
            lv_seach_result.onRefreshComplete();
            Toast.makeText(context, "数据已加载完毕", Toast.LENGTH_SHORT).show();
        }
    }
}
