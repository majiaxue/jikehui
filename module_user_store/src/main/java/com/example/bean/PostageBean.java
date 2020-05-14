package com.example.bean;

public class PostageBean {

    /**
     * total : 2
     * feight : 0
     * isPinkage : 0
     */

    private double total;
    private double feight;
    private Integer quantity;
    private int sellerId;
    private int productId;
    private int isPinkage;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getFeight() {
        return feight;
    }

    public void setFeight(double feight) {
        this.feight = feight;
    }

    public int getIsPinkage() {
        return isPinkage;
    }

    public void setIsPinkage(int isPinkage) {
        this.isPinkage = isPinkage;
    }

    @Override
    public String toString() {
        return "PostageBean{" +
                "total=" + total +
                ", feight=" + feight +
                ", quantity=" + quantity +
                ", sellerId=" + sellerId +
                ", productId=" + productId +
                ", isPinkage=" + isPinkage +
                '}';
    }
}
