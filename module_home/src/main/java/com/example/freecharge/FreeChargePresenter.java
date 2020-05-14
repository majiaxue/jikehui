package com.example.freecharge;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.trade.biz.AlibcConstants;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.FreeChargeBean;
import com.example.common.CommonResource;
import com.example.freecharge.adapter.FreeChargeAdapter;
import com.example.freecharge.adapter.FreeChargeLookAdapter;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class FreeChargePresenter extends BasePresenter<FreeChargeView> {

    private FreeChargeAdapter freeChargeAdapter;
    private FreeChargeLookAdapter freeChargeLookAdapter;

    public FreeChargePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void freeChargeActivity(final int activityType, final RecyclerView freeChargeRec) {
        Map type = MapUtil.getInstance().addParms("type", activityType).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TAOLIJIN, type);
        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
//                ProcessDialogUtil.dismissDialog();
                LogUtil.e("FreeChargePresenterResult" + result);
                final List<FreeChargeBean> freeChargeBeans = JSON.parseArray(result, FreeChargeBean.class);
                if (freeChargeBeans.size() != 0) {
                    if (getView() != null) {
                        getView().noGoods(false);
                    }
                    if (activityType == 0) {
                        if (freeChargeAdapter == null) {
                            freeChargeAdapter = new FreeChargeAdapter(mContext, freeChargeBeans, R.layout.item_free_charge_activity_rec);
                        } else {
                            freeChargeAdapter.notifyDataSetChanged();
                        }
                        getView().load(freeChargeAdapter);

                        freeChargeAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                            @Override
                            public void ViewOnClick(View view, final int index) {
                                view.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        jumpToTB(freeChargeBeans.get(index).getSendUrl());
                                    }
                                });
                            }
                        });
                    } else {
                        if (freeChargeLookAdapter == null) {
                            freeChargeLookAdapter = new FreeChargeLookAdapter(mContext, freeChargeBeans, R.layout.item_free_charge_look_back_rec);
                        } else {
                            freeChargeLookAdapter.notifyDataSetChanged();
                        }
                        getView().load(freeChargeLookAdapter);
                    }
                } else {
                    if (getView() != null) {
                        getView().noGoods(true);
                    }
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
//                ProcessDialogUtil.dismissDialog();
                LogUtil.e("FreeChargePresenterErrorMsg" + errorMsg);
            }
        }));
    }

    private void jumpToTB(String originUrl) {
        Map<String, String> exParams = new HashMap<>();
        exParams.put(AlibcConstants.ISV_CODE, "appisvcode");


        //设置页面打开方式
        AlibcShowParams showParams = new AlibcShowParams();
        showParams.setOpenType(OpenType.Native);

        AlibcTaokeParams taokeParams = new AlibcTaokeParams("", "", "");
        taokeParams.setPid("mm_112883640_11584347_72287650277");


        AlibcTrade.openByUrl((Activity) mContext, "", originUrl, null, null, null, showParams, taokeParams, exParams, new AlibcTradeCallback() {
            @Override
            public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }


}
