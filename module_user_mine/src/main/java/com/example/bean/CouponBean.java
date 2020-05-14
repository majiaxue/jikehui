package com.example.bean;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public class CouponBean {

    /**
     * id : 1
     * type : 0
     * name : 测试优惠券添加
     * platform : 0
     * count : null
     * amount : 100
     * perLimit : 1
     * minPoint : 50
     * startTime : 2019-05-29 00:00:00
     * endTime : 2019-05-31 00:00:00
     * useType : 0
     * note : 测试优惠券添加test
     * publishCount : 100
     * useCount : null
     * receiveCount : null
     * enableTime : null
     * code : null
     * memberLevel : null
     * sellerId : 100001
     * sellerName : null
     */

    private int id;
    private int type;
    private String name;
    private int platform;
    private Object count;
    private int amount;
    private int perLimit;
    private int minPoint;
    private String startTime;
    private String endTime;
    private int useType;
    private String note;
    private int publishCount;
    private Object useCount;
    private Object receiveCount;
    private Object enableTime;
    private Object code;
    private Object memberLevel;
    private String sellerId;
    private String sellerName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public Object getCount() {
        return count;
    }

    public void setCount(Object count) {
        this.count = count;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPerLimit() {
        return perLimit;
    }

    public void setPerLimit(int perLimit) {
        this.perLimit = perLimit;
    }

    public int getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(int minPoint) {
        this.minPoint = minPoint;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getUseType() {
        return useType;
    }

    public void setUseType(int useType) {
        this.useType = useType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPublishCount() {
        return publishCount;
    }

    public void setPublishCount(int publishCount) {
        this.publishCount = publishCount;
    }

    public Object getUseCount() {
        return useCount;
    }

    public void setUseCount(Object useCount) {
        this.useCount = useCount;
    }

    public Object getReceiveCount() {
        return receiveCount;
    }

    public void setReceiveCount(Object receiveCount) {
        this.receiveCount = receiveCount;
    }

    public Object getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Object enableTime) {
        this.enableTime = enableTime;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Object memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
