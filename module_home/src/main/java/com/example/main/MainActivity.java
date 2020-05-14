package com.example.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragmentActivity;
import com.example.utils.TxtUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 主页面
 */
@Route(path = "/home/main")
public class MainActivity extends BaseFragmentActivity<MainView, MainPresenter> implements MainView {

    private final String[] perms = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private final int REQUEST_CODE = 0xa123;

    @BindView(R2.id.main_group)
    RadioGroup mainGroup;
    @BindView(R2.id.main_shangcheng)
    Button mRela;
    @BindView(R2.id.main_operator)
    RadioButton mYYS;
    @Autowired(name = "type")
    String type;

    private String url = "";


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        EventBus.getDefault().register(this);
        getWindow().setFormat(PixelFormat.TRANSPARENT);
        initPermission();
        presenter.registerReceiver();
//        WebSocketManager.getInstance().init(url);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    presenter.checkUp();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        presenter.loadData(getSupportFragmentManager(), R.id.main_frame);
        if ("login".equals(type)) {
            presenter.click(R.id.main_mine);
        }
    }

    @Override
    public void initClick() {
        mainGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                presenter.click(checkedId);
            }
        });

        mRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_store/UserActivity").navigation();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean eventBusBean) {
        if (CommonResource.JUMP_OPERATOR.equals(eventBusBean.getMsg())) {
            mYYS.setChecked(true);
            presenter.click(R.id.main_operator);
        }
    }

    @Override
    public void toHome() {
        mainGroup.check(R.id.main_home);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (TxtUtil.isFirst) {
            //TxtUtil.hasClipboard(this, true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().post(new EventBusBean("login"));
    }

    private void initPermission() {
        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, perms, REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_GRANTED) {

                } else {
                    finish();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0x111) {
            presenter.installAPK();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            presenter.exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public MainView createView() {
        return this;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }
}
