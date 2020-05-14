package com.example.community;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adapter.BaseVPAdapter;
import com.example.community.good_goods.GoodGoodsFragment;
import com.example.community.goods_commend.GoodsCommendFragment;
import com.example.mvp.BasePresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CommunityPresenter extends BasePresenter<CommunityView> {
    private String[] titleArr = {"商品推荐", "好货专场"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private GoodsCommendFragment commendFragment;
    private GoodGoodsFragment goodsFragment;

    public CommunityPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setTab(final TabLayout tab) {
        tab.addTab(tab.newTab().setText(titleArr[0]));
        tab.addTab(tab.newTab().setText(titleArr[1]));

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
        commendFragment = new GoodsCommendFragment();
        goodsFragment = new GoodGoodsFragment();
        fragmentList.add(commendFragment);
        fragmentList.add(goodsFragment);

        BaseVPAdapter vpAdapter = new BaseVPAdapter(fm, fragmentList, titleArr);
        getView().updateVP(vpAdapter);
    }
}
