package com.example.local_shop.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.LocalShopBean;
import com.example.user_store.R;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.SpaceItemDecoration;
import com.example.view.RatingBarView;

import java.util.ArrayList;
import java.util.List;

public class LocalSellerAdapter extends MyRecyclerAdapter<LocalShopBean> {

    public LocalSellerAdapter(Context context, List<LocalShopBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, LocalShopBean data, int position) {
        holder.setImageUrl(R.id.rv_local_seller_img, data.getSeller_logo())
                .setText(R.id.rv_local_seller_name, data.getSeller_shop_name())
                .setText(R.id.rv_local_seller_type, data.getSeller_category_name())
                .setText(R.id.rv_local_seller_address, data.getSeller_addredd());
        RatingBarView star = holder.getView(R.id.rv_local_seller_star);
        star.setClickable(false);
        star.setStar(Integer.valueOf(data.getStar()), false);
        if (data.getDistance() != null) {
            Integer integer = Integer.valueOf(data.getDistance().split("\\.")[0]);
            if (integer >= 1000) {
                double mi= ( ArithUtil.div(integer * 1.0, 1000.0, 1))/1000;
                double exact = ArithUtil.exact(mi, 2);
                holder.setText(R.id.rv_local_seller_distance, exact + "千米");
                LogUtil.e("这是距离-----"+ArithUtil.div(integer * 1.0, 1000.0, 1));
            } else {
                holder.setText(R.id.rv_local_seller_distance, integer + "米");
                LogUtil.e("这是距离-----"+integer);
            }
        }
        RecyclerView rv = holder.getView(R.id.rv_local_seller_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        rv.setLayoutManager(layoutManager);
        if (rv.getItemDecorationCount() == 0) {
            rv.addItemDecoration(new SpaceItemDecoration(0, (int) context.getResources().getDimension(R.dimen.dp_10), 0, (int) context.getResources().getDimension(R.dimen.dp_10)));
        }

        ManJianAdapter adapter = new ManJianAdapter(context, data.getCouponList(), R.layout.rv_local_seller_inside);
        rv.setAdapter(adapter);
    }
}
