package com.example.login;

import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.entity.EventBusBean;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.register.NoDoubleClickListener;
import com.example.utils.CountDownTimerUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 登录
 */
@Route(path = "/mine/login")
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

    @BindView(R2.id.login_login_text)
    TextView loginLoginText;
    @BindView(R2.id.login_reg_text)
    TextView loginRegText;
    @BindView(R2.id.login_name)
    EditText loginName;
    @BindView(R2.id.login_code)
    EditText loginCode;
    @BindView(R2.id.register_get_code)
    TextView registerGetCode;
    @BindView(R2.id.login_visi_login1)
    RelativeLayout loginVisiLogin1;
    @BindView(R2.id.login_visi_login2)
    View loginVisiLogin2;
    @BindView(R2.id.login_invite_code)
    EditText loginInviteCode;
    @BindView(R2.id.login_visi_login3)
    View loginVisiLogin3;
    @BindView(R2.id.login_password)
    EditText loginPassword;
    @BindView(R2.id.login_forget)
    TextView loginForget;
    @BindView(R2.id.login_confirmLogin)
    TextView loginConfirmLogin;
    @BindView(R2.id.login_visi_register1)
    LinearLayout loginVisiRegister1;
    @BindView(R2.id.login_user_agreement)
    TextView loginUserAgreement;
    @BindView(R2.id.login_btn_login)
    ImageView loginBtnLogin;
    @BindView(R2.id.login_weixin)
    ImageView weiXin;
    @BindView(R2.id.login_visi_login4)
    LinearLayout loginVisiLogin4;
    @BindView(R2.id.login_read_check)
    ImageView mReadCheck;
    @BindView(R2.id.login_visi_register2)
    View loginVisiRegister2;


    private boolean isLogin = true;
    private boolean isRead = true;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initClick() {
        //点击登录---注册
        //防止暴力点击  NoDoubleClickListener()
        loginBtnLogin.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if (isLogin) {
                    presenter.login(loginName.getText().toString(), loginPassword.getText().toString());
                } else {
                    if (isRead) {
                        presenter.register(loginName.getText().toString(), loginPassword.getText().toString(), loginCode.getText().toString(), loginInviteCode.getText().toString());
                    } else {
                        Toast.makeText(LoginActivity.this, "请先阅读并同意用户协议", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //登录页面
        loginLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLogin = true;
                loginVisiLogin1.setVisibility(View.GONE);
                loginVisiLogin2.setVisibility(View.GONE);
                loginVisiLogin3.setVisibility(View.GONE);
                loginVisiLogin4.setVisibility(View.GONE);
                loginInviteCode.setVisibility(View.GONE);
                loginVisiRegister1.setVisibility(View.VISIBLE);
                loginVisiRegister2.setVisibility(View.VISIBLE);
                loginLoginText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21);
                loginRegText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

            }
        });

        //注册页面
        loginRegText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLogin = false;
                loginVisiLogin1.setVisibility(View.VISIBLE);
                loginVisiLogin2.setVisibility(View.VISIBLE);
                loginVisiLogin3.setVisibility(View.VISIBLE);
                loginVisiLogin4.setVisibility(View.VISIBLE);
                loginInviteCode.setVisibility(View.VISIBLE);
                loginVisiRegister1.setVisibility(View.GONE);
                loginVisiRegister2.setVisibility(View.GONE);
                loginLoginText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                loginRegText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21);
            }
        });
        //忘记密码
        loginForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toForget();
            }
        });
        //手机验证登录
        loginConfirmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toCodeLogin();
            }
        });

        weiXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.WeChatLogin();
            }
        });

        loginVisiLogin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRead = !isRead;
                mReadCheck.setImageResource(isRead ? R.drawable.xuanzhong : R.drawable.weixuanzhong);
            }
        });

        loginUserAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/mine/agreement").withString("type", "zcxy").navigation();
            }
        });

        registerGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerGetCode.setEnabled(false);
                presenter.getCodeNum(loginName.getText().toString());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean eventBusBean) {
        if ("WXCODE".equals(eventBusBean.getMsg())) {
            presenter.sendCode();
        }
    }

    @Override
    public void getCodeSuccess() {
        registerGetCode.setEnabled(true);
        registerGetCode.setBackgroundResource(R.drawable.bg_get_code_clicked);
        CountDownTimerUtil.startTimer(this, registerGetCode);
    }

    @Override
    public void getCodeFail() {
        registerGetCode.setEnabled(true);
    }

    @Override
    public LoginView createView() {
        return this;
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }
}
