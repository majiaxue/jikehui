package com.example.points_mingxi.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.PointsMingxiBean;
import com.example.module_mine.R;

import java.util.List;

public class PointsRecordAdapter extends MyRecyclerAdapter<PointsMingxiBean> {
    public PointsRecordAdapter(Context context, List<PointsMingxiBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, PointsMingxiBean data, int position) {
        holder.setText(R.id.rv_points_record_money, data.getIntegration() + "（" + data.getNote() + "）")
                .setText(R.id.rv_points_record_time, data.getCreateTime())
                .setText(R.id.rv_points_record_total, data.getUserIntegration());
    }
}
