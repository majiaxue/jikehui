package com.example.upgrade;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.upgrade.adapter.UpgradeAdapter;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

@Route(path = "/mine/upgrade")
public class UpGradeActivity extends BaseActivity<UpgradeView, UpgradePresenter> implements UpgradeView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.upgrade_rv)
    RecyclerView upgradeRv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_upgrade;
    }

    @Override
    public void initData() {
        includeTitle.setText("我要升级");
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
    }

    @Override
    public void loadUI(UpgradeAdapter adapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        upgradeRv.setLayoutManager(layoutManager);
        upgradeRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_27)));
        upgradeRv.setAdapter(adapter);
        presenter.click();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            presenter.upSuccess();
        }
    }

    @Override
    public UpgradeView createView() {
        return this;
    }

    @Override
    public UpgradePresenter createPresenter() {
        return new UpgradePresenter(this);
    }
}
