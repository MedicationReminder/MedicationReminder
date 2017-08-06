package com.renqi.takemedicine.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.renqi.takemedicine.activity.OnlineConsultingActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xu Wei on 2017/8/5.
 */

public class TabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private ArrayList<String> titleList;

    public TabAdapter(FragmentManager fm, ArrayList<String> titleList, List<Fragment> fragments) {
        super(fm);
        this.titleList=titleList;
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //设置tablayout标题
    @Override
    public CharSequence getPageTitle(int position) {

        return titleList.get(position);
    }

}
