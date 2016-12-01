package com.thvc.beeway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.thvc.beeway.R;
import com.thvc.beeway.base.BaseActivity;

/**
 * Created by Administrator on 2015/9/22.
 */
public class TreasureTishiActivity extends BaseActivity{

    private ImageView iv_relback;
    private TextView tv_user_tishi1,tv_user_tishi2,tv_user_tishi3,tv_user_tishi4,tv_user_tishi5,tv_user_tishi6,tv_user_tishi7,tv_user_tishi8,tv_user_tishi9;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tishi);

        inint();
    }

    public void inint(){
        iv_relback =(ImageView)this.findViewById(R.id.iv_relback);
        iv_relback.setOnClickListener(new MyOnClickListener());
        tv_user_tishi1 = (TextView)this.findViewById(R.id.tv_user_tishi1);
        tv_user_tishi1.setOnClickListener(new MyOnClickListener());
        tv_user_tishi2 = (TextView)this.findViewById(R.id.tv_user_tishi2);
        tv_user_tishi2.setOnClickListener(new MyOnClickListener());
        tv_user_tishi3 = (TextView)this.findViewById(R.id.tv_user_tishi3);
        tv_user_tishi3.setOnClickListener(new MyOnClickListener());
        tv_user_tishi4 = (TextView)this.findViewById(R.id.tv_user_tishi4);
        tv_user_tishi4.setOnClickListener(new MyOnClickListener());
        tv_user_tishi5 = (TextView)this.findViewById(R.id.tv_user_tishi5);
        tv_user_tishi5.setOnClickListener(new MyOnClickListener());
        tv_user_tishi6 = (TextView)this.findViewById(R.id.tv_user_tishi6);
        tv_user_tishi6.setOnClickListener(new MyOnClickListener());
        tv_user_tishi7 = (TextView)this.findViewById(R.id.tv_user_tishi7);
        tv_user_tishi7.setOnClickListener(new MyOnClickListener());
        tv_user_tishi8 = (TextView)this.findViewById(R.id.tv_user_tishi8);
        tv_user_tishi8.setOnClickListener(new MyOnClickListener());
        tv_user_tishi9 = (TextView)this.findViewById(R.id.tv_user_tishi9);
        tv_user_tishi9.setOnClickListener(new MyOnClickListener());

    }
    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_relback:
                    TreasureTishiActivity.this.finish();
                    break;
                case R.id.tv_user_tishi1:
                    //数据是使用Intent返回
                    Intent intent = new Intent();
                    //把返回数据存入Intent
                    intent.putExtra("result", tv_user_tishi1.getText().toString().trim());
                    //设置返回数据
                    TreasureTishiActivity.this.setResult(RESULT_OK, intent);
                    //关闭Activity
                    TreasureTishiActivity.this.finish();
                    break;
                case R.id.tv_user_tishi2:
                    //数据是使用Intent返回
                    Intent intent1 = new Intent();
                    //把返回数据存入Intent
                    intent1.putExtra("result", tv_user_tishi2.getText().toString().trim());
                    //设置返回数据
                    TreasureTishiActivity.this.setResult(RESULT_OK, intent1);
                    //关闭Activity
                    TreasureTishiActivity.this.finish();
                    break;
                case R.id.tv_user_tishi3:
                    //数据是使用Intent返回
                    Intent intent2 = new Intent();
                    //把返回数据存入Intent
                    intent2.putExtra("result", tv_user_tishi3.getText().toString().trim());
                    //设置返回数据
                    TreasureTishiActivity.this.setResult(RESULT_OK, intent2);
                    //关闭Activity
                    TreasureTishiActivity.this.finish();
                    break;
                case R.id.tv_user_tishi4:
                    //数据是使用Intent返回
                    Intent intent3 = new Intent();
                    //把返回数据存入Intent
                    intent3.putExtra("result", tv_user_tishi4.getText().toString().trim());
                    //设置返回数据
                    TreasureTishiActivity.this.setResult(RESULT_OK, intent3);
                    //关闭Activity
                    TreasureTishiActivity.this.finish();
                    break;
                case R.id.tv_user_tishi5:
                    //数据是使用Intent返回
                    Intent intent4 = new Intent();
                    //把返回数据存入Intent
                    intent4.putExtra("result", tv_user_tishi5.getText().toString().trim());
                    //设置返回数据
                    TreasureTishiActivity.this.setResult(RESULT_OK, intent4);
                    //关闭Activity
                    TreasureTishiActivity.this.finish();
                    break;
                case R.id.tv_user_tishi6:
                    //数据是使用Intent返回
                    Intent intent5 = new Intent();
                    //把返回数据存入Intent
                    intent5.putExtra("result", tv_user_tishi6.getText().toString().trim());
                    //设置返回数据
                    TreasureTishiActivity.this.setResult(RESULT_OK, intent5);
                    //关闭Activity
                    TreasureTishiActivity.this.finish();
                    break;
                case R.id.tv_user_tishi7:
                    //数据是使用Intent返回
                    Intent intent6 = new Intent();
                    //把返回数据存入Intent
                    intent6.putExtra("result", tv_user_tishi7.getText().toString().trim());
                    //设置返回数据
                    TreasureTishiActivity.this.setResult(RESULT_OK, intent6);
                    //关闭Activity
                    TreasureTishiActivity.this.finish();
                    break;
                case R.id.tv_user_tishi8:
                    //数据是使用Intent返回
                    Intent intent7 = new Intent();
                    //把返回数据存入Intent
                    intent7.putExtra("result", tv_user_tishi8.getText().toString().trim());
                    //设置返回数据
                    TreasureTishiActivity.this.setResult(RESULT_OK, intent7);
                    //关闭Activity
                    TreasureTishiActivity.this.finish();
                    break;
                case R.id.tv_user_tishi9:
                    //数据是使用Intent返回
                    Intent intent8 = new Intent();
                    //把返回数据存入Intent
                    intent8.putExtra("result", tv_user_tishi9.getText().toString().trim());
                    //设置返回数据
                    TreasureTishiActivity.this.setResult(RESULT_OK, intent8);
                    //关闭Activity
                    TreasureTishiActivity.this.finish();
                    break;
            }

        }
    }


}
