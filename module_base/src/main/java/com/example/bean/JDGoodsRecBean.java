package com.example.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cuihaohao on 2019/6/15
 * Describe:
 */
public class JDGoodsRecBean implements Serializable {


    /**
     * code : 200
     * data : {"lists":[{"brandCode":"13392","brandName":"尼康（Nikon）","categoryInfo":{"cid1":652,"cid1Name":"数码","cid2":654,"cid2Name":"摄影摄像","cid3":832,"cid3Name":"单反相机"},"comments":21,"commissionInfo":{"commission":16,"commissionShare":0.1},"couponInfo":{"couponList":[{"bindType":2,"discount":50,"getEndTime":1561024560000,"getStartTime":1559296500000,"link":"http://coupon.jd.com/ilink/couponActiveFront/front_index.action?key=e2c4f79e2e8c4f17a335a366f6d7e550&roleId=20191949&to=aishenbo.jd.com","platformType":0,"quota":6180,"useEndTime":1561996799000,"useStartTime":1559232000000},{"bindType":2,"discount":20,"getEndTime":1561046340000,"getStartTime":1559296500000,"link":"http://coupon.jd.com/ilink/couponActiveFront/front_index.action?key=89acc6c43a924763a8d2c3d7e233ae3b&roleId=20191901&to=aishenbo.jd.com","platformType":0,"quota":1680,"useEndTime":1561046399000,"useStartTime":1559232000000}]},"goodCommentsShare":100,"imageInfo":{"imageList":[{"url":"http://img14.360buyimg.com/ads/jfs/t1/69610/27/1243/400030/5cf88682E13a4458a/739dbebb44e2a8f5.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t2593/182/1605363392/191655/f400beae/5743e96bNd5aa5998.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t28627/49/495317597/358159/575b06f9/5bf518d8Nb2330fba.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t4549/10/1808362740/361562/75e22b05/58e6056bNf2f794b2.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/5519/30/7606/368543/5ba65f32Eef42aab4/b0c99bcbb22ffd80.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t16510/284/1074443450/237465/39986fbf/5a4aed91N7bc0bd40.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t26275/107/1971176156/469516/ca54f546/5bf4cd67N935852ba.jpg"}]},"inOrderComm30Days":85.67,"inOrderCount30Days":9,"isHot":0,"isJdSale":0,"materialUrl":"item.jd.com/1725179436.html","owner":"p","pinGouInfo":[],"pingGouInfo":[],"priceInfo":{"price":15999},"shopInfo":{"shopId":121268,"shopName":"爱深博影像旗舰店"},"skuId":1725179436,"skuName":"尼康（Nikon）Df全画幅数码复古单反照相机旅游风景拍摄 尼康50/1.8G镜头(黑色套机)","spuid":1725179429}],"totalCount":30}
     * msg : 数据获取成功.
     */

    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean implements Serializable {
        /**
         * lists : [{"brandCode":"13392","brandName":"尼康（Nikon）","categoryInfo":{"cid1":652,"cid1Name":"数码","cid2":654,"cid2Name":"摄影摄像","cid3":832,"cid3Name":"单反相机"},"comments":21,"commissionInfo":{"commission":16,"commissionShare":0.1},"couponInfo":{"couponList":[{"bindType":2,"discount":50,"getEndTime":1561024560000,"getStartTime":1559296500000,"link":"http://coupon.jd.com/ilink/couponActiveFront/front_index.action?key=e2c4f79e2e8c4f17a335a366f6d7e550&roleId=20191949&to=aishenbo.jd.com","platformType":0,"quota":6180,"useEndTime":1561996799000,"useStartTime":1559232000000},{"bindType":2,"discount":20,"getEndTime":1561046340000,"getStartTime":1559296500000,"link":"http://coupon.jd.com/ilink/couponActiveFront/front_index.action?key=89acc6c43a924763a8d2c3d7e233ae3b&roleId=20191901&to=aishenbo.jd.com","platformType":0,"quota":1680,"useEndTime":1561046399000,"useStartTime":1559232000000}]},"goodCommentsShare":100,"imageInfo":{"imageList":[{"url":"http://img14.360buyimg.com/ads/jfs/t1/69610/27/1243/400030/5cf88682E13a4458a/739dbebb44e2a8f5.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t2593/182/1605363392/191655/f400beae/5743e96bNd5aa5998.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t28627/49/495317597/358159/575b06f9/5bf518d8Nb2330fba.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t4549/10/1808362740/361562/75e22b05/58e6056bNf2f794b2.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/5519/30/7606/368543/5ba65f32Eef42aab4/b0c99bcbb22ffd80.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t16510/284/1074443450/237465/39986fbf/5a4aed91N7bc0bd40.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t26275/107/1971176156/469516/ca54f546/5bf4cd67N935852ba.jpg"}]},"inOrderComm30Days":85.67,"inOrderCount30Days":9,"isHot":0,"isJdSale":0,"materialUrl":"item.jd.com/1725179436.html","owner":"p","pinGouInfo":[],"pingGouInfo":[],"priceInfo":{"price":15999},"shopInfo":{"shopId":121268,"shopName":"爱深博影像旗舰店"},"skuId":1725179436,"skuName":"尼康（Nikon）Df全画幅数码复古单反照相机旅游风景拍摄 尼康50/1.8G镜头(黑色套机)","spuid":1725179429}]
         * totalCount : 30
         */

        private String totalCount;
        private List<ListsBean> lists;

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean implements Serializable {
            /**
             * brandCode : 13392
             * brandName : 尼康（Nikon）
             * categoryInfo : {"cid1":652,"cid1Name":"数码","cid2":654,"cid2Name":"摄影摄像","cid3":832,"cid3Name":"单反相机"}
             * comments : 21
             * commissionInfo : {"commission":16,"commissionShare":0.1}
             * couponInfo : {"couponList":[{"bindType":2,"discount":50,"getEndTime":1561024560000,"getStartTime":1559296500000,"link":"http://coupon.jd.com/ilink/couponActiveFront/front_index.action?key=e2c4f79e2e8c4f17a335a366f6d7e550&roleId=20191949&to=aishenbo.jd.com","platformType":0,"quota":6180,"useEndTime":1561996799000,"useStartTime":1559232000000},{"bindType":2,"discount":20,"getEndTime":1561046340000,"getStartTime":1559296500000,"link":"http://coupon.jd.com/ilink/couponActiveFront/front_index.action?key=89acc6c43a924763a8d2c3d7e233ae3b&roleId=20191901&to=aishenbo.jd.com","platformType":0,"quota":1680,"useEndTime":1561046399000,"useStartTime":1559232000000}]}
             * goodCommentsShare : 100
             * imageInfo : {"imageList":[{"url":"http://img14.360buyimg.com/ads/jfs/t1/69610/27/1243/400030/5cf88682E13a4458a/739dbebb44e2a8f5.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t2593/182/1605363392/191655/f400beae/5743e96bNd5aa5998.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t28627/49/495317597/358159/575b06f9/5bf518d8Nb2330fba.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t4549/10/1808362740/361562/75e22b05/58e6056bNf2f794b2.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t1/5519/30/7606/368543/5ba65f32Eef42aab4/b0c99bcbb22ffd80.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t16510/284/1074443450/237465/39986fbf/5a4aed91N7bc0bd40.jpg"},{"url":"http://img14.360buyimg.com/ads/jfs/t26275/107/1971176156/469516/ca54f546/5bf4cd67N935852ba.jpg"}]}
             * inOrderComm30Days : 85.67
             * inOrderCount30Days : 9
             * isHot : 0
             * isJdSale : 0
             * materialUrl : item.jd.com/1725179436.html
             * owner : p
             * pinGouInfo : []
             * pingGouInfo : []
             * priceInfo : {"price":15999}
             * shopInfo : {"shopId":121268,"shopName":"爱深博影像旗舰店"}
             * skuId : 1725179436
             * skuName : 尼康（Nikon）Df全画幅数码复古单反照相机旅游风景拍摄 尼康50/1.8G镜头(黑色套机)
             * spuid : 1725179429
             */

            private String brandCode;
            private String brandName;
            private CategoryInfoBean categoryInfo;
            private String comments;
            private CommissionInfoBean commissionInfo;
            private CouponInfoBean couponInfo;
            private String goodCommentsShare;
            private ImageInfoBean imageInfo;
            private double inOrderComm30Days;
            private String inOrderCount30Days;
            private String isHot;
            private String isJdSale;
            private String materialUrl;
            private String owner;
            private PriceInfoBean priceInfo;
            private ShopInfoBean shopInfo;
            private String skuId;
            private String skuName;
            private String spuid;
            private List pinGouInfo;
            private List pingGouInfo;

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

            public CategoryInfoBean getCategoryInfo() {
                return categoryInfo;
            }

            public void setCategoryInfo(CategoryInfoBean categoryInfo) {
                this.categoryInfo = categoryInfo;
            }

            public String getComments() {
                return comments;
            }

            public void setComments(String comments) {
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

            public String getGoodCommentsShare() {
                return goodCommentsShare;
            }

            public void setGoodCommentsShare(String goodCommentsShare) {
                this.goodCommentsShare = goodCommentsShare;
            }

            public ImageInfoBean getImageInfo() {
                return imageInfo;
            }

            public void setImageInfo(ImageInfoBean imageInfo) {
                this.imageInfo = imageInfo;
            }

            public double getInOrderComm30Days() {
                return inOrderComm30Days;
            }

            public void setInOrderComm30Days(double inOrderComm30Days) {
                this.inOrderComm30Days = inOrderComm30Days;
            }

            public String getInOrderCount30Days() {
                return inOrderCount30Days;
            }

            public void setInOrderCount30Days(String inOrderCount30Days) {
                this.inOrderCount30Days = inOrderCount30Days;
            }

            public String getIsHot() {
                return isHot;
            }

            public void setIsHot(String isHot) {
                this.isHot = isHot;
            }

            public String getIsJdSale() {
                return isJdSale;
            }

            public void setIsJdSale(String isJdSale) {
                this.isJdSale = isJdSale;
            }

            public String getMaterialUrl() {
                return materialUrl;
            }

            public void setMaterialUrl(String materialUrl) {
                this.materialUrl = materialUrl;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
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

            public String getSkuId() {
                return skuId;
            }

            public void setSkuId(String skuId) {
                this.skuId = skuId;
            }

            public String getSkuName() {
                return skuName;
            }

            public void setSkuName(String skuName) {
                this.skuName = skuName;
            }

            public String getSpuid() {
                return spuid;
            }

            public void setSpuid(String spuid) {
                this.spuid = spuid;
            }

            public List getPinGouInfo() {
                return pinGouInfo;
            }

            public void setPinGouInfo(List pinGouInfo) {
                this.pinGouInfo = pinGouInfo;
            }

            public List getPingGouInfo() {
                return pingGouInfo;
            }

            public void setPingGouInfo(List pingGouInfo) {
                this.pingGouInfo = pingGouInfo;
            }

            public static class CategoryInfoBean implements Serializable {
                /**
                 * cid1 : 652
                 * cid1Name : 数码
                 * cid2 : 654
                 * cid2Name : 摄影摄像
                 * cid3 : 832
                 * cid3Name : 单反相机
                 */

                private String cid1;
                private String cid1Name;
                private String cid2;
                private String cid2Name;
                private String cid3;
                private String cid3Name;

                public String getCid1() {
                    return cid1;
                }

                public void setCid1(String cid1) {
                    this.cid1 = cid1;
                }

                public String getCid1Name() {
                    return cid1Name;
                }

                public void setCid1Name(String cid1Name) {
                    this.cid1Name = cid1Name;
                }

                public String getCid2() {
                    return cid2;
                }

                public void setCid2(String cid2) {
                    this.cid2 = cid2;
                }

                public String getCid2Name() {
                    return cid2Name;
                }

                public void setCid2Name(String cid2Name) {
                    this.cid2Name = cid2Name;
                }

                public String getCid3() {
                    return cid3;
                }

                public void setCid3(String cid3) {
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
                            "cid1='" + cid1 + '\'' +
                            ", cid1Name='" + cid1Name + '\'' +
                            ", cid2='" + cid2 + '\'' +
                            ", cid2Name='" + cid2Name + '\'' +
                            ", cid3='" + cid3 + '\'' +
                            ", cid3Name='" + cid3Name + '\'' +
                            '}';
                }
            }


            public static class CommissionInfoBean implements Serializable {
                /**
                 * commission : 16
                 * commissionShare : 0.1
                 */

                private String commission;
                private String commissionShare;

                public String getCommission() {
                    return commission;
                }

                public void setCommission(String commission) {
                    this.commission = commission;
                }

                public String getCommissionShare() {
                    return commissionShare;
                }

                public void setCommissionShare(String commissionShare) {
                    this.commissionShare = commissionShare;
                }

                @Override
                public String toString() {
                    return "CommissionInfoBean{" +
                            "commission='" + commission + '\'' +
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

                public static class CouponListBean implements Serializable {
                    /**
                     * bindType : 2
                     * discount : 50
                     * getEndTime : 1561024560000
                     * getStartTime : 1559296500000
                     * link : http://coupon.jd.com/ilink/couponActiveFront/front_index.action?key=e2c4f79e2e8c4f17a335a366f6d7e550&roleId=20191949&to=aishenbo.jd.com
                     * platformType : 0
                     * quota : 6180
                     * useEndTime : 1561996799000
                     * useStartTime : 1559232000000
                     */

                    private String bindType;
                    private String discount;
                    private long getEndTime;
                    private long getStartTime;
                    private String isBest;
                    private String link;
                    private String platformType;
                    private String quota;
                    private long useEndTime;
                    private long useStartTime;

                    public String getIsBest() {
                        return isBest;
                    }

                    public void setIsBest(String isBest) {
                        this.isBest = isBest;
                    }

                    public String getBindType() {
                        return bindType;
                    }

                    public void setBindType(String bindType) {
                        this.bindType = bindType;
                    }

                    public String getDiscount() {
                        return discount;
                    }

                    public void setDiscount(String discount) {
                        this.discount = discount;
                    }

                    public long getGetEndTime() {
                        return getEndTime;
                    }

                    public void setGetEndTime(long getEndTime) {
                        this.getEndTime = getEndTime;
                    }

                    public long getGetStartTime() {
                        return getStartTime;
                    }

                    public void setGetStartTime(long getStartTime) {
                        this.getStartTime = getStartTime;
                    }

                    public String getLink() {
                        return link;
                    }

                    public void setLink(String link) {
                        this.link = link;
                    }

                    public String getPlatformType() {
                        return platformType;
                    }

                    public void setPlatformType(String platformType) {
                        this.platformType = platformType;
                    }

                    public String getQuota() {
                        return quota;
                    }

                    public void setQuota(String quota) {
                        this.quota = quota;
                    }

                    public long getUseEndTime() {
                        return useEndTime;
                    }

                    public void setUseEndTime(long useEndTime) {
                        this.useEndTime = useEndTime;
                    }

                    public long getUseStartTime() {
                        return useStartTime;
                    }

                    public void setUseStartTime(long useStartTime) {
                        this.useStartTime = useStartTime;
                    }

                    @Override
                    public String toString() {
                        return "CouponListBean{" +
                                "bindType='" + bindType + '\'' +
                                ", discount='" + discount + '\'' +
                                ", getEndTime=" + getEndTime +
                                ", getStartTime=" + getStartTime +
                                ", isBest='" + isBest + '\'' +
                                ", link='" + link + '\'' +
                                ", platformType='" + platformType + '\'' +
                                ", quota='" + quota + '\'' +
                                ", useEndTime=" + useEndTime +
                                ", useStartTime=" + useStartTime +
                                '}';
                    }
                }

                @Override
                public String toString() {
                    return "CouponInfoBean{" +
                            "couponList=" + couponList +
                            '}';
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

                public static class ImageListBean implements Serializable {
                    /**
                     * url : http://img14.360buyimg.com/ads/jfs/t1/69610/27/1243/400030/5cf88682E13a4458a/739dbebb44e2a8f5.jpg
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

                @Override
                public String toString() {
                    return "ImageInfoBean{" +
                            "imageList=" + imageList +
                            '}';
                }
            }

            public static class PriceInfoBean implements Serializable {
                /**
                 * price : 15999
                 */

                private String price;
                private String lowestPrice;
                private String lowestPriceType;

                public String getLowestPrice() {
                    return lowestPrice;
                }

                public void setLowestPrice(String lowestPrice) {
                    this.lowestPrice = lowestPrice;
                }

                public String getLowestPriceType() {
                    return lowestPriceType;
                }

                public void setLowestPriceType(String lowestPriceType) {
                    this.lowestPriceType = lowestPriceType;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                @Override
                public String toString() {
                    return "PriceInfoBean{" +
                            "price='" + price + '\'' +
                            ", lowestPrice='" + lowestPrice + '\'' +
                            ", lowestPriceType='" + lowestPriceType + '\'' +
                            '}';
                }
            }

            public static class ShopInfoBean implements Serializable {
                /**
                 * shopId : 121268
                 * shopName : 爱深博影像旗舰店
                 */

                private String shopId;
                private String shopName;

                public String getShopId() {
                    return shopId;
                }

                public void setShopId(String shopId) {
                    this.shopId = shopId;
                }

                public String getShopName() {
                    return shopName;
                }

                public void setShopName(String shopName) {
                    this.shopName = shopName;
                }

                @Override
                public String toString() {
                    return "ShopInfoBean{" +
                            "shopId='" + shopId + '\'' +
                            ", shopName='" + shopName + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "ListsBean{" +
                        "brandCode='" + brandCode + '\'' +
                        ", brandName='" + brandName + '\'' +
                        ", categoryInfo=" + categoryInfo +
                        ", comments='" + comments + '\'' +
                        ", commissionInfo=" + commissionInfo +
                        ", couponInfo=" + couponInfo +
                        ", goodCommentsShare='" + goodCommentsShare + '\'' +
                        ", imageInfo=" + imageInfo +
                        ", inOrderComm30Days=" + inOrderComm30Days +
                        ", inOrderCount30Days='" + inOrderCount30Days + '\'' +
                        ", isHot='" + isHot + '\'' +
                        ", isJdSale='" + isJdSale + '\'' +
                        ", materialUrl='" + materialUrl + '\'' +
                        ", owner='" + owner + '\'' +
                        ", priceInfo=" + priceInfo +
                        ", shopInfo=" + shopInfo +
                        ", skuId='" + skuId + '\'' +
                        ", skuName='" + skuName + '\'' +
                        ", spuid='" + spuid + '\'' +
                        ", pinGouInfo=" + pinGouInfo +
                        ", pingGouInfo=" + pingGouInfo +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "totalCount='" + totalCount + '\'' +
                    ", lists=" + lists +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "JDGoodsRecBean{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
