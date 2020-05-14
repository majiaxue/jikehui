package com.example.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Html;
import android.widget.TextView;

import com.example.module_mine.R;

public class CountDownTimerUtil {
    public static void startTimer(final Context context, final TextView textView) {
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String str = "<font color='#666666'>剩余</font>" + "<font color='#fb4119'> " + millisUntilFinished / 1000 + "s" + "</font>";
                textView.setText(Html.fromHtml(str));
            }

            @Override
            public void onFinish() {
                textView.setText(context.getResources().getString(R.string.get_code));
                textView.setTextColor(Color.parseColor("#fb4119"));
                textView.setBackgroundResource(R.drawable.bg_get_code);
                textView.setEnabled(true);
            }
        }.start();
    }
}
