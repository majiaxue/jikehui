package com.example.bean;

public class TKouLingBean {

    /**
     * num_iid : 595537285133
     * click_url : https://s.click.taobao.com/t?e=m%3D2%26s%3D79quNf86lD4cQipKwQzePOeEDrYVVa64yK8Cckff7TVRAdhuF14FMSNGkBn4GP2PRitN3%2FurF3zwafiEtMgsu6R0wSSZmfuSlHJtDjjM7fSbb7SNhMQtQ%2F0jCkSDmD6DKe3%2FbelnU7eOJ4hmg2sGJMYjGr6SHqGcSdcIEPyUgLPEXafbsfsa6m6bZnhlvEG4P98tNS8yGJoAlvagRoMWi42xk%2BCj0iPXOYQwrhPE0iw%3D&union_lens=lensId:0b0fb9a1_0ca8_16c22c6acaf_9de2
     * title : 三色糙米2500g五谷杂粮糙米黑米红米新米5斤粗粮健身胚芽脂减饭
     */

    private String num_iid;
    private String click_url;
    private String title;

    public String getNum_iid() {
        return num_iid;
    }

    public void setNum_iid(String num_iid) {
        this.num_iid = num_iid;
    }

    public String getClick_url() {
        return click_url;
    }

    public void setClick_url(String click_url) {
        this.click_url = click_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TKouLingBean{" +
                "num_iid='" + num_iid + '\'' +
                ", click_url='" + click_url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
