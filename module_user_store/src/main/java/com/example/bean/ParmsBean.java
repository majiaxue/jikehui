package com.example.bean;

public class ParmsBean {
    private String key;
    private String value;

    public ParmsBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ParmsBean{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
