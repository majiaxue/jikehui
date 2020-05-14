package com.example.user_store;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.local_shop.LocalShopFragment;
import com.example.module_base.ModuleBaseApplication;
import com.example.mvp.BasePresenter;
import com.example.user_classify.ClassifyFragment;
import com.example.user_home.HomeFragment;
import com.example.user_mine.MineFragment;
import com.example.user_shopping_cart.ShoppingCartFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class UserPresenter extends BasePresenter<UserView> {
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private LocalShopFragment localShopFragment;
    private MineFragment mineFragment;

    private boolean isHomeShow = true;

    public UserPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {
        ModuleBaseApplication.mLocationClient.stop();
        EventBus.getDefault().unregister(mContext);
    }

    public void loadData(FragmentManager fragmentManager, int resId) {
        this.fragmentManager = fragmentManager;
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        shoppingCartFragment = new ShoppingCartFragment();
        mineFragment = new MineFragment();
        localShopFragment = new LocalShopFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(resId, homeFragment)
                .add(resId, classifyFragment)
                .add(resId, shoppingCartFragment)
                .add(resId, mineFragment)
                .add(resId, localShopFragment);

        transaction.show(homeFragment)
                .hide(classifyFragment)
                .hide(shoppingCartFragment)
                .hide(mineFragment)
                .hide(localShopFragment)
                .commit();
    }

    public void click(int resId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (resId == R.id.user_home) {
            transaction.show(homeFragment)
                    .hide(classifyFragment)
                    .hide(shoppingCartFragment)
                    .hide(mineFragment)
                    .hide(localShopFragment)
                    .commit();
            isHomeShow = true;
        } else if (resId == R.id.user_classify) {
            transaction.show(classifyFragment)
                    .hide(homeFragment)
                    .hide(shoppingCartFragment)
                    .hide(mineFragment)
                    .hide(localShopFragment)
                    .commit();
            isHomeShow = false;
        } else if (resId == R.id.user_shopping_cart) {
            transaction.show(shoppingCartFragment)
                    .hide(homeFragment)
                    .hide(classifyFragment)
                    .hide(mineFragment)
                    .hide(localShopFragment)
                    .commit();
            isHomeShow = false;
        } else if (resId == R.id.user_mine) {
            transaction.show(mineFragment)
                    .hide(homeFragment)
                    .hide(classifyFragment)
                    .hide(shoppingCartFragment)
                    .hide(localShopFragment)
                    .commit();
            isHomeShow = false;
        } else if (resId == R.id.user_local_shop) {
            transaction.show(localShopFragment)
                    .hide(homeFragment)
                    .hide(classifyFragment)
                    .hide(shoppingCartFragment)
                    .hide(mineFragment)
                    .commit();
            isHomeShow = false;
        }
    }

    public void setBack() {
        if (isHomeShow) {
            ((Activity) mContext).finish();
        } else {
            click(R.id.user_home);
            getView().toHome();
        }
    }

    public void initNotification() {
        //开启前台定位服务：

        Notification.Builder builder = new Notification.Builder(mContext.getApplicationContext());
        //获取一个Notification构造器

        Intent nfIntent = new Intent(mContext.getApplicationContext(), ((Activity) mContext).getClass());
        builder.setContentIntent(PendingIntent.getActivity(mContext, 0, nfIntent, 0)) // 设置PendingIntent
                .setContentTitle("正在进行后台定位") // 设置下拉列表里的标题
                .setSmallIcon(R.drawable.icon_app) // 设置状态栏内的小图标
                .setContentText("后台定位通知") // 设置上下文内容
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间
        Notification notification = null;
        notification = builder.build();
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
        ModuleBaseApplication.mLocationClient.enableLocInForeground(1001, notification);// 调起前台定位
    }
}
