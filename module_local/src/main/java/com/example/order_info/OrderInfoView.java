package com.example.order_info;

import com.example.mvp.IView;
import com.example.order_info.adapter.OrderInfoAdapter;

public interface OrderInfoView extends IView {
    void loadRv(OrderInfoAdapter adapter);
}
