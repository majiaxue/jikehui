package com.example.operator;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ali.auth.third.ui.context.CallbackContext;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.operator.adapter.YysQuanyiAdapter;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

@Route(path = "/mine/operator")
public class OperatorActivity extends BaseActivity<OperatorView, OperatorPresenter> implements OperatorView {
    @BindView(R2.id.operator_back)
    ImageView operatorBack;
    @BindView(R2.id.operator_vp)
    ViewPager operatorVp;
    @BindView(R2.id.operator_factor)
    TextView operatorFactor;
    @BindView(R2.id.operator_goods)
    RecyclerView mGoods;
    @BindView(R2.id.operator_rbtn1)
    RadioButton operatorRbtn1;
    @BindView(R2.id.operator_rbtn2)
    RadioButton operatorRbtn2;
    @BindView(R2.id.operator_rbtn3)
    RadioButton operatorRbtn3;


    @Override
    public int getLayoutId() {
        return R.layout.activity_operator;
    }

    @Override
    public void initData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mGoods.setLayoutManager(gridLayoutManager);
        mGoods.addItemDecoration(new SpaceItemDecoration(0, (int) getResources().getDimension(R.dimen.dp_10), 0, (int) getResources().getDimension(R.dimen.dp_10)));

        presenter.loadData();
        presenter.loadQuanyi();

    }

    @Override
    public void initClick() {
        operatorBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        operatorVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                presenter.getFactor(i);
                if (i == 0) {
                    operatorRbtn1.setChecked(true);
                } else if (i == 1) {
                    operatorRbtn2.setChecked(true);
                } else if (i == 2) {
                    operatorRbtn3.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CallbackContext.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void loadFactor(String s) {
        operatorFactor.setText(s);
    }

    @Override
    public void loadVp(PagerAdapter adapter) {
        operatorVp.setAdapter(adapter);
    }

    @Override
    public void loadQuanyi(YysQuanyiAdapter adapter) {
        mGoods.setAdapter(adapter);
    }

    @Override
    public OperatorView createView() {
        return this;
    }

    @Override
    public OperatorPresenter createPresenter() {
        return new OperatorPresenter(this);
    }
}
