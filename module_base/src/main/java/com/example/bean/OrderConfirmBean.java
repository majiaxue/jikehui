package com.example.bean;

import java.io.Serializable;

public class OrderConfirmBean implements Serializable {
    /**
     * 对应的商店Id
     */
    private String sellerId;
    /**
     * 对应的商店名字
     */
    private String sellerName;
    /**
     * 对应的商品skuId
     */
    private String productSkuId;
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


    /**
     * 运费
     */
    private double freightAmount;

    /**
     * 订单留言
     */
    private String remark;

    /**
     * 运费模板ID
     */
    private Long feightTemplateId;

    /**
     * 优惠券ID
     */
    private String couponId = "0";
    /**
     * 优惠券抵扣金额
     */
    private double couponAmount = 0;

    /**
     * 购买数量
     */
    private Integer quantity;

    private String productAttr;
    /**
     * 属性1
     */
    private String sp1;
    /**
     * 属性2
     */
    private String sp2;


    /**
     * 订单来源：0->PC订单；1->app订单
     */
    private Integer sourceType;
    /**
     * 商品图片
     */
    private String pic;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品实际价格
     */
    private double price;
    /**
     * 库存
     */
    private Long stock;

    /**
     * 商品id
     */
    private String productId;
    /**
     * 分类id
     */
    private String productCategoryId;
    /**
     * 货号
     */
    private String productSn;
    /**
     * 折扣价格
     */
    private double promotionPrice;
    /**
     * 商品价格
     */
    private double productPrice;

    public double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(String productSkuId) {
        this.productSkuId = productSkuId;
    }

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

    public double getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(double freightAmount) {
        this.freightAmount = freightAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getFeightTemplateId() {
        return feightTemplateId;
    }

    public void setFeightTemplateId(Long feightTemplateId) {
        this.feightTemplateId = feightTemplateId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSp1() {
        return sp1;
    }

    public void setSp1(String sp1) {
        this.sp1 = sp1;
    }

    public String getSp2() {
        return sp2;
    }

    public void setSp2(String sp2) {
        this.sp2 = sp2;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public String toString() {
        return "OrderConfirmBean{" +
                "sellerId='" + sellerId + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", productSkuId='" + productSkuId + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverRegion='" + receiverRegion + '\'' +
                ", receiverCity='" + receiverCity + '\'' +
                ", receiverProvince='" + receiverProvince + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", freightAmount=" + freightAmount +
                ", remark='" + remark + '\'' +
                ", feightTemplateId=" + feightTemplateId +
                ", couponId='" + couponId + '\'' +
                ", couponAmount=" + couponAmount +
                ", quantity=" + quantity +
                ", productAttr='" + productAttr + '\'' +
                ", sp1='" + sp1 + '\'' +
                ", sp2='" + sp2 + '\'' +
                ", sourceType=" + sourceType +
                ", pic='" + pic + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", productId='" + productId + '\'' +
                ", productCategoryId='" + productCategoryId + '\'' +
                ", productSn='" + productSn + '\'' +
                ", promotionPrice=" + promotionPrice +
                ", productPrice=" + productPrice +
                '}';
    }
}
