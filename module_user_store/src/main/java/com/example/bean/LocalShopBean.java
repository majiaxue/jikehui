package com.example.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.io.Serializable;
import java.util.List;

public class LocalShopBean extends SimpleBannerInfo implements Serializable {

    /**
     * seller_shop_name : 123123
     * seller_id_back_card_url :
     * distance : 1.2230251618879126E7
     * seller_introduce :
     * seller_food_safety_permit_url : seller-dee17f863fdd481b88aeab99cb7a75f1.png
     * seller_ischeck : true
     * seller_category_name : KTV
     * seller_addredd : 北京市 北京 丰台区 东高地
     * update_time : 2019-07-13 16:16:29
     * user_code :
     * seller_name : 123123
     * seller_type : 1
     * seller_phone : 123123
     * id : 94
     * seller_category : 10004
     * seller_status : true
     * seller_lon : 116.42494202613686
     * seller_business_hours : 18:09:33 - 19:09:33
     * seller_business_license_url :
     * create_time : 2019-07-05 18:09:42
     * star : 4
     * seller_id_positive_card_url :
     * seller_logo : seller-8ca73f2c5b984ed09e011701e85cbf6b.png
     * seller_lat : 39.81223704079177
     * couponList : [{"id":17,"type":1,"name":"2222","platform":1,"count":null,"amount":222,"perLimit":1,"minPoint":22,"startTime":"2019-07-31 00:00:00","endTime":"2019-07-31 00:00:00","useType":0,"note":"222","publishCount":222,"useCount":null,"receiveCount":null,"enableTime":null,"code":null,"memberLevel":null,"sellerId":94,"sellerName":"","goodsId":null,"goodsCategoryId":null}]
     */

    private String seller_shop_name;    //店铺名字
    private String seller_id_back_card_url;
    private String distance;
    private String seller_introduce;
    private String seller_food_safety_permit_url;
    private boolean seller_ischeck;
    private String seller_category_name;
    private String seller_addredd;
    private String update_time;
    private String user_code;
    private String seller_name; //经营者名字
    private String seller_type;
    private String seller_phone;
    private String id;
    private String seller_category;
    private boolean seller_status;
    private String seller_lon;
    private String seller_business_hours;
    private String seller_business_license_url;
    private String create_time;
    private int star;
    private String seller_id_positive_card_url;
    private String seller_logo;
    private String seller_lat;
    private String sellerpics;
    private String pigxx_id;
    private String min_point;
    private String full_reduction_amount;
    private List<UserCouponBean> couponList;

    public String getMin_point() {
        return min_point;
    }

    public void setMin_point(String min_point) {
        this.min_point = min_point;
    }

    public String getFull_reduction_amount() {
        return full_reduction_amount;
    }

    public void setFull_reduction_amount(String full_reduction_amount) {
        this.full_reduction_amount = full_reduction_amount;
    }

    public String getPigxx_id() {
        return pigxx_id;
    }

    public void setPigxx_id(String pigxx_id) {
        this.pigxx_id = pigxx_id;
    }

    public String getSellerpics() {
        return sellerpics;
    }

    public void setSellerpics(String sellerpics) {
        this.sellerpics = sellerpics;
    }

    public String getSeller_shop_name() {
        return seller_shop_name;
    }

    public void setSeller_shop_name(String seller_shop_name) {
        this.seller_shop_name = seller_shop_name;
    }

    public String getSeller_id_back_card_url() {
        return seller_id_back_card_url;
    }

