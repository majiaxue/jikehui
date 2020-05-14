package com.example.bean;

/**
 * Created by cuihaohao on 2019/6/13
 * Describe:
 */
public class SellerVo {

    /**
     * 用户ID
     */
    private String userCode;

    /**
     * LOGO
     */
    private String sellerLogo;

    /**
     * 店铺名称
     */
    private String sellerShopName;

    /**
     * 商家类型
     */
    private String sellerType;
    /**
     * 商家分类（商家分类code）
     */
    private String sellerCategory;

    /**
     * 商家姓名（经营者姓名）
     */
    private String sellerName;
    /**
     * 商家联系方式（经营者联系方式）
     */
    private String sellerPhone;
    /**
     * 商家地址（真实地址）
     */
    private String sellerAddredd;

    /**
     * 身份证正面
     */
    private String sellerIdPositiveCardUrl;
    /**
     * 身份证背面
     */
    private String sellerIdBackCardUrl;
    /**
     * 营业执照
     */
    private String sellerBusinessLicenseUrl;
    /**
     * 食品安全许可证
     */
    private String sellerFoodSafetyPermitUrl;

    //SellerLon
    private String sellerLon;

    private String sellerLat;

    public String getSellerLon() {
        return sellerLon;
    }

    public void setSellerLon(String sellerLon) {
        this.sellerLon = sellerLon;
    }

    public String getSellerLat() {
        return sellerLat;
    }

    public void setSellerLat(String sellerLat) {
        this.sellerLat = sellerLat;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getSellerShopName() {
        return sellerShopName;
    }

    public void setSellerShopName(String sellerShopName) {
        this.sellerShopName = sellerShopName;
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public String getSellerCategory() {
        return sellerCategory;
    }

    public void setSellerCategory(String sellerCategory) {
        this.sellerCategory = sellerCategory;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getSellerAddredd() {
        return sellerAddredd;
    }

    public void setSellerAddredd(String sellerAddredd) {
        this.sellerAddredd = sellerAddredd;
    }

    public String getSellerLogo() {
        return sellerLogo;
    }

    public void setSellerLogo(String sellerLogo) {
        this.sellerLogo = sellerLogo;
    }

    public String getSellerIdPositiveCardUrl() {
        return sellerIdPositiveCardUrl;
    }

    public void setSellerIdPositiveCardUrl(String sellerIdPositiveCardUrl) {
        this.sellerIdPositiveCardUrl = sellerIdPositiveCardUrl;
    }

    public String getSellerIdBackCardUrl() {
        return sellerIdBackCardUrl;
    }

    public void setSellerIdBackCardUrl(String sellerIdBackCardUrl) {
        this.sellerIdBackCardUrl = sellerIdBackCardUrl;
    }

    public String getSellerBusinessLicenseUrl() {
        return sellerBusinessLicenseUrl;
    }

    public void setSellerBusinessLicenseUrl(String sellerBusinessLicenseUrl) {
        this.sellerBusinessLicenseUrl = sellerBusinessLicenseUrl;
    }

    public String getSellerFoodSafetyPermitUrl() {
        return sellerFoodSafetyPermitUrl;
    }

    public void setSellerFoodSafetyPermitUrl(String sellerFoodSafetyPermitUrl) {
        this.sellerFoodSafetyPermitUrl = sellerFoodSafetyPermitUrl;
    }
}
