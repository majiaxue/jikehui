package com.example.bean;

import java.io.Serializable;

/**
 * Created by cuihaohao on 2019/5/23
 * Describe:
 */
public class ShippingAddressBean implements Serializable {


    /**
     * id : 2
     * userCode : 297881222686703616
     * postCode : 20002
     * addressName : 张三
     * addressPhone : 12312312312
     * addressProvince : 2
     * addressCity : 52
     * addressArea : 501
     * addressDetail : 金水路
     * addressCreateTime : 2019-05-15 00:00:00
     * addressUpdateTime : 2019-05-15 00:00:00
     * addressDefault : 0
     * addressStatus : 1
     * addressTips : null
     */

    private int id;
    private String userCode;
    private String postCode;
    private String addressName;
    private String addressPhone;
    private String addressProvince;
    private String addressCity;
    private String addressArea;
    private String addressDetail;
    private String addressCreateTime;
    private String addressUpdateTime;
    private int addressDefault;
    private int addressStatus;
    private int addressTips;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAddressCreateTime() {
        return addressCreateTime;
    }

    public void setAddressCreateTime(String addressCreateTime) {
        this.addressCreateTime = addressCreateTime;
    }

    public String getAddressUpdateTime() {
        return addressUpdateTime;
    }

    public void setAddressUpdateTime(String addressUpdateTime) {
        this.addressUpdateTime = addressUpdateTime;
    }

    public int getAddressDefault() {
        return addressDefault;
    }

    public void setAddressDefault(int addressDefault) {
        this.addressDefault = addressDefault;
    }

    public int getAddressStatus() {
        return addressStatus;
    }

    public void setAddressStatus(int addressStatus) {
        this.addressStatus = addressStatus;
    }

    public int getAddressTips() {
        return addressTips;
    }

    public void setAddressTips(int addressTips) {
        this.addressTips = addressTips;
    }

    @Override
    public String toString() {
        return "ShippingAddressBean{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", postCode='" + postCode + '\'' +
                ", addressName='" + addressName + '\'' +
                ", addressPhone='" + addressPhone + '\'' +
                ", addressProvince='" + addressProvince + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressArea='" + addressArea + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", addressCreateTime='" + addressCreateTime + '\'' +
                ", addressUpdateTime='" + addressUpdateTime + '\'' +
                ", addressDefault=" + addressDefault +
                ", addressStatus=" + addressStatus +
                ", addressTips=" + addressTips +
                '}';
    }
}
