package com.example.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/22
 * Describe:
 */
public class GoodsCollectionRecBean {

    private List<RecordsBean> records;

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * id : 27
         * productCategoryId : 91
         * feightTemplateId : 0
         * productAttributeCategoryId : 1
         * name : 志愿者马甲定制订做工作服广告服装定做宣传义工红马甲背心印LOGO
         * pic : http://192.168.1.5:9000/goods/19be348f82c047f99743f0041505b9fa.jpg
         * productSn : 100001
         * deleteStatus : 0
         * publishStatus : 1
         * newStatus : 1
         * recommandStatus : 1
         * verifyStatus : 1
         * sort : 0
         * sale : 2
         * price : 111
         * promotionPrice : 1
         * giftGrowth : 1
         * giftPoint : 1
         * usePointLimit : 1
         * subTitle : 批发走量、采用环保印刷、五件起印
         * description : 1
         * originalPrice : 0
         * stock : 0
         * lowStock : 0
         * unit :
         * weight : 0
         * previewStatus : 1
         * serviceIds : 1,2,3
         * keywords : 批发走量、采用环保印刷、五件起印
         * note : 批发走量、采用环保印刷、五件起印
         * <p>
         * 批发走量、采用环保印刷、五件起印
         * albumPics : http://192.168.1.5:9000/goods/3b767eb57d23484f94f56b36211d37c8.jpg,http://192.168.1.5:9000/goods/04c57e776545434ba9072ebecb9bbdb2.jpg,http://192.168.1.5:9000/goods/17f3224113e2493da09c35cbaf16f804.jpg
         * detailTitle : 志愿者马甲定制订做工作服广告服装定做宣传义工红马甲背心印LOGO
         * detailDesc : 批发走量、采用环保印刷、五件起印
         * detailHtml : <p><img src="http://192.168.1.5:9000/goods/fda718df9a194b79a180e68f38739b8c.jpg"></p><p><img src="http://192.168.1.5:9000/goods/643882c7127b4eb7b7d651370426e06b.jpg"></p><p><img src="http://192.168.1.5:9000/goods/4bc0b94e6b764e629d70eeb0e9a4c296.jpg"></p><p><img src="http://192.168.1.5:9000/goods/d0473a3d209944d29c4b606a69bb1022.jpg">123123123123</p><p><img src=""></p><p><img src=""></p>
         * detailMobileHtml :
         * promotionStartTime : null
         * promotionEndTime : null
         * promotionPerLimit : 0
         * promotionType : 3
         * brandName :
         * productCategoryName : 长裙
         * supplyId : null
         * createTime : null
         * sellerId : 11
         * sellerName : Allisjoy/舞悦时节
         * goodReputation : 100.0%
         */

        private int id;
        private int productCategoryId;
        private int feightTemplateId;
        private int productAttributeCategoryId;
        private String name;
        private String pic;
        private String productSn;
        private int deleteStatus;
        private int publishStatus;
        private int newStatus;
        private int recommandStatus;
        private int verifyStatus;
        private int sort;
        private int sale;
        private int price;
        private int promotionPrice;
        private int giftGrowth;
        private int giftPoint;
        private int usePointLimit;
        private String subTitle;
        private String description;
        private int originalPrice;
        private int stock;
        private int lowStock;
        private String unit;
        private int weight;
        private int previewStatus;
        private String serviceIds;
        private String keywords;
        private String note;
        private String albumPics;
        private String detailTitle;
        private String detailDesc;
        private String detailHtml;
        private String detailMobileHtml;
        private Object promotionStartTime;
        private Object promotionEndTime;
        private int promotionPerLimit;
        private int promotionType;
        private String brandName;
        private String productCategoryName;
        private Object supplyId;
        private Object createTime;
        private int sellerId;
        private String sellerName;
        private String goodReputation;
        private boolean isCheck;
        private int favoriteId;

        public int getFavoriteId() {
            return favoriteId;
        }

