package com.example.bean;

import java.io.Serializable;
import java.util.List;

public class LocalOrderBean implements Serializable {

    /**
     * id : 1
     * userCode : 1
     * localSellerId : 1
     * redPackedId : 1
     * redPackedMoney : 5
     * orderSn : 12213
     * totalMoney : 40
     * realMoney : 35
     * deliverType : 0
     * status : 6
     * payWay : 1
     * userName : 1
     * userAddress : 11111
     * userPhone : 1
     * createTime : 2019-09-17 15:46:56
     * payTime : 2019-09-03 15:46:58
     * receiveTime : 2019-09-03 15:47:01
     * deliverTime : 2019-09-03 15:47:07
     * finishTime : 2019-09-03 15:47:10
     * refundTime : 2019-09-03 15:47:12
     * closeTime : null
     * commentTime : 2019-09-03 15:47:15
     * note : 1
     * tenantId : null
     * takeGoodsCode : null
     * isDelete : 0
     * outTradeNo : null
     * localOrderItemList : [{"id":1,"orderSn":"12213","goodsId":6,"goodsNum":2,"goodsPrice":10,"goodsSpec":"大份","goodsName":"山沟的名字很长","goodsPic":"http://192.168.0.17:9000/parameter/71093e43ac484951a4b62db9b6d13d67.png"},{"id":2,"orderSn":"12213","goodsId":6,"goodsNum":2,"goodsPrice":10,"goodsSpec":"大份","goodsName":"山沟的名字很长","goodsPic":"http://192.168.0.17:9000/parameter/71093e43ac484951a4b62db9b6d13d67.png"}]
     * totalAmount : null
     * timeOut : null
     */

    /**
     * 唯一标识
     */
    private String id;
    /**
     * 用户ID
     */
    private String userCode;
    /**
     * 商家ID
     */
    private String localSellerId;
    /**
     * 红包ID
     */
    private String redPackedId;
    /**
     * 红包金额
     */
    private String redPackedMoney;
    /**
     * 订单ID
     */
    private String orderSn;
    /**
     * 订单总金额
     */
    private double totalMoney;
    /**
     * 实付金额
     */
    private double realMoney;
    /**
     * 收货方式（0：自提 1：配送）
     */
    private String deliverType;
    /**
     * 订单状态（0：未付款 1：已付款 2：已接单 3：已配送 4：已完成 5：已退款 6：已关闭 7：已评价）
     */
    private String status;
    /**
     * 退款中订单状态（0->待处理；1->退货中；2->已完成；3->已拒绝）
     */
    private String returnStatus;
    /**
     * 支付方式（0：微信 1：支付宝）
     */
    private String payWay;
    /**
     * 收货人姓名
     */
    private String userName;
    /**
     * 收货人地址
     */
    private String userAddress;
    /**
     * 收货人手机号
     */
    private String userPhone;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 付款时间
     */
    private String payTime;
    /**
     * 接单时间
     */
    private String receiveTime;
    /**
     * 配送时间
     */
    private String deliverTime;
    /**
     * 完成时间
     */
    private String finishTime;
    /**
     * 退款时间
     */
    private String refundTime;
    /**
     * 关闭时间
     */
    private String closeTime;
    /**
     * 评论时间
     */
    private String commentTime;
    /**
     * 备注
     */
    private String note;
    /**
     * 租户ID
     */
    private Integer tenantId;

    /**
     * 取货码
     */
    private String takeGoodsCode;
    /**
     * 是否删除（0：否 1：是）
     */
    private Integer isDelete;
    /**
     * 支付宝支付订单号
     */
    private String outTradeNo;

    private String totalAmount;

    /**
     * 订单超时时间
     */
    private Long timeOut;

    /**
     * 商家信息
     */
    private SellerInfo sellerInfo;

    private String sellerName;

    private String sellerManJian;

    private List<LocalOrderItemListBean> localOrderItemList;

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getSellerManJian() {
        return sellerManJian;
    }

