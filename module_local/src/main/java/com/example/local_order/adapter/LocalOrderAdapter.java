package com.example.local_order.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.LocalOrderBean;
import com.example.module_local.R;
import com.example.utils.LogUtil;
import com.example.utils.SpaceItemDecoration;

import java.util.List;

public class LocalOrderAdapter extends MyRecyclerAdapter<LocalOrderBean> {
    public LocalOrderAdapter(Context context, List<LocalOrderBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, LocalOrderBean data, int position) {
        try {
            if (data.getLocalOrderItemList().size() > 0) {
                holder.setText(R.id.rv_local_order_list_shop_name, data.getSellerInfo().getShop_name())
                        .setText(R.id.rv_local_order_list_count, "共" + data.getLocalOrderItemList().size() + "件商品")
                        .setText(R.id.rv_local_order_list_money, "￥" + data.getTotalMoney());
                if (!TextUtils.isEmpty(data.getSellerInfo().getSeller_logo())) {
                    holder.setImageFresco(R.id.rv_local_order_list_img, data.getSellerInfo().getSeller_logo());
                }

                if (TextUtils.isEmpty(data.getTakeGoodsCode())) {
                    holder.getView(R.id.rv_local_order_list_code).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.rv_local_order_list_code).setVisibility(View.VISIBLE);
                    holder.setText(R.id.rv_local_order_list_code, "您的取货码为：" + data.getTakeGoodsCode());
                }
            }

            if ("0".equals(data.getStatus())) {
                holder.getView(R.id.rv_local_order_list_linear).setVisibility(View.VISIBLE);
                holder.getView(R.id.rv_local_order_list_temp).setVisibility(View.VISIBLE);
                holder.setText(R.id.rv_local_order_list_cancel, "取消订单")
                        .setText(R.id.rv_local_order_list_confirm, "去付款")
                        .setText(R.id.rv_local_order_list_status, "待付款");
            } else if ("1".equals(data.getStatus())) {
                holder.getView(R.id.rv_local_order_list_linear).setVisibility(View.VISIBLE);
                holder.getView(R.id.rv_local_order_list_temp).setVisibility(View.VISIBLE);
                holder.setText(R.id.rv_local_order_list_cancel, "申请退款")
                        .setText(R.id.rv_local_order_list_confirm, "查看订单")
                        .setText(R.id.rv_local_order_list_status, "已付款");
            } else if ("2".equals(data.getStatus())) {
                holder.getView(R.id.rv_local_order_list_linear).setVisibility(View.VISIBLE);
                holder.getView(R.id.rv_local_order_list_temp).setVisibility(View.VISIBLE);
                holder.setText(R.id.rv_local_order_list_cancel, "申请退款")
                        .setText(R.id.rv_local_order_list_confirm, "确认收货")
                        .setText(R.id.rv_local_order_list_status, "待取货");
            } else if ("3".equals(data.getStatus())) {
                holder.getView(R.id.rv_local_order_list_linear).setVisibility(View.VISIBLE);
                holder.getView(R.id.rv_local_order_list_temp).setVisibility(View.VISIBLE);
                holder.setText(R.id.rv_local_order_list_cancel, "申请退款")
                        .setText(R.id.rv_local_order_list_confirm, "确认收货")
                        .setText(R.id.rv_local_order_list_status, "配送中");
            } else if ("4".equals(data.getStatus()) || "3".equals(data.getStatus())) {
                holder.getView(R.id.rv_local_order_list_linear).setVisibility(View.GONE);
                holder.getView(R.id.rv_local_order_list_temp).setVisibility(View.GONE);
                holder.setText(R.id.rv_local_order_list_status, "已完成");
            } else if ("5".equals(data.getStatus())) {
                holder.getView(R.id.rv_local_order_list_linear).setVisibility(View.GONE);
                holder.getView(R.id.rv_local_order_list_temp).setVisibility(View.GONE);
                holder.setText(R.id.rv_local_order_list_status, "退货");
            } else if ("6".equals(data.getStatus())) {
                holder.getView(R.id.rv_local_order_list_linear).setVisibility(View.GONE);
                holder.getView(R.id.rv_local_order_list_temp).setVisibility(View.GONE);
                holder.setText(R.id.rv_local_order_list_status, "已关闭");
            } else if ("8".equals(data.getStatus())) {
                holder.getView(R.id.rv_local_order_list_linear).setVisibility(View.GONE);
                holder.getView(R.id.rv_local_order_list_temp).setVisibility(View.GONE);
                holder.setText(R.id.rv_local_order_list_status, "退款中");
            } else {
                holder.getView(R.id.rv_local_order_list_linear).setVisibility(View.GONE);
                holder.getView(R.id.rv_local_order_list_temp).setVisibility(View.GONE);
            }

            RecyclerView rv = holder.getView(R.id.rv_local_order_list_rv);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            rv.setLayoutManager(layoutManager);
            if (rv.getItemDecorationCount() == 0) {
                rv.addItemDecoration(new SpaceItemDecoration((int) context.getResources().getDimension(R.dimen.dp_3), (int) context.getResources().getDimension(R.dimen.dp_3), 0, 0));
            }
            LocalOrderInnerAdapter innerAdapter = new LocalOrderInnerAdapter(context, data.getLocalOrderItemList(), R.layout.rv_inner_local_order);
            rv.setAdapter(innerAdapter);

            if (viewThreeOnClickListener != null) {
                viewThreeOnClickListener.ViewThreeOnClick(holder.getView(R.id.rv_local_order_list_shop_name), holder.getView(R.id.rv_local_order_list_cancel), holder.getView(R.id.rv_local_order_list_confirm), position);
            }
        } catch (Exception e) {
            LogUtil.e("----------------->" + e.getMessage());
            e.printStackTrace();
        }
    }
}
