package com.example.bean;

/**
 * Created by cuihaohao on 2019/6/12
 * Describe:
 */
public class AddressInfo {

    //所在区、县
    private String addressArea;

    //所在城市
    private String addressCity;

    //创建时间
    private String addressCreateTime;

    //默认收货地址（0：否 1：是）
    private String addressDefault;

    // 详细地址

    private String addressDetail;

    // 收货人姓名

    private String addressName;

    //联系方式

    private String addressPhone;

    //所在省份

    private String addressProvince;

    //状态（0：无效 1：有效）

    private String addressStatus;

    // 标签 1：家 2：公司 3：学校

    private String addressTips;

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressCreateTime() {
        return addressCreateTime;
    }

    public void setAddressCreateTime(String addressCreateTime) {
        this.addressCreateTime = addressCreateTime;
    }

    public String getAddressDefault() {
        return addressDefault;
    }

    public void setAddressDefault(String addressDefault) {
        this.addressDefault = addressDefault;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
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

    public String getAddressStatus() {
        return addressStatus;
    }

    public void setAddressStatus(String addressStatus) {
        this.addressStatus = addressStatus;
    }

    public String getAddressTips() {
        return addressTips;
    }

    public void setAddressTips(String addressTips) {
        this.addressTips = addressTips;
    }
}
