package com.example.location;

import android.app.Activity;
import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.CitySPUtil;
import com.example.utils.MyLocationListener;
import com.example.utils.SPUtil;
import com.example.view.wavesidebar.SearchEditText;
import com.example.view.wavesidebar.WaveSideBarView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 定位
 */
@Route(path = "/module_user_store/LocationActivity")
public class LocationActivity extends BaseActivity<LocationView, LocationPresenter> implements LocationView {

    @BindView(R2.id.location_image_back)
    ImageView locationImageBack;
    @BindView(R2.id.location_search)
    SearchEditText locationSearch;
    @BindView(R2.id.location_recycler)
    RecyclerView locationRecycler;
    @BindView(R2.id.location_side_bar)
    WaveSideBarView locationSideBar;
    @BindView(R2.id.location_select_recycler)
    RecyclerView locationSelectRecycler;
    @BindView(R2.id.location_relative)
    RelativeLayout locationRelative;
    private String cityName;

    private static int RESULTCODE = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_location;
    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        cityName = intent.getStringExtra("cityName");

        presenter.initView(locationSearch, locationRecycler, locationSelectRecycler, locationSideBar, cityName);

    }

    @Override
    public void initClick() {
        locationImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                if ("选择地址".equals(cityName)){
                    intent.putExtra("cityName", MyLocationListener.city);
                }else{
                    intent.putExtra("cityName", cityName);
                }
                CitySPUtil.addParm(CommonResource.CITY, cityName);
                setResult(RESULTCODE, intent);
                finish();
            }
        });
    }

    @Override
    public LocationView createView() {
        return this;
    }

    @Override
    public LocationPresenter createPresenter() {
        return new LocationPresenter(this);
    }

    @Override
    public void whetherToHide(boolean isHide) {
        if (isHide) {
            locationRelative.setVisibility(View.GONE);
            locationSelectRecycler.setVisibility(View.VISIBLE);
        } else {
            locationRelative.setVisibility(View.VISIBLE);
            locationSelectRecycler.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getRepeatCount() == 0) {
            //具体的操作代码
            CitySPUtil.addParm(CommonResource.CITY, MyLocationListener.city);
            Intent intent = new Intent();
            if ("选择地址".equals(cityName)){
                intent.putExtra("cityName", MyLocationListener.city);
            }else{
                intent.putExtra("cityName", cityName);
            }
            setResult(RESULTCODE, intent);
            finish();
        }
        return super.dispatchKeyEvent(event);
    }
}
