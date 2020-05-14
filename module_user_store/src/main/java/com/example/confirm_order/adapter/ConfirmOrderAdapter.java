package com.example.confirm_order.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.CartBean;
import com.example.user_store.R;
import com.example.utils.ArithUtil;
import com.example.utils.SpaceItemDecoration;

import java.util.List;

public class ConfirmOrderAdapter extends MyRecyclerAdapter<CartBean.RecordsBean> {
    public ConfirmOrderAdapter(Context context, List<CartBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(final RecyclerViewHolder holder, CartBean.RecordsBean data, final int position) {
        holder.setText(R.id.confirm_order_shop_name, data.getSellerName())
                .setText(R.id.confirm_order_delivery_txt2, data.getTotalFeight() + "")
                .setText(R.id.confirm_order_delivery_choose_coupon, "优惠￥" + data.getDisAmount() + "元")
                .setText(R.id.confirm_order_count, "共" + data.getItems().size() + "件")
                .setText(R.id.confirm_order_xiaoji, "￥" + ArithUtil.exact(data.getTotalPrice(), 2));
        RecyclerView rv = holder.getView(R.id.confirm_order_inside_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        if (rv.getItemDecorationCount() < 1) {
            rv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) context.getResources().getDimension(R.dimen.dp_10)));
        }
        ConfirmOrderInsideAdapter insideAdapter = new ConfirmOrderInsideAdapter(context, data.getItems(), R.layout.rv_inside_confirm_order);
        rv.setAdapter(insideAdapter);

        insideAdapter.setViewTwoOnClickListener(new ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(View view1, View view2, int index) {
                if (viewThreeOnClickListener2 != null) {
                    viewThreeOnClickListener2.viewThreeOnClick2(view1, view2, holder.getView(R.id.confirm_order_delivery_choose_coupon), position, index);
                }
            }
        });

    }
}
