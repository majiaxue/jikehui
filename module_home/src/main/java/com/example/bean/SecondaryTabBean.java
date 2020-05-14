package com.example.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/6
 * Describe:
 */
public class SecondaryTabBean {

    /**
     * goods_opt_get_response : {"goods_opt_list":[{"level":1,"parent_opt_id":0,"opt_name":"食品","opt_id":1},{"level":1,"parent_opt_id":0,"opt_name":"母婴","opt_id":4},{"level":1,"parent_opt_id":0,"opt_name":"海淘","opt_id":12},{"level":1,"parent_opt_id":0,"opt_name":"水果","opt_id":13},{"level":1,"parent_opt_id":0,"opt_name":"女装","opt_id":14},{"level":1,"parent_opt_id":0,"opt_name":"百货","opt_id":15},{"level":1,"parent_opt_id":0,"opt_name":"美妆","opt_id":16},{"level":1,"parent_opt_id":0,"opt_name":"电器","opt_id":18},{"level":1,"parent_opt_id":0,"opt_name":"充值","opt_id":590},{"level":1,"parent_opt_id":0,"opt_name":"男装","opt_id":743},{"level":1,"parent_opt_id":0,"opt_name":"家纺","opt_id":818},{"level":1,"parent_opt_id":0,"opt_name":"鞋包","opt_id":1281},{"level":1,"parent_opt_id":0,"opt_name":"内衣","opt_id":1282},{"level":1,"parent_opt_id":0,"opt_name":"运动","opt_id":1451},{"level":1,"parent_opt_id":0,"opt_name":"手机","opt_id":1543},{"level":1,"parent_opt_id":0,"opt_name":"家装","opt_id":1917},{"level":1,"parent_opt_id":0,"opt_name":"汽车","opt_id":2048},{"level":1,"parent_opt_id":0,"opt_name":"电脑","opt_id":2478},{"level":1,"parent_opt_id":0,"opt_name":"生活个护","opt_id":2946},{"level":1,"parent_opt_id":0,"opt_name":"大家电","opt_id":2964},{"level":1,"parent_opt_id":0,"opt_name":"家具","opt_id":2974},{"level":1,"parent_opt_id":0,"opt_name":"手机","opt_id":3132},{"level":1,"parent_opt_id":0,"opt_name":"电脑","opt_id":3149},{"level":1,"parent_opt_id":0,"opt_name":"数码","opt_id":3162},{"level":1,"parent_opt_id":0,"opt_name":"文化","opt_id":3175},{"level":1,"parent_opt_id":0,"opt_name":"医药","opt_id":3279},{"level":1,"parent_opt_id":0,"opt_name":"办公","opt_id":3526},{"level":1,"parent_opt_id":0,"opt_name":"厨房电器","opt_id":5127},{"level":1,"parent_opt_id":0,"opt_name":"精选","opt_id":8569},{"level":1,"parent_opt_id":0,"opt_name":"女装","opt_id":8572},{"level":1,"parent_opt_id":0,"opt_name":"男装","opt_id":8581},{"level":1,"parent_opt_id":0,"opt_name":"食品","opt_id":8584},{"level":1,"parent_opt_id":0,"opt_name":"母婴","opt_id":8587},{"level":1,"parent_opt_id":0,"opt_name":"百货","opt_id":8590},{"level":1,"parent_opt_id":0,"opt_name":"美妆","opt_id":8593},{"level":1,"parent_opt_id":0,"opt_name":"运动","opt_id":8596},{"level":1,"parent_opt_id":0,"opt_name":"内衣","opt_id":8599},{"level":1,"parent_opt_id":0,"opt_name":"家纺","opt_id":8602},{"level":1,"parent_opt_id":0,"opt_name":"鞋包","opt_id":8605},{"level":1,"parent_opt_id":0,"opt_name":"家装","opt_id":8608},{"level":1,"parent_opt_id":0,"opt_name":"家具","opt_id":8611},{"level":1,"parent_opt_id":0,"opt_name":"手机","opt_id":9016},{"level":1,"parent_opt_id":0,"opt_name":"电器","opt_id":9019},{"level":1,"parent_opt_id":0,"opt_name":"生鲜","opt_id":9419},{"level":1,"parent_opt_id":0,"opt_name":"女装","opt_id":9490},{"level":1,"parent_opt_id":0,"opt_name":"内衣","opt_id":9527},{"level":1,"parent_opt_id":0,"opt_name":"手机","opt_id":9563},{"level":1,"parent_opt_id":0,"opt_name":"电器","opt_id":9583},{"level":1,"parent_opt_id":0,"opt_name":"家纺","opt_id":9646},{"level":1,"parent_opt_id":0,"opt_name":"数码","opt_id":9689},{"level":1,"parent_opt_id":0,"opt_name":"家装","opt_id":9739},{"level":1,"parent_opt_id":0,"opt_name":"男装","opt_id":9805},{"level":1,"parent_opt_id":0,"opt_name":"百货","opt_id":9831},{"level":1,"parent_opt_id":0,"opt_name":"母婴","opt_id":9907},{"level":1,"parent_opt_id":0,"opt_name":"食品","opt_id":9975},{"level":1,"parent_opt_id":0,"opt_name":"美妆","opt_id":10053},{"level":1,"parent_opt_id":0,"opt_name":"洗护","opt_id":10110},{"level":1,"parent_opt_id":0,"opt_name":"充值","opt_id":10141},{"level":1,"parent_opt_id":0,"opt_name":"健康","opt_id":10223},{"level":1,"parent_opt_id":0,"opt_name":"车品","opt_id":10301},{"level":1,"parent_opt_id":0,"opt_name":"鞋包","opt_id":10364},{"level":1,"parent_opt_id":0,"opt_name":"运动","opt_id":10649},{"level":1,"parent_opt_id":0,"opt_name":"海淘","opt_id":10696}]}
     */

    private GoodsOptGetResponseBean goods_opt_get_response;

    public GoodsOptGetResponseBean getGoods_opt_get_response() {
        return goods_opt_get_response;
    }

    public void setGoods_opt_get_response(GoodsOptGetResponseBean goods_opt_get_response) {
        this.goods_opt_get_response = goods_opt_get_response;
    }

    public static class GoodsOptGetResponseBean {
        private List<GoodsOptListBean> goods_opt_list;

        public List<GoodsOptListBean> getGoods_opt_list() {
            return goods_opt_list;
        }

        public void setGoods_opt_list(List<GoodsOptListBean> goods_opt_list) {
            this.goods_opt_list = goods_opt_list;
        }

        public static class GoodsOptListBean {
            /**
             * level : 1
             * parent_opt_id : 0
             * opt_name : 食品
             * opt_id : 1
             */

            private int level;
            private int parent_opt_id;
            private String opt_name;
            private int opt_id;

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getParent_opt_id() {
                return parent_opt_id;
            }

            public void setParent_opt_id(int parent_opt_id) {
                this.parent_opt_id = parent_opt_id;
            }

            public String getOpt_name() {
                return opt_name;
            }

            public void setOpt_name(String opt_name) {
                this.opt_name = opt_name;
            }

            public int getOpt_id() {
                return opt_id;
            }

            public void setOpt_id(int opt_id) {
                this.opt_id = opt_id;
            }
        }
    }
}
