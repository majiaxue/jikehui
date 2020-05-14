package com.example.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class CommodityXBannerBean extends SimpleBannerInfo {
    private int url;

    public CommodityXBannerBean(int url) {
        this.url = url;
    }

    @Override
    public Object getXBannerUrl() {
        return url;
    }
}
