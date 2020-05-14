package com.example.group_fans.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.UserInfoBean;
import com.example.module_mine.R;

import java.util.List;

public class GroupFansRvAdapter extends MyRecyclerAdapter<UserInfoBean> {
    public GroupFansRvAdapter(Context context, List<UserInfoBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, UserInfoBean data, int position) {
        holder.setText(R.id.rv_group_fans_name, data.getNickname())
                .setText(R.id.rv_group_fans_time, "时间：" + data.getCreateTime())
                .setImageUrlCircular(R.id.rv_group_fans_img, data.getIcon())
                .setText(R.id.rv_group_fans_total_fans, data.getTotalFans() == null ? "0" : data.getTotalFans());
    }
}
