package com.example.adapter;

import android.content.Context;

import com.example.module_base.R;

import java.util.List;

public class GoodsImageAdapter extends MyRecyclerAdapter<String> {
    public GoodsImageAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        holder.setImageUrl(R.id.rv_goods_choose_img, data);
    }
}
