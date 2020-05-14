package com.example.bean;

public class FlashSaleTimeBean {
    private String time;
    private boolean isCheck;

    public FlashSaleTimeBean(String time, boolean isCheck) {
        this.time = time;
        this.isCheck = isCheck;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
