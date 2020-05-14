package com.example.local_main;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.local_cart.LocalCartFragment;
import com.example.local_home.LocalHomeFragment;
import com.example.local_mine.LocalMineFragment;
import com.example.local_order.LocalOrderFragment;
import com.example.module_local.R;
import com.example.mvp.BasePresenter;

public class LocalMainPresenter extends BasePresenter<LocalMainView> {
    private FragmentManager fm;
    private LocalHomeFragment localHomeFragment;
    private LocalOrderFragment localOrderFragment;
    private LocalMineFragment localMineFragment;
//    private LocalCartFragment localCartFragment;


    public LocalMainPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(FragmentManager fm, int resId) {
        this.fm = fm;
        localHomeFragment = new LocalHomeFragment();
        localOrderFragment = new LocalOrderFragment();
//        localCartFragment = new LocalCartFragment();
        localMineFragment = new LocalMineFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(resId, localHomeFragment);
        transaction.add(resId, localOrderFragment);
//        transaction.add(resId, localCartFragment);
        transaction.add(resId, localMineFragment);
        transaction.show(localHomeFragment)
                .hide(localMineFragment)
                .hide(localOrderFragment)
                .commit();
    }

    public void click(int checkedId) {
        FragmentTransaction transaction = fm.beginTransaction();
        if (checkedId == R.id.local_main_home) {
            transaction.show(localHomeFragment)
                    .hide(localOrderFragment)
                    .hide(localMineFragment)
                    .commit();
        } else if (checkedId == R.id.local_main_order) {
            transaction.show(localOrderFragment)
                    .hide(localHomeFragment)
                    .hide(localMineFragment)
                    .commit();
        } else if (checkedId == R.id.local_main_mine) {
            transaction.show(localMineFragment)
                    .hide(localHomeFragment)
                    .hide(localOrderFragment)
                    .commit();
        }
    }
}
