package com.example.bean;

public class PunchSignBean {

    /**
     * result : {"fansIntegration":0,"viewGoods":0,"viewMoreGoods":0,"firstOrder":0,"daySign":0,"orderIntegration":0,"inviteUser":0,"shareGoods":0}
     * newUserConf : {"id":1,"orderIntegration":15,"orderNum":3,"fansIntegration":10,"fansNum":2,"firstOrder":10,"createTime":"2019-07-08 17:48:40","updateTime":"2019-07-09 14:24:06","status":1,"note":"413416541641341654164134165416413416541641341654164134165416413416541641341654164134165416413416541641341654164134165416413416541641341654164134165416413416541641341654164134165416413416541641341654164134165416413416541641341654164134165416","tenantId":1}
     * integration : 0
     * finish : {"fansNum":0,"todayFansCount":0,"orderNum":0,"continueDay":0,"historyCount":0}
     * signSetting : {"id":1,"daySign":5,"continueSign":5,"continueDay":7,"viewGoods":4,"viewMoreGoods":10,"goodsNum":2,"shareGoods":6,"shareNum":3,"inviteUser":10,"inviteNum":3,"createTime":"2019-07-04 16:42:14","updateTime":"2019-07-10 11:55:27","tenantId":1,"status":1,"note":"41896418416841653156"}
     */

    private ResultBean result;
    private NewUserConfBean newUserConf;
    private FinishBean finish;
    private SignSettingBean signSetting;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public NewUserConfBean getNewUserConf() {
        return newUserConf;
    }

    public void setNewUserConf(NewUserConfBean newUserConf) {
        this.newUserConf = newUserConf;
    }

    public FinishBean getFinish() {
        return finish;
    }

    public void setFinish(FinishBean finish) {
        this.finish = finish;
    }

    public SignSettingBean getSignSetting() {
        return signSetting;
    }

    public void setSignSetting(SignSettingBean signSetting) {
        this.signSetting = signSetting;
    }

    public static class ResultBean {
        /**
         * fansIntegration : 0
         * viewGoods : 0
         * viewMoreGoods : 0
         * firstOrder : 0
         * daySign : 0
         * orderIntegration : 0
         * inviteUser : 0
         * shareGoods : 0
         */

        private int fansIntegration;
        private int integration;
        private int viewGoods;
        private int viewMoreGoods;
        private int firstOrder;
        private int daySign;
        private int orderIntegration;
        private int inviteUser;
        private int shareGoods;

        public int getIntegration() {
            return integration;
        }

        public void setIntegration(int integration) {
            this.integration = integration;
        }

        public int getFansIntegration() {
            return fansIntegration;
        }

        public void setFansIntegration(int fansIntegration) {
            this.fansIntegration = fansIntegration;
        }

        public int getViewGoods() {
            return viewGoods;
        }

        public void setViewGoods(int viewGoods) {
            this.viewGoods = viewGoods;
        }

        public int getViewMoreGoods() {
            return viewMoreGoods;
        }

        public void setViewMoreGoods(int viewMoreGoods) {
            this.viewMoreGoods = viewMoreGoods;
        }

        public int getFirstOrder() {
            return firstOrder;
        }

        public void setFirstOrder(int firstOrder) {
            this.firstOrder = firstOrder;
        }

        public int getDaySign() {
            return daySign;
        }

        public void setDaySign(int daySign) {
            this.daySign = daySign;
        }

        public int getOrderIntegration() {
            return orderIntegration;
        }

        public void setOrderIntegration(int orderIntegration) {
            this.orderIntegration = orderIntegration;
        }

        public int getInviteUser() {
            return inviteUser;
        }

        public void setInviteUser(int inviteUser) {
            this.inviteUser = inviteUser;
        }

        public int getShareGoods() {
            return shareGoods;
        }

        public void setShareGoods(int shareGoods) {
            this.shareGoods = shareGoods;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "fansIntegration=" + fansIntegration +
                    ", integration=" + integration +
                    ", viewGoods=" + viewGoods +
                    ", viewMoreGoods=" + viewMoreGoods +
                    ", firstOrder=" + firstOrder +
                    ", daySign=" + daySign +
                    ", orderIntegration=" + orderIntegration +
                    ", inviteUser=" + inviteUser +
                    ", shareGoods=" + shareGoods +
                    '}';
        }
    }

    public static class NewUserConfBean {
        /**
         * id : 1
         * orderIntegration : 15
         * orderNum : 3
         * fansIntegration : 10
         * fansNum : 2
         * firstOrder : 10
         * createTime : 2019-07-08 17:48:40
         * updateTime : 2019-07-09 14:24:06
         * status : 1
         * note : 413416541641341654164134165416413416541641341654164134165416413416541641341654164134165416413416541641341654164134165416413416541641341654164134165416413416541641341654164134165416413416541641341654164134165416413416541641341654164134165416
         * tenantId : 1
         */

        private int id;
        private int orderIntegration;
        private int orderNum;
        private int fansIntegration;
        private int fansNum;
        private int firstOrder;
        private String createTime;
        private String updateTime;
        private int status;
        private String note;
        private int tenantId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrderIntegration() {
            return orderIntegration;
        }

