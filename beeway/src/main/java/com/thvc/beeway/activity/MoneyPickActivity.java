package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.thvc.beeway.R;
import com.thvc.beeway.adapter.BankNameAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.BankCardBean;
import com.thvc.beeway.bean.IsCollectDataBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;

import java.util.ArrayList;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/9/25.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class MoneyPickActivity extends BaseActivity implements View.OnClickListener {
    private TextView head2_title, head2_address, moneypick_money;
    private ImageView head2_back;
    private Button moneypick_OK;
    private EditText moneypick_edit;
    private ListView moneypick_listview;
    private RelativeLayout rootview, yesOrNo;
    private LinearLayout moneypick_bankname;
    private TextView moneypick_title;
    private Context context = this;
    private boolean bl = true;
    private String bankname = "", cardNumber = "", owners = "", pickfee = "", Usefee = "", BankName = "";
    private ArrayList<String> lists = new ArrayList<>();
    private ArrayList<BankCardBean.DataEntity> list = new ArrayList<>();
    private BankNameAdapter bankNameAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneypick);
        init();

    }

    public void init() {

        moneypick_listview = (ListView) findViewById(R.id.moneypick_listview);
        moneypick_bankname = (LinearLayout) findViewById(R.id.moneypick_bankname);
        head2_title = (TextView) findViewById(R.id.head2_title);
        head2_address = (TextView) findViewById(R.id.head2_address);
        moneypick_title = (TextView) findViewById(R.id.moneypick_title);
        moneypick_money = (TextView) findViewById(R.id.moneypick_money);
        moneypick_edit = (EditText) findViewById(R.id.moneypick_edit);
        moneypick_OK = (Button) findViewById(R.id.moneypick_OK);
        head2_back = (ImageView) findViewById(R.id.head2_back);
        head2_back.setOnClickListener(this);
        head2_title.setText("提现");
        head2_address.setText("提现记录");
        head2_address.setOnClickListener(this);
        moneypick_OK.setOnClickListener(this);
        moneypick_bankname.setOnClickListener(this);
        Intent intent = getIntent();
        Usefee = intent.getStringExtra("Usefee");
        moneypick_money.setText(Usefee);
        bankNameAdapter = new BankNameAdapter(list, context);
        moneypick_listview.setAdapter(bankNameAdapter);
        bankCardData();
        moneypick_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                bankname = lists.get(i);
                BankName = list.get(i).getBankname();
                cardNumber = list.get(i).getCardnumber();
                owners = list.get(i).getOwners();
                moneypick_title.setText(bankname);
                moneypick_listview.setVisibility(View.GONE);
                bl = true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head2_back:
                finish();
                break;
            case R.id.head2_address:
                Intent intent = new Intent(context, MoreOtherActivity.class);
                intent.putExtra("record", "RECORD");
                startActivity(intent);
                break;
            case R.id.moneypick_OK:
                pickfee = moneypick_edit.getText().toString().trim();
                if (bankname != null && bankname.length() != 0) {
                    if (pickfee != null && pickfee.length() != 0) {
                        moneyPick();
                    } else {
                        Toast.makeText(context, "请输入金额！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "请选择账户！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.moneypick_bankname:
                if (bl) {
                    System.out.println(" --lists.size()" + list.size());
                    if (list.size() == 0 && list == null) {
                        Toast.makeText(context, "您暂时还未添加银行卡！", Toast.LENGTH_SHORT).show();
                    }
                    moneypick_listview.setVisibility(View.VISIBLE);
                    bankNameAdapter.notifyDataSetChanged();
                    bl = false;
                } else {
                    moneypick_listview.setVisibility(View.GONE);
                    bl = true;
                }
                break;

        }
    }

    /**
     * 申请提现
     */
    public void moneyPick() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                String userid = new BeewayApplication().getmUserid(context);
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", userid);
                params.addQueryStringParameter("pickfee", pickfee);
                params.addQueryStringParameter("bankname", BankName);
                params.addQueryStringParameter("owners", owners);
                params.addQueryStringParameter("cardnumber", cardNumber);
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.MONEYPICK + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        Log.e("  申请提现  result", result);
                        IsCollectDataBean bankAdd = bankAdd(result);
                        if (bankAdd.getStatus() == 1) {
                            Toast.makeText(context, "添加成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, MyWalletActivity.class);
                            startActivity(intent);
                        }
                        removeDialog();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
            }
        }.start();
    }

    public IsCollectDataBean bankAdd(String result) {
        Gson gson = new Gson();
        IsCollectDataBean bankAdd = gson.fromJson(result, IsCollectDataBean.class);
        return bankAdd;
    }

    /**
     * 获取银行卡列表
     */
    public void bankCardData() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                String userid = new BeewayApplication().getmUserid(context);
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", userid);
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.BANKCARD + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        System.out.println(" -----bankCardData" + result);
                        BankCardBean bankCardBean = bankCardBean(result);
                        if (bankCardBean.getStatus() == 1) {
                            ArrayList<BankCardBean.DataEntity> listEntity = (ArrayList<BankCardBean.DataEntity>) bankCardBean.getData();
                            if (listEntity != null && listEntity.size() != 0) {
                                list.addAll(listEntity);
                                for (int i = 0; i < listEntity.size(); i++) {
                                    lists.add(list.get(i).getBankname() + "(" + list.get(i).getCardnumber().substring(list.get(i).getCardnumber().length() - 5, list.get(i).getCardnumber().length() - 1) + ")");
                                    bankNameAdapter.notifyDataSetChanged();
                                    setListViewHeight(moneypick_listview);
                                }

                            }
                        }
                        removeDialog();
//                        initview();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
            }
        }.start();
    }

    public BankCardBean bankCardBean(String result) {
        Gson gson = new Gson();
        BankCardBean bankCardBean = gson.fromJson(result, BankCardBean.class);
        return bankCardBean;
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
        params.height += 40;
        listView.setLayoutParams(params);
    }
}
