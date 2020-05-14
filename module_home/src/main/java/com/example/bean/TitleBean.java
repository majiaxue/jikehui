package com.example.bean;

public class TitleBean {
    private String content;
    private boolean isCheck;

    public TitleBean(String content, boolean isCheck) {
        this.content = content;
        this.isCheck = isCheck;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "TitleBean{" +
                "content='" + content + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}
