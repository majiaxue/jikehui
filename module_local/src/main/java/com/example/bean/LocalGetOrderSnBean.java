package com.example.bean;

public class LocalGetOrderSnBean {
    private String orderSn;
    private String totalMoney;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "LocalGetOrderSnBean{" +
                "orderSn='" + orderSn + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                '}';
    }
}
