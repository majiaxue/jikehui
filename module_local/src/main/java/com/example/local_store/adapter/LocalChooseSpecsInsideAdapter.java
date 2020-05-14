package com.example.local_store.adapter;

import android.content.Context;
import android.graphics.Color;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.TxtAndChooseBean;
import com.example.module_local.R;
import com.example.utils.LogUtil;

import java.util.List;

public class LocalChooseSpecsInsideAdapter extends MyRecyclerAdapter<TxtAndChooseBean> {
    public LocalChooseSpecsInsideAdapter(Context context, List<TxtAndChooseBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, TxtAndChooseBean data, int position) {
        holder.setText(R.id.rv_pop_choose_specs_inside_txt, data.getTitle());
        if (data.isChoose()) {
            holder.setTextColor(R.id.rv_pop_choose_specs_inside_txt, Color.parseColor("#fb5318"))
                    .setBackgroundColor(R.id.rv_pop_choose_specs_inside_txt, Color.parseColor("#fde8e1"));
        } else {
            holder.setTextColor(R.id.rv_pop_choose_specs_inside_txt, Color.parseColor("#333333"))
                    .setBackgroundColor(R.id.rv_pop_choose_specs_inside_txt, Color.parseColor("#f1f1f1"));
        }
    }
}
