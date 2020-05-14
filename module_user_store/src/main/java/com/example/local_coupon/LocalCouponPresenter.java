package com.example.local_coupon;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.bean.UserCouponBean;
import com.example.common.CommonResource;
import com.example.local_coupon.adapter.LocalCouponAdapter;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class LocalCouponPresenter extends BasePresenter<LocalCouponView> {
    private List<UserCouponBean> couponList = new ArrayList<>();
    private LocalCouponAdapter couponAdapter;

    public LocalCouponPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        Map map = MapUtil.getInstance().addParms("status", "0").addParms("userCode", SPUtil.getUserCode()).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getData(CommonResource.QUERY_COUPON, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("优惠券：" + result);
                couponList.addAll(JSON.parseArray(result, UserCouponBean.class));

                couponAdapter = new LocalCouponAdapter(mContext, couponList, R.layout.rv_local_my_coupon);
                if (getView() != null) {
                    getView().loadCoupon(couponAdapter);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));

    }
}
