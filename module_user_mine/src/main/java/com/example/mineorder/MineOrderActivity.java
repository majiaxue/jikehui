package com.example.mineorder;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.BaseVPAdapter;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragmentActivity;

import butterknife.BindView;

/**
 * 我的订单页面
 */
@Route(path = "/module_user_mine/MineOrderActivity")
public class MineOrderActivity extends BaseFragmentActivity<MineOrderView, MineOrderPresenter> implements MineOrderView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.mine_order_tab)
    TabLayout mineOrderTab;
    @BindView(R2.id.mine_order_vp)
    ViewPager mineOrderVp;
    @Autowired(name = "type")
    int type;


    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_order;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        includeTitle.setText("我的订单");
        //初始化tablayout
        presenter.initTabLayout(mineOrderTab);
        presenter.initViewPager(getSupportFragmentManager());
        //预加载
        mineOrderVp.setOffscreenPageLimit(0);
        //tablayout联动viewpager
        mineOrderTab.setupWithViewPager(mineOrderVp);

        mineOrderVp.setCurrentItem(type);
        mineOrderTab.getTabAt(type).select();

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
    public MineOrderView createView() {
        return this;
    }

    @Override
    public MineOrderPresenter createPresenter() {
        return new MineOrderPresenter(this);
    }

    @Override
    public void updateVp(BaseVPAdapter baseVPAdapter) {
        mineOrderVp.setAdapter(baseVPAdapter);
        mineOrderTab.setTabsFromPagerAdapter(baseVPAdapter);
    }
}
