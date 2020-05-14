package com.example.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/11
 * Describe:
 */
public class CommodityDetailsBean {

    /**
     * goods_detail_response : {"goods_details":[{"mall_coupon_id":0,"mall_coupon_discount_pct":0,"mall_coupon_min_order_amount":0,"mall_coupon_max_discount_amount":0,"mall_coupon_total_quantity":0,"mall_coupon_remain_quantity":0,"mall_coupon_start_time":0,"mall_coupon_end_time":0,"goods_id":5901483212,"goods_name":"【8.9元限时抢，抢完恢复10.9元】【40卷36卷12卷】6斤40卷卫生纸批发家用卷纸手纸木浆纸巾厕纸","goods_desc":"【买三提送一】品牌推广亏本促销!!健康无添加 倡导健康理念以以最好的品质为您的健康生活助理,改变从一张纸开始,系列木浆纸(厕用纸),采用原生木浆,自动化流水线生产,无添加健康不漂白,保持植物纤维天然纸巾。愿景:我们通过。把最高的质量的产品,用最实惠的价格,传授受益于更多的人。将真正的实惠送到每一位消费者手里。5种规格:1提40卷6斤【一包用半年】卷高118mm;1提36卷5.2斤【家庭装】卷高118mm;1提21卷4斤【大卷】卷高115mm;1提12卷2斤【长卷】卷高135mm;1提12卷体验装1.4斤【买三提发四提】卷高118mm。","goods_image_url":"https://t00img.yangkeduo.com/goods/images/2019-03-15/30889beb-296d-4c8a-9664-01325dc0db65.jpg","goods_gallery_urls":["https://t00img.yangkeduo.com/goods/images/2019-02-21/dbcf0457-a702-4fe7-8f19-30714b2661a2.jpg","https://t00img.yangkeduo.com/goods/images/2019-02-21/ca36ffc9-3ce7-4677-b16d-6e1fcb6e7af5.jpg","https://t00img.yangkeduo.com/goods/images/2019-02-21/3ce4407c-338e-4d6f-a81c-47f8e8318bde.jpg","https://t00img.yangkeduo.com/goods/images/2019-02-21/5bea08a3-aef8-487a-ac66-9d579dd87043.jpg","https://t00img.yangkeduo.com/goods/images/2019-03-06/e8ddfe1e-5a4f-47ab-a370-1e13f41b653a.jpg","https://t00img.yangkeduo.com/goods/images/2019-03-06/91e0c042-051b-4e27-b5bc-9f772c921dd8.jpg","https://t00img.yangkeduo.com/goods/images/2019-03-06/67481b8b-ec06-4295-ad7d-ca7db74757d5.jpg","https://t00img.yangkeduo.com/goods/images/2019-03-06/d562a8f7-67c7-409a-85c9-bafd76e802ae.jpg","https://t00img.yangkeduo.com/goods/images/2019-03-06/9e2f81d8-e947-4709-9524-5ecc24205ccf.jpg"],"sold_quantity":552083,"min_group_price":890,"min_normal_price":1190,"mall_name":"尊贵生活家居生活旗舰店","opt_id":8590,"opt_name":"百货","opt_ids":[8592,360,8569,8570,330,8571,8590,8591,15],"cat_ids":[17285,17297,17402],"coupon_min_order_amount":0,"coupon_discount":0,"coupon_total_quantity":0,"coupon_remain_quantity":0,"coupon_start_time":0,"coupon_end_time":0,"promotion_rate":200,"goods_eval_score":4.56,"goods_eval_count":51509,"avg_desc":466,"avg_lgst":470,"avg_serv":471,"desc_pct":0.5569,"lgst_pct":0.5527,"serv_pct":0.5609,"sales_tip":"10万+"}]}
     */

    private GoodsDetailResponseBean goods_detail_response;

    public GoodsDetailResponseBean getGoods_detail_response() {
        return goods_detail_response;
    }

