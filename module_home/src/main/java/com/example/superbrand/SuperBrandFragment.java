package com.example.superbrand;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.superbrand.adapter.SuperBrandRecAdapter;
import com.example.utils.StatusBarUtils;

import butterknife.BindView;

public class SuperBrandFragment extends BaseFragment<SuperBrandView, SuperBrandPresenter> implements SuperBrandView {


    @BindView(R2.id.super_brand_tab)
    TabLayout superBrandTab;
    @BindView(R2.id.super_brand_viewpager)
    ViewPager superBrandViewpager;
    @BindView(R2.id.super_brand_rec)
    RecyclerView superBrandRec;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_super_brand;
    }

    @Override
    public void initData() {
        StatusBarUtils.setAndroidNativeLightStatusBar(getActivity(), false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4, LinearLayoutManager.VERTICAL, false);
        superBrandRec.setLayoutManager(gridLayoutManager);
        presenter.initView(superBrandTab, getChildFragmentManager(), superBrandViewpager);
    }

    @Override
    public void initClick() {

    }

    @Override
    public SuperBrandView createView() {
        return this;
    }

    @Override
    public SuperBrandPresenter createPresenter() {
        return new SuperBrandPresenter(getContext());
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //不可见
        } else {
            //可见
            StatusBarUtils.setAndroidNativeLightStatusBar(getActivity(), false);
            presenter.initList(0);

        }
    }

    @Override
    public void loadAdapter(SuperBrandRecAdapter superBrandRecAdapter) {
        superBrandRec.setAdapter(superBrandRecAdapter);
    }
}