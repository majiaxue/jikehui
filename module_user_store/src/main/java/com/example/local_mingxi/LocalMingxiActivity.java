package com.example.local_mingxi;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.local_mingxi.adapter.LocalMingxiAdapter;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocalMingxiActivity extends BaseActivity<LocalMingxiView, LocalMingxiPresenter> implements LocalMingxiView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.local_mingxi_rv)
    RecyclerView localMingxiRv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_mingxi;
    }

    @Override
    public void initData() {
        includeTitle.setText("支出明细");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        localMingxiRv.setLayoutManager(layoutManager);
        localMingxiRv.addItemDecoration(new SpaceItemDecoration(0, 0, (int) getResources().getDimension(R.dimen.dp_6), (int) getResources().getDimension(R.dimen.dp_6)));

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
    public void loadRv(LocalMingxiAdapter adapter) {
        localMingxiRv.setAdapter(adapter);
    }

    @Override
    public LocalMingxiView createView() {
        return this;
    }

    @Override
    public LocalMingxiPresenter createPresenter() {
        return new LocalMingxiPresenter(this);
    }
}
