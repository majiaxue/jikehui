package com.example.entity;

public class UpgradeBean {
    private String title;
    private String description;
    private String shoufei;
    private String zigou;
    private String yaoqing;
    private String yugu;

    public UpgradeBean(String title, String description, String shoufei, String zigou, String yaoqing, String yugu) {
        this.title = title;
        this.description = description;
        this.shoufei = shoufei;
        this.zigou = zigou;
        this.yaoqing = yaoqing;
        this.yugu = yugu;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getShoufei() {
        return shoufei;
    }

    public String getZigou() {
        return zigou;
    }

    public String getYaoqing() {
        return yaoqing;
    }

    public String getYugu() {
        return yugu;
    }

    @Override
    public String toString() {
        return "UpgradeBean{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", shoufei='" + shoufei + '\'' +
                ", zigou='" + zigou + '\'' +
                ", yaoqing='" + yaoqing + '\'' +
                ", yugu='" + yugu + '\'' +
                '}';
    }
}
