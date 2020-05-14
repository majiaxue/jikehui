package com.example.entity;

public class OrderRetrieveBean {
    private String num;
    private String centent;

    public OrderRetrieveBean(String num, String centent) {
        this.num = num;
        this.centent = centent;
    }

    public String getNum() {
        return num;
    }

    public String getCentent() {
        return centent;
    }

    @Override
    public String toString() {
        return "OrderRetrieveBean{" +
                "num='" + num + '\'' +
                ", centent='" + centent + '\'' +
                '}';
    }
}
