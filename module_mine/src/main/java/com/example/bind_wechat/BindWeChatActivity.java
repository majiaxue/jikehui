package com.example.bind_wechat;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entity.EventBusBean;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.setting.SettingActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 绑定微信
 */
public class BindWeChatActivity extends BaseActivity<BindWeChatView, BindWeChatPresenter> implements BindWeChatView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.bind_wechat_btn)
    TextView bindWechatBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_wechat;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        includeTitle.setText(getResources().getString(R.string.bind_wechat));
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bindWechatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.bind();
            }
        });
    }

    @Override
    public void bindSuccess() {
        Toast.makeText(this, "绑定成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean eventBusBean) {
        if ("WXBIND".equals(eventBusBean.getMsg())) {
            presenter.bindWX();
        }
    }

    @Override
    public BindWeChatView createView() {
        return this;
    }

    @Override
    public BindWeChatPresenter createPresenter() {
        return new BindWeChatPresenter(this);
    }
}
