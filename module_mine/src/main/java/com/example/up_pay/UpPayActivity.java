package com.example.up_pay;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.SPUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

@Route(path = "/module_mine/up_pay")
public class UpPayActivity extends BaseActivity<UpPayView, UpPayPresenter> implements UpPayView {
    @BindView(R2.id.uppay_back)
    ImageView uppayBack;
    @BindView(R2.id.uppay_money)
    TextView uppayMoney;
    @BindView(R2.id.uppay_weixin_img)
    ImageView uppayWeixinImg;
    @BindView(R2.id.uppay_weixin)
    LinearLayout uppayWeixin;
    @BindView(R2.id.uppay_zfb_img)
    ImageView uppayZfbImg;
    @BindView(R2.id.uppay_zfb)
    LinearLayout uppayZfb;
    @BindView(R2.id.uppay_btn)
    TextView uppayBtn;

    @Autowired(name = "money")
    String money;
    @Autowired(name = "type")
    String type;
    @Autowired(name = "name")
    String name;
    @Autowired(name = "levelId")
    String levelId;

    private boolean isWeChat = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_up_pay;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        EventBus.getDefault().register(this);
        uppayMoney.setText("￥" + money);
    }

    @Override
    public void initClick() {
        uppayBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goBack();
            }
        });

        uppayWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWeChat = true;
                changePayType();
            }
        });

        uppayZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWeChat = false;
                changePayType();
            }
        });

        uppayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uppayBtn.setEnabled(false);
                presenter.pay(isWeChat, money, type, name, levelId);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean eventBusBean) {
        if (CommonResource.WXPAY_SUCCESS_UP.equals(eventBusBean.getMsg())) {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        presenter.goBack();
        return false;
    }

    private void changePayType() {
        uppayWeixinImg.setImageResource(isWeChat ? R.drawable.icon_xuanzhong : R.drawable.icon_weixuanzhong);
        uppayZfbImg.setImageResource(isWeChat ? R.drawable.icon_weixuanzhong : R.drawable.icon_xuanzhong);
    }

    @Override
    public void callBack() {
        uppayBtn.setEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ("3".equals(SPUtil.getStringValue("wxpay"))) {
            SPUtil.addParm("wxpay", "");
            finish();
        }
    }

    @Override
    public UpPayView createView() {
        return this;
    }

    @Override
    public UpPayPresenter createPresenter() {
        return new UpPayPresenter(this);
    }
}
