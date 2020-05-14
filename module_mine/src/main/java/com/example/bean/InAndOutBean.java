package com.example.bean;

import java.util.List;

public class InAndOutBean {

    /**
     * records : [{"id":2,"userCode":"298242555449966592","price":1,"type":1,"note":"提现","createTime":"2019-06-11 10:52:32"},{"id":4,"userCode":"298242555449966592","price":11,"type":1,"note":"购物","createTime":"2019-06-11 10:52:32"},{"id":6,"userCode":"298242555449966592","price":111,"type":1,"note":"提现","createTime":"2019-06-11 10:52:32"},{"id":8,"userCode":"298242555449966592","price":1111,"type":1,"note":"购物","createTime":"2019-06-11 10:52:32"},{"id":10,"userCode":"298242555449966592","price":11111,"type":1,"note":"提现","createTime":"2019-06-11 10:52:32"}]
     * total : 5
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
         * id : 2
         * userCode : 298242555449966592
         * price : 1
         * type : 1
         * note : 提现
         * createTime : 2019-06-11 10:52:32
         */

        private int id;
        private String userCode;
        private double price;
        private int type;
        private String note;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
