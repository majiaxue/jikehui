package com.example.local_mingxi.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;

import java.util.List;

public class LocalMingxiAdapter extends MyRecyclerAdapter<String> {
    public LocalMingxiAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {

    }
}
