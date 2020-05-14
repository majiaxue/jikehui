package com.example.balance;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.fastjson.JSON;
import com.example.balance.income.IncomeFragment;
import com.example.balance.payout.PayoutFragment;
import com.example.bean.BalanceBean;
import com.example.cashout.CashoutActivity;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class BalancePresenter extends BasePresenter<BalanceView> {

    private FragmentManager fragmentManager;
    private IncomeFragment incomeFragment;
    private PayoutFragment payoutFragment;

    public BalancePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.GETBALANCE, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("余额：" + result);
                BalanceBean balanceBean = JSON.parseObject(result, BalanceBean.class);
                if (getView() != null) {
                    getView().loadBalance(balanceBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void jumpToCashout() {
        mContext.startActivity(new Intent(mContext, CashoutActivity.class));
    }

    public void initFragment(FragmentManager fragmentManager, int resId) {
        this.fragmentManager = fragmentManager;
        incomeFragment = new IncomeFragment();
        payoutFragment = new PayoutFragment();
        FragmentTransaction transaction = this.fragmentManager.beginTransaction();
        transaction.add(resId, incomeFragment)
                .add(resId, payoutFragment)
                .show(incomeFragment)
                .hide(payoutFragment)
                .commit();
    }

    public void changeView(int i) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (i == 1) {
            transaction.show(incomeFragment)
                    .hide(payoutFragment)
                    .commit();
        } else {
            transaction.show(payoutFragment)
                    .hide(incomeFragment)
                    .commit();
        }
    }
}
