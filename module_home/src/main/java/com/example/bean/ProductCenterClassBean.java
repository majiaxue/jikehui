package com.example.bean;

public class ProductCenterClassBean {

    /**
     * id : 1
     * name : 淘客
     * createTime : 2019-11-21 14:11:46
     * updateTime : null
     * sort : 1
     */

    private int id;
    private String name;
    private String createTime;
    private Object updateTime;
    private int sort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
