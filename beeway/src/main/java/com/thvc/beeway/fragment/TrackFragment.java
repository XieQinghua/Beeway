package com.thvc.beeway.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thvc.beeway.R;
import com.thvc.beeway.activity.MainActivity;
import com.thvc.beeway.activity.SearchActivity;
import com.thvc.beeway.adapter.TrackFFpagerAdapter;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.base.BaseActivity;
import com.thvc.beeway.http.UrlPools;
import com.thvc.beeway.utils.VolleyHepler;
import com.thvc.beeway.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * com.thvc.fragment
 * 创建日期： 2015/8/12.
 * 版权：天合融创
 * 作者：.
 * 版本号：1.0.
 * 修改历史：
 */
public class TrackFragment extends BaseActivity {
    private List<Fragment> fragments;

    private ViewPager viewPager;
    private CircleImageView imageView;
    private ImageView search;
    private TextView[] titles, views;
    private LinearLayout layout_text, layout_view;
    private TextView nickname;
    private Context context = this;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_track_fragment);
        init();
        initview();
        initTitles();

    }


    private void initview() {
        // TODO Auto-generated method stub
        fragments = new ArrayList<Fragment>();
        Bundle bundle = new Bundle();
        Fragment fragment1 = new MainTrackFragment();
        bundle.putInt("tabIndex", 0);
        fragment1.setArguments(bundle);

        Fragment fragment2 = new MainTrackFragment();
        bundle = new Bundle();
        bundle.putInt("tabIndex", 1);
        fragment2.setArguments(bundle);

        Fragment fragment3 = new MainTrackFragment();
        bundle = new Bundle();
        bundle.putInt("tabIndex", 2);
        fragment3.setArguments(bundle);

        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        TrackFFpagerAdapter trackFFpagerAdapter = new TrackFFpagerAdapter(
                getSupportFragmentManager(), fragments);
        viewPager.setAdapter(trackFFpagerAdapter);

        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 3; i++) {
                    titles[i].setTextColor(Color.WHITE);
                    views[i].setBackgroundColor(getResources().getColor(R.color.beeway_title_bule));
                }
                titles[position].setTextColor(Color.WHITE);
                views[position].setBackgroundColor(Color.WHITE);
                MainActivity.mOpen(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }

        });
    }

    public void initTitles() {
        titles = new TextView[3];
        views = new TextView[3];
        for (int i = 0; i < titles.length; i++) {
            titles[i] = (TextView) layout_text.getChildAt(i);
            views[i] = (TextView) layout_view.getChildAt(i);
            titles[i].setTextColor(Color.WHITE);
            views[i].setBackgroundColor(getResources().getColor(R.color.beeway_title_bule));
            titles[i].setTag(i);
            views[i].setTag(i);
            titles[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem((Integer) v.getTag());
                }
            });
        }
        titles[0].setTextColor(Color.WHITE);
        views[0].setBackgroundColor(Color.WHITE);
    }

    public void init() {
        layout_text = (LinearLayout) findViewById(R.id.track_fragment_linearlayout_text);
        layout_view = (LinearLayout) findViewById(R.id.track_fragment_linearlayout_view);
        viewPager = (ViewPager) findViewById(R.id.track_fragment_viewpager);
        search = (ImageView) findViewById(R.id.iv_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SearchActivity.class);
                startActivity(intent);
            }
        });
        imageView = (CircleImageView) findViewById(R.id.iv_user_img);

        imageView.setImageUrl(UrlPools.QINIU + "avatar/" + new BeewayApplication().getmUserid(context) + ".jpg", VolleyHepler.getInstance().getImageLoader());

        nickname = (TextView) findViewById(R.id.tv_title);
        nickname.setVisibility(View.GONE);
        imageView.setImageUrl(UrlPools.QINIU + "avatar/" + new BeewayApplication().getmUserid(context)+".jpg", VolleyHepler.getInstance().getImageLoader());

        //打开侧滑菜单
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mMenu();
            }
        });
    }
}
