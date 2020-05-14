package com.example.local_goods_detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_local.R;
import com.example.module_local.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

public class LocalGoodsDetailActivity extends BaseActivity<LocalGoodsDetailView, LocalGoodsDetailPresenter> implements LocalGoodsDetailView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.local_goods_detail_shop)
    TextView localGoodsDetailShop;
    @BindView(R2.id.local_goods_detail_rv_pic)
    RecyclerView localGoodsDetailRvPic;
    @BindView(R2.id.local_goods_detail_rules)
    TextView localGoodsDetailRules;
    @BindView(R2.id.local_goods_detail_money)
    TextView localGoodsDetailMoney;
    @BindView(R2.id.local_goods_detail_btn)
    TextView localGoodsDetailBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_goods_detail;
    }

    @Override
    public void initData() {
        includeTitle.setText("商品详情");
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
    public LocalGoodsDetailView createView() {
        return this;
    }

    @Override
    public LocalGoodsDetailPresenter createPresenter() {
        return new LocalGoodsDetailPresenter(this);
    }
}
