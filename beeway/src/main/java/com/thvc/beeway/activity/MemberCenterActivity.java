package com.thvc.beeway.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chatui.activity.AlertDialog;
import com.easemob.chatui.activity.LoginActivity;
import com.easemob.chatui.utils.UserUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.thvc.beeway.R;
import com.thvc.beeway.Release.MessageCenterDao;
import com.thvc.beeway.Zing.MyErweimaActivity;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.MyWalletBean;
import com.thvc.beeway.bean.TokenBean;
import com.thvc.beeway.bean.UserLevelBean;
import com.thvc.beeway.fragment.CustomerFragment;
import com.thvc.beeway.fragment.DialogFragment;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.photos.Bimp;
import com.thvc.beeway.photos.FileUtils;
import com.thvc.beeway.utils.GlobalParams;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;
import com.thvc.beeway.view.MyMessagePerson;
import com.thvc.beeway.view.UserDefineScrollView;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：Beeway
 * 类描述：会员中心
 * 创建人：谢庆华.
 * 创建时间：2015/9/7 15:51
 * 修改人：Administrator
 * 修改时间：2015/9/7 15:51
 * 修改备注：
 */
public class MemberCenterActivity extends BaseActivity implements View.OnClickListener {
    public static final int CANCEL_LOGIN = 0;
    public static final String TAG = "MemberCenterActivity";
    public static final String JUMP = "jump";
    private RelativeLayout iv_layout, rl_layout_title;
    private CircleImageView iv_user_img;
    private UserDefineScrollView sv_content;
    private ImageView iv_cover;
    private TextView tv_nick_name, tv_user_sex, tv_user_rank, tv_content, tv_myzuji, tv_mytravel,
            tv_myxiaoxi, tv_myzhongchou, tv_mybaozang, tv_myshoucang,
            tv_myerweima, tv_mytongxulu, tv_shezhi,
            tv_xiugai, tv_xiugaimima, tv_zhuxiao, tv_money, tv_honey;
    ViewGroup.LayoutParams para;
    private int title_height;
    private String userids;
    private String status;

