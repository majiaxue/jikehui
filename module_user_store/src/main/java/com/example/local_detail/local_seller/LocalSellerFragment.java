package com.example.local_detail.local_seller;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.bean.LocalShopBean;
import com.example.local_detail.adapter.SellerImaAdapter;
import com.example.mvp.BaseFragment;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

public class LocalSellerFragment extends BaseFragment<LocalSellerView, LocalSellerPresenter> implements LocalSellerView {
    @BindView(R2.id.local_detail_seller_rv)
    RecyclerView localDetailSellerRv;
    @BindView(R2.id.local_detail_seller_name)
    TextView localDetailSellerName;
    @BindView(R2.id.local_detail_seller_type)
    TextView localDetailSellerType;
    @BindView(R2.id.local_detail_seller_address)
    TextView localDetailSellerAddress;
    @BindView(R2.id.local_detail_seller_phone)
    TextView localDetailSellerPhone;
    @BindView(R2.id.local_detail_seller_time)
    TextView localDetailSellerTime;

    private static LocalShopBean bean;

    public static LocalSellerFragment getInstance(LocalShopBean shopBean) {
        bean = shopBean;
        return new LocalSellerFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_local_seller;
    }

    @Override
    public void initData() {
        localDetailSellerName.setText(bean.getSeller_name());
        localDetailSellerType.setText(bean.getSeller_type());
        localDetailSellerAddress.setText(bean.getSeller_addredd());
        localDetailSellerPhone.setText(bean.getSeller_phone());
        localDetailSellerTime.setText(bean.getSeller_business_hours());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        localDetailSellerRv.setLayoutManager(linearLayoutManager);
        localDetailSellerRv.addItemDecoration(new SpaceItemDecoration(0, (int) getContext().getResources().getDimension(R.dimen.dp_8), 0, 0));

        presenter.loadData(bean.getSellerpics());
    }

    @Override
    public void initClick() {

    }

    @Override
    public void loadImg(SellerImaAdapter adapter) {
        localDetailSellerRv.setAdapter(adapter);
    }

    @Override
    public LocalSellerView createView() {
        return this;
    }

    @Override
    public LocalSellerPresenter createPresenter() {
        return new LocalSellerPresenter(getContext());
    }
}
