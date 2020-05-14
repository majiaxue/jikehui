package com.example.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class GoodsRecommendBean {

    /**
     * count : 2
     * data : [{"item_id":"594628530417","title":"修正桔红罗汉果茶 胖大海润嗓橘皮栀子润喉橘红桑叶淡竹叶咽喉茶","pict_url":"http://img.alicdn.com/bao/uploaded/i4/3278510613/O1CN01tpZSOo1GOnK7MP2IA_!!0-item_pic.jpg","seller_id":"3278510613","user_type":"1","small_images":"https://img.alicdn.com/i2/3278510613/O1CN01lauPY41GOnJpciZun_!!3278510613.png|https://img.alicdn.com/i2/3278510613/O1CN01rLyfTF1GOnK4CNw4D_!!3278510613.jpg|https://img.alicdn.com/i1/3278510613/O1CN0156wI0k1GOnK7LvyAj_!!3278510613.jpg|https://img.alicdn.com/i1/3278510613/O1CN01lV36LP1GOnK5W7icp_!!3278510613.jpg","volume":"7217","item_description":"","zk_final_price":"79.00","commission_rate":"70.00","coupon_amount":"50.00","coupon_start_fee":"69.00","coupon_total_count":"30000","coupon_remain_count":"27600","coupon_start_time":"1560355200","coupon_end_time":"1560614399","shop_title":"百年修正旗舰店"},{"item_id":"595687512906","title":"澳洲进口原料21天极光晚安精华套装原液补水夜间提亮修护收缩毛孔","pict_url":"http://img.alicdn.com/bao/uploaded/i4/909321915/O1CN01Jh1cVv1Q1704Qf196_!!0-item_pic.jpg","seller_id":"909321915","user_type":"1","small_images":"https://img.alicdn.com/i4/909321915/O1CN01PuBlOX1Q1706AvC6p_!!909321915.jpg|https://img.alicdn.com/i4/909321915/O1CN01cJJG6H1Q1702NQAPM_!!909321915.jpg|https://img.alicdn.com/i3/909321915/O1CN011zJIgX1Q1705WJr0E_!!909321915.jpg|https://img.alicdn.com/i4/909321915/O1CN01w7FIkW1Q1704QcjRh_!!909321915.jpg","volume":"36212","item_description":"","zk_final_price":"179.00","commission_rate":"60.00","coupon_amount":"120.00","coupon_start_fee":"179.00","coupon_total_count":"100000","coupon_remain_count":"85000","coupon_start_time":"1560268800","coupon_end_time":"1560700799","shop_title":"活美旗舰店"}]
     */

    private int count;
    private List<DataBean> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * item_id : 594628530417
         * title : 修正桔红罗汉果茶 胖大海润嗓橘皮栀子润喉橘红桑叶淡竹叶咽喉茶
         * pict_url : http://img.alicdn.com/bao/uploaded/i4/3278510613/O1CN01tpZSOo1GOnK7MP2IA_!!0-item_pic.jpg
         * seller_id : 3278510613
         * user_type : 1
         * small_images : https://img.alicdn.com/i2/3278510613/O1CN01lauPY41GOnJpciZun_!!3278510613.png|https://img.alicdn.com/i2/3278510613/O1CN01rLyfTF1GOnK4CNw4D_!!3278510613.jpg|https://img.alicdn.com/i1/3278510613/O1CN0156wI0k1GOnK7LvyAj_!!3278510613.jpg|https://img.alicdn.com/i1/3278510613/O1CN01lV36LP1GOnK5W7icp_!!3278510613.jpg
         * volume : 7217
         * item_description :
         * zk_final_price : 79.00
         * commission_rate : 70.00
         * coupon_amount : 50.00
         * coupon_start_fee : 69.00
         * coupon_total_count : 30000
         * coupon_remain_count : 27600
         * coupon_start_time : 1560355200
         * coupon_end_time : 1560614399
         * shop_title : 百年修正旗舰店
         */

        private String item_id;
        private String title;
        private String pict_url;
        private String seller_id;
        private String user_type;
        private String small_images;
        private String volume;
        private String item_description;
        private String zk_final_price;
        private String commission_rate;
        private String coupon_amount;
        private String coupon_start_fee;
        private String coupon_total_count;
        private String coupon_remain_count;
        private long coupon_start_time;
        private long coupon_end_time;
        private String shop_title;

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPict_url() {
            return pict_url;
        }

        public void setPict_url(String pict_url) {
            this.pict_url = pict_url;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getSmall_images() {
            return small_images;
        }

        public void setSmall_images(String small_images) {
            this.small_images = small_images;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getItem_description() {
            return item_description;
        }

        public void setItem_description(String item_description) {
            this.item_description = item_description;
        }

        public String getZk_final_price() {
            return zk_final_price;
        }

        public void setZk_final_price(String zk_final_price) {
            this.zk_final_price = zk_final_price;
        }

        public String getCommission_rate() {
            return commission_rate;
        }

        public void setCommission_rate(String commission_rate) {
            this.commission_rate = commission_rate;
        }

        public String getCoupon_amount() {
            return coupon_amount;
        }

        public void setCoupon_amount(String coupon_amount) {
            this.coupon_amount = coupon_amount;
        }

        public String getCoupon_start_fee() {
            return coupon_start_fee;
        }

        public void setCoupon_start_fee(String coupon_start_fee) {
            this.coupon_start_fee = coupon_start_fee;
        }

        public String getCoupon_total_count() {
            return coupon_total_count;
        }

        public void setCoupon_total_count(String coupon_total_count) {
            this.coupon_total_count = coupon_total_count;
        }

        public String getCoupon_remain_count() {
            return coupon_remain_count;
        }

        public void setCoupon_remain_count(String coupon_remain_count) {
            this.coupon_remain_count = coupon_remain_count;
        }

        public long getCoupon_start_time() {
            return coupon_start_time;
        }

        public void setCoupon_start_time(long coupon_start_time) {
            this.coupon_start_time = coupon_start_time;
        }

        public long getCoupon_end_time() {
            return coupon_end_time;
        }

        public void setCoupon_end_time(long coupon_end_time) {
            this.coupon_end_time = coupon_end_time;
        }

        public String getShop_title() {
            return shop_title;
        }

        public void setShop_title(String shop_title) {
            this.shop_title = shop_title;
        }
    }
}
