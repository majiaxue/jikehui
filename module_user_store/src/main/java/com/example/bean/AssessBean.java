package com.example.bean;

import java.util.List;

public class AssessBean {

    /**
     * records : [{"id":2,"memberNickName":"评论","memberIcon":"2","star":5,"content":"123","pics":"1","productId":38,"productName":"1","productAttribute":"1","memberIp":"1","showStatus":1,"collectCount":1,"readCount":1,"replayCount":1,"createTime":"2019-06-04 11:17:13","sellerId":1,"sellerDescribe":1,"sellerLogistics":1,"sellerServer":1}]
     * total : 1
     * size : 2
     * current : 1
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

    public static class RecordsBean {
        /**
         * id : 2
         * memberNickName : 评论
         * memberIcon : 2
         * star : 5
         * content : 123
         * pics : 1
         * productId : 38
         * productName : 1
         * productAttribute : 1
         * memberIp : 1
         * showStatus : 1
         * collectCount : 1
         * readCount : 1
         * replayCount : 1
         * createTime : 2019-06-04 11:17:13
         * sellerId : 1
         * sellerDescribe : 1
         * sellerLogistics : 1
         * sellerServer : 1
         */

        private String id;
        private String info;
        private String pics;
        private String orderSn;
        private String orderItemId;
        private String productId;
        private int pjpf;
        private int fwpf;
        private int sppf;
        private int wlpf;
        private String isAnonymous;
        private String icon;
        private String nickname;
        private String ceatedTime;
        private String attr;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public String getOrderItemId() {
            return orderItemId;
        }

        public void setOrderItemId(String orderItemId) {
            this.orderItemId = orderItemId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public int getPjpf() {
            return pjpf;
        }

        public void setPjpf(int pjpf) {
            this.pjpf = pjpf;
        }

        public int getFwpf() {
            return fwpf;
        }

        public void setFwpf(int fwpf) {
            this.fwpf = fwpf;
        }

        public int getSppf() {
            return sppf;
        }

        public void setSppf(int sppf) {
            this.sppf = sppf;
        }

        public int getWlpf() {
            return wlpf;
        }

        public void setWlpf(int wlpf) {
            this.wlpf = wlpf;
        }

        public String getIsAnonymous() {
            return isAnonymous;
        }

        public void setIsAnonymous(String isAnonymous) {
            this.isAnonymous = isAnonymous;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCeatedTime() {
            return ceatedTime;
        }

        public void setCeatedTime(String ceatedTime) {
            this.ceatedTime = ceatedTime;
        }

        public String getAttr() {
            return attr;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "id='" + id + '\'' +
                    ", info='" + info + '\'' +
                    ", pics='" + pics + '\'' +
                    ", orderSn='" + orderSn + '\'' +
                    ", orderItemId='" + orderItemId + '\'' +
                    ", productId='" + productId + '\'' +
                    ", pjpf=" + pjpf +
                    ", fwpf='" + fwpf + '\'' +
                    ", sppf='" + sppf + '\'' +
                    ", wlpf='" + wlpf + '\'' +
                    ", isAnonymous=" + isAnonymous +
                    ", icon=" + icon +
                    ", nickname=" + nickname +
                    ", ceatedTime=" + ceatedTime +
                    ", attr='" + attr + '\'' +
                    '}';
        }
    }
}
