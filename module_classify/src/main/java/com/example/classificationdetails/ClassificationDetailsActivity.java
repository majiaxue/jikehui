package com.example.classificationdetails;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.BaseRecAdapter;
import com.example.adapter.SecondaryJDRecAdapter;
import com.example.adapter.SecondaryPddRecAdapter;
import com.example.classificationdetails.adapter.ClassificationRecAdapter;
import com.example.classificationdetails.adapter.JdWaterfallAdapter;
import com.example.classificationdetails.adapter.PddWaterAdapter;
import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * 分类商品
 */
@Route(path = "/module_classify/ClassificationDetailsActivity")
public class ClassificationDetailsActivity extends BaseActivity<ClassificationDetailsView, ClassificationDetailsPresenter> implements ClassificationDetailsView, View.OnClickListener {


    @BindView(R2.id.classification_back)
    ImageView classificationBack;
    @BindView(R2.id.classification_text)
    TextView classificationText;
    @BindView(R2.id.classification_search)
    LinearLayout classificationSearch;
    @BindView(R2.id.classification_message)
    LinearLayout classificationMessage;
    @BindView(R2.id.classification_tab)
    TabLayout classificationTab;
    @BindView(R2.id.classification_text1)
    TextView classificationText1;
    @BindView(R2.id.synthesize_bottom)
    ImageView synthesizeBottom;
    @BindView(R2.id.classification_synthesize)
    RelativeLayout classificationSynthesize;
    @BindView(R2.id.classification_text2)
    TextView classificationText2;
    @BindView(R2.id.sales_volume_top)
    ImageView salesVolumeTop;
    @BindView(R2.id.sales_volume_bottom)
    ImageView salesVolumeBottom;
    @BindView(R2.id.classification_sales_volume)
    RelativeLayout classificationSalesVolume;
    @BindView(R2.id.classification_text3)
    TextView classificationText3;
    @BindView(R2.id.price_top)
    ImageView priceTop;
    @BindView(R2.id.price_bottom)
    ImageView priceBottom;
    @BindView(R2.id.classification_price)
    RelativeLayout classificationPrice;
    @BindView(R2.id.classification_text4)
    TextView classificationText4;
    @BindView(R2.id.credit_top)
    ImageView creditTop;
    @BindView(R2.id.credit_bottom)
    ImageView creditBottom;
    @BindView(R2.id.classification_credit)
    RelativeLayout classificationCredit;
    @BindView(R2.id.classification_switchover)
    ImageView classificationSwitchover;
    @BindView(R2.id.classification_rec)
    RecyclerView classificationRec;
    @BindView(R2.id.classification_refresh)
    SmartRefreshLayout mRefresh;


    @Autowired(name = "searchContent")
    String searchContent;
    @Autowired(name = "position")
    int position;

    private int index = 0;
    private int page = 1;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight;


    @Override
    public int getLayoutId() {
        return R.layout.activity_classification_details;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        //添加间距
        spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(this, 15), DisplayUtil.dip2px(this, 15));

        //初始化tablayout
        presenter.initTabLayout(classificationTab);
        if (searchContent != null && !"".equals(searchContent)) {
            classificationText.setText(searchContent);
        }
        presenter.setContent(searchContent);

        classificationTab.getTabAt(position).select();
        if (position == 0) {
            presenter.searchTB(page, null);
        } else if (position == 1) {
            presenter.searchPDD(page);
        } else if (position == 2) {
            presenter.searchJD(page, null, null);
        }

