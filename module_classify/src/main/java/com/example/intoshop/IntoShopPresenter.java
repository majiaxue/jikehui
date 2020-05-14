package com.example.intoshop;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.intoshop.adapter.IntoShopVPAdapter;
import com.example.intoshop.baby.BabyFragment;
import com.example.intoshop.home.ShopHomeFragment;
import com.example.mvp.BasePresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/21
 * Describe:
 */
public class IntoShopPresenter extends BasePresenter<IntoShopView> {
    private String[] titleArr = {"首页", "宝贝"};
    private List<Fragment> fragmentList = new ArrayList<>();

    public IntoShopPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTabLayout(final TabLayout intoShopTab) {
        for (String title : titleArr) {
            intoShopTab.addTab(intoShopTab.newTab().setText(title));
        }

        fragmentList.add(new ShopHomeFragment());
        fragmentList.add(new BabyFragment());

        intoShopTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) intoShopTab.getChildAt(0);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField =
                                tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding
                        // 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params =
                                (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (Exception e) {

                }
            }
        });

    }

    public void initViewPager(FragmentManager fm) {
        IntoShopVPAdapter intoShopVPAdapter = new IntoShopVPAdapter(fm, fragmentList, titleArr);
        getView().updateVp(intoShopVPAdapter);
    }

}
