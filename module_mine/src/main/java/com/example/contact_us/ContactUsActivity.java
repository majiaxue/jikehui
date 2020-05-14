package com.example.contact_us;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

/**
 * 联系客服
 */
@Route(path = "/mine/contactus")
public class ContactUsActivity extends BaseActivity<ContactUsView, ContactUsPresenter> implements ContactUsView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.contact_us_phonenum)
    TextView contactUsPhonenum;
    @BindView(R2.id.contact_us_call)
    TextView contactUsCall;

    @Override
    public int getLayoutId() {
        return R.layout.activity_contact_us;
    }

    @Override
    public void initData() {
        includeTitle.setText("联系我们");
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        contactUsCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callPhone(CommonResource.SERVICE_PHONE);
            }
        });
    }

    @Override
    public ContactUsView createView() {
        return this;
    }

    @Override
    public ContactUsPresenter createPresenter() {
        return new ContactUsPresenter(this);
    }
}
