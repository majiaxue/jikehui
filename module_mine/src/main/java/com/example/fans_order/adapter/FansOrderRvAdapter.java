package com.example.fans_order.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.FansOrderBean;
import com.example.module_mine.R;
import com.example.utils.MyTimeUtil;

import java.util.List;

public class FansOrderRvAdapter extends MyRecyclerAdapter<FansOrderBean> {
    public FansOrderRvAdapter(Context context, List<FansOrderBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, FansOrderBean data, int position) {
        holder.setText(R.id.fans_order_list_name, data.getGoodsName())
                .setImageUrl(R.id.fans_order_list_img, data.getGoodsThumbnailUrl())
                .setText(R.id.fans_order_list_price, "￥" + Double.valueOf(data.getGoodsPrice() == null ? "0" : data.getGoodsPrice()))
                .setText(R.id.fans_order_list_count, "x" + data.getGoodsQuantity())
                .setText(R.id.fans_order_list_total, "共" + data.getGoodsQuantity() + "件商品  合计：￥" + Double.valueOf(data.getOrderAmount() == null ? "0" : data.getOrderAmount()))
                .setText(R.id.fans_order_list_time, "购买时间：" + MyTimeUtil.date2StringLong(data.getOrderCreateTime() + "000"))
                .setImageResource(R.id.fans_order_list_type, R.drawable.icon_pdd)
                .setImageUrl(R.id.fans_order_list_head, data.getFansIcon())
                .setText(R.id.fans_order_list_nickname, data.getFansName())
                .setText(R.id.fans_order_list_pridect, "预计收益" + (data.getBackMoney() == null ? "0.0" : data.getBackMoney()) + "元");

        if (data.getOrderStatus() == -1) {
            holder.setText(R.id.fans_order_list_status, "待付款");
        } else if (data.getOrderStatus() == 0) {
            holder.setText(R.id.fans_order_list_status, "已付款");
        } else if (data.getOrderStatus() == 5 || data.getOrderStatus() == 3) {
            holder.setText(R.id.fans_order_list_status, "已结算");
        } else if (data.getOrderStatus() == 4 || data.getOrderStatus() == 8) {
            holder.setText(R.id.fans_order_list_status, "已失效");
        } else if (data.getOrderStatus() == 1) {
            holder.setText(R.id.fans_order_list_status, "已成团");
        }
    }
}
