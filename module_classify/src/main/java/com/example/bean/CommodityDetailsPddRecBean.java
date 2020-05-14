package com.example.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/11
 * Describe:
 */
public class CommodityDetailsPddRecBean {


    /**
     * top_goods_list_get_response : {"list":[{"avg_desc":0,"avg_lgst":0,"avg_serv":0,"cat_ids":[0],"category_id":0,"category_name":"str","coupon_discount":0,"coupon_end_time":0,"coupon_min_order_amount":0,"coupon_remain_quantity":0,"coupon_start_time":0,"coupon_total_quantity":0,"desc_pct":0,"goods_desc":"str","goods_eval_count":0,"goods_eval_score":0,"goods_gallery_urls":["str"],"goods_id":0,"goods_image_url":"str","goods_name":"str","goods_thumbnail_url":"str","has_coupon":false,"lgst_pct":0,"mall_cps":0,"mall_name":"str","merchant_type":0,"min_group_price":0,"min_normal_price":0,"opt_id":0,"opt_ids":[0],"opt_name":"str","promotion_rate":0,"sales_tip":"str","serv_pct":0,"sold_quantity":0}],"total":0}
     */

    private TopGoodsListGetResponseBean top_goods_list_get_response;

    public TopGoodsListGetResponseBean getTop_goods_list_get_response() {
        return top_goods_list_get_response;
    }

    public void setTop_goods_list_get_response(TopGoodsListGetResponseBean top_goods_list_get_response) {
        this.top_goods_list_get_response = top_goods_list_get_response;
    }

    public static class TopGoodsListGetResponseBean {
        /**
         * list : [{"avg_desc":0,"avg_lgst":0,"avg_serv":0,"cat_ids":[0],"category_id":0,"category_name":"str","coupon_discount":0,"coupon_end_time":0,"coupon_min_order_amount":0,"coupon_remain_quantity":0,"coupon_start_time":0,"coupon_total_quantity":0,"desc_pct":0,"goods_desc":"str","goods_eval_count":0,"goods_eval_score":0,"goods_gallery_urls":["str"],"goods_id":0,"goods_image_url":"str","goods_name":"str","goods_thumbnail_url":"str","has_coupon":false,"lgst_pct":0,"mall_cps":0,"mall_name":"str","merchant_type":0,"min_group_price":0,"min_normal_price":0,"opt_id":0,"opt_ids":[0],"opt_name":"str","promotion_rate":0,"sales_tip":"str","serv_pct":0,"sold_quantity":0}]
         * total : 0
         */

        private double total;
        private List<ListBean> list;

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * avg_desc : 0
             * avg_lgst : 0
             * avg_serv : 0
             * cat_ids : [0]
             * category_id : 0
             * category_name : str
             * coupon_discount : 0
             * coupon_end_time : 0
             * coupon_min_order_amount : 0
             * coupon_remain_quantity : 0
             * coupon_start_time : 0
             * coupon_total_quantity : 0
             * desc_pct : 0
             * goods_desc : str
             * goods_eval_count : 0
             * goods_eval_score : 0
             * goods_gallery_urls : ["str"]
             * goods_id : 0
             * goods_image_url : str
             * goods_name : str
             * goods_thumbnail_url : str
             * has_coupon : false
             * lgst_pct : 0
             * mall_cps : 0
             * mall_name : str
             * merchant_type : 0
             * min_group_price : 0
             * min_normal_price : 0
             * opt_id : 0
             * opt_ids : [0]
             * opt_name : str
             * promotion_rate : 0
             * sales_tip : str
             * serv_pct : 0
             * sold_quantity : 0
             */

            private double avg_desc;
            private double avg_lgst;
            private double avg_serv;
            private double category_id;
            private String category_name;
            private double coupon_discount;
            private double coupon_end_time;
            private double coupon_min_order_amount;
            private double coupon_remain_quantity;
            private double coupon_start_time;
            private double coupon_total_quantity;
            private double desc_pct;
            private String goods_desc;
            private double goods_eval_count;
            private double goods_eval_score;
            private long goods_id;
            private String goods_image_url;
            private String goods_name;
            private String goods_thumbnail_url;
            private boolean has_coupon;
            private double lgst_pct;
            private double mall_cps;
            private String mall_name;
            private double merchant_type;
            private double min_group_price;
            private double min_normal_price;
            private double opt_id;
            private String opt_name;
            private double promotion_rate;
            private String sales_tip;
            private double serv_pct;
            private double sold_quantity;
            private List<Integer> cat_ids;
            private List<String> goods_gallery_urls;
            private List<Integer> opt_ids;

            public double getAvg_desc() {
                return avg_desc;
            }

            public void setAvg_desc(double avg_desc) {
                this.avg_desc = avg_desc;
            }

            public double getAvg_lgst() {
                return avg_lgst;
            }

            public void setAvg_lgst(double avg_lgst) {
                this.avg_lgst = avg_lgst;
            }

            public double getAvg_serv() {
                return avg_serv;
            }

            public void setAvg_serv(double avg_serv) {
                this.avg_serv = avg_serv;
            }

            public double getCategory_id() {
                return category_id;
            }

