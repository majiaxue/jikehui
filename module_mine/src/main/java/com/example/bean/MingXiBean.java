package com.example.bean;

import java.io.Serializable;
import java.util.List;

public class MingXiBean implements Serializable {


    /**
     * memberIntegration : 1000
     * integralHistoryList : [{"id":1027,"userCode":"361320307522273280","integration":1000,"userIntegration":1000,"createTime":"2019-12-03 16:43:33","note":"激活码充值","type":8,"continueDay":null}]
     */

    private String memberIntegration;
    private List<IntegralHistoryListBean> integralHistoryList;

    public String getMemberIntegration() {
        return memberIntegration;
    }

    public void setMemberIntegration(String memberIntegration) {
        this.memberIntegration = memberIntegration;
    }

    public List<IntegralHistoryListBean> getIntegralHistoryList() {
        return integralHistoryList;
    }

    public void setIntegralHistoryList(List<IntegralHistoryListBean> integralHistoryList) {
        this.integralHistoryList = integralHistoryList;
    }

    public static class IntegralHistoryListBean {
        /**
         * id : 1027
         * userCode : 361320307522273280
         * integration : 1000
         * userIntegration : 1000
         * createTime : 2019-12-03 16:43:33
         * note : 激活码充值
         * type : 8
         * continueDay : null
         */

        private int id;
        private String userCode;
        private String integration;
        private String userIntegration;
        private String createTime;
        private String note;
        private int type;
        private Object continueDay;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getIntegration() {
            return integration;
        }

        public void setIntegration(String integration) {
            this.integration = integration;
        }

        public String getUserIntegration() {
            return userIntegration;
        }

        public void setUserIntegration(String userIntegration) {
            this.userIntegration = userIntegration;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getContinueDay() {
            return continueDay;
        }

        public void setContinueDay(Object continueDay) {
            this.continueDay = continueDay;
        }
    }
}
