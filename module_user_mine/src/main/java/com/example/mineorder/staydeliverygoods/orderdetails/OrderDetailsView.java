package com.example.mineorder.staydeliverygoods.orderdetails;

import com.example.bean.OrderDetailBean;
import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/6/16
 * Describe:
 */
public interface OrderDetailsView extends IView {
    void loadData(OrderDetailBean orderDetailBean);
}
