package com.example.login_wechat;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.UserInfoBean;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.CountDownTimerUtil;

import butterknife.BindView;

/**
 * 微信登录
 */
public class LoginWeChatActivity extends BaseActivity<LoginWeChatView, LoginWeChatPresenter> implements LoginWeChatView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.wechat_phone)
    EditText wechatPhone;
    @BindView(R2.id.wechat_code)
    EditText wechatCode;
    @BindView(R2.id.wechat_get_code)
    TextView wechatGetCode;
    @BindView(R2.id.wechat_invite_code)
    EditText wechatInviteCode;
    @BindView(R2.id.wechat_btn)
    TextView wechatBtn;
    @BindView(R2.id.wechat_check)
    ImageView wechatCheck;
    @BindView(R2.id.wechat_user_agreement)
    TextView wechatUserAgreement;
    private UserInfoBean userInfoBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_wechat;
    }

    @Override
    public void initData() {
        includeTitle.setText(getResources().getString(R.string.wechat_login));
        Intent intent = getIntent();
        userInfoBean = (UserInfoBean) intent.getSerializableExtra("bean");

    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        wechatCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.check();
            }
        });

        wechatGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getCodeNum(wechatPhone.getText().toString());
            }
        });

        wechatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(wechatPhone.getText().toString())) {
                    Toast.makeText(LoginWeChatActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(wechatCode.getText().toString())) {
                    Toast.makeText(LoginWeChatActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                } else {
                    userInfoBean.setCheckCode(wechatCode.getText().toString());
                    userInfoBean.setPhone(wechatPhone.getText().toString());
                    userInfoBean.setInviteCode(wechatInviteCode.getText().toString());
                    presenter.login(userInfoBean);
                }
            }
        });

        wechatUserAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/mine/agreement").withString("type", "wxxy").navigation();
            }
        });
    }

    @Override
    public void readed() {
        wechatCheck.setImageResource(R.drawable.icon_yiyuedu);
    }

    @Override
    public void noRead() {
        wechatCheck.setImageResource(R.drawable.icon_weiyuedu);
    }

    @Override
    public void getCodeSuccess() {
        wechatGetCode.setEnabled(false);
        wechatGetCode.setBackgroundResource(R.drawable.bg_get_code_clicked);
        CountDownTimerUtil.startTimer(this, wechatGetCode);
    }

    @Override
    public LoginWeChatView createView() {
        return this;
    }

    @Override
    public LoginWeChatPresenter createPresenter() {
        return new LoginWeChatPresenter(this);
    }
}
