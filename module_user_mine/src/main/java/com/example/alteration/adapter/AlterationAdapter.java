package com.example.alteration.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.AlterationBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class AlterationAdapter extends MyRecyclerAdapter<AlterationBean> {

    public AlterationAdapter(Context context, List<AlterationBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, AlterationBean data, int position) {
        holder.setText(R.id.alteration_rec_shop_name, data.getSellerName());
        if (0 == data.getReturnType()) {
            //退货退款
            holder.setText(R.id.alteration_rec_type, "退货退款");
        } else if (1 == data.getReturnType()) {
            //未收货
            holder.setText(R.id.alteration_rec_type, "未收货");
        } else {
            //只退款
            holder.setText(R.id.alteration_rec_type, "只退款");
        }
        //申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝
        if (0 == data.getBackStatus()) {
            holder.setText(R.id.alteration_rec_status, "等待商家处理");
        } else if (1 == data.getBackStatus()) {
            holder.setText(R.id.alteration_rec_status, "退货中");
        } else if (2 == data.getBackStatus()) {
            holder.setText(R.id.alteration_rec_status, "退款成功");
        } else if (3 == data.getBackStatus()) {
            holder.setText(R.id.alteration_rec_status, "商家已拒绝");
        } else if (4 == data.getBackStatus()) {
            holder.setText(R.id.alteration_rec_status, "退款申请已取消");
        }

        viewOnClickListener.ViewOnClick(holder.getView(R.id.alteration_rec_view_details), position);

        RecyclerView alterationChildRec = holder.getView(R.id.alteration_child_rec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        alterationChildRec.setLayoutManager(linearLayoutManager);
        AlterationChildAdapter alterationChildAdapter = new AlterationChildAdapter(context, data.getItemlist(), R.layout.item_alteration_child_rec);
        alterationChildRec.setAdapter(alterationChildAdapter);

    }
}
