package com.example.adapter;

import android.content.Context;

import com.example.bean.UserCouponBean;
import com.example.module_base.R;

import java.util.List;

public class PopCouponAdapter extends MyRecyclerAdapter<UserCouponBean> {

    public PopCouponAdapter(Context context, List<UserCouponBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, UserCouponBean data, int position) {
        holder.setText(R.id.coupon_rec1_price, "￥" + data.getAmount());
        holder.setText(R.id.coupon_rec1_max_price, "满" + data.getMinPoint() + "使用");

        viewOnClickListener.ViewOnClick(holder.getView(R.id.coupon_rec1_immediately_get), position);
    }
}
