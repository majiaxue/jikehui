package com.example.coupon;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adapter.BaseVPAdapter;
import com.example.coupon.all.AllFragment;
import com.example.coupon.haveexpired.HaveExpiredFragment;
import com.example.mvp.BasePresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public class CouponPresenter extends BasePresenter<CouponView> {

    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titleArr = {"全部", "已过期"};

    public CouponPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTabLayout(final TabLayout intoShopTab, String from) {
        for (String title : titleArr) {
            intoShopTab.addTab(intoShopTab.newTab().setText(title));
        }

        fragmentList.add(new AllFragment(from));
        fragmentList.add(new HaveExpiredFragment(from));

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
        BaseVPAdapter baseVPAdapter = new BaseVPAdapter(fm, fragmentList, titleArr);
        getView().updateVp(baseVPAdapter);
    }
}
