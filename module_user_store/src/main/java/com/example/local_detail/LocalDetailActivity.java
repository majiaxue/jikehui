package com.example.local_detail;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adapter.BaseVPAdapter;
import com.example.bean.LocalShopBean;
import com.example.local_shop.adapter.ManJianAdapter;
import com.example.mvp.BaseFragmentActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.SpaceItemDecoration;
import com.example.view.RatingBarView;

import butterknife.BindView;

public class LocalDetailActivity extends BaseFragmentActivity<LocalDetailView, LocalDetailPresenter> implements LocalDetailView {

    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.local_detail_banner)
    ImageView localDetailBanner;
    @BindView(R2.id.local_detail_name)
    TextView localDetailName;
    @BindView(R2.id.local_detail_star)
    RatingBarView localDetailStar;
    @BindView(R2.id.local_detail_type)
    TextView localDetailType;
    @BindView(R2.id.local_detail_time)
    TextView localDetailTime;
    @BindView(R2.id.local_detail_address)
    TextView localDetailAddress;
    @BindView(R2.id.local_detail_choose_address)
    ImageView localDetailChooseAddress;
    @BindView(R2.id.local_detail_phone)
    ImageView localDetailPhone;
    @BindView(R2.id.local_detail_lingquan)
    RelativeLayout localDetailLingquan;
    @BindView(R2.id.local_detail_coupon_rv)
    RecyclerView localDetailCouponRv;
    @BindView(R2.id.local_detail_my_coupon)
    LinearLayout localDetailMyCoupon;
    @BindView(R2.id.local_detail_tab)
    TabLayout localDetailTab;
    @BindView(R2.id.local_detail_vp)
    ViewPager localDetailVp;
    @BindView(R2.id.local_detail_lingquan2)
    LinearLayout mLingQuan;
    @BindView(R2.id.local_detail_map)
    LinearLayout mMap;

    private LocalShopBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_detail;
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        bean = (LocalShopBean) intent.getSerializableExtra("bean");
        includeTitle.setText("商品详情");
        localDetailName.setText(bean.getSeller_shop_name());
        localDetailStar.setClickable(false);
        localDetailStar.setStar(bean.getStar(), false);
        localDetailType.setText(bean.getSeller_type());
        localDetailTime.setText("营业时间：" + bean.getSeller_business_hours());
        localDetailAddress.setText(bean.getSeller_addredd());

        if (bean.getSellerpics() != null) {
            String[] split = bean.getSellerpics().split(",");
            Glide.with(this).load(split[0]).into(localDetailBanner);
        }


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        localDetailCouponRv.setLayoutManager(linearLayoutManager);
        localDetailCouponRv.addItemDecoration(new SpaceItemDecoration((int) getResources().getDimension(R.dimen.dp_10), 0, 0, 0));

        localDetailTab.setupWithViewPager(localDetailVp);
        presenter.initTab(localDetailTab);
        presenter.initVp(getSupportFragmentManager(), bean);
        presenter.loadData(bean.getCouponList());

        localDetailStar.setStar(4, false);
        localDetailStar.setClickable(false);
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        localDetailLingquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.lingquan(bean.getCouponList());
            }
        });

        mLingQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.lingquan(bean.getCouponList());
            }
        });

        localDetailPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callPhone();
            }
        });

        localDetailMyCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToCoupon();
            }
        });

        mMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToMap(bean);
            }
        });
    }

    @Override
    public void loadManJian(ManJianAdapter adapter) {
        localDetailCouponRv.setAdapter(adapter);
    }

    @Override
    public void updateVP(BaseVPAdapter vpAdapter) {
        localDetailVp.setAdapter(vpAdapter);
    }

    @Override
    public LocalDetailView createView() {
        return this;
    }

    @Override
    public LocalDetailPresenter createPresenter() {
        return new LocalDetailPresenter(this);
    }
}
