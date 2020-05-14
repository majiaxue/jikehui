package com.example.bean;

import java.util.List;

public class PopGuiGeBean {
    private String title;
    private List<TxtAndChooseBean> content;

    public PopGuiGeBean(String title, List<TxtAndChooseBean> content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TxtAndChooseBean> getContent() {
        return content;
    }

    public void setContent(List<TxtAndChooseBean> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PopGuiGeBean{" +
                "title='" + title + '\'' +
                ", content=" + content +
                '}';
    }
}
