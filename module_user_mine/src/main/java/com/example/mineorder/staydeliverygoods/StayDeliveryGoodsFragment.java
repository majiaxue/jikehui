package com.example.mineorder.staydeliverygoods;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mineorder.adapter.MineOrderParentAdapter;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.LogUtil;

import butterknife.BindView;

/**
 * 待收货
 */
public class StayDeliveryGoodsFragment extends BaseFragment<StayDeliveryGoodsView, StayDeliveryGoodsPresenter> implements StayDeliveryGoodsView {

    @BindView(R2.id.stay_delivery_goods_rec)
    RecyclerView stayDeliveryGoodsRec;
    private int flag = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_stay_delivery_goods;
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        stayDeliveryGoodsRec.setLayoutManager(linearLayoutManager);
        presenter.stayDeliveryGoodsRec();
        flag = 1;
    }

    @Override
    public void initClick() {

    }

    @Override
    public StayDeliveryGoodsView createView() {
        return this;
    }

    @Override
    public StayDeliveryGoodsPresenter createPresenter() {
        return new StayDeliveryGoodsPresenter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("setUserVisibleHint-------->待收货当前可见");
        if (flag == 1){
            presenter.stayDeliveryGoodsRec();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.e("11111111111" + isVisibleToUser);
        if (isVisibleToUser) {
            if (flag == 1) {
                presenter.stayDeliveryGoodsRec();
            }
        }
    }

    @Override
    public void load(MineOrderParentAdapter mineOrderParentAdapter) {
        stayDeliveryGoodsRec.setAdapter(mineOrderParentAdapter);
    }
}
