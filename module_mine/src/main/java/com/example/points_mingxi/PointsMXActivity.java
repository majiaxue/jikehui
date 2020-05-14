package com.example.points_mingxi;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragmentActivity;
import com.example.order.adapter.OrderVPAdapter;

import butterknife.BindView;

public class PointsMXActivity extends BaseFragmentActivity<PointsMXView, PointsMXPresenter> implements PointsMXView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.points_mx_tab)
    TabLayout pointsMxTab;
    @BindView(R2.id.points_mx_vp)
    ViewPager pointsMxVp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_points_mx;
    }

    @Override
    public void initData() {
        includeTitle.setText("账户明细");
        pointsMxTab.setupWithViewPager(pointsMxVp);
        presenter.initTab(pointsMxTab);
        presenter.initVp(getSupportFragmentManager());
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
    public void updateVP(OrderVPAdapter vpAdapter) {
        pointsMxVp.setAdapter(vpAdapter);
    }

    @Override
    public PointsMXView createView() {
        return this;
    }

    @Override
    public PointsMXPresenter createPresenter() {
        return new PointsMXPresenter(this);
    }
}
