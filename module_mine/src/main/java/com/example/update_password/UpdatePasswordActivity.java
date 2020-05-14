package com.example.update_password;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.UserInfoBean;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 修改密码
 */
public class UpdatePasswordActivity extends BaseActivity<UpdatePasswordView, UpdatePasswordPresenter> implements UpdatePasswordView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.password_new)
    EditText passwordNew;
    @BindView(R2.id.password_new_second)
    EditText passwordNewSecond;
    @BindView(R2.id.password_btn)
    TextView passwordBtn;
    @BindView(R2.id.password_old)
    EditText passwordOld;
    @BindView(R2.id.password_new_img)
    ImageView imgNew;
    @BindView(R2.id.password_new_second_img)
    ImageView imgNewSecond;
    @BindView(R2.id.password_temp1)
    View mTemp1;

    private boolean firstShow = false;
    private boolean secondShow = false;
    private UserInfoBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_password;
    }

    @Override
    public void initData() {
        includeTitle.setText(getResources().getString(R.string.update_password));
        Intent intent = getIntent();
        bean = (UserInfoBean) intent.getSerializableExtra("bean");
        if ("".equals(bean.getPassword()) || bean.getPassword() == null) {
            passwordOld.setVisibility(View.GONE);
            mTemp1.setVisibility(View.GONE);
        } else {
            passwordOld.setVisibility(View.VISIBLE);
            mTemp1.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        passwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.commit(passwordOld.getText().toString(), passwordNew.getText().toString(), passwordNewSecond.getText().toString(), bean);
            }
        });

        imgNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstShow = !firstShow;
                passwordNew.setTransformationMethod(firstShow ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                imgNew.setImageResource(firstShow ? R.drawable.showpassword : R.drawable.hidepassword);
                passwordNew.setSelection(passwordNew.getText().length());
            }
        });

        imgNewSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondShow = !secondShow;
                passwordNewSecond.setTransformationMethod(secondShow ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                imgNewSecond.setImageResource(secondShow ? R.drawable.showpassword : R.drawable.hidepassword);
                passwordNewSecond.setSelection(passwordNewSecond.getText().length());
            }
        });
    }

    @Override
    public UpdatePasswordView createView() {
        return this;
    }

    @Override
    public UpdatePasswordPresenter createPresenter() {
        return new UpdatePasswordPresenter(this);
    }
}
