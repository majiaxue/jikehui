package com.example.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/14
 * Describe:
 */
public class TBGoodsRecBean {
    /**
     * error : 0
     * msg : 查询成功！
     * search_type : 1
     * is_similar : 0
     * total_results : 2587118
     * result_list : [{"category_id":162205,"category_name":"牛仔裤","commission_rate":"550","coupon_amount":"3","coupon_end_time":"2019-10-10","coupon_id":"b2ab2c7e867a46898c8204f18abfbbb2","coupon_info":"满4元减3元","coupon_remain_count":89820,"coupon_start_fee":"4","coupon_start_time":"2019-10-06","coupon_total_count":100000,"item_description":"2019韩版小黑裤 送运费险  定制面料","item_id":601043792095,"item_url":"https://detail.tmall.com/item.htm?id=601043792095","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"恋人心服饰旗舰店","num_iid":601043792095,"pict_url":"https://img.alicdn.com/bao/uploaded/i3/2851318218/O1CN01l2qeFN2AZtQP0hw59_!!0-item_pic.jpg","provcity":"广东 中山","real_post_fee":"0.00","reserve_price":"149","seller_id":2851318218,"shop_dsr":48425,"shop_title":"恋人心服饰旗舰店","short_title":"直筒黑色九分女秋装2019高腰牛仔裤","small_images":["https://img.alicdn.com/i3/2851318218/O1CN01QQmlIU2AZtQNINb6a_!!2851318218.jpg","https://img.alicdn.com/i4/2851318218/O1CN01abiFlR2AZtQ8b9aPd_!!2851318218.jpg","https://img.alicdn.com/i1/2851318218/O1CN01Rs0pBF2AZtQLSpb5o_!!2851318218.jpg","https://img.alicdn.com/i2/2851318218/O1CN01OnCHGY2AZtQ9Kk1T6_!!2851318218.jpg"],"title":"直筒黑色九分牛仔裤女秋装2019新款高腰显瘦宽松女装秋季百搭裤子","tk_total_commi":"9765.26","tk_total_sales":"2625","user_type":1,"volume":21342,"white_image":"https://img.alicdn.com/bao/uploaded/TB14KxLeuH2gK0jSZJnXXaT1FXa.png","zk_final_price":"79"},{"category_id":50008898,"category_name":"卫衣/绒衫","commission_rate":"2000","coupon_amount":"20","coupon_end_time":"2019-10-14","coupon_id":"f4323547cca94bc18785a79a05a9641c","coupon_info":"满49元减20元","coupon_remain_count":98000,"coupon_start_fee":"49","coupon_start_time":"2019-10-08","coupon_total_count":100000,"item_description":"","item_id":601688720012,"item_url":"https://detail.tmall.com/item.htm?id=601688720012","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"流婉菲旗舰店","num_iid":601688720012,"pict_url":"https://img.alicdn.com/bao/uploaded/i2/4140484520/O1CN013BXXgj1jGCmP3S1z2_!!0-item_pic.jpg","provcity":"广东 揭阳","real_post_fee":"0.00","reserve_price":"109.8","seller_id":4140484520,"shop_dsr":47484,"shop_title":"流婉菲旗舰店","short_title":"牛油果绿女秋装ins超火韩版卫衣","small_images":["https://img.alicdn.com/i2/4140484520/O1CN01bQkiow1jGCm0VUlxv_!!4140484520.jpg","https://img.alicdn.com/i2/4140484520/O1CN01hAUgb31jGCm1weA7D_!!4140484520.jpg","https://img.alicdn.com/i2/4140484520/O1CN01GVKAwP1jGCmX34wH7_!!4140484520.jpg","https://img.alicdn.com/i1/4140484520/O1CN01vBPmsd1jGCm0nfI86_!!4140484520.jpg"],"title":"牛油果绿卫衣女秋装ins超火韩版cec时尚上衣连帽加绒冬季女装外套","tk_total_commi":"120732","tk_total_sales":"12968","user_type":1,"volume":17781,"white_image":"https://img.alicdn.com/bao/uploaded/i1/4140484520/O1CN01vBPmsd1jGCm0nfI86_!!4140484520.jpg","zk_final_price":"54.9"},{"category_id":50000671,"category_name":"T恤","commission_rate":"2001","coupon_amount":"5","coupon_end_time":"2019-10-13","coupon_id":"004f9a0f87d44321957d63c66ae5391e","coupon_info":"满32元减5元","coupon_remain_count":96754,"coupon_start_fee":"32","coupon_start_time":"2019-10-07","coupon_total_count":100000,"item_description":"收藏+购物车=赠送运费","item_id":587002978894,"item_url":"https://detail.tmall.com/item.htm?id=587002978894","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"喜简旗舰店","num_iid":587002978894,"pict_url":"https://img.alicdn.com/bao/uploaded/i3/3382002357/O1CN01d4YKLz1THY3ugiAdJ_!!0-item_pic.jpg","provcity":"福建 厦门","real_post_fee":"0.00","reserve_price":"79","seller_id":3382002357,"shop_dsr":48422,"shop_title":"喜简旗舰店","short_title":"女装2019新款潮白色女短袖宽松t恤","small_images":["https://img.alicdn.com/i2/3382002357/O1CN01OoYH7y1THY4zkyXum_!!3382002357.jpg","https://img.alicdn.com/i3/3382002357/O1CN01jrP8fq1THY32HU1TP_!!3382002357.jpg","https://img.alicdn.com/i2/3382002357/O1CN01A5a9Cl1THY3alrwA3_!!3382002357.jpg","https://img.alicdn.com/i4/3382002357/O1CN01pm2BN51THY3bSbw8F_!!3382002357.jpg"],"title":"女装2019新款潮白色t恤女短袖宽松纯棉上衣女夏百搭半袖ins韩版T","tk_total_commi":"15540.6","tk_total_sales":"3010","user_type":1,"volume":13790,"white_image":"https://img.alicdn.com/bao/uploaded/O1CN01xJ2fla1THY1WamH3J_!!2-item_pic.png","zk_final_price":"32"},{"category_id":50000671,"category_name":"T恤","commission_rate":"550","coupon_amount":"3","coupon_end_time":"2019-10-10","coupon_id":"80789335f0ac48aa87cd86a9730b798d","coupon_info":"满19元减3元","coupon_remain_count":87189,"coupon_start_fee":"19","coupon_start_time":"2019-10-01","coupon_total_count":100000,"item_description":"100%纯棉 透气吸汗 中厚保暖 可打底/外穿","item_id":601227974550,"item_url":"https://detail.tmall.com/item.htm?id=601227974550","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"恋蔻旗舰店","num_iid":601227974550,"pict_url":"https://img.alicdn.com/bao/uploaded/i1/2914300313/O1CN01Ogfz8P1EBOWtsUU2R_!!2914300313-0-pixelsss.jpg","provcity":"广东 广州","real_post_fee":"0.00","reserve_price":"39","seller_id":2914300313,"shop_dsr":48388,"shop_title":"恋蔻旗舰店","short_title":"秋冬纯棉长袖t恤初秋白色潮打底衫","small_images":["https://img.alicdn.com/i3/2914300313/O1CN01A9oTjy1EBOWQgcf26_!!2914300313.jpg","https://img.alicdn.com/i2/2914300313/O1CN01PhD2kG1EBOWfCOVs1_!!2914300313.jpg","https://img.alicdn.com/i2/2914300313/O1CN01gjutid1EBOWQ05Pmp_!!2914300313.jpg","https://img.alicdn.com/i1/2914300313/O1CN01Htiu7G1EBOWxs7Zvm_!!2914300313.jpg"],"title":"秋冬纯棉打底衫长袖t恤女内搭秋衣白色上衣女装2019新款洋气宽松","tk_total_commi":"1874.7","tk_total_sales":"1329","user_type":1,"volume":8449,"white_image":"https://img.alicdn.com/bao/uploaded/O1CN01YYAbKU1EBOWTVdofq_!!2-item_pic.png","zk_final_price":"19.9"},{"category_id":50000671,"category_name":"T恤","commission_rate":"600","coupon_amount":"5","coupon_end_time":"2019-11-02","coupon_id":"d032e507a02d4b89a10a559410b3902e","coupon_info":"满39元减5元","coupon_remain_count":4100,"coupon_start_fee":"39","coupon_start_time":"2019-10-07","coupon_total_count":5000,"item_description":"91.2%螺纹棉 好面料 精做工 颜色正 不褪色","item_id":599202981363,"item_url":"https://detail.tmall.com/item.htm?id=599202981363","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"甄度旗舰店","num_iid":599202981363,"pict_url":"https://img.alicdn.com/bao/uploaded/i1/2217148345/O1CN01rWvksJ2BW3j4DivGo_!!0-item_pic.jpg","provcity":"福建 厦门","real_post_fee":"0.00","reserve_price":"76","seller_id":2217148345,"shop_dsr":48740,"shop_title":"甄度旗舰店","short_title":"中袖黑色五分袖修身白色2019潮t恤","small_images":["https://img.alicdn.com/i3/2217148345/O1CN01TIjses2BW3itb3DDi_!!2217148345.jpg","https://img.alicdn.com/i4/2217148345/O1CN01Fb8MlY2BW3iqldPgG_!!2217148345.jpg","https://img.alicdn.com/i3/2217148345/O1CN01ziH9jX2BW3iqob5x9_!!2217148345.jpg","https://img.alicdn.com/i4/2217148345/O1CN01mvKJPl2BW3iVRSZBz_!!2217148345.jpg"],"title":"中袖t恤女黑色五分袖修身白色打底衫内搭上衣女装2019秋冬新款潮","tk_total_commi":"2072.23","tk_total_sales":"941","user_type":1,"volume":7878,"white_image":"https://img.alicdn.com/bao/uploaded/TB1ypyQh1T2gK0jSZFvXXXnFXXa.png","zk_final_price":"39"},{"category_id":1623,"category_name":"半身裙","commission_rate":"550","coupon_amount":"100","coupon_end_time":"2019-10-10","coupon_id":"5448f10a93464f6e960f0e45b254380c","coupon_info":"满139元减100元","coupon_remain_count":3000,"coupon_start_fee":"139","coupon_start_time":"2019-10-09","coupon_total_count":3000,"item_description":"","item_id":602308868213,"item_url":"https://detail.tmall.com/item.htm?id=602308868213","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"妖精的口袋旗舰店","num_iid":602308868213,"pict_url":"https://img.alicdn.com/bao/uploaded/i4/362409818/O1CN01HXmX2A2MOh8aK2Tyt_!!0-item_pic.jpg","provcity":"江苏 南京","real_post_fee":"10.00","reserve_price":"239","seller_id":362409818,"shop_dsr":48691,"shop_title":"妖精的口袋旗舰店","short_title":"妖精的口袋小香风格子2019半身裙","small_images":["https://img.alicdn.com/i1/362409818/O1CN01WGhlKK2MOh8SRGPnJ_!!362409818.jpg","https://img.alicdn.com/i4/362409818/O1CN01eKiuo22MOh8SRFg4q_!!362409818.jpg","https://img.alicdn.com/i1/362409818/O1CN01oNpB6n2MOh8SdM3IN_!!362409818.jpg","https://img.alicdn.com/i3/362409818/O1CN01tBZdNH2MOh8U5ASbj_!!362409818.jpg"],"title":"妖精的口袋小香风格子半身裙2019秋季新款女装a字毛呢不规则裙子","tk_total_commi":"33713.4","tk_total_sales":"4796","user_type":1,"volume":7707,"white_image":"https://img.alicdn.com/bao/uploaded/O1CN0112YnmY2MOh8WKB3OI_!!2-item_pic.png","zk_final_price":"139.8"},{"category_id":50008899,"category_name":"羽绒服","commission_rate":"2010","coupon_amount":"100","coupon_end_time":"2019-10-20","coupon_id":"c1ec832408fd4b5fab324b04fc1d755e","coupon_info":"满159元减100元","coupon_remain_count":89000,"coupon_start_fee":"159","coupon_start_time":"2019-09-29","coupon_total_count":100000,"item_description":"白鸭绒填充 面料柔软 做工精细","item_id":576475157528,"item_url":"https://detail.tmall.com/item.htm?id=576475157528","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"麦酷麦库旗舰店","num_iid":576475157528,"pict_url":"https://img.alicdn.com/bao/uploaded/i3/797362626/O1CN01MhjZnz1VGkcqnT3PX_!!0-item_pic.jpg","provcity":"上海","real_post_fee":"0.00","reserve_price":"299","seller_id":797362626,"shop_dsr":48947,"shop_title":"麦酷麦库旗舰店","short_title":"2019新款反季轻薄圆领保暖羽绒服","small_images":["https://img.alicdn.com/i2/797362626/O1CN01X6WcTU1VGkcsj6clu_!!797362626.jpg","https://img.alicdn.com/i2/797362626/O1CN01MbLmO01VGkcs0c48l_!!797362626.jpg","https://img.alicdn.com/i1/797362626/O1CN017P6aHy1VGkcrZlrjv_!!797362626.jpg","https://img.alicdn.com/i3/797362626/O1CN018J1eVp1VGkcrrECPO_!!797362626.jpg"],"title":"2019新款反季轻薄圆领羽绒服女保暖内胆短款大码外套秋冬女装无领","tk_total_commi":"14918.2","tk_total_sales":"943","user_type":1,"volume":7591,"white_image":"https://img.alicdn.com/bao/uploaded/O1CN01jWorih1VGkcnfgZbY_!!2-item_pic.png","zk_final_price":"179.9"},{"category_id":50010850,"category_name":"连衣裙","commission_rate":"601","coupon_amount":"5","coupon_end_time":"2019-10-13","coupon_id":"5a471e2901ec4a3bb087831366be891e","coupon_info":"满158元减5元","coupon_remain_count":79645,"coupon_start_fee":"158","coupon_start_time":"2019-09-27","coupon_total_count":100000,"item_description":"","item_id":600267572191,"item_url":"https://item.taobao.com/item.htm?id=600267572191","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"点名时间到了","num_iid":600267572191,"pict_url":"https://img.alicdn.com/bao/uploaded/i3/2107328912/O1CN01SR8sBk2FhkI8Ri2Vk_!!2107328912.jpg","provcity":"浙江 杭州","real_post_fee":"0.00","reserve_price":"398","seller_id":2107328912,"shop_dsr":48380,"shop_title":"DmTime DM家潮流女装","short_title":"秋冬季女装2019年新款法式复古碎花","small_images":["https://img.alicdn.com/i2/2107328912/O1CN01hakDhq2FhkIdaCsau_!!2107328912.jpg","https://img.alicdn.com/i2/2107328912/O1CN01rMjUGC2FhkI5CabmJ_!!2107328912.jpg","https://img.alicdn.com/i4/2107328912/O1CN01wnIzGt2FhkHyfAuCp_!!2107328912.jpg","https://img.alicdn.com/i2/2107328912/O1CN01JUtljf2FhkI1m0xYK_!!2107328912.jpg"],"title":"秋冬季女装2019年新款法式复古连衣裙碎花雪纺长袖气质长款裙子潮","tk_total_commi":"10922.5","tk_total_sales":"1182","user_type":0,"volume":7411,"white_image":"","zk_final_price":"178"},{"category_id":50008898,"category_name":"卫衣/绒衫","commission_rate":"2000","coupon_amount":"5","coupon_end_time":"2019-10-13","coupon_id":"18c82880756e448faacb68057e79b5dc","coupon_info":"满79元减5元","coupon_remain_count":6160,"coupon_start_fee":"79","coupon_start_time":"2019-10-07","coupon_total_count":8000,"item_description":"天猫正品 质量保证 售后无忧","item_id":597521074874,"item_url":"https://detail.tmall.com/item.htm?id=597521074874","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"蔻莲娜旗舰店","num_iid":597521074874,"pict_url":"https://img.alicdn.com/bao/uploaded/i4/875071047/O1CN012TYX2i1JbZDgkeSJA_!!0-item_pic.jpg","provcity":"浙江 温州","real_post_fee":"0.00","reserve_price":"158","seller_id":875071047,"shop_dsr":48159,"shop_title":"蔻莲娜旗舰店","short_title":"春秋装2019年新款宽松秋季长袖卫衣","small_images":["https://img.alicdn.com/i1/875071047/O1CN01o6ID8q1JbZEVTcuf1_!!0-item_pic.jpg","https://img.alicdn.com/i1/875071047/O1CN01o7k2BF1JbZE0X7uCe_!!0-item_pic.jpg","https://img.alicdn.com/i2/875071047/O1CN01QyHCuX1JbZDjiF7OH_!!875071047.jpg","https://img.alicdn.com/i4/875071047/O1CN01oPp2kw1JbZEBuKOAZ_!!875071047.jpg"],"title":"秋装2019年新款宽松加绒卫衣女加厚上衣秋冬季韩版潮ins女装外套","tk_total_commi":"10855.6","tk_total_sales":"1239","user_type":1,"volume":7043,"white_image":"https://img.alicdn.com/bao/uploaded/TB1LWP.bQL0gK0jSZFxXXXWHVXa.png","zk_final_price":"79"},{"category_id":50000697,"category_name":"毛针织衫","commission_rate":"550","coupon_amount":"20","coupon_end_time":"2019-10-10","coupon_id":"27cb6bc0670f4c98b033e27271bee332","coupon_info":"满99元减20元","coupon_remain_count":6336,"coupon_start_fee":"99","coupon_start_time":"2019-10-03","coupon_total_count":10000,"item_description":"","item_id":597412320833,"item_url":"https://detail.tmall.com/item.htm?id=597412320833","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"芊艺旗舰店","num_iid":597412320833,"pict_url":"https://img.alicdn.com/bao/uploaded/i1/285767803/O1CN01Xba1qg27Vp37E4h3U_!!0-item_pic.jpg","provcity":"广东 东莞","real_post_fee":"0.00","reserve_price":"202","seller_id":285767803,"shop_dsr":48588,"shop_title":"芊艺旗舰店","short_title":"针织外套女上衣2019新款网红潮毛衣","small_images":["https://img.alicdn.com/i3/285767803/O1CN01fhOBVE27Vp3AunFsG_!!285767803.jpg","https://img.alicdn.com/i2/285767803/O1CN01EMWeKE27Vp3BNwqvE_!!285767803.jpg","https://img.alicdn.com/i3/285767803/O1CN01VsOm9727Vp39xgVsT_!!285767803.jpg","https://img.alicdn.com/i2/285767803/O1CN018ThgYR27Vp37atLvm_!!285767803.jpg"],"title":"针织开衫外套女上衣2019新款女装春秋长袖秋冬女士毛衣宽松外穿潮","tk_total_commi":"5238.42","tk_total_sales":"1012","user_type":1,"volume":5731,"white_image":"https://img.alicdn.com/bao/uploaded/TB1RaYqdkP2gK0jSZPxXXacQpXa.png","zk_final_price":"99.8"},{"category_id":1629,"category_name":"大码女装","commission_rate":"500","coupon_amount":"10","coupon_end_time":"2019-10-09","coupon_id":"5041d1cfa24b493f80b4513d8df82518","coupon_info":"满59元减10元","coupon_remain_count":800,"coupon_start_fee":"59","coupon_start_time":"2019-10-09","coupon_total_count":2000,"item_description":"","item_id":603339207818,"item_url":"https://item.taobao.com/item.htm?id=603339207818","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"心宝宝roy","num_iid":603339207818,"pict_url":"https://img.alicdn.com/bao/uploaded/i4/1889175486/O1CN01OLrXXE1qOdNQS0Fso_!!1889175486.jpg","provcity":"辽宁 鞍山","real_post_fee":"0.00","reserve_price":"102","seller_id":1889175486,"shop_dsr":48464,"shop_title":"大姗姗家 FASHION SHOP大码女装独家定制","short_title":"大姗姗家胖mm小猫瘦瘦裤2019打底裤","small_images":["https://img.alicdn.com/i3/1889175486/O1CN01BGkLA61qOdNPiuN2D_!!1889175486.jpg","https://img.alicdn.com/i3/1889175486/O1CN01BTsCek1qOdNSDTYJJ_!!1889175486.jpg","https://img.alicdn.com/i2/1889175486/O1CN01SFG1qD1qOdNMk1ooZ_!!1889175486.jpg","https://img.alicdn.com/i4/1889175486/O1CN01t1QzMD1qOdNP1uBn1_!!1889175486.jpg"],"title":"大姗姗家胖mm小猫瘦瘦裤2019显瘦外穿打底裤大码女装韩版小脚潮裤","tk_total_commi":"13141.6","tk_total_sales":"4259","user_type":0,"volume":5539,"white_image":"https://img.alicdn.com/bao/uploaded/i4/1889175486/O1CN01t1QzMD1qOdNP1uBn1_!!1889175486.jpg","zk_final_price":"89.76"},{"category_id":1629,"category_name":"大码女装","commission_rate":"500","coupon_amount":"5","coupon_end_time":"2019-10-17","coupon_id":"46176bf5b3344836bf8b7d51ec02f7c1","coupon_info":"满98元减5元","coupon_remain_count":38259,"coupon_start_fee":"98","coupon_start_time":"2019-09-01","coupon_total_count":100000,"item_description":"","item_id":600570457422,"item_url":"https://item.taobao.com/item.htm?id=600570457422","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"huyiyi62714","num_iid":600570457422,"pict_url":"https://img.alicdn.com/bao/uploaded/i2/464433013/O1CN015hr6E61Y7zthmoJfv_!!464433013.jpg","provcity":"广东 广州","real_post_fee":"0.00","reserve_price":"256","seller_id":464433013,"shop_dsr":48748,"shop_title":"胖橘夫人 定制大码女装","short_title":"大码女装2019新款秋季显瘦连衣裙","small_images":["https://img.alicdn.com/i3/464433013/O1CN01DQglc01Y7ztmDgYIL_!!464433013.jpg","https://img.alicdn.com/i4/464433013/O1CN01YDnVG31Y7ztnBfntH_!!464433013.jpg","https://img.alicdn.com/i3/464433013/O1CN0121IaPx1Y7ztjiVygg_!!464433013.jpg","https://img.alicdn.com/i3/464433013/O1CN01zNirdY1Y7zttBX4QE_!!464433013.jpg"],"title":"大码女装2019新款秋季显瘦连衣裙胖妹妹时尚洋气减龄秋冬两件套装","tk_total_commi":"4048.52","tk_total_sales":"618","user_type":0,"volume":5395,"white_image":"https://img.alicdn.com/bao/uploaded/i3/464433013/O1CN01zNirdY1Y7zttBX4QE_!!464433013.jpg","zk_final_price":"138"},{"category_id":162205,"category_name":"牛仔裤","commission_rate":"550","coupon_amount":"3","coupon_end_time":"2019-10-10","coupon_id":"b2ab2c7e867a46898c8204f18abfbbb2","coupon_info":"满4元减3元","coupon_remain_count":89820,"coupon_start_fee":"4","coupon_start_time":"2019-10-06","coupon_total_count":100000,"item_description":"送运费险  显瘦显高 不规则腰头","item_id":601960542610,"item_url":"https://detail.tmall.com/item.htm?id=601960542610","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"恋人心服饰旗舰店","num_iid":601960542610,"pict_url":"https://img.alicdn.com/bao/uploaded/i1/2851318218/O1CN01f1riIl2AZtQTxoyeQ_!!0-item_pic.jpg","provcity":"广东 中山","real_post_fee":"0.00","reserve_price":"149","seller_id":2851318218,"shop_dsr":48425,"shop_title":"恋人心服饰旗舰店","short_title":"高腰女秋装2019年新款修身潮牛仔裤","small_images":["https://img.alicdn.com/i3/2851318218/O1CN01oCDvsR2AZtQOYh67W_!!2851318218.jpg","https://img.alicdn.com/i3/2851318218/O1CN017mMRh72AZtQLzC2jJ_!!2851318218.jpg","https://img.alicdn.com/i3/2851318218/O1CN01gYPeVR2AZtQj5HWDt_!!2851318218.jpg","https://img.alicdn.com/i1/2851318218/O1CN01PcRIUU2AZtQLh0W5K_!!2851318218.jpg"],"title":"高腰牛仔裤女秋装2019新款显瘦显高紧身春秋女装九分小脚加绒裤子","tk_total_commi":"2234.77","tk_total_sales":"594","user_type":1,"volume":5389,"white_image":"https://img.alicdn.com/bao/uploaded/TB1M9jue.Y1gK0jSZFMXXaWcVXa.png","zk_final_price":"79"},{"category_id":50000697,"category_name":"毛针织衫","commission_rate":"550","coupon_amount":"3","coupon_end_time":"2019-10-12","coupon_id":"21bed99fab9f4659974d8c496497a062","coupon_info":"满18元减3元","coupon_remain_count":56542,"coupon_start_fee":"18","coupon_start_time":"2019-04-14","coupon_total_count":100000,"item_description":"天猫精选及时发货时尚舒适不易变形不易起球","item_id":588731593177,"item_url":"https://detail.tmall.com/item.htm?id=588731593177","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"邻家思语旗舰店","num_iid":588731593177,"pict_url":"https://img.alicdn.com/bao/uploaded/i2/1753754381/O1CN01rVDJKm1iEXidan7Hv_!!0-item_pic.jpg","provcity":"江苏 苏州","real_post_fee":"0.00","reserve_price":"199","seller_id":1753754381,"shop_dsr":49254,"shop_title":"邻家思语旗舰店","short_title":"秋装2019新款女装时尚早秋外套毛衣","small_images":["https://img.alicdn.com/i3/1753754381/O1CN01Q38KZV1iEXfUCmTUW_!!1753754381.jpg","https://img.alicdn.com/i1/1753754381/O1CN01xjRU5k1iEXfUZcTU4_!!1753754381.jpg","https://img.alicdn.com/i2/1753754381/O1CN01hBNHQr1iEXfY60qnY_!!1753754381.jpg","https://img.alicdn.com/i1/1753754381/O1CN01yejDv61iEXfTSJnfL_!!1753754381.jpg"],"title":"秋装2019新款女装时尚早秋毛衣外套春秋宽松中长款很仙的针织开衫","tk_total_commi":"1617.93","tk_total_sales":"380","user_type":1,"volume":5312,"white_image":"https://img.alicdn.com/bao/uploaded/O1CN01UpvFgT1iEXfY62fKW_!!2-item_pic.png","zk_final_price":"83"},{"category_id":50010850,"category_name":"连衣裙","commission_rate":"1000","coupon_amount":"5","coupon_end_time":"2019-10-13","coupon_id":"8b51138002994386a8f695ca03456139","coupon_info":"满99元减5元","coupon_remain_count":100000,"coupon_start_fee":"99","coupon_start_time":"2019-10-09","coupon_total_count":100000,"item_description":"","item_id":601472178138,"item_url":"https://item.taobao.com/item.htm?id=601472178138","level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"leohui_2007","num_iid":601472178138,"pict_url":"https://img.alicdn.com/bao/uploaded/i3/76658125/O1CN01pLwsXQ29tIaWCrfT8_!!76658125.jpg","provcity":"广东 广州","real_post_fee":"0.00","reserve_price":"178","seller_id":76658125,"shop_dsr":48291,"shop_title":"新风尚 NEW Fashion","short_title":"秋冬季女装2019年新款法式连衣裙","small_images":["https://img.alicdn.com/i3/76658125/O1CN014tWusk29tIaQTBl53_!!76658125.jpg","https://img.alicdn.com/i4/76658125/O1CN01mvMhi429tIaY4Fhz2_!!76658125.jpg","https://img.alicdn.com/i2/76658125/O1CN01qhITAe29tIaY4Fycc_!!76658125.jpg","https://img.alicdn.com/i1/76658125/O1CN014KiA2o29tIaanioYQ_!!76658125.jpg"],"title":"秋冬季女装2019年新款法式复古少女长袖连衣裙初秋气质女神范衣服","tk_total_commi":"28080.9","tk_total_sales":"1126","user_type":0,"volume":4798,"white_image":"","zk_final_price":"178"}]
     */

