package com.example.operator_gain;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioButton;

import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.operator_gain.adapter.OperatorGainBottomAdapter;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

public class OperatorGainFragment extends BaseFragment<OperatorGainView, OperatorGainPresenter> implements OperatorGainView {
    @BindView(R2.id.operator_gain_vp)
    ViewPager operatorGainVp;
    @BindView(R2.id.operator_gain_goods)
    RecyclerView operatorGainGoods;
    @BindView(R2.id.operator_gain_scroll)
    NestedScrollView operatorGainScroll;
    @BindView(R2.id.operator_gain_rbtn1)
    RadioButton operatorGainRbtn1;
    @BindView(R2.id.operator_gain_rbtn2)
    RadioButton operatorGainRbtn2;
    @BindView(R2.id.operator_gain_rbtn3)
    RadioButton operatorGainRbtn3;

    private boolean isFirst = true;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_operator_gain;
    }

    @Override
    public void initData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        operatorGainGoods.setLayoutManager(gridLayoutManager);
        operatorGainGoods.addItemDecoration(new SpaceItemDecoration(0, (int) getResources().getDimension(R.dimen.dp_15), 0, (int) getResources().getDimension(R.dimen.dp_10)));

    }

    @Override
    public void initClick() {
        operatorGainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    operatorGainRbtn1.setChecked(true);
                } else if (i == 1) {
                    operatorGainRbtn2.setChecked(true);
                } else if (i == 2) {
                    operatorGainRbtn3.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (isFirst) {
                presenter.loadQuanyi();
                presenter.loadData();
            }
            isFirst = false;
        }
    }

    @Override
    public void loadQuanyi(OperatorGainBottomAdapter adapter) {
        operatorGainGoods.setAdapter(adapter);
    }

    @Override
    public void loadVp(PagerAdapter adapter) {
        operatorGainVp.setAdapter(adapter);
    }

    @Override
    public OperatorGainView createView() {
        return this;
    }

    @Override
    public OperatorGainPresenter createPresenter() {
        return new OperatorGainPresenter(getContext());
    }
}
