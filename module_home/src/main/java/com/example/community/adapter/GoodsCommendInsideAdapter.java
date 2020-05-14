package com.example.community.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_home.R;

import java.util.List;

public class GoodsCommendInsideAdapter extends MyRecyclerAdapter<String> {
    public GoodsCommendInsideAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        holder.setImageUrl(R.id.rv_goods_commend_inside_img, data);
    }
}