        public void setOrderIntegration(int orderIntegration) {
            this.orderIntegration = orderIntegration;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public int getFansIntegration() {
            return fansIntegration;
        }

        public void setFansIntegration(int fansIntegration) {
            this.fansIntegration = fansIntegration;
        }

        public int getFansNum() {
            return fansNum;
        }

        public void setFansNum(int fansNum) {
            this.fansNum = fansNum;
        }

        public int getFirstOrder() {
            return firstOrder;
        }

        public void setFirstOrder(int firstOrder) {
            this.firstOrder = firstOrder;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getTenantId() {
            return tenantId;
        }

        public void setTenantId(int tenantId) {
            this.tenantId = tenantId;
        }

        @Override
        public String toString() {
            return "NewUserConfBean{" +
                    "id=" + id +
                    ", orderIntegration=" + orderIntegration +
                    ", orderNum=" + orderNum +
                    ", fansIntegration=" + fansIntegration +
                    ", fansNum=" + fansNum +
                    ", firstOrder=" + firstOrder +
                    ", createTime='" + createTime + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", status=" + status +
                    ", note='" + note + '\'' +
                    ", tenantId=" + tenantId +
                    '}';
        }
    }

    public static class FinishBean {
        /**
         * fansNum : 0
         * todayFansCount : 0
         * orderNum : 0
         * continueDay : 0
         * historyCount : 0
         */

        private int fansNum;
        private int todayFansCount;
        private int orderNum;
        private int continueDay;
        private int historyCount;

        public int getFansNum() {
            return fansNum;
        }

        public void setFansNum(int fansNum) {
            this.fansNum = fansNum;
        }

        public int getTodayFansCount() {
            return todayFansCount;
        }

        public void setTodayFansCount(int todayFansCount) {
            this.todayFansCount = todayFansCount;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public int getContinueDay() {
            return continueDay;
        }

        public void setContinueDay(int continueDay) {
            this.continueDay = continueDay;
        }

        public int getHistoryCount() {
            return historyCount;
        }

        public void setHistoryCount(int historyCount) {
            this.historyCount = historyCount;
        }

        @Override
        public String toString() {
            return "FinishBean{" +
                    "fansNum=" + fansNum +
                    ", todayFansCount=" + todayFansCount +
                    ", orderNum=" + orderNum +
                    ", continueDay=" + continueDay +
                    ", historyCount=" + historyCount +
                    '}';
        }
    }

    public static class SignSettingBean {
        /**
         * id : 1
         * daySign : 5
         * continueSign : 5
         * continueDay : 7
         * viewGoods : 4
         * viewMoreGoods : 10
         * goodsNum : 2
         * shareGoods : 6
         * shareNum : 3
         * inviteUser : 10
         * inviteNum : 3
         * createTime : 2019-07-04 16:42:14
         * updateTime : 2019-07-10 11:55:27
         * tenantId : 1
         * status : 1
         * note : 41896418416841653156
         */

        private int id;
        private int daySign;
        private int continueSign;
        private int continueDay;
        private int viewGoods;
        private int viewMoreGoods;
        private int goodsNum;
        private int shareGoods;
        private int shareNum;
        private int inviteUser;
        private int inviteNum;
        private String createTime;
        private String updateTime;
        private int tenantId;
        private int status;
        private String note;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDaySign() {
            return daySign;
        }

        public void setDaySign(int daySign) {
            this.daySign = daySign;
        }

        public int getContinueSign() {
            return continueSign;
        }

        public void setContinueSign(int continueSign) {
            this.continueSign = continueSign;
        }

        public int getContinueDay() {
            return continueDay;
        }

        public void setContinueDay(int continueDay) {
            this.continueDay = continueDay;
        }

        public int getViewGoods() {
            return viewGoods;
        }

        public void setViewGoods(int viewGoods) {
            this.viewGoods = viewGoods;
        }

        public int getViewMoreGoods() {
            return viewMoreGoods;
        }

        public void setViewMoreGoods(int viewMoreGoods) {
            this.viewMoreGoods = viewMoreGoods;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public int getShareGoods() {
            return shareGoods;
        }

        public void setShareGoods(int shareGoods) {
            this.shareGoods = shareGoods;
        }

        public int getShareNum() {
            return shareNum;
        }

        public void setShareNum(int shareNum) {
            this.shareNum = shareNum;
        }

        public int getInviteUser() {
            return inviteUser;
        }

        public void setInviteUser(int inviteUser) {
            this.inviteUser = inviteUser;
        }

        public int getInviteNum() {
            return inviteNum;
        }

        public void setInviteNum(int inviteNum) {
            this.inviteNum = inviteNum;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getTenantId() {
            return tenantId;
        }

        public void setTenantId(int tenantId) {
            this.tenantId = tenantId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        @Override
        public String toString() {
            return "SignSettingBean{" +
                    "id=" + id +
                    ", daySign=" + daySign +
                    ", continueSign=" + continueSign +
                    ", continueDay=" + continueDay +
                    ", viewGoods=" + viewGoods +
                    ", viewMoreGoods=" + viewMoreGoods +
                    ", goodsNum=" + goodsNum +
                    ", shareGoods=" + shareGoods +
                    ", shareNum=" + shareNum +
                    ", inviteUser=" + inviteUser +
                    ", inviteNum=" + inviteNum +
                    ", createTime='" + createTime + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", tenantId=" + tenantId +
                    ", status=" + status +
                    ", note='" + note + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PunchSignBean{" +
                "result=" + result +
                ", newUserConf=" + newUserConf +
                ", finish=" + finish +
                ", signSetting=" + signSetting +
                '}';
    }
}