        //设置 Header 为 官方主题 样式
        mRefresh.setRefreshHeader(new MaterialHeader(this));
        //设置 Footer 为 默认 样式
        mRefresh.setRefreshFooter(new ClassicsFooter(this));
        LogUtil.e("----------------------->" + searchContent);
    }

    @Override
    public void initClick() {
        classificationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        classificationSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ClassificationDetailsActivity.this, "我被点击了", Toast.LENGTH_SHORT).show();
                //到搜索页面
                ARouter.getInstance().build("/module_home/SearchActivity").navigation();
            }
        });

        classificationSwitchover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.ChangeShow(position);
            }
        });

        classificationSynthesize.setOnClickListener(this);
        classificationSalesVolume.setOnClickListener(this);
        classificationPrice.setOnClickListener(this);
        classificationCredit.setOnClickListener(this);

        classificationTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        position = 0;
                        page = 1;
                        presenter.searchTB(page, null);
                        break;
                    case 1:
                        position = 1;
                        page = 1;
                        presenter.searchPDD(page);
                        break;
                    case 2:
                        position = 2;
                        page = 1;
                        presenter.searchJD(page, null, null);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //********************设置上拉刷新下拉加载
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                if (position == 0) presenter.searchTB(page, null);
                else if (position == 1) {
                    presenter.searchPDD(page);
                } else if (position == 2) {
                    presenter.searchJD(page, null, null);
                }
            }
        });
        mRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (position == 0) {
                    presenter.searchTB(page, null);
                } else if (position == 1) {
                    presenter.searchPDD(page);
                } else if (position == 2) {
                    presenter.searchJD(page, null, null);
                }
            }
        });
    }

    @Override
    public ClassificationDetailsView createView() {
        return this;
    }

    @Override
    public ClassificationDetailsPresenter createPresenter() {
        return new ClassificationDetailsPresenter(this);
    }

    @Override
    public void moveTo(int num, boolean isWaterfall) {
        if (isWaterfall) {
            gridLayoutManager.scrollToPosition(num);
        } else {
            linearLayoutManager.scrollToPosition(num);
        }
    }

    @Override
    public void loadFinish() {
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
    }

    @Override
    public void loadTBLstRv(BaseRecAdapter adapter) {
        classificationSwitchover.setImageResource(R.drawable.xfxfgvx);
        classificationRec.setLayoutManager(linearLayoutManager);
        if (classificationRec.getItemDecorationCount() != 0) {
            classificationRec.removeItemDecoration(spaceItemDecorationLeftAndRight);
        }
        classificationRec.setAdapter(adapter);
    }

    @Override
    public void loadTBWaterfallRv(ClassificationRecAdapter adapter) {
        classificationSwitchover.setImageResource(R.drawable.fghfghfg);
        classificationRec.setLayoutManager(gridLayoutManager);
        if (classificationRec.getItemDecorationCount() == 0) {
            classificationRec.addItemDecoration(spaceItemDecorationLeftAndRight);
        }
        classificationRec.setAdapter(adapter);
    }

    @Override
    public void loadPDDLstRv(SecondaryPddRecAdapter adapter) {
        classificationSwitchover.setImageResource(R.drawable.xfxfgvx);
        classificationRec.setLayoutManager(linearLayoutManager);
        if (classificationRec.getItemDecorationCount() != 0) {
            classificationRec.removeItemDecoration(spaceItemDecorationLeftAndRight);
        }
        classificationRec.setAdapter(adapter);
    }

    @Override
    public void loadPDDWaterfallRv(PddWaterAdapter adapter) {
        classificationSwitchover.setImageResource(R.drawable.fghfghfg);
        classificationRec.setLayoutManager(gridLayoutManager);
        if (classificationRec.getItemDecorationCount() == 0) {
            classificationRec.addItemDecoration(spaceItemDecorationLeftAndRight);
        }
        classificationRec.setAdapter(adapter);
    }

    @Override
    public void loadJDLstRv(SecondaryJDRecAdapter adapter) {
        classificationSwitchover.setImageResource(R.drawable.xfxfgvx);
        classificationRec.setLayoutManager(linearLayoutManager);
        if (classificationRec.getItemDecorationCount() != 0) {
            classificationRec.removeItemDecoration(spaceItemDecorationLeftAndRight);
        }
        classificationRec.setAdapter(adapter);
    }

    @Override
    public void loadJDWaterfallRv(JdWaterfallAdapter adapter) {
        classificationSwitchover.setImageResource(R.drawable.fghfghfg);
        classificationRec.setLayoutManager(gridLayoutManager);
        if (classificationRec.getItemDecorationCount() == 0) {
            classificationRec.addItemDecoration(spaceItemDecorationLeftAndRight);
        }
        classificationRec.setAdapter(adapter);
    }

    @Override
    public void updateTitle(boolean salesVolume, boolean price, boolean credit) {
        classificationText1.setTextColor(Color.parseColor(index == 0 ? "#fd3c15" : "#333333"));
        synthesizeBottom.setImageResource(index == 0 ? R.drawable.cgbhdfg : R.drawable.khjkjhgjk);

        classificationText2.setTextColor(Color.parseColor(index == 1 ? "#fd3c15" : "#333333"));
        salesVolumeTop.setImageResource(index == 1 ? salesVolume ? R.drawable.ghfgh : R.drawable.gvhgh : R.drawable.ghfgh);
        salesVolumeBottom.setImageResource(index == 1 ? salesVolume ? R.drawable.cgbhdfg : R.drawable.khjkjhgjk : R.drawable.khjkjhgjk);

        classificationText3.setTextColor(Color.parseColor(index == 2 ? "#fd3c15" : "#333333"));
        priceTop.setImageResource(index == 2 ? price ? R.drawable.gvhgh : R.drawable.ghfgh : R.drawable.ghfgh);
        priceBottom.setImageResource(index == 2 ? price ? R.drawable.khjkjhgjk : R.drawable.cgbhdfg : R.drawable.khjkjhgjk);

        classificationText4.setTextColor(Color.parseColor(index == 3 ? "#fd3c15" : "#333333"));
        creditTop.setImageResource(index == 3 ? credit ? R.drawable.ghfgh : R.drawable.gvhgh : R.drawable.ghfgh);
        creditBottom.setImageResource(index == 3 ? credit ? R.drawable.cgbhdfg : R.drawable.khjkjhgjk : R.drawable.khjkjhgjk);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.e("=========================>" + searchContent);
        presenter.setContent(searchContent);
        if (position == 0) {
            presenter.searchTB(page, null);
        } else if (position == 1) {
            presenter.searchPDD(page);
        } else if (position == 2) {
            presenter.searchJD(page, null, null);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.classification_synthesize) {
            index = 0;
            page = 1;
            presenter.changeType(index);
        } else if (v.getId() == R.id.classification_sales_volume) {
            index = 1;
            page = 1;
            presenter.changeType(index);

        } else if (v.getId() == R.id.classification_price) {
            index = 2;
            page = 1;
            presenter.changeType(index);

        } else if (v.getId() == R.id.classification_credit) {
            index = 3;
            page = 1;
            presenter.changeType(index);

        }
    }
}