    public void setSeller_id_back_card_url(String seller_id_back_card_url) {
        this.seller_id_back_card_url = seller_id_back_card_url;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSeller_introduce() {
        return seller_introduce;
    }

    public void setSeller_introduce(String seller_introduce) {
        this.seller_introduce = seller_introduce;
    }

    public String getSeller_food_safety_permit_url() {
        return seller_food_safety_permit_url;
    }

    public void setSeller_food_safety_permit_url(String seller_food_safety_permit_url) {
        this.seller_food_safety_permit_url = seller_food_safety_permit_url;
    }

    public boolean isSeller_ischeck() {
        return seller_ischeck;
    }

    public void setSeller_ischeck(boolean seller_ischeck) {
        this.seller_ischeck = seller_ischeck;
    }

    public String getSeller_category_name() {
        return seller_category_name;
    }

    public void setSeller_category_name(String seller_category_name) {
        this.seller_category_name = seller_category_name;
    }

    public String getSeller_addredd() {
        return seller_addredd;
    }

    public void setSeller_addredd(String seller_addredd) {
        this.seller_addredd = seller_addredd;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getSeller_type() {
        return seller_type;
    }

    public void setSeller_type(String seller_type) {
        this.seller_type = seller_type;
    }

    public String getSeller_phone() {
        return seller_phone;
    }

    public void setSeller_phone(String seller_phone) {
        this.seller_phone = seller_phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeller_category() {
        return seller_category;
    }

    public void setSeller_category(String seller_category) {
        this.seller_category = seller_category;
    }

    public boolean isSeller_status() {
        return seller_status;
    }

    public void setSeller_status(boolean seller_status) {
        this.seller_status = seller_status;
    }

    public String getSeller_lon() {
        return seller_lon;
    }

    public void setSeller_lon(String seller_lon) {
        this.seller_lon = seller_lon;
    }

    public String getSeller_business_hours() {
        return seller_business_hours;
    }

    public void setSeller_business_hours(String seller_business_hours) {
        this.seller_business_hours = seller_business_hours;
    }

    public String getSeller_business_license_url() {
        return seller_business_license_url;
    }

    public void setSeller_business_license_url(String seller_business_license_url) {
        this.seller_business_license_url = seller_business_license_url;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getSeller_id_positive_card_url() {
        return seller_id_positive_card_url;
    }

    public void setSeller_id_positive_card_url(String seller_id_positive_card_url) {
        this.seller_id_positive_card_url = seller_id_positive_card_url;
    }

    public String getSeller_logo() {
        return seller_logo;
    }

    public void setSeller_logo(String seller_logo) {
        this.seller_logo = seller_logo;
    }

    public String getSeller_lat() {
        return seller_lat;
    }

    public void setSeller_lat(String seller_lat) {
        this.seller_lat = seller_lat;
    }

    public List<UserCouponBean> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<UserCouponBean> couponList) {
        this.couponList = couponList;
    }

    @Override
    public Object getXBannerUrl() {
        return null;
    }

    public static class CouponListBean implements Serializable {
        /**
         * id : 17
         * type : 1
         * name : 2222
         * platform : 1
         * count : null
         * amount : 222
         * perLimit : 1
         * minPoint : 22
         * startTime : 2019-07-31 00:00:00
         * endTime : 2019-07-31 00:00:00
         * useType : 0
         * note : 222
         * publishCount : 222
         * useCount : null
         * receiveCount : null
         * enableTime : null
         * code : null
         * memberLevel : null
         * sellerId : 94
         * sellerName :
         * goodsId : null
         * goodsCategoryId : null
         */

        private String id;
        private String type;
        private String name;
        private String platform;
        private String count;
        private String amount;
        private String perLimit;
        private String minPoint;
        private String startTime;
        private String endTime;
        private String useType;
        private String note;
        private String publishCount;
        private String useCount;
        private String receiveCount;
        private String enableTime;
        private String code;
        private String memberLevel;
        private String sellerId;
        private String sellerName;
        private String goodsId;
        private String goodsCategoryId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPerLimit() {
            return perLimit;
        }

        public void setPerLimit(String perLimit) {
            this.perLimit = perLimit;
        }

        public String getMinPoint() {
            return minPoint;
        }

        public void setMinPoint(String minPoint) {
            this.minPoint = minPoint;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getUseType() {
            return useType;
        }

        public void setUseType(String useType) {
            this.useType = useType;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getPublishCount() {
            return publishCount;
        }

        public void setPublishCount(String publishCount) {
            this.publishCount = publishCount;
        }

        public String getUseCount() {
            return useCount;
        }

        public void setUseCount(String useCount) {
            this.useCount = useCount;
        }

        public String getReceiveCount() {
            return receiveCount;
        }

        public void setReceiveCount(String receiveCount) {
            this.receiveCount = receiveCount;
        }

        public String getEnableTime() {
            return enableTime;
        }

        public void setEnableTime(String enableTime) {
            this.enableTime = enableTime;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMemberLevel() {
            return memberLevel;
        }

        public void setMemberLevel(String memberLevel) {
            this.memberLevel = memberLevel;
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

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsCategoryId() {
            return goodsCategoryId;
        }

        public void setGoodsCategoryId(String goodsCategoryId) {
            this.goodsCategoryId = goodsCategoryId;
        }

        @Override
        public String toString() {
            return "CouponListBean{" +
                    "id='" + id + '\'' +
                    ", type='" + type + '\'' +
                    ", name='" + name + '\'' +
                    ", platform='" + platform + '\'' +
                    ", count='" + count + '\'' +
                    ", amount='" + amount + '\'' +
                    ", perLimit='" + perLimit + '\'' +
                    ", minPoint='" + minPoint + '\'' +
                    ", startTime='" + startTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    ", useType='" + useType + '\'' +
                    ", note='" + note + '\'' +
                    ", publishCount='" + publishCount + '\'' +
                    ", useCount='" + useCount + '\'' +
                    ", receiveCount='" + receiveCount + '\'' +
                    ", enableTime='" + enableTime + '\'' +
                    ", code='" + code + '\'' +
                    ", memberLevel='" + memberLevel + '\'' +
                    ", sellerId='" + sellerId + '\'' +
                    ", sellerName='" + sellerName + '\'' +
                    ", goodsId='" + goodsId + '\'' +
                    ", goodsCategoryId='" + goodsCategoryId + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LocalShopBean{" +
                "seller_shop_name='" + seller_shop_name + '\'' +
                ", seller_id_back_card_url='" + seller_id_back_card_url + '\'' +
                ", distance='" + distance + '\'' +
                ", seller_introduce='" + seller_introduce + '\'' +
                ", seller_food_safety_permit_url='" + seller_food_safety_permit_url + '\'' +
                ", seller_ischeck=" + seller_ischeck +
                ", seller_category_name='" + seller_category_name + '\'' +
                ", seller_addredd='" + seller_addredd + '\'' +
                ", update_time='" + update_time + '\'' +
                ", user_code='" + user_code + '\'' +
                ", seller_name='" + seller_name + '\'' +
                ", seller_type='" + seller_type + '\'' +
                ", seller_phone='" + seller_phone + '\'' +
                ", id='" + id + '\'' +
                ", seller_category='" + seller_category + '\'' +
                ", seller_status=" + seller_status +
                ", seller_lon='" + seller_lon + '\'' +
                ", seller_business_hours='" + seller_business_hours + '\'' +
                ", seller_business_license_url='" + seller_business_license_url + '\'' +
                ", create_time='" + create_time + '\'' +
                ", star=" + star +
                ", seller_id_positive_card_url='" + seller_id_positive_card_url + '\'' +
                ", seller_logo='" + seller_logo + '\'' +
                ", seller_lat='" + seller_lat + '\'' +
                ", sellerpics='" + sellerpics + '\'' +
                ", pigxx_id='" + pigxx_id + '\'' +
                ", couponList=" + couponList +
                '}';
    }
}
