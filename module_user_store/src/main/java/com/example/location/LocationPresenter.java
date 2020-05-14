package com.example.location;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.CitiesBean;
import com.example.bean.CityListBean;
import com.example.bean.RegionBean;
import com.example.common.CommonResource;
import com.example.location.adapter.LocationHotCityAdapter;
import com.example.location.adapter.LocationSelectAdapter;
import com.example.location.adapter.RegionAdapter;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_store.R;
import com.example.utils.CitySPUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.MyLocationListener;
import com.example.utils.SPUtil;
import com.example.view.wavesidebar.SearchEditText;
import com.example.view.wavesidebar.WaveSideBarView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class LocationPresenter extends BasePresenter<LocationView> {

    private List<RegionBean.CityBean> mList = new ArrayList<>();
    private List<String> selectList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private static int RESULTCODE = 0;
    private List<String> recentlyList = new ArrayList<>();


    public LocationPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(SearchEditText locationSearch, final RecyclerView locationRecycler, final RecyclerView locationSelectRecycler, WaveSideBarView locationSideBar, final String cityName) {


        mList.add(new RegionBean.CityBean("您正在看:" + cityName, Arrays.asList("")));
        mList.add(new RegionBean.CityBean("定位", Arrays.asList(MyLocationListener.city)));
        mList.add(new RegionBean.CityBean("热门城市", Arrays.asList("北京", "上海", "广州", "深圳", "杭州", "郑州")));

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(mContext.getAssets().open("city.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("city");
            for (int i = 0; i < jsonArray.length(); i++) {
//                LogUtil.e("数组长度" + jsonArray.length());
//                LogUtil.e("数组内容" + jsonArray.get(i).toString());
                CityListBean cityListBean = new Gson().fromJson(jsonArray.get(i).toString(), CityListBean.class);
                mList.add(new RegionBean.CityBean(cityListBean.getLabel(), cityListBean.getRegion()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        linearLayoutManager = new LinearLayoutManager(mContext);
        locationRecycler.setLayoutManager(linearLayoutManager);
        RegionAdapter regionAdapter = new RegionAdapter(mContext, mList, R.layout.item_location_rec);
        locationRecycler.setAdapter(regionAdapter);

        regionAdapter.setOnItemClick( new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                final RecyclerView itemRegionList = view.findViewById(R.id.item_regionList);
                if (position == 0) {
                    Map map = MapUtil.getInstance().addParms("cityName", cityName).build();
                    Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getData(CommonResource.ADDRESSAREA, map);
                    RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                        @Override
                        public void onSuccess(String result, String msg) {
                            LogUtil.e("LocationPresenterResult" + result);
                            List<CitiesBean> citiesBeans = JSON.parseArray(result, CitiesBean.class);
                            if (citiesBeans.size() != 0) {
                                recentlyList.clear();
                                for (int i = 0; i < citiesBeans.size(); i++) {
                                    recentlyList.add(citiesBeans.get(i).getName());
                                }
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3, LinearLayoutManager.VERTICAL, false);
                                LocationHotCityAdapter locationHotCityAdapter = new LocationHotCityAdapter(mContext, recentlyList, R.layout.item_location_hot_city_rec);
                                itemRegionList.setLayoutManager(gridLayoutManager);
                                itemRegionList.setAdapter(locationHotCityAdapter);
                                locationHotCityAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(RecyclerView parent, View view, int position) {
                                        TextView hotCityName = view.findViewById(R.id.location_hot_city_name);
                                        CitySPUtil.addParm(CommonResource.CITY, hotCityName.getText().toString());
                                        Intent intent = new Intent();
                                        intent.putExtra("cityName", hotCityName.getText().toString());
                                        ((Activity) mContext).setResult(RESULTCODE, intent);
                                        ((Activity) mContext).finish();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onError(String errorCode, String errorMsg) {
                            LogUtil.e("LocationPresenterErrorMsg" + errorMsg);
                        }
                    }));
                }
            }
        });

        locationSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                selectList.clear();
                if (s.toString().equals("")) {
                    getView().whetherToHide(false);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                    locationRecycler.setLayoutManager(linearLayoutManager);
                    RegionAdapter regionAdapter = new RegionAdapter(mContext, mList, R.layout.item_location_rec);
                    locationRecycler.setAdapter(regionAdapter);
                } else {
                    getView().whetherToHide(true);
                    //向selectList添加数据
                    for (int i = 3; i < mList.size(); i++) {
                        List<String> regionList = mList.get(i).getRegion();
                        for (int j = 0; j < regionList.size(); j++) {
                            if (regionList.get(j).contains(s.toString())) {
                                selectList.add(regionList.get(j));
                            }
                        }
                    }
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    LocationSelectAdapter locationSelectAdapter = new LocationSelectAdapter(mContext, selectList, R.layout.item_location_select_rec);
                    locationSelectRecycler.setLayoutManager(linearLayoutManager1);
                    locationSelectRecycler.setAdapter(locationSelectAdapter);
                    locationSelectAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            TextView name = view.findViewById(R.id.item_text);
                            CitySPUtil.addParm(CommonResource.CITY, name.getText().toString());
                            Intent intent = new Intent();
                            intent.putExtra("cityName", name.getText().toString());
                            ((Activity) mContext).setResult(RESULTCODE, intent);
                            ((Activity) mContext).finish();
                        }
                    });

                }
            }
        });

        locationSideBar.setOnSelectIndexItemListener(new WaveSideBarView.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String letter) {
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getLabel().equals(letter)) {
                        linearLayoutManager.scrollToPositionWithOffset(i, 0);
                    }
                    if ("#".equals(letter)) {
                        linearLayoutManager.scrollToPositionWithOffset(0, 0);
                    }
                }
            }
        });
    }

}
