package com.example.entity;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class BaseRecBean {
    private String imgUrl;
    private int image;
    private String name;
    private String reduce_price;
    private String preferential_price;
    private String original_price;
    private String number;
    private String totalCount;
    private boolean isCheck;

    public BaseRecBean(int image, String name, String reduce_price, String preferential_price, String original_price, String number) {
        this.image = image;
        this.name = name;
        this.reduce_price = reduce_price;
        this.preferential_price = preferential_price;
        this.original_price = original_price;
        this.number = number;
    }

    public BaseRecBean(String imgUrl, String name, String reduce_price, String preferential_price, String original_price, String number, String totalCount) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.reduce_price = reduce_price;
        this.preferential_price = preferential_price;
        this.original_price = original_price;
        this.number = number;
        this.totalCount = totalCount;
    }

    public BaseRecBean(String imgUrl, String name, String reduce_price, String preferential_price, String original_price, String number, String totalCount, boolean isCheck) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.reduce_price = reduce_price;
        this.preferential_price = preferential_price;
        this.original_price = original_price;
        this.number = number;
        this.totalCount = totalCount;
        this.isCheck = isCheck;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReduce_price() {
        return reduce_price;
    }

    public void setReduce_price(String reduce_price) {
        this.reduce_price = reduce_price;
    }

    public String getPreferential_price() {
        return preferential_price;
    }

    public void setPreferential_price(String preferential_price) {
        this.preferential_price = preferential_price;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "BaseRecBean{" +
                "imgUrl='" + imgUrl + '\'' +
                ", image=" + image +
                ", name='" + name + '\'' +
                ", reduce_price='" + reduce_price + '\'' +
                ", preferential_price='" + preferential_price + '\'' +
                ", original_price='" + original_price + '\'' +
                ", number='" + number + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}
