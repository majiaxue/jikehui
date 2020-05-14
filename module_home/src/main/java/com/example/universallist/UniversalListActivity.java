package com.example.universallist;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;
import com.example.universallist.adapter.BaoYouAdapter;
import com.example.universallist.adapter.HotRecommendRecAdapter;
import com.example.universallist.adapter.UniversalListRecAdapter;
import com.example.utils.LogUtil;
import com.example.utils.ProcessDialogUtil;
import com.kongzue.dialog.v3.WaitDialog;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * 全能列表
 */
@Route(path = "/module_home/UniversalListActivity")
public class UniversalListActivity extends BaseActivity<UniversalListView, UniversalListPresenter> implements UniversalListView {

    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.universal_list_rec)
    RecyclerView universalListRec;
    @BindView(R2.id.universal_list_smart_refresh)
    SmartRefreshLayout universalListSmartRefresh;

    @Autowired(name = "position")
    int position;
    @Autowired(name = "type")
    int type;

    private int page = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_universal_list;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        ProcessDialogUtil.showProcessDialog(this);
//        WaitDialog.show(this,null);

        LogUtil.e("从哪个地方近的" + position);
        if (position == 1) {
            includeTitle.setText("淘抢购");
            presenter.universalList(position, page);
        } else if (position == 2) {
            includeTitle.setText("9.9包邮");
            presenter.baoyou(page);
        } else if (position == 3) {
            includeTitle.setText("聚划算");
            presenter.universalList(position, page);
        } else if (position == 4) {
            includeTitle.setText("爆款推荐");
            presenter.hotRecommend(page, type);
        } else {
            includeTitle.setText("好货优选");
            presenter.hotRecommend(page, type);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        universalListRec.setLayoutManager(gridLayoutManager);

        universalListSmartRefresh.setRefreshHeader(new MaterialHeader(this));
        universalListSmartRefresh.setRefreshFooter(new ClassicsFooter(this));

        universalListSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                if (position >= 4) {
                    presenter.hotRecommend(page, type);
                } else if (position == 2) {
                    presenter.baoyou(page);
                } else {
                    presenter.universalList(position, page);
                }
            }
        });

        universalListSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (position >= 4) {
                    presenter.hotRecommend(page, type);
                } else if (position == 2) {
                    presenter.baoyou(page);
                } else {
                    presenter.universalList(position, page);
                }
            }
        });
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
    public UniversalListView createView() {
        return this;
    }

    @Override
    public UniversalListPresenter createPresenter() {
        return new UniversalListPresenter(this);
    }

    @Override
    public void finishRefresh() {
        universalListSmartRefresh.finishRefresh();
        universalListSmartRefresh.finishLoadMore();
    }

    @Override
    public void loadData(BaoYouAdapter baoYouAdapter) {
        universalListRec.setAdapter(baoYouAdapter);
    }

    @Override
    public void loadData(HotRecommendRecAdapter hotRecommendRecAdapter) {
        universalListRec.setAdapter(hotRecommendRecAdapter);
    }

    @Override
    public void loadData(UniversalListRecAdapter universalListRecAdapter) {
        universalListRec.setAdapter(universalListRecAdapter);
    }
}
