package com.example.ali_account;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

public class AliAccountActivity extends BaseActivity<AliAccountView, AliAccountPresneter> implements AliAccountView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.ali_account_name)
    EditText aliAccountName;
    @BindView(R2.id.ali_account_account)
    EditText aliAccountAccount;
    @BindView(R2.id.ali_account_btn)
    TextView aliAccountBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ali_account;
    }

    @Override
    public void initData() {
        includeTitle.setText("添加账户");
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        aliAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.commit(aliAccountName.getText().toString(), aliAccountAccount.getText().toString());
            }
        });
    }

    @Override
    public AliAccountView createView() {
        return this;
    }

    @Override
    public AliAccountPresneter createPresenter() {
        return new AliAccountPresneter(this);
    }

}
