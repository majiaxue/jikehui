package com.example.local_store.ShoppingRight;


import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.bean.LocalCartBean;
import com.example.bean.LocalOrderBean;
import com.example.common.CommonResource;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * author pangchao
 * created on 2017/5/12
 * email fat_chao@163.com.
 */

public class SortDetailPresenter extends BasePresenter {
    @Override
    protected void getData() {

    }

    public void loadCart() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getDataWithout(CommonResource.LOCAL_GET_CART + SPUtil.getStringValue(CommonResource.SELLERID) + "/" + SPUtil.getUserCode());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("购物车：" + result);
                LocalCartBean localCartBeans = JSON.parseObject(result, LocalCartBean.class);
                submitOrder(localCartBeans.getLocalShopcarList());
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------------" + errorMsg);
            }
        }));
    }

    public void submitOrder(List<LocalCartBean.InsideCart> data) {
        List<LocalOrderBean.LocalOrderItemListBean> list = new ArrayList<>();
        double price = 0;
        boolean hasGoods = false;
        for (int i = 0; i < data.size(); i++) {
            hasGoods = true;
            LocalOrderBean.LocalOrderItemListBean bean = new LocalOrderBean.LocalOrderItemListBean();
            bean.setGoodsName(data.get(i).getLocalGoodsName());
            bean.setGoodsId(data.get(i).getLocalGoodsId());
            bean.setGoodsNum(Integer.valueOf(data.get(i).getNum()));
            bean.setGoodsPic(data.get(i).getLocalGoodsPic());
            bean.setGoodsSpec(data.get(i).getLocalGoodsSpecification());
            bean.setGoodsPrice(data.get(i).getPrice() + "");

            list.add(bean);
            price = (price * 1000 + Double.valueOf(bean.getGoodsPrice()) * bean.getGoodsNum() * 1000) / 1000;

        }
        LocalOrderBean localOrderBean = new LocalOrderBean();
        localOrderBean.setLocalSellerId(SPUtil.getStringValue(CommonResource.SELLERID));
        localOrderBean.setSellerName(SPUtil.getStringValue(CommonResource.SELLERNAME));
        localOrderBean.setUserCode(SPUtil.getUserCode());
        localOrderBean.setLocalOrderItemList(list);
        localOrderBean.setTotalMoney(price);
        localOrderBean.setDeliverType("1");
        localOrderBean.setSellerManJian(SPUtil.getStringValue(CommonResource.LOCAL_SELLER_MANJIAN));

        if (hasGoods) {
            ARouter.getInstance().build("/module_local/LocalOrderConfirmActivity").withSerializable("bean", localOrderBean).navigation();
        }
    }
}
