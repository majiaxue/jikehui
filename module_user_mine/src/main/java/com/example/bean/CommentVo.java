package com.example.bean;

import java.sql.Time;
import java.util.List;

/**
 * Created by cuihaohao on 2019/6/17
 * Describe:
 */
public class CommentVo {
    /**
     * 名称
     */
    private String icon;
    /**
     * 头像
     */
    private String nickname;
    /**
     * 信息
     */
    private String info;
    /**
     * 图片集合','分割
     */
    private String pics;
    /**
     * 订单管理SN
     */
    private String orderSn;
    /**
     * 订单ITEM,,ID
     */
    private String orderItemId;
    /**
     *
     */
    private String productId;
    /**
     * 评价评分
     */
    private Integer pjpf;
    /**
     *服务评分
     */
    private Integer fwpf;
    /**
     *商品评分
     */
    private Integer sppf;
    /**
     *物流评分
     */
    private Integer wlpf;
    /**
     * 是否匿名评价 0->否1 ->是
     */
    private Integer isAnonymous;

    private String attr;

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getPjpf() {
        return pjpf;
    }

    public void setPjpf(Integer pjpf) {
        this.pjpf = pjpf;
    }

    public Integer getFwpf() {
        return fwpf;
    }

    public void setFwpf(Integer fwpf) {
        this.fwpf = fwpf;
    }

    public Integer getSppf() {
        return sppf;
    }

    public void setSppf(Integer sppf) {
        this.sppf = sppf;
    }

    public Integer getWlpf() {
        return wlpf;
    }

    public void setWlpf(Integer wlpf) {
        this.wlpf = wlpf;
    }

    public Integer getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Integer isAnonymous) {
        this.isAnonymous = isAnonymous;
    }
}
