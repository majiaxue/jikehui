package com.example.bean;

public class TxtAndChooseBean {
    private String title;
    private String price;
    private boolean isChoose;

    public TxtAndChooseBean(String title, boolean isChoose) {
        this.title = title;
        this.isChoose = isChoose;
    }

    public TxtAndChooseBean(String title, String price, boolean isChoose) {
        this.title = title;
        this.price = price;
        this.isChoose = isChoose;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    @Override
    public String toString() {
        return "TxtAndChooseBean{" +
                "title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", isChoose=" + isChoose +
                '}';
    }
}
