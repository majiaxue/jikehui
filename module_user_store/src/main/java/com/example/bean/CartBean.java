package com.example.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/24
 * Describe:
 */
public class CartBean {


    private List<RecordsBean> records;

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "CartBean{" +
                "records=" + records +
                '}';
    }

    public static class RecordsBean {
        /**
         * sellerId : 1
         * sellerName : 小米旗舰店
         * items : [{"id":1,"productId":1,"productSkuId":238,"userId":1,"quantity":1,"price":999,"sp1":"土豪金","sp2":"16g","sp3":"","productPic":"http://192.168.1.5:9000/seller/a34a9e8cd52f4b1f9f779f107cd511a3.jpg","productName":"小米6","productSubTitle":"小米6-双核-土豪金-16g","memberNickname":"1","createDate":"2019-05-31 14:29:50","modifyDate":"2019-05-31 14:29:52","productCategoryId":1,"productBrand":"小米","productSn":"1","productAttr":"1","checked":1,"sellerId":1,"sellerName":"小米旗舰店"},{"id":2,"productId":1,"productSkuId":238,"userId":1,"quantity":1,"price":888,"sp1":"土豪金","sp2":"16g","sp3":"","productPic":"http://192.168.1.5:9000/seller/a34a9e8cd52f4b1f9f779f107cd511a3.jpg","productName":"小米8","productSubTitle":"小米8-八核-土豪金-128g","memberNickname":"1","createDate":"2019-06-04 10:09:34","modifyDate":"2019-06-04 10:09:36","productCategoryId":1,"productBrand":"小米","productSn":"1","productAttr":"1","checked":1,"sellerId":1,"sellerName":"小米旗舰店"}]
         */

        private int sellerId;
        private String sellerName;
        private boolean isCheck;
        private double totalFeight; //运费
        private double totalPrice;  //小计金额
        private double disAmount;   //优惠金额
        private String couponId;    //优惠券id
        private double minAmount;   //优惠券最低要求金额
        private List<ItemsBean> items;

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public double getMinAmount() {
            return minAmount;
        }

        public void setMinAmount(double minAmount) {
            this.minAmount = minAmount;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public double getDisAmount() {
            return disAmount;
        }

        public void setDisAmount(double disAmount) {
            this.disAmount = disAmount;
        }

        public double getTotalFeight() {
            return totalFeight;
        }

        public void setTotalFeight(double totalFeight) {
            this.totalFeight = totalFeight;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
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

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "sellerId=" + sellerId +
                    ", sellerName='" + sellerName + '\'' +
                    ", isCheck=" + isCheck +
                    ", totalFeight=" + totalFeight +
                    ", totalPrice=" + totalPrice +
                    ", disAmount=" + disAmount +
                    ", couponId='" + couponId + '\'' +
                    ", minAmount=" + minAmount +
                    ", items=" + items +
                    '}';
        }

        public static class ItemsBean {
            /**
             * id : 1
             * productId : 1
             * productSkuId : 238
             * userId : 1
             * quantity : 1
             * price : 999
             * sp1 : 土豪金
             * sp2 : 16g
             * sp3 :
             * productPic : http://192.168.1.5:9000/seller/a34a9e8cd52f4b1f9f779f107cd511a3.jpg
             * productName : 小米6
             * productSubTitle : 小米6-双核-土豪金-16g
             * memberNickname : 1
             * createDate : 2019-05-31 14:29:50
             * modifyDate : 2019-05-31 14:29:52
             * productCategoryId : 1
             * productBrand : 小米
             * productSn : 1
             * productAttr : 1
             * checked : 1
             * sellerId : 1
             * sellerName : 小米旗舰店
             */

            private int id;
            private int productId;
            private int productSkuId;
            private String userId;
            private int quantity;
            private double price;
            private String sp1;
            private String sp2;
            private String sp3;
            private String productPic;
            private String productName;
            private String productSubTitle;
            private String memberNickname;
            private String createDate;
            private String modifyDate;
            private int productCategoryId;
            private String productBrand;
            private String productSn;
            private String productAttr;
            private int checked;
            private int sellerId;
            private String sellerName;
            private double feight;

            public double getFeight() {
                return feight;
            }

            public void setFeight(double feight) {
                this.feight = feight;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public int getProductSkuId() {
                return productSkuId;
            }

            public void setProductSkuId(int productSkuId) {
                this.productSkuId = productSkuId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
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

            public String getProductSubTitle() {
                return productSubTitle;
            }

            public void setProductSubTitle(String productSubTitle) {
                this.productSubTitle = productSubTitle;
            }

            public String getMemberNickname() {
                return memberNickname;
            }

            public void setMemberNickname(String memberNickname) {
                this.memberNickname = memberNickname;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
            }

            public int getProductCategoryId() {
                return productCategoryId;
            }

            public void setProductCategoryId(int productCategoryId) {
                this.productCategoryId = productCategoryId;
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

            public String getProductAttr() {
                return productAttr;
            }

            public void setProductAttr(String productAttr) {
                this.productAttr = productAttr;
            }

            public int getChecked() {
                return checked;
            }

            public void setChecked(int checked) {
                this.checked = checked;
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

            @Override
            public String toString() {
                return "ItemsBean{" +
                        "id=" + id +
                        ", productId=" + productId +
                        ", productSkuId=" + productSkuId +
                        ", userId=" + userId +
                        ", quantity=" + quantity +
                        ", price=" + price +
                        ", sp1='" + sp1 + '\'' +
                        ", sp2='" + sp2 + '\'' +
                        ", sp3='" + sp3 + '\'' +
                        ", productPic='" + productPic + '\'' +
                        ", productName='" + productName + '\'' +
                        ", productSubTitle='" + productSubTitle + '\'' +
                        ", memberNickname='" + memberNickname + '\'' +
                        ", createDate='" + createDate + '\'' +
                        ", modifyDate='" + modifyDate + '\'' +
                        ", productCategoryId=" + productCategoryId +
                        ", productBrand='" + productBrand + '\'' +
                        ", productSn='" + productSn + '\'' +
                        ", productAttr='" + productAttr + '\'' +
                        ", checked=" + checked +
                        ", sellerId=" + sellerId +
                        ", sellerName='" + sellerName + '\'' +
                        ", feight=" + feight +
                        '}';
            }
        }
    }
}
