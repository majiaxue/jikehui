package com.example.bean;

import java.io.Serializable;
import java.util.List;

public class JDListBean implements Serializable {

    /**
     * sysRequestUrl : https://router.jd.com/api?app_key=3c9be283d7d9e0e1529463ff7cc61398&method=jd.union.open.goods.query&v=1.0&format=json&sign_method=md5&sign=FAC45352B087B68F8ADC599AC0BB23AA&timestamp=2019-08-26+14%3A27%3A27&param_json={"goodsReqDTO":{"cid1":652,"pageIndex":1,"pageSize":3,"keyword":""}}
     * sysOriginalMsg : {"jd_union_open_goods_query_response":{"result":"{\"code\":200,\"data\":[{\"brandCode\":\"346688\",\"brandName\":\"五度音（wuden）\",\"categoryInfo\":{\"cid1\":652,\"cid1Name\":\"数码\",\"cid2\":828,\"cid2Name\":\"影音娱乐\",\"cid3\":842,\"cid3Name\":\"耳机/耳麦\"},\"comments\":22274,\"commissionInfo\":{\"commission\":9.95,\"commissionShare\":50.0},\"couponInfo\":{\"couponList\":[{\"bindType\":3,\"discount\":10.0,\"getEndTime\":1567075920000,\"getStartTime\":1564397460000,\"isBest\":1,\"link\":\"http://coupon.jd.com/ilink/couponActiveFront/front_index.action?key=4ab977bb783b46c3bda0feca1d70a8c6&roleId=21767624&to=wuden.jd.com\",\"platformType\":0,\"quota\":19.0,\"useEndTime\":1567094399000,\"useStartTime\":1564329600000}]},\"goodCommentsShare\":96.0,\"imageInfo\":{\"imageList\":[{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/16612/13/5489/252185/5c3f429eE889d2f65/a3c4cacb0f4a0ba1.jpg\"},{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/22793/9/6862/221527/5c641e80Ec5a99852/075c28de6250611d.jpg\"},{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/19104/37/6860/142481/5c642010Eab961904/7fefc5e19246de4d.jpg\"},{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/14740/3/5522/81795/5c3f429dEfdc0bb32/0534b6dbf0e80d99.jpg\"},{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/14814/4/5608/83919/5c3f429eEce497c89/cdf0bf39efd5608c.jpg\"},{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/7281/38/13170/91247/5c3f429eEef7e2f8e/d84fa7096a221949.jpg\"},{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/11799/15/6579/93222/5c3f429eE257eaa91/48531de750205e1d.jpg\"}]},\"inOrderComm30Days\":22213.85,\"inOrderCount30Days\":4223,\"isHot\":1,\"isJdSale\":0,\"materialUrl\":\"item.jd.com/41265992595.html\",\"owner\":\"p\",\"pinGouInfo\":{},\"pingGouInfo\":{},\"priceInfo\":{\"lowestPrice\":19.9,\"lowestPriceType\":1,\"price\":19.9},\"shopInfo\":{\"shopId\":807024,\"shopName\":\"五度音旗舰店\"},\"skuId\":41265992595,\"skuName\":\"五度音（wuden） 耳机入耳式有线 运动音乐游戏吃鸡魔音K歌苹果华为OPPO小米vivo手机通用 黑色-线控带麦\",\"spuid\":41265992595},{\"brandCode\":\"14026\",\"brandName\":\"Apple\",\"categoryInfo\":{\"cid1\":652,\"cid1Name\":\"数码\",\"cid2\":981,\"cid2Name\":\"虚拟商品\",\"cid3\":982,\"cid3Name\":\"延保服务\"},\"comments\":5913,\"commissionInfo\":{\"commission\":0.4,\"commissionShare\":0.6},\"couponInfo\":{\"couponList\":[]},\"goodCommentsShare\":100.0,\"imageInfo\":{\"imageList\":[{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/44709/31/3428/121681/5d132a38Eb840cb4e/ed899bf431449d23.jpg\"},{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/35890/39/13239/97212/5d132a39E99d45950/cae92351678bf18b.jpg\"},{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/76212/2/2956/61668/5d132a39E8bf8411c/6a4bc289c6c7c6fd.jpg\"},{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/37173/19/13100/97232/5d132a3aE2685aea9/38c95b67d857b6e7.jpg\"},{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/36295/18/11852/127559/5d132a39E81a165ba/7322cf0529599e02.jpg\"}]},\"inOrderComm30Days\":3037.27,\"inOrderCount30Days\":7849,\"isHot\":0,\"isJdSale\":1,\"materialUrl\":\"item.jd.com/100006452480.html\",\"owner\":\"g\",\"pinGouInfo\":{},\"pingGouInfo\":{},\"priceInfo\":{\"lowestPrice\":67.28,\"lowestPriceType\":1,\"price\":67.28},\"shopInfo\":{\"shopId\":0,\"shopName\":\"\"},\"skuId\":100006452480,\"skuName\":\"【月付】适用于iPhone XS、 XS Max、 X的换修无忧服务\",\"spuid\":100006452480},{\"brandCode\":\"15581\",\"brandName\":\"声缪斯（SONMUSE）\",\"categoryInfo\":{\"cid1\":652,\"cid1Name\":\"数码\",\"cid2\":828,\"cid2Name\":\"影音娱乐\",\"cid3\":5270,\"cid3Name\":\"苹果配件\"},\"comments\":346,\"commissionInfo\":{\"commission\":26.97,\"commissionShare\":30.0},\"couponInfo\":{\"couponList\":[{\"bindType\":3,\"discount\":60.0,\"getEndTime\":1566921540000,\"getStartTime\":1566489600000,\"isBest\":1,\"link\":\"http://coupon.m.jd.com/coupons/show.action?key=7e51f428474346e5af235a7a897cce6e&roleId=22386622&to=item.jd.com/53600393449.html\",\"platformType\":0,\"quota\":89.0,\"useEndTime\":1566921599000,\"useStartTime\":1566489600000},{\"bindType\":3,\"discount\":50.0,\"getEndTime\":1569806400000,\"getStartTime\":1564449540000,\"isBest\":0,\"link\":\"http://coupon.jd.com/ilink/couponActiveFront/front_index.action?key=c7b15580163d481d80b0f1d84be2e9f3&roleId=21772789&to=item.jd.com/53600393449.html\",\"platformType\":0,\"quota\":88.0,\"useEndTime\":1569859199000,\"useStartTime\":1564416000000}]},\"goodCommentsShare\":98.0,\"imageInfo\":{\"imageList\":[{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/62269/1/6210/42432/5d44e39cEf06a360c/4b8e91bb35bf5165.jpg\"},{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/56911/5/8380/124875/5d5e1ae4Ecfc6e141/003b35fead57ef4f.jpg\"},{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/38392/28/4293/129901/5cc8432cE967906a6/4a3ec342809c80d0.jpg\"},{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/38727/6/4282/143247/5cc8432cE92f4edb5/cc0b3a709bdead11.jpg\"},{\"url\":\"http://img14.360buyimg.com/ads/jfs/t1/36249/13/7758/220161/5cc8432cE56e078d4/6eb5e65f142c3c5b.jpg\"}]},\"inOrderComm30Days\":5985.68,\"inOrderCount30Days\":443,\"isHot\":1,\"isJdSale\":0,\"materialUrl\":\"item.jd.com/53600393449.html\",\"owner\":\"p\",\"pinGouInfo\":{},\"pingGouInfo\":{},\"priceInfo\":{\"lowestPrice\":89.9,\"lowestPriceType\":1,\"price\":89.9},\"shopInfo\":{\"shopId\":98564,\"shopName\":\"SONMUSE声缪斯旗舰店\"},\"skuId\":53600393449,\"skuName\":\"声缪斯（SONMUSE） tws蓝牙耳机无线双耳迷你运动跑步入耳式智能触控耳机弹窗适用苹果/安卓 精灵标准版 蓝牙5.0+清晰音质+1-2小时听歌\",\"spuid\":46645526819}],\"message\":\"success\",\"requestId\":\"21922_0aae21ea_jzs0rs63_15737631\",\"totalCount\":331301}","code":"0"}}
     * code : 200
     * message : success
     * data : [{"categoryInfo":{"cid1":652,"cid1Name":"数码","cid2":828,"cid2Name":"影音娱乐","cid3":842,"cid3Name":"耳机/耳麦"},"comments":22274,"commissionInfo":{"commission":9.95,"commissionShare":50},"couponInfo":{"couponList":[{"bindType":3,"discount":10,"link":"http://coupon.jd.com/ilink/couponActiveFront/front_index.action?key=4ab977bb783b46c3bda0feca1d70a8c6&roleId=21767624&to=wuden.jd.com","platformType":0,"quota":19,"getStartTime":1564397460000,"getEndTime":1567075920000,"useStartTime":1564329600000,"useEndTime":1567094399000}]},"goodCommentsShare":96,"imageInfo":{"imageList":[{"url":"http://img14.360buyimg.com/ads/jfs/t1/16612/13/5489/252185/5c3f429eE889d2f65/a3c4cacb0f4a0ba1.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/22793/9/6862/221527/5c641e80Ec5a99852/075c28de6250611d.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/19104/37/6860/142481/5c642010Eab961904/7fefc5e19246de4d.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/14740/3/5522/81795/5c3f429dEfdc0bb32/0534b6dbf0e80d99.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/14814/4/5608/83919/5c3f429eEce497c89/cdf0bf39efd5608c.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/7281/38/13170/91247/5c3f429eEef7e2f8e/d84fa7096a221949.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/11799/15/6579/93222/5c3f429eE257eaa91/48531de750205e1d.jpg"}]},"inOrderCount30Days":4223,"isJdSale":0,"materialUrl":"item.jd.com/41265992595.html","priceInfo":{"price":19.9},"shopInfo":{"shopName":"五度音旗舰店","shopId":807024},"skuId":41265992595,"skuName":"五度音（wuden） 耳机入耳式有线 运动音乐游戏吃鸡魔音K歌苹果华为OPPO小米vivo手机通用 黑色-线控带麦","isHot":1,"spuid":41265992595,"brandCode":"346688","brandName":"五度音（wuden）","owner":"p","pingGouInfo":{"pingouPrice":null,"pingouTmCount":null,"pingouUrl":null},"pinGouInfo":{"pingouPrice":null,"pingouTmCount":null,"pingouUrl":null,"pingouStartTime":null,"pingouEndTime":null}},{"categoryInfo":{"cid1":652,"cid1Name":"数码","cid2":981,"cid2Name":"虚拟商品","cid3":982,"cid3Name":"延保服务"},"comments":5913,"commissionInfo":{"commission":0.4,"commissionShare":0.6},"couponInfo":{"couponList":[]},"goodCommentsShare":100,"imageInfo":{"imageList":[{"url":"http://img14.360buyimg.com/ads/jfs/t1/44709/31/3428/121681/5d132a38Eb840cb4e/ed899bf431449d23.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/35890/39/13239/97212/5d132a39E99d45950/cae92351678bf18b.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/76212/2/2956/61668/5d132a39E8bf8411c/6a4bc289c6c7c6fd.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/37173/19/13100/97232/5d132a3aE2685aea9/38c95b67d857b6e7.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/36295/18/11852/127559/5d132a39E81a165ba/7322cf0529599e02.jpg"}]},"inOrderCount30Days":7849,"isJdSale":1,"materialUrl":"item.jd.com/100006452480.html","priceInfo":{"price":67.28},"shopInfo":{"shopName":"","shopId":0},"skuId":100006452480,"skuName":"【月付】适用于iPhone XS、 XS Max、 X的换修无忧服务","isHot":0,"spuid":100006452480,"brandCode":"14026","brandName":"Apple","owner":"g","pingGouInfo":{"pingouPrice":null,"pingouTmCount":null,"pingouUrl":null},"pinGouInfo":{"pingouPrice":null,"pingouTmCount":null,"pingouUrl":null,"pingouStartTime":null,"pingouEndTime":null}},{"categoryInfo":{"cid1":652,"cid1Name":"数码","cid2":828,"cid2Name":"影音娱乐","cid3":5270,"cid3Name":"苹果配件"},"comments":346,"commissionInfo":{"commission":26.97,"commissionShare":30},"couponInfo":{"couponList":[{"bindType":3,"discount":60,"link":"http://coupon.m.jd.com/coupons/show.action?key=7e51f428474346e5af235a7a897cce6e&roleId=22386622&to=item.jd.com/53600393449.html","platformType":0,"quota":89,"getStartTime":1566489600000,"getEndTime":1566921540000,"useStartTime":1566489600000,"useEndTime":1566921599000},{"bindType":3,"discount":50,"link":"http://coupon.jd.com/ilink/couponActiveFront/front_index.action?key=c7b15580163d481d80b0f1d84be2e9f3&roleId=21772789&to=item.jd.com/53600393449.html","platformType":0,"quota":88,"getStartTime":1564449540000,"getEndTime":1569806400000,"useStartTime":1564416000000,"useEndTime":1569859199000}]},"goodCommentsShare":98,"imageInfo":{"imageList":[{"url":"http://img14.360buyimg.com/ads/jfs/t1/62269/1/6210/42432/5d44e39cEf06a360c/4b8e91bb35bf5165.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/56911/5/8380/124875/5d5e1ae4Ecfc6e141/003b35fead57ef4f.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/38392/28/4293/129901/5cc8432cE967906a6/4a3ec342809c80d0.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/38727/6/4282/143247/5cc8432cE92f4edb5/cc0b3a709bdead11.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/36249/13/7758/220161/5cc8432cE56e078d4/6eb5e65f142c3c5b.jpg"}]},"inOrderCount30Days":443,"isJdSale":0,"materialUrl":"item.jd.com/53600393449.html","priceInfo":{"price":89.9},"shopInfo":{"shopName":"SONMUSE声缪斯旗舰店","shopId":98564},"skuId":53600393449,"skuName":"声缪斯（SONMUSE） tws蓝牙耳机无线双耳迷你运动跑步入耳式智能触控耳机弹窗适用苹果/安卓 精灵标准版 蓝牙5.0+清晰音质+1-2小时听歌","isHot":1,"spuid":46645526819,"brandCode":"15581","brandName":"声缪斯（SONMUSE）","owner":"p","pingGouInfo":{"pingouPrice":null,"pingouTmCount":null,"pingouUrl":null},"pinGouInfo":{"pingouPrice":null,"pingouTmCount":null,"pingouUrl":null,"pingouStartTime":null,"pingouEndTime":null}}]
     * totalCount : 331301
     */

