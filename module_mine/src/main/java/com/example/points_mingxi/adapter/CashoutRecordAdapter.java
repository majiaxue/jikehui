package com.example.points_mingxi.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.PointsCashoutRecordBean;
import com.example.module_mine.R;

import java.util.List;

public class CashoutRecordAdapter extends MyRecyclerAdapter<PointsCashoutRecordBean> {
    public CashoutRecordAdapter(Context context, List<PointsCashoutRecordBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, PointsCashoutRecordBean data, int position) {
        holder.setText(R.id.rv_cashout_record_money, data.getIntegration())
                .setText(R.id.rv_cashout_record_time, data.getDealTime());
    }
}
