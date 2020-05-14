package com.example.bean;

public class MyPointsBean {

    /**
     * member : {"id":81,"userCode":"298242555449966592","username":"13201835918","password":"$2a$10$MRQbFykd2savGuHv26dswu3lei4k2VtXzC5/uYy5qyRyupFvGZUhK","realName":null,"nickname":"秋水共长天一色","phone":"13201835918","status":1,"createTime":"2019-06-23 15:26:52","icon":"http://192.168.1.17:9000/member/9697fb3bce79412a88f5b41d997bf840.jpg","gender":null,"birthday":null,"province":null,"city":null,"job":null,"personalizedSignature":null,"sourceType":null,"integration":502,"growth":null,"luckeyCount":null,"historyIntegration":null,"weixinOpenid":"oC5Gu5k8hOSlrbXkYgh-YDHkva48","inviteCode":"VsVqWO","blance":1000,"levelId":1,"backMoney":0,"notBackMoney":0,"checkCode":null,"oldPassword":null,"newPassword":null,"oldPhone":null,"token":null,"level":null,"tenantId":1,"totalBackMoney":1,"firstFansNum":1,"selfOrderNum":1,"recommendNum":1,"relationId":"1234","aliAccount":null,"cashOutIntegration":0,"integrationCashOutNote":"100积分=1元，最小提现金额为100个积分，手续费为10%，提现必须是5的倍数"}
     * integrationConf : {"id":2,"ratio":100,"min":100,"serviceRatio":10,"tenantId":1,"createTime":"2019-07-09 09:17:18","updateTime":"2019-07-09 10:03:01","status":1,"multiple":5}
     */

    private MemberBean member;
    private IntegrationConfBean integrationConf;

    @Override
    public String toString() {
        return "MyPointsBean{" +
                "member=" + member +
                ", integrationConf=" + integrationConf +
                '}';
    }

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public IntegrationConfBean getIntegrationConf() {
        return integrationConf;
    }

    public void setIntegrationConf(IntegrationConfBean integrationConf) {
        this.integrationConf = integrationConf;
    }

    public static class MemberBean {
        /**
         * id : 81
         * userCode : 298242555449966592
         * username : 13201835918
         * password : $2a$10$MRQbFykd2savGuHv26dswu3lei4k2VtXzC5/uYy5qyRyupFvGZUhK
         * realName : null
         * nickname : 秋水共长天一色
         * phone : 13201835918
         * status : 1
         * createTime : 2019-06-23 15:26:52
         * icon : http://192.168.1.17:9000/member/9697fb3bce79412a88f5b41d997bf840.jpg
         * gender : null
         * birthday : null
         * province : null
         * city : null
         * job : null
         * personalizedSignature : null
         * sourceType : null
         * integration : 502
         * growth : null
         * luckeyCount : null
         * historyIntegration : null
         * weixinOpenid : oC5Gu5k8hOSlrbXkYgh-YDHkva48
         * inviteCode : VsVqWO
         * blance : 1000
         * levelId : 1
         * backMoney : 0
         * notBackMoney : 0
         * checkCode : null
         * oldPassword : null
         * newPassword : null
         * oldPhone : null
         * token : null
         * level : null
         * tenantId : 1
         * totalBackMoney : 1
         * firstFansNum : 1
         * selfOrderNum : 1
         * recommendNum : 1
         * relationId : 1234
         * aliAccount : null
         * cashOutIntegration : 0
         * integrationCashOutNote : 100积分=1元，最小提现金额为100个积分，手续费为10%，提现必须是5的倍数
         */

        private String id;
        private String userCode;
        private String username;
        private String password;
        private String realName;
        private String nickname;
        private String phone;
        private String status;
        private String createTime;
        private String icon;
        private String gender;
        private String birthday;
        private String province;
        private String city;
        private String job;
        private String personalizedSignature;
        private String sourceType;
        private String integration;
        private String growth;
        private String luckeyCount;
        private String historyIntegration;
        private String weixinOpenid;
        private String inviteCode;
        private String blance;
        private String levelId;
        private String backMoney;
        private String notBackMoney;
        private String checkCode;
        private String oldPassword;
        private String newPassword;
        private String oldPhone;
        private String token;
        private String level;
        private String tenantId;
        private String totalBackMoney;
        private String firstFansNum;
        private String selfOrderNum;
        private String recommendNum;
        private String relationId;
        private String aliAccount;
        private String cashOutIntegration;
        private String integrationCashOutNote;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getPersonalizedSignature() {
            return personalizedSignature;
        }

        public void setPersonalizedSignature(String personalizedSignature) {
            this.personalizedSignature = personalizedSignature;
        }

        public String getSourceType() {
            return sourceType;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }

        public String getIntegration() {
            return integration;
        }

        public void setIntegration(String integration) {
            this.integration = integration;
        }

        public String getGrowth() {
            return growth;
        }

        public void setGrowth(String growth) {
            this.growth = growth;
        }

        public String getLuckeyCount() {
            return luckeyCount;
        }

        public void setLuckeyCount(String luckeyCount) {
            this.luckeyCount = luckeyCount;
        }

        public String getHistoryIntegration() {
            return historyIntegration;
        }

        public void setHistoryIntegration(String historyIntegration) {
            this.historyIntegration = historyIntegration;
        }

        public String getWeixinOpenid() {
            return weixinOpenid;
        }

