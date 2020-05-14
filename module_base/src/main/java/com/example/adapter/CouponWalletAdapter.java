package com.example.adapter;

import android.content.Context;

import com.example.bean.RedPackageBean;
import com.example.module_base.R;

import java.util.List;

public class CouponWalletAdapter extends MyRecyclerAdapter<RedPackageBean> {
    public CouponWalletAdapter(Context context, List<RedPackageBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, RedPackageBean data, int position) {
        holder.setText(R.id.rv_coupon_wallet_money, data.getMoney())
                .setText(R.id.rv_coupon_wallet_count, data.getCount());
    }
}
