package com.example.bean;

import java.util.List;

public class CityListBean {
    /**
     * label : A
     * region : ["阿坝","阿巴嘎旗","阿尔山","阿合奇","阿克塞","阿克苏","阿克陶","阿克苏","阿拉山口","阿拉善右旗","阿拉善左旗","阿勒泰","阿里山","阿勒泰","阿鲁科尔沁旗","阿拉善盟","安达","安多","安福","昂仁","安国","安化","安徽","安吉","安康","安龙","安陆","安宁","安平","安庆","安丘","安仁","安塞","鞍山","安顺","安图","安溪","安县","安乡","安新","安阳","安义","安远","安岳","安泽","敖汉旗","阿拉尔","阿荣旗","阿图什","阿瓦提"]
     */

    private String label;
    private List<String> region;

    public CityListBean(String label, List<String> region) {
        this.label = label;
        this.region = region;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getRegion() {
        return region;
    }

    public void setRegion(List<String> region) {
        this.region = region;
    }
}
