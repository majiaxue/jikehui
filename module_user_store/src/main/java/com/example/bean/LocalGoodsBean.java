package com.example.bean;

import java.util.List;

public class LocalGoodsBean {

    /**
     * records : [{"id":65,"name":"商品名","pic":"http://192.168.1.17:9000/goods/3e0fa6a703c74a809113b3a4bac61a84.png","deleteStatus":null,"publishStatus":1,"sort":1,"sale":0,"price":1,"description":"1","standard":null,"createTime":"2019-07-08 15:31:59","startDate":"星期一","endDate":"星期一","isReservation":1,"localSellerId":94,"tenantId":1}]
     * total : 1
     * size : 1000
     * current : 1
     * searchCount : true
     * pages : 1
     */

    private String total;
    private String size;
    private String current;
    private boolean searchCount;
    private String pages;
    private List<RecordsBean> records;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
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
         * id : 65
         * name : 商品名
         * pic : http://192.168.1.17:9000/goods/3e0fa6a703c74a809113b3a4bac61a84.png
         * deleteStatus : null
         * publishStatus : 1
         * sort : 1
         * sale : 0
         * price : 1
         * description : 1
         * standard : null
         * createTime : 2019-07-08 15:31:59
         * startDate : 星期一
         * endDate : 星期一
         * isReservation : 1
         * localSellerId : 94
         * tenantId : 1
         */

        private String id;
        private String name;
        private String pic;
        private String deleteStatus;
        private String publishStatus;
        private String sort;
        private String sale;
        private String price;
        private String description;
        private String standard;
        private String createTime;
        private String startDate;
        private String endDate;
        private String isReservation;
        private int localSellerId;
        private int tenantId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getDeleteStatus() {
            return deleteStatus;
        }

        public void setDeleteStatus(String deleteStatus) {
            this.deleteStatus = deleteStatus;
        }

        public String getPublishStatus() {
            return publishStatus;
        }

        public void setPublishStatus(String publishStatus) {
            this.publishStatus = publishStatus;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getSale() {
            return sale;
        }

        public void setSale(String sale) {
            this.sale = sale;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStandard() {
            return standard;
        }

        public void setStandard(String standard) {
            this.standard = standard;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getIsReservation() {
            return isReservation;
        }

        public void setIsReservation(String isReservation) {
            this.isReservation = isReservation;
        }

        public int getLocalSellerId() {
            return localSellerId;
        }

        public void setLocalSellerId(int localSellerId) {
            this.localSellerId = localSellerId;
        }

        public int getTenantId() {
            return tenantId;
        }

        public void setTenantId(int tenantId) {
            this.tenantId = tenantId;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", pic='" + pic + '\'' +
                    ", deleteStatus='" + deleteStatus + '\'' +
                    ", publishStatus='" + publishStatus + '\'' +
                    ", sort='" + sort + '\'' +
                    ", sale='" + sale + '\'' +
                    ", price='" + price + '\'' +
                    ", description='" + description + '\'' +
                    ", standard='" + standard + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", startDate='" + startDate + '\'' +
                    ", endDate='" + endDate + '\'' +
                    ", isReservation='" + isReservation + '\'' +
                    ", localSellerId=" + localSellerId +
                    ", tenantId=" + tenantId +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LocalGoodsBean{" +
                "total='" + total + '\'' +
                ", size='" + size + '\'' +
                ", current='" + current + '\'' +
                ", searchCount=" + searchCount +
                ", pages='" + pages + '\'' +
                ", records=" + records +
                '}';
    }
}
