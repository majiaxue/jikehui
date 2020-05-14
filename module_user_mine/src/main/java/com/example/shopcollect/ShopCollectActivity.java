package com.example.shopcollect;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.view.SlideRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 店铺收藏
 */
@Route(path = "/module_user_mine/ShopCollectActivity")
public class ShopCollectActivity extends BaseActivity<ShopCollectView, ShopCollectPresenter> implements ShopCollectView {

    @BindView(R2.id.shop_collect_rec)
    SlideRecyclerView shopCollectRec;
    @BindView(R2.id.shop_collect_bottom_rec)
    RecyclerView shopCollectBottomRec;
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_collect;
    }

    @Override
    public void initData() {
        includeTitle.setText("店铺收藏");
        presenter.shopCollectBottomRec(shopCollectBottomRec);
    }

    @Override
    public void initClick() {
        //为你推荐
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public ShopCollectView createView() {
        return this;
    }

    @Override
    public ShopCollectPresenter createPresenter() {
        return new ShopCollectPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //店铺
        presenter.initShopCollectRec(shopCollectRec);
    }
}
