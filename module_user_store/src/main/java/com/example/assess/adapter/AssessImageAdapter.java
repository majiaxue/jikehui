package com.example.assess.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.user_store.R;

import java.util.List;

public class AssessImageAdapter extends MyRecyclerAdapter<String> {
    public AssessImageAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        holder.setImageUrl(R.id.rv_assess_rv_img_img, data);
        if (viewOnClickListener != null) {
            viewOnClickListener.ViewOnClick(holder.getView(R.id.rv_assess_rv_img_img), position);
        }
    }
}
