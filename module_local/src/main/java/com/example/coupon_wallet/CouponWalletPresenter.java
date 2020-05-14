package com.example.coupon_wallet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.RedPackageBean;
import com.example.common.CommonResource;
import com.example.adapter.CouponWalletAdapter;
import com.example.module_local.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class CouponWalletPresenter extends BasePresenter<CouponWalletView> {

    private List<RedPackageBean> redPackageBeans;

    public CouponWalletPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getDataWithout(CommonResource.LOCAL_GET_HONGBAO);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("红包：" + result);
                try {
                    redPackageBeans = JSON.parseArray(result, RedPackageBean.class);
                    CouponWalletAdapter walletAdapter = new CouponWalletAdapter(mContext, redPackageBeans, R.layout.rv_coupon_wallet);
                    if (getView() != null) {
                        getView().loadRv(walletAdapter);
                    }

                    walletAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance().build("/module_user_store/PaymentActivity").withSerializable("redPackageBean", redPackageBeans.get(position)).navigation();

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------------" + errorMsg);
            }
        }));
    }

}
