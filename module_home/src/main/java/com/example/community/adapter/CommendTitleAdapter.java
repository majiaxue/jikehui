package com.example.community.adapter;

import android.content.Context;
import android.graphics.Color;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.TitleBean;
import com.example.module_home.R;

import java.util.List;

public class CommendTitleAdapter extends MyRecyclerAdapter<TitleBean> {
    public CommendTitleAdapter(Context context, List<TitleBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, TitleBean data, int position) {
        holder.setText(R.id.rv_commend_title_txt, data.getContent());
        if (data.isCheck()) {
            holder.setTextColor(R.id.rv_commend_title_txt, Color.parseColor("#fc5917"));
        } else {
            holder.setTextColor(R.id.rv_commend_title_txt, Color.parseColor("#333333"));
        }
    }
}
