package com.example.coupon.haveexpired;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adapter.CouponWalletAdapter;
import com.example.common.CommonResource;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragment;

import butterknife.BindView;

public class HaveExpiredFragment extends BaseFragment<HaveExpiredView, HaveExpiredPresenter> implements HaveExpiredView {

    @BindView(R2.id.have_expired_rec)
    RecyclerView haveExpiredRec;

    private String from;

    public HaveExpiredFragment() {
    }

    @SuppressLint("ValidFragment")
    public HaveExpiredFragment(String from) {
        this.from = from;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_have_expired_;
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        haveExpiredRec.setLayoutManager(linearLayoutManager);

        if (CommonResource.HISTORY_LOCAL.equals(from)) {
            presenter.localGuoQiCoupon();
        } else {
            presenter.haveExpiredRec(haveExpiredRec);
        }
    }

    @Override
    public void initClick() {

    }

    @Override
    public void loadRv(CouponWalletAdapter adapter) {
        haveExpiredRec.setAdapter(adapter);
    }

    @Override
    public HaveExpiredView createView() {
        return this;
    }

    @Override
    public HaveExpiredPresenter createPresenter() {
        return new HaveExpiredPresenter(getContext());
    }

}
