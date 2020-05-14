package com.example.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.io.Serializable;
import java.util.List;

public class BannerBean implements Serializable {

    private List<RecordsBean> Records;

    public List<RecordsBean> getRecords() {
        return Records;
    }

    public void setRecords(List<RecordsBean> Records) {
        this.Records = Records;
    }

    @Override
    public String toString() {
        return "BannerBean{" +
                "Records=" + Records +
                '}';
    }

    public static class RecordsBean extends SimpleBannerInfo {
        public RecordsBean() {
        }

        public RecordsBean(String picUrl) {
            this.picUrl = picUrl;
        }

        /**
         * id : 27
         * name : 促销5折起
         * type : 1
         * picUrl : http://192.168.1.22:9000/seller/6afb8b1d240641c5a3ec92802d9eb1be.png
         * picBackUrl : http://192.168.1.22:9000/seller/15feeaf7c742436ab1c2141a3b1e1550.png
         * startTime : null
         * endTime : null
         * status : 1
         * clickCount : null
         * orderCount : null
         * url : www.baidu.com
         * note : 促销5折起
         * sort : 0
         * pid : null
         */

        private String id;
        private String name;
        private String type;
        private String picUrl;
        private String picBackUrl;
        private String startTime;
        private String endTime;
        private String status;
        private String clickCount;
        private String orderCount;
        private String url;
        private String note;
        private String sort;
        private String pid;
        private String flag;
        private String switchFlag;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getSwitchFlag() {
            return switchFlag;
        }

        public void setSwitchFlag(String switchFlag) {
            this.switchFlag = switchFlag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getPicBackUrl() {
            return picBackUrl;
        }

        public void setPicBackUrl(String picBackUrl) {
            this.picBackUrl = picBackUrl;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getClickCount() {
            return clickCount;
        }

        public void setClickCount(String clickCount) {
            this.clickCount = clickCount;
        }

        public String getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(String orderCount) {
            this.orderCount = orderCount;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        @Override
        public String getXBannerUrl() {
            return picUrl;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    ", picBackUrl='" + picBackUrl + '\'' +
                    ", startTime='" + startTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    ", status='" + status + '\'' +
                    ", clickCount='" + clickCount + '\'' +
                    ", orderCount='" + orderCount + '\'' +
                    ", url='" + url + '\'' +
                    ", note='" + note + '\'' +
                    ", sort='" + sort + '\'' +
                    ", pid='" + pid + '\'' +
                    ", flag='" + flag + '\'' +
                    ", switchFlag='" + switchFlag + '\'' +
                    '}';
        }
    }
}
