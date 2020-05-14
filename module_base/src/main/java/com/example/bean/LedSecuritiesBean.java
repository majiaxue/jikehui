package com.example.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/11
 * Describe:
 */
public class LedSecuritiesBean {

    /**
     * goods_promotion_url_generate_response : {"goods_promotion_url_list":[{"we_app_web_view_short_url":"https://p.pinduoduo.com/QajijBK5","we_app_web_view_url":"https://mobile.yangkeduo.com/duo_coupon_landing.html?goods_id=297881222686703616&pid=8714264_70974453&customParameters=6721102107&cpsSign=CC_190611_8714264_70974453_a17f065ab4d82d79a573c7b0f4cdf121&duoduo_type=2&launch_wx=1","mobile_short_url":"https://p.pinduoduo.com/d3ViqZQt","mobile_url":"https://mobile.yangkeduo.com/app.html?launch_url=duo_coupon_landing.html%3Fgoods_id%3D297881222686703616%26pid%3D8714264_70974453%26customParameters%3D6721102107%26cpsSign%3DCC_190611_8714264_70974453_a17f065ab4d82d79a573c7b0f4cdf121%26duoduo_type%3D2","short_url":"https://p.pinduoduo.com/ft0iRLir","url":"https://mobile.yangkeduo.com/duo_coupon_landing.html?goods_id=297881222686703616&pid=8714264_70974453&customParameters=6721102107&cpsSign=CC_190611_8714264_70974453_a17f065ab4d82d79a573c7b0f4cdf121&duoduo_type=2","we_app_info":{"we_app_icon_url":"http://xcxcdn.yangkeduo.com/pdd_logo.png","desc":"拼多多，多实惠，多乐趣。","source_display_name":"拼多多","page_path":"package_a/welfare_coupon/welfare_coupon?goods_id=297881222686703616&pid=8714264_70974453&customParameters=6721102107&cpsSign=CC_190611_8714264_70974453_a17f065ab4d82d79a573c7b0f4cdf121&duoduo_type=2","user_name":"gh_0e7477744313@app","app_id":"wx32540bd863b27570"},"weibo_app_web_view_short_url":"https://p.pinduoduo.com/HPdiDpuI","weibo_app_web_view_url":"https://mobile.yangkeduo.com/duo_coupon_landing.html?goods_id=297881222686703616&pid=8714264_70974453&customParameters=6721102107&cpsSign=CC_190611_8714264_70974453_a17f065ab4d82d79a573c7b0f4cdf121&duoduo_type=2&launch_weibo=1"}]}
     */

    private GoodsPromotionUrlGenerateResponseBean goods_promotion_url_generate_response;

    public GoodsPromotionUrlGenerateResponseBean getGoods_promotion_url_generate_response() {
        return goods_promotion_url_generate_response;
    }

    public void setGoods_promotion_url_generate_response(GoodsPromotionUrlGenerateResponseBean goods_promotion_url_generate_response) {
        this.goods_promotion_url_generate_response = goods_promotion_url_generate_response;
    }

    public static class GoodsPromotionUrlGenerateResponseBean {
        private List<GoodsPromotionUrlListBean> goods_promotion_url_list;

        public List<GoodsPromotionUrlListBean> getGoods_promotion_url_list() {
            return goods_promotion_url_list;
        }

        public void setGoods_promotion_url_list(List<GoodsPromotionUrlListBean> goods_promotion_url_list) {
            this.goods_promotion_url_list = goods_promotion_url_list;
        }

