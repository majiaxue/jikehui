package com.example.bean;

public class MyCollectBean {

    /**
     * goodsName : 白色t恤女短袖修身夏装2019新款黑色圆领紧身纯色上衣显瘦打底衫
     * normalPrice : 29.9
     * groupPrice : null
     * quantity : 5158
     * image : //img.alicdn.com/imgextra/i1/4028944728/O1CN01ygmWlL1knT8UBRAHk_!!0-item_pic.jpg
     * type : 0
     * sellerId : 秘印旗舰店
     * sellerName : null
     * goodsId : 587883653347
     */

    private String goodsName;
    private String normalPrice;
    private Double groupPrice;
    private String quantity;
    private String image;
    private int type;
    private String sellerId;
    private String sellerName;
    private long goodsId;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(String normalPrice) {
        this.normalPrice = normalPrice;
    }

    public Double getGroupPrice() {
        return groupPrice;
    }

    public void setGroupPrice(Double groupPrice) {
        this.groupPrice = groupPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "MyCollectBean{" +
                "goodsName='" + goodsName + '\'' +
                ", normalPrice=" + normalPrice +
                ", groupPrice=" + groupPrice +
                ", quantity='" + quantity + '\'' +
                ", image='" + image + '\'' +
                ", type=" + type +
                ", sellerId='" + sellerId + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", goodsId=" + goodsId +
                ", isCheck=" + isCheck +
                '}';
    }
}
