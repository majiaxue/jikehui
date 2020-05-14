package com.example.shop_home.first_page;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mvp.BaseFragment;
import com.example.shop_home.adapter.FirstCouponAdapter;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

public class ShopFirstFragment extends BaseFragment<ShopFirstView, ShopFirstPresenter> implements ShopFirstView {
    @BindView(R2.id.shop_first_rv)
    RecyclerView shopFirstRv;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_first;
    }

    @Override
    public void initData() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        shopFirstRv.setLayoutManager(layoutManager);
        shopFirstRv.addItemDecoration(new SpaceItemDecoration(0, (int) getContext().getResources().getDimension(R.dimen.dp_8), 0, 0));
        presenter.loadData();
    }

    @Override
    public void initClick() {

    }

    @Override
    public void loadRv(FirstCouponAdapter adapter) {
        shopFirstRv.setAdapter(adapter);
    }

    @Override
    public ShopFirstView createView() {
        return this;
    }

    @Override
    public ShopFirstPresenter createPresenter() {
        return new ShopFirstPresenter(getContext());
    }
}
