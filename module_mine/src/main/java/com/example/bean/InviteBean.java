package com.example.bean;

import android.graphics.Bitmap;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

public class InviteBean extends SimpleBannerInfo {
    private Bitmap bitmap;

    public InviteBean(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public String toString() {
        return "InviteBean{" +
                "bitmap=" + bitmap +
                '}';
    }

    @Override
    public Bitmap getXBannerUrl() {
        return bitmap;
    }
}
