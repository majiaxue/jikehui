package com.example.bean;

/**
 * Created by cuihaohao on 2019/6/5
 * Describe:
 */
public class SuperBrandBean {

    /**
     * id : 7
     * categoryId : 0
     * pic : http://47.99.93.123:8083/category/c1db892a899040c58ceee768eb686e6b.png
     * sellerName : 苏宁
     * address : https://suning.tmall.com/search.htm?spm=a220m.1000858.1000725.3.460e2a68fX5ui1&user_number_id=2616970884&rn=3a8d59aa0f5598bcdc43fe6863022075&keyword=%CA%D6%BB%FA
     * ratio : 5
     * tenantId : 1
     */

    private String id;
    private String categoryId;
    private String pic;
    private String sellerName;
    private String address;
    private String ratio;
    private String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "SuperBrandBean{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", pic='" + pic + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", address='" + address + '\'' +
                ", ratio=" + ratio +
                ", tenantId=" + tenantId +
                '}';
    }
}
