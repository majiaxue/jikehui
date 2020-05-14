package com.example.community;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.View;

import com.example.adapter.BaseVPAdapter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;

import butterknife.BindView;

public class CommunityFragment extends BaseFragment<CommunityView, CommunityPresenter> implements CommunityView {
    @BindView(R2.id.community_tab)
    TabLayout communityTab;
    @BindView(R2.id.community_vp)
    ViewPager communityVp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_community;
    }

    @Override
    public void initData() {
        communityTab.setupWithViewPager(communityVp);
        presenter.setTab(communityTab);
        presenter.initVp(getActivity().getSupportFragmentManager());
    }

    @Override
    public void initClick() {

    }

    @Override
    public void updateVP(BaseVPAdapter adapter) {
        communityVp.setAdapter(adapter);
    }

    @Override
    public CommunityView createView() {
        return this;
    }

    @Override
    public CommunityPresenter createPresenter() {
        return new CommunityPresenter(getContext());
    }

}
