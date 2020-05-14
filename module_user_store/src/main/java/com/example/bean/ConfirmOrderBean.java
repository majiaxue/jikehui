package com.example.bean;

import java.util.List;

public class ConfirmOrderBean {
    /**
     * 收货人手机号
     */
    private String receiverPhone;
    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 区
     */
    private String receiverRegion;
    /**
     * 城市
     */
    private String receiverCity;
    /**
     * 省份
     */
    private String receiverProvince;
    /**
     * 订单收货地址
     */
    private String orderAddress;

    private double couponAmount = 0;

    /**
     * 订单详情数组
     */
    List<ConfirmOrderInsideBean> orderRequestItems;

    private String userId;


    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverRegion() {
        return receiverRegion;
    }

    public void setReceiverRegion(String receiverRegion) {
        this.receiverRegion = receiverRegion;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public List<ConfirmOrderInsideBean> getOrderRequestItems() {
        return orderRequestItems;
    }

    public void setOrderRequestItems(List<ConfirmOrderInsideBean> orderRequestItems) {
        this.orderRequestItems = orderRequestItems;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ConfirmOrderBean{" +
                "receiverPhone='" + receiverPhone + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverRegion='" + receiverRegion + '\'' +
                ", receiverCity='" + receiverCity + '\'' +
                ", receiverProvince='" + receiverProvince + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderRequestItems=" + orderRequestItems +
                ", userId=" + userId +
                '}';
    }
}
