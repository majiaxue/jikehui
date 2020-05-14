package com.example.bean;

public class AddressBean {

    /**
     * id : 7
     * userCode : 296539935500926976
     * postCode : 450000
     * addressName : 晓玉
     * addressPhone : 13201835918
     * addressProvince : 河南省
     * addressCity : 郑州市
     * addressArea : 金水区
     * addressDetail : 金城国际3号楼
     * addressCreateTime : 2019-06-10 15:47:02
     * addressUpdateTime : 2019-06-10 15:47:04
     * addressDefault : 1
     * addressStatus : 1
     * addressTips : 1
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
        return "AddressBean{" +
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
