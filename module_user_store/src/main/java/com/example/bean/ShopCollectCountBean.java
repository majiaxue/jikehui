package com.example.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/16
 * Describe:
 */
public class ShopCollectCountBean {
    private List<RecordsBean> records;

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * id : 11
         * sellerCode : 100001
         * sellerShopName : Allisjoy/舞悦时节
         * sellerLogo :
         * sellerCategory : 1003
         * sellerName : 孙浩
         * sellerPhone : 15133339666
         * sellerIdPositiveCardUrl : seller-50092d094d5346c186b771f907a3d432.jpg
         * sellerIdBackCardUrl : seller-77dd7b92391d427984a9da0284e1dca6.jpg
         * sellerBusinessLicenseUrl : seller-882cd7fd5bf7465fba0cd3f5985905ce.jpg
         * sellerFoodSafetyPermitUrl : seller-f6e3c13312004275a4734de3e02dbef7.jpg
         * sellerIscheck : 1
         * sellerAddredd : 北京市 北京 朝阳区 朝阳区 soho大厦 1001室
         * sellerStatus : 1
         * createTime : 2019-05-29 10:20:25
         * updateTime : 2019-05-29 10:20:55
         * sellerIntroduce : <p>这是店铺介绍</p>
         */

        private int id;
        private String sellerCode;
        private String sellerShopName;
        private String sellerLogo;
        private String sellerCategory;
        private String sellerName;
        private String sellerPhone;
        private String sellerIdPositiveCardUrl;
        private String sellerIdBackCardUrl;
        private String sellerBusinessLicenseUrl;
        private String sellerFoodSafetyPermitUrl;
        private int sellerIscheck;
        private String sellerAddredd;
        private int sellerStatus;
        private String createTime;
        private String updateTime;
        private String sellerIntroduce;
        private int favoriteId;

        public int getFavoriteId() {
            return favoriteId;
        }

        public void setFavoriteId(int favoriteId) {
            this.favoriteId = favoriteId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSellerCode() {
            return sellerCode;
        }

        public void setSellerCode(String sellerCode) {
            this.sellerCode = sellerCode;
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

        public int getSellerIscheck() {
            return sellerIscheck;
        }

        public void setSellerIscheck(int sellerIscheck) {
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
    }
}