    public void setGoods_detail_response(GoodsDetailResponseBean goods_detail_response) {
        this.goods_detail_response = goods_detail_response;
    }

    public static class GoodsDetailResponseBean {
        private List<GoodsDetailsBean> goods_details;

        public List<GoodsDetailsBean> getGoods_details() {
            return goods_details;
        }

        public void setGoods_details(List<GoodsDetailsBean> goods_details) {
            this.goods_details = goods_details;
        }

        public static class GoodsDetailsBean {
            /**
             * mall_coupon_id : 0
             * mall_coupon_discount_pct : 0
             * mall_coupon_min_order_amount : 0
             * mall_coupon_max_discount_amount : 0
             * mall_coupon_total_quantity : 0
             * mall_coupon_remain_quantity : 0
             * mall_coupon_start_time : 0
             * mall_coupon_end_time : 0
             * goods_id : 5901483212
             * goods_name : 【8.9元限时抢，抢完恢复10.9元】【40卷36卷12卷】6斤40卷卫生纸批发家用卷纸手纸木浆纸巾厕纸
             * goods_desc : 【买三提送一】品牌推广亏本促销!!健康无添加 倡导健康理念以以最好的品质为您的健康生活助理,改变从一张纸开始,系列木浆纸(厕用纸),采用原生木浆,自动化流水线生产,无添加健康不漂白,保持植物纤维天然纸巾。愿景:我们通过。把最高的质量的产品,用最实惠的价格,传授受益于更多的人。将真正的实惠送到每一位消费者手里。5种规格:1提40卷6斤【一包用半年】卷高118mm;1提36卷5.2斤【家庭装】卷高118mm;1提21卷4斤【大卷】卷高115mm;1提12卷2斤【长卷】卷高135mm;1提12卷体验装1.4斤【买三提发四提】卷高118mm。
             * goods_image_url : https://t00img.yangkeduo.com/goods/images/2019-03-15/30889beb-296d-4c8a-9664-01325dc0db65.jpg
             * goods_gallery_urls : ["https://t00img.yangkeduo.com/goods/images/2019-02-21/dbcf0457-a702-4fe7-8f19-30714b2661a2.jpg","https://t00img.yangkeduo.com/goods/images/2019-02-21/ca36ffc9-3ce7-4677-b16d-6e1fcb6e7af5.jpg","https://t00img.yangkeduo.com/goods/images/2019-02-21/3ce4407c-338e-4d6f-a81c-47f8e8318bde.jpg","https://t00img.yangkeduo.com/goods/images/2019-02-21/5bea08a3-aef8-487a-ac66-9d579dd87043.jpg","https://t00img.yangkeduo.com/goods/images/2019-03-06/e8ddfe1e-5a4f-47ab-a370-1e13f41b653a.jpg","https://t00img.yangkeduo.com/goods/images/2019-03-06/91e0c042-051b-4e27-b5bc-9f772c921dd8.jpg","https://t00img.yangkeduo.com/goods/images/2019-03-06/67481b8b-ec06-4295-ad7d-ca7db74757d5.jpg","https://t00img.yangkeduo.com/goods/images/2019-03-06/d562a8f7-67c7-409a-85c9-bafd76e802ae.jpg","https://t00img.yangkeduo.com/goods/images/2019-03-06/9e2f81d8-e947-4709-9524-5ecc24205ccf.jpg"]
             * sold_quantity : 552083
             * min_group_price : 890
             * min_normal_price : 1190
             * mall_name : 尊贵生活家居生活旗舰店
             * opt_id : 8590
             * opt_name : 百货
             * opt_ids : [8592,360,8569,8570,330,8571,8590,8591,15]
             * cat_ids : [17285,17297,17402]
             * coupon_min_order_amount : 0
             * coupon_discount : 0
             * coupon_total_quantity : 0
             * coupon_remain_quantity : 0
             * coupon_start_time : 0
             * coupon_end_time : 0
             * promotion_rate : 200
             * goods_eval_score : 4.56
             * goods_eval_count : 51509
             * avg_desc : 466
             * avg_lgst : 470
             * avg_serv : 471
             * desc_pct : 0.5569
             * lgst_pct : 0.5527
             * serv_pct : 0.5609
             * sales_tip : 10万+
             */

