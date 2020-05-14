package com.example.order.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.MyOrderBean;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.utils.ArithUtil;
import com.example.utils.SPUtil;

import java.util.List;

public class RvListAdapter extends MyRecyclerAdapter<MyOrderBean> {

    public RvListAdapter(Context context, List<MyOrderBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MyOrderBean data, int position) {
        holder.setText(R.id.order_list_my_name, SPUtil.getStringValue(CommonResource.USER_NAME))
                .setText(R.id.order_list_name, data.getGoodsName())
                .setText(R.id.order_list_price, "￥" + (data.getGoodsPrice() * 1.0 / 100))
                .setText(R.id.order_list_count, "x" + data.getGoodsQuantity())
                .setImageUrl(R.id.order_list_img, data.getGoodsThumbnailUrl())
                .setText(R.id.order_list_total, "共" + data.getGoodsQuantity() + "件商品  合计：￥" + (data.getOrderAmount() * 1.0 / 100))
                .setText(R.id.order_list_predict, "预计收益" + ArithUtil.mul(SPUtil.getFloatValue("back"), data.getPromotionAmount() * 1.0 / 100) + "元");

        ImageView img = holder.getView(R.id.order_list_my_head);
        Glide.with(context).load(SPUtil.getStringValue(CommonResource.USER_PIC)).placeholder(R.drawable.vhjfg).into(img);

        if (data.getOrderStatus() == -1) {
            holder.setText(R.id.order_list_status, "待付款");
        } else if (data.getOrderStatus() == 0) {
            holder.setText(R.id.order_list_status, "已付款");
        } else if (data.getOrderStatus() == 5 || data.getOrderStatus() == 3) {
            holder.setText(R.id.order_list_status, "已结算");
        } else if (data.getOrderStatus() == 4 || data.getOrderStatus() == 8) {
            holder.setText(R.id.order_list_status, "已失效");
        } else if (data.getOrderStatus() == 1) {
            holder.setText(R.id.order_list_status, "已成团");
        }
    }
}
