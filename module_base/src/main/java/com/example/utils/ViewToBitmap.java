package com.example.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViewToBitmap {

    public static Bitmap createBitmap3(View v, int width, int height) {
        //测量使得view指定大小
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        v.measure(measuredWidth, measuredHeight);
        //调用layout方法布局后，可以得到view的尺寸大小
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        c.drawColor(Color.WHITE);
        v.draw(c);
        return bitmap;
    }

    public static Bitmap GetImageInputStream(String imageurl){
        URL url;
        HttpURLConnection connection=null;
        Bitmap bitmap=null;
        try {
            url = new URL(imageurl);
            connection=(HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(6000); //超时设置
            connection.setDoInput(true);
            connection.setUseCaches(false); //设置不使用缓存
            InputStream inputStream=connection.getInputStream();
            bitmap=BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static void saveBitmap(Bitmap bitmap1) {
        FileOutputStream fos;
        try {
            File root = Environment.getExternalStorageDirectory();
            File file = new File(root, "test.jpg");
            fos = new FileOutputStream(file);
            bitmap1.compress(Bitmap.CompressFormat.PNG, 30, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
}
