package com.example.fans_order.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.JdFansOrderBean;
import com.example.module_mine.R;
import com.example.utils.ArithUtil;
import com.example.utils.MyTimeUtil;

import java.util.List;

public class JdFansAdapter extends MyRecyclerAdapter<JdFansOrderBean> {
    public JdFansAdapter(Context context, List<JdFansOrderBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, JdFansOrderBean data, int position) {
        holder.setText(R.id.fans_order_list_name, data.getSkuName())
                .setImageUrl(R.id.fans_order_list_img, data.getImage())
                .setText(R.id.fans_order_list_price, "￥" + data.getPrice())
                .setText(R.id.fans_order_list_count, "x" + data.getSkuNum())
                .setText(R.id.fans_order_list_total, "共" + data.getSkuNum() + "件商品  合计：￥" + ArithUtil.mul(Double.valueOf(data.getPrice()), Double.valueOf(data.getSkuNum())))
                .setText(R.id.fans_order_list_time, "购买时间：" + MyTimeUtil.date2String(data.getOrderTime()))
                .setImageResource(R.id.fans_order_list_type, R.drawable.icon_jd)
                .setImageUrl(R.id.fans_order_list_head, data.getFansIcon())
                .setText(R.id.fans_order_list_nickname, data.getFansName())
                .setText(R.id.fans_order_list_pridect, "预计收益" + (data.getBackMoney() == null ? "0.0" : data.getBackMoney()) + "元");

        if ("16".equals(data.getOrderValidCode())) {
            holder.setText(R.id.fans_order_list_status, "已付款");
        } else if ("18".equals(data.getOrderValidCode())) {
            holder.setText(R.id.fans_order_list_status, "已结算");
        } else if ("3".equals(data.getOrderValidCode())) {
            holder.setText(R.id.fans_order_list_status, "已失效");
        }
    }
}
