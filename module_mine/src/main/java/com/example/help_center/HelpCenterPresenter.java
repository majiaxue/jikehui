package com.example.help_center;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.help_detail.HelpDetailActivity;
import com.example.mvp.BasePresenter;
import com.example.new_guide.NewGuideActivity;

public class HelpCenterPresenter extends BasePresenter<HelpCenterView> {
    public HelpCenterPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void jumpToDetail(int position) {
        mContext.startActivity(new Intent(mContext, HelpDetailActivity.class));
    }

    public void jumpToGuide() {
        mContext.startActivity(new Intent(mContext, NewGuideActivity.class));
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        mContext.startActivity(intent);
    }
}
