package com.example.entity;

public class SuggestionBean {
    private String FeedBackContent;
    private String FeedBackTime;
    private String replyContent;
    private String replyTime;

    public SuggestionBean(String feedBackContent, String feedBackTime, String replyContent, String replyTime) {
        FeedBackContent = feedBackContent;
        FeedBackTime = feedBackTime;
        this.replyContent = replyContent;
        this.replyTime = replyTime;
    }

    public String getFeedBackContent() {
        return FeedBackContent;
    }

    public String getFeedBackTime() {
        return FeedBackTime;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public String getReplyTime() {
        return replyTime;
    }

    @Override
    public String toString() {
        return "SuggestionBean{" +
                "FeedBackContent='" + FeedBackContent + '\'' +
                ", FeedBackTime='" + FeedBackTime + '\'' +
                ", replyContent='" + replyContent + '\'' +
                ", replyTime='" + replyTime + '\'' +
                '}';
    }
}
