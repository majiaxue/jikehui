package com.example.goods_detail.adapter;

import android.content.Context;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.UserCouponBean;
import com.example.user_store.R;

import java.util.List;

public class PopLingQuanAdapter extends MyRecyclerAdapter<UserCouponBean> {
    public PopLingQuanAdapter(Context context, List<UserCouponBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, UserCouponBean data, int position) {
        holder.setText(R.id.rv_pop_lingquan_money, data.getAmount() + "")
                .setText(R.id.rv_pop_lingquan_demand, data.getMinPoint() == 0 ? "" : "满" + data.getMinPoint() + "元可使用")
                .setText(R.id.rv_pop_lingquan_time, "有效期至  " + data.getEndTime());
        if (data.isHas()) {
            holder.getView(R.id.rv_pop_lingquan_img).setVisibility(View.VISIBLE);
            holder.getView(R.id.rv_pop_lingquan_parent).setEnabled(false);
        } else {
            holder.getView(R.id.rv_pop_lingquan_img).setVisibility(View.GONE);
            holder.getView(R.id.rv_pop_lingquan_parent).setEnabled(true);
        }
        if (viewOnClickListener != null) {
            viewOnClickListener.ViewOnClick(holder.getView(R.id.rv_pop_lingquan_parent), position);
        }
    }
}
