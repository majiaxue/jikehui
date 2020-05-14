package com.example.local_order.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.LocalOrderBean;
import com.example.module_local.R;

import java.util.List;

public class LocalOrderInnerAdapter extends MyRecyclerAdapter<LocalOrderBean.LocalOrderItemListBean> {
    public LocalOrderInnerAdapter(Context context, List<LocalOrderBean.LocalOrderItemListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, LocalOrderBean.LocalOrderItemListBean data, int position) {
        if (!TextUtils.isEmpty(data.getGoodsPic())) {
            holder.setImageFresco(R.id.rv_local_order_list_img, data.getGoodsPic());
        }
        holder.setText(R.id.rv_local_order_list_name, data.getGoodsName());
    }
}
