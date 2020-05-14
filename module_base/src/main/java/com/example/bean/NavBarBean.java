package com.example.bean;

import java.io.Serializable;
import java.util.List;

public class NavBarBean implements Serializable {
    /**
     * records : [{"id":77,"parentId":0,"name":"顶级分类","level":0,"productCount":null,"productUnit":null,"navStatus":null,"showStatus":null,"sort":null,"icon":null,"keywords":null,"description":null,"path":"77","productAttributeIdList":null}]
     * total : 1
     * size : 10
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
         * id : 77
         * parentId : 0
         * name : 顶级分类
         * level : 0
         * productCount : null
         * productUnit : null
         * navStatus : null
         * showStatus : null
         * sort : null
         * icon : null
         * keywords : null
         * description : null
         * path : 77
         * productAttributeIdList : null
         */

        private int id;
        private int parentId;
        private String name;
        private int level;
        private Object productCount;
        private Object productUnit;
        private Object navStatus;
        private Object showStatus;
        private Object sort;
        private String icon;
        private Object keywords;
        private Object description;
        private String path;
        private Object productAttributeIdList;
        private String sellName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public Object getProductCount() {
            return productCount;
        }

        public void setProductCount(Object productCount) {
            this.productCount = productCount;
        }

        public Object getProductUnit() {
            return productUnit;
        }

        public void setProductUnit(Object productUnit) {
            this.productUnit = productUnit;
        }

        public Object getNavStatus() {
            return navStatus;
        }

        public void setNavStatus(Object navStatus) {
            this.navStatus = navStatus;
        }

        public Object getShowStatus() {
            return showStatus;
        }

        public void setShowStatus(Object showStatus) {
            this.showStatus = showStatus;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Object getKeywords() {
            return keywords;
        }

        public void setKeywords(Object keywords) {
            this.keywords = keywords;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Object getProductAttributeIdList() {
            return productAttributeIdList;
        }

        public String getSellName() {
            return sellName;
        }

        public void setSellName(String sellName) {
            this.sellName = sellName;
        }

        public void setProductAttributeIdList(Object productAttributeIdList) {
            this.productAttributeIdList = productAttributeIdList;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "id=" + id +
                    ", parentId=" + parentId +
                    ", name='" + name + '\'' +
                    ", level=" + level +
                    ", productCount=" + productCount +
                    ", productUnit=" + productUnit +
                    ", navStatus=" + navStatus +
                    ", showStatus=" + showStatus +
                    ", sort=" + sort +
                    ", icon=" + icon +
                    ", keywords=" + keywords +
                    ", description=" + description +
                    ", path='" + path + '\'' +
                    ", productAttributeIdList=" + productAttributeIdList +
                    ", sellName='" + sellName + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NavBarBean{" +
                "total=" + total +
                ", size=" + size +
                ", current=" + current +
                ", searchCount=" + searchCount +
                ", pages=" + pages +
                ", records=" + records +
                '}';
    }
}
