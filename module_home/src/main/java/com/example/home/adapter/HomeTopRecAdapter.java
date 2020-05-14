package com.example.home.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.BaseRecImageAndTextBean;
import com.example.module_home.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class HomeTopRecAdapter extends MyRecyclerAdapter<BaseRecImageAndTextBean> {

    public HomeTopRecAdapter(Context context, List mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, BaseRecImageAndTextBean data, int position) {
        holder.setText(R.id.home_top_rec_text, data.getName());
        holder.setImageResource(R.id.home_top_rec_image,data.getImage());

    }



}
