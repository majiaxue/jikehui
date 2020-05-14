package com.example.bean;

import java.io.Serializable;
import java.util.List;

public class ProductCenterBean implements Serializable {
    /**
     * records : [{"id":4,"logo":"http://192.168.0.118:9000/goods/4a7f5d8b907742308f7e0f9e48de3157.png","title":"测试1","message":"测试","price":99,"info":"<p><img src=\"http://47.99.93.123:8083/goods/d26d9d2509934b75be0eec8158cb0199.jpg\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/2661b2bab1bc49de93f8b7995f35b05c.jpg\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/1104d05177974e7fa52aaa3cb0f303ed.jpg\"><\/p>","pic":"http://192.168.0.118:9000/goods/410f8fb4b2bb4dd1aa2271668596e8e9.png","createTime":null,"updateTime":"2019-11-22 10:37:15","sort":2,"status":1,"phone":"13523565115","name":"销售A","categoryId":1,"testName":"APP,后台","testAddress":"www.xxx.com,www.bbb.com","testAccount":"test1,test2","testPassword":"123,456"},{"id":12,"logo":"http://192.168.0.118:9000/goods/4a7f5d8b907742308f7e0f9e48de3157.png","title":"测试6","message":"测试","price":99,"info":"<p><img src=\"http://47.99.93.123:8083/goods/432229703cd04e3aa1c9afa78a6a485d.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 790px; height: 392px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/aa0657400fdc4baea239bda61c454999.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 790px; height: 1119px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/ba0dda34e2974533bba3924a4d4ccff1.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 790px; height: 920px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/4fbdd1e3aeef44cfbc3b029bfc12d158.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 790px; height: 1463px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/314d44264a7147b29f7c02d1a3d100d7.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 790px; height: 696px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/5bbefd90937441ebb396381b6bf26e5b.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 790px; height: 1016px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/1a0b01dbbae24eb4a13be2cfa32f1786.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 790px; height: 1103px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/c469f69aa9574b589947b29a81806a3c.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 790px; height: 928px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/5c8757ea81a74a88813d38ad186d31b0.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 790px; height: 1535px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/0bd214d3a8764e96b1f993b0bedac13f.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 790px; height: 768px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/0fe76663283b49b4977563a68ce747ec.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 790px; height: 1519px;\"><\/p>","pic":"http://192.168.0.118:9000/goods/410f8fb4b2bb4dd1aa2271668596e8e9.png","createTime":null,"updateTime":"2019-11-22 10:37:15","sort":2,"status":1,"phone":"13523565115","name":"销售A","categoryId":1,"testName":"APP,后台","testAddress":"www.xxx.com,www.bbb.com","testAccount":"test1,test2","testPassword":"123,456"},{"id":13,"logo":"http://192.168.0.118:9000/goods/4a7f5d8b907742308f7e0f9e48de3157.png","title":"测试7","message":"测试","price":99,"info":"<p><img src=\"http://47.99.93.123:8083/goods/475d2d2197f447758791dcac7470b6c1.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 750px; height: 777px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/79ad7c70858541a1b257ec922427de74.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 750px; height: 1194px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/dac69e7d49fd40deac438eb0f6a2704f.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 750px; height: 1078px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/cb66a8baee3f4b98930db1653c2bf091.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 750px; height: 1271px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/7519177545554590b812c38c3bb4f6b3.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 750px; height: 1198px;\"><\/p><p><img src=\"http://47.99.93.123:8083/goods/4a0e760341ae4fb19cfe9ea30de10e4b.jpg\" alt=\"\" class=\"avue-ueditor__img-img\" style=\"width: 721px; height: 1600px;\"><\/p>","pic":"http://192.168.0.118:9000/goods/410f8fb4b2bb4dd1aa2271668596e8e9.png","createTime":null,"updateTime":"2019-11-22 10:37:15","sort":2,"status":1,"phone":"13523565115","name":"销售A","categoryId":1,"testName":"APP,后台","testAddress":"www.xxx.com,www.bbb.com","testAccount":"test1,test2","testPassword":"123,456"},{"id":1,"logo":"http://47.99.93.123:8083/parameter/ea2542daf5cc40f695a267645bded688.png","title":"1111","message":"1111","price":100,"info":"<p><img src=\"http://47.99.93.123:8083/goods/4d1f037cf4ab4ee28e6476f933e38c82.jpg\"><\/p>","pic":"165165","createTime":null,"updateTime":"2019-11-22 10:37:12","sort":1,"status":1,"phone":"13523565115","name":"销售A","categoryId":1,"testName":"APP,后台","testAddress":"www.xxx.com,www.bbb.com","testAccount":"test1,test2","testPassword":"123,456"}]
     * total : 4
     * size : 10
     * current : 1
     * searchCount : true
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private boolean searchCount;
    private int pages;
    private List<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean implements Serializable{
        /**
         * id : 4
         * logo : http://192.168.0.118:9000/goods/4a7f5d8b907742308f7e0f9e48de3157.png
         * title : 测试1
         * message : 测试
         * price : 99
         * info : <p><img src="http://47.99.93.123:8083/goods/d26d9d2509934b75be0eec8158cb0199.jpg"></p><p><img src="http://47.99.93.123:8083/goods/2661b2bab1bc49de93f8b7995f35b05c.jpg"></p><p><img src="http://47.99.93.123:8083/goods/1104d05177974e7fa52aaa3cb0f303ed.jpg"></p>
         * pic : http://192.168.0.118:9000/goods/410f8fb4b2bb4dd1aa2271668596e8e9.png
         * createTime : null
         * updateTime : 2019-11-22 10:37:15
         * sort : 2
         * status : 1
         * phone : 13523565115
         * name : 销售A
         * categoryId : 1
         * testName : APP,后台
         * testAddress : www.xxx.com,www.bbb.com
         * testAccount : test1,test2
         * testPassword : 123,456
         */

        private Long id;
        private String logo;
        private String title;
        private String message;
        private double price;
        private String info;
        private String pic;
        private String createTime;
        private String updateTime;
        private int sort;
        private int status;
        private String phone;
        private String name;
        private int categoryId;
        private String testName;
        private String testAddress;
        private String testAccount;
        private String testPassword;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getTestName() {
            return testName;
        }

        public void setTestName(String testName) {
            this.testName = testName;
        }

        public String getTestAddress() {
            return testAddress;
        }

        public void setTestAddress(String testAddress) {
            this.testAddress = testAddress;
        }

        public String getTestAccount() {
            return testAccount;
        }

        public void setTestAccount(String testAccount) {
            this.testAccount = testAccount;
        }

        public String getTestPassword() {
            return testPassword;
        }

        public void setTestPassword(String testPassword) {
            this.testPassword = testPassword;
        }
    }
}
