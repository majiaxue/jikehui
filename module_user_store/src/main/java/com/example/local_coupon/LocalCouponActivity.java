package com.example.local_coupon;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.local_coupon.adapter.LocalCouponAdapter;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;

import butterknife.BindView;

public class LocalCouponActivity extends BaseActivity<LocalCouponView, LocalCouponPresenter> implements LocalCouponView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.local_coupon_rv)
    RecyclerView localCouponRv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_coupon;
    }

    @Override
    public void initData() {
        includeTitle.setText("我的优惠券");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        localCouponRv.setLayoutManager(layoutManager);
        presenter.loadData();
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void loadCoupon(LocalCouponAdapter adapter) {
        localCouponRv.setAdapter(adapter);
    }

    @Override
    public LocalCouponView createView() {
        return this;
    }

    @Override
    public LocalCouponPresenter createPresenter() {
        return new LocalCouponPresenter(this);
    }
}
