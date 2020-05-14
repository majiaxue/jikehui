package com.example.bean;

import java.util.List;

public class CheckUpBean {

    /**
     * id : 4
     * version : 3.0
     * type : 0
     * url : http://192.168.1.17:9000/version/app-release.apk?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=0KFVAJ4BUZ7J1F7GFUSQ/20190702//s3/aws4_request&X-Amz-Date=20190702T031437Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=a985fa26873dad8c3f5911346bed49eb0acfc8e54f6a73a9dfe9ccb4cf8f0d75
     * appDescribe : 测试第一版
     * content : 测试升级
     * isForce : 1
     * isRelease : 1
     * createTime : 2019-07-02 09:54:24
     * upgradeType : 0
     * list : [{"id":1,"version":"2.0","type":"0","url":"http://192.168.1.17:9000/version/app-release.apk?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=0KFVAJ4BUZ7J1F7GFUSQ/20190702//s3/aws4_request&X-Amz-Date=20190702T031437Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=a985fa26873dad8c3f5911346bed49eb0acfc8e54f6a73a9dfe9ccb4cf8f0d75","appDescribe":"测试第一版","content":"测试升级","isForce":1,"isRelease":1,"createTime":"2019-07-02 09:54:24","upgradeType":1},{"id":2,"version":"2.1","type":"0","url":"http://192.168.1.17:9000/version/app-release.apk?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=0KFVAJ4BUZ7J1F7GFUSQ/20190702//s3/aws4_request&X-Amz-Date=20190702T031437Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=a985fa26873dad8c3f5911346bed49eb0acfc8e54f6a73a9dfe9ccb4cf8f0d75","appDescribe":"测试第一版","content":"测试升级","isForce":1,"isRelease":1,"createTime":"2019-07-02 09:54:24","upgradeType":1},{"id":3,"version":"2.2","type":"0","url":"http://192.168.1.17:9000/version/app-release.apk?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=0KFVAJ4BUZ7J1F7GFUSQ/20190702//s3/aws4_request&X-Amz-Date=20190702T031437Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=a985fa26873dad8c3f5911346bed49eb0acfc8e54f6a73a9dfe9ccb4cf8f0d75","appDescribe":"测试第一版","content":"测试升级","isForce":1,"isRelease":1,"createTime":"2019-07-02 09:54:24","upgradeType":1}]
     */

    private String id;
    private String version;
    private String type;
    private String url;
    private String appDescribe;
    private String content;
    private String isForce;
    private String isRelease;
    private String createTime;
    private String upgradeType;
    private List<ListBean> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppDescribe() {
        return appDescribe;
    }

    public void setAppDescribe(String appDescribe) {
        this.appDescribe = appDescribe;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsForce() {
        return isForce;
    }

    public void setIsForce(String isForce) {
        this.isForce = isForce;
    }

    public String getIsRelease() {
        return isRelease;
    }

    public void setIsRelease(String isRelease) {
        this.isRelease = isRelease;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpgradeType() {
        return upgradeType;
    }

    public void setUpgradeType(String upgradeType) {
        this.upgradeType = upgradeType;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "CheckUpBean{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", appDescribe='" + appDescribe + '\'' +
                ", content='" + content + '\'' +
                ", isForce='" + isForce + '\'' +
                ", isRelease='" + isRelease + '\'' +
                ", createTime='" + createTime + '\'' +
                ", upgradeType='" + upgradeType + '\'' +
                ", list=" + list +
                '}';
    }

    public static class ListBean {
        /**
         * id : 1
         * version : 2.0
         * type : 0
         * url : http://192.168.1.17:9000/version/app-release.apk?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=0KFVAJ4BUZ7J1F7GFUSQ/20190702//s3/aws4_request&X-Amz-Date=20190702T031437Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=a985fa26873dad8c3f5911346bed49eb0acfc8e54f6a73a9dfe9ccb4cf8f0d75
         * appDescribe : 测试第一版
         * content : 测试升级
         * isForce : 1
         * isRelease : 1
         * createTime : 2019-07-02 09:54:24
         * upgradeType : 1
         */

        private String id;
        private String version;
        private String type;
        private String url;
        private String appDescribe;
        private String content;
        private String isForce;
        private String isRelease;
        private String createTime;
        private String upgradeType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAppDescribe() {
            return appDescribe;
        }

        public void setAppDescribe(String appDescribe) {
            this.appDescribe = appDescribe;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIsForce() {
            return isForce;
        }

        public void setIsForce(String isForce) {
            this.isForce = isForce;
        }

        public String getIsRelease() {
            return isRelease;
        }

        public void setIsRelease(String isRelease) {
            this.isRelease = isRelease;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpgradeType() {
            return upgradeType;
        }

        public void setUpgradeType(String upgradeType) {
            this.upgradeType = upgradeType;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "id='" + id + '\'' +
                    ", version='" + version + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", appDescribe='" + appDescribe + '\'' +
                    ", content='" + content + '\'' +
                    ", isForce='" + isForce + '\'' +
                    ", isRelease='" + isRelease + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", upgradeType='" + upgradeType + '\'' +
                    '}';
        }
    }
}
