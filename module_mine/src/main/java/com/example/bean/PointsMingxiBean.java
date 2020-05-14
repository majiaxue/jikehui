package com.example.bean;

public class PointsMingxiBean {

    /**
     * id : 49
     * userCode : 298242555449966592
     * integration : 5
     * userIntegration : 352
     * createTime : 2019-07-08 10:50:09
     * note : 每日签到
     * type : 0
     * continueDay : 19
     */

    private String id;
    private String userCode;
    private String integration;
    private String userIntegration;
    private String createTime;
    private String note;
    private String type;
    private String continueDay;

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

    public String getIntegration() {
        return integration;
    }

    public void setIntegration(String integration) {
        this.integration = integration;
    }

    public String getUserIntegration() {
        return userIntegration;
    }

    public void setUserIntegration(String userIntegration) {
        this.userIntegration = userIntegration;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContinueDay() {
        return continueDay;
    }

    public void setContinueDay(String continueDay) {
        this.continueDay = continueDay;
    }

    @Override
    public String toString() {
        return "PointsMingxiBean{" +
                "id='" + id + '\'' +
                ", userCode='" + userCode + '\'' +
                ", integration='" + integration + '\'' +
                ", userIntegration='" + userIntegration + '\'' +
                ", createTime='" + createTime + '\'' +
                ", note='" + note + '\'' +
                ", type='" + type + '\'' +
                ", continueDay='" + continueDay + '\'' +
                '}';
    }
}
