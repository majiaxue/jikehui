package com.example.ali_account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.mvp.BasePresenter;

public class AliAccountPresneter extends BasePresenter<AliAccountView> {
    public AliAccountPresneter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void commit(String name, String account) {
        if ("".equals(name) || "".equals(account)) {
            Toast.makeText(mContext, "请把信息填写完整", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("account", account);
            ((Activity) mContext).setResult(Activity.RESULT_OK, intent);
            ((Activity) mContext).finish();
        }
    }
}
