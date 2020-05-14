package com.example.bean;

public class ConfirmOrderInsideBean {
    /**
     * 运费
     */
    private double freightAmount;
    /**
     * 优惠券ID
     */
    private String couponId = "0";

    /**
     * 优惠券分解金额
     */
    private double couponAmount = 0;

    private Long sellerId;

    public double getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(double freightAmount) {
        this.freightAmount = freightAmount;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public double getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(double couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "OrderRequestItem{" +
                "freightAmount=" + freightAmount +
                ", couponId=" + couponId +
                ", couponAmount=" + couponAmount +
                ", sellerId=" + sellerId +
                '}';
    }
}
