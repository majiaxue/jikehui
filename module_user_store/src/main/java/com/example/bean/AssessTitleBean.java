package com.example.bean;

public class AssessTitleBean {
    private String title;
    private boolean isCheck;

    public AssessTitleBean(String title, boolean isCheck) {
        this.title = title;
        this.isCheck = isCheck;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "AssessTitleBean{" +
                "title='" + title + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}
