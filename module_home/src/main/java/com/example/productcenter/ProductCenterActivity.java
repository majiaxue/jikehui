package com.example.productcenter;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;
import com.example.productcenter.adapter.ProductCenterAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import butterknife.BindView;

/**
 * 产品中心
 */
@Route(path = "/module_home/ProductCenterActivity")
public class ProductCenterActivity extends BaseActivity<ProductCenterView, ProductCenterPresenter> implements ProductCenterView {

    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.include_right)
    ImageView includeRight;
    @BindView(R2.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R2.id.product_center_tab)
    TabLayout productCenterTab;
    @BindView(R2.id.product_center_rec)
    RecyclerView productCenterRec;
    @BindView(R2.id.product_center_smart)
    SmartRefreshLayout productCenterSmart;

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_center;
    }

    @Override
    public void initData() {
        includeTitle.setText("产品中心");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        productCenterRec.setLayoutManager(linearLayoutManager);
        productCenterTab.setSelectedTabIndicatorHeight(0);
        presenter.initTab(productCenterTab, productCenterSmart);


        //设置 Header 为 贝塞尔雷达 样式
        productCenterSmart.setRefreshHeader(new MaterialHeader(this));
        //设置 Footer 为 球脉冲 样式
        productCenterSmart.setRefreshFooter(new ClassicsFooter(this));
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
    public ProductCenterView createView() {
        return this;
    }

    @Override
    public ProductCenterPresenter createPresenter() {
        return new ProductCenterPresenter(this);
    }

    @Override
    public void loadAdapter(ProductCenterAdapter productCenterAdapter) {
        productCenterRec.setAdapter(productCenterAdapter);
    }

    @Override
    public void loadRefresh() {
        productCenterSmart.finishRefresh();
        productCenterSmart.finishLoadMore();
    }
}
