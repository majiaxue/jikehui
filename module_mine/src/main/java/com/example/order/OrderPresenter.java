package com.example.order;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mvp.BasePresenter;
import com.example.order.adapter.OrderVPAdapter;
import com.example.order.fragment_all.AllOrderFragment;
import com.example.order.fragment_lose.LoseOrderFragment;
import com.example.order.fragment_pay.PayOrderFragment;
import com.example.order.fragment_settle.SettleOrderFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class OrderPresenter extends BasePresenter<OrderView> {
    private String[] titleArr = {"全部订单", "已付款", "已结算", "已失效"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private AllOrderFragment allOrderFragment;
    private PayOrderFragment payOrderFragment;
    private SettleOrderFragment settleOrderFragment;
    private LoseOrderFragment loseOrderFragment;

    public OrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {
        OrderActivity.index = 1;
    }

    public void initTabLayout(final TabLayout orderTab, int position) {
        allOrderFragment = AllOrderFragment.getInstance();
        payOrderFragment = PayOrderFragment.getInstance();
        settleOrderFragment = SettleOrderFragment.getInstance();
        loseOrderFragment = LoseOrderFragment.getInstance();

        addFlag(position);

        for (String title : titleArr) {
            orderTab.addTab(orderTab.newTab().setText(title));
        }
        fragmentList.add(allOrderFragment);
        fragmentList.add(payOrderFragment);
        fragmentList.add(settleOrderFragment);
        fragmentList.add(loseOrderFragment);

        orderTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) orderTab.getChildAt(0);

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

    private void addFlag(int position) {

        if (position == 0) {
            payOrderFragment.addFlag();
            settleOrderFragment.addFlag();
            loseOrderFragment.addFlag();
        } else if (position == 1) {
            allOrderFragment.addFlag();
            settleOrderFragment.addFlag();
            loseOrderFragment.addFlag();
        } else if (position == 2) {
            allOrderFragment.addFlag();
            payOrderFragment.addFlag();
            loseOrderFragment.addFlag();
        } else if (position == 3) {
            allOrderFragment.addFlag();
            payOrderFragment.addFlag();
            settleOrderFragment.addFlag();
        }
    }

    public void initViewPager(FragmentManager fm) {
        OrderVPAdapter vpAdapter = new OrderVPAdapter(fm, fragmentList, titleArr);
        getView().updateVP(vpAdapter);
    }

    public void TabScoll(String text) {
        switch (text) {
            case "全部订单":
//                fragmentList.get(0).setDataList(titleArr[0]);
                break;
            case "已付款":
//                fragmentList.get(1).setDataList(titleArr[1]);
                break;
            case "已结算":
//                fragmentList.get(2).setDataList(titleArr[2]);
                break;
            case "已失效":
//                fragmentList.get(3).setDataList(titleArr[3]);
                break;
        }
    }

    public void change(int i, int index) {
        getView().typeChanged(i);
        allOrderFragment.setOrign(index);

    }
}
