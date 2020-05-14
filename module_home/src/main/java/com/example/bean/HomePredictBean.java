package com.example.bean;

public class HomePredictBean {

    /**
     * settleLastMonth : 0
     * waitCurrentMonth : 4800
     * settleCurrentMonth : 3600
     */

    private String settleLastMonth;
    private String waitCurrentMonth;
    private String settleCurrentMonth;
    private String waitLastMonth;

    public String getWaitLastMonth() {
        return waitLastMonth;
    }

    public void setWaitLastMonth(String waitLastMonth) {
        this.waitLastMonth = waitLastMonth;
    }

    public String getSettleLastMonth() {
        return settleLastMonth;
    }

    public void setSettleLastMonth(String settleLastMonth) {
        this.settleLastMonth = settleLastMonth;
    }

    public String getWaitCurrentMonth() {
        return waitCurrentMonth;
    }

    public void setWaitCurrentMonth(String waitCurrentMonth) {
        this.waitCurrentMonth = waitCurrentMonth;
    }

    public String getSettleCurrentMonth() {
        return settleCurrentMonth;
    }

    public void setSettleCurrentMonth(String settleCurrentMonth) {
        this.settleCurrentMonth = settleCurrentMonth;
    }

    @Override
    public String toString() {
        return "HomePredictBean{" +
                "settleLastMonth='" + settleLastMonth + '\'' +
                ", waitCurrentMonth='" + waitCurrentMonth + '\'' +
                ", settleCurrentMonth='" + settleCurrentMonth + '\'' +
                ", waitLastMonth='" + waitLastMonth + '\'' +
                '}';
    }
}