    private String error;
    private String msg;
    private String search_type;
    private String is_similar;
    private int total_results;
    private List<ResultListBean> result_list;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public String getIs_similar() {
        return is_similar;
    }

    public void setIs_similar(String is_similar) {
        this.is_similar = is_similar;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public List<ResultListBean> getResult_list() {
        return result_list;
    }

    public void setResult_list(List<ResultListBean> result_list) {
        this.result_list = result_list;
    }

    public static class ResultListBean {
        /**
         * category_id : 162205
         * category_name : 牛仔裤
         * commission_rate : 550
         * coupon_amount : 3
         * coupon_end_time : 2019-10-10
         * coupon_id : b2ab2c7e867a46898c8204f18abfbbb2
         * coupon_info : 满4元减3元
         * coupon_remain_count : 89820
         * coupon_start_fee : 4
         * coupon_start_time : 2019-10-06
         * coupon_total_count : 100000
         * item_description : 2019韩版小黑裤 送运费险  定制面料
         * item_id : 601043792095
         * item_url : https://detail.tmall.com/item.htm?id=601043792095
         * level_one_category_id : 16
         * level_one_category_name : 女装/女士精品
         * nick : 恋人心服饰旗舰店
         * num_iid : 601043792095
         * pict_url : https://img.alicdn.com/bao/uploaded/i3/2851318218/O1CN01l2qeFN2AZtQP0hw59_!!0-item_pic.jpg
         * provcity : 广东 中山
         * real_post_fee : 0.00
         * reserve_price : 149
         * seller_id : 2851318218
         * shop_dsr : 48425
         * shop_title : 恋人心服饰旗舰店
         * short_title : 直筒黑色九分女秋装2019高腰牛仔裤
         * small_images : ["https://img.alicdn.com/i3/2851318218/O1CN01QQmlIU2AZtQNINb6a_!!2851318218.jpg","https://img.alicdn.com/i4/2851318218/O1CN01abiFlR2AZtQ8b9aPd_!!2851318218.jpg","https://img.alicdn.com/i1/2851318218/O1CN01Rs0pBF2AZtQLSpb5o_!!2851318218.jpg","https://img.alicdn.com/i2/2851318218/O1CN01OnCHGY2AZtQ9Kk1T6_!!2851318218.jpg"]
         * title : 直筒黑色九分牛仔裤女秋装2019新款高腰显瘦宽松女装秋季百搭裤子
         * tk_total_commi : 9765.26
         * tk_total_sales : 2625
         * user_type : 1
         * volume : 21342
         * white_image : https://img.alicdn.com/bao/uploaded/TB14KxLeuH2gK0jSZJnXXaT1FXa.png
         * zk_final_price : 79
         */

        private int category_id;
        private String category_name;
        private String commission_rate;
        private String coupon_amount;
        private String coupon_end_time;
        private String coupon_id;
        private String coupon_info;
        private int coupon_remain_count;
        private String coupon_start_fee;
        private String coupon_start_time;
        private int coupon_total_count;
        private String item_description;
        private String item_id;
        private String item_url;
        private int level_one_category_id;
        private String level_one_category_name;
        private String nick;
        private long num_iid;
        private String pict_url;
        private String provcity;
        private String real_post_fee;
        private String reserve_price;
        private long seller_id;
        private int shop_dsr;
        private String shop_title;
        private String short_title;
        private String title;
        private String tk_total_commi;
        private String tk_total_sales;
        private String user_type;
        private String volume;
        private String white_image;
        private String zk_final_price;
        private List<String> small_images;

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
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

        public String getCoupon_end_time() {
            return coupon_end_time;
        }

        public void setCoupon_end_time(String coupon_end_time) {
            this.coupon_end_time = coupon_end_time;
        }

        public String getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(String coupon_id) {
            this.coupon_id = coupon_id;
        }

        public String getCoupon_info() {
            return coupon_info;
        }

        public void setCoupon_info(String coupon_info) {
            this.coupon_info = coupon_info;
        }

        public int getCoupon_remain_count() {
            return coupon_remain_count;
        }

        public void setCoupon_remain_count(int coupon_remain_count) {
            this.coupon_remain_count = coupon_remain_count;
        }

        public String getCoupon_start_fee() {
            return coupon_start_fee;
        }

        public void setCoupon_start_fee(String coupon_start_fee) {
            this.coupon_start_fee = coupon_start_fee;
        }

        public String getCoupon_start_time() {
            return coupon_start_time;
        }

        public void setCoupon_start_time(String coupon_start_time) {
            this.coupon_start_time = coupon_start_time;
        }

        public int getCoupon_total_count() {
            return coupon_total_count;
        }

        public void setCoupon_total_count(int coupon_total_count) {
            this.coupon_total_count = coupon_total_count;
        }

        public String getItem_description() {
            return item_description;
        }

        public void setItem_description(String item_description) {
            this.item_description = item_description;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getItem_url() {
            return item_url;
        }

        public void setItem_url(String item_url) {
            this.item_url = item_url;
        }

        public int getLevel_one_category_id() {
            return level_one_category_id;
        }

        public void setLevel_one_category_id(int level_one_category_id) {
            this.level_one_category_id = level_one_category_id;
        }

        public String getLevel_one_category_name() {
            return level_one_category_name;
        }

        public void setLevel_one_category_name(String level_one_category_name) {
            this.level_one_category_name = level_one_category_name;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public long getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(long num_iid) {
            this.num_iid = num_iid;
        }

        public String getPict_url() {
            return pict_url;
        }

        public void setPict_url(String pict_url) {
            this.pict_url = pict_url;
        }

        public String getProvcity() {
            return provcity;
        }

        public void setProvcity(String provcity) {
            this.provcity = provcity;
        }

        public String getReal_post_fee() {
            return real_post_fee;
        }

        public void setReal_post_fee(String real_post_fee) {
            this.real_post_fee = real_post_fee;
        }

        public String getReserve_price() {
            return reserve_price;
        }

        public void setReserve_price(String reserve_price) {
            this.reserve_price = reserve_price;
        }

        public long getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(long seller_id) {
            this.seller_id = seller_id;
        }

        public int getShop_dsr() {
            return shop_dsr;
        }

        public void setShop_dsr(int shop_dsr) {
            this.shop_dsr = shop_dsr;
        }

        public String getShop_title() {
            return shop_title;
        }

        public void setShop_title(String shop_title) {
            this.shop_title = shop_title;
        }

        public String getShort_title() {
            return short_title;
        }

        public void setShort_title(String short_title) {
            this.short_title = short_title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTk_total_commi() {
            return tk_total_commi;
        }

        public void setTk_total_commi(String tk_total_commi) {
            this.tk_total_commi = tk_total_commi;
        }

        public String getTk_total_sales() {
            return tk_total_sales;
        }

        public void setTk_total_sales(String tk_total_sales) {
            this.tk_total_sales = tk_total_sales;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getWhite_image() {
            return white_image;
        }

        public void setWhite_image(String white_image) {
            this.white_image = white_image;
        }

        public String getZk_final_price() {
            return zk_final_price;
        }

        public void setZk_final_price(String zk_final_price) {
            this.zk_final_price = zk_final_price;
        }

        public List<String> getSmall_images() {
            return small_images;
        }

        public void setSmall_images(List<String> small_images) {
            this.small_images = small_images;
        }

        @Override
        public String toString() {
            return "ResultListBean{" +
                    "category_id=" + category_id +
                    ", category_name='" + category_name + '\'' +
                    ", commission_rate='" + commission_rate + '\'' +
                    ", coupon_amount='" + coupon_amount + '\'' +
                    ", coupon_end_time='" + coupon_end_time + '\'' +
                    ", coupon_id='" + coupon_id + '\'' +
                    ", coupon_info='" + coupon_info + '\'' +
                    ", coupon_remain_count=" + coupon_remain_count +
                    ", coupon_start_fee='" + coupon_start_fee + '\'' +
                    ", coupon_start_time='" + coupon_start_time + '\'' +
                    ", coupon_total_count=" + coupon_total_count +
                    ", item_description='" + item_description + '\'' +
                    ", item_id=" + item_id +
                    ", item_url='" + item_url + '\'' +
                    ", level_one_category_id=" + level_one_category_id +
                    ", level_one_category_name='" + level_one_category_name + '\'' +
                    ", nick='" + nick + '\'' +
                    ", num_iid=" + num_iid +
                    ", pict_url='" + pict_url + '\'' +
                    ", provcity='" + provcity + '\'' +
                    ", real_post_fee='" + real_post_fee + '\'' +
                    ", reserve_price='" + reserve_price + '\'' +
                    ", seller_id=" + seller_id +
                    ", shop_dsr=" + shop_dsr +
                    ", shop_title='" + shop_title + '\'' +
                    ", short_title='" + short_title + '\'' +
                    ", title='" + title + '\'' +
                    ", tk_total_commi='" + tk_total_commi + '\'' +
                    ", tk_total_sales='" + tk_total_sales + '\'' +
                    ", user_type=" + user_type +
                    ", volume=" + volume +
                    ", white_image='" + white_image + '\'' +
                    ", zk_final_price='" + zk_final_price + '\'' +
                    ", small_images=" + small_images +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TBGoodsRecBean{" +
                "error='" + error + '\'' +
                ", msg='" + msg + '\'' +
                ", search_type='" + search_type + '\'' +
                ", is_similar='" + is_similar + '\'' +
                ", total_results=" + total_results +
                ", result_list=" + result_list +
                '}';
    }

}
