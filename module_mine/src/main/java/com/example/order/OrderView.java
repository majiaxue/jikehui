package com.example.order;

import com.example.mvp.IView;
import com.example.order.adapter.OrderVPAdapter;

public interface OrderView extends IView {

    void updateVP(OrderVPAdapter adapter);

    void typeChanged(int position);
}
