package com.example.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

public class BannerImageBean extends SimpleBannerInfo {
    private  String uri;

    public BannerImageBean(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String getXBannerUrl() {
        return uri;
    }
}
