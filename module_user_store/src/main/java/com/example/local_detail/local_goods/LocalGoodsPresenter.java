package com.example.local_detail.local_goods;

import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.example.bean.LocalGoodsBean;
import com.example.bean.LocalShopBean;
import com.example.common.CommonResource;
import com.example.local_detail.adapter.LocalDetailGoodsAdapter;
import com.example.local_pay.LocalPayActivity;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class LocalGoodsPresenter extends BasePresenter<LocalGoodsView> {
    private LocalDetailGoodsAdapter goodsAdapter;
    private List<LocalGoodsBean.RecordsBean> beanList;

    public LocalGoodsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(String sellerId) {
        Map map = MapUtil.getInstance().addParms("sellerId", sellerId).addParms("page", "1").addParms("size", "1000").build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.LOCAL_SHOP_GOODS, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("本地商品：" + result);
                try {
                    if (result != null) {
                        LocalGoodsBean goodsBean = JSON.parseObject(result, LocalGoodsBean.class);
                        beanList = goodsBean.getRecords();
                        goodsAdapter = new LocalDetailGoodsAdapter(mContext, beanList, R.layout.rv_local_detail_goods);
                        if (getView() != null) {
                            getView().loadGoods(goodsAdapter);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "----------" + errorMsg);
            }
        }));
    }

    public void jumpToPay(LocalShopBean bean) {
        Intent intent = new Intent(mContext, LocalPayActivity.class);
        intent.putExtra("bean", bean);
        mContext.startActivity(intent);
    }
}
