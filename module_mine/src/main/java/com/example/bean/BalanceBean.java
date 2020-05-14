package com.example.bean;

public class BalanceBean {

    /**
     * totalblance : 0
     * historyBalance : 0
     */

    private double totalblance;
    private double historyBalance;

    public double getTotalblance() {
        return totalblance;
    }

    public void setTotalblance(double totalblance) {
        this.totalblance = totalblance;
    }

    public double getHistoryBalance() {
        return historyBalance;
    }

    public void setHistoryBalance(double historyBalance) {
        this.historyBalance = historyBalance;
    }

    @Override
    public String toString() {
        return "BalanceBean{" +
                "totalblance=" + totalblance +
                ", historyBalance=" + historyBalance +
                '}';
    }
}
