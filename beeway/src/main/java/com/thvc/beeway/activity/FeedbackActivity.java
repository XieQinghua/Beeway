package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.thvc.beeway.R;
import com.thvc.beeway.adapter.ImagePublishAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.FeedbackBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.photos.NewMainAdapter;
import com.thvc.beeway.photos.NewTestPicActivity;
import com.thvc.beeway.utils.CustomConstants;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.view.CustomProgressDialog;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/22.
 * 发布意见反馈
 */
public class FeedbackActivity extends BaseActivity {

    private EditText ed_feedbacks;
    private TextView tv_feedbacks;
    private HttpUtils httpUtils;
    private String content;
    private GridView gv_gridviews;
    private ImagePublishAdapter mAdapter;

    private static final int TAKE_PICTURE = 0x000000;
    private String path = "";

    private ImageView addpic;
    private ArrayList<String> mArrayList;
    private ImageView im_feedbacks_back;

    private String footprintid;
    private String userid;
    private String issedit;
    private String issreason;

    private CustomProgressDialog pd;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_feedback);
        inint();

    }

    private void removeTempFromPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        sp.edit().remove(CustomConstants.PREF_TEMP_IMAGES).commit();
    }


    public void inint() {
        ed_feedbacks = (EditText) this.findViewById(R.id.ed_feedbacks);
        tv_feedbacks = (TextView) this.findViewById(R.id.tv_feedbacks);
        tv_feedbacks.setOnClickListener(new MyOnClickListener());

        im_feedbacks_back = (ImageView) this.findViewById(R.id.im_feedbacks_back);
        im_feedbacks_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(im_feedbacks_back);
            }
        });
        gv_gridviews = (GridView) this.findViewById(R.id.gv_gridviews);

        addpic = (ImageView) this.findViewById(R.id.addpic);
        addpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PopupWindows(FeedbackActivity.this, gv_gridviews);
            }
        });

    }

    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            content = ed_feedbacks.getText().toString().trim();
            if (content.equals("")) {
                Toast.makeText(FeedbackActivity.this, "请输入这一刻的宝贵建议", Toast.LENGTH_SHORT).show();
                return;
            }
            pd = CustomProgressDialog.createDialog(FeedbackActivity.this);
            pd.show();
            //点击屏幕外侧，dialog不消失
            pd.setCanceledOnTouchOutside(false);
            postDate();

        }
    }

//    public void Setinsert(){
//        footprintid = String.valueOf(getTaskId()+System.currentTimeMillis());
//        String pathlist = "";
//        String getstatus = String.valueOf(0);
//
//        for (int i = 0; i < mArrayList.size(); i++) {
//            String sourcePath = mArrayList.get(i);
//
//        }
//        SimpleDateFormat formatters   =  new    SimpleDateFormat    ("yyyy年MM月dd日   HH:mm:ss");
//        Date curDates  = new  Date(System.currentTimeMillis());//获取当前时间
//        String  strDates =  formatters.format(curDates);
//
//        FeedbackText feedbackText = new FeedbackText(userid,footprintid,content,pathlist,getstatus,strDates);
//        FeedbackTextDao feedbackTextDao = new FeedbackTextDao(FeedbackActivity.this);
//        feedbackTextDao.textinsert(feedbackText);

    //        FengYouIssueCutnmerImge fengYouIssueCutnmerImge =new FengYouIssueCutnmerImge(userid,footprintid,sourcePath,key,getstatus,strDates);
//        FengYouIssueCutnmerImgeDao fengYouIssueCutnmerImgeDao = new FengYouIssueCutnmerImgeDao(IssueCutomerActivity.this);
//        fengYouIssueCutnmerImgeDao.imginsert(fengYouIssueCutnmerImge);
//
//        getToken();
//    }
    private void postDate() {
        httpUtils = new HttpUtils();
        String userid = new BeewayApplication().getmUserid(this);
        String nickname = new BeewayApplication().getmNickname(this);
        String mobile = new BeewayApplication().getUserName();
        String pathlist = "";
        MyRequestParams params = new MyRequestParams();//定义访问服务器参数
        params.addQueryStringParameter("userid", userid);
        params.addQueryStringParameter("nickname", nickname);
        params.addQueryStringParameter("mobile", mobile);
        params.addQueryStringParameter("content", content);
        params.addQueryStringParameter("pathlist", pathlist);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.FEEDBACK + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                FeedbackBean addTrackBean = paraseAddTrackDate(result);
                if (addTrackBean.getStatus() == 1) {
                    Toast.makeText(FeedbackActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                    removeDialog();
                    FeedbackActivity.this.finish();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(FeedbackActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 接口返回json字段的解析
     *
     * @param result
     * @return
     */
    private FeedbackBean paraseAddTrackDate(String result) {
        Gson gson = new Gson();
        FeedbackBean feedbackBean = gson.fromJson(result, FeedbackBean.class);
        return feedbackBean;
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
                    Intent intent = new Intent(FeedbackActivity.this, NewTestPicActivity.class);
                    startActivityForResult(intent, 0);
                }
            });
            bt3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (data != null) {
                    Bundle bundle = new Bundle();
                    bundle = data.getExtras();
                    mArrayList = bundle.getStringArrayList("imagelists");
                    Toast.makeText(FeedbackActivity.this, mArrayList.size() + "", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < mArrayList.size(); i++) {
                    }
                    gv_gridviews.setAdapter(new NewMainAdapter(FeedbackActivity.this, mArrayList));
                }
                break;
        }
    }


}
