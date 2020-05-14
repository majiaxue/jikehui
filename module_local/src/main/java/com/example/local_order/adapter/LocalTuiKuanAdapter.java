package com.example.local_order.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.LocalTuiKuanBean;
import com.example.module_local.R;
import com.example.utils.SpaceItemDecoration;

import java.util.List;

public class LocalTuiKuanAdapter extends MyRecyclerAdapter<LocalTuiKuanBean.RecordsBean> {
    public LocalTuiKuanAdapter(Context context, List<LocalTuiKuanBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, LocalTuiKuanBean.RecordsBean data, int position) {
        try {

            if (data.getLocalOrderItemList().size() > 0) {
                holder.setText(R.id.rv_local_order_list_count, "共" + data.getLocalOrderItemList().size() + "件商品")
                        .setText(R.id.rv_local_order_list_money, "￥" + data.getReturnAmount());
            }

            if (data.getSeller() != null && !TextUtils.isEmpty(data.getSeller().getSellerLogo())) {
                holder.setImageFresco(R.id.rv_local_order_list_img, data.getSeller().getSellerLogo())
                        .setText(R.id.rv_local_order_list_shop_name, data.getSeller().getSellerShopName());
            }

            holder.getView(R.id.rv_local_order_list_code).setVisibility(View.GONE);
            holder.getView(R.id.rv_local_order_list_linear).setVisibility(View.GONE);
            holder.getView(R.id.rv_local_order_list_temp).setVisibility(View.GONE);

            if (data.getStatus() == 0) {
                holder.setText(R.id.rv_local_order_list_status, "等待商家处理");
            } else if (data.getStatus() == 1) {
                holder.setText(R.id.rv_local_order_list_status, "退货中");
            } else if (data.getStatus() == 2) {
                holder.setText(R.id.rv_local_order_list_status, "已退款");
            } else if (data.getStatus() == 3) {
                holder.setText(R.id.rv_local_order_list_status, "已拒绝");
            }

            RecyclerView rv = holder.getView(R.id.rv_local_order_list_rv);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            rv.setLayoutManager(layoutManager);
            if (rv.getItemDecorationCount() == 0) {
                rv.addItemDecoration(new SpaceItemDecoration((int) context.getResources().getDimension(R.dimen.dp_3), (int) context.getResources().getDimension(R.dimen.dp_3), 0, 0));
            }
            LocalOrderInnerAdapter innerAdapter = new LocalOrderInnerAdapter(context, data.getLocalOrderItemList(), R.layout.rv_inner_local_order);
            rv.setAdapter(innerAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
