package com.example.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Map;

public class QRCode {

    public static Bitmap createQRImage(String content, int widthPix, int heightPix) {
        try {
//            if (content == null || "".equals(content)) {
//                return false;
//            }

            //配置参数
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //容错级别
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            //设置空白边距的宽度
            hints.put(EncodeHintType.MARGIN, 1); //default is 4

            // 图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = null;
            try {
                bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, widthPix,
                        heightPix, hints);
            } catch (WriterException e) {
                e.printStackTrace();
            }
            int[] pixels = new int[widthPix * heightPix];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (int y = 0; y < heightPix; y++) {
                for (int x = 0; x < widthPix; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * widthPix + x] = 0xff000000;
                    } else {
                        pixels[y * widthPix + x] = 0xffffffff;
                    }
                }
            }

            // 生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix);

            //必须使用compress方法将bitmap保存到文件中再进行读取。直接返回的bitmap是没有任何压缩的，
            // 内存消耗巨大！
            return bitmap;
//            return bitmap != null && bitmap.compress(Bitmap.CompressFormat.JPEG, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //给二维码添加图片
    //第一个参数为原二维码，第二个参数为添加的logo
    public static Bitmap addLogo(Context context, Bitmap src, int logoRes) {
        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), logoRes);

        //如果原二维码为空，返回空
        if (src == null) {
            return null;
        }
        //如果logo为空，返回原二维码
        if (src == null || logo == null) {
            return src;
        }

        //这里得到原二维码bitmap的数据
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        //logo的Width和Height
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        //同样如果为空，返回空
        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }
        //同样logo大小为0，返回原二维码
        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }

        //logo大小为二维码整体大小的1/5，也可以自定义多大，越小越好
        //二维码有一定的纠错功能，中间图片越小，越容易纠错
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);

            canvas.save();
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }

        return bitmap;
    }
}
