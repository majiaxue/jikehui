package com.example.local_home.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.LocalShopCommendBean;
import com.example.module_local.R;

import java.util.List;

public class LocalHomeCommendAdapter extends MyRecyclerAdapter<LocalShopCommendBean.GoodsListBean> {
    public LocalHomeCommendAdapter(Context context, List<LocalShopCommendBean.GoodsListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, LocalShopCommendBean.GoodsListBean data, int position) {
        holder.setImageUrl(R.id.rv_local_home_commend_img, data.getPics())
                .setText(R.id.rv_local_home_commend_name, data.getName())
                .setText(R.id.rv_local_home_commend_new_price, "￥" + data.getDiscountPrice())
                .setText(R.id.rv_local_home_commend_old_price, "￥" + data.getPrice());
    }
}
