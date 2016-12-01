package com.thvc.beeway.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * com.thvc.beeway.adapter
 * 创建日期： 2015/8/13.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class TrackFFpagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;
    public void setList(List<Fragment> fragments){
        this.fragments=fragments;
    }
    public TrackFFpagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        // TODO Auto-generated method stub
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return  fragments.size();
    }

}