    public void setSellerManJian(String sellerManJian) {
        this.sellerManJian = sellerManJian;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getLocalSellerId() {
        return localSellerId;
    }

    public void setLocalSellerId(String localSellerId) {
        this.localSellerId = localSellerId;
    }

    public String getRedPackedId() {
        return redPackedId;
    }

    public void setRedPackedId(String redPackedId) {
        this.redPackedId = redPackedId;
    }

    public String getRedPackedMoney() {
        return redPackedMoney;
    }

    public void setRedPackedMoney(String redPackedMoney) {
        this.redPackedMoney = redPackedMoney;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(double realMoney) {
        this.realMoney = realMoney;
    }

    public String getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(String deliverType) {
        this.deliverType = deliverType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTakeGoodsCode() {
        return takeGoodsCode;
    }

    public void setTakeGoodsCode(String takeGoodsCode) {
        this.takeGoodsCode = takeGoodsCode;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Long timeOut) {
        this.timeOut = timeOut;
    }

    public SellerInfo getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(SellerInfo sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    public List<LocalOrderItemListBean> getLocalOrderItemList() {
        return localOrderItemList;
    }

    public void setLocalOrderItemList(List<LocalOrderItemListBean> localOrderItemList) {
        this.localOrderItemList = localOrderItemList;
    }

    @Override
    public String toString() {
        return "LocalOrderBean{" +
                "id='" + id + '\'' +
                ", userCode='" + userCode + '\'' +
                ", localSellerId='" + localSellerId + '\'' +
                ", redPackedId='" + redPackedId + '\'' +
                ", redPackedMoney='" + redPackedMoney + '\'' +
                ", orderSn='" + orderSn + '\'' +
                ", totalMoney=" + totalMoney +
                ", realMoney=" + realMoney +
                ", deliverType='" + deliverType + '\'' +
                ", status='" + status + '\'' +
                ", returnStatus='" + returnStatus + '\'' +
                ", payWay='" + payWay + '\'' +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", createTime='" + createTime + '\'' +
                ", payTime='" + payTime + '\'' +
                ", receiveTime='" + receiveTime + '\'' +
                ", deliverTime='" + deliverTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", refundTime='" + refundTime + '\'' +
                ", closeTime='" + closeTime + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", note='" + note + '\'' +
                ", tenantId=" + tenantId +
                ", takeGoodsCode='" + takeGoodsCode + '\'' +
                ", isDelete=" + isDelete +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", timeOut=" + timeOut +
                ", sellerInfo=" + sellerInfo +
                ", sellerName='" + sellerName + '\'' +
                ", sellerManJian='" + sellerManJian + '\'' +
                ", localOrderItemList=" + localOrderItemList +
                '}';
    }

    public static class LocalOrderItemListBean implements Serializable {
        /**
         * id : 1
         * orderSn : 12213
         * goodsId : 6
         * goodsNum : 2
         * goodsPrice : 10
         * goodsSpec : 大份
         * goodsName : 山沟的名字很长
         * goodsPic : http://192.168.0.17:9000/parameter/71093e43ac484951a4b62db9b6d13d67.png
         */

        private String id;
        private String orderSn;
        private String goodsId;
        private int goodsNum;
        private String goodsPrice;
        private String goodsSpec;
        private String goodsName;
        private String goodsPic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getGoodsSpec() {
            return goodsSpec;
        }

        public void setGoodsSpec(String goodsSpec) {
            this.goodsSpec = goodsSpec;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsPic() {
            return goodsPic;
        }

        public void setGoodsPic(String goodsPic) {
            this.goodsPic = goodsPic;
        }

        @Override
        public String toString() {
            return "LocalOrderItemListBean{" +
                    "id='" + id + '\'' +
                    ", orderSn='" + orderSn + '\'' +
                    ", goodsId='" + goodsId + '\'' +
                    ", goodsNum='" + goodsNum + '\'' +
                    ", goodsPrice=" + goodsPrice +
                    ", goodsSpec='" + goodsSpec + '\'' +
                    ", goodsName='" + goodsName + '\'' +
                    ", goodsPic='" + goodsPic + '\'' +
                    '}';
        }
    }

    public static class SellerInfo implements Serializable {
        private String amount;
        private String seller_logo;
        private String min_point;
        private String shop_name;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getSeller_logo() {
            return seller_logo;
        }

        public void setSeller_logo(String seller_logo) {
            this.seller_logo = seller_logo;
        }

        public String getMin_point() {
            return min_point;
        }

        public void setMin_point(String min_point) {
            this.min_point = min_point;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        @Override
        public String toString() {
            return "SellerInfo{" +
                    "amount='" + amount + '\'' +
                    ", seller_logo='" + seller_logo + '\'' +
                    ", min_point='" + min_point + '\'' +
                    ", shop_name='" + shop_name + '\'' +
                    '}';
        }
    }
}
