package com.example.bean;

public class PopupXSGoodsClassBean {

    /**
     * id : 78
     * tenantId : 1
     * parentId : 77
     * name : 女装
     * level : 1
     * productCount : null
     * productUnit :
     * navStatus : 1
     * showStatus : 1
     * sort : 1
     * icon : http://47.99.93.123:8083/category/3d22de6400854d5eb850cc2c6212e6ce.png
     * keywords :
     * description :
     * path : 77-78
     * productAttributeIdList : null
     */

    private int id;
    private int tenantId;
    private int parentId;
    private String name;
    private int level;
    private Object productCount;
    private String productUnit;
    private int navStatus;
    private int showStatus;
    private int sort;
    private String icon;
    private String keywords;
    private String description;
    private String path;
    private Object productAttributeIdList;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
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

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public int getNavStatus() {
        return navStatus;
    }

    public void setNavStatus(int navStatus) {
        this.navStatus = navStatus;
    }

    public int getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(int showStatus) {
        this.showStatus = showStatus;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    public void setProductAttributeIdList(Object productAttributeIdList) {
        this.productAttributeIdList = productAttributeIdList;
    }
}
