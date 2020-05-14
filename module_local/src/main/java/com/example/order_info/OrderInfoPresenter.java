package com.example.order_info;

import android.content.Context;

import com.example.bean.LocalOrderBean;
import com.example.module_local.R;
import com.example.mvp.BasePresenter;
import com.example.order_info.adapter.OrderInfoAdapter;

import java.util.List;

public class OrderInfoPresenter extends BasePresenter<OrderInfoView> {
    public OrderInfoPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(List<LocalOrderBean.LocalOrderItemListBean> list) {
        OrderInfoAdapter orderInfoAdapter = new OrderInfoAdapter(mContext, list, R.layout.rv_order_info);
        if (getView()!=null){
            getView().loadRv(orderInfoAdapter);
        }
    }
}
