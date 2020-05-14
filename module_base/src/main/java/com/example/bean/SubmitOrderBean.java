package com.example.bean;

import java.io.Serializable;

public class SubmitOrderBean implements Serializable {

    /**
     * code : 0
     * totalAmount : 0
     * masterNo : 19061111160001
     */

    private int code;
    private double totalAmount;
    private String masterNo;
    private String productName;
    /**
     * 分类id
     */
    private String productCategoryId;

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getMasterNo() {
        return masterNo;
    }

    public void setMasterNo(String masterNo) {
        this.masterNo = masterNo;
    }

    @Override
    public String toString() {
        return "SubmitOrderBean{" +
                "code=" + code +
                ", totalAmount=" + totalAmount +
                ", masterNo='" + masterNo + '\'' +
                ", productName='" + productName + '\'' +
                ", productCategoryId='" + productCategoryId + '\'' +
                '}';
    }
}
