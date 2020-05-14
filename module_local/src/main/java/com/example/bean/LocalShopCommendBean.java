package com.example.bean;

import java.util.List;

public class LocalShopCommendBean {

    /**
     * id : 18
     * userCode : 349497299002458112
     * sellerShopName : 脆哨店铺
     * sellerLogo : seller-d134b978a57f4919a2bca8c2effc77af..jpg
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
     * updateTime : 2019-11-07 09:48:29
     * sellerIntroduce :
     * favoriteId : null
     * sellerType : 1
     * sellerLon : 113.66378927001952
     * sellerLat : 34.7599163363232
     * sellerBusinessHours : null
     * sellerPics : null
     * amount : null
     * minPoint : null
     * label : null
     * proportion : null
     * pigxxId : null
     * hotRecommendStatus : 1
     * localRecommendStatus : 1
     * goodsList : [{"serialVersionUID":1,"id":null,"createDate":"2019-11-06 15:02:31","updateDate":null,"remarks":null,"name":"披萨","introduce":"这个披萨是真好吃","monthSales":0,"parameter":"[{\"key\":\"口味\",\"value\":\"榴莲味，培根，菠萝味\"}]","specification":null,"recommend":1,"price":98,"discountPrice":9.8,"pics":"http://47.99.93.123:8083/parameter/b3b48e8481a24c488a6b1b5286703589.jpg","status":0,"tenantId":1,"sellerId":18},{"serialVersionUID":1,"id":null,"createDate":"2019-11-06 15:03:40","updateDate":null,"remarks":null,"name":"意大利面","introduce":"这个意面真好吃啊","monthSales":0,"parameter":null,"specification":null,"recommend":1,"price":48,"discountPrice":4.8,"pics":"http://47.99.93.123:8083/parameter/76fc85b4a8b7412fa926849cdea8e24c.jpg","status":0,"tenantId":1,"sellerId":18},{"serialVersionUID":1,"id":null,"createDate":"2019-11-06 15:06:15","updateDate":null,"remarks":null,"name":"刺身拼盘","introduce":"三文鱼金枪鱼北极贝刺身拼盘是真好吃啊","monthSales":0,"parameter":null,"specification":null,"recommend":1,"price":198,"discountPrice":19.8,"pics":"http://47.99.93.123:8083/parameter/977f63022cb44b0f87e8546fa516f0e8.jpg","status":0,"tenantId":1,"sellerId":18}]
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
    private String sellerStatus;
    private String createTime;
    private String updateTime;
    private String sellerIntroduce;
    private String favoriteId;
    private String sellerType;
    private String sellerLon;
    private String sellerLat;
    private String sellerBusinessHours;
    private String sellerPics;
    private String amount;
    private String minPoint;
    private String label;
    private String proportion;
    private String pigxxId;
    private int hotRecommendStatus;
    private int localRecommendStatus;
    private String fullReductionAmount;
    private List<GoodsListBean> goodsList;

    public String getFullReductionAmount() {
        return fullReductionAmount;
    }

    public void setFullReductionAmount(String fullReductionAmount) {
        this.fullReductionAmount = fullReductionAmount;
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

    public String getSellerStatus() {
        return sellerStatus;
    }

    public void setSellerStatus(String sellerStatus) {
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

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
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

    public List<GoodsListBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsListBean> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public String toString() {
        return "LocalShopCommendBean{" +
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
                ", sellerStatus='" + sellerStatus + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", sellerIntroduce='" + sellerIntroduce + '\'' +
                ", favoriteId='" + favoriteId + '\'' +
                ", sellerType='" + sellerType + '\'' +
                ", sellerLon='" + sellerLon + '\'' +
                ", sellerLat='" + sellerLat + '\'' +
                ", sellerBusinessHours='" + sellerBusinessHours + '\'' +
                ", sellerPics='" + sellerPics + '\'' +
                ", amount='" + amount + '\'' +
                ", minPoint='" + minPoint + '\'' +
                ", label='" + label + '\'' +
                ", proportion='" + proportion + '\'' +
                ", pigxxId='" + pigxxId + '\'' +
                ", hotRecommendStatus=" + hotRecommendStatus +
                ", localRecommendStatus=" + localRecommendStatus +
                ", fullReductionAmount='" + fullReductionAmount + '\'' +
                ", goodsList=" + goodsList +
                '}';
    }

    public static class GoodsListBean {
        /**
         * serialVersionUID : 1
         * id : null
         * createDate : 2019-11-06 15:02:31
         * updateDate : null
         * remarks : null
         * name : 披萨
         * introduce : 这个披萨是真好吃
         * monthSales : 0
         * parameter : [{"key":"口味","value":"榴莲味，培根，菠萝味"}]
         * specification : null
         * recommend : 1
         * price : 98
         * discountPrice : 9.8
         * pics : http://47.99.93.123:8083/parameter/b3b48e8481a24c488a6b1b5286703589.jpg
         * status : 0
         * tenantId : 1
         * sellerId : 18
         */

        private String serialVersionUID;
        private String id;
        private String createDate;
        private String updateDate;
        private String remarks;
        private String name;
        private String introduce;
        private String monthSales;
        private String parameter;
        private String specification;
        private String recommend;
        private String price;
        private String discountPrice;
        private String pics;
        private String status;
        private String tenantId;
        private String sellerId;

        public String getSerialVersionUID() {
            return serialVersionUID;
        }

        public void setSerialVersionUID(String serialVersionUID) {
            this.serialVersionUID = serialVersionUID;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getMonthSales() {
            return monthSales;
        }

        public void setMonthSales(String monthSales) {
            this.monthSales = monthSales;
        }

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(String discountPrice) {
            this.discountPrice = discountPrice;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        @Override
        public String toString() {
            return "GoodsListBean{" +
                    "serialVersionUID='" + serialVersionUID + '\'' +
                    ", id='" + id + '\'' +
                    ", createDate='" + createDate + '\'' +
                    ", updateDate='" + updateDate + '\'' +
                    ", remarks='" + remarks + '\'' +
                    ", name='" + name + '\'' +
                    ", introduce='" + introduce + '\'' +
                    ", monthSales='" + monthSales + '\'' +
                    ", parameter='" + parameter + '\'' +
                    ", specification='" + specification + '\'' +
                    ", recommend='" + recommend + '\'' +
                    ", price='" + price + '\'' +
                    ", discountPrice='" + discountPrice + '\'' +
                    ", pics='" + pics + '\'' +
                    ", status='" + status + '\'' +
                    ", tenantId='" + tenantId + '\'' +
                    ", sellerId='" + sellerId + '\'' +
                    '}';
        }
    }
}
