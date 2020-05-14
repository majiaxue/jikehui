package com.example.utils.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.OnViewListener;

import java.util.List;

public class VPBigPicAdapter extends PagerAdapter {
    private List<View> dataList;
    private OnViewListener listener;

    public VPBigPicAdapter(List<View> dataList, OnViewListener listener) {
        this.dataList = dataList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = dataList.get(position);
        container.addView(view);
        listener.setOnViewListener(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(dataList.get(position));
    }
}
