package com.example.coupon.all;

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
import com.example.coupon.adapter.CouponAdapter;
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
public class AllPresenter extends BasePresenter<AllView> {


    public AllPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void allRec(final RecyclerView allRec) {
        Map status = MapUtil.getInstance().addParms("status", 0).build();
        Observable head = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.COUPONSTATUS, status, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(head, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("AllResult-------->" + result);
                final List<CouponBean> couponBeans = JSON.parseArray(result, CouponBean.class);

                CouponAdapter couponAdapter = new CouponAdapter(mContext, couponBeans, R.layout.item_coupon_rec);
                allRec.setAdapter(couponAdapter);

                couponAdapter.setViewTwoOnClickListener(new MyRecyclerAdapter.ViewTwoOnClickListener() {
                    @Override
                    public void ViewTwoOnClick(View view1, View view2, final int position) {
                        view1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ARouter.getInstance().build("/module_user_store/ShopHomeActivity").withString("sellerId", couponBeans.get(position).getSellerId()).navigation();
                            }
                        });

                        view2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ARouter.getInstance().build("/module_user_store/ShopHomeActivity").withString("sellerId", couponBeans.get(position).getSellerId()).navigation();
                            }
                        });
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("AllErrorMsg-------->" + errorMsg);
            }
        }));
    }

    public void localMyCoupon() {
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
