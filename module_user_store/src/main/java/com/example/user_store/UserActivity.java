package com.example.user_store;


import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean2;
import com.example.module_base.ModuleBaseApplication;
import com.example.mvp.BaseFragmentActivity;
import com.example.user_classify.ClassifyFragment;
import com.example.view.WindowInsetsFrameLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 多用户商城主界面
 */
@Route(path = "/module_user_store/UserActivity")
public class UserActivity extends BaseFragmentActivity<UserView, UserPresenter> implements UserView {


    @BindView(R2.id.user_frame)
    WindowInsetsFrameLayout userFrame;
    @BindView(R2.id.user_home)
    RadioButton userHome;
    @BindView(R2.id.user_classify)
    RadioButton userClassify;
    @BindView(R2.id.user_shopping_cart)
    RadioButton userShoppingCart;
    @BindView(R2.id.user_mine)
    RadioButton userMine;
    @BindView(R2.id.user_group)
    RadioGroup userGroup;
    @BindView(R2.id.user_local_shop)
    RadioButton userLocalShop;
    @BindView(R2.id.user_finish)
    ImageView mFinish;

    @Autowired(name = "go")
    String goLocalShop;


    @Override
    public int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);

        EventBus.getDefault().register(this);
        presenter.loadData(getSupportFragmentManager(), R.id.user_frame);
        presenter.initNotification();
        ModuleBaseApplication.mLocationClient.restart();
        if ("go".equals(goLocalShop)) {
            presenter.click(R.id.user_local_shop);
            userLocalShop.setChecked(true);
        }
    }

    @Override
    public void initClick() {
        userGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                presenter.click(checkedId);
            }
        });

        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String key = intent.getStringExtra("key");
        if (CommonResource.JUMP_CART.equals(key)) {
            presenter.click(R.id.user_shopping_cart);
            userShoppingCart.setChecked(true);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            presenter.setBack();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean2 eventBusBean2) {
        if (CommonResource.USER_BACK.equals(eventBusBean2.getMsg())) {
            finish();
        } else if (CommonResource.JUMP_CLASSIFY.equals(eventBusBean2.getMsg())) {
            ClassifyFragment.position = eventBusBean2.getPosition();
            userClassify.setChecked(true);
            presenter.click(R.id.user_classify);
        } else if ("toBuy".equals(eventBusBean2.getMsg())) {
            presenter.setBack();
        }
    }

    @Override
    public void toHome() {
        userHome.setChecked(true);
    }

    @Override
    public UserView createView() {
        return this;
    }

    @Override
    public UserPresenter createPresenter() {
        return new UserPresenter(this);
    }
}
