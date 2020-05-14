package com.example.operator.adapter;

import android.content.Context;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.OperatorBean;
import com.example.entity.YysFactorBean;
import com.example.module_mine.R;

import java.util.List;

public class YysFactorAdapter extends MyRecyclerAdapter<OperatorBean> {
    public YysFactorAdapter(Context context, List<OperatorBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, OperatorBean data, int position) {
        holder.setText(R.id.rv_yys_title, data.getName())
                .setText(R.id.rv_yys_remark, data.getNote() == null ? "备注：暂无" : "备注：" + data.getNote());
        if ("0".equals(data.getUpType())) {
            holder.getView(R.id.rv_yys_factor1).setVisibility(View.GONE);
            holder.setText(R.id.rv_yys_txt, "条件一：")
                    .setText(R.id.rv_yys_price, data.getPrice() + "元");
        } else if ("1".equals(data.getUpType())) {
            holder.getView(R.id.rv_yys_factor2).setVisibility(View.GONE);
            holder.setText(R.id.rv_yys_content, getFactor(data));
        } else if ("2".equals(data.getUpType())) {
            holder.setText(R.id.rv_yys_content, getFactor(data))
                    .setText(R.id.rv_yys_price, data.getPrice() + "元");
        }

//        if (viewOnClickListener != null) {
//            viewOnClickListener.ViewOnClick(holder.getView(R.id.rv_yys_topay), position);
//        }

        if (viewTwoOnClickListener != null) {
            viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.rv_yys_just_up),holder.getView(R.id.rv_yys_topay), position);
        }

    }

    private String getFactor(OperatorBean data) {
        StringBuffer str = new StringBuffer();
        if (data.getDirectFansNum() != null) {
            str.append("直推有效粉丝" + data.getDirectFansNum() + "人\n");
        }
        if (data.getIndirectFansNum() != null) {
            str.append("非直推有效粉丝" + data.getIndirectFansNum() + "人\n");
        }
        if (data.getSelfOrderNum() != null) {
            str.append("个人自购结算订单" + data.getSelfOrderNum() + "单\n");
        }
        if (data.getSelfCommission() != null) {
            str.append("个人累计获得佣金" + data.getSelfCommission() + "元\n");
        }
        if (data.getRecommendNum() != null) {
            str.append("推荐运营商" + data.getRecommendNum() + "个\n");
        }
        return str.length() == 0 ? "" : str.toString().substring(0, str.length() - 1);
    }
}
