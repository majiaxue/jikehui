package com.example.productcenter;

import com.example.mvp.IView;
import com.example.productcenter.adapter.ProductCenterAdapter;

public interface ProductCenterView extends IView {
    void loadAdapter(ProductCenterAdapter productCenterAdapter);

    void loadRefresh();

}
