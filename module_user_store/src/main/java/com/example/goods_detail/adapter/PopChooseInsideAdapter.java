package com.example.goods_detail.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.ChooseInsideBean;

import java.util.List;

public class PopChooseInsideAdapter extends MyRecyclerAdapter<ChooseInsideBean> {
    public PopChooseInsideAdapter(Context context, List<ChooseInsideBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, ChooseInsideBean data, int position) {

    }
}
