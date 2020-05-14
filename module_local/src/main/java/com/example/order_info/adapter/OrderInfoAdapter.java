package com.example.order_info.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.LocalOrderBean;
import com.example.module_local.R;

import java.util.List;

public class OrderInfoAdapter extends MyRecyclerAdapter<LocalOrderBean.LocalOrderItemListBean> {
    public OrderInfoAdapter(Context context, List<LocalOrderBean.LocalOrderItemListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, LocalOrderBean.LocalOrderItemListBean data, int position) {
        holder.setText(R.id.rv_order_info_name, data.getGoodsName())
                .setText(R.id.rv_order_info_count, "X" + data.getGoodsNum())
                .setText(R.id.rv_order_info_money, data.getGoodsPrice())
                .setImageFresco(R.id.rv_order_info_img, data.getGoodsPic());
    }
}
