package com.example.mineorder.stayobligation;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mineorder.adapter.MineOrderParentAdapter;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.LogUtil;

import butterknife.BindView;

/**
 * 待付款
 */
public class StayObligationFragment extends BaseFragment<StayObligationView, StayObligationPresenter> implements StayObligationView {


    @BindView(R2.id.stay_obligation_rec)
    RecyclerView stayObligationRec;
    private int flag = 0;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_stay_obligation;
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        stayObligationRec.setLayoutManager(linearLayoutManager);
        presenter.stayObligationRec();
        flag = 1;
    }

    @Override
    public void initClick() {

    }

    @Override
    public StayObligationView createView() {
        return this;
    }

    @Override
    public StayObligationPresenter createPresenter() {
        return new StayObligationPresenter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("setUserVisibleHint-------->待付款当前可见");
        if (flag == 1){
            presenter.stayObligationRec();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.e("11111111111" + isVisibleToUser);
        if (isVisibleToUser) {
            if (flag == 1){
                presenter.stayObligationRec();
            }
        }
    }

    @Override
    public void load(MineOrderParentAdapter mineOrderParentAdapter) {
        stayObligationRec.setAdapter(mineOrderParentAdapter);
    }
}
