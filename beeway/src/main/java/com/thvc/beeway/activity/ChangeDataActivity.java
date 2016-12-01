package com.thvc.beeway.activity;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.easemob.chatui.Constant;
import com.easemob.chatui.activity.LoginActivity;
import com.easemob.chatui.activity.RegisterActivity;
import com.easemob.chatui.db.UserDao;
import com.easemob.chatui.domain.User;
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
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.MyUserDetailsBean;
import com.thvc.beeway.bean.TokenBean;
import com.thvc.beeway.bean.UserInfo;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.photos.Bimp;
import com.thvc.beeway.photos.FileUtils;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：Beeway
 * 类描述：修改个人资料页面
 * 创建人：谢庆华.
 * 创建时间：2015/9/29 13:56
 * 修改人：Administrator
 * 修改时间：2015/9/29 13:56
 * 修改备注：
 */
public class ChangeDataActivity extends BaseActivity {
    private final static String TAG = "ChangeDataActivity";
    private CircleImageView iv_user_img;
    private ImageView iv_back;
    private EditText et_nickname, et_content, et_realname, et_email, et_wechat, et_qq, et_weibo;
    private TextView tv_title, tv_user_sex, tv_datetime;
    private String nickname, content, sex, realname, birthday, email, wechat, qq, weibo;
    private static final int sleepTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_data);
        init();
    }

    private void init() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_user_img = (CircleImageView) findViewById(R.id.iv_user_img);
        et_nickname = (EditText) findViewById(R.id.et_nickname);
        et_content = (EditText) findViewById(R.id.et_content);
        et_realname = (EditText) findViewById(R.id.et_realname);
        et_email = (EditText) findViewById(R.id.et_email);
        et_wechat = (EditText) findViewById(R.id.et_wechat);
        et_qq = (EditText) findViewById(R.id.et_qq);
        et_weibo = (EditText) findViewById(R.id.et_weibo);
        tv_user_sex = (TextView) findViewById(R.id.tv_user_sex);
        tv_datetime = (TextView) findViewById(R.id.tv_datetime);
        iv_user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PopupWindows(ChangeDataActivity.this, v);
            }
        });
        iv_user_img.setImageUrl(UrlPools.getAvatarUrl(this), VolleyHepler.getInstance().getImageLoader());
        et_nickname.setText(BeewayApplication.getInstance().getmNickname(this));
        et_nickname.setSelection(et_nickname.getText().length());
        et_content.setText(BeewayApplication.getInstance().getContent(this));
        et_realname.setText(BeewayApplication.getInstance().getRealname(this));
        et_email.setText(BeewayApplication.getInstance().getEmall(this));
        et_wechat.setText(BeewayApplication.getInstance().getWechat(this));
        et_qq.setText(BeewayApplication.getInstance().getQq(this));
        et_weibo.setText(BeewayApplication.getInstance().getWeibo(this));
        tv_datetime.setText(BeewayApplication.getInstance().getBirthday(this));
        if ((BeewayApplication.getInstance().getSex(this)).equals("1")) {
            tv_user_sex.setText("男");
        } else {
            tv_user_sex.setText("女");
        }
        if (getIntent().getStringExtra("label").equals(MemberCenterActivity.JUMP)) {
            tv_title.setText("修改个人资料");
        }
        if (getIntent().getStringExtra("label").equals(RegisterActivity.REGISTER_JUMP)) {
            iv_back.setVisibility(View.GONE);
            et_nickname.setText("");
            tv_user_sex.setText("");
            tv_title.setText("完善个人资料");
        }

    }

    /**
     * 弹出对话框，弹出性别选择框和日期选择对话框
     *
     * @param view
     */
    String[] items = {"男", "女"};
    int index = 0;

    public void showDialog(final View view) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        switch (view.getId()) {
            case R.id.tv_user_sex:
                //选择性别对话框
                builder.setSingleChoiceItems(items, index, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        index = which;
                    }
                });

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_user_sex.setText(items[index]);
                    }
                });
                builder.show();
                break;
            case R.id.tv_datetime:
                //日期选择对话框
                DatePickerDialog dpd = new DatePickerDialog(ChangeDataActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                tv_datetime.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dpd.show();
                break;
        }
    }

    /**
     * 完成按钮
     *
     * @param view
     */
    public void commit(View view) {

        nickname = et_nickname.getText().toString().trim();
        content = et_content.getText().toString().trim();
        realname = et_realname.getText().toString().trim();
        email = et_email.getText().toString().trim();
        wechat = et_wechat.getText().toString().trim();
        qq = et_qq.getText().toString().trim();
        weibo = et_weibo.getText().toString().trim();
        birthday = tv_datetime.getText().toString().trim();
        if (tv_user_sex.getText().toString().trim().equals("男")) {
            sex = 1 + "";
        } else {
            sex = 2 + "";
        }
        showDialog(LOADING_DIALOG);
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("userid", BeewayApplication.getInstance().getmUserid(this));
        params.addQueryStringParameter("nickname", nickname);
        params.addQueryStringParameter("sex", sex);
        params.addQueryStringParameter("content", content);
        params.addQueryStringParameter("realname", realname);
        params.addQueryStringParameter("birthday", birthday);
        params.addQueryStringParameter("email", email);
        params.addQueryStringParameter("wechat", wechat);
        params.addQueryStringParameter("qq", qq);
        params.addQueryStringParameter("weibo", weibo);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.EDITDATA + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
//                Log.e(TAG, result);
                MyUserDetailsBean myUserDetailsBean = paraseMyUserDetailsBean(result);
                //数据保存到应用
                BeewayApplication.getInstance().saveParam(ChangeDataActivity.this,
                        BeewayApplication.getInstance().getmUserName(ChangeDataActivity.this),
                        BeewayApplication.getInstance().getUserPassword(ChangeDataActivity.this),
                        true,
                        BeewayApplication.getInstance().getmUserid(ChangeDataActivity.this),
                        myUserDetailsBean.getData().getNickname(),
                        myUserDetailsBean.getData().getSex(),
                        myUserDetailsBean.getData().getContent(),
                        myUserDetailsBean.getData().getRealname(),
                        myUserDetailsBean.getData().getBirthday(),
                        myUserDetailsBean.getData().getEmail(),
                        myUserDetailsBean.getData().getWechat(),
                        myUserDetailsBean.getData().getQq(),
                        myUserDetailsBean.getData().getWeibo());
                //头像上传七牛
                if (filePath != null) {
                    uploadQiNiu();
                }
                if (getIntent().getStringExtra("label").equals(MemberCenterActivity.JUMP)) {
                    Toast.makeText(ChangeDataActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    ChangeDataActivity.this.finish();
                }
                if (getIntent().getStringExtra("label").equals(RegisterActivity.REGISTER_JUMP)) {
                    //执行登录操作
                    login(BeewayApplication.getInstance().getmUserName(ChangeDataActivity.this), BeewayApplication.getInstance().getUserPassword(ChangeDataActivity.this));
                }
                removeDialog();

            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtils.i(s);
            }
        });
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    private void login(String username, String password) {
        showDialog(LOADING_DIALOG);
        HttpUtils httpUtils = new HttpUtils();
        MyRequestParams params = new MyRequestParams();
        params.addQueryStringParameter("username", username);
        params.addQueryStringParameter("password", password);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.LOGIN_HOME + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
//                LogUtils.i(responseInfo.result);
                UserInfo userInfo = paraseUserInfoData(result);

                if (userInfo.getStatus() == 1) {
                    loginHuanXin();
                } else {
                    startActivity(new Intent(ChangeDataActivity.this, LoginActivity.class));
                    Toast.makeText(ChangeDataActivity.this, "登录聊天服务器失败", Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //访问失败
                LogUtils.i(msg);
            }
        });

    }

    /**
     * 登录环信
     */
    private void loginHuanXin() {
        // 调用sdk登陆方法登陆聊天服务器，需要参数用户UseId+密码123456
        EMChatManager.getInstance().login(BeewayApplication.getInstance().getmUserid(this), "123456", new EMCallBack() {
            @Override
            public void onSuccess() {
                try {
                    // ** 第一次登录或者之前logout后再登录，加载所有本地群和回话
                    // ** manually load all local groups and
                    EMGroupManager.getInstance().loadAllGroups();
                    EMChatManager.getInstance().loadAllConversations();
                    // 处理好友和群组
                    initializeContacts();
                    LogUtils.i("登录成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    // 取好友或者群聊失败，不让进入主页面
                    runOnUiThread(new Runnable() {
                        public void run() {
                            BeewayApplication.getInstance().logout(null);
                            Toast.makeText(getApplicationContext(), R.string.login_failure_failed, Toast.LENGTH_LONG).show();
                        }
                    });
                    return;
                }
                // 更新当前用户的nickname 此方法的作用是在ios离线推送时能够显示用户nick
                boolean updatenick = EMChatManager.getInstance().updateCurrentUserNick(
                        BeewayApplication.currentUserNick.trim());
                if (!updatenick) {
                    Log.e(TAG, "update current user nick fail");
                }
                if (!ChangeDataActivity.this.isFinishing()) {

                }
                // 进入主页面
                Intent intent = new Intent(ChangeDataActivity.this, com.thvc.beeway.activity.MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(final int code, final String message) {
//                if (!progressShow) {
//                    return;
//                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), getString(R.string.Login_failed) + message,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    /**
     * 处理好友和群组
     */
    private void initializeContacts() {
        Map<String, User> userlist = new HashMap<String, User>();
        // 添加user"申请与通知"
        User newFriends = new User();
        newFriends.setUsername(Constant.NEW_FRIENDS_USERNAME);
        String strChat = getResources().getString(
                R.string.Application_and_notify);
        newFriends.setNick(strChat);

        userlist.put(Constant.NEW_FRIENDS_USERNAME, newFriends);
        // 添加"群聊"
        User groupUser = new User();
        String strGroup = getResources().getString(R.string.group_chat);
        groupUser.setUsername(Constant.GROUP_USERNAME);
        groupUser.setNick(strGroup);
        groupUser.setHeader("");
        userlist.put(Constant.GROUP_USERNAME, groupUser);

        // 存入内存
        BeewayApplication.getInstance().setContactList(userlist);
        // 存入db
        UserDao dao = new UserDao(ChangeDataActivity.this);
        List<User> users = new ArrayList<User>(userlist.values());
        dao.saveContactList(users);
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
                    photo();
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
            bt3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }

    private static final int TAKE_PICTURE = 1;
    private static final int RESULT_LOAD_IMAGE = 2;
    private static final int CUT_PHOTO_REQUEST_CODE = 3;
    private String path = "";
    private Uri photoUri;

    public void photo() {
        try {
            Intent openCameraIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);

            String sdcardState = Environment.getExternalStorageState();
            String sdcardPathDir = android.os.Environment
                    .getExternalStorageDirectory().getPath() + "/tempImage/";
            File file = null;
            if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
                // 有sd卡，是否有myImage文件夹
                File fileDir = new File(sdcardPathDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                // 是否有headImg文件
                file = new File(sdcardPathDir + System.currentTimeMillis()
                        + ".JPG");
            }
            if (file != null) {
                path = file.getPath();
                photoUri = Uri.fromFile(file);
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(openCameraIntent, TAKE_PICTURE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == -1) {// 拍照
                    startPhotoZoom(photoUri);
                }
                break;
            case RESULT_LOAD_IMAGE:
                if (resultCode == RESULT_OK && null != data) {// 相册返回
                    Uri uri = data.getData();
                    if (uri != null) {
                        startPhotoZoom(uri);
                    }
                }
                break;
            case CUT_PHOTO_REQUEST_CODE:
                if (resultCode == RESULT_OK && null != data) {// 裁剪返回
                    Bitmap bitmap = Bimp.getLoacalBitmap(filePath);
                    iv_user_img.setImageBitmap(bitmap);

                }
                break;
        }
    }

    private String imageName;
    private Uri imageUri;
    private String token = null;
    private UploadManager uploadManager;
    public List<Bitmap> bmp = new ArrayList<Bitmap>();
    //    public List<String> drr = new ArrayList<String>();
    public String filePath = null;

    /**
     * 图片上传七牛方法
     */
    private void uploadQiNiu() {
        uploadManager = new UploadManager();
        HttpUtils httpUtils = new HttpUtils();
        final String key = "avatar/" + imageName;
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
                Log.e("修改头像的key", key);
                Log.e("修改头像的token", token);
//                Log.e("修改头像的filePath", drr.get(drr.size() - 1));
                Log.e("修改头像的filePath", filePath);
                uploadManager.put(filePath, key, token, new UpCompletionHandler() {
                    @Override
                    public void complete(String s, com.qiniu.android.http.ResponseInfo responseInfo, JSONObject jsonObject) {
                        Log.e("上传状态码", responseInfo.statusCode + "");
                        if (responseInfo.statusCode == 200) {
                            Log.e(TAG, "修改头像成功");
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

    private void startPhotoZoom(Uri uri) {
        try {
            imageName = BeewayApplication.getInstance().getmUserid(this) + ".jpg";
            if (!FileUtils.isFileExist("")) {
                FileUtils.createSDDir("");
            }
//            drr.add(FileUtils.SDPATH + key + ".JPEG");
//            drr.add(FileUtils.SDPATH + imageName);
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


    private UserInfo paraseUserInfoData(String result) {
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(result, UserInfo.class);
        return userInfo;
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
     * 修改个人资料成功返回json字段解析
     *
     * @param result
     */
    public MyUserDetailsBean paraseMyUserDetailsBean(String result) {
        Gson gson = new Gson();
        MyUserDetailsBean myUserDetailsBean = gson.fromJson(result, MyUserDetailsBean.class);
        return myUserDetailsBean;
    }
}
