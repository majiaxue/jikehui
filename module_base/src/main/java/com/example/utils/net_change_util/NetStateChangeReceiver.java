package com.example.utils.net_change_util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.example.module_base.ModuleBaseApplication;
import com.example.utils.LogUtil;

public class NetStateChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            NetworkType networkType = NetworkUtil.getNetworkType(context);
            LogUtil.e("当前网络：" + networkType);
            if (networkType == NetworkType.NETWORK_NO) {
            } else {
                if (ModuleBaseApplication.isDingWei) {
                    ModuleBaseApplication.mLocationClient.restart();
                }
            }
        }
    }
}
