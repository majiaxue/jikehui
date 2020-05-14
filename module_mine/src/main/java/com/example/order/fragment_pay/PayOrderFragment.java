package com.example.order.fragment_pay;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.order.adapter.JDAdapter;
import com.example.order.adapter.RvListAdapter;
import com.example.order.adapter.TBAdapter;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

public class PayOrderFragment extends BaseFragment<PayOrderView, PayOrderPresenter> implements PayOrderView {
    @BindView(R2.id.order_list_rv)
    RecyclerView orderListRv;

    private static PayOrderFragment fragment;
    private int flag = 0;
    private LinearLayoutManager layoutManager;


    public static PayOrderFragment getInstance() {
        if (fragment == null) {
            synchronized (PayOrderFragment.class) {
                if (fragment == null) {
                    fragment = new PayOrderFragment();
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
        flag++;
        presenter.loadData();
    }

    @Override
    public void initClick() {

    }

    public void addFlag() {
        flag++;
    }

    @Override
    public void moveTo(int flag) {
        layoutManager.scrollToPosition(flag);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (flag > 0) {
                try {
                    Thread.sleep(1000);
                    presenter.loadData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                flag++;
            }
        }
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
    public PayOrderView createView() {
        return this;
    }

    @Override
    public PayOrderPresenter createPresenter() {
        return new PayOrderPresenter(getContext());
    }
}
