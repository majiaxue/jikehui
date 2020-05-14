package com.example.contact_us;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.mvp.BasePresenter;

public class ContactUsPresenter extends BasePresenter<ContactUsView> {
    public ContactUsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        mContext.startActivity(intent);
    }
}
