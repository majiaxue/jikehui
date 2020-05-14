package com.example.shop_home.first_page;

import android.content.Context;

import com.example.bean.ParmsBean;
import com.example.mvp.BasePresenter;
import com.example.shop_home.adapter.FirstCouponAdapter;
import com.example.user_store.R;

import java.util.ArrayList;
import java.util.List;

public class ShopFirstPresenter extends BasePresenter<ShopFirstView> {
    public ShopFirstPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        List<ParmsBean> dataList = new ArrayList<>();
        dataList.add(new ParmsBean("10", "满100元可用"));
        dataList.add(new ParmsBean("100", "满500元可用"));
        dataList.add(new ParmsBean("300", "满1000元可用"));
        FirstCouponAdapter firstCouponAdapter = new FirstCouponAdapter(mContext, dataList, R.layout.rv_shop_first);
        if (getView() != null) {
            getView().loadRv(firstCouponAdapter);
        }
    }
}
