package com.example.local_home.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_local.R;

import java.util.List;

public class ZhongBannerAdapter extends MyRecyclerAdapter<String> {
    public ZhongBannerAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        holder.setText(R.id.rv_textview_txt, data);
    }
}
