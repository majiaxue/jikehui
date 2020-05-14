package com.example.flashsale;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_home.R;
import com.example.mvp.BaseActivity;
@Route(path = "/module_home/FlashSaleActivity")
public class FlashSaleActivity extends BaseActivity<FlashSaleView,FlashSalePresenter> implements FlashSaleView {

    @Override
    public int getLayoutId() {
        return R.layout.activity_flash_sale;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public FlashSaleView createView() {
        return this;
    }

    @Override
    public FlashSalePresenter createPresenter() {
        return new FlashSalePresenter(this);
    }
}
