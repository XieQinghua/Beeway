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
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.thvc.beeway.R;
import com.thvc.beeway.Release.AddTravelDao;
import com.thvc.beeway.Release.AddTravelText;
import com.thvc.beeway.Release.FengYouAddtravelImge;
import com.thvc.beeway.Release.FengYouAddtravelImgeDao;
import com.thvc.beeway.Release.FengYouAddtravelText;
import com.thvc.beeway.Release.FengYouAddtravelTextDao;
import com.thvc.beeway.Release.FengyouFootprintImage;
import com.thvc.beeway.Release.FengyouFootprintImageDao;
import com.thvc.beeway.Release.FengyouFootprintText;
import com.thvc.beeway.Release.FengyouFootprintTextDao;
import com.thvc.beeway.Release.MessageCenterDao;
import com.thvc.beeway.adapter.AddFootprintbeanAdapter;
import com.thvc.beeway.adapter.AddTravelListJsonAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.bean.AddFootprintbean;
import com.thvc.beeway.bean.AddTraveLlistJsonBean;
import com.thvc.beeway.bean.AddTravelBean;
import com.thvc.beeway.bean.AddTravelBeans;
import com.thvc.beeway.bean.AddTravelJsonArrayBean;
import com.thvc.beeway.bean.TokenBean;
import com.thvc.beeway.bean.TravelBean;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.CustomConstants;
import com.thvc.beeway.utils.MyRequestParams;
import com.thvc.beeway.utils.MyStatic;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 项目名称：Beeway
 * 类描述：游记Activity
 * 创建人：谢庆华.
 * 创建时间：2015/9/1 9:40
 * 修改人：Administrator
 * 修改时间：2015/9/1 9:40
 * 修改备注：
 */
