package com.example.fans_order.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.TbFansOrderBean;
import com.example.module_mine.R;

import java.util.List;

public class TbFansAdapter extends MyRecyclerAdapter<TbFansOrderBean> {
    public TbFansAdapter(Context context, List<TbFansOrderBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, TbFansOrderBean data, int position) {
        if (data.getImage() != null && data.getImage().startsWith("//")) {
            data.setImage("https:" + data.getImage());
        } else if (data.getImage() != null && data.getImage().startsWith("img")) {
            data.setImage("https://" + data.getImage());
        }

        holder.setText(R.id.fans_order_list_name, data.getItemTitle())
                .setImageUrl(R.id.fans_order_list_img, data.getImage())
                .setText(R.id.fans_order_list_price, "￥" + data.getPrice())
                .setText(R.id.fans_order_list_count, "x" + data.getItemNum())
                .setText(R.id.fans_order_list_total, "共" + data.getItemNum() + "件商品  合计：￥" + data.getAlipayTotalPrice())
                .setText(R.id.fans_order_list_time, "购买时间：" + data.getCreateTime())
                .setImageUrl(R.id.fans_order_list_head, data.getFansIcon())
                .setText(R.id.fans_order_list_nickname, data.getFansName())
                .setText(R.id.fans_order_list_pridect, "预计收益" + (data.getBackMoney() == null ? "0.0" : data.getBackMoney()) + "元");

        if ("京东".equals(data.getOrderType())) {
            holder.setImageResource(R.id.fans_order_list_type, R.drawable.icon_jingdong);
        } else if ("拼多多".equals(data.getOrderType())) {
            holder.setImageResource(R.id.fans_order_list_type, R.drawable.icon_pinduoduo);
        } else if ("天猫".equals(data.getOrderType())) {
            holder.setImageResource(R.id.fans_order_list_type, R.drawable.icon_tianmao);
        } else {
            holder.setImageResource(R.id.fans_order_list_type, R.drawable.icon_taobao);
        }

        if ("3".equals(data.getTkStatus())) {
            holder.setText(R.id.fans_order_list_status, "已结算");
        } else if ("12".equals(data.getTkStatus())) {
            holder.setText(R.id.fans_order_list_status, "已付款");
        } else if ("13".equals(data.getTkStatus())) {
            holder.setText(R.id.fans_order_list_status, "已失效");
        }
    }
}
