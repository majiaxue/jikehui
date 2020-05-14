package com.example.order.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.JDOrderBean;
import com.example.module_mine.R;
import com.example.utils.ArithUtil;
import com.example.utils.SPUtil;

import java.util.List;

public class JDAdapter extends MyRecyclerAdapter<JDOrderBean> {
    public JDAdapter(Context context, List<JDOrderBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, JDOrderBean data, int position) {
        holder.setText(R.id.order_list_my_name, SPUtil.getStringValue("name"))
                .setText(R.id.order_list_name, data.getSkuName())
                .setText(R.id.order_list_price, "￥" + data.getPrice())
                .setText(R.id.order_list_count, "x" + data.getSkuNum())
                .setImageUrl(R.id.order_list_img, data.getImage())
                .setText(R.id.order_list_total, "共" + data.getSkuNum() + "件商品  合计：￥" + (data.getPrice() * data.getSkuNum()))
                .setText(R.id.order_list_predict, "预计收益" + ArithUtil.mul(SPUtil.getFloatValue("back"), data.getActualFee()) + "元");
        ImageView img = holder.getView(R.id.order_list_my_head);
        Glide.with(context).load(SPUtil.getStringValue("head")).placeholder(R.drawable.vhjfg).into(img);

        if ("16".equals(data.getOrderValidCode())) {
            holder.setText(R.id.order_list_status, "已付款");
        } else if ("18".equals(data.getOrderValidCode())) {
            holder.setText(R.id.order_list_status, "已结算");
        } else if ("3".equals(data.getOrderValidCode())) {
            holder.setText(R.id.order_list_status, "已失效");
        } else if ("15".equals(data.getOrderValidCode())) {
            holder.setText(R.id.order_list_status, "待付款");
        }
    }
}
