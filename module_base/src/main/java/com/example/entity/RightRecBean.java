package com.example.entity;

import java.util.List;

public class RightRecBean {


    private String name;

    private List<ListBean> list;

    public RightRecBean(String name, List<ListBean> list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * icon : http://120.27.23.105/images/icon.png
         * name : 月饼
         * pcid : 1
         * pscid : 1
         */

        private int icon;
        private String name;

        public ListBean(int icon, String name) {
            this.icon = icon;
            this.name = name;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
