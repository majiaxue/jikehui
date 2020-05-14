package com.example.bean;

public class PointsCashoutRecordBean {

    /**
     * id : 28
     * userCode : 298242555449966592
     * cashOutCode : 309827827103170560
     * totalMoney : 2
     * backMoney : 1.8
     * createTime : 2019-07-13 09:12:00
     * dealTime : 2019-07-13 09:21:32
     * status : 1
     * dealUser : admin
     * account : 13201835918
     * name : 李晓玉
     * type : 1
     * tenantId : 1
     * classify : 1
     * integration : 200
     */

    private String id;
    private String userCode;
    private String cashOutCode;
    private String totalMoney;
    private String backMoney;
    private String createTime;
    private String dealTime;
    private String status;
    private String dealUser;
    private String account;
    private String name;
    private String type;
    private String tenantId;
    private String classify;
    private String integration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getCashOutCode() {
        return cashOutCode;
    }

    public void setCashOutCode(String cashOutCode) {
        this.cashOutCode = cashOutCode;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(String backMoney) {
        this.backMoney = backMoney;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDealUser() {
        return dealUser;
    }

    public void setDealUser(String dealUser) {
        this.dealUser = dealUser;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getIntegration() {
        return integration;
    }

    public void setIntegration(String integration) {
        this.integration = integration;
    }

    @Override
    public String toString() {
        return "PointsCashoutRecordBean{" +
                "id='" + id + '\'' +
                ", userCode='" + userCode + '\'' +
                ", cashOutCode='" + cashOutCode + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                ", backMoney='" + backMoney + '\'' +
                ", createTime='" + createTime + '\'' +
                ", dealTime='" + dealTime + '\'' +
                ", status='" + status + '\'' +
                ", dealUser='" + dealUser + '\'' +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", classify='" + classify + '\'' +
                ", integration='" + integration + '\'' +
                '}';
    }
}
