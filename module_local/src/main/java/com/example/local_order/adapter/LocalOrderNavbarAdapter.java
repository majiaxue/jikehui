package com.example.local_order.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.TxtAndChooseBean;
import com.example.module_local.R;

import java.util.List;

public class LocalOrderNavbarAdapter extends MyRecyclerAdapter<TxtAndChooseBean> {
    public LocalOrderNavbarAdapter(Context context, List<TxtAndChooseBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, TxtAndChooseBean data, int position) {
        holder.setText(R.id.rv_local_order_navbar_txt, data.getTitle());
        if (data.isChoose()) {
            holder.getView(R.id.rv_local_order_navbar_bottom).setVisibility(View.VISIBLE);
            holder.setTextColor(R.id.rv_local_order_navbar_txt, Color.parseColor("#fb5318"));
        } else {
            holder.getView(R.id.rv_local_order_navbar_bottom).setVisibility(View.INVISIBLE);
            holder.setTextColor(R.id.rv_local_order_navbar_txt, Color.parseColor("#999999"));
        }
    }
}
