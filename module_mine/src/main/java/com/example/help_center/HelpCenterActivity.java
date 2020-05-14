package com.example.help_center;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.CommonResource;
import com.example.help_detail.HelpDetailActivity;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

@Route(path = "/mine/helpcenter")
public class HelpCenterActivity extends BaseActivity<HelpCenterView, HelpCenterPresenter> implements HelpCenterView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.help_center_txt1)
    TextView helpCenterTxt1;
    @BindView(R2.id.help_center_txt2)
    TextView helpCenterTxt2;
    @BindView(R2.id.help_center_txt3)
    TextView helpCenterTxt3;
    @BindView(R2.id.help_center_txt4)
    TextView helpCenterTxt4;
    @BindView(R2.id.help_center_txt5)
    TextView helpCenterTxt5;
    @BindView(R2.id.help_center_call_just)
    TextView helpCenterCallJust;

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_center;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        helpCenterTxt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToDetail(0);
            }
        });

        helpCenterTxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToDetail(1);
            }
        });

        helpCenterTxt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToDetail(2);
            }
        });

        helpCenterTxt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToDetail(3);
            }
        });

        helpCenterTxt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToGuide();
            }
        });

        helpCenterCallJust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callPhone(CommonResource.SERVICE_PHONE);
            }
        });
    }

    @Override
    public HelpCenterView createView() {
        return this;
    }

    @Override
    public HelpCenterPresenter createPresenter() {
        return new HelpCenterPresenter(this);
    }
}
