package com.example.bean;

import java.io.Serializable;

public class MessageCenterBean implements Serializable {

    /**
     * id : 36
     * title : 物流消息
     * message : 你的xxx物品正在路上，由xxxx物流公司运输，大约xxx天后送达
     * goodsUrl : 123
     * picUrl : 123
     * targetPlatform : 0,1,2
     * targetPeople :
     * sendType : 0
     * time : 2019-05-30 00:00:00
     * sendTime : 2019-06-18 08:12:15
     * flag : 0
     * createTime : 2019-05-27 17:11:22
     * userId :
     */

    private String id;
    private String title;
    private String message;
    private String goodsUrl;
    private String picUrl;
    private String targetPlatform;
    private String targetPeople;
    private String sendType;
    private String time;
    private String sendTime;
    private String flag;
    private String createTime;
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTargetPlatform() {
        return targetPlatform;
    }

    public void setTargetPlatform(String targetPlatform) {
        this.targetPlatform = targetPlatform;
    }

    public String getTargetPeople() {
        return targetPeople;
    }

    public void setTargetPeople(String targetPeople) {
        this.targetPeople = targetPeople;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MessageCenterBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", goodsUrl='" + goodsUrl + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", targetPlatform='" + targetPlatform + '\'' +
                ", targetPeople='" + targetPeople + '\'' +
                ", sendType='" + sendType + '\'' +
                ", time='" + time + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", flag=" + flag +
                ", createTime='" + createTime + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
