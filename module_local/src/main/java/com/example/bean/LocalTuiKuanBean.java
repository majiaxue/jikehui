package com.example.bean;

import java.io.Serializable;
import java.util.List;

public class LocalTuiKuanBean implements Serializable {

    /**
     * records : [{"id":33,"orderId":390,"companyAddressId":null,"productId":null,"orderSn":"157406191377359298","createTime":"2019-11-18 15:27:17","memberUsername":"13201835918","returnAmount":0,"returnName":"晓玉","returnPhone":"13201835918","status":0,"handleTime":null,"productPic":null,"productName":null,"productBrand":null,"productAttr":null,"productCount":null,"productPrice":null,"productRealPrice":0,"reason":null,"description":null,"proofPics":"","handleNote":null,"handleMan":null,"receiveMan":null,"receiveTime":null,"receiveNote":null,"sellerId":18,"userCode":"298242555449966592","pics":null,"localOrderItemList":[{"id":521,"orderSn":"157406191377359298","goodsId":21,"goodsNum":1,"goodsPrice":10,"goodsSpec":"大份","goodsName":"披萨-榴莲味，培根，菠萝味","goodsPic":"http://47.99.93.123:8083/parameter/b3b48e8481a24c488a6b1b5286703589.jpg"}],"seller":{"id":18,"userCode":"349497299002458112","sellerShopName":"脆哨店铺","sellerLogo":"http://47.99.93.123:8083/parameter/977f63022cb44b0f87e8546fa516f0e8.jpg","sellerCategory":"食品","sellerName":"崔绍川","sellerPhone":"13253300882","sellerIdPositiveCardUrl":"seller-7744959af1454ae2ab38e9e59fec39be..jpg","sellerIdBackCardUrl":"seller-a2695f0ba84e4415bcb8b6229baf6920..jpg","sellerBusinessLicenseUrl":"seller-1de27a6ecb94484db4c66e81efdb972f..jpg","sellerFoodSafetyPermitUrl":"","sellerIscheck":1,"sellerAddredd":"河南省 郑州 迭部县 啦啦啦111","sellerStatus":1,"createTime":"2019-10-31 10:43:03","updateTime":"2019-11-18 10:26:23","sellerIntroduce":"","favoriteId":null,"sellerType":1,"sellerLon":"113.66378927001952","sellerLat":"34.7599163363232","sellerBusinessHours":"09:00-23:00","sellerPics":"http://47.99.93.123:8083/parameter/977f63022cb44b0f87e8546fa516f0e8.jpg","amount":0,"minPoint":null,"label":null,"proportion":null,"pigxxId":18,"hotRecommendStatus":1,"localRecommendStatus":1}},{"id":34,"orderId":397,"companyAddressId":null,"productId":null,"orderSn":"157406293291459298","createTime":"2019-11-18 15:43:56","memberUsername":"13201835918","returnAmount":0,"returnName":"晓玉","returnPhone":"13201835918","status":0,"handleTime":null,"productPic":null,"productName":null,"productBrand":null,"productAttr":null,"productCount":null,"productPrice":null,"productRealPrice":0,"reason":null,"description":null,"proofPics":"","handleNote":null,"handleMan":null,"receiveMan":null,"receiveTime":null,"receiveNote":null,"sellerId":18,"userCode":"298242555449966592","pics":null,"localOrderItemList":[{"id":528,"orderSn":"157406293291459298","goodsId":23,"goodsNum":1,"goodsPrice":10,"goodsSpec":"大份","goodsName":"刺身拼盘-榴莲味，培根，菠萝味","goodsPic":"http://47.99.93.123:8083/parameter/977f63022cb44b0f87e8546fa516f0e8.jpg"},{"id":529,"orderSn":"157406293291459298","goodsId":22,"goodsNum":1,"goodsPrice":8,"goodsSpec":"小份","goodsName":"意大利面-榴莲味，培根，菠萝味","goodsPic":"http://47.99.93.123:8083/parameter/76fc85b4a8b7412fa926849cdea8e24c.jpg"}],"seller":{"id":18,"userCode":"349497299002458112","sellerShopName":"脆哨店铺","sellerLogo":"http://47.99.93.123:8083/parameter/977f63022cb44b0f87e8546fa516f0e8.jpg","sellerCategory":"食品","sellerName":"崔绍川","sellerPhone":"13253300882","sellerIdPositiveCardUrl":"seller-7744959af1454ae2ab38e9e59fec39be..jpg","sellerIdBackCardUrl":"seller-a2695f0ba84e4415bcb8b6229baf6920..jpg","sellerBusinessLicenseUrl":"seller-1de27a6ecb94484db4c66e81efdb972f..jpg","sellerFoodSafetyPermitUrl":"","sellerIscheck":1,"sellerAddredd":"河南省 郑州 迭部县 啦啦啦111","sellerStatus":1,"createTime":"2019-10-31 10:43:03","updateTime":"2019-11-18 10:26:23","sellerIntroduce":"","favoriteId":null,"sellerType":1,"sellerLon":"113.66378927001952","sellerLat":"34.7599163363232","sellerBusinessHours":"09:00-23:00","sellerPics":"http://47.99.93.123:8083/parameter/977f63022cb44b0f87e8546fa516f0e8.jpg","amount":0,"minPoint":null,"label":null,"proportion":null,"pigxxId":18,"hotRecommendStatus":1,"localRecommendStatus":1}}]
     * total : 2
     * size : 10
     * current : 0
     * searchCount : true
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private boolean searchCount;
    private int pages;
    private List<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "LocalTuiKuanBean{" +
                "total=" + total +
                ", size=" + size +
                ", current=" + current +
                ", searchCount=" + searchCount +
                ", pages=" + pages +
                ", records=" + records +
                '}';
    }

    public static class RecordsBean implements Serializable {
        /**
         * id : 33
         * orderId : 390
         * companyAddressId : null
         * productId : null
         * orderSn : 157406191377359298
         * createTime : 2019-11-18 15:27:17
         * memberUsername : 13201835918
         * returnAmount : 0
         * returnName : 晓玉
         * returnPhone : 13201835918
         * status : 0
         * handleTime : null
         * productPic : null
         * productName : null
         * productBrand : null
         * productAttr : null
         * productCount : null
         * productPrice : null
         * productRealPrice : 0
         * reason : null
         * description : null
         * proofPics :
         * handleNote : null
         * handleMan : null
         * receiveMan : null
         * receiveTime : null
         * receiveNote : null
         * sellerId : 18
         * userCode : 298242555449966592
         * pics : null
         * localOrderItemList : [{"id":521,"orderSn":"157406191377359298","goodsId":21,"goodsNum":1,"goodsPrice":10,"goodsSpec":"大份","goodsName":"披萨-榴莲味，培根，菠萝味","goodsPic":"http://47.99.93.123:8083/parameter/b3b48e8481a24c488a6b1b5286703589.jpg"}]
         * seller : {"id":18,"userCode":"349497299002458112","sellerShopName":"脆哨店铺","sellerLogo":"http://47.99.93.123:8083/parameter/977f63022cb44b0f87e8546fa516f0e8.jpg","sellerCategory":"食品","sellerName":"崔绍川","sellerPhone":"13253300882","sellerIdPositiveCardUrl":"seller-7744959af1454ae2ab38e9e59fec39be..jpg","sellerIdBackCardUrl":"seller-a2695f0ba84e4415bcb8b6229baf6920..jpg","sellerBusinessLicenseUrl":"seller-1de27a6ecb94484db4c66e81efdb972f..jpg","sellerFoodSafetyPermitUrl":"","sellerIscheck":1,"sellerAddredd":"河南省 郑州 迭部县 啦啦啦111","sellerStatus":1,"createTime":"2019-10-31 10:43:03","updateTime":"2019-11-18 10:26:23","sellerIntroduce":"","favoriteId":null,"sellerType":1,"sellerLon":"113.66378927001952","sellerLat":"34.7599163363232","sellerBusinessHours":"09:00-23:00","sellerPics":"http://47.99.93.123:8083/parameter/977f63022cb44b0f87e8546fa516f0e8.jpg","amount":0,"minPoint":null,"label":null,"proportion":null,"pigxxId":18,"hotRecommendStatus":1,"localRecommendStatus":1}
         */

