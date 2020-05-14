package com.example.mine.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.BaseRecImageAndTextBean;
import com.example.module_home.R;
import com.example.utils.LogUtil;

import java.util.List;

public class MyToolAdapter extends MyRecyclerAdapter<BaseRecImageAndTextBean> {

    public MyToolAdapter(Context context, List<BaseRecImageAndTextBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, BaseRecImageAndTextBean data, int position) {
        holder.setText(R.id.rv_mytool_txt, data.getName())
                .setImageResource(R.id.rv_mytool_img, data.getImage());

        viewOnClickListener.ViewOnClick(holder.getView(R.id.rv_mytool_parent), position);
    }
}
