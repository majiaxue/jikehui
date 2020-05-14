package com.example.bean;

import java.util.List;

public class GroupFansBean {

    /**
     * records : [{"id":44,"userCode":"293701080951291904","username":"12345","password":"$2a$10$QN6G5OF6cFVPs9/1bPj5PO/zlPNuyka/r.V1KDCkUgjz2KDJBHUaG","nickname":"12345","phone":"12345","status":null,"createTime":"2019-05-29 21:10:04","icon":null,"gender":null,"birthday":null,"city":null,"job":null,"personalizedSignature":null,"sourceType":null,"integration":null,"growth":null,"luckeyCount":null,"historyIntegration":null,"weixinOpenid":null,"inviteCode":"Y4oxfTPy","blance":null,"sellerId":null,"level":null,"checkCode":null,"oldPassword":null,"newPassword":null,"oldPhone":null,"token":null}]
     * total : 8
     * size : 1
     * current : 3
     * searchCount : true
     * pages : 8
     */

    private int total;
    private int size;
    private int current;
    private boolean searchCount;
    private int pages;
    private List<UserInfoBean> records;

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

    public List<UserInfoBean> getRecords() {
        return records;
    }

    public void setRecords(List<UserInfoBean> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "GroupFansBean{" +
                "total=" + total +
                ", size=" + size +
                ", current=" + current +
                ", searchCount=" + searchCount +
                ", pages=" + pages +
                ", records=" + records +
                '}';
    }
}
