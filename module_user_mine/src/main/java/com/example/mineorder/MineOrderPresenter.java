package com.example.mineorder;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adapter.BaseVPAdapter;
import com.example.mineorder.orderall.OrderAllFragment;
import com.example.mineorder.stayappraise.StayAppraiseFragment;
import com.example.mineorder.staydeliverygoods.StayDeliveryGoodsFragment;
import com.example.mineorder.stayobligation.StayObligationFragment;
import com.example.mineorder.staysendgoods.StaySendGoodsFragment;
import com.example.mvp.BasePresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class MineOrderPresenter extends BasePresenter<MineOrderView> {

    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titleArr = {"全部订单","待付款","待发货","待收货","待评价"};

    public MineOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTabLayout(final TabLayout mineOrderTab) {
        for (String title : titleArr) {
            mineOrderTab.addTab(mineOrderTab.newTab().setText(title));
        }

        fragmentList.add(new OrderAllFragment());
        fragmentList.add(new StayObligationFragment());
        fragmentList.add(new StaySendGoodsFragment());
        fragmentList.add(new StayDeliveryGoodsFragment());
        fragmentList.add(new StayAppraiseFragment());

        initTabIndicator(mineOrderTab);

    }


    public void initViewPager(FragmentManager fm) {
        BaseVPAdapter baseVPAdapter = new BaseVPAdapter(fm, fragmentList, titleArr);
        getView().updateVp(baseVPAdapter);
    }

    private void initTabIndicator(final TabLayout mineOrderTab) {
        mineOrderTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) mineOrderTab.getChildAt(0);

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


}
