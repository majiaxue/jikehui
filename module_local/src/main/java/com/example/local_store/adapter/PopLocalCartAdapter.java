package com.example.local_store.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.LocalCartBean;
import com.example.module_local.R;

import java.util.List;

public class PopLocalCartAdapter extends MyRecyclerAdapter<LocalCartBean.InsideCart> {
    public PopLocalCartAdapter(Context context, List<LocalCartBean.InsideCart> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, LocalCartBean.InsideCart data, int position) {
        holder.setText(R.id.rv_pop_shopcart_name, data.getLocalGoodsName())
                .setText(R.id.rv_pop_shopcart_price, data.getPrice() + "")
                .setText(R.id.rv_pop_shopcart_count, data.getNum() + "");

        if (viewTwoOnClickListener != null) {
            viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.rv_pop_shopcart_minus), holder.getView(R.id.rv_pop_shopcart_add), position);
        }
    }
}
