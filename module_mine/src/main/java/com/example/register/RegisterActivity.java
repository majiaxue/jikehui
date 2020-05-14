package com.example.register;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.CountDownTimerUtil;
import com.example.utils.PhoneNumUtil;

import butterknife.BindView;

/**
 * 注册
 */
@Route(path = "/module_mine/RegisterActivity")
public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter> implements RegisterView {
    @BindView(R2.id.register_back)
    ImageView registerBack;
    @BindView(R2.id.register_phone)
    EditText registerPhone;
    @BindView(R2.id.register_code)
    EditText registerCode;
    @BindView(R2.id.register_get_code)
    TextView registerGetCode;
    @BindView(R2.id.register_invite_code)
    EditText registerInviteCode;
    @BindView(R2.id.register_password)
    EditText registerPassword;
    @BindView(R2.id.register_btn)
    TextView registerBtn;
    @BindView(R2.id.register_check)
    ImageView registerCheck;
    @BindView(R2.id.register_user_agreement)
    TextView registerUserAgreement;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        //阅读协议勾选
        registerCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.check();
            }
        });

        registerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //获取验证码
        registerGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getCodeNum(registerPhone.getText().toString());
            }
        });

        registerUserAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/mine/agreement").withString("type", "zcxy").navigation();
            }
        });

        registerBtn.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!PhoneNumUtil.isMobileNO(registerPhone.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(registerCode.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入邀请码", Toast.LENGTH_SHORT).show();
                }
                else if (!presenter.isRead) {
                    Toast.makeText(RegisterActivity.this, "请勾选用户协议", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(registerPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请设置密码", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.toRegister(registerPhone.getText().toString(), registerPassword.getText().toString(), registerCode.getText().toString(), registerInviteCode.getText().toString());
                }
            }
        });
    }

    @Override
    public void readed() {
        registerCheck.setImageResource(R.drawable.icon_yiyuedu);
    }

    @Override
    public void noRead() {
        registerCheck.setImageResource(R.drawable.icon_weiyuedu);
    }

    @Override
    public void getCodeSuccess() {
        registerGetCode.setEnabled(false);
        registerGetCode.setBackgroundResource(R.drawable.bg_get_code_clicked);
        CountDownTimerUtil.startTimer(this, registerGetCode);
    }

    @Override
    public RegisterView createView() {
        return this;
    }

    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }
}
