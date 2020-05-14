package com.example.goods_detail.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.UserCouponBean;
import com.example.user_store.R;

import java.util.List;

public class GoodsCouponAdapter extends MyRecyclerAdapter<UserCouponBean> {
    public GoodsCouponAdapter(Context context, List<UserCouponBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public int getItemCount() {
        return mList.size() > 2 ? 2 : mList.size();
    }

    @Override
    public void convert(RecyclerViewHolder holder, UserCouponBean data, int position) {
        if (data.getMinPoint() == 0) {
            holder.setText(R.id.rv_goods_coupon_txt, "立减" + data.getAmount() + "元");
        } else {
            holder.setText(R.id.rv_goods_coupon_txt, "满" + data.getMinPoint() + "减" + data.getAmount());
        }
    }
}
