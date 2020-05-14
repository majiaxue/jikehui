package com.example.entity;

public class EventBusBean {
    private String code;
    private String msg;

    public EventBusBean(String msg) {
        this.msg = msg;
    }

    public EventBusBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
