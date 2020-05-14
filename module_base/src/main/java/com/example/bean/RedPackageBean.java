package com.example.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.io.Serializable;

public class RedPackageBean extends SimpleBannerInfo implements Serializable {

    /**
     * id : 13
     * money : 20
     * buyMoney : 10
     * count : 4
     * type : 0
     * useCondition : 20
     * validTime : 1
     * status : 1
     * createTime : 2019-08-27 17:21:48
     * updateTime : 2019-08-27 17:30:01
     * tenantId : 1
     * pic : http://192.168.0.17:9000/goods/7b2e5803b2cf4a189ac6fe6f78f7dc5f.png
     * name : 优惠红包满20减5元
     * note : 111111111111111111111111111111
     */

    private String id;
    private String money;
    private String buyMoney;
    private String count;
    private String type;
    private String useCondition;
    private String validTime;
    private String status;
    private String createTime;
    private String updateTime;
    private String tenantId;
    private String pic;
    private String name;
    private String note;
    private int background;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBuyMoney() {
        return buyMoney;
    }

    public void setBuyMoney(String buyMoney) {
        this.buyMoney = buyMoney;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUseCondition() {
        return useCondition;
    }

    public void setUseCondition(String useCondition) {
        this.useCondition = useCondition;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    @Override
    public Object getXBannerUrl() {
        return null;
    }

    @Override
    public String toString() {
        return "RedPackageBean{" +
                "id='" + id + '\'' +
                ", money='" + money + '\'' +
                ", buyMoney='" + buyMoney + '\'' +
                ", count='" + count + '\'' +
                ", type='" + type + '\'' +
                ", useCondition='" + useCondition + '\'' +
                ", validTime='" + validTime + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", pic='" + pic + '\'' +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
