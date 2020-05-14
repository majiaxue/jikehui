package com.example.bean;

import java.util.List;

public class CommunityLocalBean {
    private String id;
    private String itempic;
    private String itemtitle;
    private String couponurl;
    private String content;
    private String copyContent;
    private String tkrates;
    private String couponmoney;
    private String dummyClickStatistics;
    private String itemprice;
    private String communityType;
    private String mallType;
    private String sellerIcon;  //自己添加字段
    private String time;
    private List<String> pics;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSellerIcon() {
        return sellerIcon;
    }

    public void setSellerIcon(String sellerIcon) {
        this.sellerIcon = sellerIcon;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItempic() {
        return itempic;
    }

    public void setItempic(String itempic) {
        this.itempic = itempic;
    }

    public String getItemtitle() {
        return itemtitle;
    }

    public void setItemtitle(String itemtitle) {
        this.itemtitle = itemtitle;
    }

    public String getCouponurl() {
        return couponurl;
    }

    public void setCouponurl(String couponurl) {
        this.couponurl = couponurl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCopyContent() {
        return copyContent;
    }

    public void setCopyContent(String copyContent) {
        this.copyContent = copyContent;
    }

    public String getTkrates() {
        return tkrates;
    }

    public void setTkrates(String tkrates) {
        this.tkrates = tkrates;
    }

    public String getCouponmoney() {
        return couponmoney;
    }

    public void setCouponmoney(String couponmoney) {
        this.couponmoney = couponmoney;
    }

    public String getDummyClickStatistics() {
        return dummyClickStatistics;
    }

    public void setDummyClickStatistics(String dummyClickStatistics) {
        this.dummyClickStatistics = dummyClickStatistics;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getCommunityType() {
        return communityType;
    }

    public void setCommunityType(String communityType) {
        this.communityType = communityType;
    }

    public String getMallType() {
        return mallType;
    }

    public void setMallType(String mallType) {
        this.mallType = mallType;
    }

    @Override
    public String toString() {
        return "CommunityLocalBean{" +
                "id='" + id + '\'' +
                ", itempic='" + itempic + '\'' +
                ", itemtitle='" + itemtitle + '\'' +
                ", couponurl='" + couponurl + '\'' +
                ", content='" + content + '\'' +
                ", copyContent='" + copyContent + '\'' +
                ", tkrates='" + tkrates + '\'' +
                ", couponmoney='" + couponmoney + '\'' +
                ", dummyClickStatistics='" + dummyClickStatistics + '\'' +
                ", itemprice='" + itemprice + '\'' +
                ", communityType='" + communityType + '\'' +
                ", mallType='" + mallType + '\'' +
                ", sellerIcon='" + sellerIcon + '\'' +
                ", time='" + time + '\'' +
                ", pics=" + pics +
                '}';
    }
}
