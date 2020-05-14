package com.example.bean;

public class GroupFansPeopleBean {

    /**
     * id : null
     * userCode : null
     * parentId : null
     * parentList : null
     * createTime : null
     * firstFans : 1
     * todayFans : 3
     */

    private Object id;
    private Object userCode;
    private Object parentId;
    private Object parentList;
    private Object createTime;
    private int firstFans;
    private int todayFans;
    private String parent;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getUserCode() {
        return userCode;
    }

    public void setUserCode(Object userCode) {
        this.userCode = userCode;
    }

    public Object getParentId() {
        return parentId;
    }

    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    public Object getParentList() {
        return parentList;
    }

    public void setParentList(Object parentList) {
        this.parentList = parentList;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public int getFirstFans() {
        return firstFans;
    }

    public void setFirstFans(int firstFans) {
        this.firstFans = firstFans;
    }

    public int getTodayFans() {
        return todayFans;
    }

    public void setTodayFans(int todayFans) {
        this.todayFans = todayFans;
    }

    @Override
    public String toString() {
        return "GroupFansPeopleBean{" +
                "id=" + id +
                ", userCode=" + userCode +
                ", parentId=" + parentId +
                ", parentList=" + parentList +
                ", createTime=" + createTime +
                ", firstFans=" + firstFans +
                ", todayFans=" + todayFans +
                ", parent='" + parent + '\'' +
                '}';
    }
}
