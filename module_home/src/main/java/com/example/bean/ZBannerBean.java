package com.example.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

public class ZBannerBean extends SimpleBannerInfo {
    private String image;

    public ZBannerBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public Object getXBannerUrl() {
        return image;
    }
}
