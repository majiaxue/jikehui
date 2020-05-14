package com.example.bean;

import java.io.Serializable;
import java.util.List;

public class OrderDetailBean implements Serializable {


    /**
     * id : 690
     * orderSn : 1909051623000145
     * masterSn : 1909051623000145
     * userName : 13201835918
     * userId : 298242555449966592
     * tradeStatus : 1
     * autoTakeTime : 10
     * receiverPhone : 13201835918
     * receiverName : 晓玉
     * receiverRegion : 金水区
     * receiverCity : 郑州市
     * receiverProvince : 河南省
     * orderAddress : 金城国际3号楼
     * billContent : null
     * billHeader : null
     * billType : null
     * billReceiverEmail : null
     * billReceiverPhone : null
     * orderCreateTime : 2019-09-05 16:23:17
     * orderPayTime : null
     * orderUpdateTime : null
     * orderOutTime : 2019-09-05 16:53:17
     * receiveTime : null
     * orderOverTime : null
     * commentTime : null
     * orderPayAmount : null
     * payAmount : 126
     * integrationAmount : null
     * couponAmount : 0
     * discountAmount : null
     * promotionAmount : null
     * freightAmount : 27
     * totalAmount : 99
     * remark :
     * payWay : null
     * sourceType : 1
     * outerTradeNo : null
     * isRemove : 1
     * useIntegration : null
     * confirmStatus : 0
     * deliverySn : null
     * deliveryCompany : null
     * prepayId : null
     * supplyId : null
     * goodsId : null
     * goodsName : 凉鞋女夏平底ins潮女士百搭2019新款夏季网红超火时尚运动沙滩鞋
     * orderCategory : 213
     * sellerId : 1
     * sellerName : Allisjoy/我的时代1
     * status : 2
     * promotionInfo : null
     * integration : null
     * receiverPostCode : null
     * tenantId : 1
     * isLevelOrder : 0
     * sellerLogo : null
     * items : [{"id":757,"orderId":null,"orderSn":"1909051623000145","productId":56,"productPic":"http://47.99.93.123:8083/goods/75ee1d95b67642828b8b75709f40b1d1.jpg","productName":"凉鞋女夏平底ins潮女士百搭2019新款夏季网红超火时尚运动沙滩鞋","productBrand":"","productSn":"SNQON111855695","productPrice":99,"productQuantity":1,"productSkuId":1094,"productSkuCode":null,"productCategoryId":null,"sp1":"蓝色","sp2":"m","sp3":"","promotionName":null,"promotionAmount":null,"couponAmount":0,"integrationAmount":null,"realAmount":null,"giftIntegration":null,"giftGrowth":null,"productAttr":"颜色：蓝色、尺寸：m","couponId":0,"itemDeliveryTemplateId":null,"expireDate":null,"sellerName":null}]
     */

    private int id;
    private String orderSn;
    private String masterSn;
    private String userName;
    private String userId;
    private int tradeStatus;
    private int autoTakeTime;
    private String receiverPhone;
    private String receiverName;
    private String receiverRegion;
    private String receiverCity;
    private String receiverProvince;
    private String orderAddress;
    private Object billContent;
    private Object billHeader;
    private Object billType;
    private Object billReceiverEmail;
    private Object billReceiverPhone;
    private String orderCreateTime;
    private Object orderPayTime;
    private Object orderUpdateTime;
    private String orderOutTime;
    private String receiveTime;
    private Object orderOverTime;
    private Object commentTime;
    private Object orderPayAmount;
    private double payAmount;
    private Object integrationAmount;
    private double couponAmount;
    private double discountAmount;
    private double promotionAmount;
    private double freightAmount;
    private double totalAmount;
    private String remark;
    private Object payWay;
    private int sourceType;
    private Object outerTradeNo;
    private int isRemove;
    private Object useIntegration;
    private int confirmStatus;
    private Object deliverySn;
    private Object deliveryCompany;
    private Object prepayId;
    private Object supplyId;
    private Object goodsId;
    private String goodsName;
    private int orderCategory;
    private int sellerId;
    private String sellerName;
    private int status;
    private int backStatus;
    private Object promotionInfo;
    private Object integration;
    private Object receiverPostCode;
    private int tenantId;
    private int isLevelOrder;
    private Object sellerLogo;
    private List<ItemsBean> items;