        public void setWeixinOpenid(String weixinOpenid) {
            this.weixinOpenid = weixinOpenid;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public String getBlance() {
            return blance;
        }

        public void setBlance(String blance) {
            this.blance = blance;
        }

        public String getLevelId() {
            return levelId;
        }

        public void setLevelId(String levelId) {
            this.levelId = levelId;
        }

        public String getBackMoney() {
            return backMoney;
        }

        public void setBackMoney(String backMoney) {
            this.backMoney = backMoney;
        }

        public String getNotBackMoney() {
            return notBackMoney;
        }

        public void setNotBackMoney(String notBackMoney) {
            this.notBackMoney = notBackMoney;
        }

        public String getCheckCode() {
            return checkCode;
        }

        public void setCheckCode(String checkCode) {
            this.checkCode = checkCode;
        }

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        public String getOldPhone() {
            return oldPhone;
        }

        public void setOldPhone(String oldPhone) {
            this.oldPhone = oldPhone;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getTotalBackMoney() {
            return totalBackMoney;
        }

        public void setTotalBackMoney(String totalBackMoney) {
            this.totalBackMoney = totalBackMoney;
        }

        public String getFirstFansNum() {
            return firstFansNum;
        }

        public void setFirstFansNum(String firstFansNum) {
            this.firstFansNum = firstFansNum;
        }

        public String getSelfOrderNum() {
            return selfOrderNum;
        }

        public void setSelfOrderNum(String selfOrderNum) {
            this.selfOrderNum = selfOrderNum;
        }

        public String getRecommendNum() {
            return recommendNum;
        }

        public void setRecommendNum(String recommendNum) {
            this.recommendNum = recommendNum;
        }

        public String getRelationId() {
            return relationId;
        }

        public void setRelationId(String relationId) {
            this.relationId = relationId;
        }

        public String getAliAccount() {
            return aliAccount;
        }

        public void setAliAccount(String aliAccount) {
            this.aliAccount = aliAccount;
        }

        public String getCashOutIntegration() {
            return cashOutIntegration;
        }

        public void setCashOutIntegration(String cashOutIntegration) {
            this.cashOutIntegration = cashOutIntegration;
        }

        public String getIntegrationCashOutNote() {
            return integrationCashOutNote;
        }

        public void setIntegrationCashOutNote(String integrationCashOutNote) {
            this.integrationCashOutNote = integrationCashOutNote;
        }

        @Override
        public String toString() {
            return "MemberBean{" +
                    "id='" + id + '\'' +
                    ", userCode='" + userCode + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", realName='" + realName + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", phone='" + phone + '\'' +
                    ", status='" + status + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", icon='" + icon + '\'' +
                    ", gender='" + gender + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", job='" + job + '\'' +
                    ", personalizedSignature='" + personalizedSignature + '\'' +
                    ", sourceType='" + sourceType + '\'' +
                    ", integration='" + integration + '\'' +
                    ", growth='" + growth + '\'' +
                    ", luckeyCount='" + luckeyCount + '\'' +
                    ", historyIntegration='" + historyIntegration + '\'' +
                    ", weixinOpenid='" + weixinOpenid + '\'' +
                    ", inviteCode='" + inviteCode + '\'' +
                    ", blance='" + blance + '\'' +
                    ", levelId='" + levelId + '\'' +
                    ", backMoney='" + backMoney + '\'' +
                    ", notBackMoney='" + notBackMoney + '\'' +
                    ", checkCode='" + checkCode + '\'' +
                    ", oldPassword='" + oldPassword + '\'' +
                    ", newPassword='" + newPassword + '\'' +
                    ", oldPhone='" + oldPhone + '\'' +
                    ", token='" + token + '\'' +
                    ", level='" + level + '\'' +
                    ", tenantId='" + tenantId + '\'' +
                    ", totalBackMoney='" + totalBackMoney + '\'' +
                    ", firstFansNum='" + firstFansNum + '\'' +
                    ", selfOrderNum='" + selfOrderNum + '\'' +
                    ", recommendNum='" + recommendNum + '\'' +
                    ", relationId='" + relationId + '\'' +
                    ", aliAccount='" + aliAccount + '\'' +
                    ", cashOutIntegration=" + cashOutIntegration +
                    ", integrationCashOutNote='" + integrationCashOutNote + '\'' +
                    '}';
        }
    }

    public static class IntegrationConfBean {
        /**
         * id : 2
         * ratio : 100
         * min : 100
         * serviceRatio : 10
         * tenantId : 1
         * createTime : 2019-07-09 09:17:18
         * updateTime : 2019-07-09 10:03:01
         * status : 1
         * multiple : 5
         */

        private String id;
        private String ratio;   //积分比例（X积分一元）
        private String min;      //最小兑换积分值
        private String serviceRatio;    //手续费（%）
        private String tenantId;        //租户ID
        private String createTime;
        private String updateTime;
        private String status;          //状态（0：禁用   1：启用）
        private String multiple;        //倍数

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRatio() {
            return ratio;
        }

        public void setRatio(String ratio) {
            this.ratio = ratio;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }

        public String getServiceRatio() {
            return serviceRatio;
        }

        public void setServiceRatio(String serviceRatio) {
            this.serviceRatio = serviceRatio;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMultiple() {
            return multiple;
        }

        public void setMultiple(String multiple) {
            this.multiple = multiple;
        }

        @Override
        public String toString() {
            return "IntegrationConfBean{" +
                    "id='" + id + '\'' +
                    ", ratio='" + ratio + '\'' +
                    ", min='" + min + '\'' +
                    ", serviceRatio='" + serviceRatio + '\'' +
                    ", tenantId='" + tenantId + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", status='" + status + '\'' +
                    ", multiple='" + multiple + '\'' +
                    '}';
        }
    }
}
