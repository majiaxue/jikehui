package com.example.main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;

import com.example.module_home.R;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;
import com.example.utils.StatusBarUtils;

/**
 * 启动页
 */
public class WelcomeActivity extends Activity {
    private boolean isFirstIn = false;
    private static final int TIME = 1500;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        SharedPreferences preferences = getSharedPreferences("first_pref", MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("isFirstIn", false);
        init();
        changeStatus();
    }

    private void changeStatus() {
        // 设置状态栏
        StatusBarUtils.transparencyBar(this);
        StatusBarUtils.setStatusTheme(this, true, true);
    }

    private void init() {

        if (isFirstIn) {
            handler.sendEmptyMessageDelayed(GO_HOME, TIME);
        } else {
            SharedPreferences preferences = getSharedPreferences("first_pref", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstIn", true);
            editor.commit();
            handler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
        }
    }

    private void goHome() {
        Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goGuide() {
        Intent i = new Intent(WelcomeActivity.this, GuideActivity.class);
        startActivity(i);
        finish();
    }
}