    private ImageView im_prompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_center);
        initView();
        WalletData();
        userids = BeewayApplication.getInstance().getmUserid(this);//拿到当前用户的用户名
        /**监听ScrollView下滑高度*/
        sv_content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {//监听到ScrollView滚动事件
                    if (v.getScrollY() >= (para.height - title_height)) {
                        rl_layout_title.setBackgroundColor(getResources().getColor(R.color.beeway_title_bule));
                        rl_layout_title.getBackground().setAlpha(178);
                    } else {
                        rl_layout_title.setBackgroundColor(getResources().getColor(R.color.beeway_title_transparent));
                    }
                }
                return false;
            }
        });
        /**设置头像和封面,均实时更新*/
        iv_user_img.setImageUrl(UrlPools.getAvatarUrl(this), VolleyHepler.getInstance().getImageLoader());
        UserUtils.setUserCover(this, BeewayApplication.getInstance().getmUserid(this), iv_cover);
        iv_user_img.setOnClickListener(this);
        iv_cover.setOnClickListener(this);
        tv_nick_name.setText(BeewayApplication.getInstance().getmNickname(this));
        tv_content.setText(BeewayApplication.getInstance().getContent(this));
        tv_myxiaoxi.setOnClickListener(this);
        tv_myzuji.setOnClickListener(this);
        tv_mytravel.setOnClickListener(this);
        tv_myzhongchou.setOnClickListener(this);
        tv_mybaozang.setOnClickListener(this);
        tv_myshoucang.setOnClickListener(this);
        tv_myerweima.setOnClickListener(this);
        tv_mytongxulu.setOnClickListener(this);
        tv_shezhi.setOnClickListener(this);
        tv_xiugai.setOnClickListener(this);
        tv_xiugaimima.setOnClickListener(this);
        tv_zhuxiao.setOnClickListener(this);
        tv_money.setOnClickListener(this);
        tv_honey.setOnClickListener(this);
    }

    private void initView() {
        dp = getResources().getDimension(R.dimen.dp);
        title_height = (int) getResources().getDimension(R.dimen.height_top_bar);
        iv_user_img = (CircleImageView) findViewById(R.id.iv_user_img);
        rl_layout_title = (RelativeLayout) findViewById(R.id.rl_layout_title);
        sv_content = (UserDefineScrollView) findViewById(R.id.sv_content);
        iv_cover = (ImageView) findViewById(R.id.iv_cover);
        iv_layout = (RelativeLayout) findViewById(R.id.iv_layout);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        para = iv_layout.getLayoutParams();
        para.height = wm.getDefaultDisplay().getWidth() * 3 / 4;
        para.width = wm.getDefaultDisplay().getWidth();
        iv_layout.setLayoutParams(para);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_nick_name = (TextView) findViewById(R.id.tv_nick_name);
        tv_user_sex = (TextView) findViewById(R.id.tv_user_sex);
        if ((BeewayApplication.getInstance().getSex(this)).equals("1")) {
            tv_user_sex.setText("男");
            tv_user_sex.setBackgroundResource(R.drawable.corner_view_left);
        } else {
            tv_user_sex.setText("女");
            tv_user_sex.setBackgroundResource(R.drawable.corner_view_left_sex);
        }
        tv_user_rank = (TextView) findViewById(R.id.tv_user_rank);
        tv_myxiaoxi = (TextView) findViewById(R.id.tv_myxiaoxi);
        tv_myzuji = (TextView) findViewById(R.id.tv_myzuji);
        tv_mytravel = (TextView) findViewById(R.id.tv_mytravel);
        tv_myzhongchou = (TextView) findViewById(R.id.tv_myzhongchou);
        tv_mybaozang = (TextView) findViewById(R.id.tv_mybaozang);
        tv_myshoucang = (TextView) findViewById(R.id.tv_myshoucang);
        tv_myerweima = (TextView) findViewById(R.id.tv_myerweima);
        tv_mytongxulu = (TextView) findViewById(R.id.tv_mytongxulu);
        tv_shezhi = (TextView) findViewById(R.id.tv_shezhi);
        tv_xiugai = (TextView) findViewById(R.id.tv_xiugai);
        tv_xiugaimima = (TextView) findViewById(R.id.tv_xiugaimima);
        tv_zhuxiao = (TextView) findViewById(R.id.tv_zhuxiao);
        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_honey = (TextView) this.findViewById(R.id.tv_honey);
        im_prompt =(ImageView)this.findViewById(R.id.im_prompt);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //查询数据库 显示推送消息提示
        MessageCenterDao messageCenterDao = new MessageCenterDao(getApplication());
        List<MyMessagePerson> persons = messageCenterDao.select(userids);
        for (MyMessagePerson person : persons) {
            status = person.getStatuss();//拿到数据库里的标示
            if (persons.size() > 0 && status.equals("0")) {
                im_prompt.setVisibility(View.VISIBLE);
            }else {
                im_prompt.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.iv_cover:
                new PopupWindows(this, v);
                break;
            case R.id.iv_user_img:
                new PopupWindows(this, v);
                break;
            case R.id.tv_myzuji:
                startActivity(new Intent(MemberCenterActivity.this, MyTrackActivity.class).putExtra("count", JUMP));
                break;
            case R.id.tv_mytravel:
                startActivity(new Intent(MemberCenterActivity.this, MyTravelsActivity.class).putExtra("count", JUMP));
                break;
            case R.id.tv_myzhongchou:
                intent = new Intent(MemberCenterActivity.this, CustomerFragment.class);
                intent.putExtra("label", JUMP);
                startActivity(intent);
                break;
            case R.id.tv_mybaozang:
                startActivity(new Intent(MemberCenterActivity.this, TreasureActivity.class));
                break;
            case R.id.tv_myshoucang:
                startActivity(new Intent(MemberCenterActivity.this, CollectActivity.class));
                break;
            case R.id.tv_myerweima:
                startActivity(new Intent(MemberCenterActivity.this, MyErweimaActivity.class));
                break;
            case R.id.tv_mytongxulu:
                startActivity((new Intent(MemberCenterActivity.this, DialogFragment.class)).putExtra("label", JUMP));
                break;
            case R.id.tv_myxiaoxi:
                startActivity(new Intent(MemberCenterActivity.this, MyMessageActivity.class));
                break;
            case R.id.tv_shezhi:
                startActivity(new Intent(MemberCenterActivity.this, SettingActivity.class));
                break;
            case R.id.tv_xiugai:
                startActivity((new Intent(MemberCenterActivity.this, ChangeDataActivity.class)).putExtra("label", JUMP));
                break;
            case R.id.tv_xiugaimima:
                startActivity(new Intent(MemberCenterActivity.this, ChangePwdActivity.class));
                break;
            case R.id.tv_zhuxiao:
                //弹出提示框，是否注销登录操作
                intent = new Intent(MemberCenterActivity.this, AlertDialog.class);
                intent.putExtra("msg", "是否继续注销登录操作?");
                intent.putExtra("cancel", true);
                intent.putExtra("position", 1);
                startActivityForResult(intent, CANCEL_LOGIN);
                break;
            case R.id.tv_money:
                intent = new Intent(this, MyWalletActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_honey:
                intent = new Intent(this, MyHoneyActivity.class);
                startActivity(intent);
                break;

        }
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
            if (parent.getId() == (R.id.iv_cover)) {
                TextView change = (TextView) view.findViewById(R.id.tv_change);
                change.setVisibility(View.VISIBLE);
                change.setText("修改封面");
                bt1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        photoForCover();
                        dismiss();
                    }
                });
                bt2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(
                                // 相册
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, RESULT_LOAD_COVER);
                        dismiss();

                    }
                });

            }
            if (parent.getId() == (R.id.iv_user_img)) {
                TextView change = (TextView) view.findViewById(R.id.tv_change);
                change.setVisibility(View.VISIBLE);
                change.setText("修改头像");
                bt1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        photoForUserImg();
                        dismiss();
                    }
                });
                bt2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(
                                // 相册
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, RESULT_LOAD_IMAGE);
                        dismiss();

                    }
                });
            }


            bt3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }

    /**
     * 为封面拍照
     */
    public void photoForCover() {
        try {
            Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String sdcardState = Environment.getExternalStorageState();
            String sdcardPathDir = android.os.Environment.getExternalStorageDirectory().getPath() + "/tempImage/";
            File file = null;
            if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
                // 有sd卡，是否有myImage文件夹
                File fileDir = new File(sdcardPathDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                // 是否有headImg文件
                file = new File(sdcardPathDir + System.currentTimeMillis() + ".JPG");
            }
            if (file != null) {
                path = file.getPath();
                photoUri = Uri.fromFile(file);
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(openCameraIntent, TAKE_PICTURE_COVER);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 为头像拍照
     */
    public void photoForUserImg() {
        try {
            Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String sdcardState = Environment.getExternalStorageState();
            String sdcardPathDir = android.os.Environment.getExternalStorageDirectory().getPath() + "/tempImage/";
            File file = null;
            if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
                // 有sd卡，是否有myImage文件夹
                File fileDir = new File(sdcardPathDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                // 是否有headImg文件
                file = new File(sdcardPathDir + System.currentTimeMillis() + ".JPG");
            }
            if (file != null) {
                path = file.getPath();
                photoUri = Uri.fromFile(file);
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(openCameraIntent, TAKE_PICTURE_IMAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final int TAKE_PICTURE_IMAGE = 1;
    private static final int RESULT_LOAD_IMAGE = 2;
    private static final int TAKE_PICTURE_COVER = 3;
    private static final int RESULT_LOAD_COVER = 4;
    private static final int CUT_PHOTO_REQUEST_CODE = 5;
    private static final int CUT_PHOTO_COVER_REQUEST_CODE = 6;
    private String path = "";
    private Uri photoUri;
    private float dp;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //点击Alert确定则执行注销操作
            case CANCEL_LOGIN:
                if (requestCode == CANCEL_LOGIN && resultCode == RESULT_OK) {
                    BeewayApplication.getInstance().clearsaveParam(MemberCenterActivity.this);
                    BeewayApplication.getInstance().logout(new EMCallBack() {
                        @Override
                        public void onSuccess() {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    // 重新显示登陆页面
                                    finish();
                                    startActivity(new Intent(MemberCenterActivity.this, LoginActivity.class));
                                }
                            });
                        }

                        @Override
                        public void onProgress(int progress, String status) {

                        }

                        @Override
                        public void onError(int code, String message) {

                        }
                    });
                }
                break;
            case TAKE_PICTURE_IMAGE:
                if (resultCode == -1) {// 为头像拍照
                    startPhotoZoom(photoUri);
                }
                break;
            case RESULT_LOAD_IMAGE:
                if (resultCode == RESULT_OK && null != data) {// 设置头像的相册返回
                    Uri uri = data.getData();
                    if (uri != null) {
                        startPhotoZoom(uri);
                    }
                }
                break;
            case TAKE_PICTURE_COVER:
                if (resultCode == -1) {//为封面拍照
                    getCoverPic(photoUri);
                }
                break;
            case RESULT_LOAD_COVER:
                if (resultCode == RESULT_OK && null != data) {//设置封面的相册返回
                    Uri uri = data.getData();
                    if (uri != null) {
                        getCoverPic(uri);
                    }
                }
                break;
            case CUT_PHOTO_REQUEST_CODE:
                if (resultCode == RESULT_OK && null != data) {// 裁剪返回
                    Bitmap bitmap = Bimp.getLoacalBitmap(filePath);
                    iv_user_img.setImageBitmap(bitmap);
                    bitmap = Bimp.createFramedPhoto(480, 480, bitmap,
                            (int) (dp * 1.6f));
                    bmp.add(bitmap);
                    //上传七牛
                    String key = "avatar/" + imageName;
                    uploadQiNiu(key);
                }
                break;
            case CUT_PHOTO_COVER_REQUEST_CODE:
                if (resultCode == RESULT_OK && null != data) {
                    Bitmap bitmap = Bimp.getLoacalBitmap(filePath);
                    iv_cover.setImageBitmap(bitmap);
                    bitmap = Bimp.createFramedPhoto(480, 360, bitmap,
                            (int) (dp * 1.6f));
                    bmp.add(bitmap);
                    //上传七牛
                    String key = "membg/" + imageName;
                    uploadQiNiu(key);
                }
        }
    }

    private String imageName;
    private Uri imageUri;
    private String token = null;
    private UploadManager uploadManager;
    public String filePath = null;
    public List<Bitmap> bmp = new ArrayList<Bitmap>();

    /**
     * 获得封面方法
     *
     * @param uri
     */
    private void getCoverPic(Uri uri) {
        try {
            //给选定图片名字修改成七牛封面图片名称
            imageName = BeewayApplication.getInstance().getmUserid(this) + "-back.jpg";
            if (!FileUtils.isFileExist("")) {
                FileUtils.createSDDir("");
            }
            filePath = FileUtils.SDPATH + imageName;
            imageUri = Uri.parse("file:///sdcard/formats/" + imageName);

            final Intent intent = new Intent("com.android.camera.action.CROP");
            // 照片URL地址
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 4);
            intent.putExtra("aspectY", 3);
            intent.putExtra("outputX", 480);
            intent.putExtra("outputY", 360);
            // 输出路径
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            // 输出格式
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            // 不启用人脸识别
            intent.putExtra("noFaceDetection", false);
            intent.putExtra("return-data", false);
            startActivityForResult(intent, CUT_PHOTO_COVER_REQUEST_CODE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 选定完相册图片跳转到图片裁剪页面方法
     *
     * @param uri
     * @author xieqinghua
     */
    private void startPhotoZoom(Uri uri) {
        try {
            //给选定图片名字修改成七牛头像图片名称
            imageName = BeewayApplication.getInstance().getmUserid(this) + ".jpg";
            if (!FileUtils.isFileExist("")) {
                FileUtils.createSDDir("");
            }
            filePath = FileUtils.SDPATH + imageName;
            imageUri = Uri.parse("file:///sdcard/formats/" + imageName);
            final Intent intent = new Intent("com.android.camera.action.CROP");

            // 照片URL地址
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 480);
            intent.putExtra("outputY", 480);
            // 输出路径
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            // 输出格式
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            // 不启用人脸识别
            intent.putExtra("noFaceDetection", false);
            intent.putExtra("return-data", false);
            startActivityForResult(intent, CUT_PHOTO_REQUEST_CODE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片上传七牛方法
     */
    private void uploadQiNiu(final String key) {
        uploadManager = new UploadManager();
        showDialog(LOADING_DIALOG);
        HttpUtils httpUtils = new HttpUtils();
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
//                Log.e("key", key);
//                Log.e("token", token);
//                Log.e("filePath", filePath);
                uploadManager.put(filePath, key, token, new UpCompletionHandler() {
                    @Override
                    public void complete(String s, com.qiniu.android.http.ResponseInfo responseInfo, JSONObject jsonObject) {
                        Log.e("上传状态码", responseInfo.statusCode + "");
                        if (responseInfo.statusCode == 200) {
                            Toast.makeText(MemberCenterActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            removeDialog();
                        } else {
                            //吐司七牛错误状态码
                            Toast.makeText(MemberCenterActivity.this, "上传错误" + responseInfo.statusCode, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, null);

            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtils.i(s);
            }
        });
    }

    @Override
    protected void onDestroy() {
        FileUtils.deleteDir(FileUtils.SDPATH);
        FileUtils.deleteDir(FileUtils.SDPATH1);
        // 清理图片缓存
        for (int i = 0; i < bmp.size(); i++) {
            bmp.get(i).recycle();
        }
        bmp.clear();
//        drr.clear();
        super.onDestroy();
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
     * 加载余额数据
     */
    public void WalletData() {
        showDialog(LOADING_DIALOG);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                String userid = GlobalParams.loginUserId;
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", userid);
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.MYWALLET + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        MyWalletBean myWalletBean = myWalletBean(result);
                        System.out.println("myWalletBean   " + result);
                        if (myWalletBean.getStatus() == 1) {
                            if (myWalletBean.getData().getUsefee() == null) {
                                tv_money.setText("0.00元");
                            } else {
                                tv_money.setText(myWalletBean.getData().getUsefee() + "元");
                            }
                        }
                        usejifen();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(MemberCenterActivity.this, "网络获取失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }.start();
    }

    public MyWalletBean myWalletBean(String result) {
        Gson gson = new Gson();
        MyWalletBean myWalletBean = gson.fromJson(result, MyWalletBean.class);
        return myWalletBean;
    }

    /**
     * 加载积分数据
     */
    public void usejifen() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils httpUtils = new HttpUtils();
                MyRequestParams params = new MyRequestParams();
                params.addQueryStringParameter("userid", BeewayApplication.getInstance().getmUserid(MemberCenterActivity.this));
                String url = params.myRequestParams(params);
                httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.USERJIFEN + "?" + url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //获得访问解析出来的json字符串
                        String result = responseInfo.result;
                        UserLevelBean userLevelBean = userLevelBean(result);
//                        System.out.println("myWalletBean   " + result);
                        if (userLevelBean.getStatus() == 1) {
                            tv_honey.setText(userLevelBean.getData().getUsejifen() + "蜂蜜");
                            tv_user_rank.setText(userLevelBean.getData().getLevelKey().getLvName());
                            tv_user_rank.setBackgroundResource(R.drawable.corner_view_right_white);
                        }
                        removeDialog();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        LogUtils.i(s);
                    }
                });
            }
        }.start();
    }

    public UserLevelBean userLevelBean(String result) {
        Gson gson = new Gson();
        UserLevelBean userLevelBean = gson.fromJson(result, UserLevelBean.class);
        return userLevelBean;
    }
}
