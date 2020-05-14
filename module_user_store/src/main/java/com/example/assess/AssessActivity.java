package com.example.assess;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.assess.adapter.AssessAdapter;
import com.example.assess.adapter.AssessTitleAdapter;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.SpaceItemDecoration;
import com.example.view.flowLayout.TagFlowLayout;

import butterknife.BindView;

public class AssessActivity extends BaseActivity<AssessView, AssessPresenter> implements AssessView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.include_right)
    ImageView includeRight;
    @BindView(R2.id.assess_title)
    RecyclerView assessTitle;
    @BindView(R2.id.assess_shop)
    LinearLayout assessShop;
    @BindView(R2.id.assess_serve)
    LinearLayout assessServe;
    @BindView(R2.id.assess_cart)
    LinearLayout assessCart;
    @BindView(R2.id.assess_add_cart)
    TextView assessAddCart;
    @BindView(R2.id.assess_buy)
    TextView assessBuy;
    @BindView(R2.id.assess_rv)
    RecyclerView assessRv;

    private int page = 1;
    private String id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_assess;
    }

    @Override
    public void initData() {
        includeTitle.setText("宝贝评价");
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        includeRight.setImageResource(R.drawable.icon_fenxiang11);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        assessTitle.setLayoutManager(gridLayoutManager);
        assessTitle.addItemDecoration(new SpaceItemDecoration(0, (int) getResources().getDimension(R.dimen.dp_20), 0, 0));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        assessRv.setLayoutManager(linearLayoutManager);

        presenter.loadData(page,id);
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
    public void loadTitle(AssessTitleAdapter adapter) {
        assessTitle.setAdapter(adapter);
    }

    @Override
    public void loadAssess(AssessAdapter adapter) {
        assessRv.setAdapter(adapter);
    }

    @Override
    public AssessView createView() {
        return this;
    }

    @Override
    public AssessPresenter createPresenter() {
        return new AssessPresenter(this);
    }
}
