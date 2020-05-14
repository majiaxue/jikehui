package com.example.pay_success;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.SubmitOrderBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_store.UserActivity;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class PaySuccessPresenter extends BasePresenter<PaySuccessView> {
    public PaySuccessPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(String masterNo) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).getHeadWithout(CommonResource.PAYSUCCESS + "/" + masterNo, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("支付成功：" + result);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void jumpToOrder(SubmitOrderBean bean) {
        if ("goods".equals(bean.getProductName())) {
//            Intent intent = new Intent(mContext, OrderDetailActivity.class);
//            intent.putExtra("bean", bean);
//            mContext.startActivity(intent);
            ARouter.getInstance()
                    .build("/module_user_mine/OrderDetailsActivity")
                    .withString("orderSn", bean.getMasterNo())
                    .navigation();
            ((Activity) mContext).finish();
        } else if ("cart".equals(bean.getProductName())) {
            ARouter.getInstance().build("/module_user_mine/MineOrderActivity").navigation();
            ((Activity) mContext).finish();
        } else {
            ARouter.getInstance().build("/module_user_mine/MineOrderActivity").navigation();
            ((Activity) mContext).finish();
        }
    }

    public void jumpToHome() {
        mContext.startActivity(new Intent(mContext, UserActivity.class));
    }
}