    private String sysRequestUrl;
    private String sysOriginalMsg;
    private int code;
    private String message;
    private int totalCount;
    private List<DataBean> data;

    public String getSysRequestUrl() {
        return sysRequestUrl;
    }

    public void setSysRequestUrl(String sysRequestUrl) {
        this.sysRequestUrl = sysRequestUrl;
    }

    public String getSysOriginalMsg() {
        return sysOriginalMsg;
    }

    public void setSysOriginalMsg(String sysOriginalMsg) {
        this.sysOriginalMsg = sysOriginalMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JDListBean{" +
                "sysRequestUrl='" + sysRequestUrl + '\'' +
                ", sysOriginalMsg='" + sysOriginalMsg + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", totalCount=" + totalCount +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Serializable {
        /**
         * categoryInfo : {"cid1":652,"cid1Name":"数码","cid2":828,"cid2Name":"影音娱乐","cid3":842,"cid3Name":"耳机/耳麦"}
         * comments : 22274
         * commissionInfo : {"commission":9.95,"commissionShare":50}
         * couponInfo : {"couponList":[{"bindType":3,"discount":10,"link":"http://coupon.jd.com/ilink/couponActiveFront/front_index.action?key=4ab977bb783b46c3bda0feca1d70a8c6&roleId=21767624&to=wuden.jd.com","platformType":0,"quota":19,"getStartTime":1564397460000,"getEndTime":1567075920000,"useStartTime":1564329600000,"useEndTime":1567094399000}]}
         * goodCommentsShare : 96
         * imageInfo : {"imageList":[{"url":"http://img14.360buyimg.com/ads/jfs/t1/16612/13/5489/252185/5c3f429eE889d2f65/a3c4cacb0f4a0ba1.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/22793/9/6862/221527/5c641e80Ec5a99852/075c28de6250611d.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/19104/37/6860/142481/5c642010Eab961904/7fefc5e19246de4d.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/14740/3/5522/81795/5c3f429dEfdc0bb32/0534b6dbf0e80d99.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/14814/4/5608/83919/5c3f429eEce497c89/cdf0bf39efd5608c.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/7281/38/13170/91247/5c3f429eEef7e2f8e/d84fa7096a221949.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/11799/15/6579/93222/5c3f429eE257eaa91/48531de750205e1d.jpg"}]}
         * inOrderCount30Days : 4223
         * isJdSale : 0
         * materialUrl : item.jd.com/41265992595.html
         * priceInfo : {"price":19.9}
         * shopInfo : {"shopName":"五度音旗舰店","shopId":807024}
         * skuId : 41265992595
         * skuName : 五度音（wuden） 耳机入耳式有线 运动音乐游戏吃鸡魔音K歌苹果华为OPPO小米vivo手机通用 黑色-线控带麦
         * isHot : 1
         * spuid : 41265992595
         * brandCode : 346688
         * brandName : 五度音（wuden）
         * owner : p
         * pingGouInfo : {"pingouPrice":null,"pingouTmCount":null,"pingouUrl":null}
         * pinGouInfo : {"pingouPrice":null,"pingouTmCount":null,"pingouUrl":null,"pingouStartTime":null,"pingouEndTime":null}
         */

        private CategoryInfoBean categoryInfo;
        private int comments;
        private CommissionInfoBean commissionInfo;
        private CouponInfoBean couponInfo;
        private int goodCommentsShare;
        private ImageInfoBean imageInfo;
        private int inOrderCount30Days;
        private int isJdSale;
        private String materialUrl;
        private PriceInfoBean priceInfo;
        private ShopInfoBean shopInfo;
        private long skuId;
        private String skuName;
        private int isHot;
        private long spuid;
        private String brandCode;
        private String brandName;
        private String owner;
        private PingGouInfoBean pingGouInfo;
        private PinGouInfoBean pinGouInfo;

        public CategoryInfoBean getCategoryInfo() {
            return categoryInfo;
        }

        public void setCategoryInfo(CategoryInfoBean categoryInfo) {
            this.categoryInfo = categoryInfo;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public CommissionInfoBean getCommissionInfo() {
            return commissionInfo;
        }

        public void setCommissionInfo(CommissionInfoBean commissionInfo) {
            this.commissionInfo = commissionInfo;
        }

        public CouponInfoBean getCouponInfo() {
            return couponInfo;
        }

        public void setCouponInfo(CouponInfoBean couponInfo) {
            this.couponInfo = couponInfo;
        }

        public int getGoodCommentsShare() {
            return goodCommentsShare;
        }

        public void setGoodCommentsShare(int goodCommentsShare) {
            this.goodCommentsShare = goodCommentsShare;
        }

        public ImageInfoBean getImageInfo() {
            return imageInfo;
        }

        public void setImageInfo(ImageInfoBean imageInfo) {
            this.imageInfo = imageInfo;
        }

        public int getInOrderCount30Days() {
            return inOrderCount30Days;
        }

        public void setInOrderCount30Days(int inOrderCount30Days) {
            this.inOrderCount30Days = inOrderCount30Days;
        }

        public int getIsJdSale() {
            return isJdSale;
        }

        public void setIsJdSale(int isJdSale) {
            this.isJdSale = isJdSale;
        }

        public String getMaterialUrl() {
            return materialUrl;
        }

        public void setMaterialUrl(String materialUrl) {
            this.materialUrl = materialUrl;
        }

        public PriceInfoBean getPriceInfo() {
            return priceInfo;
        }

        public void setPriceInfo(PriceInfoBean priceInfo) {
            this.priceInfo = priceInfo;
        }

        public ShopInfoBean getShopInfo() {
            return shopInfo;
        }

        public void setShopInfo(ShopInfoBean shopInfo) {
            this.shopInfo = shopInfo;
        }

        public long getSkuId() {
            return skuId;
        }

        public void setSkuId(long skuId) {
            this.skuId = skuId;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public long getSpuid() {
            return spuid;
        }

        public void setSpuid(long spuid) {
            this.spuid = spuid;
        }

        public String getBrandCode() {
            return brandCode;
        }

        public void setBrandCode(String brandCode) {
            this.brandCode = brandCode;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public PingGouInfoBean getPingGouInfo() {
            return pingGouInfo;
        }

        public void setPingGouInfo(PingGouInfoBean pingGouInfo) {
            this.pingGouInfo = pingGouInfo;
        }

        public PinGouInfoBean getPinGouInfo() {
            return pinGouInfo;
        }

        public void setPinGouInfo(PinGouInfoBean pinGouInfo) {
            this.pinGouInfo = pinGouInfo;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "categoryInfo=" + categoryInfo +
                    ", comments=" + comments +
                    ", commissionInfo=" + commissionInfo +
                    ", couponInfo=" + couponInfo +
                    ", goodCommentsShare=" + goodCommentsShare +
                    ", imageInfo=" + imageInfo +
                    ", inOrderCount30Days=" + inOrderCount30Days +
                    ", isJdSale=" + isJdSale +
                    ", materialUrl='" + materialUrl + '\'' +
                    ", priceInfo=" + priceInfo +
                    ", shopInfo=" + shopInfo +
                    ", skuId=" + skuId +
                    ", skuName='" + skuName + '\'' +
                    ", isHot=" + isHot +
                    ", spuid=" + spuid +
                    ", brandCode='" + brandCode + '\'' +
                    ", brandName='" + brandName + '\'' +
                    ", owner='" + owner + '\'' +
                    ", pingGouInfo=" + pingGouInfo +
                    ", pinGouInfo=" + pinGouInfo +
                    '}';
        }

        public static class CategoryInfoBean implements Serializable {
            /**
             * cid1 : 652
             * cid1Name : 数码
             * cid2 : 828
             * cid2Name : 影音娱乐
             * cid3 : 842
             * cid3Name : 耳机/耳麦
             */

            private int cid1;
            private String cid1Name;
            private int cid2;
            private String cid2Name;
            private int cid3;
            private String cid3Name;

            public int getCid1() {
                return cid1;
            }

            public void setCid1(int cid1) {
                this.cid1 = cid1;
            }

            public String getCid1Name() {
                return cid1Name;
            }

            public void setCid1Name(String cid1Name) {
                this.cid1Name = cid1Name;
            }

            public int getCid2() {
                return cid2;
            }

            public void setCid2(int cid2) {
                this.cid2 = cid2;
            }

            public String getCid2Name() {
                return cid2Name;
            }

            public void setCid2Name(String cid2Name) {
                this.cid2Name = cid2Name;
            }

            public int getCid3() {
                return cid3;
            }

            public void setCid3(int cid3) {
                this.cid3 = cid3;
            }

            public String getCid3Name() {
                return cid3Name;
            }

            public void setCid3Name(String cid3Name) {
                this.cid3Name = cid3Name;
            }

            @Override
            public String toString() {
                return "CategoryInfoBean{" +
                        "cid1=" + cid1 +
                        ", cid1Name='" + cid1Name + '\'' +
                        ", cid2=" + cid2 +
                        ", cid2Name='" + cid2Name + '\'' +
                        ", cid3=" + cid3 +
                        ", cid3Name='" + cid3Name + '\'' +
                        '}';
            }
        }

        public static class CommissionInfoBean implements Serializable {
            /**
             * commission : 9.95
             * commissionShare : 50
             */

            private double commission;
            private double commissionShare;

            public double getCommission() {
                return commission;
            }

            public void setCommission(double commission) {
                this.commission = commission;
            }

            public double getCommissionShare() {
                return commissionShare;
            }

            public void setCommissionShare(double commissionShare) {
                this.commissionShare = commissionShare;
            }

            @Override
            public String toString() {
                return "CommissionInfoBean{" +
                        "commission=" + commission +
                        ", commissionShare=" + commissionShare +
                        '}';
            }
        }

        public static class CouponInfoBean implements Serializable {
            private List<CouponListBean> couponList;

            public List<CouponListBean> getCouponList() {
                return couponList;
            }

            public void setCouponList(List<CouponListBean> couponList) {
                this.couponList = couponList;
            }

            @Override
            public String toString() {
                return "CouponInfoBean{" +
                        "couponList=" + couponList +
                        '}';
            }

            public static class CouponListBean implements Serializable {
                /**
                 * bindType : 3
                 * discount : 10
                 * link : http://coupon.jd.com/ilink/couponActiveFront/front_index.action?key=4ab977bb783b46c3bda0feca1d70a8c6&roleId=21767624&to=wuden.jd.com
                 * platformType : 0
                 * quota : 19
                 * getStartTime : 1564397460000
                 * getEndTime : 1567075920000
                 * useStartTime : 1564329600000
                 * useEndTime : 1567094399000
                 */

                private int bindType;
                private double discount;
                private String link;
                private int platformType;
                private double quota;
                private long getStartTime;
                private long getEndTime;
                private long useStartTime;
                private long useEndTime;

                public int getBindType() {
                    return bindType;
                }

                public void setBindType(int bindType) {
                    this.bindType = bindType;
                }

                public double getDiscount() {
                    return discount;
                }

                public void setDiscount(double discount) {
                    this.discount = discount;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public int getPlatformType() {
                    return platformType;
                }

                public void setPlatformType(int platformType) {
                    this.platformType = platformType;
                }

                public double getQuota() {
                    return quota;
                }

                public void setQuota(double quota) {
                    this.quota = quota;
                }

                public long getGetStartTime() {
                    return getStartTime;
                }

                public void setGetStartTime(long getStartTime) {
                    this.getStartTime = getStartTime;
                }

                public long getGetEndTime() {
                    return getEndTime;
                }

                public void setGetEndTime(long getEndTime) {
                    this.getEndTime = getEndTime;
                }

                public long getUseStartTime() {
                    return useStartTime;
                }

                public void setUseStartTime(long useStartTime) {
                    this.useStartTime = useStartTime;
                }

                public long getUseEndTime() {
                    return useEndTime;
                }

                public void setUseEndTime(long useEndTime) {
                    this.useEndTime = useEndTime;
                }

                @Override
                public String toString() {
                    return "CouponListBean{" +
                            "bindType=" + bindType +
                            ", discount=" + discount +
                            ", link='" + link + '\'' +
                            ", platformType=" + platformType +
                            ", quota=" + quota +
                            ", getStartTime=" + getStartTime +
                            ", getEndTime=" + getEndTime +
                            ", useStartTime=" + useStartTime +
                            ", useEndTime=" + useEndTime +
                            '}';
                }
            }
        }

        public static class ImageInfoBean implements Serializable {
            private List<ImageListBean> imageList;

            public List<ImageListBean> getImageList() {
                return imageList;
            }

            public void setImageList(List<ImageListBean> imageList) {
                this.imageList = imageList;
            }

            @Override
            public String toString() {
                return "ImageInfoBean{" +
                        "imageList=" + imageList +
                        '}';
            }

            public static class ImageListBean implements Serializable {
                /**
                 * url : http://img14.360buyimg.com/ads/jfs/t1/16612/13/5489/252185/5c3f429eE889d2f65/a3c4cacb0f4a0ba1.jpg
                 */

                private String url;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                @Override
                public String toString() {
                    return "ImageListBean{" +
                            "url='" + url + '\'' +
                            '}';
                }
            }
        }

        public static class PriceInfoBean implements Serializable {
            /**
             * price : 19.9
             */

            private double price;

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            @Override
            public String toString() {
                return "PriceInfoBean{" +
                        "price=" + price +
                        '}';
            }
        }

        public static class ShopInfoBean implements Serializable {
            /**
             * shopName : 五度音旗舰店
             * shopId : 807024
             */

            private String shopName;
            private int shopId;

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            @Override
            public String toString() {
                return "ShopInfoBean{" +
                        "shopName='" + shopName + '\'' +
                        ", shopId=" + shopId +
                        '}';
            }
        }

        public static class PingGouInfoBean implements Serializable {
            /**
             * pingouPrice : null
             * pingouTmCount : null
             * pingouUrl : null
             */

            private String pingouPrice;
            private String pingouTmCount;
            private String pingouUrl;

            public String getPingouPrice() {
                return pingouPrice;
            }

            public void setPingouPrice(String pingouPrice) {
                this.pingouPrice = pingouPrice;
            }

            public String getPingouTmCount() {
                return pingouTmCount;
            }

            public void setPingouTmCount(String pingouTmCount) {
                this.pingouTmCount = pingouTmCount;
            }

            public String getPingouUrl() {
                return pingouUrl;
            }

            public void setPingouUrl(String pingouUrl) {
                this.pingouUrl = pingouUrl;
            }

            @Override
            public String toString() {
                return "PingGouInfoBean{" +
                        "pingouPrice=" + pingouPrice +
                        ", pingouTmCount=" + pingouTmCount +
                        ", pingouUrl=" + pingouUrl +
                        '}';
            }
        }

        public static class PinGouInfoBean implements Serializable {
            /**
             * pingouPrice : null
             * pingouTmCount : null
             * pingouUrl : null
             * pingouStartTime : null
             * pingouEndTime : null
             */

            private String pingouPrice;
            private String pingouTmCount;
            private String pingouUrl;
            private String pingouStartTime;
            private String pingouEndTime;

            public String getPingouPrice() {
                return pingouPrice;
            }

            public void setPingouPrice(String pingouPrice) {
                this.pingouPrice = pingouPrice;
            }

            public String getPingouTmCount() {
                return pingouTmCount;
            }

            public void setPingouTmCount(String pingouTmCount) {
                this.pingouTmCount = pingouTmCount;
            }

            public String getPingouUrl() {
                return pingouUrl;
            }

            public void setPingouUrl(String pingouUrl) {
                this.pingouUrl = pingouUrl;
            }

            public String getPingouStartTime() {
                return pingouStartTime;
            }

            public void setPingouStartTime(String pingouStartTime) {
                this.pingouStartTime = pingouStartTime;
            }

            public String getPingouEndTime() {
                return pingouEndTime;
            }

            public void setPingouEndTime(String pingouEndTime) {
                this.pingouEndTime = pingouEndTime;
            }

            @Override
            public String toString() {
                return "PinGouInfoBean{" +
                        "pingouPrice=" + pingouPrice +
                        ", pingouTmCount=" + pingouTmCount +
                        ", pingouUrl=" + pingouUrl +
                        ", pingouStartTime=" + pingouStartTime +
                        ", pingouEndTime=" + pingouEndTime +
                        '}';
            }
        }
    }
}
