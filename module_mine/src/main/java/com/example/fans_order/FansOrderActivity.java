package com.example.fans_order;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.bean.FansOrderCensusBean;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragmentActivity;
import com.example.order.adapter.OrderVPAdapter;

import butterknife.BindView;

@Route(path = "/mine/fansorder")
public class FansOrderActivity extends BaseFragmentActivity<FansOrderView, FansOrderPresenter> implements FansOrderView {
    @BindView(R2.id.fans_order_back)
    ImageView mBack;
    @BindView(R2.id.fans_order_tb)
    TextView fansOrderTb;
    @BindView(R2.id.fans_order_pdd)
    TextView fansOrderPdd;
    @BindView(R2.id.fans_order_jd)
    TextView fansOrderJd;
    @BindView(R2.id.fans_order_sc)
    TextView fansOrderSc;
    @BindView(R2.id.fans_order_txt1)
    TextView fansOrderTxt1;
    @BindView(R2.id.fans_order_txt2)
    TextView fansOrderTxt2;
    @BindView(R2.id.fans_order_txt3)
    TextView fansOrderTxt3;
    @BindView(R2.id.fans_order_tab)
    TabLayout fansOrderTab;
    @BindView(R2.id.fans_order_viewpager)
    ViewPager fansOrderViewpager;

    public static int index = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fans_order;
    }

    @Override
    public void initData() {
        fansOrderTab.setupWithViewPager(fansOrderViewpager);
        presenter.initTabLayout(fansOrderTab);
        presenter.initViewPager(getSupportFragmentManager());
        fansOrderViewpager.setOffscreenPageLimit(3);
        presenter.loadData(index);
    }

    @Override
    public void initClick() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fansOrderTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 1;
                presenter.change(0, index);
                fansOrderTab.getTabAt(0).select();
            }
        });

        fansOrderPdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 3;
                presenter.change(1, index);
                fansOrderTab.getTabAt(0).select();
            }
        });

        fansOrderJd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 2;
                presenter.change(2, index);
                fansOrderTab.getTabAt(0).select();
            }
        });
    }

    @Override
    public void updateVP(OrderVPAdapter adapter) {
        fansOrderViewpager.setAdapter(adapter);
    }

    @Override
    public void typeChanged(int position) {
        fansOrderTb.setTextColor(Color.parseColor(position == 0 ? "#e74744" : "#ffffff"));
        fansOrderPdd.setTextColor(Color.parseColor(position == 1 ? "#e74744" : "#ffffff"));
        fansOrderJd.setTextColor(Color.parseColor(position == 2 ? "#e74744" : "#ffffff"));


        fansOrderTb.setBackgroundResource(position == 0 ? R.drawable.bg_fans_order_left : 0);
        fansOrderPdd.setBackgroundResource(position == 1 ? R.drawable.bg_fans_order_zhong : 0);
        fansOrderJd.setBackgroundResource(position == 2 ? R.drawable.bg_fans_order_right : 0);
    }

    @Override
    public void loadCensus(FansOrderCensusBean bean) {
        if (index == 3) {
            fansOrderTxt2.setText(bean.getTotalAmount() / 100 + "");
        } else {
            fansOrderTxt2.setText(bean.getTotalAmount() + "");
        }
        fansOrderTxt1.setText(bean.getTotalCount() + "");
        fansOrderTxt3.setText(bean.getTotalBackMoney() + "");

    }

    @Override
    public FansOrderView createView() {
        return this;
    }

    @Override
    public FansOrderPresenter createPresenter() {
        return new FansOrderPresenter(this);
    }
}
