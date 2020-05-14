package com.example.entity;

public class YysQuanyiBean {
    private String url;
    private String name;
    private String price;

    public YysQuanyiBean(String url, String name, String price) {
        this.url = url;
        this.name = name;
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "YysQuanyiBean{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
