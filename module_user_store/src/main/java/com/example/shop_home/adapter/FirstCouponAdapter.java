package com.example.shop_home.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.ParmsBean;
import com.example.user_store.R;
import com.example.view.AutoScaleTextView;

import java.util.List;

public class FirstCouponAdapter extends MyRecyclerAdapter<ParmsBean> {
    public FirstCouponAdapter(Context context, List<ParmsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, ParmsBean data, int position) {
        ((AutoScaleTextView) holder.getView(R.id.rv_shop_first_money)).setText(data.getKey());
        ((AutoScaleTextView) holder.getView(R.id.rv_shop_first_factor)).setText(data.getValue());
    }
}
