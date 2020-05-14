package com.example.bean;

public class MyOrderBean {

    /**
     * id : 77
     * orderSn : 7
     * goodsId : 1
     * goodsName : 123
     * goodsThumbnailUrl : 123
     * goodsQuantity : 123
     * goodsPrice : 123
     * orderAmount : 123
     * promotionRate : 123
     * promotionAmount : 123
     * orderStatus : 5
     * orderStatusDesc : 123
     * orderCreateTime : 123123123
     * orderPayTime : 123123123
     * orderGroupSuccessTime : 123123123
     * orderVerifyTime : 123
     * orderModifyAt : 12312
     * customParameters : 3123
     * userCode : 293644338167021568
     * pid : 123
     */

    private String id;
    private String orderSn;
    private String goodsId;
    private String goodsName;
    private String goodsThumbnailUrl;
    private String goodsQuantity;
    private int goodsPrice;
    private int orderAmount;
    private int promotionRate;
    private int promotionAmount;
    private int orderStatus;
    private String orderStatusDesc;
    private String orderCreateTime;
    private String orderPayTime;
    private String orderGroupSuccessTime;
    private String orderVerifyTime;
    private String orderModifyAt;
    private String customParameters;
    private String userCode;
    private String pid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsThumbnailUrl() {
        return goodsThumbnailUrl;
    }

    public void setGoodsThumbnailUrl(String goodsThumbnailUrl) {
        this.goodsThumbnailUrl = goodsThumbnailUrl;
    }

    public String getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(String goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(int promotionRate) {
        this.promotionRate = promotionRate;
    }

    public int getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(int promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    public void setOrderStatusDesc(String orderStatusDesc) {
        this.orderStatusDesc = orderStatusDesc;
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(String orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public String getOrderGroupSuccessTime() {
        return orderGroupSuccessTime;
    }

    public void setOrderGroupSuccessTime(String orderGroupSuccessTime) {
        this.orderGroupSuccessTime = orderGroupSuccessTime;
    }

    public String getOrderVerifyTime() {
        return orderVerifyTime;
    }

    public void setOrderVerifyTime(String orderVerifyTime) {
        this.orderVerifyTime = orderVerifyTime;
    }

    public String getOrderModifyAt() {
        return orderModifyAt;
    }

    public void setOrderModifyAt(String orderModifyAt) {
        this.orderModifyAt = orderModifyAt;
    }

    public String getCustomParameters() {
        return customParameters;
    }

    public void setCustomParameters(String customParameters) {
        this.customParameters = customParameters;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "MyOrderBean{" +
                "id=" + id +
                ", orderSn='" + orderSn + '\'' +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsThumbnailUrl='" + goodsThumbnailUrl + '\'' +
                ", goodsQuantity=" + goodsQuantity +
                ", goodsPrice=" + goodsPrice +
                ", orderAmount=" + orderAmount +
                ", promotionRate=" + promotionRate +
                ", promotionAmount=" + promotionAmount +
                ", orderStatus=" + orderStatus +
                ", orderStatusDesc='" + orderStatusDesc + '\'' +
                ", orderCreateTime=" + orderCreateTime +
                ", orderPayTime=" + orderPayTime +
                ", orderGroupSuccessTime=" + orderGroupSuccessTime +
                ", orderVerifyTime=" + orderVerifyTime +
                ", orderModifyAt=" + orderModifyAt +
                ", customParameters='" + customParameters + '\'' +
                ", userCode='" + userCode + '\'' +
                ", pid='" + pid + '\'' +
                '}';
    }
}
