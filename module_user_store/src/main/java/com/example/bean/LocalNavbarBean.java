package com.example.bean;

public class LocalNavbarBean {

    /**
     * id : 10
     * sellerCategoryCode : 10001
     * sellerCategoryName : 电影院
     * sellerCategoryPicture : http://192.168.1.17:9000/seller/1ccf866103674aca942d8057dd97a65d.png
     * sellerCategorySort : 1
     * sellerCategoryState : 0
     * createTime : 2019-07-08 18:02:54
     * updateTime : 2019-07-09 10:42:23
     * sellerType : 1
     */

    private String id;
    private String sellerCategoryCode;
    private String sellerCategoryName;
    private String sellerCategoryPicture;
    private String sellerCategorySort;
    private String sellerCategoryState;
    private String createTime;
    private String updateTime;
    private String sellerType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerCategoryCode() {
        return sellerCategoryCode;
    }

    public void setSellerCategoryCode(String sellerCategoryCode) {
        this.sellerCategoryCode = sellerCategoryCode;
    }

    public String getSellerCategoryName() {
        return sellerCategoryName;
    }

    public void setSellerCategoryName(String sellerCategoryName) {
        this.sellerCategoryName = sellerCategoryName;
    }

    public String getSellerCategoryPicture() {
        return sellerCategoryPicture;
    }

    public void setSellerCategoryPicture(String sellerCategoryPicture) {
        this.sellerCategoryPicture = sellerCategoryPicture;
    }

    public String getSellerCategorySort() {
        return sellerCategorySort;
    }

    public void setSellerCategorySort(String sellerCategorySort) {
        this.sellerCategorySort = sellerCategorySort;
    }

    public String getSellerCategoryState() {
        return sellerCategoryState;
    }

    public void setSellerCategoryState(String sellerCategoryState) {
        this.sellerCategoryState = sellerCategoryState;
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

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    @Override
    public String toString() {
        return "LocalNavbarBean{" +
                "id='" + id + '\'' +
                ", sellerCategoryCode='" + sellerCategoryCode + '\'' +
                ", sellerCategoryName='" + sellerCategoryName + '\'' +
                ", sellerCategoryPicture='" + sellerCategoryPicture + '\'' +
                ", sellerCategorySort='" + sellerCategorySort + '\'' +
                ", sellerCategoryState='" + sellerCategoryState + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", sellerType='" + sellerType + '\'' +
                '}';
    }
}
