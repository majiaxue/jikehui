package com.example.bean;

import java.util.List;

public class MXBean {
    /**
     * memberIntegration : 10925
     * integralHistoryList : [{"id":1000,"userCode":"359947008724697088","integration":-5,"userIntegration":9995,"createTime":"2019-11-28 16:51:37","note":"兑换优惠券","type":9,"continueDay":null},{"id":1001,"userCode":"359947008724697088","integration":-5,"userIntegration":9990,"createTime":"2019-11-28 16:51:45","note":"兑换优惠券","type":9,"continueDay":null},{"id":1002,"userCode":"359947008724697088","integration":-5,"userIntegration":9985,"createTime":"2019-11-28 16:52:17","note":"兑换优惠券","type":9,"continueDay":null},{"id":1003,"userCode":"359947008724697088","integration":-5,"userIntegration":9980,"createTime":"2019-11-28 16:52:27","note":"兑换优惠券","type":9,"continueDay":null},{"id":1004,"userCode":"359947008724697088","integration":-5,"userIntegration":9975,"createTime":"2019-11-28 16:52:33","note":"兑换优惠券","type":9,"continueDay":null},{"id":1005,"userCode":"359947008724697088","integration":-5,"userIntegration":9970,"createTime":"2019-11-28 16:52:38","note":"兑换优惠券","type":9,"continueDay":null},{"id":1006,"userCode":"359947008724697088","integration":-5,"userIntegration":9965,"createTime":"2019-11-28 16:56:28","note":"兑换优惠券","type":9,"continueDay":null},{"id":1008,"userCode":"359947008724697088","integration":1000,"userIntegration":10965,"createTime":"2019-11-28 17:03:23","note":"激活码充值","type":8,"continueDay":null},{"id":1009,"userCode":"359947008724697088","integration":-5,"userIntegration":10960,"createTime":"2019-11-28 17:03:27","note":"兑换优惠券","type":9,"continueDay":null},{"id":1010,"userCode":"359947008724697088","integration":-5,"userIntegration":10955,"createTime":"2019-11-28 17:06:40","note":"兑换优惠券","type":9,"continueDay":null},{"id":1011,"userCode":"359947008724697088","integration":-5,"userIntegration":10950,"createTime":"2019-11-28 17:06:47","note":"兑换优惠券","type":9,"continueDay":null},{"id":1012,"userCode":"359947008724697088","integration":-5,"userIntegration":10945,"createTime":"2019-11-28 17:12:24","note":"兑换优惠券","type":9,"continueDay":null},{"id":1013,"userCode":"359947008724697088","integration":-5,"userIntegration":10940,"createTime":"2019-11-28 17:13:43","note":"兑换优惠券","type":9,"continueDay":null},{"id":1014,"userCode":"359947008724697088","integration":-5,"userIntegration":10935,"createTime":"2019-11-28 17:17:31","note":"兑换优惠券","type":9,"continueDay":null},{"id":1015,"userCode":"359947008724697088","integration":-5,"userIntegration":10930,"createTime":"2019-11-28 17:22:17","note":"兑换优惠券","type":9,"continueDay":null},{"id":1016,"userCode":"359947008724697088","integration":-5,"userIntegration":10925,"createTime":"2019-11-28 17:26:58","note":"兑换优惠券","type":9,"continueDay":null}]
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
         * id : 1000
         * userCode : 359947008724697088
         * integration : -5
         * userIntegration : 9995
         * createTime : 2019-11-28 16:51:37
         * note : 兑换优惠券
         * type : 9
         * continueDay : null
         */

        private int id;
        private String userCode;
        private String integration;
        private int userIntegration;
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

        public int getUserIntegration() {
            return userIntegration;
        }

        public void setUserIntegration(int userIntegration) {
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
