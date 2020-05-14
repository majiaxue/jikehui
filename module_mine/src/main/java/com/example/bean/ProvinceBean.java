package com.example.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ProvinceBean implements Parcelable {

    private String id; /*110101*/

    private String name; /*东城区*/


    private ArrayList<City2Bean> cityList;



    @Override
    public String toString() {
        return  name ;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<City2Bean> getCityList() {
        return cityList;
    }

    public void setCityList(ArrayList<City2Bean> cityList) {
        this.cityList = cityList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeTypedList(this.cityList);
    }

    public ProvinceBean() {
    }

    protected ProvinceBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.cityList = in.createTypedArrayList(City2Bean.CREATOR);
    }

    public static final Creator<ProvinceBean> CREATOR = new Creator<ProvinceBean>() {
        @Override
        public ProvinceBean createFromParcel(Parcel source) {
            return new ProvinceBean(source);
        }

        @Override
        public ProvinceBean[] newArray(int size) {
            return new ProvinceBean[size];
        }
    };
}
