package com.example.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/29
 * Describe:
 */
public class LogisticsInforMationBean {

    /**
     * LogisticCode : 806080423249290885
     * ShipperCode : 圆通速递
     * Traces : [{"AcceptStation":"【浙江省温州市平阳县公司】 已收件","AcceptTime":"2019-05-27 17:35:56"},{"AcceptStation":"【浙江省温州市平阳县公司】 已打包","AcceptTime":"2019-05-27 17:45:26"},{"AcceptStation":"【浙江省温州市平阳县】 已发出 下一站 【温州转运中心】","AcceptTime":"2019-05-27 17:48:27"},{"AcceptStation":"【温州转运中心】 已收入","AcceptTime":"2019-05-27 20:36:48"},{"AcceptStation":"【温州转运中心】 已发出 下一站 【郑州转运中心】","AcceptTime":"2019-05-27 20:38:00"},{"AcceptStation":"【郑州转运中心】 已收入","AcceptTime":"2019-05-28 20:54:50"},{"AcceptStation":"【郑州转运中心】 已收入","AcceptTime":"2019-05-28 21:05:35"},{"AcceptStation":"【郑州转运中心】 已发出 下一站 【河南省郑州市正光公司】","AcceptTime":"2019-05-28 21:06:22"},{"AcceptStation":"【河南省郑州市正光公司】 派件人: 张伟 派件中 派件员电话13526795830","AcceptTime":"2019-05-29 07:16:41"},{"AcceptStation":"快件已由晖达新领地2期物业服务中心丰巢柜代收，取件码已发送，请及时取件。","AcceptTime":"2019-05-29 08:45:09"},{"AcceptStation":"快件已存放至HN晖达新领地2期丰巢【自提柜】，请及时取件。有问题请联系派件员13526795830","AcceptTime":"2019-05-29 08:45:15"},{"AcceptStation":"客户 签收人: 快递柜 已签收 感谢使用圆通速递，期待再次为您服务","AcceptTime":"2019-05-29 21:17:49"}]
     * State : 3
     * EBusinessID : 1534311
     * Success : true
     */

    private String LogisticCode;
    private String ShipperCode;
    private String State;
    private String EBusinessID;
    private String Reason;
    private boolean Success;
    private List<TracesBean> Traces;

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String LogisticCode) {
        this.LogisticCode = LogisticCode;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String ShipperCode) {
        this.ShipperCode = ShipperCode;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public List<TracesBean> getTraces() {
        return Traces;
    }

    public void setTraces(List<TracesBean> Traces) {
        this.Traces = Traces;
    }

    public static class TracesBean {
        /**
         * AcceptStation : 【浙江省温州市平阳县公司】 已收件
         * AcceptTime : 2019-05-27 17:35:56
         */

        private String AcceptStation;
        private String AcceptTime;

        public String getAcceptStation() {
            return AcceptStation;
        }

        public void setAcceptStation(String AcceptStation) {
            this.AcceptStation = AcceptStation;
        }

        public String getAcceptTime() {
            return AcceptTime;
        }

        public void setAcceptTime(String AcceptTime) {
            this.AcceptTime = AcceptTime;
        }

    }
}
