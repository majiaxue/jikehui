package com.example.balance;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.bean.BalanceBean;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragmentActivity;

import butterknife.BindView;

@Route(path = "/mine/balance")
public class BalanceActivity extends BaseFragmentActivity<BalanceView, BalancePresenter> implements BalanceView {

    @BindView(R2.id.balance_total_money)
    TextView balanceTotalMoney;
    @BindView(R2.id.balance_total_back)
    ImageView includeBack;
    @BindView(R2.id.balance_tixian)
    TextView balanceTixian;
    @BindView(R2.id.balance_ljsy)
    TextView balanceLjsy;
    @BindView(R2.id.balance_srjl_check)
    ImageView balanceSrjlCheck;
    @BindView(R2.id.balance_srjl)
    LinearLayout balanceSrjl;
    @BindView(R2.id.balance_zcjl_check)
    ImageView balanceZcjlCheck;
    @BindView(R2.id.balance_zcjl)
    LinearLayout balanceZcjl;
    @BindView(R2.id.balance_srjl_txt)
    TextView balanceSrjlTxt;
    @BindView(R2.id.balance_zcjl_txt)
    TextView balanceZcjlTxt;

    @Override
    public int getLayoutId() {
        return R.layout.activity_balance;
    }

    @Override
    public void initData() {
        presenter.initFragment(getSupportFragmentManager(), R.id.balance_frame);

        presenter.loadData();
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        balanceTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToCashout();
            }
        });

        balanceSrjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balanceSrjlCheck.setImageResource(R.drawable.circle_16_fb4119);
                balanceSrjlTxt.setTextColor(Color.parseColor("#f23d3d"));
                balanceZcjlCheck.setImageResource(R.drawable.circle_16_border_000);
                balanceZcjlTxt.setTextColor(Color.parseColor("#333333"));
                presenter.changeView(1);
            }
        });

        balanceZcjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balanceZcjlCheck.setImageResource(R.drawable.circle_16_fb4119);
                balanceSrjlTxt.setTextColor(Color.parseColor("#333333"));
                balanceSrjlCheck.setImageResource(R.drawable.circle_16_border_000);
                balanceZcjlTxt.setTextColor(Color.parseColor("#f23d3d"));
                presenter.changeView(0);
            }
        });
    }

    @Override
    public void loadBalance(BalanceBean balanceBean) {
        balanceTotalMoney.setText("￥" + balanceBean.getTotalblance());
        balanceLjsy.setText("￥" + balanceBean.getHistoryBalance());
    }

    @Override
    public BalanceView createView() {
        return this;
    }

    @Override
    public BalancePresenter createPresenter() {
        return new BalancePresenter(this);
    }
}
