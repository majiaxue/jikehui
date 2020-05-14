package com.example.bean;

import java.util.List;

public class ZhongXBannerBean {

    private List<RecordsBean> Records;

    public List<RecordsBean> getRecords() {
        return Records;
    }

    public void setRecords(List<RecordsBean> Records) {
        this.Records = Records;
    }

    public static class RecordsBean {
        /**
         * id : 39
         * name : 多用户下方轮播3
         * type : 9
         * picUrl : http://192.168.1.17:9000/seller/359d3e160c6042a78d75b096ee81db10.png
         * picBackUrl : null
         * startTime : null
         * endTime : null
         * status : 1
         * clickCount : null
         * orderCount : null
         * url : null
         * note : null
         * sort : 0
         * pid : null
         * flag : 1
         * switchFlag : 0
         */

        private int id;
        private String name;
        private int type;
        private String picUrl;
        private Object picBackUrl;
        private Object startTime;
        private Object endTime;
        private int status;
        private Object clickCount;
        private Object orderCount;
        private Object url;
        private Object note;
        private int sort;
        private Object pid;
        private int flag;
        private int switchFlag;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public Object getPicBackUrl() {
            return picBackUrl;
        }

        public void setPicBackUrl(Object picBackUrl) {
            this.picBackUrl = picBackUrl;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getClickCount() {
            return clickCount;
        }

        public void setClickCount(Object clickCount) {
            this.clickCount = clickCount;
        }

        public Object getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(Object orderCount) {
            this.orderCount = orderCount;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }

        public Object getNote() {
            return note;
        }

        public void setNote(Object note) {
            this.note = note;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public Object getPid() {
            return pid;
        }

        public void setPid(Object pid) {
            this.pid = pid;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public int getSwitchFlag() {
            return switchFlag;
        }

        public void setSwitchFlag(int switchFlag) {
            this.switchFlag = switchFlag;
        }
    }
}
