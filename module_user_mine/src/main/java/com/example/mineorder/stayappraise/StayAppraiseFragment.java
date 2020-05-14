package com.example.mineorder.stayappraise;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mineorder.stayappraise.adapter.StayAppraiseParentAdapter;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.LogUtil;

import butterknife.BindView;

/**
 * 待评价
 */
public class StayAppraiseFragment extends BaseFragment<StayAppraiseView, StayAppraisePresenter> implements StayAppraiseView {


    @BindView(R2.id.stay_appraise_rec)
    RecyclerView stayAppraiseRec;
    private int flag = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_stay_appraise;
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        stayAppraiseRec.setLayoutManager(linearLayoutManager);
        presenter.stayAppraiseRec();
        flag = 1;
    }

    @Override
    public void initClick() {

    }

    @Override
    public StayAppraiseView createView() {
        return this;
    }

    @Override
    public StayAppraisePresenter createPresenter() {
        return new StayAppraisePresenter(getContext());
    }


    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("setUserVisibleHint-------->待评价当前可见");
        if (flag == 1) {
            presenter.stayAppraiseRec();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (flag == 1) {
                presenter.stayAppraiseRec();
            }
        }
    }

    @Override
    public void load(StayAppraiseParentAdapter stayAppraiseParentAdapter) {
        stayAppraiseRec.setAdapter(stayAppraiseParentAdapter);
    }
}
