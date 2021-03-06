package com.thvc.beeway.Zing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.chatui.utils.UserUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.thvc.beeway.R;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;

import java.util.Hashtable;

/**
 * 项目名称：Beeway
 * 类描述：我的二维码页面
 * 创建人：谢庆华.
 * 创建时间：2015/9/29 11:04
 * 修改人：Administrator
 * 修改时间：2015/9/29 11:04
 * 修改备注：
 */
public class MyErweimaActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_user_img, iv_erweima;
    private TextView tv_saoyisao, tv_user_name, tv_content;
    private int QR_WIDTH = 240, QR_HEIGHT = 240;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myerweima);
        iv_user_img = (ImageView) findViewById(R.id.iv_user_img);
        iv_erweima = (ImageView) findViewById(R.id.iv_erweima);
        //设置方形头像
        UserUtils.setUserAvatar(this, BeewayApplication.getInstance().getmUserid(this), iv_user_img);
        tv_saoyisao = (TextView) findViewById(R.id.tv_saoyisao);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content.setText(BeewayApplication.getInstance().getContent(this));
        tv_user_name.setText(BeewayApplication.getInstance().getmNickname(this));
        tv_saoyisao.setOnClickListener(this);
        String url = "fy://userid/" + BeewayApplication.getInstance().getmUserid(this);
        createQRImage(url);
    }


    /**
     * @param url //要转换的地址或字符串,可以是中文
     * @方法功能说明: 生成二维码图片, 实际使用时要初始化ImageView, 不然会报空指针错误
     */
    public void createQRImage(String url) {
        try {
            //判断URL合法性
            if (url == null || "".equals(url) || url.length() < 1) {
                return;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    } else {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            //显示到一个ImageView上面
            iv_erweima.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_saoyisao:
                Intent intent = new Intent(MyErweimaActivity.this, MipcaActivityCapture.class);
                startActivity(intent);
                break;
        }
    }
}
