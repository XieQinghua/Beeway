package com.thvc.beeway.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
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
import com.thvc.beeway.bean.AddTrackBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.CustomConstants;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.view.CustomProgressDialog;

/**
 * Created by Administrator on 2015/9/22.
 * 发布宝藏
 */
public class ReleaseTreasureActivity extends BaseActivity {

    private EditText issuecutomer_rel_reason;
    private TextView tv_rel_clean, issuecutomer_rel_liyou, tv_user_tishi, tv_user_weizhi;
    private ImageView iv_rel_back;

    private TextView tv_title_rel_center;


    private String area, company, address, detail, latitude, longitude, content, prompt;

    private int count = 0;

    private HttpUtils httpUtils;

    private CustomProgressDialog pd;


    String ed_texts[] = {
            "少年,我看你骨骼清奇,聪慧过人,这里有葵花宝典一部,劝你勤加练习,将来拯救世界就靠你啦!",
            "你运气贼好了,这么隐蔽的宝藏居然被你找到了,联系我吧,发9.9元红包给你",
            "XXX,如果你能找到这个宝藏,我答应做你女朋友.",
            "父亲节这天，如果你找到了这个宝藏，您可以到芒果的店来领取一份精美小礼品.",
            "如果你找到了这个宝藏，就意味着你可以与我共度晚餐，不过需要你来买单.",
            "众里寻TA千百度,那人正在看我的宝藏,约不?", "我在佛前苦苦求了几千年,求得宝藏埋此间,愿得有缘真心人,共度人生一百年."
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_release_treasure);
        init();


    }

    public void init() {
        issuecutomer_rel_reason = (EditText) this.findViewById(R.id.issuecutomer_rel_reason);

        tv_rel_clean = (TextView) this.findViewById(R.id.tv_rel_clean);
        tv_rel_clean.setOnClickListener(new MyOnClickListener());

        issuecutomer_rel_liyou = (TextView) this.findViewById(R.id.issuecutomer_rel_liyou);
        issuecutomer_rel_liyou.setOnClickListener(new MyOnClickListener());

        tv_user_tishi = (TextView) this.findViewById(R.id.tv_user_tishi);
        tv_user_tishi.setOnClickListener(new MyOnClickListener());

        tv_user_weizhi = (TextView) this.findViewById(R.id.tv_user_weizhi);
        tv_user_weizhi.setOnClickListener(new MyOnClickListener());

        iv_rel_back = (ImageView) this.findViewById(R.id.iv_rel_back);
        iv_rel_back.setOnClickListener(new MyOnClickListener());

        tv_title_rel_center = (TextView) this.findViewById(R.id.tv_title_rel_centers);
        tv_title_rel_center.setOnClickListener(new MyOnClickListener());
    }

    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_rel_clean:
                    issuecutomer_rel_reason.setText("");
                    break;
                case R.id.issuecutomer_rel_liyou:
                    count++;
                    if (count > 6) {
                        count = 1;
                    }
                    issuecutomer_rel_reason.setText(ed_texts[count]);
                    break;
                case R.id.tv_user_tishi:
                    Intent intent = new Intent(ReleaseTreasureActivity.this, TreasureTishiActivity.class);
                    startActivityForResult(intent, 0);
                    break;
                case R.id.tv_user_weizhi:
                    String mark = "baozang";
                    Intent intentweizhi = new Intent(ReleaseTreasureActivity.this, ScenicActivity.class);
                    intentweizhi.putExtra("mark", mark);
                    startActivityForResult(intentweizhi, 1);
                    break;
                case R.id.iv_rel_back:
                    ReleaseTreasureActivity.this.finish();
                    break;
                case R.id.tv_title_rel_centers:
                    content = issuecutomer_rel_reason.getText().toString().trim();
                    prompt = tv_user_tishi.getText().toString().trim();
                    String weizhi = tv_user_weizhi.getText().toString().trim();
                    if (content.equals("")) {
                        Toast.makeText(ReleaseTreasureActivity.this, "请输入宝藏内容", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (prompt.equals("你宝藏提示语(请选择)")) {
                        Toast.makeText(ReleaseTreasureActivity.this, "请选择宝藏提示语", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (weizhi.equals("选一个宝藏地址")) {
                        Toast.makeText(ReleaseTreasureActivity.this, "请选择宝藏地址", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    pd = CustomProgressDialog.createDialog(ReleaseTreasureActivity.this);
                    pd.show();
                    //点击屏幕外侧，dialog不消失
                    pd.setCanceledOnTouchOutside(false);
                    postDate();
                    break;
            }
        }
    }

    private void removeTempFromPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        sp.edit().remove(CustomConstants.PREF_TEMP_IMAGES).commit();
    }

    private void postDate() {
        httpUtils = new HttpUtils();
        String userid = new BeewayApplication().getmUserid(this);
        MyRequestParams params = new MyRequestParams();//定义访问服务器参数
        params.addQueryStringParameter("content", content);
        params.addQueryStringParameter("prompt", prompt);
        params.addQueryStringParameter("userid", userid);
        params.addQueryStringParameter("latitude", latitude);
        params.addQueryStringParameter("longitude", longitude);
        params.addQueryStringParameter("area", area);
        params.addQueryStringParameter("company", company);
        params.addQueryStringParameter("address", address);
        params.addQueryStringParameter("detail", detail);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.TREASURE + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                AddTrackBean addTrackBean = paraseAddTrackDate(result);
                if (addTrackBean.getStatus() == 1) {
                    Toast.makeText(ReleaseTreasureActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                    removeDialog();
                    ReleaseTreasureActivity.this.finish();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(ReleaseTreasureActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * ADD_TRACK接口返回json字段的解析
     *
     * @param result
     * @return
     */
    private AddTrackBean paraseAddTrackDate(String result) {
        Gson gson = new Gson();
        AddTrackBean addTrackBean = gson.fromJson(result, AddTrackBean.class);
        return addTrackBean;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = data.getExtras().getString("result");//得到新Activity 关闭后返回的数据
        area = data.getExtras().getString("area");
        company = data.getExtras().getString("company");
        address = data.getExtras().getString("address");
        detail = data.getExtras().getString("detail");
        latitude = data.getExtras().getString("latitude");
        longitude = data.getExtras().getString("longitude");

        if (resultCode == RESULT_OK) {  //返回成功
            switch (requestCode) {
                case 0:
                    String results =result.substring(6);
                    tv_user_tishi.setText(results);
                    tv_user_tishi.setTextSize(13);
                    break;
                case 1:
                    tv_user_weizhi.setText(result);
                    break;
            }
        }
    }
}
