package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.thvc.beeway.R;
import com.thvc.beeway.Release.FengyouFootprintImage;
import com.thvc.beeway.Release.FengyouFootprintImageDao;
import com.thvc.beeway.Release.FengyouFootprintText;
import com.thvc.beeway.Release.FengyouFootprintTextDao;
import com.thvc.beeway.adapter.ImagePublishAdapter;
import com.thvc.beeway.adapter.wheelview.adapter.NumericWheelAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.AddTrackBean;
import com.thvc.beeway.bean.ImageItem;
import com.thvc.beeway.bean.JsonArrayBean;
import com.thvc.beeway.bean.TokenBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.CustomConstants;
import com.thvc.beeway.utils.IntentConstants;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.utils.wheelview.OnWheelScrollListener;
import com.thvc.beeway.utils.wheelview.WheelView;
import com.thvc.beeway.view.CustomProgressDialog;
import com.thvc.beeway.view.FaceParser;
import com.thvc.beeway.view.FaceView;

import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 项目名称：Beeway
 * 类描述：发布足迹页面
 * 创建人：谢庆华.
 * 创建时间：2015/8/22 18:39
 * 修改人：Administrator
 * 修改时间：2015/8/22 18:39
 * 修改备注：
 */
public class AddTrackActivity extends BaseActivity implements FaceView.Work {

    Context context = this;
    private GridView mGridView;
    private ImageView iv_back;
    private ImagePublishAdapter mAdapter;
    private TextView tv_publish, tv_track_label;
    private EditText et_track_text;
    public static List<ImageItem> mDataList = new ArrayList<ImageItem>();


    private TextView tv_select_time;//选择时间控件
    private TextView tv_user_place;//选取景点所在位置控件
    private LayoutInflater inflater = null;
    private WheelView year;
    private WheelView month;
    private WheelView day;

    private LinearLayout ll;
    private View view = null;
    public ScrollView scrollView;
    private String tv_track, tv__time, tv__place, et_text;
    private int norYear, norMonth;

    private TextView tv_cancel, tv_determine;

    private RelativeLayout rl_expression;
    private ImageView im_expression;
    private TextView tv_close;
    private InputMethodManager imm;

    private LinearLayout ll_face;
    private FaceView fv_face;
    private FaceParser parser;

    private String keys;
    private String token = null;
    private HttpUtils httpUtils;
    private ImageView imviews;


    private ImageView ed_expression;
    private LinearLayout ll_layout;


    //发布足迹数据变量
    private String addtime, tv_tracklabel, latitude, longitude, company, area, detail, content, userid, filekeys, address, public_status, istype;
    private UploadManager uploadManager;
    private String footprintid;
    private String jsonobj;
    private TextView tv_synchronous;
    private String resumeId;

