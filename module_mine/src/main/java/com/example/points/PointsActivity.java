package com.example.points;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.ali_account.AliAccountActivity;
import com.example.bean.MyPointsBean;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

@Route(path = "/mine/points")
public class PointsActivity extends BaseActivity<PointsView, PointsPresenter> implements PointsView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R2.id.points_total_points)
    TextView pointsTotalPoints;
    @BindView(R2.id.points_cashing_points)
    TextView pointsCashingPoints;
    @BindView(R2.id.points_zfb_account)
    TextView pointsZfbAccount;
    @BindView(R2.id.points_choose_zfb)
    RelativeLayout pointsChooseZfb;
    @BindView(R2.id.points_edit)
    EditText pointsEdit;
    @BindView(R2.id.points_my_points)
    TextView pointsMyPoints;
    @BindView(R2.id.points_toall)
    TextView pointsToall;
    @BindView(R2.id.points_rules)
    TextView pointsRules;
    @BindView(R2.id.points_btn)
    TextView pointsBtn;

    private MyPointsBean bean = new MyPointsBean();

    @Override
    public int getLayoutId() {
        return R.layout.activity_points;
    }

    @Override
    public void initData() {
        includeTitle.setText("积分提现");
        includeRightBtn.setText("明细");
        includeRightBtn.setVisibility(View.VISIBLE);
        presenter.getData();
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        includeRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToMingXi();
            }
        });

        pointsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.commit(pointsEdit.getText().toString());
            }
        });

        pointsToall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointsEdit.setText(bean.getMember().getIntegration() == null ? "0" : bean.getMember().getIntegration());
            }
        });

        pointsChooseZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(PointsActivity.this, AliAccountActivity.class), 100);
            }
        });

        pointsEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    if ("0".equals(s.toString())) {
                        pointsEdit.setText("");
                    } else if (bean.getMember().getIntegration() != null && Double.valueOf(bean.getMember().getIntegration()) != 0) {
                        if (Double.valueOf(s.toString()) > Double.valueOf(bean.getMember().getIntegration())) {
                            pointsEdit.setText(bean.getMember().getIntegration());
                            pointsEdit.setSelection(pointsEdit.getText().length());
                        }
                    } else {
                        pointsEdit.setText("");
                        Toast.makeText(PointsActivity.this, "积分不足", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("name");
            String account = data.getStringExtra("account");
            bean.getMember().setAliAccount(account);
            bean.getMember().setRealName(name);
            pointsZfbAccount.setText(name + "    " + account);
        }
    }

    @Override
    public void loadData(MyPointsBean pointsBean) {
        this.bean = pointsBean;
        pointsTotalPoints.setText(pointsBean.getMember().getIntegration() == null ? "0" : pointsBean.getMember().getIntegration());
        pointsCashingPoints.setText(pointsBean.getMember().getCashOutIntegration() == null ? "0" : pointsBean.getMember().getCashOutIntegration());
        if (pointsBean.getMember().getAliAccount() == null) {
            pointsZfbAccount.setText("还没有支付宝账号，去添加");
        } else {
            pointsZfbAccount.setText(pointsBean.getMember().getRealName() + "    " + pointsBean.getMember().getAliAccount());
        }
        if (pointsBean.getMember().getIntegration() != null) {
            pointsMyPoints.setText(pointsBean.getMember().getIntegration() == null ? "剩余积分0，" : "剩余积分" + pointsBean.getMember().getIntegration() + "，");
            pointsRules.setText(pointsBean.getIntegrationConf().getRatio() + "积分=1元，最小提现金额为" + pointsBean.getIntegrationConf().getMin() + "个积分，手续费为" + pointsBean.getIntegrationConf().getServiceRatio() + "%，提现必须是" + pointsBean.getIntegrationConf().getMultiple() + "的倍数");
        }
    }

    @Override
    public PointsView createView() {
        return this;
    }

    @Override
    public PointsPresenter createPresenter() {
        return new PointsPresenter(this);
    }
}
