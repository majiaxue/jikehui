package com.example.entity;

/**
 * Created by cuihaohao on 2019/5/22
 * Describe:
 */
public class BaseStaggeredRecBean {
    private int image;
    private String name;
    private String price;
    private String payment_amount;
    private String good_reputation;
    private String shop;

    public BaseStaggeredRecBean(int image, String name, String price, String payment_amount, String good_reputation, String shop) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.payment_amount = payment_amount;
        this.good_reputation = good_reputation;
        this.shop = shop;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(String payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getGood_reputation() {
        return good_reputation;
    }

    public void setGood_reputation(String good_reputation) {
        this.good_reputation = good_reputation;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }
}
