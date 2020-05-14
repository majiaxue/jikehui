package com.example.operator_gain.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_home.R;

import java.util.List;

public class OperatorGainFactorAdapter extends MyRecyclerAdapter<String> {
    public OperatorGainFactorAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        holder.setTextFormHtml(R.id.rv_vp_operator_factor_txt, data);
    }
}
