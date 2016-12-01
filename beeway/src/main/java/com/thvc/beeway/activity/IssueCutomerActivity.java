package com.thvc.beeway.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.thvc.beeway.Release.FengYouIssueCutnmerImge;
import com.thvc.beeway.Release.FengYouIssueCutnmerImgeDao;
import com.thvc.beeway.Release.FengYouIssueCutnmerText;
import com.thvc.beeway.Release.FengYouIssueCutnmerTextDao;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.AddTrackBean;
import com.thvc.beeway.bean.IssueCutomerBean;
import com.thvc.beeway.bean.TokenBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.CustomConstants;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.view.CustomProgressDialog;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/9/6.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class IssueCutomerActivity extends BaseActivity {
    private Context context;
    private String title = "", company = "";
    private TextView issuecutomer_title, issuecutomer_liyou, tv_clean, head2_address, im_change;
    private RelativeLayout issuecutomer_relative1;
    private EditText issuecutomer_edit, issuecutomer_reason;
    private ImageView img_issuecut, head2_back;


    private static final int PHOTO_WITH_CAMERA = 37;// 拍摄照片
    private static final int PHOTO_WITH_DATA = 18;  //从SD卡中得到图片
    private String key = "";
    private Boolean isBackground = true;
    private AlertDialog alertDialog;

    private String jsonobj;
    private String iden;
    private String footprintid;
    private String userid;
    private String issedit;
    private String issreason;

    private String token = null;
    private String sourcePath;
    private UploadManager uploadManager;

    private HttpUtils httpUtils;
    private int count = 0;
    private CustomProgressDialog pd;



    String ed_text[] = {
            "我想去帕劳看火山，求喷~~~", "好莱坞请我拍电影没有路费5~~~", "报告司令官，没有裤子穿~~~",
            "穷游睡天桥，求赞助一个枕头", "慕尼黑的啤酒节没有钱买酒是不是好惨，给点酒钱啊~~~ ",
            "卖艺赚雪橇，有想听两只老虎的吗？", "贫僧怀有去西天取经之愿，不知施主是否可助我一臂之力。",
            "为了去北京，已经坐了一个星期的挖掘机，求车费带我上京", "去迪拜捡垃圾，行行好给点路费吧~",
            "没了你，世界寸步难行；知道你，会给我毛爷爷", "好想去西西里海享受日光浴，阳光会太好赏点零钱买把太阳伞~",
            "让零钱的暴风雨带我去热带雨林探险吧", "有钱人知道你任性 任性的给我一次旅行吧 拜托拜托",
            "来我的旅行计划中插一脚把", "给我的旅行“化”上一笔", "雪中送炭的你在哪里", "一般般的旅游，一般般的我，一般般的赏钱；快到碗里来。",
            "子欲出行，奈何钱少，如若宽裕，赏我可好。", "我爱着旅游，我深爱着旅游，但是没有钱", "旅行没有那么多借口，打赏没有那么多理由", "心有多大，路就有多宽；钱有多少，你就有多好"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_issuecutomer);
        context = this;
        Intent intent = getIntent();
        title = intent.getStringExtra("Title");
        company = intent.getStringExtra("Company");
        userid = new BeewayApplication().getmUserid(this);
        init();

    }

    public void init() {
        issuecutomer_title = (TextView) findViewById(R.id.issuecutomer_title);
        issuecutomer_reason = (EditText) this.findViewById(R.id.issuecutomer_reason);
        issuecutomer_edit = (EditText) this.findViewById(R.id.issuecutomer_edit);
        issuecutomer_relative1 = (RelativeLayout) this.findViewById(R.id.issuecutomer_relative1);
        issuecutomer_relative1.setOnClickListener(new MyOnClickListener());
        issuecutomer_liyou = (TextView) this.findViewById(R.id.issuecutomer_liyou);
        im_change = (TextView) this.findViewById(R.id.im_change);
        issuecutomer_liyou.setOnClickListener(new MyOnClickListener());
        tv_clean = (TextView) this.findViewById(R.id.tv_clean);
        tv_clean.setOnClickListener(new MyOnClickListener());
        img_issuecut = (ImageView) this.findViewById(R.id.img_issuecut);
        im_change.setOnClickListener(new MyOnClickListener());
        head2_back = (ImageView) this.findViewById(R.id.head2_back);
        head2_back.setOnClickListener(new MyOnClickListener());
        head2_address = (TextView) this.findViewById(R.id.head2_address);
        head2_address.setOnClickListener(new MyOnClickListener());
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        ViewGroup.LayoutParams para;
        para = img_issuecut.getLayoutParams();
        para.height = wm.getDefaultDisplay().getWidth()*3/4;
        para.width = wm.getDefaultDisplay().getWidth();
        img_issuecut.setLayoutParams(para);
    }

    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.issuecutomer_relative1:
                    String editcutomer = issuecutomer_edit.getText().toString().trim();
                    String reason = issuecutomer_reason.getText().toString().trim();
                    String mark = "众筹";
                    Intent intent = new Intent(IssueCutomerActivity.this, ScenicActivity.class);
                    intent.putExtra("mark", mark);
                    intent.putExtra("editcutomer", editcutomer);
                    intent.putExtra("reason", reason);
                    startActivity(intent);
                    IssueCutomerActivity.this.finish();
                    break;
                case R.id.issuecutomer_liyou:
                    count++;
                    if (count > 20) {
                        count = 1;
                    }
                    issuecutomer_reason.setText(ed_text[count]);
                    break;
                case R.id.tv_clean:
                    issuecutomer_reason.setText("");
                    break;
                case R.id.head2_back:
                    IssueCutomerActivity.this.finish();
                    break;
                case R.id.head2_address:
                    removeTempFromPref();
                    getDatas();
                    break;
                case R.id.im_change:
                    showCustomAlertDialog();
                    break;
            }

        }

        public void getDatas() {
            String title = issuecutomer_title.getText().toString().trim();
            issedit = issuecutomer_edit.getText().toString().trim();
            issreason = issuecutomer_reason.getText().toString().trim();
            if (title.equals("你要去哪里？")) {
                Toast.makeText(IssueCutomerActivity.this, "请选择你想去的地方再发布", Toast.LENGTH_SHORT).show();
                return;
            } else if (issedit.equals("")) {
                Toast.makeText(IssueCutomerActivity.this, "请输入筹集目标再发布", Toast.LENGTH_SHORT).show();
                return;
            } else if (issreason.equals("")) {
                Toast.makeText(IssueCutomerActivity.this, "请输入众筹理由再发布", Toast.LENGTH_SHORT).show();
                return;
            } else if (isBackground) {
                Toast.makeText(IssueCutomerActivity.this, "请选择封面图片在发布", Toast.LENGTH_SHORT).show();
                return;
            }
            pd = CustomProgressDialog.createDialog(IssueCutomerActivity.this);
            pd.show();
            //点击屏幕外侧，dialog不消失
            pd.setCanceledOnTouchOutside(false);
            IssueCutomerBean addTrackJsonArrayBean = new IssueCutomerBean(company, issedit, issreason);
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            jsonobj = gson.toJson(addTrackJsonArrayBean);

            /**
             *获取手机的状态码
             */
            TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            String simSerialNumber = tm.getSimSerialNumber();
            String androidId = android.provider.Settings.Secure.getString(
                    getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) imei.hashCode() << 32) | simSerialNumber.hashCode());
            iden = deviceUuid.toString();
            Setinsert();
        }

    }

    public void Setinsert() {
        footprintid = String.valueOf(getTaskId() + System.currentTimeMillis());
        String pathlist = "";
        String getstatus = String.valueOf(0);

        SimpleDateFormat formatters = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        Date curDates = new Date(System.currentTimeMillis());//获取当前时间
        String strDates = formatters.format(curDates);

        FengYouIssueCutnmerText fengYouIssueCutnmerText = new FengYouIssueCutnmerText(userid, footprintid, jsonobj, pathlist, getstatus, strDates);
        FengYouIssueCutnmerTextDao fengYouIssueCutnmerTextDao = new FengYouIssueCutnmerTextDao(IssueCutomerActivity.this);
        fengYouIssueCutnmerTextDao.textinsert(fengYouIssueCutnmerText);

        FengYouIssueCutnmerImge fengYouIssueCutnmerImge = new FengYouIssueCutnmerImge(userid, footprintid, sourcePath, key, getstatus, strDates);
        FengYouIssueCutnmerImgeDao fengYouIssueCutnmerImgeDao = new FengYouIssueCutnmerImgeDao(IssueCutomerActivity.this);
        fengYouIssueCutnmerImgeDao.imginsert(fengYouIssueCutnmerImge);

        getToken();
    }

    private void getToken() {
        FengYouIssueCutnmerImgeDao fengYouIssueCutnmerImgeDao = new FengYouIssueCutnmerImgeDao(IssueCutomerActivity.this);
        FengYouIssueCutnmerImge fengYouIssueCutnmerImge = fengYouIssueCutnmerImgeDao.select(userid, footprintid);
        final String key = fengYouIssueCutnmerImge.getContent().toString();
        final String filePath = fengYouIssueCutnmerImge.getFilePath().toString();

        uploadManager = new UploadManager();
        HttpUtils httpUtils = new HttpUtils();
//        String url = String.format(UrlPools.GETTOKEN, key);
        MyRequestParams params = new MyRequestParams();//定义访问服务器参数
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
                            String statuss = String.valueOf(3);
                            FengYouIssueCutnmerImgeDao fengYouIssueCutnmerImgeDao1 = new FengYouIssueCutnmerImgeDao(IssueCutomerActivity.this);
                            fengYouIssueCutnmerImgeDao1.imgupdate(userid, filePath, statuss);
                            postDate();
                            Toast.makeText(IssueCutomerActivity.this, "完成上传", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(IssueCutomerActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
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
    }

    private void postDate() {
        /**
         * 查询数据库上传数据到服务器
         */
        FengYouIssueCutnmerTextDao fengYouIssueCutnmerTextDao = new FengYouIssueCutnmerTextDao(IssueCutomerActivity.this);
        FengYouIssueCutnmerText fengYouIssueCutnmerText = fengYouIssueCutnmerTextDao.select(userid, footprintid);
        String userid = fengYouIssueCutnmerText.getUserid();
        String filekeys = key;
        httpUtils = new HttpUtils();
        String public_status = String.valueOf(2);
//        String url = UrlPools.ISSUECUTOMER;
        MyRequestParams params = new MyRequestParams();//定义访问服务器参数
        params.addQueryStringParameter("userid", userid);
        params.addQueryStringParameter("content", issreason);
        params.addQueryStringParameter("filekeys", filekeys);
        params.addQueryStringParameter("company", company);
        params.addQueryStringParameter("sprice", issedit);
        params.addQueryStringParameter("public_status", public_status);
//        params.addQueryStringParameter("iden", iden);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.ISSUECUTOMER + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                AddTrackBean addTrackBean = paraseAddTrackDate(result);
                if (addTrackBean.getStatus() == 1) {
                    Toast.makeText(IssueCutomerActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                    removeDialog();
                    IssueCutomerActivity.this.finish();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(IssueCutomerActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
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

    private void removeTempFromPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        sp.edit().remove(CustomConstants.PREF_TEMP_IMAGES).commit();
    }


    private void showCustomAlertDialog() {
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();
        Window win = alertDialog.getWindow();

        WindowManager.LayoutParams lp = win.getAttributes();
        win.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        lp.alpha = 0.7f;
        win.setAttributes(lp);
        win.setContentView(R.layout.item_popupwindows);

        Button cancelBtn = (Button) win.findViewById(R.id.item_popupwindows_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //取消选择
                alertDialog.cancel();
            }
        });

        Button camera_phone = (Button) win.findViewById(R.id.item_popupwindows_Photo);
        camera_phone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //相册选择图片
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, PHOTO_WITH_DATA);
            }
        });

        Button camera_camera = (Button) win.findViewById(R.id.item_popupwindows_camera);
        camera_camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //调用系统相机
                Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
                //指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                //直接使用，没有缩小
                sourcePath = imageUri.getPath().toString().trim();
                startActivityForResult(intent, PHOTO_WITH_CAMERA);  //用户点击了从相机获取
            }
        });
    }

    /**
     * You will receive this call immediately before onResume() when your activity is re-starting.
     **/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {  //返回成功
            switch (requestCode) {
                case PHOTO_WITH_CAMERA: {//拍照获取图片
                    String status = Environment.getExternalStorageState();
                    if (status.equals(Environment.MEDIA_MOUNTED)) { //是否有SD卡
                        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/image.jpg");
                        key = createPhotoFileName();
                        //写一个方法将此文件保存到本应用下面啦
                        savePicture(key, bitmap);
                        if (bitmap != null) {
                            //为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
                            Bitmap smallBitmap = zoomBitmap(bitmap, bitmap.getWidth() / 1, bitmap.getHeight() / 1);
                            img_issuecut.setImageBitmap(smallBitmap);
                            alertDialog.dismiss();
                            isBackground = false;
                        }
                    } else {
                    }
                    break;
                }
                case PHOTO_WITH_DATA: {//从图库中选择图片
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    sourcePath = cursor.getString(columnIndex);
                    key = String.valueOf("want/" + System.currentTimeMillis()) + sourcePath.substring(sourcePath.lastIndexOf("/") + 1);
                    cursor.close();
                    img_issuecut.setImageBitmap(BitmapFactory.decodeFile(sourcePath));
                    alertDialog.dismiss();
                    isBackground = false;
                    break;

                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 创建图片不同的文件名
     **/
    private String createPhotoFileName() {
        String fileName = "";
        fileName = "want/" + System.currentTimeMillis() + ".jpg";
        return fileName;
    }

    /**
     * 保存图片到本应用下
     **/
    private void savePicture(String fileName, Bitmap bitmap) {

        FileOutputStream fos = null;
        try {//直接写入名称即可，没有会被自动创建；私有：只有本应用才能访问，重新内容写入会被覆盖
            fos = IssueCutomerActivity.this.openFileOutput(fileName, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);// 把图片写入指定文件夹中

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fos) {
                    fos.close();
                    fos = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Resize the bitmap
     *
     * @param bitmap
     * @param width
     * @param height
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }


}
