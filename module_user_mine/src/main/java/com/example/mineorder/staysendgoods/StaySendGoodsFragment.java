package com.example.mineorder.staysendgoods;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mineorder.adapter.MineOrderParentAdapter;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.LogUtil;

import butterknife.BindView;

/**
 * 待发货
 */
public class StaySendGoodsFragment extends BaseFragment<StaySendGoodsView, StaySendGoodsPresenter> implements StaySendGoodsView {


    @BindView(R2.id.stay_send_goods_rec)
    RecyclerView staySendGoodsRec;

    private int flag = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_stay_send_goods;
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        staySendGoodsRec.setLayoutManager(linearLayoutManager);
        presenter.staySendGoodsRec();
        flag = 1;
    }

    @Override
    public void initClick() {

    }

    @Override
    public StaySendGoodsView createView() {
        return this;
    }

    @Override
    public StaySendGoodsPresenter createPresenter() {
        return new StaySendGoodsPresenter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("setUserVisibleHint-------->待发货当前可见");
        if (flag == 1) {
            presenter.staySendGoodsRec();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.e("11111111111" + isVisibleToUser);
        if (isVisibleToUser) {
            if (flag == 1) {
                presenter.staySendGoodsRec();
            }
        }
    }

    @Override
    public void load(MineOrderParentAdapter mineOrderParentAdapter) {
        staySendGoodsRec.setAdapter(mineOrderParentAdapter);
    }
}
