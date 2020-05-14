package com.example.predict.pdd;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.bean.PredictBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.Map;

import io.reactivex.Observable;

public class PddPresenter extends BasePresenter<PddView> {
    public PddPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        Map map = MapUtil.getInstance().addParms("type", "3").build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.GETPREDICT, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("pdd:" + result);
                PredictBean predictBean = JSON.parseObject(result, PredictBean.class);
                if (predictBean != null) {
                    if (getView() != null) {
                        getView().loadUI(predictBean);
                    }
                } else {
                    if (getView() != null) {
                        getView().loadUI();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                if (getView() != null) {
                    getView().loadUI();
                }
            }
        }));
    }
}
