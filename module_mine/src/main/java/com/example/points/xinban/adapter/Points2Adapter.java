package com.example.points.xinban.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_mine.R;
import com.example.bean.MingXiBean;

import java.util.List;

public class Points2Adapter extends MyRecyclerAdapter<MingXiBean.IntegralHistoryListBean> {
    public Points2Adapter(Context context, List<MingXiBean.IntegralHistoryListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MingXiBean.IntegralHistoryListBean data, int position) {
        holder.setText(R.id.item_gou_xiao,data.getNote())
                .setText(R.id.item_gou_time,data.getCreateTime())
                .setText(R.id.item_gou_kouqian,data.getIntegration());
        if (data.getType()==8){
            holder.setImageResource(R.id.img_xiao,R.drawable.chong);
            holder.setText(R.id.item_gou_kouqian,"+"+data.getIntegration());
            holder.setText(R.id.item_gou_ok,"充值成功");
        }else {
            holder.setImageResource(R.id.img_xiao,R.drawable.xiao);

        }
    }
}
