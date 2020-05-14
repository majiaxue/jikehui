package com.example.coupon;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.BaseVPAdapter;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragmentActivity;

import butterknife.BindView;

/**
 * 优惠劵
 */
@Route(path = "/module_user_mine/CouponActivity")
public class CouponActivity extends BaseFragmentActivity<CouponView, CouponPresenter> implements CouponView {

    @BindView(R2.id.coupon_tab)
    TabLayout couponTab;
    @BindView(R2.id.coupon_vp)
    ViewPager couponVp;
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;

    @Autowired(name = "from")
    String from;

    @Override
    public int getLayoutId() {
        return R.layout.activity_coupon;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);

        includeTitle.setText("优惠券");
        //初始化tablayout
        presenter.initTabLayout(couponTab, from);
        presenter.initViewPager(getSupportFragmentManager());
        //预加载
        couponVp.setOffscreenPageLimit(2);
        //tablayout联动viewpager
        couponTab.setupWithViewPager(couponVp);
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
    public CouponView createView() {
        return this;
    }

    @Override
    public CouponPresenter createPresenter() {
        return new CouponPresenter(this);
    }

    @Override
    public void updateVp(BaseVPAdapter baseVPAdapter) {
        couponVp.setAdapter(baseVPAdapter);
    }
}
