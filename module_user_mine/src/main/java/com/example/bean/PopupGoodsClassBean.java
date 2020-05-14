package com.example.bean;

public class PopupGoodsClassBean {

    /**
     * id : 18
     * sellerCategoryCode : 00001
     * sellerCategoryName : 服装
     * sellerCategoryPicture : http://192.168.1.17:9000/seller/1ca42de8951842ef981b87c08fd81bfc.png
     * sellerCategorySort : 0
     * sellerCategoryState : 0
     * createTime : 2019-07-09 10:54:09
     * updateTime : null
     * sellerType : 0
     */

    private int id;
    private String sellerCategoryCode;
    private String sellerCategoryName;
    private String sellerCategoryPicture;
    private int sellerCategorySort;
    private int sellerCategoryState;
    private String createTime;
    private Object updateTime;
    private int sellerType;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getSellerCategorySort() {
        return sellerCategorySort;
    }

    public void setSellerCategorySort(int sellerCategorySort) {
        this.sellerCategorySort = sellerCategorySort;
    }

    public int getSellerCategoryState() {
        return sellerCategoryState;
    }

    public void setSellerCategoryState(int sellerCategoryState) {
        this.sellerCategoryState = sellerCategoryState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public int getSellerType() {
        return sellerType;
    }

    public void setSellerType(int sellerType) {
        this.sellerType = sellerType;
    }
}
