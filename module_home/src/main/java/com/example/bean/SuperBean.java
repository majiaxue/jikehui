package com.example.bean;

public class SuperBean {

    /**
     * name : 女装
     * id : 0
     * listId : 2
     */

    private String name;
    private int id;
    private int listId;

    public SuperBean(String name, int id, int listId) {
        this.name = name;
        this.id = id;
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }
}