    public int getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(int backStatus) {
        this.backStatus = backStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getMasterSn() {
        return masterSn;
    }

    public void setMasterSn(String masterSn) {
        this.masterSn = masterSn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(int tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public int getAutoTakeTime() {
        return autoTakeTime;
    }

    public void setAutoTakeTime(int autoTakeTime) {
        this.autoTakeTime = autoTakeTime;
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

    public Object getBillContent() {
        return billContent;
    }

    public void setBillContent(Object billContent) {
        this.billContent = billContent;
    }

    public Object getBillHeader() {
        return billHeader;
    }

    public void setBillHeader(Object billHeader) {
        this.billHeader = billHeader;
    }

    public Object getBillType() {
        return billType;
    }

    public void setBillType(Object billType) {
        this.billType = billType;
    }

    public Object getBillReceiverEmail() {
        return billReceiverEmail;
    }

    public void setBillReceiverEmail(Object billReceiverEmail) {
        this.billReceiverEmail = billReceiverEmail;
    }

    public Object getBillReceiverPhone() {
        return billReceiverPhone;
    }

    public void setBillReceiverPhone(Object billReceiverPhone) {
        this.billReceiverPhone = billReceiverPhone;
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Object getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Object orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public Object getOrderUpdateTime() {
        return orderUpdateTime;
    }

    public void setOrderUpdateTime(Object orderUpdateTime) {
        this.orderUpdateTime = orderUpdateTime;
    }

    public String getOrderOutTime() {
        return orderOutTime;
    }

    public void setOrderOutTime(String orderOutTime) {
        this.orderOutTime = orderOutTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Object getOrderOverTime() {
        return orderOverTime;
    }

    public void setOrderOverTime(Object orderOverTime) {
        this.orderOverTime = orderOverTime;
    }

    public Object getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Object commentTime) {
        this.commentTime = commentTime;
    }

    public Object getOrderPayAmount() {
        return orderPayAmount;
    }

    public void setOrderPayAmount(Object orderPayAmount) {
        this.orderPayAmount = orderPayAmount;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public Object getIntegrationAmount() {
        return integrationAmount;
    }

    public void setIntegrationAmount(Object integrationAmount) {
        this.integrationAmount = integrationAmount;
    }

    public double getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(double couponAmount) {
        this.couponAmount = couponAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(double promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public double getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(double freightAmount) {
        this.freightAmount = freightAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Object getPayWay() {
        return payWay;
    }

    public void setPayWay(Object payWay) {
        this.payWay = payWay;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public Object getOuterTradeNo() {
        return outerTradeNo;
    }

    public void setOuterTradeNo(Object outerTradeNo) {
        this.outerTradeNo = outerTradeNo;
    }

    public int getIsRemove() {
        return isRemove;
    }

    public void setIsRemove(int isRemove) {
        this.isRemove = isRemove;
    }

    public Object getUseIntegration() {
        return useIntegration;
    }

    public void setUseIntegration(Object useIntegration) {
        this.useIntegration = useIntegration;
    }

    public int getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(int confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Object getDeliverySn() {
        return deliverySn;
    }

    public void setDeliverySn(Object deliverySn) {
        this.deliverySn = deliverySn;
    }

    public Object getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(Object deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public Object getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(Object prepayId) {
        this.prepayId = prepayId;
    }

    public Object getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Object supplyId) {
        this.supplyId = supplyId;
    }

    public Object getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Object goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getOrderCategory() {
        return orderCategory;
    }

    public void setOrderCategory(int orderCategory) {
        this.orderCategory = orderCategory;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getPromotionInfo() {
        return promotionInfo;
    }

    public void setPromotionInfo(Object promotionInfo) {
        this.promotionInfo = promotionInfo;
    }

    public Object getIntegration() {
        return integration;
    }

    public void setIntegration(Object integration) {
        this.integration = integration;
    }

    public Object getReceiverPostCode() {
        return receiverPostCode;
    }

    public void setReceiverPostCode(Object receiverPostCode) {
        this.receiverPostCode = receiverPostCode;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public int getIsLevelOrder() {
        return isLevelOrder;
    }

    public void setIsLevelOrder(int isLevelOrder) {
        this.isLevelOrder = isLevelOrder;
    }

    public Object getSellerLogo() {
        return sellerLogo;
    }

    public void setSellerLogo(Object sellerLogo) {
        this.sellerLogo = sellerLogo;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean implements Serializable {
        /**
         * id : 757
         * orderId : null
         * orderSn : 1909051623000145
         * productId : 56
         * productPic : http://47.99.93.123:8083/goods/75ee1d95b67642828b8b75709f40b1d1.jpg
         * productName : 凉鞋女夏平底ins潮女士百搭2019新款夏季网红超火时尚运动沙滩鞋
         * productBrand :
         * productSn : SNQON111855695
         * productPrice : 99
         * productQuantity : 1
         * productSkuId : 1094
         * productSkuCode : null
         * productCategoryId : null
         * sp1 : 蓝色
         * sp2 : m
         * sp3 :
         * promotionName : null
         * promotionAmount : null
         * couponAmount : 0
         * integrationAmount : null
         * realAmount : null
         * giftIntegration : null
         * giftGrowth : null
         * productAttr : 颜色：蓝色、尺寸：m
         * couponId : 0
         * itemDeliveryTemplateId : null
         * expireDate : null
         * sellerName : null
         */

        private int id;
        private Object orderId;
        private String orderSn;
        private int productId;
        private String productPic;
        private String productName;
        private String productBrand;
        private String productSn;
        private double productPrice;
        private int productQuantity;
        private int productSkuId;
        private Object productSkuCode;
        private Object productCategoryId;
        private String sp1;
        private String sp2;
        private String sp3;
        private Object promotionName;
        private double promotionAmount;
        private double couponAmount;
        private double integrationAmount;
        private double realAmount;
        private Object giftIntegration;
        private Object giftGrowth;
        private String productAttr;
        private int couponId;
        private Object itemDeliveryTemplateId;
        private Object expireDate;
        private Object sellerName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getOrderId() {
            return orderId;
        }

        public void setOrderId(Object orderId) {
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

        public void setProductPrice(double productPrice) {
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

        public Object getProductSkuCode() {
            return productSkuCode;
        }

        public void setProductSkuCode(Object productSkuCode) {
            this.productSkuCode = productSkuCode;
        }

        public Object getProductCategoryId() {
            return productCategoryId;
        }

        public void setProductCategoryId(Object productCategoryId) {
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

        public Object getPromotionName() {
            return promotionName;
        }

        public void setPromotionName(Object promotionName) {
            this.promotionName = promotionName;
        }

        public double getPromotionAmount() {
            return promotionAmount;
        }

        public void setPromotionAmount(double promotionAmount) {
            this.promotionAmount = promotionAmount;
        }

        public double getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(double couponAmount) {
            this.couponAmount = couponAmount;
        }

        public double getIntegrationAmount() {
            return integrationAmount;
        }

        public void setIntegrationAmount(double integrationAmount) {
            this.integrationAmount = integrationAmount;
        }

        public double getRealAmount() {
            return realAmount;
        }

        public void setRealAmount(double realAmount) {
            this.realAmount = realAmount;
        }

        public Object getGiftIntegration() {
            return giftIntegration;
        }

        public void setGiftIntegration(Object giftIntegration) {
            this.giftIntegration = giftIntegration;
        }

        public Object getGiftGrowth() {
            return giftGrowth;
        }

        public void setGiftGrowth(Object giftGrowth) {
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

        public Object getSellerName() {
            return sellerName;
        }

        public void setSellerName(Object sellerName) {
            this.sellerName = sellerName;
        }

        @Override
        public String toString() {
            return "ItemsBean{" +
                    "id=" + id +
                    ", orderId=" + orderId +
                    ", orderSn='" + orderSn + '\'' +
                    ", productId=" + productId +
                    ", productPic='" + productPic + '\'' +
                    ", productName='" + productName + '\'' +
                    ", productBrand='" + productBrand + '\'' +
                    ", productSn='" + productSn + '\'' +
                    ", productPrice=" + productPrice +
                    ", productQuantity=" + productQuantity +
                    ", productSkuId=" + productSkuId +
                    ", productSkuCode=" + productSkuCode +
                    ", productCategoryId=" + productCategoryId +
                    ", sp1='" + sp1 + '\'' +
                    ", sp2='" + sp2 + '\'' +
                    ", sp3='" + sp3 + '\'' +
                    ", promotionName=" + promotionName +
                    ", promotionAmount=" + promotionAmount +
                    ", couponAmount=" + couponAmount +
                    ", integrationAmount=" + integrationAmount +
                    ", realAmount=" + realAmount +
                    ", giftIntegration=" + giftIntegration +
                    ", giftGrowth=" + giftGrowth +
                    ", productAttr='" + productAttr + '\'' +
                    ", couponId=" + couponId +
                    ", itemDeliveryTemplateId=" + itemDeliveryTemplateId +
                    ", expireDate=" + expireDate +
                    ", sellerName=" + sellerName +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OrderDetailBean{" +
                "id=" + id +
                ", orderSn='" + orderSn + '\'' +
                ", masterSn='" + masterSn + '\'' +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", tradeStatus=" + tradeStatus +
                ", autoTakeTime=" + autoTakeTime +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverRegion='" + receiverRegion + '\'' +
                ", receiverCity='" + receiverCity + '\'' +
                ", receiverProvince='" + receiverProvince + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", billContent=" + billContent +
                ", billHeader=" + billHeader +
                ", billType=" + billType +
                ", billReceiverEmail=" + billReceiverEmail +
                ", billReceiverPhone=" + billReceiverPhone +
                ", orderCreateTime='" + orderCreateTime + '\'' +
                ", orderPayTime=" + orderPayTime +
                ", orderUpdateTime=" + orderUpdateTime +
                ", orderOutTime='" + orderOutTime + '\'' +
                ", receiveTime=" + receiveTime +
                ", orderOverTime=" + orderOverTime +
                ", commentTime=" + commentTime +
                ", orderPayAmount=" + orderPayAmount +
                ", payAmount=" + payAmount +
                ", integrationAmount=" + integrationAmount +
                ", couponAmount=" + couponAmount +
                ", discountAmount=" + discountAmount +
                ", promotionAmount=" + promotionAmount +
                ", freightAmount=" + freightAmount +
                ", totalAmount=" + totalAmount +
                ", remark='" + remark + '\'' +
                ", payWay=" + payWay +
                ", sourceType=" + sourceType +
                ", outerTradeNo=" + outerTradeNo +
                ", isRemove=" + isRemove +
                ", useIntegration=" + useIntegration +
                ", confirmStatus=" + confirmStatus +
                ", deliverySn=" + deliverySn +
                ", deliveryCompany=" + deliveryCompany +
                ", prepayId=" + prepayId +
                ", supplyId=" + supplyId +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", orderCategory=" + orderCategory +
                ", sellerId=" + sellerId +
                ", sellerName='" + sellerName + '\'' +
                ", status=" + status +
                ", promotionInfo=" + promotionInfo +
                ", integration=" + integration +
                ", receiverPostCode=" + receiverPostCode +
                ", tenantId=" + tenantId +
                ", isLevelOrder=" + isLevelOrder +
                ", sellerLogo=" + sellerLogo +
                ", items=" + items +
                '}';
    }
}
