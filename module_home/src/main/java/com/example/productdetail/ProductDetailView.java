package com.example.productdetail;

import com.example.bean.BannerImageBean;
import com.example.mvp.IView;
import com.example.productdetail.adapter.ProductAccountAdapter;

import java.util.List;

public interface ProductDetailView extends IView {
    void loadRv(ProductAccountAdapter adapter);

    void updatePhone(int type, String phone);

    void loadBanner(List<BannerImageBean> imgList);
}