            public void setCategory_id(double category_id) {
                this.category_id = category_id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public double getCoupon_discount() {
                return coupon_discount;
            }

            public void setCoupon_discount(double coupon_discount) {
                this.coupon_discount = coupon_discount;
            }

            public double getCoupon_end_time() {
                return coupon_end_time;
            }

            public void setCoupon_end_time(double coupon_end_time) {
                this.coupon_end_time = coupon_end_time;
            }

            public double getCoupon_min_order_amount() {
                return coupon_min_order_amount;
            }

            public void setCoupon_min_order_amount(double coupon_min_order_amount) {
                this.coupon_min_order_amount = coupon_min_order_amount;
            }

            public double getCoupon_remain_quantity() {
                return coupon_remain_quantity;
            }

            public void setCoupon_remain_quantity(double coupon_remain_quantity) {
                this.coupon_remain_quantity = coupon_remain_quantity;
            }

            public double getCoupon_start_time() {
                return coupon_start_time;
            }

            public void setCoupon_start_time(double coupon_start_time) {
                this.coupon_start_time = coupon_start_time;
            }

            public double getCoupon_total_quantity() {
                return coupon_total_quantity;
            }

            public void setCoupon_total_quantity(double coupon_total_quantity) {
                this.coupon_total_quantity = coupon_total_quantity;
            }

            public double getDesc_pct() {
                return desc_pct;
            }

            public void setDesc_pct(double desc_pct) {
                this.desc_pct = desc_pct;
            }

            public String getGoods_desc() {
                return goods_desc;
            }

            public void setGoods_desc(String goods_desc) {
                this.goods_desc = goods_desc;
            }

            public double getGoods_eval_count() {
                return goods_eval_count;
            }

            public void setGoods_eval_count(double goods_eval_count) {
                this.goods_eval_count = goods_eval_count;
            }

            public double getGoods_eval_score() {
                return goods_eval_score;
            }

            public void setGoods_eval_score(double goods_eval_score) {
                this.goods_eval_score = goods_eval_score;
            }

            public long getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(long goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_image_url() {
                return goods_image_url;
            }

            public void setGoods_image_url(String goods_image_url) {
                this.goods_image_url = goods_image_url;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_thumbnail_url() {
                return goods_thumbnail_url;
            }

            public void setGoods_thumbnail_url(String goods_thumbnail_url) {
                this.goods_thumbnail_url = goods_thumbnail_url;
            }

            public boolean isHas_coupon() {
                return has_coupon;
            }

            public void setHas_coupon(boolean has_coupon) {
                this.has_coupon = has_coupon;
            }

            public double getLgst_pct() {
                return lgst_pct;
            }

            public void setLgst_pct(double lgst_pct) {
                this.lgst_pct = lgst_pct;
            }

            public double getMall_cps() {
                return mall_cps;
            }

            public void setMall_cps(double mall_cps) {
                this.mall_cps = mall_cps;
            }

            public String getMall_name() {
                return mall_name;
            }

            public void setMall_name(String mall_name) {
                this.mall_name = mall_name;
            }

            public double getMerchant_type() {
                return merchant_type;
            }

            public void setMerchant_type(double merchant_type) {
                this.merchant_type = merchant_type;
            }

            public double getMin_group_price() {
                return min_group_price;
            }

            public void setMin_group_price(double min_group_price) {
                this.min_group_price = min_group_price;
            }

            public double getMin_normal_price() {
                return min_normal_price;
            }

            public void setMin_normal_price(double min_normal_price) {
                this.min_normal_price = min_normal_price;
            }

            public double getOpt_id() {
                return opt_id;
            }

            public void setOpt_id(double opt_id) {
                this.opt_id = opt_id;
            }

            public String getOpt_name() {
                return opt_name;
            }

            public void setOpt_name(String opt_name) {
                this.opt_name = opt_name;
            }

            public double getPromotion_rate() {
                return promotion_rate;
            }

            public void setPromotion_rate(double promotion_rate) {
                this.promotion_rate = promotion_rate;
            }

            public String getSales_tip() {
                return sales_tip;
            }

            public void setSales_tip(String sales_tip) {
                this.sales_tip = sales_tip;
            }

            public double getServ_pct() {
                return serv_pct;
            }

            public void setServ_pct(double serv_pct) {
                this.serv_pct = serv_pct;
            }

            public double getSold_quantity() {
                return sold_quantity;
            }

            public void setSold_quantity(double sold_quantity) {
                this.sold_quantity = sold_quantity;
            }

            public List<Integer> getCat_ids() {
                return cat_ids;
            }

            public void setCat_ids(List<Integer> cat_ids) {
                this.cat_ids = cat_ids;
            }

            public List<String> getGoods_gallery_urls() {
                return goods_gallery_urls;
            }

            public void setGoods_gallery_urls(List<String> goods_gallery_urls) {
                this.goods_gallery_urls = goods_gallery_urls;
            }

            public List<Integer> getOpt_ids() {
                return opt_ids;
            }

            public void setOpt_ids(List<Integer> opt_ids) {
                this.opt_ids = opt_ids;
            }
        }
    }
}

