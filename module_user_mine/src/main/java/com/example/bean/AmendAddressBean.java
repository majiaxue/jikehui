package com.example.bean;

/**
 * Created by cuihaohao on 2019/6/10
 * Describe:
 */
public class AmendAddressBean {
    /**
     * 唯一标识
     */
    private Integer id;
    /**
     * 用户ID
     */
    private String userCode;
    /**
     * 收货人姓名
     */
    private String addressName;
    /**
     * 联系方式
     */
    private String addressPhone;
    /**
     * 所在省份
     */
    private String addressProvince;
    /**
     * 所在城市
     */
    private String addressCity;
    /**
     * 所在区、县
     */
    private String addressArea;
    /**
     * 详细地址
     */
    private String addressDetail;
    /**
     * 是否默认（0：否 1：是）
     */
    private Integer addressDefault;
    /**
     * 标签(1 家 2 公司 3  学校)
     */
    private int addressTips;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public Integer getAddressDefault() {
        return addressDefault;
    }

    public void setAddressDefault(Integer addressDefault) {
        this.addressDefault = addressDefault;
    }

    public int getAddressTips() {
        return addressTips;
    }

    public void setAddressTips(int addressTips) {
        this.addressTips = addressTips;
    }
}
