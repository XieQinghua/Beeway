package com.thvc.beeway.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.thvc.beeway.R;
import com.thvc.beeway.base.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * com.thvc.beeway.activity
 * 创建日期： 2015/9/16.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class JSImageActivity extends BaseActivity {
    private Context context = this;
    public ImageView js_image, back;
    public ImageLoader imageLoader;
    private List<String> list;
    private List<String> id;
    private ViewPager js_viewPager;
    private TextView js_save,js_count;
    public RelativeLayout js_layout, js_layout2;
    private String TYPE = "", myJpgPath= "";
    private int page=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.trackdetails_js_image);
        js_image = (ImageView) findViewById(R.id.js_image);
        back = (ImageView) findViewById(R.id.js_back);
        js_save = (TextView) findViewById(R.id.js_save);
        js_count = (TextView) findViewById(R.id.js_count);
        js_layout = (RelativeLayout) findViewById(R.id.js_layout);
        js_layout2 = (RelativeLayout) findViewById(R.id.js_layout2);
        js_viewPager = (ViewPager) findViewById(R.id.js_viewpager);

        imageLoader = ImageLoader.getInstance();
        Intent intent = getIntent();
        TYPE = intent.getStringExtra("TYPE");
        if (TYPE.equals("photo")) {
            js_image.setVisibility(View.GONE);
            js_layout2.setVisibility(View.VISIBLE);
            js_viewPager.setVisibility(View.VISIBLE);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            js_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "正在保存", Toast.LENGTH_SHORT).show();
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            String urlPath = "http://7xj45w.com1.z0.glb.clouddn.com/" + list.get(page);
                            Bitmap tmpBitmap = null;
                            try {
                                InputStream is = new java.net.URL(urlPath).openStream();
                                tmpBitmap = BitmapFactory.decodeStream(is);
                                saveMyBitmap("myBitmap", tmpBitmap);
//                                bit = getBitmapByPath("myBitmap");
                                is.close();
                                handler.sendEmptyMessage(1);
                                Log.e("saveMyBitmap","保存成功");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            });
            list = intent.getStringArrayListExtra("url");
            id = intent.getStringArrayListExtra("id");
            myJpgPath= Environment.getExternalStorageDirectory()+"/pepper/" +id.get(0)+ ".png";
            js_viewPager.setAdapter(new MyPagerAdapter());
            js_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    js_count.setText((position + 1) + "/" + list.size());
                    page = position;
                    myJpgPath= Environment.getExternalStorageDirectory()+"/pepper/" + id.get(position)+".png";
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }
    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();

            }
        }

    };
    /*
	 * 自定义pageradapter  适配viewpager
	 */
    public class MyPagerAdapter extends PagerAdapter {
        /* 表示当前适配器中得数据得总条目
         */
        @Override
        public int getCount() {
            return list.size();
        }

        /**
         * 根据指定的下标 创建viewpager中展示的item  返回当前page中的view对象
         * 第一个参数表示 当前管理page的视图组
         * 第二个参数表示 指定下标
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageview = new ImageView(getApplicationContext());
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            String url = "http://7xj45w.com1.z0.glb.clouddn.com/" + list.get(position);
            System.out.println(url);
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.imgurl)         //加载开始默认的图片
                    .showImageForEmptyUri(R.drawable.imgurl)     //url爲空會显示该图片，自己放在drawable里面的
                    .showImageOnFail(R.drawable.imgurl)                //加载图片出现问题，会显示该图片
                    .cacheInMemory()                                               //缓存用
                    .cacheOnDisc()                                                    //缓存用
                    .build();
            imageLoader.displayImage(url, imageview, options);
            ((ViewPager) container).addView(imageview);
            return imageview;
        }

        /**
         * 根据指定的下标移除视图组中的view对象
         * 第一个参数表示 视图组对象
         * 第二个参数表示 当前移除的视图的下标
         * 第三个参数表示 instantiateItem 返回的object对象
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);//根据指定的下标获得当前移除的view对象 然后在视图组中移除
        }

        /**
         * 表示判断viewpager中展示的view对象与instantiateItem对象是否时同一个对象
         */
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

    }

    /**
     * 保存图片到SD卡
     *
     * @param bitName
     * @param mBitmap
     * @throws IOException
     * @return void
     * @since v 1.0
     */
    public void saveMyBitmap(String bitName, Bitmap mBitmap)
            throws IOException {
        File tmp = new File("/sdcard/pepper/");
        if (!tmp.exists()) {
            tmp.mkdir();
        }
        File f = new File(myJpgPath);
        f.createNewFile();
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
            fOut.close();
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(tmp);
            intent.setData(uri);
            sendBroadcast(intent);//这个广播的目的就是更新图库

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
