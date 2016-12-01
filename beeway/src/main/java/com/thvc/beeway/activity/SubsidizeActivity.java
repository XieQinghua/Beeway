package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.thvc.beeway.bean.IsGoodDataBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.MyRequestParams;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/9/18.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class SubsidizeActivity extends BaseActivity {
    private Context context;
    private String company = "";
    private ImageView head2_back;
    private TextView subsidize_exchange, subsidize_clear, head2_address;
    private Button subsidize_bt10, subsidize_bt20, subsidize_bt50;
    private EditText subsidize_et, subsidize_reason;
    private String content = "", cprice = "", noteid = "", userid = "";
    private String[] reasons = {"算借你的，下次还我~", "妞~你不笑，爷赏你一个笑", "玩得开心，小心井盖~", "帮我带点日本酱油~", "待你归来，不要把我忘了",
            "中国最佳好友 非我莫属 给我点赞", "快点回来！家里的娃还需要你照顾呢", "不求回报，但求回抱", "荷包瘪了，请让它再次鼓起来，不客气",
            "我的大恩大德你此生都难以回报吧，带上我一起去吧", "给你两分的快乐，三分的平安，五分祝福，十分的甜蜜", "给你的每一分钱都是我最诚心的祈祷。",
            "其实零钱什么的 你给我 我不会介意的", "你的渐行渐远，使我们越走越近。"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_subsidize);
        context = this;
        Intent intent = getIntent();
        noteid = intent.getStringExtra("noteid");
        init();

    }

    public void init() {
        head2_back = (ImageView) findViewById(R.id.head2_back);
        head2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        subsidize_exchange = (TextView) findViewById(R.id.subsidize_exchange);
        head2_address = (TextView) findViewById(R.id.head2_address);
        subsidize_clear = (TextView) findViewById(R.id.subsidize_clear);
        subsidize_reason = (EditText) findViewById(R.id.subsidize_reason);
        subsidize_bt10 = (Button) findViewById(R.id.subsidize_bt10);
        subsidize_bt20 = (Button) findViewById(R.id.subsidize_bt20);
        subsidize_bt50 = (Button) findViewById(R.id.subsidize_bt50);
        subsidize_et = (EditText) findViewById(R.id.subsidize_et);
        head2_address.setText("资助");
        head2_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Support();
            }
        });
        subsidize_exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = (int) (0 + Math.random() * 14);
                subsidize_reason.setText(reasons[num]);
                content = reasons[num];
            }
        });
        subsidize_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subsidize_reason.setText("");
                content = "";
            }
        });
        subsidize_et.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    subsidize_bt10.setBackgroundResource(R.drawable.background_view_rounded_while);
                    subsidize_bt20.setBackgroundResource(R.drawable.background_view_rounded_while);
                    subsidize_bt50.setBackgroundResource(R.drawable.background_view_rounded_while);
                    subsidize_et.setBackgroundResource(R.drawable.bt_blue_shape);

                } else {
                    // 此处为失去焦点时的处理内容
                    subsidize_bt10.setBackgroundResource(R.drawable.background_view_rounded_while);
                    subsidize_bt20.setBackgroundResource(R.drawable.background_view_rounded_while);
                    subsidize_bt50.setBackgroundResource(R.drawable.background_view_rounded_while);
                    subsidize_et.setBackgroundResource(R.drawable.background_view_rounded_while);

                }
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.subsidize_bt10:
                cprice = "10";
                subsidize_bt10.setBackgroundResource(R.drawable.bt_blue_shape);
                subsidize_bt20.setBackgroundResource(R.drawable.background_view_rounded_while);
                subsidize_bt50.setBackgroundResource(R.drawable.background_view_rounded_while);
                subsidize_et.setBackgroundResource(R.drawable.background_view_rounded_while);
                break;
            case R.id.subsidize_bt20:
                cprice = "20";
                subsidize_bt10.setBackgroundResource(R.drawable.background_view_rounded_while);
                subsidize_bt20.setBackgroundResource(R.drawable.bt_blue_shape);
                subsidize_bt50.setBackgroundResource(R.drawable.background_view_rounded_while);
                subsidize_et.setBackgroundResource(R.drawable.background_view_rounded_while);
                break;
            case R.id.subsidize_bt50:
                cprice = "30";
                subsidize_bt10.setBackgroundResource(R.drawable.background_view_rounded_while);
                subsidize_bt20.setBackgroundResource(R.drawable.background_view_rounded_while);
                subsidize_bt50.setBackgroundResource(R.drawable.bt_blue_shape);
                subsidize_et.setBackgroundResource(R.drawable.background_view_rounded_while);
                break;
        }
    }

    public void Support() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                company = subsidize_et.getText().toString().trim();
                if (company != null && company.length() != 0) {
                    cprice = company;
                }
                userid = new BeewayApplication().getmUserid(context);
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("content", content);
                params.addQueryStringParameter("cprice", cprice);
                params.addQueryStringParameter("noteid", noteid);
                params.addQueryStringParameter("userid", userid);
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.SUPPORT + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        IsGoodDataBean isGoodDataBean = isGoodDataBean(result);
                        if (isGoodDataBean.getStatus() == 1) {
                            Toast.makeText(SubsidizeActivity.this, "资助成功！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SubsidizeActivity.this, isGoodDataBean.getInfo(), Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
            }
        }.start();
    }

    /**
     * 资助网络获取数据解析
     */
    public IsGoodDataBean isGoodDataBean(String result) {
        Gson gson = new Gson();
        IsGoodDataBean isGoodDataBean = gson.fromJson(result, IsGoodDataBean.class);
        return isGoodDataBean;
    }
}
