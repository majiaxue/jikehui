package com.example.order.fragment_all;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.order.OrderActivity;
import com.example.order.adapter.JDAdapter;
import com.example.order.adapter.RvListAdapter;
import com.example.order.adapter.TBAdapter;
import com.example.utils.LogUtil;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

public class AllOrderFragment extends BaseFragment<AllOrderView, AllOrderPresenter> implements AllOrderView {
    @BindView(R2.id.order_list_rv)
    RecyclerView orderListRv;

    private int flag = 0;

    private static AllOrderFragment fragment;
    private LinearLayoutManager layoutManager;

    public static AllOrderFragment getInstance() {
        if (fragment == null) {
            synchronized (AllOrderFragment.class) {
                if (fragment == null) {
                    fragment = new AllOrderFragment();
                }
            }
        }
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_list;
    }

    @Override
    public void initData() {
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderListRv.setLayoutManager(layoutManager);
        orderListRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getContext().getResources().getDimension(R.dimen.dp_10)));
        presenter.loadData(1);

    }

    @Override
    public void initClick() {

    }

    public void addFlag() {
        flag++;
    }

    public void setOrign(int index) {
        presenter.loadData(index);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (flag > 0) {
                presenter.loadData(OrderActivity.index);
            } else {
                flag++;
            }
        }
    }

    @Override
    public void moveTo(int flag) {
        layoutManager.scrollToPosition(flag);
    }

    @Override
    public void loadMineRv(RvListAdapter adapter) {
        orderListRv.setAdapter(adapter);
    }

    @Override
    public void loadJD(JDAdapter adapter) {
        orderListRv.setAdapter(adapter);
    }

    @Override
    public void loadTB(TBAdapter adapter) {
        orderListRv.setAdapter(adapter);
    }

    @Override
    public AllOrderView createView() {
        return this;
    }

    @Override
    public AllOrderPresenter createPresenter() {
        return new AllOrderPresenter(getContext());
    }
}