        public static class GoodsPromotionUrlListBean {
            /**
             * we_app_web_view_short_url : https://p.pinduoduo.com/QajijBK5
             * we_app_web_view_url : https://mobile.yangkeduo.com/duo_coupon_landing.html?goods_id=297881222686703616&pid=8714264_70974453&customParameters=6721102107&cpsSign=CC_190611_8714264_70974453_a17f065ab4d82d79a573c7b0f4cdf121&duoduo_type=2&launch_wx=1
             * mobile_short_url : https://p.pinduoduo.com/d3ViqZQt
             * mobile_url : https://mobile.yangkeduo.com/app.html?launch_url=duo_coupon_landing.html%3Fgoods_id%3D297881222686703616%26pid%3D8714264_70974453%26customParameters%3D6721102107%26cpsSign%3DCC_190611_8714264_70974453_a17f065ab4d82d79a573c7b0f4cdf121%26duoduo_type%3D2
             * short_url : https://p.pinduoduo.com/ft0iRLir
             * url : https://mobile.yangkeduo.com/duo_coupon_landing.html?goods_id=297881222686703616&pid=8714264_70974453&customParameters=6721102107&cpsSign=CC_190611_8714264_70974453_a17f065ab4d82d79a573c7b0f4cdf121&duoduo_type=2
             * we_app_info : {"we_app_icon_url":"http://xcxcdn.yangkeduo.com/pdd_logo.png","desc":"拼多多，多实惠，多乐趣。","source_display_name":"拼多多","page_path":"package_a/welfare_coupon/welfare_coupon?goods_id=297881222686703616&pid=8714264_70974453&customParameters=6721102107&cpsSign=CC_190611_8714264_70974453_a17f065ab4d82d79a573c7b0f4cdf121&duoduo_type=2","user_name":"gh_0e7477744313@app","app_id":"wx32540bd863b27570"}
             * weibo_app_web_view_short_url : https://p.pinduoduo.com/HPdiDpuI
             * weibo_app_web_view_url : https://mobile.yangkeduo.com/duo_coupon_landing.html?goods_id=297881222686703616&pid=8714264_70974453&customParameters=6721102107&cpsSign=CC_190611_8714264_70974453_a17f065ab4d82d79a573c7b0f4cdf121&duoduo_type=2&launch_weibo=1
             */

            private String we_app_web_view_short_url;
            private String we_app_web_view_url;
            private String mobile_short_url;
            private String mobile_url;
            private String short_url;
            private String url;
            private WeAppInfoBean we_app_info;
            private String weibo_app_web_view_short_url;
            private String weibo_app_web_view_url;

            public String getWe_app_web_view_short_url() {
                return we_app_web_view_short_url;
            }

            public void setWe_app_web_view_short_url(String we_app_web_view_short_url) {
                this.we_app_web_view_short_url = we_app_web_view_short_url;
            }

            public String getWe_app_web_view_url() {
                return we_app_web_view_url;
            }

            public void setWe_app_web_view_url(String we_app_web_view_url) {
                this.we_app_web_view_url = we_app_web_view_url;
            }

            public String getMobile_short_url() {
                return mobile_short_url;
            }

            public void setMobile_short_url(String mobile_short_url) {
                this.mobile_short_url = mobile_short_url;
            }

            public String getMobile_url() {
                return mobile_url;
            }

            public void setMobile_url(String mobile_url) {
                this.mobile_url = mobile_url;
            }

            public String getShort_url() {
                return short_url;
            }

            public void setShort_url(String short_url) {
                this.short_url = short_url;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public WeAppInfoBean getWe_app_info() {
                return we_app_info;
            }

            public void setWe_app_info(WeAppInfoBean we_app_info) {
                this.we_app_info = we_app_info;
            }

            public String getWeibo_app_web_view_short_url() {
                return weibo_app_web_view_short_url;
            }

            public void setWeibo_app_web_view_short_url(String weibo_app_web_view_short_url) {
                this.weibo_app_web_view_short_url = weibo_app_web_view_short_url;
            }

            public String getWeibo_app_web_view_url() {
                return weibo_app_web_view_url;
            }

            public void setWeibo_app_web_view_url(String weibo_app_web_view_url) {
                this.weibo_app_web_view_url = weibo_app_web_view_url;
            }

            public static class WeAppInfoBean {
                /**
                 * we_app_icon_url : http://xcxcdn.yangkeduo.com/pdd_logo.png
                 * desc : 拼多多，多实惠，多乐趣。
                 * source_display_name : 拼多多
                 * page_path : package_a/welfare_coupon/welfare_coupon?goods_id=297881222686703616&pid=8714264_70974453&customParameters=6721102107&cpsSign=CC_190611_8714264_70974453_a17f065ab4d82d79a573c7b0f4cdf121&duoduo_type=2
                 * user_name : gh_0e7477744313@app
                 * app_id : wx32540bd863b27570
                 */

                private String we_app_icon_url;
                private String desc;
                private String source_display_name;
                private String page_path;
                private String user_name;
                private String app_id;

                public String getWe_app_icon_url() {
                    return we_app_icon_url;
                }

                public void setWe_app_icon_url(String we_app_icon_url) {
                    this.we_app_icon_url = we_app_icon_url;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getSource_display_name() {
                    return source_display_name;
                }

                public void setSource_display_name(String source_display_name) {
                    this.source_display_name = source_display_name;
                }

                public String getPage_path() {
                    return page_path;
                }

                public void setPage_path(String page_path) {
                    this.page_path = page_path;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public String getApp_id() {
                    return app_id;
                }

                public void setApp_id(String app_id) {
                    this.app_id = app_id;
                }
            }
        }
    }
}