        public void setFavoriteId(int favoriteId) {
            this.favoriteId = favoriteId;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProductCategoryId() {
            return productCategoryId;
        }

        public void setProductCategoryId(int productCategoryId) {
            this.productCategoryId = productCategoryId;
        }

        public int getFeightTemplateId() {
            return feightTemplateId;
        }

        public void setFeightTemplateId(int feightTemplateId) {
            this.feightTemplateId = feightTemplateId;
        }

        public int getProductAttributeCategoryId() {
            return productAttributeCategoryId;
        }

        public void setProductAttributeCategoryId(int productAttributeCategoryId) {
            this.productAttributeCategoryId = productAttributeCategoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getProductSn() {
            return productSn;
        }

        public void setProductSn(String productSn) {
            this.productSn = productSn;
        }

        public int getDeleteStatus() {
            return deleteStatus;
        }

        public void setDeleteStatus(int deleteStatus) {
            this.deleteStatus = deleteStatus;
        }

        public int getPublishStatus() {
            return publishStatus;
        }

        public void setPublishStatus(int publishStatus) {
            this.publishStatus = publishStatus;
        }

        public int getNewStatus() {
            return newStatus;
        }

        public void setNewStatus(int newStatus) {
            this.newStatus = newStatus;
        }

        public int getRecommandStatus() {
            return recommandStatus;
        }

        public void setRecommandStatus(int recommandStatus) {
            this.recommandStatus = recommandStatus;
        }

        public int getVerifyStatus() {
            return verifyStatus;
        }

        public void setVerifyStatus(int verifyStatus) {
            this.verifyStatus = verifyStatus;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getSale() {
            return sale;
        }

        public void setSale(int sale) {
            this.sale = sale;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getPromotionPrice() {
            return promotionPrice;
        }

        public void setPromotionPrice(int promotionPrice) {
            this.promotionPrice = promotionPrice;
        }

        public int getGiftGrowth() {
            return giftGrowth;
        }

        public void setGiftGrowth(int giftGrowth) {
            this.giftGrowth = giftGrowth;
        }

        public int getGiftPoint() {
            return giftPoint;
        }

        public void setGiftPoint(int giftPoint) {
            this.giftPoint = giftPoint;
        }

        public int getUsePointLimit() {
            return usePointLimit;
        }

        public void setUsePointLimit(int usePointLimit) {
            this.usePointLimit = usePointLimit;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(int originalPrice) {
            this.originalPrice = originalPrice;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getLowStock() {
            return lowStock;
        }

        public void setLowStock(int lowStock) {
            this.lowStock = lowStock;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getPreviewStatus() {
            return previewStatus;
        }

        public void setPreviewStatus(int previewStatus) {
            this.previewStatus = previewStatus;
        }

        public String getServiceIds() {
            return serviceIds;
        }

        public void setServiceIds(String serviceIds) {
            this.serviceIds = serviceIds;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getAlbumPics() {
            return albumPics;
        }

        public void setAlbumPics(String albumPics) {
            this.albumPics = albumPics;
        }

        public String getDetailTitle() {
            return detailTitle;
        }

        public void setDetailTitle(String detailTitle) {
            this.detailTitle = detailTitle;
        }

        public String getDetailDesc() {
            return detailDesc;
        }

        public void setDetailDesc(String detailDesc) {
            this.detailDesc = detailDesc;
        }

        public String getDetailHtml() {
            return detailHtml;
        }

        public void setDetailHtml(String detailHtml) {
            this.detailHtml = detailHtml;
        }

        public String getDetailMobileHtml() {
            return detailMobileHtml;
        }

        public void setDetailMobileHtml(String detailMobileHtml) {
            this.detailMobileHtml = detailMobileHtml;
        }

        public Object getPromotionStartTime() {
            return promotionStartTime;
        }

        public void setPromotionStartTime(Object promotionStartTime) {
            this.promotionStartTime = promotionStartTime;
        }

        public Object getPromotionEndTime() {
            return promotionEndTime;
        }

        public void setPromotionEndTime(Object promotionEndTime) {
            this.promotionEndTime = promotionEndTime;
        }

        public int getPromotionPerLimit() {
            return promotionPerLimit;
        }

        public void setPromotionPerLimit(int promotionPerLimit) {
            this.promotionPerLimit = promotionPerLimit;
        }

        public int getPromotionType() {
            return promotionType;
        }

        public void setPromotionType(int promotionType) {
            this.promotionType = promotionType;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getProductCategoryName() {
            return productCategoryName;
        }

        public void setProductCategoryName(String productCategoryName) {
            this.productCategoryName = productCategoryName;
        }

        public Object getSupplyId() {
            return supplyId;
        }

        public void setSupplyId(Object supplyId) {
            this.supplyId = supplyId;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getGoodReputation() {
            return goodReputation;
        }

        public void setGoodReputation(String goodReputation) {
            this.goodReputation = goodReputation;
        }
    }
}
