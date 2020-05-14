package com.example.points_mingxi;

import com.example.mvp.IView;
import com.example.order.adapter.OrderVPAdapter;

public interface PointsMXView extends IView {
    void updateVP(OrderVPAdapter vpAdapter);
}
