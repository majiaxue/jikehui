package com.example.shop_home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ShopHomeVPAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> dataList;

    public ShopHomeVPAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> dataList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.dataList = dataList;
    }
    public ShopHomeVPAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
//        this.dataList = dataList;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return dataList.get(position);
    }
}
