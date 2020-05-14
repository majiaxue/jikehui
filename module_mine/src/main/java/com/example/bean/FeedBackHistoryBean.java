package com.example.bean;

public class FeedBackHistoryBean {

    /**
     * id : 1
     * userCode : 298242555449966592
     * message : 拉姆咯喔太可恶了替我
     * backMessage : null
     * createTime : 2019-06-11 16:09:15
     * status : 0
     * hander : null
     * dealTime : null
     */

    private long id;
    private String userCode;
    private String message;
    private String backMessage;
    private String createTime;
    private int status;
    private String hander;
    private String dealTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBackMessage() {
        return backMessage;
    }

    public void setBackMessage(String backMessage) {
        this.backMessage = backMessage;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHander() {
        return hander;
    }

    public void setHander(String hander) {
        this.hander = hander;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    @Override
    public String toString() {
        return "FeedBackHistoryBean{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", message='" + message + '\'' +
                ", backMessage='" + backMessage + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                ", hander='" + hander + '\'' +
                ", dealTime='" + dealTime + '\'' +
                '}';
    }
}
