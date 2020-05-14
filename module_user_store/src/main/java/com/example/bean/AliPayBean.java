package com.example.bean;

public class AliPayBean {

    /**
     * code : null
     * msg : null
     * subCode : null
     * subMsg : null
     * body : alipay_sdk=alipay-sdk-java-3.6.0.ALL&app_id=2019051064463082&biz_content=%7B%22body%22%3A%22%E6%B5%8B%E8%AF%95%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%222.0%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=%E5%95%86%E6%88%B7%E5%A4%96%E7%BD%91%E5%8F%AF%E4%BB%A5%E8%AE%BF%E9%97%AE%E7%9A%84%E5%BC%82%E6%AD%A5%E5%9C%B0%E5%9D%80&sign=Eu5yWEarKTdM1msD1ceNeImUWN%2B5lrdr670TlUOJuZzwMHFS%2BSgWGD%2FOMItevwIMR1Ri5NDTr%2FKjezU9lwO9ZjHFFUcab8LIHha9oj8SdAYZD2PDhoEwGLWTPMiPkf9Z2uELa5hxhkUs9k7o4haUK5vjrh5ONot6m20SIazySbejIH3t00Wjbg0kWOerN0OZSU7XDTXNdkD%2FNn6CsUIxc7aYMpmYPCL0hLpqJBnTu0q7ZiYZe%2F5HDM2D29IKbQarSba3%2FqBg9br5UCOKP8XAiiYxGd0yrm49Pj9GrfF9jkrDYqseIMVLyV9IZoMC4AFGzFUX2Tw5BegawuSRc9vMsw%3D%3D&sign_type=RSA2&timestamp=2019-06-11+14%3A48%3A48&version=1.0
     * params : null
     * outTradeNo : null
     * sellerId : null
     * totalAmount : null
     * tradeNo : null
     * errorCode : null
     * success : true
     */

    private String code;
    private String msg;
    private String subCode;
    private String subMsg;
    private String body;
    private String params;
    private String outTradeNo;
    private String sellerId;
    private String totalAmount;
    private String tradeNo;
    private String errorCode;
    private boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
