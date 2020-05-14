package com.example.coupon.all;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adapter.CouponWalletAdapter;
import com.example.common.CommonResource;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragment;

import butterknife.BindView;

public class AllFragment extends BaseFragment<AllView, AllPresenter> implements AllView {

    @BindView(R2.id.all_rec)
    RecyclerView allRec;
    @BindView(R2.id.all_go)
    TextView allGo;
    @BindView(R2.id.all_hide)
    LinearLayout allHide;

    private String from;

    public AllFragment() {
    }

    @SuppressLint("ValidFragment")
    public AllFragment(String from) {
        this.from = from;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        allRec.setLayoutManager(linearLayoutManager);
        if (CommonResource.HISTORY_LOCAL.equals(from)) {
            presenter.localMyCoupon();
        } else {
            presenter.allRec(allRec);
        }
    }

    @Override
    public void initClick() {

    }

    @Override
    public void loadRv(CouponWalletAdapter adapter) {

    }

    @Override
    public AllView createView() {
        return this;
    }

    @Override
    public AllPresenter createPresenter() {
        return new AllPresenter(getContext());
    }

}
