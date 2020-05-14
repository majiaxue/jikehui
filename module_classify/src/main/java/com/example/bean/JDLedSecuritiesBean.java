package com.example.bean;

/**
 * Created by cuihaohao on 2019/6/15
 * Describe:
 */
public class JDLedSecuritiesBean {

    /**
     * code : 200
     * msg : success
     * data : {"clickURL":"https://union-click.jd.com/jdc?e=293644338167021568&p=AyIGZRJTEQIbD1QaUyUHFAFUEl0UBxsGUysfSlpMWGVCHlBDUAxLBQNQVk4YDk5ER1xOGVAdXRQLFAZQEloTHUtCCUZralFOVShpQWVhUE8lSwtoAVBYXW87dQ4eN1UdWxYBEgRcHlwlAhMGVR9eHAIbAGUrWxAyU2lWG1MXAxE3VCtbEQUTAFQYXhUCFA5VK1sdBiIHXB9aFwQQA1QdNUkyIjdWK1gdABUCUBlZHAIVAVQYWxEFGzdlG2sVMk1DCEZrFwMTBVc%3D&t=W1dCFFlQCxxKQgFHRE5XDVULR0UQBBQGXB1aEAsTAUpCHklf"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * clickURL : https://union-click.jd.com/jdc?e=293644338167021568&p=AyIGZRJTEQIbD1QaUyUHFAFUEl0UBxsGUysfSlpMWGVCHlBDUAxLBQNQVk4YDk5ER1xOGVAdXRQLFAZQEloTHUtCCUZralFOVShpQWVhUE8lSwtoAVBYXW87dQ4eN1UdWxYBEgRcHlwlAhMGVR9eHAIbAGUrWxAyU2lWG1MXAxE3VCtbEQUTAFQYXhUCFA5VK1sdBiIHXB9aFwQQA1QdNUkyIjdWK1gdABUCUBlZHAIVAVQYWxEFGzdlG2sVMk1DCEZrFwMTBVc%3D&t=W1dCFFlQCxxKQgFHRE5XDVULR0UQBBQGXB1aEAsTAUpCHklf
         */

        private String clickURL;

        public String getClickURL() {
            return clickURL;
        }

        public void setClickURL(String clickURL) {
            this.clickURL = clickURL;
        }
    }
}
