package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.BankCardBean;
import com.thvc.beeway.bean.IsCollectDataBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.view.ListItemDelete;
import com.thvc.beeway.view.SildeDelListvew;
import com.thvc.beeway.view.view.LoopListener;
import com.thvc.beeway.view.view.LoopView;

import java.util.ArrayList;
import java.util.List;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/9/24.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class BankCardActivity extends BaseActivity implements View.OnClickListener {
    private SildeDelListvew bankCard_listview;
    private RelativeLayout bankCard_card, myWallet_layout, rootview, yesOrNo;
    private LinearLayout bankCard, add_bankCard, bankCard_bankname;
    private TextView head2_title, head2_address, yes, no, bankCard_nickname;
    private ImageView head2_back;
    private String userid = "", TYPE = "", owners = "", Owners = "", cardNumber = "", Cardnumber = "", remark = "", bankname = "", BankName = "", id = "", Id = "";
    private Context context = this;
    private BankCardAdapter bankCardAdapter;
    private EditText bankCard_owners, bankCard_cardNumber, bankCard_remark;
    private RelativeLayout.LayoutParams layoutParams;
    private ArrayList<BankCardBean.DataEntity> lists = new ArrayList<>();
    public int num;
    public Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywallet);
        intent = getIntent();
        TYPE = intent.getStringExtra("TYPE");
        init();

    }

    public void init() {
        bankCard_owners = (EditText) findViewById(R.id.bankCard_owners);
        bankCard_cardNumber = (EditText) findViewById(R.id.bankCard_cardNumber);
        bankCard_remark = (EditText) findViewById(R.id.bankCard_remark);

        yesOrNo = (RelativeLayout) findViewById(R.id.yesOrNo);
        rootview = (RelativeLayout) findViewById(R.id.rootview);
        bankCard_card = (RelativeLayout) findViewById(R.id.bankCard_card);
        myWallet_layout = (RelativeLayout) findViewById(R.id.myWallet_layout);
        bankCard_bankname = (LinearLayout) findViewById(R.id.bankCard_bankname);
        bankCard = (LinearLayout) findViewById(R.id.bankCard);
        add_bankCard = (LinearLayout) findViewById(R.id.add_bankCard);
        bankCard_listview = (SildeDelListvew) findViewById(R.id.bankCard_listview);
        head2_title = (TextView) findViewById(R.id.head2_title);
        bankCard_nickname = (TextView) findViewById(R.id.bankCard_nickname);
        head2_address = (TextView) findViewById(R.id.head2_address);
        yes = (TextView) findViewById(R.id.yes);
        no = (TextView) findViewById(R.id.no);
        head2_back = (ImageView) findViewById(R.id.head2_back);
        if (TYPE != null && TYPE.equals("ADD")) {
            head2_title.setText("添加银行卡");
            head2_address.setText("完成");
            add_bankCard.setVisibility(View.VISIBLE);
            bankCard.setVisibility(View.GONE);
            myWallet_layout.setVisibility(View.GONE);
            initview();
        } else if (TYPE != null && TYPE.equals("Modi")) {
            Cardnumber = intent.getStringExtra("Cardnumber");
            BankName = intent.getStringExtra("Bankname");
            Owners = intent.getStringExtra("Owners");
            Id = intent.getStringExtra("Id");
            userid = intent.getStringExtra("userid");

            head2_title.setText("银行卡编辑");
            head2_address.setText("完成");
            add_bankCard.setVisibility(View.VISIBLE);
            bankCard.setVisibility(View.GONE);
            bankname = BankName;
            myWallet_layout.setVisibility(View.GONE);
            bankCard_nickname.setText(BankName);
            bankCard_owners.setText(Owners);
            bankCard_cardNumber.setText(Cardnumber);
            initview();
        } else {
            head2_title.setText("我的银行卡");
            head2_address.setText("添加");
            bankCard.setVisibility(View.VISIBLE);
            myWallet_layout.setVisibility(View.GONE);
            bankCardData();
        }
        head2_address.setOnClickListener(this);
        bankCard_bankname.setOnClickListener(this);
        head2_back.setOnClickListener(this);
        bankCard_card.setOnClickListener(this);
        bankCardAdapter = new BankCardAdapter(lists, context);
        bankCard_listview.setAdapter(bankCardAdapter);

        bankCard_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("lists.get(i).getBankname()" + lists.get(i).getBankname());
                Intent bankModi = new Intent(context, BankCardActivity.class);
                bankModi.putExtra("TYPE", "Modi");
                bankModi.putExtra("Cardnumber", lists.get(i).getCardnumber());
                bankModi.putExtra("Bankname", lists.get(i).getBankname());
                bankModi.putExtra("Owners", lists.get(i).getOwners());
                bankModi.putExtra("Id", lists.get(i).getId());
                bankModi.putExtra("userid", lists.get(i).getUserid());

                startActivity(bankModi);
            }
        });

    }

    public void bankCardData() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                userid = new BeewayApplication().getmUserid(context);
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", userid);
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.BANKCARD + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        BankCardBean bankCardBean = bankCardBean(result);
                        if (bankCardBean.getStatus() == 1) {
                            ArrayList<BankCardBean.DataEntity> list = (ArrayList<BankCardBean.DataEntity>) bankCardBean.getData();
                            if (list != null && list.size() != 0) {
                                lists.addAll(list);
                                bankCardAdapter.notifyDataSetChanged();
                            }
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

    public BankCardBean bankCardBean(String result) {
        Gson gson = new Gson();
        BankCardBean bankCardBean = gson.fromJson(result, BankCardBean.class);
        return bankCardBean;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head2_back:
                finish();
                break;
            case R.id.bankCard_card:
                Intent intent = new Intent(context, BankCardActivity.class);
                intent.putExtra("TYPE", "ADD");
                startActivity(intent);
                break;
            case R.id.bankCard_bankname:
                yesOrNo.setVisibility(View.VISIBLE);
                yes.setOnClickListener(this);
                no.setOnClickListener(this);

                break;
            case R.id.head2_address:
                if (TYPE != null && TYPE.equals("ADD")) {
                    owners = bankCard_owners.getText().toString().trim();
                    cardNumber = bankCard_cardNumber.getText().toString().trim();
                    remark = bankCard_remark.getText().toString().trim();
                    if (bankname != null && bankname.length() != 0) {
                        if (owners != null && owners.length() != 0) {
                            if (cardNumber != null && cardNumber.length() != 0) {
                                bankAdd();
                            } else {
                                Toast.makeText(context, "请输入卡号！", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "请输入户主！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "请输入开户行名称！", Toast.LENGTH_SHORT).show();
                    }
                } else if (TYPE != null && TYPE.equals("Modi")) {
                    owners = bankCard_owners.getText().toString().trim();
                    cardNumber = bankCard_cardNumber.getText().toString().trim();
                    remark = bankCard_remark.getText().toString().trim();
                    if (bankname != null && bankname.length() != 0) {
                        if (owners != null && owners.length() != 0) {
                            if (cardNumber != null && cardNumber.length() != 0) {
                                bankModi();
                            } else {
                                Toast.makeText(context, "请输入卡号！", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "请输入户主！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "请输入开户行名称！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Intent bank = new Intent(context, BankCardActivity.class);
                    bank.putExtra("TYPE", "ADD");
                    startActivity(bank);
                }
                break;
            case R.id.yes:
                yesOrNo.setVisibility(View.GONE);
                bankCard_nickname.setText(bankname);
                break;
            case R.id.no:
                yesOrNo.setVisibility(View.GONE);
                bankname = "";
                break;

        }
    }

    public void initview() {
        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        rootview = (RelativeLayout) findViewById(R.id.rootview);
        final String bankcard[] = {"中国银行", "工商银行", "农业银行", "招商银行", "中信银行", "建设银行", "中国人民银行", "邮政银行", "浦发银行", "支付宝"};
//        final String bankcard[] = {"中国银行","工商银行","农业银行"};
        LoopView loopView = new LoopView(this);
        ArrayList<String> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(bankcard[i]);
        }
        //设置是否循环播放
        loopView.setNotLoop();
        //滚动监听
        loopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                Log.d("debug", "Item " + item);
                bankname = bankcard[item];
            }
        });
        //设置原始数据
        loopView.setArrayList(list);
        //设置初始位置
        loopView.setPosition(5);
        //设置字体大小
        loopView.setTextSize(30);
        rootview.addView(loopView, layoutParams);
    }

    public void bankAdd() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                userid = new BeewayApplication().getmUserid(context);
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", userid);
                params.addQueryStringParameter("bankname", bankname);
                params.addQueryStringParameter("owners", owners);
                params.addQueryStringParameter("cardnumber", cardNumber);
                params.addQueryStringParameter("remark", remark);
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.BANKADD + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        System.out.println("result result" + result);
                        IsCollectDataBean bankAdd = bankAdd(result);
                        if (bankAdd.getStatus() == 1) {
                            Toast.makeText(context, "添加成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, BankCardActivity.class);
                            intent.putExtra("TYPE", "");
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

    public void bankModi() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("id", Id);
                params.addQueryStringParameter("userid", userid);
                params.addQueryStringParameter("bankname", BankName);
                params.addQueryStringParameter("owners", Owners);
                params.addQueryStringParameter("cardnumber", Cardnumber);
                params.addQueryStringParameter("remark", remark);
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.BANKMODI + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        System.out.println("bankModi result" + result);
                        IsCollectDataBean bankAdd = bankAdd(result);
                        if (bankAdd.getStatus() == 1) {
                            Toast.makeText(context, "修改成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, BankCardActivity.class);
                            intent.putExtra("TYPE", "");
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

    public class BankCardAdapter extends BaseAdapter {
        private List<BankCardBean.DataEntity> list;
        private Context context;

        public BankCardAdapter(List<BankCardBean.DataEntity> list, Context
                context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.mywallet_listview_item, null);
                vh.bankCard_item_owners = (TextView) convertView.findViewById(R.id.bankCard_item_owners);
                vh.bankCard_item_bankname = (TextView) convertView.findViewById(R.id.bankCard_item_bankname);
                vh.bankCard_item_cardnumber = (TextView) convertView.findViewById(R.id.bankCard_item_cardnumber);
                vh.bankCard_item_layout = (ListItemDelete) convertView.findViewById(R.id.bankCard_item_layout);
                vh.myWallet_item_layout = (RelativeLayout) convertView.findViewById(R.id.myWallet_item_layout);
                vh.btndel = (Button) convertView.findViewById(R.id.btndel);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.bankCard_item_layout.setVisibility(View.VISIBLE);
            vh.myWallet_item_layout.setVisibility(View.GONE);
            vh.bankCard_item_owners.setText(list.get(position).getOwners());
            vh.bankCard_item_bankname.setText(list.get(position).getBankname());
            vh.bankCard_item_cardnumber.setText(list.get(position).getCardnumber());
            vh.btndel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    id = list.get(position).getId();
                    num = position;
                    bankDel();
                    vh.bankCard_item_layout.setback();
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView bankCard_item_owners, bankCard_item_bankname, bankCard_item_cardnumber;
            RelativeLayout myWallet_item_layout;
            ListItemDelete bankCard_item_layout;
            Button btndel;
        }
    }

    public void bankDel() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                String userid = new BeewayApplication().getmUserid(context);
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", userid);
                params.addQueryStringParameter("id", id);
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.BANKDEL + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        System.out.println("  bankDel result" + result);
                        IsCollectDataBean bankAdd = bankDel(result);
                        if (bankAdd.getStatus() == 1) {
                            lists.remove(num);
                            bankCardAdapter.notifyDataSetChanged();
                            Toast.makeText(context, "删除成功！", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
            }
        }.start();
    }

    public IsCollectDataBean bankDel(String result) {
        Gson gson = new Gson();
        IsCollectDataBean bankAdd = gson.fromJson(result, IsCollectDataBean.class);
        return bankAdd;
    }
}
