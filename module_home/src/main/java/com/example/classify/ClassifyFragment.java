package com.example.classify;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.stx.xhb.xbanner.XBanner;

import butterknife.BindView;

/**
 * 分类
 */
public class ClassifyFragment extends BaseFragment<ClassifyView, ClassifyPresenter> implements ClassifyView {

    @BindView(R2.id.classify_search)
    LinearLayout classifySearch;
    @BindView(R2.id.classify_message)
    LinearLayout classifyMessage;
    @BindView(R2.id.classify_left_rec)
    RecyclerView classifyLeftRec;
    @BindView(R2.id.classify_x_banner)
    XBanner classifyXBanner;
    @BindView(R2.id.classify_rec)
    RecyclerView classifyRecommendRec;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    public void initData() {
        //二级列表
        presenter.setLeftRec(classifyLeftRec, classifyXBanner);
        //xBanner
        presenter.setXBanner(classifyXBanner);

        //商品二级列表
        presenter.setRightRec(classifyRecommendRec);
    }

    @Override
    public void initClick() {
        classifySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //到搜索页面
                ARouter.getInstance().build("/module_home/SearchActivity").navigation();
            }
        });
    }

    @Override
    public ClassifyView createView() {
        return this;
    }

    @Override
    public ClassifyPresenter createPresenter() {
        return new ClassifyPresenter(getContext());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //不可见
            classifyXBanner.stopAutoPlay();
        } else {
            //可见
            classifyXBanner.startAutoPlay();
        }
    }
}
