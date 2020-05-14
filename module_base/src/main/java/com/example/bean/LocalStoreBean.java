package com.example.bean;

import java.util.List;

public class LocalStoreBean {

    /**
     * id : 18
     * shopCategoryName : 进店必点
     * sellerId : 95
     * list : [{"id":6,"createDate":"2019-08-28 14:15:32","updateDate":"2019-09-02 16:19:17","remarks":"这是备注","name":"山沟的名字很长","introduce":"这是简介","monthSales":0,"parameter":"[{\"key\":\"口味\",\"value\":\"12\"},{\"key\":\"饮品\",\"value\":\"13\"}]","specification":"[{\"key\":\"大份\",\"value\":\"10\"},{\"key\":\"小份\",\"value\":\"10\"}]","recommend":0,"price":0,"discountPrice":0,"pics":"http://192.168.0.17:9000/parameter/71093e43ac484951a4b62db9b6d13d67.png","status":0,"tenantId":1,"sellerId":95}]
     */

    private String id;
    private String shopCategoryName;
    private String sellerId;
    private List<ListBean> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "LocalStoreBean{" +
                "id='" + id + '\'' +
                ", shopCategoryName='" + shopCategoryName + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", list=" + list +
                '}';
    }

    public static class ListBean {
        /**
         * id : 6
         * createDate : 2019-08-28 14:15:32
         * updateDate : 2019-09-02 16:19:17
         * remarks : 这是备注
         * name : 山沟的名字很长
         * introduce : 这是简介
         * monthSales : 0
         * parameter : [{"key":"口味","value":"12"},{"key":"饮品","value":"13"}]
         * specification : [{"key":"大份","value":"10"},{"key":"小份","value":"10"}]
         * recommend : 0
         * price : 0
         * discountPrice : 0
         * pics : http://192.168.0.17:9000/parameter/71093e43ac484951a4b62db9b6d13d67.png
         * status : 0
         * tenantId : 1
         * sellerId : 95
         */

        private String id;
        private String createDate;
        private String updateDate;
        private String remarks;
        private String name;
        private String introduce;
        private String monthSales;
        private String parameter;
        private String specification;
        /**
         * 是否强烈推荐  0：不是  1：是
         */
        private String recommend;
        private String price;
        private String discountPrice;
        private String pics;
        private String status;
        private String tenantId;
        private String sellerId;

        private boolean isTitle;
        private String tag;
        private int count;
        private String selectName;
        private String selectSpec;

        public ListBean() {
        }

        public ListBean(String name, boolean isTitle, String tag) {
            this.name = name;
            this.isTitle = isTitle;
            this.tag = tag;
        }

        public String getSelectName() {
            return selectName;
        }

        public void setSelectName(String selectName) {
            this.selectName = selectName;
        }

        public String getSelectSpec() {
            return selectSpec;
        }

        public void setSelectSpec(String selectSpec) {
            this.selectSpec = selectSpec;
        }

        public boolean isTitle() {
            return isTitle;
        }

        public void setTitle(boolean title) {
            isTitle = title;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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
            return "ListBean{" +
                    "id='" + id + '\'' +
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
                    ", isTitle=" + isTitle +
                    ", tag='" + tag + '\'' +
                    ", count=" + count +
                    ", selectName='" + selectName + '\'' +
                    ", selectSpec='" + selectSpec + '\'' +
                    '}';
        }

        public static class MyParameter {
            private String key;
            private String value;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            @Override
            public String toString() {
                return "Parameter{" +
                        "key='" + key + '\'' +
                        ", value='" + value + '\'' +
                        '}';
            }
        }
    }
}
