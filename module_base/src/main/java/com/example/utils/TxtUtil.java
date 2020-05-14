package com.example.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.common.CommonResource;

import java.math.BigDecimal;

public class TxtUtil {
    public static boolean isFirst = true;

    public static void txtJianbian(TextView txt, String startColor, String endColor) {
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, txt.getPaint().getTextSize(), Color.parseColor(startColor), Color.parseColor(endColor), Shader.TileMode.CLAMP);
        txt.getPaint().setShader(linearGradient);
        txt.invalidate();
    }

    public static String parse(long value) {
        if (value >= 10000) {
            double v = value * 1.0 / 10000;
            return new BigDecimal(v).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue() + "万人";
        }
        return value + "人";
    }

    public static void hasClipboard(final Context context, boolean isMain) {
        try {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            CharSequence text = clipboard.getText();
            if (text != null) {
                String string = text.toString().trim().replace(" ", "");
                SharedPreferences sp = context.getSharedPreferences("clipboard", 0);
                String value = sp.getString(CommonResource.TAN_CONTENT, "");
                LogUtil.e(string + "--------------------" + value);
                isFirst = false;
                if (!TextUtils.isEmpty(string)) {
                    if (isMain || !string.equals(value)) {
                        PopUtils.popClipboard(context, string);
                        sp.edit().putString(CommonResource.TAN_CONTENT, string).commit();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