public class AddTravelActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_back;
    private ImageView rlay_cover;
    private CircleImageView iv_user_img;
    private TextView im_change;
    private EditText ed_title, ed_cost, ed_feel;
    private Button btn_addfootprint;
    private AlertDialog alertDialog;
    private TextView tv_publish, tv_nick_name;

    private static final int PHOTO_WITH_CAMERA = 37;// 拍摄照片
    private static final int PHOTO_WITH_DATA = 18;  //从SD卡中得到图片
    private static final int ADD_FOOTPRINT = 1;
    private String key = "";

    private View CustomView;
    private String listjson;


    private Boolean isBackground = true;

    private String footprintid;
    private String userid;
    private String jsonobj;
    private String sourcePath;
    private UploadManager uploadManager;
    private String token = null;

    private HttpUtils httpUtils;

    private String title, money, description, trackid;
    private String strDate;
    private ListView add_footsteps_list;

    private ArrayList<AddFootprintbean> list;
    private String sourcePaths, titles, cost, feel;
    private ArrayList<AddTraveLlistJsonBean.ListEntity> lists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel);
        initView();
        userid = new BeewayApplication().getmUserid(this);

        list = (ArrayList<AddFootprintbean>) getIntent().getSerializableExtra("list");
        if (list != null && list.size() > 0) {
            AddFootprintbeanAdapter addFootprintbeanAdapter = new AddFootprintbeanAdapter(list, this);
            add_footsteps_list.setAdapter(addFootprintbeanAdapter);
        }

        sourcePaths = getIntent().getStringExtra("sourcePath");
        titles = getIntent().getStringExtra("title");
        cost = getIntent().getStringExtra("cost");
        feel = getIntent().getStringExtra("feel");

        if (sourcePaths == null || titles == null || cost == null || feel == null) {

        } else {
            if (!sourcePaths.equals("")) {
                rlay_cover.setImageBitmap(BitmapFactory.decodeFile(sourcePaths));
            }
            if (!titles.equals("")) {
                ed_title.setText(titles);
            }
            if (!cost.equals("")) {
                ed_cost.setText(cost);
            }
            if (!feel.equals("")) {
                ed_feel.setText(feel);
            }
        }
        getAddtavel();
    }

    /**
     * 查询数据库保存的数据
     */
    public void getAddtavel() {
        AddTravelDao addTravelDao = new AddTravelDao(AddTravelActivity.this);
        String footprintids = "1";
        AddTravelText addTravelText = addTravelDao.select(userid, footprintids);
        if (addTravelText != null) {
            String printid = addTravelText.getFootprintid();
            String content = addTravelText.getContent();
            if (printid.equals("1")) {
                AddTraveLlistJsonBean llistJsonBean = getLlistJsonBean(content);
                ed_title.setText(llistJsonBean.getTitles());
                ed_cost.setText(llistJsonBean.getCost());
                ed_feel.setText(llistJsonBean.getFeel());
                rlay_cover.setImageBitmap(BitmapFactory.decodeFile(llistJsonBean.getSourcePaths()));
                lists = (ArrayList<AddTraveLlistJsonBean.ListEntity>) llistJsonBean.getList();
                if (lists != null) {
                    AddTravelListJsonAdapter addFootprintbeanAdapter = new AddTravelListJsonAdapter(lists, this);
                    add_footsteps_list.setAdapter(addFootprintbeanAdapter);
                }
                AddTravelDao addTravelDao1 = new AddTravelDao(AddTravelActivity.this);
                addTravelDao1.update(userid, "0");

            }
        }
    }


    /**
     * 查询数据库json字段的解析
     *
     * @param result
     * @return
     */
    private AddTraveLlistJsonBean getLlistJsonBean(String result) {
        Gson gson = new Gson();
        AddTraveLlistJsonBean addTravelBean = gson.fromJson(result, AddTraveLlistJsonBean.class);
        return addTravelBean;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        iv_back = (ImageView) this.findViewById(R.id.iv_back);//返回按鈕
        iv_back.setOnClickListener(this);//返回按鈕点击事件
        rlay_cover = (ImageView) this.findViewById(R.id.rlay_cover);//封面
        iv_user_img = (CircleImageView) this.findViewById(R.id.iv_user_img);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        ViewGroup.LayoutParams para;
        para = rlay_cover.getLayoutParams();
        para.height = wm.getDefaultDisplay().getWidth() * 3 / 4;
        para.width = wm.getDefaultDisplay().getWidth();
        rlay_cover.setLayoutParams(para);
        im_change = (TextView) this.findViewById(R.id.im_change);//更换封面按钮
        im_change.setOnClickListener(this);//更换封面点击事件
        ed_title = (EditText) this.findViewById(R.id.ed_title);//输入游记标题的输入框
        ed_cost = (EditText) this.findViewById(R.id.ed_cost);//输入旅游费用的输入框
        ed_feel = (EditText) this.findViewById(R.id.ed_feel);//输入旅游感想输入框
        btn_addfootprint = (Button) this.findViewById(R.id.btn_addfootprint);//添加足迹按钮
        btn_addfootprint.setOnClickListener(this);
        tv_publish = (TextView) this.findViewById(R.id.tv_publish);//发布按钮
        tv_publish.setOnClickListener(this);
        tv_nick_name = (TextView) this.findViewById(R.id.tv_nick_name);
        tv_nick_name.setText(BeewayApplication.getInstance().getmNickname(this));
        //设置头像
        iv_user_img.setImageUrl(UrlPools.getAvatarUrl(this), VolleyHepler.getInstance().getImageLoader());
        add_footsteps_list = (ListView) this.findViewById(R.id.add_footsteps_list);
    }

    /**
     * 页面上的所有点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                title = ed_title.getText().toString().trim();
                money = ed_cost.getText().toString().trim();
                description = ed_feel.getText().toString().trim();
                if (title.equals("") && money.equals("") && description.equals("") && sourcePath == null && list == null) {
                    back(iv_back);//点击返回事件
                } else if (!title.equals("") || money.equals("") || description.equals("") || !sourcePath.equals("") || list.size() > 0) {
                    AlertDialog();
                }
                break;
            case R.id.im_change://点击更换封面事件
                showCustomAlertDialog();
                break;
            case R.id.btn_addfootprint://点击添加足迹事件
                Intent intent = new Intent(AddTravelActivity.this, MyFootstepsActivity.class);
                String title = ed_title.getText().toString().trim();
                String cost = ed_cost.getText().toString().trim();
                String feel = ed_feel.getText().toString().trim();
                intent.putExtra("sourcePath", sourcePath);
                intent.putExtra("title", title);
                intent.putExtra("cost", cost);
                intent.putExtra("feel", feel);
                startActivity(intent);
                AddTravelActivity.this.finish();
                break;
            case R.id.tv_publish:
                removeTempFromPref();
                getDatas();
                break;
        }
    }

    public void getDatas() {
        title = ed_title.getText().toString().trim();
        money = ed_cost.getText().toString().trim();
        description = ed_feel.getText().toString().trim();
        trackid = "";
        if (isBackground) {
            Toast.makeText(AddTravelActivity.this, "请跟换封面再发布", Toast.LENGTH_SHORT).show();
            return;
        } else if (title.equals("")) {
            Toast.makeText(AddTravelActivity.this, "请输入游记标题", Toast.LENGTH_SHORT).show();
            return;
        } else if (money.equals("")) {
            Toast.makeText(AddTravelActivity.this, "请输入旅游费用", Toast.LENGTH_SHORT).show();
            return;
        } else if (description.equals("")) {
            Toast.makeText(AddTravelActivity.this, "请输入旅游感想", Toast.LENGTH_SHORT).show();
            return;
        }
        tv_publish.setVisibility(View.GONE);

//
        AddTravelJsonArrayBean addTrackJsonArrayBean = new AddTravelJsonArrayBean(title, money, description, trackid);
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        jsonobj = gson.toJson(addTrackJsonArrayBean);

        Setinsert();
    }

    public void Setinsert() {
        footprintid = String.valueOf(getTaskId() + System.currentTimeMillis());
        String pathlist = "";
        String getstatus = String.valueOf(0);

        SimpleDateFormat formatters = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        Date curDates = new Date(System.currentTimeMillis());//获取当前时间
        strDate = formatters.format(curDates);

        FengYouAddtravelText fengyouFootprint = new FengYouAddtravelText(userid, footprintid, jsonobj, pathlist, getstatus, strDate);
        FengYouAddtravelTextDao fengYouAddtravelTextDao = new FengYouAddtravelTextDao(AddTravelActivity.this);
        fengYouAddtravelTextDao.textinsert(fengyouFootprint);


        FengYouAddtravelImge fengYouAddtravelImge = new FengYouAddtravelImge(userid, footprintid, sourcePath, key, getstatus, strDate);
        FengYouAddtravelImgeDao fengYouAddtravelImgeDao = new FengYouAddtravelImgeDao(AddTravelActivity.this);
        fengYouAddtravelImgeDao.imginsert(fengYouAddtravelImge);
        getToken();
    }

    private void getToken() {
        FengYouAddtravelImgeDao fengYouAddtravelImgeDao = new FengYouAddtravelImgeDao(AddTravelActivity.this);
        FengYouAddtravelImge fengyouImage = fengYouAddtravelImgeDao.select(userid, footprintid);
        final String key = fengyouImage.getContent().toString();
        final String filePath = fengyouImage.getFilePath().toString();

        uploadManager = new UploadManager();
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
                Log.e("此次上传的token值", token);
                uploadManager.put(filePath, key, token, new UpCompletionHandler() {
                    @Override
                    public void complete(String s, com.qiniu.android.http.ResponseInfo responseInfo, JSONObject jsonObject) {
                        if (responseInfo.statusCode == 200) {
                            moveTaskToBack(false);
                            String statuss = String.valueOf(3);
                            FengYouAddtravelImgeDao addtravelImgeDao = new FengYouAddtravelImgeDao(AddTravelActivity.this);
                            addtravelImgeDao.imgupdate(userid, footprintid, statuss);
                            postDate();
                            Toast.makeText(AddTravelActivity.this, "完成上传", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddTravelActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
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
        FengYouAddtravelTextDao fengYouAddtravelTextDao = new FengYouAddtravelTextDao(AddTravelActivity.this);
        FengYouAddtravelText footprintText = fengYouAddtravelTextDao.select(userid, footprintid);
        String userid = footprintText.getUserid();
        String filekeys = key;
        httpUtils = new HttpUtils();
        String isend = String.valueOf(1);
        String public_status = String.valueOf(2);

        String trackid = MyStatic.trackid;
        if (trackid != null && !"".equals(trackid)) {
            trackid.substring(0, trackid.length() - 1);
        }
        MyRequestParams params = new MyRequestParams();//定义访问服务器参数
        params.addQueryStringParameter("userid", userid);
        params.addQueryStringParameter("description", description);
        params.addQueryStringParameter("filekeys", filekeys);
        params.addQueryStringParameter("title", title);
        params.addQueryStringParameter("money", money);
        params.addQueryStringParameter("isend", isend);
        params.addQueryStringParameter("trackid", trackid);
        params.addQueryStringParameter("addtime", strDate);
        params.addQueryStringParameter("modtime", strDate);
        params.addQueryStringParameter("public_status", public_status);
        String url = params.myRequestParams(params);
        httpUtils.send(HttpRequest.HttpMethod.POST, UrlPools.ADD_TRAVEL + "?" + url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                AddTravelBean addTravelBean = paraseAddTrackDate(result);
                if (addTravelBean.getStatus() == 1) {
                    Toast.makeText(AddTravelActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                    AddTravelActivity.this.finish();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(AddTravelActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * AddTravelBean接口返回json字段的解析
     *
     * @param result
     * @return
     */
    private AddTravelBean paraseAddTrackDate(String result) {
        Gson gson = new Gson();
        AddTravelBean addTravelBean = gson.fromJson(result, AddTravelBean.class);
        return addTravelBean;
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
                            rlay_cover.setImageBitmap(smallBitmap);
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
                    key = String.valueOf("travel/" + System.currentTimeMillis()) + sourcePath.substring(sourcePath.lastIndexOf("/") + 1);
                    cursor.close();
                    rlay_cover.setImageBitmap(BitmapFactory.decodeFile(sourcePath));
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
        fileName = "travel/" + System.currentTimeMillis() + ".jpg";
        return fileName;
    }

    /**
     * 保存图片到本应用下
     **/
    private void savePicture(String fileName, Bitmap bitmap) {

        FileOutputStream fos = null;
        try {//直接写入名称即可，没有会被自动创建；私有：只有本应用才能访问，重新内容写入会被覆盖
            fos = AddTravelActivity.this.openFileOutput(fileName, Context.MODE_PRIVATE);
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

    /**
     * 监听放回按钮
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //假如按的回退键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            title = ed_title.getText().toString().trim();
            money = ed_cost.getText().toString().trim();
            description = ed_feel.getText().toString().trim();

            title = ed_title.getText().toString().trim();
            money = ed_cost.getText().toString().trim();
            description = ed_feel.getText().toString().trim();
            if (title.equals("") && money.equals("") && description.equals("") && sourcePath == null && list == null) {
                back(iv_back);//点击返回事件
            } else if (!title.equals("") || money.equals("") || description.equals("") || sourcePath.equals("") || list.size() > 0) {
                AlertDialog();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //弹出对话框
    public void AlertDialog() {
        AlertDialog.Builder builder = myBuilder(AddTravelActivity.this);
        final AlertDialog dialog = builder.show();
        //点击屏幕外侧，dialog不消失
        dialog.setCanceledOnTouchOutside(false);
        Button ortherbtnemil = (Button) CustomView.findViewById(R.id.ortherbtnemil);
        ortherbtnemil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                AddTravelActivity.this.finish();
            }
        });
        Button ortherbtnweb = (Button) CustomView.findViewById(R.id.ortherbtnweb);
        ortherbtnweb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                preservation();
                AddTravelActivity.this.finish();
            }
        });

    }

    protected AlertDialog.Builder myBuilder(AddTravelActivity dialogWindows) {
        final LayoutInflater inflater = this.getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(dialogWindows);
        CustomView = inflater.inflate(R.layout.dialog, null);
        return builder.setView(CustomView);
    }


    /**
     * 保存游记数据
     */
    public void preservation() {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        title = ed_title.getText().toString().trim();
        money = ed_cost.getText().toString().trim();
        description = ed_feel.getText().toString().trim();
        sourcePaths = sourcePath.toString();


        if (title.equals("")) {
            title = "";
        }
        if (money.equals("")) {
            money = "";
        }
        if (description.equals("")) {
            description = "";
        }
        if (sourcePaths.equals("")) {
            sourcePaths = "";
        }
        AddTravelBeans addTravelBeans = new AddTravelBeans(sourcePaths, title, money, description, list);
        String content = gson.toJson(addTravelBeans);

        AddTravelText travelText = new AddTravelText(userid, "1", content, "");
        AddTravelDao addTravelDao = new AddTravelDao(AddTravelActivity.this);
        addTravelDao.insert(travelText);
    }

}
