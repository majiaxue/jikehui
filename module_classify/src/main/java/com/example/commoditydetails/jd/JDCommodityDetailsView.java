package com.example.commoditydetails.jd;

import com.example.bean.JDListBean;
import com.example.commoditydetails.pdd.adapter.CommodityDetailsRecAdapter;
import com.example.mvp.IView;

public interface JDCommodityDetailsView extends IView {

    void isNoGoods(boolean isNoGoods);

    void qrImage(String url);

    void loadUI(JDListBean jDGoodsRecBean, CommodityDetailsRecAdapter adapter);
}
