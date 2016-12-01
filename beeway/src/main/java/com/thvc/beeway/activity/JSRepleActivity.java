package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
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
import com.thvc.beeway.bean.IsGoodDataBean;
import com.thvc.beeway.bean.RepleSendBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.view.FaceParser;
import com.thvc.beeway.view.FaceView;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/9/16.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class JSRepleActivity extends BaseActivity implements FaceView.Work {
    private Context context;
    public TextView tv_close;
    private FaceView fv_face;
    public EditText edit_commend;
    public ImageView ed_expression, im_expression, imviews;
    private InputMethodManager imm;
    public LinearLayout ll_layout, ll_face, js_reple_layout;
    public RelativeLayout rl_expression;
    private FaceParser parser;
    public String type, cid, commid, commnick, dataid, content;
    public int isdata;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.trackdetails_js_reple);
        context = this;
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if ("comment".equals(type)) {
            isdata = intent.getIntExtra("isdata", 0);
            dataid = intent.getStringExtra("dataid");
        } else if ("reple".equals(type)) {
            cid = intent.getStringExtra("cid");
            commid = intent.getStringExtra("commid");
            commnick = intent.getStringExtra("commnick");
            isdata = intent.getIntExtra("isdata", 0);
            dataid = intent.getStringExtra("dataid");
        }
        js_reple_layout = (LinearLayout) findViewById(R.id.js_reple_layout);
        edit_commend = (EditText) findViewById(R.id.edit_commed);
        ed_expression = (ImageView) findViewById(R.id.ed_expression);
        im_expression = (ImageView) findViewById(R.id.im_expression);
        tv_close = (TextView) findViewById(R.id.tv_close);
        imviews = (ImageView) findViewById(R.id.views);
        ll_layout = (LinearLayout) findViewById(R.id.ll_layouts);
        ll_face = (LinearLayout) findViewById(R.id.ll_face);
        rl_expression = (RelativeLayout) findViewById(R.id.rl_expression);
        fv_face = new FaceView(context, null, this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        ll_face.addView(fv_face, params);

        FaceParser.init(context);
        parser = FaceParser.getInstance();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        init();
    }

    public void init() {
        im_expression.setVisibility(View.VISIBLE);
        ed_expression.setVisibility(View.GONE);
        ll_layout.setVisibility(View.VISIBLE);
        rl_expression.setVisibility(View.VISIBLE);
        fv_face.setVisibility(View.GONE);
        imviews.setVisibility(View.VISIBLE);

        im_expression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_face.setVisibility(View.VISIBLE);
                fv_face.setVisibility(View.VISIBLE);
                im_expression.setVisibility(View.GONE);
                ed_expression.setVisibility(View.VISIBLE);
                imviews.setVisibility(View.VISIBLE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘

            }
        });
        ed_expression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                ll_face.setVisibility(View.GONE);
                ed_expression.setVisibility(View.GONE);
                fv_face.setVisibility(View.VISIBLE);
                im_expression.setVisibility(View.VISIBLE);
                imviews.setVisibility(View.VISIBLE);
            }
        });

        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im_expression.setVisibility(View.VISIBLE);
                ed_expression.setVisibility(View.GONE);
                ll_layout.setVisibility(View.GONE);
                rl_expression.setVisibility(View.GONE);
                fv_face.setVisibility(View.GONE);
                imviews.setVisibility(View.GONE);
                content = edit_commend.getText().toString().trim();
                if ("comment".equals(type)) {
                    if (!"".equals(content) && content.length() != 0) {
                        commentData();
                    } else {
                        Toast.makeText(context, "您没有输入数据！", Toast.LENGTH_SHORT).show();
                    }
                } else if ("reple".equals(type)) {
                    if (!"".equals(content) && content.length() != 0) {
                        loadData();
                    } else {
                        Toast.makeText(context, "您没有输入数据！", Toast.LENGTH_SHORT).show();
                    }
                }

                imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘

//                Intent intent = new Intent(context,TrackDetailsActivity.class);
//                startActivity(intent);
            }
        });
        edit_commend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im_expression.setVisibility(View.VISIBLE);
                ed_expression.setVisibility(View.GONE);
                ll_layout.setVisibility(View.VISIBLE);
                rl_expression.setVisibility(View.VISIBLE);
                fv_face.setVisibility(View.GONE);
                imviews.setVisibility(View.VISIBLE);
            }
        });
        js_reple_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    /**
     * 回复网络获取数据
     */
    public void loadData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("cid", cid);
                params.addQueryStringParameter("commid", commid);
                params.addQueryStringParameter("commnick", commnick);
                params.addQueryStringParameter("isdata", String.valueOf(isdata));
                params.addQueryStringParameter("dataid", dataid);
                params.addQueryStringParameter("userid", GlobalParams.loginUserId);
                params.addQueryStringParameter("nickname", new BeewayApplication().getmNickname(context));
                params.addQueryStringParameter("content", content);
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.REPLE + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        System.out.println("发送 评论    json====" + result);
                        RepleSendBean reple = repleSendBean(result);
                        if (reple.getStatus() == 1) {
                            Toast.makeText(context, reple.getData(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.putExtra("TYPE", "success");
                            setResult(2, intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
            }
        }.start();
    }

    /**
     * 评论网络获取数据
     */
    public void commentData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("isdata", String.valueOf(isdata));
                params.addQueryStringParameter("dataid", dataid);
                params.addQueryStringParameter("userid", GlobalParams.loginUserId);
                params.addQueryStringParameter("nickname", new BeewayApplication().getmNickname(context));
                params.addQueryStringParameter("content", content);
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.REPLE + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        System.out.println("发送 评论    json====" + result);
                        IsGoodDataBean comment = isGoodDataBean(result);
                        if (comment.getStatus() == 1) {
                            Toast.makeText(context, "评论成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.putExtra("TYPE", "success");
                            setResult(1, intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
            }
        }.start();
    }

    /**
     * 评论网络获取数据解析
     */
    public IsGoodDataBean isGoodDataBean(String result) {
        Gson gson = new Gson();
        IsGoodDataBean isGoodDataBean = gson.fromJson(result, IsGoodDataBean.class);
        return isGoodDataBean;
    }

    /**
     * 回复网络获取数据解析
     */
    public RepleSendBean repleSendBean(String result) {
        Gson gson = new Gson();
        RepleSendBean repleSendBean = gson.fromJson(result, RepleSendBean.class);
        return repleSendBean;
    }

    @Override
    public void onClick(int id, String item_str) {
        // TODO Auto-generated method stub
        replace(id, item_str, edit_commend);
    }

    private void replace(int id, String item_str, TextView view) {
        // TODO Auto-generated method stub
        Drawable drawable = context.getResources().getDrawable(id); // 要出入的图片
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        // 需要处理的文本，[smile]是需要被替代的文本
        SpannableString spannable = new SpannableString(item_str);
        // 要让图片替代指定的文字就要用ImageSpan
        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        // 开始替换
        spannable.setSpan(span, 0, spannable.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        view.append(spannable);
    }
}
