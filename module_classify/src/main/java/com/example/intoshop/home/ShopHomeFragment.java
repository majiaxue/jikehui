package com.example.intoshop.home;

import com.example.module_classify.R;
import com.example.mvp.BaseFragment;

/**
 * Created by cuihaohao on 2019/5/21
 * Describe:
 */
public class ShopHomeFragment extends BaseFragment<ShopHomeView, ShopHomePresenter> implements ShopHomeView {



    @Override
    public int getLayoutId() {
        return R.layout.fragment_into_shop_home;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public ShopHomeView createView() {
        return this;
    }

    @Override
    public ShopHomePresenter createPresenter() {
        return new ShopHomePresenter(getContext());
    }
}
