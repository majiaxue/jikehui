package com.example.commoditydetails.taobao;

import com.alibaba.fastjson.JSONArray;
import com.example.bean.MXBean;
import com.example.bean.NewTBGoodsDetailsBean;
import com.example.bean.TBBean;
import com.example.bean.TBGoodsDetailsBean;
import com.example.bean.TBLedSecuritiesBean;
import com.example.mvp.IView;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/14
 * Describe:
 */
public interface TBCommodityDetailsView extends IView {
    void tbBeanList(NewTBGoodsDetailsBean tbGoodsDetailsBean);

    void tBDetails();

    void noCoupon(boolean noCoupon);

    void loadData(MXBean recordBeans);
}
