package com.example.local_coupon.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.UserCouponBean;
import com.example.user_store.R;

import java.util.List;

public class LocalCouponAdapter extends MyRecyclerAdapter<UserCouponBean> {
    public LocalCouponAdapter(Context context, List<UserCouponBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, UserCouponBean data, int position) {
        holder.setText(R.id.rv_mycoupon_money, data.getAmount() + "")
                .setText(R.id.rv_mycoupon_demand, "满" + data.getMinPoint() + "元可用")
                .setText(R.id.rv_mycoupon_time, "有效期至" + data.getEndTime());
    }
}
