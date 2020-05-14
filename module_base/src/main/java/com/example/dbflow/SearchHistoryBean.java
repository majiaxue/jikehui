package com.example.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDataBase.class)
public class SearchHistoryBean extends BaseModel {
    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private String content;
    @Column
    private String type;

    public void insertData(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SearchHistoryBean{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
