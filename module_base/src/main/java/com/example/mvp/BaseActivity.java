package com.example.mvp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.entity.EventBusBean;
import com.example.utils.AppManager;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.StatusBarUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;


public abstract class BaseActivity<V extends IView, P extends BasePresenter> extends AppCompatActivity implements BaseMVP<V, P> {
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        //创建Presenter
        presenter = createPresenter();
        if (presenter != null) {
            //将View层注册到Presenter中
            presenter.registerView(createView());
        }
        changeStatus();
        initData();
        initClick();
    }

    private void changeStatus() {
        // 设置状态栏
        StatusBarUtils.transparencyBar(this);
        StatusBarUtils.setStatusTheme(this, true, true);

    }

    public abstract int getLayoutId();

    public abstract void initData();

    public abstract void initClick();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean eventBusBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ProcessDialogUtil.dismissDialog();
        if (presenter != null) {
            AppManager.getInstance().finishActivity(this);
            //Activity销毁时的调用，让具体实现BasePresenter中onViewDestroy()方法做出决定
            presenter.destroy();
        }
    }
}
