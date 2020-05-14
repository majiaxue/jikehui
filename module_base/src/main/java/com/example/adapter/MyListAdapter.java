package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class MyListAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mList;
    protected int mLayoutId;

    public MyListAdapter(Context mContext, List<T> mList, int mLayoutId) {
        this.mContext = mContext;
        this.mList = mList;
        this.mLayoutId = mLayoutId;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder holder = ListViewHolder.getInstance(mContext, convertView, mLayoutId, parent, position);
        convert(holder, getItem(position), position);
        return holder.getConvertView();
    }

    protected abstract void convert(ListViewHolder holder, T item, int position);
}
