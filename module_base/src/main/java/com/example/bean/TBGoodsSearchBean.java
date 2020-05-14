package com.example.bean;

/**
 * Created by cuihaohao on 2019/6/14
 * Describe:
 */
public class TBGoodsSearchBean {

    /**
     * id : null
     * cat_name : 女装
     * cat_icon : https://img.alicdn.com/imgextra/i1/2053469401/TB2oX82HL9TBuNjy0FcXXbeiFXa-2053469401.png
     * category_id : 16
     * parent_cid : 0
     */

    private Object id;
    private String cat_name;
    private String cat_icon;
    private String category_id;
    private String parent_cid;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_icon() {
        return cat_icon;
    }

    public void setCat_icon(String cat_icon) {
        this.cat_icon = cat_icon;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getParent_cid() {
        return parent_cid;
    }

    public void setParent_cid(String parent_cid) {
        this.parent_cid = parent_cid;
    }
}
