package com.example.mineorder.orderall;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mineorder.adapter.MineOrderParentAdapter;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.LogUtil;

import butterknife.BindView;

/**
 * 全部订单
 */
public class OrderAllFragment extends BaseFragment<OrderAllView, OrderAllPresenter> implements OrderAllView {

    @BindView(R2.id.order_all_rec)
    RecyclerView orderAllRec;
    private int flag = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_all;
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        orderAllRec.setLayoutManager(linearLayoutManager);
        presenter.orderAllRec();
        flag = 1;
    }

    @Override
    public void initClick() {

    }

    @Override
    public OrderAllView createView() {
        return this;
    }

    @Override
    public OrderAllPresenter createPresenter() {
        return new OrderAllPresenter(getContext());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (flag == 1) {
                presenter.orderAllRec();
            }
        }
    }

    @Override
    public void load(MineOrderParentAdapter mineOrderParentAdapter) {
        orderAllRec.setAdapter(mineOrderParentAdapter);
    }
}
