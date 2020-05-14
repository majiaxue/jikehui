package com.example.bean;

public class PredictBean {

    /**
     * totalAmount : 0
     * lastDayPayCount : 0
     * settleLastMonth : 0
     * todayPayCount : 0
     * waitLastMonth : 0
     * lastDayMoney : 0
     * waitCurrentMonth : 0
     * settleCurrentMonth : 0
     * todayMoney : 0
     */

    /**
     * 总收入
     */
    private String totalAmount;
    /**
     * 昨日付款笔数
     */
    private String lastDayPayCount;
    /**
     * 上月结算
     */
    private String settleLastMonth;
    /**
     * 本日付款笔数
     */
    private String todayPayCount;
    /**
     * 上月预估
     */
    private String waitLastMonth;
    /**
     * 昨日预计
     */
    private String lastDayMoney;
    /**
     * 本月预估
     */
    private String waitCurrentMonth;
    /**
     * 本月结算
     */
    private String settleCurrentMonth;
    /**
     * 本日预计
     */
    private String todayMoney;

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getLastDayPayCount() {
        return lastDayPayCount;
    }

    public void setLastDayPayCount(String lastDayPayCount) {
        this.lastDayPayCount = lastDayPayCount;
    }

    public String getSettleLastMonth() {
        return settleLastMonth;
    }

    public void setSettleLastMonth(String settleLastMonth) {
        this.settleLastMonth = settleLastMonth;
    }

    public String getTodayPayCount() {
        return todayPayCount;
    }

    public void setTodayPayCount(String todayPayCount) {
        this.todayPayCount = todayPayCount;
    }

    public String getWaitLastMonth() {
        return waitLastMonth;
    }

    public void setWaitLastMonth(String waitLastMonth) {
        this.waitLastMonth = waitLastMonth;
    }

    public String getLastDayMoney() {
        return lastDayMoney;
    }

    public void setLastDayMoney(String lastDayMoney) {
        this.lastDayMoney = lastDayMoney;
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

    public String getTodayMoney() {
        return todayMoney;
    }

    public void setTodayMoney(String todayMoney) {
        this.todayMoney = todayMoney;
    }

    @Override
    public String toString() {
        return "PredictBean{" +
                "totalAmount=" + totalAmount +
                ", lastDayPayCount=" + lastDayPayCount +
                ", settleLastMonth=" + settleLastMonth +
                ", todayPayCount=" + todayPayCount +
                ", waitLastMonth=" + waitLastMonth +
                ", lastDayMoney=" + lastDayMoney +
                ", waitCurrentMonth=" + waitCurrentMonth +
                ", settleCurrentMonth=" + settleCurrentMonth +
                ", todayMoney=" + todayMoney +
                '}';
    }
}
