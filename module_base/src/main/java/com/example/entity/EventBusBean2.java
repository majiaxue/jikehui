package com.example.entity;

public class EventBusBean2 {
    private String msg;
    private int position;

    public EventBusBean2(String msg, int position) {
        this.msg = msg;
        this.position = position;
    }

    public String getMsg() {
        return msg;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "EventBusBean2{" +
                "msg='" + msg + '\'' +
                ", position=" + position +
                '}';
    }
}
