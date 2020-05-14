package com.example.classify.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.RightRecBean;
import com.example.module_home.R;

import java.util.List;

public class MyRightRecChildAdapter extends MyRecyclerAdapter<RightRecBean.ListBean> {

    public MyRightRecChildAdapter(Context context, List<RightRecBean.ListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, RightRecBean.ListBean data, int position) {
        holder.setImageResource(R.id.classify_right_child_simple, data.getIcon());
        holder.setText(R.id.classify_right_child_name, data.getName());
    }
}
