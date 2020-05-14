package com.example.code_login;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.CountDownTimerUtil;

import butterknife.BindView;

/**
 * 手机验证登录
 */
public class CodeLoginActivity extends BaseActivity<CodeLoginView, CodeLoginPresenter> implements CodeLoginView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.code_login_phone)
    EditText codeLoginPhone;
    @BindView(R2.id.code_login_code)
    EditText codeLoginCode;
    @BindView(R2.id.code_login_get_code)
    TextView codeLoginGetCode;
    @BindView(R2.id.code_login_btn)
    TextView codeLoginBtn;
    @BindView(R2.id.code_login_check)
    ImageView codeLoginCheck;
    @BindView(R2.id.code_login_user_agreement)
    TextView codeLoginUserAgreement;

    @Override
    public int getLayoutId() {
        return R.layout.activity_code_login;
    }

    @Override
    public void initData() {
        includeTitle.setText("验证码登录");
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        codeLoginGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getCode(codeLoginPhone.getText().toString());
            }
        });

        codeLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.submit(codeLoginPhone.getText().toString(), codeLoginCode.getText().toString());
            }
        });

        codeLoginCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkAgreement();
            }
        });
    }

    @Override
    public void getCodeSuccess() {
        codeLoginGetCode.setEnabled(false);
        codeLoginGetCode.setBackgroundResource(R.drawable.bg_get_code_clicked);
        CountDownTimerUtil.startTimer(this, codeLoginGetCode);
    }

    @Override
    public void agreeAgreement() {
        codeLoginCheck.setImageResource(R.drawable.icon_yiyuedu);
    }

    @Override
    public void disagreeAgreement() {
        codeLoginCheck.setImageResource(R.drawable.icon_weiyuedu);
    }

    @Override
    public CodeLoginView createView() {
        return this;
    }

    @Override
    public CodeLoginPresenter createPresenter() {
        return new CodeLoginPresenter(this);
    }
}
