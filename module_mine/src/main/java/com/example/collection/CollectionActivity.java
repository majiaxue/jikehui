package com.example.collection;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.collection.adapter.CollectionAdapter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.ProcessDialogUtil;
import com.kongzue.dialog.v3.WaitDialog;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

@Route(path = "/mine/collection")
public class CollectionActivity extends BaseActivity<CollectionView, CollectionPresenter> implements CollectionView {
    @BindView(R2.id.collection_rv)
    RecyclerView collectionRv;
    @BindView(R2.id.collection_all_check)
    ImageView collectionAllCheck;
    @BindView(R2.id.collection_delete)
    TextView collectionDelete;
    @BindView(R2.id.collection_bottom)
    LinearLayout collectionBottom;
    @BindView(R2.id.collection_refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.include_right_btn)
    TextView includeRightBtn;

    private int page = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void initData() {
        includeTitle.setText("我的收藏");
        includeRightBtn.setText("编辑");
        includeRightBtn.setVisibility(View.VISIBLE);
        ProcessDialogUtil.showProcessDialog(this);
//        WaitDialog.show(this,null);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        collectionRv.setLayoutManager(layoutManager);

        //设置 Header 为 官方主题 样式
        mRefresh.setRefreshHeader(new MaterialHeader(this));
        //设置 Footer 为 默认 样式
        mRefresh.setRefreshFooter(new ClassicsFooter(this));

        presenter.loadData(page);
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
                presenter.edit();
            }
        });

        collectionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteList();
            }
        });

        collectionAllCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.allCheck();
            }
        });

        //********************设置上拉刷新下拉加载
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.loadData(page);
            }
        });
        mRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.loadData(page);
            }
        });
    }

    @Override
    public void loadUI(CollectionAdapter adapter) {
        collectionRv.setAdapter(adapter);
        presenter.rvClick();
    }

    @Override
    public void toEdit() {
        includeRightBtn.setText("完成");
        collectionBottom.setVisibility(View.VISIBLE);
    }

    @Override
    public void toFinish() {
        includeRightBtn.setText("编辑");
        collectionBottom.setVisibility(View.GONE);
    }

    @Override
    public void allCheck() {
        collectionAllCheck.setImageResource(R.drawable.icon_xuanzhong);
    }

    @Override
    public void notAllCheck() {
        collectionAllCheck.setImageResource(R.drawable.vghfgdg);
    }

    @Override
    public void loadFinish(int size) {
        mRefresh.finishLoadMore();
        mRefresh.finishRefresh();
        if (size > 0) {
            includeRightBtn.setVisibility(View.VISIBLE);
        } else {
            includeRightBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public CollectionView createView() {
        return this;
    }

    @Override
    public CollectionPresenter createPresenter() {
        return new CollectionPresenter(this);
    }
}
