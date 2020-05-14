package com.example.bean;

import com.google.gson.annotations.SerializedName;

public class WeChatPayBean {

    /**
     * appid : wxf08fd2965ac9ac30
     * partnerid : 1538482081
     * noncestr : M4YghaWrBose6Ary
     * package : prepayid=wx12115034537217c57b54198d1955018100
     * prepayid : wx12115034537217c57b54198d1955018100
     * sign : BCEABED5F550DAF0E76F2C2EC8852F25
     * signType : MD5
     * timestamp : 1562903434
     */

    private String appid;
    private String partnerid;
    private String noncestr;
    @SerializedName("package")
    private String package_x;
    private String prepayid;
    private String sign;
    private String signType;
    private String timestamp;
    private String levelOrderSn;

    public String getLevelOrderSn() {
        return levelOrderSn;
    }

    public void setLevelOrderSn(String levelOrderSn) {
        this.levelOrderSn = levelOrderSn;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackage_x() {
        return package_x;
    }

    public void setPackage_x(String package_x) {
        this.package_x = package_x;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "WeChatPayBean{" +
                "appid='" + appid + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", package_x='" + package_x + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", sign='" + sign + '\'' +
                ", signType='" + signType + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", levelOrderSn='" + levelOrderSn + '\'' +
                '}';
    }
}
