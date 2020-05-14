package com.example.order_detail;

import com.example.bean.OrderDetailBean;
import com.example.mvp.IView;
import com.example.user_home.adapter.CommendAdapter;

public interface OrderDetailView extends IView {

    void loadData(OrderDetailBean orderDetailBean);

    void loadCommend(CommendAdapter adapter);
}
