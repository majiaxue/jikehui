package com.example.bean;

public class FansOrderCensusBean {

    /**
     * totalAmount : 369
     * totalCount : 3
     * totalBackMoney : 0
     */

    private double totalAmount;
    private int totalCount;
    private double totalBackMoney;

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public double getTotalBackMoney() {
        return totalBackMoney;
    }

    public void setTotalBackMoney(double totalBackMoney) {
        this.totalBackMoney = totalBackMoney;
    }

    @Override
    public String toString() {
        return "FansOrderCensusBean{" +
                "totalAmount=" + totalAmount +
                ", totalCount=" + totalCount +
                ", totalBackMoney=" + totalBackMoney +
                '}';
    }
}
