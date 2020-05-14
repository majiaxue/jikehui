package com.example.entity;

public class YysFactorBean {
    private String type;
    private String price;

    public YysFactorBean(String type, String price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "YysFactorBean{" +
                "type='" + type + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