            private int mall_coupon_id;
            private int mall_coupon_discount_pct;
            private int mall_coupon_min_order_amount;
            private int mall_coupon_max_discount_amount;
            private int mall_coupon_total_quantity;
            private int mall_coupon_remain_quantity;
            private int mall_coupon_start_time;
            private int mall_coupon_end_time;
            private long goods_id;
            private String goods_name;
            private String goods_desc;
            private String goods_image_url;
            private int sold_quantity;
            private int min_group_price;
            private int min_normal_price;
            private String mall_name;
            private int opt_id;
            private String opt_name;
            private int coupon_min_order_amount;
            private int coupon_discount;
            private int coupon_total_quantity;
            private int coupon_remain_quantity;
            private long coupon_start_time;
            private long coupon_end_time;
            private double promotion_rate;
            private double user_promotion_rate;
            private double goods_eval_score;
            private int goods_eval_count;
            private int avg_desc;
            private int avg_lgst;
            private int avg_serv;
            private double desc_pct;
            private double lgst_pct;
            private double serv_pct;
            private String sales_tip;
            private List<String> goods_gallery_urls;
            private List<Integer> opt_ids;
            private List<Integer> cat_ids;

            public double getUser_promotion_rate() {
                return user_promotion_rate;
            }

            public void setUser_promotion_rate(double user_promotion_rate) {
                this.user_promotion_rate = user_promotion_rate;
            }
            public int getMall_coupon_id() {
                return mall_coupon_id;
            }

            public void setMall_coupon_id(int mall_coupon_id) {
                this.mall_coupon_id = mall_coupon_id;
            }

            public int getMall_coupon_discount_pct() {
                return mall_coupon_discount_pct;
            }

            public void setMall_coupon_discount_pct(int mall_coupon_discount_pct) {
                this.mall_coupon_discount_pct = mall_coupon_discount_pct;
            }

            public int getMall_coupon_min_order_amount() {
                return mall_coupon_min_order_amount;
            }

            public void setMall_coupon_min_order_amount(int mall_coupon_min_order_amount) {
                this.mall_coupon_min_order_amount = mall_coupon_min_order_amount;
            }

            public int getMall_coupon_max_discount_amount() {
                return mall_coupon_max_discount_amount;
            }

            public void setMall_coupon_max_discount_amount(int mall_coupon_max_discount_amount) {
                this.mall_coupon_max_discount_amount = mall_coupon_max_discount_amount;
            }

            public int getMall_coupon_total_quantity() {
                return mall_coupon_total_quantity;
            }

            public void setMall_coupon_total_quantity(int mall_coupon_total_quantity) {
                this.mall_coupon_total_quantity = mall_coupon_total_quantity;
            }

            public int getMall_coupon_remain_quantity() {
                return mall_coupon_remain_quantity;
            }

            public void setMall_coupon_remain_quantity(int mall_coupon_remain_quantity) {
                this.mall_coupon_remain_quantity = mall_coupon_remain_quantity;
            }

            public int getMall_coupon_start_time() {
                return mall_coupon_start_time;
            }

            public void setMall_coupon_start_time(int mall_coupon_start_time) {
                this.mall_coupon_start_time = mall_coupon_start_time;
            }

            public int getMall_coupon_end_time() {
                return mall_coupon_end_time;
            }

            public void setMall_coupon_end_time(int mall_coupon_end_time) {
                this.mall_coupon_end_time = mall_coupon_end_time;
            }

