package com.example.intoshop;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.intoshop.adapter.IntoShopVPAdapter;
import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseFragmentActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

/**
 * 店铺
 */
@Route(path = "/module_classify/IntoShopActivity")
public class IntoShopActivity extends BaseFragmentActivity<IntoShopView, IntoShopPresenter> implements IntoShopView {


    @BindView(R2.id.into_shop_image_back)
    ImageView intoShopImageBack;
    @BindView(R2.id.into_shop_search)
    LinearLayout intoShopSearch;
    @BindView(R2.id.relative)
    RelativeLayout relative;
    @BindView(R2.id.into_shop_store_image)
    SimpleDraweeView intoShopStoreImage;
    @BindView(R2.id.into_shop_store_name)
    TextView intoShopStoreName;
    @BindView(R2.id.into_shop_store_collect_number)
    TextView intoShopStoreCollectNumber;
    @BindView(R2.id.into_shop_collect_store)
    TextView intoShopCollectStore;
    @BindView(R2.id.into_shop_tab)
    TabLayout intoShopTab;
    @BindView(R2.id.into_shop_vp)
    ViewPager intoShopVp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_into_shop;
    }

    @Override
    public void initData() {
        //初始化tablayout
        presenter.initTabLayout(intoShopTab);
        presenter.initViewPager(getSupportFragmentManager());
        //预加载
        intoShopVp.setOffscreenPageLimit(2);
        //tablayout联动viewpager
        intoShopTab.setupWithViewPager(intoShopVp);
    }

    @Override
    public void initClick() {
        intoShopImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        intoShopSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //到搜索页面
                ARouter.getInstance().build("/module_home/SearchActivity").navigation();
            }
        });
        intoShopCollectStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IntoShopActivity.this, "你点击了收藏", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public IntoShopView createView() {
        return this;
    }

    @Override
    public IntoShopPresenter createPresenter() {
        return new IntoShopPresenter(this);
    }

    @Override
    public void updateVp(IntoShopVPAdapter intoShopVPAdapter) {
        intoShopVp.setAdapter(intoShopVPAdapter);

    }
}
