package com.example.superbrand.handpick;

import com.example.module_home.R;
import com.example.mvp.BaseFragment;

public class HandPickFragment extends BaseFragment<HandPickView, HandPickPresenter> implements HandPickView {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hand_pick;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public HandPickView createView() {
        return this;
    }

    @Override
    public HandPickPresenter createPresenter() {
        return new HandPickPresenter(getContext());
    }
}
