package com.example.mineorder.staydeliverygoods.orderdetails.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.OrderDetailBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/18
 * Describe:
 */
public class OrderDetailsAdapter extends MyRecyclerAdapter<OrderDetailBean.ItemsBean> {

    public OrderDetailsAdapter(Context context, List<OrderDetailBean.ItemsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, OrderDetailBean.ItemsBean data, int position) {
        holder.setImageFresco(R.id.order_details_rec_image, ""+data.getProductPic());
        holder.setText(R.id.order_details_rec_goods_name, ""+data.getProductName());
        holder.setText(R.id.order_details_rec_count, "X" + data.getProductQuantity());
        holder.setText(R.id.order_details_rec_productAttr, ""+data.getProductAttr());
        holder.setText(R.id.order_details_rec_money, "￥" + data.getProductPrice());
    }
}
