package com.example.secondarydetails;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.SecondaryJDRecAdapter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;
import com.example.search.SearchActivity;
import com.example.adapter.SecondaryPddRecAdapter;
import com.example.secondarydetails.adapter.SecondaryTBRecAdapter;
import com.example.utils.ProcessDialogUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import butterknife.BindView;

/**
 * 二级详情
 */
@Route(path = "/module_home/SecondaryDetailsActivity")
public class SecondaryDetailsActivity extends BaseActivity<SecondaryDetailsView, SecondaryDetailsPresenter> implements SecondaryDetailsView {


    @BindView(R2.id.secondary_details_image_back)
    ImageView secondaryDetailsImageBack;
    @BindView(R2.id.secondary_details_search)
    LinearLayout secondaryDetailsSearch;
    @BindView(R2.id.secondary_details_tab)
    TabLayout secondaryDetailsTab;
    @BindView(R2.id.secondary_details_rec)
    RecyclerView secondaryDetailsRec;
    @BindView(R2.id.secondary_details_no_goods)
    ImageView secondaryDetailsNoGoods;
    @BindView(R2.id.secondary_details_smart_refresh)
    SmartRefreshLayout secondaryDetailsSmartRefresh;
    //    private String type;
    @Autowired(name = "type")
    String type;

    private int preScrollState;

    @Override
    public int getLayoutId() {
        return R.layout.activity_secondary_details;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
//        Intent intent = getIntent();
//        type = intent.getStringExtra("type");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        secondaryDetailsRec.setLayoutManager(linearLayoutManager);

        presenter.initView(secondaryDetailsTab, secondaryDetailsSmartRefresh, type);
        secondaryDetailsSmartRefresh.setRefreshHeader(new MaterialHeader(this));
        //设置 Footer 为 球脉冲 样式
        secondaryDetailsSmartRefresh.setRefreshFooter(new ClassicsFooter(this));
//        presenter.secondaryDetailsRec(secondaryDetailsRec,type);
//        secondaryDetailsRec.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
////                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
////                    Fresco.getImagePipeline().resume();
////                } else {
////                    Fresco.getImagePipeline().pause();
////                }
//                switch (newState) {
//                    case RecyclerView.SCROLL_STATE_IDLE://停止滑动
//                        if (Fresco.getImagePipeline().isPaused())
//                            Fresco.getImagePipeline().resume();
//                        break;
//                    case RecyclerView.SCROLL_STATE_DRAGGING:
//                        if (preScrollState == RecyclerView.SCROLL_STATE_SETTLING) {
//                            //触摸滑动不需要加载
//                            Fresco.getImagePipeline().pause();
//                        } else {
//                            //触摸滑动需要加载
//                            if (Fresco.getImagePipeline().isPaused())
//                                Fresco.getImagePipeline().resume();
//                        }
//                        break;
//                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
//                        Fresco.getImagePipeline().pause();
//                        break;
//                }
//                preScrollState = newState;
//            }
//
//        });

    }

    @Override
    public void initClick() {
        secondaryDetailsImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击到搜索页面
        secondaryDetailsSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondaryDetailsActivity.this, SearchActivity.class));
            }
        });

    }

    @Override
    public SecondaryDetailsView createView() {
        return this;
    }

    @Override
    public SecondaryDetailsPresenter createPresenter() {
        return new SecondaryDetailsPresenter(this);
    }

    @Override
    public void lodeRec(SecondaryPddRecAdapter baseRecAdapter) {
        secondaryDetailsRec.setItemViewCacheSize(20);
        secondaryDetailsRec.setAdapter(baseRecAdapter);
    }

    @Override
    public void lodeTBRec(SecondaryTBRecAdapter secondaryTBRecAdapter) {
        secondaryDetailsRec.setItemViewCacheSize(20);
        secondaryDetailsRec.setAdapter(secondaryTBRecAdapter);
    }

    @Override
    public void lodeJDRec(SecondaryJDRecAdapter secondaryJDRecAdapter) {
        secondaryDetailsRec.setItemViewCacheSize(20);
        secondaryDetailsRec.setAdapter(secondaryJDRecAdapter);
    }

    @Override
    public void noGoods(boolean isNoGoods) {
        if (isNoGoods) {
            secondaryDetailsNoGoods.setVisibility(View.VISIBLE);
            secondaryDetailsRec.setVisibility(View.GONE);
        } else {
            secondaryDetailsNoGoods.setVisibility(View.GONE);
            secondaryDetailsRec.setVisibility(View.VISIBLE);
        }
    }
}
