package com.example.bean;

public class ChooseInsideBean {
    private String content;
    private String picUrl;
    private double price;
    private long stock;
    //是否可点击   （库存为0不可点击）
    private boolean canClick = true;

    public ChooseInsideBean(String content, String picUrl, double price, boolean canClick) {
        this.content = content;
        this.picUrl = picUrl;
        this.price = price;
        this.canClick = canClick;
    }

    public ChooseInsideBean(String content, String picUrl, double price, long stock, boolean canClick) {
        this.content = content;
        this.picUrl = picUrl;
        this.price = price;
        this.stock = stock;
        this.canClick = canClick;
    }

    public ChooseInsideBean(String content, double price, boolean canClick) {
        this.content = content;
        this.price = price;
        this.canClick = canClick;
    }

    public ChooseInsideBean(String content, double price) {
        this.content = content;
        this.price = price;
    }

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public ChooseInsideBean(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ChooseInsideBean{" +
                "content='" + content + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", canClick=" + canClick +
                '}';
    }
}
