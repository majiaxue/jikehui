package com.example.predict.tb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bean.PredictBean;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TBFragment extends BaseFragment<TBView, TBPresenter> implements TBView {
    @BindView(R2.id.predict_total_money)
    TextView predictTotalMoney;
    @BindView(R2.id.predict_ben_jiesuan)
    TextView predictBenJiesuan;
    @BindView(R2.id.predict_shang_jiesuan)
    TextView predictShangJiesuan;
    @BindView(R2.id.predict_temp)
    View predictTemp;
    @BindView(R2.id.predict_ben_fukuan)
    TextView predictBenFukuan;
    @BindView(R2.id.predict_shang_fukuan)
    TextView predictShangFukuan;
    @BindView(R2.id.predict_temp2)
    View predictTemp2;
    @BindView(R2.id.predict_fukuanshu_jin)
    TextView predictFukuanshuJin;
    @BindView(R2.id.predict_yongjin_jin)
    TextView predictYongjinJin;
    @BindView(R2.id.predict_temp3)
    View predictTemp3;
    @BindView(R2.id.predict_fukuanshu_zuo)
    TextView predictFukuanshuZuo;
    @BindView(R2.id.predict_yongjin_zuo)
    TextView predictYongjinZuo;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_predict;
    }

    @Override
    public void initData() {
        presenter.loadData();
    }

    @Override
    public void initClick() {

    }

    @Override
    public void loadUI(PredictBean predictBean) {
        predictTotalMoney.setText(predictBean.getTotalAmount());
        predictBenJiesuan.setText(predictBean.getSettleCurrentMonth());
        predictShangJiesuan.setText(predictBean.getSettleLastMonth());
        predictBenFukuan.setText(predictBean.getWaitCurrentMonth());
        predictShangFukuan.setText(predictBean.getWaitLastMonth());
        predictFukuanshuJin.setText(predictBean.getTodayPayCount());
        predictFukuanshuZuo.setText(predictBean.getLastDayPayCount());
        predictYongjinJin.setText(predictBean.getTodayMoney());
        predictYongjinZuo.setText(predictBean.getLastDayMoney());
    }

    @Override
    public void loadUI() {
        predictTotalMoney.setText("0");
        predictBenJiesuan.setText("0");
        predictShangJiesuan.setText("0");
        predictBenFukuan.setText("0");
        predictShangFukuan.setText("0");
        predictFukuanshuJin.setText("0");
        predictFukuanshuZuo.setText("0");
        predictYongjinJin.setText("0");
        predictYongjinZuo.setText("0");
    }

    @Override
    public TBView createView() {
        return this;
    }

    @Override
    public TBPresenter createPresenter() {
        return new TBPresenter(getContext());
    }
}