            public long getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(long goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_desc() {
                return goods_desc;
            }

            public void setGoods_desc(String goods_desc) {
                this.goods_desc = goods_desc;
            }

            public String getGoods_image_url() {
                return goods_image_url;
            }

            public void setGoods_image_url(String goods_image_url) {
                this.goods_image_url = goods_image_url;
            }

            public int getSold_quantity() {
                return sold_quantity;
            }

            public void setSold_quantity(int sold_quantity) {
                this.sold_quantity = sold_quantity;
            }

            public int getMin_group_price() {
                return min_group_price;
            }

            public void setMin_group_price(int min_group_price) {
                this.min_group_price = min_group_price;
            }

            public int getMin_normal_price() {
                return min_normal_price;
            }

            public void setMin_normal_price(int min_normal_price) {
                this.min_normal_price = min_normal_price;
            }

            public String getMall_name() {
                return mall_name;
            }

            public void setMall_name(String mall_name) {
                this.mall_name = mall_name;
            }

            public int getOpt_id() {
                return opt_id;
            }

            public void setOpt_id(int opt_id) {
                this.opt_id = opt_id;
            }

            public String getOpt_name() {
                return opt_name;
            }

            public void setOpt_name(String opt_name) {
                this.opt_name = opt_name;
            }

            public int getCoupon_min_order_amount() {
                return coupon_min_order_amount;
            }

            public void setCoupon_min_order_amount(int coupon_min_order_amount) {
                this.coupon_min_order_amount = coupon_min_order_amount;
            }

            public int getCoupon_discount() {
                return coupon_discount;
            }

            public void setCoupon_discount(int coupon_discount) {
                this.coupon_discount = coupon_discount;
            }

            public int getCoupon_total_quantity() {
                return coupon_total_quantity;
            }

            public void setCoupon_total_quantity(int coupon_total_quantity) {
                this.coupon_total_quantity = coupon_total_quantity;
            }

            public int getCoupon_remain_quantity() {
                return coupon_remain_quantity;
            }

            public void setCoupon_remain_quantity(int coupon_remain_quantity) {
                this.coupon_remain_quantity = coupon_remain_quantity;
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

            public double getPromotion_rate() {
                return promotion_rate;
            }

            public void setPromotion_rate(double promotion_rate) {
                this.promotion_rate = promotion_rate;
            }

            public double getGoods_eval_score() {
                return goods_eval_score;
            }

            public void setGoods_eval_score(double goods_eval_score) {
                this.goods_eval_score = goods_eval_score;
            }

            public int getGoods_eval_count() {
                return goods_eval_count;
            }

            public void setGoods_eval_count(int goods_eval_count) {
                this.goods_eval_count = goods_eval_count;
            }

            public int getAvg_desc() {
                return avg_desc;
            }

            public void setAvg_desc(int avg_desc) {
                this.avg_desc = avg_desc;
            }

            public int getAvg_lgst() {
                return avg_lgst;
            }

            public void setAvg_lgst(int avg_lgst) {
                this.avg_lgst = avg_lgst;
            }

            public int getAvg_serv() {
                return avg_serv;
            }

            public void setAvg_serv(int avg_serv) {
                this.avg_serv = avg_serv;
            }

            public double getDesc_pct() {
                return desc_pct;
            }

            public void setDesc_pct(double desc_pct) {
                this.desc_pct = desc_pct;
            }

            public double getLgst_pct() {
                return lgst_pct;
            }

            public void setLgst_pct(double lgst_pct) {
                this.lgst_pct = lgst_pct;
            }

            public double getServ_pct() {
                return serv_pct;
            }

            public void setServ_pct(double serv_pct) {
                this.serv_pct = serv_pct;
            }

            public String getSales_tip() {
                return sales_tip;
            }

            public void setSales_tip(String sales_tip) {
                this.sales_tip = sales_tip;
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

            public List<Integer> getCat_ids() {
                return cat_ids;
            }

            public void setCat_ids(List<Integer> cat_ids) {
                this.cat_ids = cat_ids;
            }
        }
    }
}
