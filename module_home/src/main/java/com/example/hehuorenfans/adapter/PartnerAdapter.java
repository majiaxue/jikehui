package com.example.hehuorenfans.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.PartnerListBean;
import com.example.module_home.R;

import java.util.List;

public class PartnerAdapter extends MyRecyclerAdapter<PartnerListBean> {
    public PartnerAdapter(Context context, List<PartnerListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, PartnerListBean data, int position) {
        holder.setText(R.id.rv_group_fans_name, data.getNickname())
                .setText(R.id.rv_group_fans_time, "时间：" + data.getCreateTime())
                .setImageUrlCircular(R.id.rv_group_fans_img, data.getIcon())
                .setText(R.id.rv_group_fans_total_fans, data.getTotalFans() == null ? "0" : data.getTotalFans());
    }
}
