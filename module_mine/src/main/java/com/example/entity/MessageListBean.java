package com.example.entity;

public class MessageListBean {
    private String title;
    private String content;
    private String time;

    public MessageListBean(String title, String content, String time) {
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "MessageListBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
