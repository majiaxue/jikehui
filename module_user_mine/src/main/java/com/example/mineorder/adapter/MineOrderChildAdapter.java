package com.example.mineorder.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.MineOrderBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class MineOrderChildAdapter extends MyRecyclerAdapter<MineOrderBean.OrderListBean.OrderItemsBean> {

    public MineOrderChildAdapter(Context context, List<MineOrderBean.OrderListBean.OrderItemsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MineOrderBean.OrderListBean.OrderItemsBean data, int position) {
        holder.setImageFresco(R.id.mine_order_child_image, data.getProductPic());
        holder.setText(R.id.mine_order_child_name, data.getProductName());
        holder.setText(R.id.mine_order_child_message, data.getProductAttr());
        holder.setText(R.id.mine_order_child_price, "￥" + data.getProductPrice());
        holder.setText(R.id.mine_order_child_count, "X" + data.getProductQuantity());
    }

}
