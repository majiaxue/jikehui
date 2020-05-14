package com.example.obligation;

import com.example.bean.OrderDetailBean;
import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/5/28
 * Describe:
 */
public interface ObligationView extends IView {
    void loadData(OrderDetailBean orderDetailBean);

    void isDelete(boolean isDelete);
}
