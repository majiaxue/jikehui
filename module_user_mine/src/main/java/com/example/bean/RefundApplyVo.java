package com.example.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/16
 * Describe:
 */
public class RefundApplyVo {
    private String orderId;
    private String productId;
    private String productName;
    private String orderSn;
    private List<String> proofPics;//凭证图片,分割
    private String productPic;//商品主图
    private String reason;//退款原因
    private String returnType;//退款类型
    private String memberUsername;//会员用户名
    private String returnPhone;//退货人电话
    private String returnName;//退货人姓名
    private Integer productCount;//退货数量
    private double productPrice;//商品单价
    private double productRealPrice;//商品实际支付单价
    private String sellerId;//商家ID
    private double returnAmount;
    private String description;

    public double getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(double returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public List<String> getProofPics() {
        return proofPics;
    }

    public void setProofPics(List<String> proofPics) {
        this.proofPics = proofPics;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public String getReturnPhone() {
        return returnPhone;
    }

    public void setReturnPhone(String returnPhone) {
        this.returnPhone = returnPhone;
    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductRealPrice() {
        return productRealPrice;
    }

    public void setProductRealPrice(double productRealPrice) {
        this.productRealPrice = productRealPrice;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "RefundApplyVo{" +
                "orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", orderSn='" + orderSn + '\'' +
                ", proofPics=" + proofPics +
                ", productPic='" + productPic + '\'' +
                ", reason='" + reason + '\'' +
                ", memberUsername='" + memberUsername + '\'' +
                ", returnPhone='" + returnPhone + '\'' +
                ", returnName='" + returnName + '\'' +
                ", productCount=" + productCount +
                ", productPrice=" + productPrice +
                ", productRealPrice=" + productRealPrice +
                ", sellerId='" + sellerId + '\'' +
                '}';
    }
}
