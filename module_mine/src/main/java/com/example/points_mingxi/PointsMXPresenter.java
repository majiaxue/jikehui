package com.example.points_mingxi;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mvp.BasePresenter;
import com.example.order.adapter.OrderVPAdapter;
import com.example.points_mingxi.cashout_record.CashoutRecordFragment;
import com.example.points_mingxi.points_record.PointsRecordFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PointsMXPresenter extends BasePresenter<PointsMXView> {
    private String[] titleArr = {"积分明细", "提现记录"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private PointsRecordFragment pointsRecordFragment;
    private CashoutRecordFragment cashoutRecordFragment;

    public PointsMXPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTab(final TabLayout tab) {
        for (String title : titleArr) {
            tab.addTab(tab.newTab().setText(title));
        }

        tab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) tab.getChildAt(0);

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

    public void initVp(FragmentManager fm) {
        pointsRecordFragment = new PointsRecordFragment();
        cashoutRecordFragment = new CashoutRecordFragment();

        fragmentList.add(pointsRecordFragment);
        fragmentList.add(cashoutRecordFragment);

        OrderVPAdapter vpAdapter = new OrderVPAdapter(fm, fragmentList, titleArr);
        getView().updateVP(vpAdapter);
    }
}
