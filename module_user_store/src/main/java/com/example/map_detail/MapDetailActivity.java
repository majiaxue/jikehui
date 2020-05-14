package com.example.map_detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.LogUtil;
import com.example.utils.MyLocationListener;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/module_user_store/MapDetailActivity")
public class MapDetailActivity extends BaseActivity<MapDetailView, MapDetailPresenter> implements MapDetailView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.map_detail_mapview)
    MapView mapDetailMapview;
    @BindView(R2.id.include_right_btn)
    TextView includeRightBtn;

    private BaiduMap baiduMap;
    private double lat = 0;
    private double lon = 0;


    @Override
    public int getLayoutId() {
        return R.layout.activity_map_detail;
    }

    @Override
    public void initData() {
        includeTitle.setText("选择位置");
        includeRightBtn.setVisibility(View.VISIBLE);
        includeRightBtn.setText("保存");
        baiduMap = mapDetailMapview.getMap();

        LatLng latLng = new LatLng(MyLocationListener.latitude, MyLocationListener.longitude);
        MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newLatLng(latLng);

        BaiduMapOptions options = new BaiduMapOptions();
        options.mapType(BaiduMap.MAP_TYPE_SATELLITE);

        baiduMap.setMapStatus(statusUpdate);

        //设置地图缩放级别
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(18.0f);
        baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        //开启地图的定位图层
        baiduMap.setMyLocationEnabled(true);
        baiduMap.setMyLocationData(MyLocationListener.locData);
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        includeRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("lat", lat);
                intent.putExtra("lon", lon);
                LogUtil.e("经纬度------------"+lat);
                LogUtil.e("经纬度------------"+lon);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                baiduMap.clear();
                lat = latLng.latitude;
                lon = latLng.longitude;
                LogUtil.e("经纬度2------------"+lat);
                LogUtil.e("经纬度2------------"+lon);
                //定义Maker坐标点
                LatLng point = new LatLng(latLng.latitude, latLng.longitude);
                //构建Marker图标
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_weizhi);

                //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option = new MarkerOptions()
                        .position(point)
                        .perspective(true)
                        .draggable(true)
                        .icon(bitmap);

                //在地图上添加Marker，并显示
                baiduMap.addOverlay(option);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });

        baiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng latLng = marker.getPosition();
                lat = latLng.latitude;
                lon = latLng.longitude;
            }

            @Override
            public void onMarkerDragStart(Marker marker) {

            }
        });
    }

    @Override
    protected void onResume() {
        mapDetailMapview.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mapDetailMapview.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapDetailMapview.getMap().setMyLocationEnabled(false);
        mapDetailMapview.onDestroy();
        mapDetailMapview = null;
        super.onDestroy();
    }

    @Override
    public MapDetailView createView() {
        return this;
    }

    @Override
    public MapDetailPresenter createPresenter() {
        return new MapDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
