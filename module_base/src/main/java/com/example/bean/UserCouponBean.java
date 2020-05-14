package com.example.bean;

import java.io.Serializable;

public class UserCouponBean implements Serializable {

    /**
     * id : 1
     * type : 0
     * name : 测试优惠券添加
     * platform : 0
     * count : null
     * amount : 100
     * perLimit : 1
     * minPoint : 50
     * startTime : 2019-05-29 00:00:00
     * endTime : 2019-05-31 00:00:00
     * useType : 2
     * note : 测试优惠券添加test
     * publishCount : 100
     * useCount : null
     * receiveCount : null
     * enableTime : null
     * code : null
     * memberLevel : null
     * sellerId : 13
     * sellerName :
     * goodsId : 27
     * goodsCategoryId : 91
     */

    private String id;
    private int type;
    private String name;
    private int platform;
    private Integer count;
    private double amount;
    private double perLimit;
    private double minPoint;
    private String startTime;
    private String endTime;
    private int useType;
    private String note;
    private int publishCount;
    private String useCount;
    private String receiveCount;
    private String enableTime;
    private String code;
    private String memberLevel;
    private String sellerId;
    private String sellerName;
    private String goodsId;
    private String goodsCategoryId;
    private boolean isHas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPerLimit() {
        return perLimit;
    }

    public void setPerLimit(double perLimit) {
        this.perLimit = perLimit;
    }

    public double getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(double minPoint) {
        this.minPoint = minPoint;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getUseType() {
        return useType;
    }

    public void setUseType(int useType) {
        this.useType = useType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPublishCount() {
        return publishCount;
    }

    public void setPublishCount(int publishCount) {
        this.publishCount = publishCount;
    }

    public String getUseCount() {
        return useCount;
    }

    public void setUseCount(String useCount) {
        this.useCount = useCount;
    }

    public String getReceiveCount() {
        return receiveCount;
    }

    public void setReceiveCount(String receiveCount) {
        this.receiveCount = receiveCount;
    }

    public String getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(String enableTime) {
        this.enableTime = enableTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(String goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public boolean isHas() {
        return isHas;
    }

    public void setHas(boolean has) {
        isHas = has;
    }

    @Override
    public String toString() {
        return "UserCouponBean{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", platform=" + platform +
                ", count=" + count +
                ", amount=" + amount +
                ", perLimit=" + perLimit +
                ", minPoint=" + minPoint +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", useType=" + useType +
                ", note='" + note + '\'' +
                ", publishCount=" + publishCount +
                ", useCount='" + useCount + '\'' +
                ", receiveCount='" + receiveCount + '\'' +
                ", enableTime='" + enableTime + '\'' +
                ", code='" + code + '\'' +
                ", memberLevel='" + memberLevel + '\'' +
                ", sellerId=" + sellerId +
                ", sellerName='" + sellerName + '\'' +
                ", goodsId=" + goodsId +
                ", goodsCategoryId=" + goodsCategoryId +
                ", isHas=" + isHas +
                '}';
    }
}
