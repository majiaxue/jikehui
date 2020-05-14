package com.example.coupon_wallet;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.adapter.CouponWalletAdapter;
import com.example.module_local.R;
import com.example.module_local.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

@Route(path = "/module_local/CouponWalletActivity")
public class CouponWalletActivity extends BaseActivity<CouponWalletView, CouponWalletPresenter> implements CouponWalletView {
    @BindView(R2.id.coupon_wallet_back)
    ImageView couponWalletBack;
    @BindView(R2.id.coupon_wallet_rv)
    RecyclerView couponWalletRv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_coupon_wallet;
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        couponWalletRv.setLayoutManager(linearLayoutManager);
        couponWalletRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_10)));
        presenter.loadData();
    }

    @Override
    public void initClick() {
        couponWalletBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void loadRv(CouponWalletAdapter adapter) {
        couponWalletRv.setAdapter(adapter);
    }

    @Override
    public CouponWalletView createView() {
        return this;
    }

    @Override
    public CouponWalletPresenter createPresenter() {
        return new CouponWalletPresenter(this);
    }
}