    private CustomProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);

        initData();
        initView();

        inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        ll = (LinearLayout) findViewById(R.id.ll_date);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        fv_face = new FaceView(context, null, this);
        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT);
        ll_face.addView(fv_face, params);

        FaceParser.init(AddTrackActivity.this);
        parser = FaceParser.getInstance();

        resumeId = getIntent().getStringExtra("resumeId");


    }


    protected void onPause() {
        super.onPause();
        saveTempToPref();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveTempToPref();
    }

    private void saveTempToPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        String prefStr = JSON.toJSONString(mDataList);
        sp.edit().putString(CustomConstants.PREF_TEMP_IMAGES, prefStr).commit();
    }

    private void getTempFromPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        String prefStr = sp.getString(CustomConstants.PREF_TEMP_IMAGES, null);
        if (!TextUtils.isEmpty(prefStr)) {
            List<ImageItem> tempImages = JSON.parseArray(prefStr,
                    ImageItem.class);
            mDataList = tempImages;
        }
    }

    private void removeTempFromPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        sp.edit().remove(CustomConstants.PREF_TEMP_IMAGES).commit();
    }

    @SuppressWarnings("unchecked")
    private void initData() {
        getTempFromPref();
        List<ImageItem> incomingDataList = (List<ImageItem>) getIntent()
                .getSerializableExtra(IntentConstants.EXTRA_IMAGE_LIST);
        if (incomingDataList != null) {
            mDataList.addAll(incomingDataList);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        notifyDataChanged(); //当在ImageZoomActivity中删除图片时，返回这里需要刷新
    }


    @Override
    public void onClick(int id, String item_str) {
        // TODO Auto-generated method stub
        replace(id, item_str, et_track_text);
    }

    private void replace(int id, String item_str, TextView view) {
        // TODO Auto-generated method stub
        Drawable drawable = getResources().getDrawable(id); // 要出入的图片
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


    public void initView() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mGridView = (GridView) findViewById(R.id.gv_gridview);
        mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mAdapter = new ImagePublishAdapter(this, mDataList);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ll.setVisibility(View.GONE);
                fv_face.setVisibility(View.GONE);
                ll.removeAllViews();
                tv_select_time.setClickable(true);
                if (position == getDataSize()) {
                    new PopupWindows(AddTrackActivity.this, mGridView);
                } else {
                    Intent intent = new Intent(AddTrackActivity.this,
                            ImageZoomActivity.class);
                    intent.putExtra(IntentConstants.EXTRA_IMAGE_LIST,
                            (Serializable) mDataList);
                    intent.putExtra(IntentConstants.EXTRA_CURRENT_IMG_POSITION, position);
                    startActivity(intent);
                }
            }
        });

        tv_select_time = (TextView) findViewById(R.id.tv_select_time);//拿到选择时间控件
        tv_select_time.setOnClickListener(new MyOnClickListener());//给控件设置点击事件

        tv_user_place = (TextView) findViewById(R.id.tv_user_place);
        tv_user_place.setOnClickListener(new MyOnClickListener());


        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataList.clear();
                back(iv_back);
            }
        });
        tv_track_label = (TextView) findViewById(R.id.tv_track_label);

        rl_expression = (RelativeLayout) findViewById(R.id.rl_expression);
        ll_face = (LinearLayout) findViewById(R.id.ll_face);
        ll_layout = (LinearLayout) findViewById(R.id.ll_layouts);
        imviews = (ImageView) findViewById(R.id.views);

        im_expression = (ImageView) findViewById(R.id.im_expression);
        tv_close = (TextView) findViewById(R.id.tv_close);

        et_track_text = (EditText) findViewById(R.id.et_track_text);
        tv_publish = (TextView) findViewById(R.id.tv_publish);


        ed_expression = (ImageView) findViewById(R.id.ed_expression);

        tv_synchronous = (TextView) this.findViewById(R.id.tv_synchronous);
        tv_synchronous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fv_face.setVisibility(View.GONE);
                ll.setVisibility(View.GONE);
                ll.removeAllViews();
                tv_select_time.setClickable(true);
                tv_track = tv_track_label.getText().toString().trim();
                tv__place = tv_user_place.getText().toString().trim();
                et_text = et_track_text.getText().toString().trim();
                tv__time = tv_select_time.getText().toString().trim();
                Intent intent = new Intent(AddTrackActivity.this, MyTravels.class);
                intent.putExtra("et_text", et_text);
                intent.putExtra("tv_track", tv_track);
                intent.putExtra("tv__place", tv__place);
                intent.putExtra("tv__time", tv__time);
                startActivity(intent);
                AddTrackActivity.this.finish();
            }
        });


        tv_track_label.setText(getIntent().getStringExtra("label"));
        if (tv_track_label.getText().toString().trim().equals("")) {
            tv_track_label.setText(getIntent().getStringExtra("tv_track"));
            tv_select_time.setText(getIntent().getStringExtra("tv__time"));
            tv_user_place.setText(getIntent().getStringExtra("tv__place"));
            et_track_text.setText(getIntent().getStringExtra("et_text"));
        }


        tv_publish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String tv_label = tv_track_label.getText().toString().trim();
                String et_text = et_track_text.getText().toString().trim();

                String tv_place = tv_user_place.getText().toString().trim();
                if (tv_label.equals("")) {
                    Toast.makeText(AddTrackActivity.this, "足迹类型不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (et_text.equals("")) {
                    Toast.makeText(AddTrackActivity.this, "请输入这一刻的想法", Toast.LENGTH_SHORT).show();
                    return;
                } else if (mDataList.size() <= 0) {
                    Toast.makeText(AddTrackActivity.this, "请选需要发布的图片", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tv_place.equals("") || tv_place.equals("请选取景点所在位置")) {
                    Toast.makeText(AddTrackActivity.this, "请选取景点所在位置", Toast.LENGTH_SHORT).show();
                    return;
                }
                pd = CustomProgressDialog.createDialog(AddTrackActivity.this);
                pd.show();
                //点击屏幕外侧，dialog不消失
                pd.setCanceledOnTouchOutside(false);
                removeTempFromPref();
                getData();
                Setinsert();
                getToken();

            }
        });

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
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
            }
        });
        et_track_text.setOnClickListener(new View.OnClickListener() {
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
        ll_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void Setinsert() {
        footprintid = String.valueOf(getTaskId() + System.currentTimeMillis());
        String pathlist = ",";
        String getstatus = String.valueOf(0);
        //缓存文字数据到本地数据库
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String strDate = formatter.format(curDate);
        FengyouFootprintText fengyouFootprint = new FengyouFootprintText(userid, footprintid, jsonobj, pathlist, getstatus, istype, strDate);
        FengyouFootprintTextDao fengyouFootprintDao = new FengyouFootprintTextDao(AddTrackActivity.this);
        fengyouFootprintDao.textinsert(fengyouFootprint);

        for (int i = 0; i < mDataList.size(); i++) {
            String sourcePath = mDataList.get(i).sourcePath.toString(); //+ String.valueOf(System.currentTimeMillis());
            keys = String.valueOf("track/" + System.currentTimeMillis()) + sourcePath.substring(sourcePath.lastIndexOf("/") + 1);

            SimpleDateFormat formatters = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
            Date curDates = new Date(System.currentTimeMillis());//获取当前时间
            String strDates = formatters.format(curDates);

            FengyouFootprintImage fengyouFootprintImage = new FengyouFootprintImage(userid, footprintid, sourcePath, keys, getstatus, strDates);
            FengyouFootprintImageDao fengyouFootprintImageDao = new FengyouFootprintImageDao(AddTrackActivity.this);
            fengyouFootprintImageDao.imageinsert(fengyouFootprintImage);
//
        }

    }

    public void getData() {
        /**
         *  //发布足迹
         * public static final String ADD_TRACK = getHost() +
         * "APP/Track/add?userid=%s&filekeys=%s&content=%s&latitude=%s&longitude=%s&area=%s&company=%s&addtime=%s&detail=%s&address=%s&type%s";
         *
         */
        addtime = tv_select_time.getText().toString().trim();

        if (addtime.equals("选择时间")) {
            addtime = "";
        }
        tv_tracklabel = tv_track_label.getText().toString().trim();
        latitude = getIntent().getStringExtra("latitude");
        longitude = getIntent().getStringExtra("longitude");
        company = getIntent().getStringExtra("company");
        area = getIntent().getStringExtra("area");
        detail = getIntent().getStringExtra("detail");

        content = et_track_text.getText().toString().trim();
        userid = new BeewayApplication().getmUserid(this);

        address = tv_user_place.getText().toString().trim();
        public_status = String.valueOf(2);


        /**
         * 足迹类型id
         */
        istype = "0";
        if (tv_tracklabel.equals("吃货")) {
            istype = "1";
        } else if (tv_tracklabel.equals("风景")) {
            istype = "2";
        } else if (tv_tracklabel.equals("购物")) {
            istype = "3";
        } else if (tv_tracklabel.equals("约伴")) {
            istype = "4";
        } else if (tv_tracklabel.equals("活动")) {
            istype = "5";
        } else if (tv_tracklabel.equals("吐槽")) {
            istype = "6";
        } else if (tv_tracklabel.equals("摄影")) {
            istype = "7";
        } else if (tv_tracklabel.equals("感慨")) {
            istype = "8";
        } else if (tv_tracklabel.equals("其他")) {
            istype = "9";
        }
        JsonArrayBean mJsonArrayBean = new JsonArrayBean(public_status, istype, addtime, tv_tracklabel,
                latitude, longitude, company, area, detail, content, userid, address);

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        jsonobj = gson.toJson(mJsonArrayBean);
        Log.i("yan", jsonobj);

    }

    private void postDate() {
        httpUtils = new HttpUtils();
        /**
         * 查询数据库上传数据到服务器
         */
        FengyouFootprintTextDao fengyouFootprintTextDao = new FengyouFootprintTextDao(AddTrackActivity.this);
        FengyouFootprintText footprintText = fengyouFootprintTextDao.select(userid, footprintid);
        String str = footprintText.getPathlist();
        String userid = footprintText.getUserid();
        String filekeys = str.substring(1, str.length() - 1);
        MyRequestParams params = new MyRequestParams();//定义访问服务器参数
        params.addQueryStringParameter("userid", userid);
        params.addQueryStringParameter("filekeys", filekeys);
        params.addQueryStringParameter("content", content);
        params.addQueryStringParameter("latitude", latitude);
        params.addQueryStringParameter("longitude", longitude);
        params.addQueryStringParameter("area", area);
        params.addQueryStringParameter("company", company);
        params.addQueryStringParameter("adddate", addtime);
        params.addQueryStringParameter("istype", istype);
        params.addQueryStringParameter("public_status", public_status);
        params.addQueryStringParameter("trackid", resumeId);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.ADD_TRACK + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                AddTrackBean addTrackBean = paraseAddTrackDate(result);
                if (addTrackBean.getStatus() == 1) {
                    Toast.makeText(AddTrackActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                    mDataList.clear();
                    removeDialog();
                    AddTrackActivity.this.finish();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(AddTrackActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getToken() {
        FengyouFootprintImageDao fengyouImageDao = new FengyouFootprintImageDao(AddTrackActivity.this);
        FengyouFootprintImage fengyouImage = fengyouImageDao.select(userid, footprintid);

        if (fengyouImage != null) {
            final String status = fengyouImage.getStatus().toString();
            final String key = fengyouImage.getContent().toString();
            final String filePath = fengyouImage.getFilePath().toString();
            if (status.equals("0") && !status.equals("3")) {
                // 1442216299897
                uploadManager = new UploadManager();
                HttpUtils httpUtils = new HttpUtils();
//                String url = String.format(UrlPools.GETTOKEN, key);
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("key", key);
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.GETTOKEN + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //访问获得token成功
                        String result = responseInfo.result;
                        TokenBean tokenBean = paraseData(result);
                        token = tokenBean.getData().getUpToken();
                        Log.e("此次上传的token值", token);
                        uploadManager.put(filePath, key, token, new UpCompletionHandler() {
                            @Override
                            public void complete(String s, com.qiniu.android.http.ResponseInfo responseInfo, JSONObject jsonObject) {
                                if (responseInfo.statusCode == 200) {
                                    FengyouFootprintTextDao fengyouFootprintTextDao = new FengyouFootprintTextDao(AddTrackActivity.this);
                                    FengyouFootprintText footprintText = fengyouFootprintTextDao.select(userid, footprintid);
                                    String pathlist = footprintText.getPathlist();

                                    filekeys = "";
                                    filekeys += pathlist + key + ",";
                                    String statuss = String.valueOf(3);

                                    FengyouFootprintTextDao footprintTextDao = new FengyouFootprintTextDao(AddTrackActivity.this);
                                    footprintTextDao.update(userid, footprintid, filekeys);

                                    FengyouFootprintImageDao footprintImageDao = new FengyouFootprintImageDao(AddTrackActivity.this);
                                    footprintImageDao.imgupdate(userid, filePath, statuss);

                                    getToken();
                                } else {
                                    Toast.makeText(AddTrackActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        }, null);
                    }
                    @Override
                    public void onFailure(HttpException e, String s) {
                        //获得token失败
                        LogUtils.i(s);
                    }
                });
            } else if (status.equals("3")) {
                Toast.makeText(AddTrackActivity.this, "完成上传", Toast.LENGTH_SHORT).show();
            }
        } else {
            //post数据方法
            postDate();
        }
    }

    /**
     * GETTOKEN接口返回json字段的解析
     *
     * @param result
     */
    private TokenBean paraseData(String result) {
        Gson gson = new Gson();
        TokenBean tokenBean = gson.fromJson(result, TokenBean.class);
        LogUtils.i(tokenBean.getStatus() + "");
        return tokenBean;
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

    /**
     * 时间选择器和景点所在位置
     * 添加人 ： 颜松梁
     */
    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.tv_select_time:
                    //选择日期
                    rl_expression.setVisibility(View.GONE);
                    ll_face.setVisibility(View.GONE);
                    ll.setVisibility(View.VISIBLE);
                    fv_face.setVisibility(View.GONE);
                    ll.addView(getDataPick());
                    tv_select_time.setClickable(false);
                    break;
                case R.id.tv_user_place:
                    //选取景点所在位置
                    ll_face.setVisibility(View.GONE);
                    fv_face.setVisibility(View.GONE);
                    ll.setVisibility(View.GONE);
                    ll.removeAllViews();
                    tv_select_time.setClickable(true);
                    tv_track = tv_track_label.getText().toString().trim();
                    tv__time = tv_select_time.getText().toString().trim();
                    tv__place = tv_user_place.getText().toString().trim();
                    et_text = et_track_text.getText().toString().trim();
                    String mark = "选择";
                    Intent intent = new Intent(AddTrackActivity.this, ScenicActivity.class);
                    intent.putExtra("et_text", et_text);
                    intent.putExtra("tv_track", tv_track);
                    intent.putExtra("tv__time", tv__time);
                    intent.putExtra("tv__place", tv__place);
                    intent.putExtra("mark", mark);
                    startActivity(intent);
                    AddTrackActivity.this.finish();
                    break;
            }
        }
    }


    private int getDataSize() {
        return mDataList == null ? 0 : mDataList.size();
    }

    private int getAvailableSize() {
        int availSize = CustomConstants.MAX_IMAGE_SIZE - mDataList.size();
        if (availSize >= 0) {
            return availSize;
        }
        return 0;
    }

    public String getString(String s) {
        String path = null;
        if (s == null) return "";
        for (int i = s.length() - 1; i > 0; i++) {
            s.charAt(i);
        }
        return path;
    }


    public class PopupWindows extends PopupWindow {

        public PopupWindows(Context mContext, View parent) {

            View view = View.inflate(mContext, R.layout.item_popupwindows, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_ins));
            LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
            ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_bottom_in_2));

            setWidth(ViewGroup.LayoutParams.FILL_PARENT);
            //修改高度显示，解决被手机底部虚拟键挡住的问题
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            //实例化一个ColorDrawable颜色为半透明
            setBackgroundDrawable(new ColorDrawable(0xb0000000));
            //menuview添加ontouchlistener监听判断获取触屏位置如果在选择框外面则销毁弹出框
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int height = view.findViewById(R.id.ll_popup).getTop();
                    int y = (int) motionEvent.getY();
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        if (y < height) {
                            dismiss();
                        }
                    }
                    return true;
                }
            });
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            update();

            Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);//拍照按钮
            Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);//选取照片按钮
            Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);//取消按钮
            bt1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    takePhoto();
                    dismiss();
                }
            });
            bt2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(AddTrackActivity.this, ImageBucketChooseActivity.class);
                    tv_track = tv_track_label.getText().toString().trim();
                    tv__time = tv_select_time.getText().toString().trim();
                    tv__place = tv_user_place.getText().toString().trim();
                    et_text = et_track_text.getText().toString().trim();
                    intent.putExtra(IntentConstants.EXTRA_CAN_ADD_IMAGE_SIZE, getAvailableSize());
                    String addtrack = "addtrack";
                    intent.putExtra("make", addtrack);
                    intent.putExtra("tv_track", tv_track);
                    intent.putExtra("tv__time", tv__time);
                    intent.putExtra("tv__place", tv__place);
                    intent.putExtra("et_text", et_text);
                    startActivity(intent);
                    dismiss();
                    AddTrackActivity.this.finish();
                }
            });
            bt3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }

    private static final int TAKE_PICTURE = 0x000000;
    private String path = "";

    public void takePhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File vFile = new File(Environment.getExternalStorageDirectory()
                + "/myimage/", String.valueOf(System.currentTimeMillis())
                + ".jpg");
        if (!vFile.exists()) {
            File vDirPath = vFile.getParentFile();
            vDirPath.mkdirs();
        } else {
            if (vFile.exists()) {
                vFile.delete();
            }
        }
        path = vFile.getPath();
        Uri cameraUri = Uri.fromFile(vFile);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (mDataList.size() < CustomConstants.MAX_IMAGE_SIZE
                        && resultCode == -1 && !TextUtils.isEmpty(path)) {
                    ImageItem item = new ImageItem();
                    item.sourcePath = path;
                    mDataList.add(item);
                }
                break;

        }
    }

    private void notifyDataChanged() {
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 设置选择日期滚动效果
     * 添加人：颜松梁
     *
     * @return
     */
    private View getDataPick() {
        Calendar c = Calendar.getInstance();
        norYear = c.get(Calendar.YEAR);
        norMonth = c.get(Calendar.MONTH) + 1;

        view = inflater.inflate(R.layout.wheel_date_picker, null);

        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_determine = (TextView) view.findViewById(R.id.tv_determine);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll.setVisibility(View.GONE);
                ll.removeAllViews();
                tv_select_time.setClickable(true);
            }
        });

        year = (WheelView) view.findViewById(R.id.year);
        NumericWheelAdapter numericWheelAdapter1 = new NumericWheelAdapter(getApplication(), norYear, norYear + 10);
        numericWheelAdapter1.setLabel("年");
        year.setViewAdapter(numericWheelAdapter1);
        year.setCyclic(true);//设置是否循环滚动
        year.addScrollingListener(scrollListener);


        /**
         * 解决时间滚动和ScrollView的冲突
         * 添加人 ： 颜松梁
         */
        year.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    scrollView.requestDisallowInterceptTouchEvent(false);
                } else {
                    scrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });

        month = (WheelView) view.findViewById(R.id.month);
        NumericWheelAdapter numericWheelAdapter2 = new NumericWheelAdapter(getApplication(), 1, 12, "%02d");
        numericWheelAdapter2.setLabel("月");
        month.setViewAdapter(numericWheelAdapter2);
        month.setCyclic(true); //设置是否循环滚动
        month.addScrollingListener(scrollListener);

        /**
         * 解决时间滚动和ScrollView的冲突
         * 添加人 ： 颜松梁
         */
        month.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    scrollView.requestDisallowInterceptTouchEvent(false);
                } else {
                    scrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        day = (WheelView) view.findViewById(R.id.day);
        NumericWheelAdapter numericWheelAdapter3 = new NumericWheelAdapter(getApplication(), 1, getDay(norYear, norMonth), "%02d");
        numericWheelAdapter3.setLabel("日");
        day.setViewAdapter(numericWheelAdapter3);
        day.setCyclic(true);//设置是否循环滚动
        day.addScrollingListener(scrollListener);


        year.setVisibleItems(7);//设置显示行数
        month.setVisibleItems(7);//设置显示行数
        day.setVisibleItems(7);//设置显示行数


        /**
         * 解决时间滚动和ScrollView的冲突
         */
        day.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    scrollView.requestDisallowInterceptTouchEvent(false);
                } else {
                    scrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });

        return view;
    }

    /**
     * 滚动日期设置到tv_select_time
     * 添加人 ： 颜松梁
     */
    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {
        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            int n_year = year.getCurrentItem() + norYear;//年
            int n_month = month.getCurrentItem() + 1;//月

            initDay(n_year, n_month);


            tv_determine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String birthday = new StringBuilder().append((year.getCurrentItem() + norYear)).append("-").append((month.getCurrentItem() + 1) < 10 ? "0" + (month.getCurrentItem() + 1) : (month.getCurrentItem() + 1)).append("-").append(((day.getCurrentItem() + 1) < 10) ? "0" + (day.getCurrentItem() + 1) : (day.getCurrentItem() + 1)).toString();
                    tv_select_time.setText(birthday);
                    ll.setVisibility(View.GONE);
                    ll.removeAllViews();
                    tv_select_time.setClickable(true);
                }
            });

        }
    };
    private NumericWheelAdapter numericWheelAdapter;

    /**
     * @param year
     * @param month
     * @return 每月天数的算法
     * 添加人 ： 颜松梁
     */
    private int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }

    /**
     * 添加人 ： 颜松梁
     */
    private void initDay(int arg1, int arg2) {
        numericWheelAdapter = new NumericWheelAdapter(this, 1, getDay(arg1, arg2), "%02d");
        numericWheelAdapter.setLabel("日");
        day.setViewAdapter(numericWheelAdapter);
    }


    /**
     * 监听放回按钮
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //假如按的回退键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            mDataList.clear();
            back(iv_back);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}