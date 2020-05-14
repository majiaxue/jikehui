package com.example.bean;

public class LocalCommidityBean {
    /**
     *
     */
    private String id;
    /**
     * 创建时间
     */
    private String createDate;
    /**
     * 更新时间
     */
    private String updateDate;
    /**
     * 备注信息
     */
    private String remarks;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 简介
     */
    private String introduce;
    /**
     * 月销量
     */
    private String monthSales;
    /**
     * 商品参数
     */
    private String parameter;
    /**
     * 规格
     */
    private String specification;
    /**
     * 强烈推荐（0：否 1：是）
     */
    private String recommend;
    /**
     * 价格
     */
    private double price;
    /**
     * 折扣价
     */
    private String discountPrice;
    /**
     * 图片
     */
    private String pics;
    /**
     * 0->上架 1->下架 2->售馨
     */
    private String status;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 商家ID
     */
    private String sellerId;

    private boolean isTitle;
    private String titleName;
    private String tag;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "LocalCommidityBean{" +
                "id=" + id +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", remarks='" + remarks + '\'' +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", monthSales=" + monthSales +
                ", parameter='" + parameter + '\'' +
                ", specification='" + specification + '\'' +
                ", recommend=" + recommend +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", pics='" + pics + '\'' +
                ", status=" + status +
                ", tenantId=" + tenantId +
                ", sellerId=" + sellerId +
                ", isTitle=" + isTitle +
                ", titleName='" + titleName + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
