package com.thvc.beeway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.easemob.chatui.activity.LoginActivity;
import com.thvc.beeway.R;
import com.thvc.beeway.adapter.ViewPagerAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.utils.SharedPreferencesUtil;
import com.thvc.beeway.widgets.DepthPageTransformer;

import java.util.ArrayList;

/**
 * 项目名称：Beeway
 * 类描述：引导页面
 * 创建人：谢庆华.
 * 创建时间：2015/8/12 14:30
 * 修改人：Administrator
 * 修改时间：2015/8/12 14:30
 * 修改备注：
 */
public class GuideActivity extends BaseActivity implements View.OnClickListener {
    //定义viewpager
    private ViewPager mViewPager;

    private Button bt_Enter;

    //引导图片资源
    private static final int[] pics = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};

    //定义一个list来存放view的集合
    private ArrayList<View> views;

    //记录当前选中位置
    private int currentIndex;

    //底部小点的图片
    private ImageView[] points;

    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        views = new ArrayList<View>();
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        bt_Enter = (Button) findViewById(R.id.bt_enter);
        //实例化Adapter
        mAdapter = new ViewPagerAdapter(views);
        initGuidePager();
    }

    /**
     * 引导页面
     */
    private void initGuidePager() {
        //定义一个布局并设置参数
        RelativeLayout.LayoutParams mParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);

        //初始化引导页面的图片
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(GuideActivity.this);
            iv.setLayoutParams(mParams);
            iv.setBackgroundResource(pics[i]);
            views.add(iv);
        }
        //设置数据
        mViewPager.setAdapter(mAdapter);
        //设置viewpager的动画效果
        mViewPager.setPageTransformer(true, new DepthPageTransformer());


        //设置监听
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                if (i == pics.length - 1) {
                    bt_Enter.setVisibility(View.VISIBLE);
                    bt_Enter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //引导页面跳转登录界面入口
                            Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                            startActivity(intent);
                            //注意这里应该做清空操作。
                            SharedPreferencesUtil.saveData(BeewayApplication.getContext(), "is_first", false);
                            finish();
                        }
                    });
                } else {
                    bt_Enter.setVisibility(View.GONE);
                }
            }

            /*页面选择的时候会调用*/
            @Override
            public void onPageSelected(int i) {
                //设置底部小点选中状态
                setCurDot(i);


            }

            @Override
            public void onPageScrollStateChanged(int i) {


            }
        });


        //初始化底部小点
        initPoint();
    }

    /**
     * 初始化底部的小点
     */
    private void initPoint() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_point);

        points = new ImageView[pics.length];


        //循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            //得到一个LinearLayout下面的每一个子元素
            points[i] = (ImageView) linearLayout.getChildAt(i);
            //默认都设为灰色
            points[i].setEnabled(true);
            //给每个小点设置监听
            points[i].setOnClickListener(this);
            //设置位置tag，方便取出与当前位置对应
            points[i].setTag(i);
        }

        //设置当面默认的位置
        currentIndex = 0;
        //设
        // 置为白色，即选中状态
        points[currentIndex].setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        setCurView(position);
        setCurDot(position);
    }

    /**
     * 设置当前的小点的位置
     */
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length - 1 || currentIndex == position) {
            return;
        }
        points[position].setEnabled(false);
        points[currentIndex].setEnabled(true);

        currentIndex = position;
    }

    /**
     * 设置当前页面的位置
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        mViewPager.setCurrentItem(position);
    }

}

