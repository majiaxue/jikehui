package com.example.buy2up;

import android.content.Context;

import com.example.bean.CityInfoBean;
import com.example.buy2up.adapter.utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CityListLoader {

    public static final String BUNDATA = "bundata";

    private static List<CityInfoBean> mCityListData = new ArrayList<>();

    private static List<CityInfoBean> mProListData = new ArrayList<>();

    /**
     * 解析所有的城市数据 357个数据
     */
    public List<CityInfoBean> getCityListData() {
        return mCityListData;
    }

    /**
     * 只解析省份34个数据
     */
    public List<CityInfoBean> getProListData() {
        return mProListData;
    }

    private volatile static CityListLoader instance;

    private CityListLoader() {

    }

    /**
     * 单例模式
     * @return
     */
    public static CityListLoader getInstance() {
        if (instance == null) {
            synchronized (CityListLoader.class) {
                if (instance == null) {
                    instance = new CityListLoader();
                }
            }
        }
        return instance;
    }

    /**
     * 解析357个城市数据
     * @param context
     */
    public void loadCityData(Context context) {

        String cityJson = utils.getJson(context, "china_city_data.json");
        Type type = new TypeToken<ArrayList<CityInfoBean>>() {
        }.getType();

        //解析省份
        ArrayList<CityInfoBean> mProvinceBeanArrayList = new Gson().fromJson(cityJson, type);
        if (mProvinceBeanArrayList == null || mProvinceBeanArrayList.isEmpty()) {
            return;
        }

        for (int p = 0; p < mProvinceBeanArrayList.size(); p++) {

            //遍历每个省份
            CityInfoBean itemProvince = mProvinceBeanArrayList.get(p);

            //每个省份对应下面的市
            ArrayList<CityInfoBean> cityList = itemProvince.getCityList();

            //遍历当前省份下面城市的所有数据
            for (int j = 0; j < cityList.size(); j++) {
                mCityListData.add(cityList.get(j));
            }
        }

    }

    /**
     * 解析34个省市直辖区数据
     * @param context
     */
    public void loadProData(Context context) {
        String cityJson = utils.getJson(context,"china_city_data.json");
        Type type = new TypeToken<ArrayList<CityInfoBean>>() {
        }.getType();

        //解析省份
        mProListData = new Gson().fromJson(cityJson, type);
    }

}