        private String id;
        private String orderId;
        private String companyAddressId;
        private String productId;
        private String orderSn;
        private String createTime;
        private String memberUsername;
        private double returnAmount;
        private String returnName;
        private String returnPhone;
        private int status;
        private String handleTime;
        private String reason;
        private String description;
        private String proofPics;
        private String handleNote;
        private String handleMan;
        private String receiveMan;
        private String receiveTime;
        private String receiveNote;
        private String sellerId;
        private String userCode;
        private String pics;
        private SellerBean seller;
        private String fullReductionAmount; //退款金额
        private String payWay;  //支付方式
        private String redPackedMoney;  //红包金额
        private String deliverType;     //配送方式
        private String userAddress;
        private List<LocalOrderBean.LocalOrderItemListBean> localOrderItemList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCompanyAddressId() {
            return companyAddressId;
        }

        public void setCompanyAddressId(String companyAddressId) {
            this.companyAddressId = companyAddressId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getMemberUsername() {
            return memberUsername;
        }

        public void setMemberUsername(String memberUsername) {
            this.memberUsername = memberUsername;
        }

        public double getReturnAmount() {
            return returnAmount;
        }

        public void setReturnAmount(double returnAmount) {
            this.returnAmount = returnAmount;
        }

        public String getReturnName() {
            return returnName;
        }

        public void setReturnName(String returnName) {
            this.returnName = returnName;
        }

        public String getReturnPhone() {
            return returnPhone;
        }

        public void setReturnPhone(String returnPhone) {
            this.returnPhone = returnPhone;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getHandleTime() {
            return handleTime;
        }

        public void setHandleTime(String handleTime) {
            this.handleTime = handleTime;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getProofPics() {
            return proofPics;
        }

        public void setProofPics(String proofPics) {
            this.proofPics = proofPics;
        }

        public String getHandleNote() {
            return handleNote;
        }

        public void setHandleNote(String handleNote) {
            this.handleNote = handleNote;
        }

        public String getHandleMan() {
            return handleMan;
        }

        public void setHandleMan(String handleMan) {
            this.handleMan = handleMan;
        }

        public String getReceiveMan() {
            return receiveMan;
        }

        public void setReceiveMan(String receiveMan) {
            this.receiveMan = receiveMan;
        }

        public String getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(String receiveTime) {
            this.receiveTime = receiveTime;
        }

        public String getReceiveNote() {
            return receiveNote;
        }

        public void setReceiveNote(String receiveNote) {
            this.receiveNote = receiveNote;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public SellerBean getSeller() {
            return seller;
        }

        public void setSeller(SellerBean seller) {
            this.seller = seller;
        }

        public String getFullReductionAmount() {
            return fullReductionAmount;
        }

        public void setFullReductionAmount(String fullReductionAmount) {
            this.fullReductionAmount = fullReductionAmount;
        }

        public String getPayWay() {
            return payWay;
        }

        public void setPayWay(String payWay) {
            this.payWay = payWay;
        }

        public String getRedPackedMoney() {
            return redPackedMoney;
        }

        public void setRedPackedMoney(String redPackedMoney) {
            this.redPackedMoney = redPackedMoney;
        }

        public String getDeliverType() {
            return deliverType;
        }

        public void setDeliverType(String deliverType) {
            this.deliverType = deliverType;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public List<LocalOrderBean.LocalOrderItemListBean> getLocalOrderItemList() {
            return localOrderItemList;
        }

        public void setLocalOrderItemList(List<LocalOrderBean.LocalOrderItemListBean> localOrderItemList) {
            this.localOrderItemList = localOrderItemList;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "id='" + id + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", companyAddressId='" + companyAddressId + '\'' +
                    ", productId='" + productId + '\'' +
                    ", orderSn='" + orderSn + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", memberUsername='" + memberUsername + '\'' +
                    ", returnAmount=" + returnAmount +
                    ", returnName='" + returnName + '\'' +
                    ", returnPhone='" + returnPhone + '\'' +
                    ", status=" + status +
                    ", handleTime='" + handleTime + '\'' +
                    ", reason='" + reason + '\'' +
                    ", description='" + description + '\'' +
                    ", proofPics='" + proofPics + '\'' +
                    ", handleNote='" + handleNote + '\'' +
                    ", handleMan='" + handleMan + '\'' +
                    ", receiveMan='" + receiveMan + '\'' +
                    ", receiveTime='" + receiveTime + '\'' +
                    ", receiveNote='" + receiveNote + '\'' +
                    ", sellerId='" + sellerId + '\'' +
                    ", userCode='" + userCode + '\'' +
                    ", pics='" + pics + '\'' +
                    ", seller=" + seller +
                    ", fullReductionAmount='" + fullReductionAmount + '\'' +
                    ", payWay='" + payWay + '\'' +
                    ", redPackedMoney='" + redPackedMoney + '\'' +
                    ", deliverType='" + deliverType + '\'' +
                    ", userAddress='" + userAddress + '\'' +
                    ", localOrderItemList=" + localOrderItemList +
                    '}';
        }

        public static class SellerBean implements Serializable {
            /**
             * id : 18
             * userCode : 349497299002458112
             * sellerShopName : 脆哨店铺
             * sellerLogo : http://47.99.93.123:8083/parameter/977f63022cb44b0f87e8546fa516f0e8.jpg
             * sellerCategory : 食品
             * sellerName : 崔绍川
             * sellerPhone : 13253300882
             * sellerIdPositiveCardUrl : seller-7744959af1454ae2ab38e9e59fec39be..jpg
             * sellerIdBackCardUrl : seller-a2695f0ba84e4415bcb8b6229baf6920..jpg
             * sellerBusinessLicenseUrl : seller-1de27a6ecb94484db4c66e81efdb972f..jpg
             * sellerFoodSafetyPermitUrl :
             * sellerIscheck : 1
             * sellerAddredd : 河南省 郑州 迭部县 啦啦啦111
             * sellerStatus : 1
             * createTime : 2019-10-31 10:43:03
             * updateTime : 2019-11-18 10:26:23
             * sellerIntroduce :
             * favoriteId : null
             * sellerType : 1
             * sellerLon : 113.66378927001952
             * sellerLat : 34.7599163363232
             * sellerBusinessHours : 09:00-23:00
             * sellerPics : http://47.99.93.123:8083/parameter/977f63022cb44b0f87e8546fa516f0e8.jpg
             * amount : 0
             * minPoint : null
             * label : null
             * proportion : null
             * pigxxId : 18
             * hotRecommendStatus : 1
             * localRecommendStatus : 1
             */

            private String id;
            private String userCode;
            private String sellerShopName;
            private String sellerLogo;
            private String sellerCategory;
            private String sellerName;
            private String sellerPhone;
            private String sellerIdPositiveCardUrl;
            private String sellerIdBackCardUrl;
            private String sellerBusinessLicenseUrl;
            private String sellerFoodSafetyPermitUrl;
            private String sellerIscheck;
            private String sellerAddredd;
            private int sellerStatus;
            private String createTime;
            private String updateTime;
            private String sellerIntroduce;
            private String favoriteId;
            private int sellerType;
            private String sellerLon;
            private String sellerLat;
            private String sellerBusinessHours;
            private String sellerPics;
            private double amount;
            private String minPoint;
            private String label;
            private String proportion;
            private String pigxxId;
            private int hotRecommendStatus;
            private int localRecommendStatus;
            private String fullReductionAmount;
            private String takeGoodsCode;

            public String getFullReductionAmount() {
                return fullReductionAmount;
            }

            public void setFullReductionAmount(String fullReductionAmount) {
                this.fullReductionAmount = fullReductionAmount;
            }

            public String getTakeGoodsCode() {
                return takeGoodsCode;
            }

            public void setTakeGoodsCode(String takeGoodsCode) {
                this.takeGoodsCode = takeGoodsCode;
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

            public String getSellerShopName() {
                return sellerShopName;
            }

            public void setSellerShopName(String sellerShopName) {
                this.sellerShopName = sellerShopName;
            }

            public String getSellerLogo() {
                return sellerLogo;
            }

            public void setSellerLogo(String sellerLogo) {
                this.sellerLogo = sellerLogo;
            }

            public String getSellerCategory() {
                return sellerCategory;
            }

            public void setSellerCategory(String sellerCategory) {
                this.sellerCategory = sellerCategory;
            }

            public String getSellerName() {
                return sellerName;
            }

            public void setSellerName(String sellerName) {
                this.sellerName = sellerName;
            }

            public String getSellerPhone() {
                return sellerPhone;
            }

            public void setSellerPhone(String sellerPhone) {
                this.sellerPhone = sellerPhone;
            }

            public String getSellerIdPositiveCardUrl() {
                return sellerIdPositiveCardUrl;
            }

            public void setSellerIdPositiveCardUrl(String sellerIdPositiveCardUrl) {
                this.sellerIdPositiveCardUrl = sellerIdPositiveCardUrl;
            }

            public String getSellerIdBackCardUrl() {
                return sellerIdBackCardUrl;
            }

            public void setSellerIdBackCardUrl(String sellerIdBackCardUrl) {
                this.sellerIdBackCardUrl = sellerIdBackCardUrl;
            }

            public String getSellerBusinessLicenseUrl() {
                return sellerBusinessLicenseUrl;
            }

            public void setSellerBusinessLicenseUrl(String sellerBusinessLicenseUrl) {
                this.sellerBusinessLicenseUrl = sellerBusinessLicenseUrl;
            }

            public String getSellerFoodSafetyPermitUrl() {
                return sellerFoodSafetyPermitUrl;
            }

            public void setSellerFoodSafetyPermitUrl(String sellerFoodSafetyPermitUrl) {
                this.sellerFoodSafetyPermitUrl = sellerFoodSafetyPermitUrl;
            }

            public String getSellerIscheck() {
                return sellerIscheck;
            }

            public void setSellerIscheck(String sellerIscheck) {
                this.sellerIscheck = sellerIscheck;
            }

            public String getSellerAddredd() {
                return sellerAddredd;
            }

            public void setSellerAddredd(String sellerAddredd) {
                this.sellerAddredd = sellerAddredd;
            }

            public int getSellerStatus() {
                return sellerStatus;
            }

            public void setSellerStatus(int sellerStatus) {
                this.sellerStatus = sellerStatus;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getSellerIntroduce() {
                return sellerIntroduce;
            }

            public void setSellerIntroduce(String sellerIntroduce) {
                this.sellerIntroduce = sellerIntroduce;
            }

            public String getFavoriteId() {
                return favoriteId;
            }

            public void setFavoriteId(String favoriteId) {
                this.favoriteId = favoriteId;
            }

            public int getSellerType() {
                return sellerType;
            }

            public void setSellerType(int sellerType) {
                this.sellerType = sellerType;
            }

            public String getSellerLon() {
                return sellerLon;
            }

            public void setSellerLon(String sellerLon) {
                this.sellerLon = sellerLon;
            }

            public String getSellerLat() {
                return sellerLat;
            }

            public void setSellerLat(String sellerLat) {
                this.sellerLat = sellerLat;
            }

            public String getSellerBusinessHours() {
                return sellerBusinessHours;
            }

            public void setSellerBusinessHours(String sellerBusinessHours) {
                this.sellerBusinessHours = sellerBusinessHours;
            }

            public String getSellerPics() {
                return sellerPics;
            }

            public void setSellerPics(String sellerPics) {
                this.sellerPics = sellerPics;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getMinPoint() {
                return minPoint;
            }

            public void setMinPoint(String minPoint) {
                this.minPoint = minPoint;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getProportion() {
                return proportion;
            }

            public void setProportion(String proportion) {
                this.proportion = proportion;
            }

            public String getPigxxId() {
                return pigxxId;
            }

            public void setPigxxId(String pigxxId) {
                this.pigxxId = pigxxId;
            }

            public int getHotRecommendStatus() {
                return hotRecommendStatus;
            }

            public void setHotRecommendStatus(int hotRecommendStatus) {
                this.hotRecommendStatus = hotRecommendStatus;
            }

            public int getLocalRecommendStatus() {
                return localRecommendStatus;
            }

            public void setLocalRecommendStatus(int localRecommendStatus) {
                this.localRecommendStatus = localRecommendStatus;
            }

            @Override
            public String toString() {
                return "SellerBean{" +
                        "id='" + id + '\'' +
                        ", userCode='" + userCode + '\'' +
                        ", sellerShopName='" + sellerShopName + '\'' +
                        ", sellerLogo='" + sellerLogo + '\'' +
                        ", sellerCategory='" + sellerCategory + '\'' +
                        ", sellerName='" + sellerName + '\'' +
                        ", sellerPhone='" + sellerPhone + '\'' +
                        ", sellerIdPositiveCardUrl='" + sellerIdPositiveCardUrl + '\'' +
                        ", sellerIdBackCardUrl='" + sellerIdBackCardUrl + '\'' +
                        ", sellerBusinessLicenseUrl='" + sellerBusinessLicenseUrl + '\'' +
                        ", sellerFoodSafetyPermitUrl='" + sellerFoodSafetyPermitUrl + '\'' +
                        ", sellerIscheck='" + sellerIscheck + '\'' +
                        ", sellerAddredd='" + sellerAddredd + '\'' +
                        ", sellerStatus=" + sellerStatus +
                        ", createTime='" + createTime + '\'' +
                        ", updateTime='" + updateTime + '\'' +
                        ", sellerIntroduce='" + sellerIntroduce + '\'' +
                        ", favoriteId='" + favoriteId + '\'' +
                        ", sellerType=" + sellerType +
                        ", sellerLon='" + sellerLon + '\'' +
                        ", sellerLat='" + sellerLat + '\'' +
                        ", sellerBusinessHours='" + sellerBusinessHours + '\'' +
                        ", sellerPics='" + sellerPics + '\'' +
                        ", amount=" + amount +
                        ", minPoint='" + minPoint + '\'' +
                        ", label='" + label + '\'' +
                        ", proportion='" + proportion + '\'' +
                        ", pigxxId='" + pigxxId + '\'' +
                        ", hotRecommendStatus=" + hotRecommendStatus +
                        ", localRecommendStatus=" + localRecommendStatus +
                        ", fullReductionAmount='" + fullReductionAmount + '\'' +
                        ", takeGoodsCode='" + takeGoodsCode + '\'' +
                        '}';
            }
        }
    }
}
