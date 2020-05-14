package com.example.coupon.haveexpired;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.CouponWalletAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.CouponBean;
import com.example.bean.RedPackageBean;
import com.example.common.CommonResource;
import com.example.coupon.adapter.HaveExpiredAdapter;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public class HaveExpiredPresenter extends BasePresenter<HaveExpiredView> {


    public HaveExpiredPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void haveExpiredRec(final RecyclerView haveExpiredRec) {
        Map status = MapUtil.getInstance().addParms("status", 2).build();
        Observable head = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.COUPONSTATUS, status, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(head, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("haveExpiredResult---------->" + result);
                final List<CouponBean> couponBeans = JSON.parseArray(result, CouponBean.class);

                HaveExpiredAdapter haveExpiredAdapter = new HaveExpiredAdapter(mContext, couponBeans, R.layout.item_have_expired_rec);
                haveExpiredRec.setAdapter(haveExpiredAdapter);
                haveExpiredAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                    @Override
                    public void ViewOnClick(View view, final int index) {
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ARouter.getInstance().build("/module_user_store/ShopHomeActivity").withString("sellerId", couponBeans.get(index).getSellerId()).navigation();
                            }
                        });
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("haveExpiredErrorMsg---------->" + errorMsg);
            }
        }));

    }

    public void localGuoQiCoupon() {
        Map map = MapUtil.getInstance().addParms("status", "2").build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getHead(CommonResource.LOCAL_COUPON_LIST, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("可用红包：" + result);
                List<RedPackageBean> redPackageBeans = JSON.parseArray(result, RedPackageBean.class);
                CouponWalletAdapter walletAdapter = new CouponWalletAdapter(mContext, redPackageBeans, R.layout.rv_coupon_wallet);
                if (getView() != null) {
                    getView().loadRv(walletAdapter);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------" + errorMsg);
            }
        }));
    }
}
