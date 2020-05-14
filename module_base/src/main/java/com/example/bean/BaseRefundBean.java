package com.example.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cuihaohao on 2019/6/18
 * Describe:
 */
public class BaseRefundBean implements Serializable {

    private double totalAmount;
    private List<OrderItemsBean> orderItems;

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItemsBean> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemsBean> orderItems) {
        this.orderItems = orderItems;
    }

    public static class OrderItemsBean implements Serializable {
        /**
         * id : 1
         * orderId : 37
         * orderSn : 12344123123
         * productId : 1
         * productPic : http://192.168.1.22:9000/seller/a34a9e8cd52f4b1f9f779f107cd511a3.jpg
         * productName : 小米8
         * productBrand : 小米
         * productSn : 1123123
         * productPrice : 998
         * productQuantity : 1
         * productSkuId : 1
         * productSkuCode : 1
         * productCategoryId : 1
         * sp1 : 1
         * sp2 : 1
         * sp3 : 1
         * promotionName : 1
         * promotionAmount : 1
         * couponAmount : 1
         * integrationAmount : 1
         * realAmount : 222
         * giftIntegration : 1
         * giftGrowth : 1
         * productAttr : 颜色：白色，尺码：X
         * couponId : 1
         * itemDeliveryTemplateId : null
         * expireDate : null
         */

        private int id;
        private int orderId;
        private String orderSn;
        private int productId;
        private String productPic;
        private String productName;
        private String productBrand;
        private String productSn;
        private double productPrice;
        private int productQuantity;
        private int productSkuId;
        private String productSkuCode;
        private int productCategoryId;
        private String sp1;
        private String sp2;
        private String sp3;
        private String promotionName;
        private int promotionAmount;
        private int couponAmount;
        private int integrationAmount;
        private int realAmount;
        private int giftIntegration;
        private int giftGrowth;
        private String productAttr;
        private int couponId;
        private Object itemDeliveryTemplateId;
        private Object expireDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getProductPic() {
            return productPic;
        }

        public void setProductPic(String productPic) {
            this.productPic = productPic;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductBrand() {
            return productBrand;
        }

        public void setProductBrand(String productBrand) {
            this.productBrand = productBrand;
        }

        public String getProductSn() {
            return productSn;
        }

        public void setProductSn(String productSn) {
            this.productSn = productSn;
        }

        public double getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(int productPrice) {
            this.productPrice = productPrice;
        }

        public int getProductQuantity() {
            return productQuantity;
        }

        public void setProductQuantity(int productQuantity) {
            this.productQuantity = productQuantity;
        }

        public int getProductSkuId() {
            return productSkuId;
        }

        public void setProductSkuId(int productSkuId) {
            this.productSkuId = productSkuId;
        }

        public String getProductSkuCode() {
            return productSkuCode;
        }

        public void setProductSkuCode(String productSkuCode) {
            this.productSkuCode = productSkuCode;
        }

        public int getProductCategoryId() {
            return productCategoryId;
        }

        public void setProductCategoryId(int productCategoryId) {
            this.productCategoryId = productCategoryId;
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

        public String getSp3() {
            return sp3;
        }

        public void setSp3(String sp3) {
            this.sp3 = sp3;
        }

        public String getPromotionName() {
            return promotionName;
        }

        public void setPromotionName(String promotionName) {
            this.promotionName = promotionName;
        }

        public int getPromotionAmount() {
            return promotionAmount;
        }

        public void setPromotionAmount(int promotionAmount) {
            this.promotionAmount = promotionAmount;
        }

        public int getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(int couponAmount) {
            this.couponAmount = couponAmount;
        }

        public int getIntegrationAmount() {
            return integrationAmount;
        }

        public void setIntegrationAmount(int integrationAmount) {
            this.integrationAmount = integrationAmount;
        }

        public int getRealAmount() {
            return realAmount;
        }

        public void setRealAmount(int realAmount) {
            this.realAmount = realAmount;
        }

        public int getGiftIntegration() {
            return giftIntegration;
        }

        public void setGiftIntegration(int giftIntegration) {
            this.giftIntegration = giftIntegration;
        }

        public int getGiftGrowth() {
            return giftGrowth;
        }

        public void setGiftGrowth(int giftGrowth) {
            this.giftGrowth = giftGrowth;
        }

        public String getProductAttr() {
            return productAttr;
        }

        public void setProductAttr(String productAttr) {
            this.productAttr = productAttr;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public Object getItemDeliveryTemplateId() {
            return itemDeliveryTemplateId;
        }

        public void setItemDeliveryTemplateId(Object itemDeliveryTemplateId) {
            this.itemDeliveryTemplateId = itemDeliveryTemplateId;
        }

        public Object getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(Object expireDate) {
            this.expireDate = expireDate;
        }
    }

}
