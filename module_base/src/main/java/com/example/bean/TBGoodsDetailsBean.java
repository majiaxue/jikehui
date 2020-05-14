package com.example.bean;

import java.util.List;

public class TBGoodsDetailsBean {

    /**
     * n_tbk_item : {"cat_name":"传统滋补营养品","zk_final_price":"68","cat_leaf_name":"传统滋补品其他","num_iid":"566861154395","pict_url":"https://img.alicdn.com/bao/uploaded/i3/3278510613/O1CN01BIdMi41GOnKHMiczo_!!0-item_pic.jpg","title":"辣木籽的功效印度种子进口包邮特级食用野生辣木 籽买3送2共1斤装","nick":"百年修正旗舰店","volume":"13783","material_lib_type":"1,2","user_type":"1","provcity":"云南 文山","item_url":"https://detail.tmall.com/item.htm?id=566861154395","small_images":{"string":["https://img.alicdn.com/i3/3278510613/TB2cCOvinCWBKNjSZFtXXaC3FXa_!!3278510613.jpg","https://img.alicdn.com/i4/3278510613/O1CN01CnWE6u1GOnKWvylzK_!!3278510613.jpg","https://img.alicdn.com/i4/3278510613/O1CN01zijF4g1GOnIX1vuIo_!!3278510613.jpg","https://img.alicdn.com/i2/3278510613/O1CN011FzvXF1GOnGxy9bMC_!!3278510613.jpg"]},"reserve_price":"128","seller_id":"3278510613"}
     */

    private NTbkItemBean n_tbk_item;

    public NTbkItemBean getN_tbk_item() {
        return n_tbk_item;
    }

    public void setN_tbk_item(NTbkItemBean n_tbk_item) {
        this.n_tbk_item = n_tbk_item;
    }

    public static class NTbkItemBean {
        /**
         * cat_name : 传统滋补营养品
         * zk_final_price : 68
         * cat_leaf_name : 传统滋补品其他
         * num_iid : 566861154395
         * pict_url : https://img.alicdn.com/bao/uploaded/i3/3278510613/O1CN01BIdMi41GOnKHMiczo_!!0-item_pic.jpg
         * title : 辣木籽的功效印度种子进口包邮特级食用野生辣木 籽买3送2共1斤装
         * nick : 百年修正旗舰店
         * volume : 13783
         * material_lib_type : 1,2
         * user_type : 1
         * provcity : 云南 文山
         * item_url : https://detail.tmall.com/item.htm?id=566861154395
         * small_images : {"string":["https://img.alicdn.com/i3/3278510613/TB2cCOvinCWBKNjSZFtXXaC3FXa_!!3278510613.jpg","https://img.alicdn.com/i4/3278510613/O1CN01CnWE6u1GOnKWvylzK_!!3278510613.jpg","https://img.alicdn.com/i4/3278510613/O1CN01zijF4g1GOnIX1vuIo_!!3278510613.jpg","https://img.alicdn.com/i2/3278510613/O1CN011FzvXF1GOnGxy9bMC_!!3278510613.jpg"]}
         * reserve_price : 128
         * seller_id : 3278510613
         */

        private String cat_name;
        private String zk_final_price;
        private String cat_leaf_name;
        private String num_iid;
        private String pict_url;
        private String title;
        private String nick;
        private String volume;
        private String material_lib_type;
        private String user_type;
        private String provcity;
        private String item_url;
        private SmallImagesBean small_images;
        private String reserve_price;
        private String seller_id;

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getZk_final_price() {
            return zk_final_price;
        }

        public void setZk_final_price(String zk_final_price) {
            this.zk_final_price = zk_final_price;
        }

        public String getCat_leaf_name() {
            return cat_leaf_name;
        }

        public void setCat_leaf_name(String cat_leaf_name) {
            this.cat_leaf_name = cat_leaf_name;
        }

        public String getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(String num_iid) {
            this.num_iid = num_iid;
        }

        public String getPict_url() {
            return pict_url;
        }

        public void setPict_url(String pict_url) {
            this.pict_url = pict_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getMaterial_lib_type() {
            return material_lib_type;
        }

        public void setMaterial_lib_type(String material_lib_type) {
            this.material_lib_type = material_lib_type;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getProvcity() {
            return provcity;
        }

        public void setProvcity(String provcity) {
            this.provcity = provcity;
        }

        public String getItem_url() {
            return item_url;
        }

        public void setItem_url(String item_url) {
            this.item_url = item_url;
        }

        public SmallImagesBean getSmall_images() {
            return small_images;
        }

        public void setSmall_images(SmallImagesBean small_images) {
            this.small_images = small_images;
        }

        public String getReserve_price() {
            return reserve_price;
        }

        public void setReserve_price(String reserve_price) {
            this.reserve_price = reserve_price;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public static class SmallImagesBean {
            private List<String> string;

            public List<String> getString() {
                return string;
            }

            public void setString(List<String> string) {
                this.string = string;
            }
        }

        @Override
        public String toString() {
            return "NTbkItemBean{" +
                    "cat_name='" + cat_name + '\'' +
                    ", zk_final_price='" + zk_final_price + '\'' +
                    ", cat_leaf_name='" + cat_leaf_name + '\'' +
                    ", num_iid='" + num_iid + '\'' +
                    ", pict_url='" + pict_url + '\'' +
                    ", title='" + title + '\'' +
                    ", nick='" + nick + '\'' +
                    ", volume='" + volume + '\'' +
                    ", material_lib_type='" + material_lib_type + '\'' +
                    ", user_type='" + user_type + '\'' +
                    ", provcity='" + provcity + '\'' +
                    ", item_url='" + item_url + '\'' +
                    ", small_images=" + small_images +
                    ", reserve_price='" + reserve_price + '\'' +
                    ", seller_id='" + seller_id + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TBGoodsDetailsBean{" +
                "n_tbk_item=" + n_tbk_item +
                '}';
    }
}
