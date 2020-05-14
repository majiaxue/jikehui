package com.example.local_main;

import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_local.R;
import com.example.module_local.R2;
import com.example.mvp.BaseFragmentActivity;
import com.example.view.WindowInsetsFrameLayout;

import butterknife.BindView;

@Route(path = "/module_local/LocalMainActivity")
public class LocalMainActivity extends BaseFragmentActivity<LocalMainView, LocalMainPresenter> implements LocalMainView {
    @BindView(R2.id.local_main_radiogroup)
    RadioGroup localMainRadiogroup;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_main;
    }

    @Override
    public void initData() {
        presenter.initView(getSupportFragmentManager(), R.id.local_main_frame);
    }

    @Override
    public void initClick() {
        localMainRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                presenter.click(checkedId);
            }
        });
    }

    @Override
    public LocalMainView createView() {
        return this;
    }

    @Override
    public LocalMainPresenter createPresenter() {
        return new LocalMainPresenter(this);
    }

}
