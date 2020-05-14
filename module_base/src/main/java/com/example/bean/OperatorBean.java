package com.example.bean;

public class OperatorBean {

    /**
     * id : 5
     * name : 初级运营商
     * defaultStatus : 0
     * isEnable : 1
     * upType : 2
     * levelType : 1
     * price : 123
     * perCashs : 12
     * directFansNum : 12
     * indirectFansNum : 12
     * selfOrderNum : 123
     * selfCommission : 116
     * recommendNum : 21312
     * sharePercent : 23
     * perMents : 12,12,12
     * recommendPercent : 12
     * perLevels : 21
     * note : 213123
     */

    private String id;
    private String name;
    private String defaultStatus;
    private String isEnable;
    private String upType;
    private String levelType;
    private String price;
    private String perCashs;
    private String directFansNum;
    private String indirectFansNum;
    private String selfOrderNum;
    private String selfCommission;
    private String recommendNum;
    private String sharePercent;
    private String perMents;
    private String recommendPercent;
    private String perLevels;
    private String note;
    private String tenantId;
    private int sort;
    private String nextLevel;

    public String getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(String nextLevel) {
        this.nextLevel = nextLevel;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

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

    public String getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(String defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getUpType() {
        return upType;
    }

    public void setUpType(String upType) {
        this.upType = upType;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPerCashs() {
        return perCashs;
    }

    public void setPerCashs(String perCashs) {
        this.perCashs = perCashs;
    }

    public String getDirectFansNum() {
        return directFansNum;
    }

    public void setDirectFansNum(String directFansNum) {
        this.directFansNum = directFansNum;
    }

    public String getIndirectFansNum() {
        return indirectFansNum;
    }

    public void setIndirectFansNum(String indirectFansNum) {
        this.indirectFansNum = indirectFansNum;
    }

    public String getSelfOrderNum() {
        return selfOrderNum;
    }

    public void setSelfOrderNum(String selfOrderNum) {
        this.selfOrderNum = selfOrderNum;
    }

    public String getSelfCommission() {
        return selfCommission;
    }

    public void setSelfCommission(String selfCommission) {
        this.selfCommission = selfCommission;
    }

    public String getRecommendNum() {
        return recommendNum;
    }

    public void setRecommendNum(String recommendNum) {
        this.recommendNum = recommendNum;
    }

    public String getSharePercent() {
        return sharePercent;
    }

    public void setSharePercent(String sharePercent) {
        this.sharePercent = sharePercent;
    }

    public String getPerMents() {
        return perMents;
    }

    public void setPerMents(String perMents) {
        this.perMents = perMents;
    }

    public String getRecommendPercent() {
        return recommendPercent;
    }

    public void setRecommendPercent(String recommendPercent) {
        this.recommendPercent = recommendPercent;
    }

    public String getPerLevels() {
        return perLevels;
    }

    public void setPerLevels(String perLevels) {
        this.perLevels = perLevels;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "OperatorBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", defaultStatus='" + defaultStatus + '\'' +
                ", isEnable='" + isEnable + '\'' +
                ", upType='" + upType + '\'' +
                ", levelType='" + levelType + '\'' +
                ", price='" + price + '\'' +
                ", perCashs='" + perCashs + '\'' +
                ", directFansNum='" + directFansNum + '\'' +
                ", indirectFansNum='" + indirectFansNum + '\'' +
                ", selfOrderNum='" + selfOrderNum + '\'' +
                ", selfCommission='" + selfCommission + '\'' +
                ", recommendNum='" + recommendNum + '\'' +
                ", sharePercent='" + sharePercent + '\'' +
                ", perMents='" + perMents + '\'' +
                ", recommendPercent='" + recommendPercent + '\'' +
                ", perLevels='" + perLevels + '\'' +
                ", note='" + note + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", sort=" + sort +
                ", nextLevel='" + nextLevel + '\'' +
                '}';
    }
}
