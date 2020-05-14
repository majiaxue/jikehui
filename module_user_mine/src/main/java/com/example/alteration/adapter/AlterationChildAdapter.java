package com.example.alteration.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.AlterationBean;
import com.example.module_user_mine.R;

import java.util.List;

public class AlterationChildAdapter extends MyRecyclerAdapter<AlterationBean.ItemlistBean> {

    public AlterationChildAdapter(Context context, List<AlterationBean.ItemlistBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, AlterationBean.ItemlistBean data, int position) {
        holder.setImageFresco(R.id.alteration_child_rec_image, data.getProductPic());
        holder.setText(R.id.alteration_child_rec_name, data.getProductName());
        holder.setText(R.id.alteration_child_rec_count, "X" + data.getProductQuantity());
        holder.setText(R.id.alteration_child_rec_Attr, data.getProductAttr());
    }
}
