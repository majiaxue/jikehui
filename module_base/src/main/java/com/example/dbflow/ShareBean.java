package com.example.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDataBase.class)
public class ShareBean extends BaseModel {
    @PrimaryKey(autoincrement = true)
    private long id;
    @Column
    private String userCode;
    @Column
    private int count;
    @Column
    private String updateTime;


    public void insertData(String userCode, int count, String updateTime) {
        this.userCode = userCode;
        this.count = count;
        this.updateTime = updateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ShareBean{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", count=" + count +
                ", time='" + updateTime + '\'' +
                '}';
    }
}
