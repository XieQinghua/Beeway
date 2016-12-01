package com.easemob.chatui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.thvc.beeway.R;
import com.thvc.beeway.base.BaseActivity;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2015/10/31.
 * 我的位置
 */
public class GaoDeMapActivity extends BaseActivity implements Runnable, AMap.OnMarkerDragListener,
        AMap.OnMapLoadedListener, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.InfoWindowAdapter,
        GeocodeSearch.OnGeocodeSearchListener {

    private  String latitudes;
    private  String longitudes;
    private String addresss;
    private ImageView iv_back;

    private LatLonPoint mLatLonPoint = null;
    private MapView mapView;
    private AMap mAMap;
    private String addressName;
    private GeocodeSearch geocodeSearch;
    private ProgressDialog progDialog = null;

    private static double longitude;
    private static double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaodemap);
        mapView = (MapView) findViewById(R.id.gaode_map);
        mapView.onCreate(savedInstanceState);
        init();
        addresss = getIntent().getStringExtra("address");
        latitudes = getIntent().getStringExtra("latitude");
        longitudes = getIntent().getStringExtra("longitude");
        if (latitudes!=null && longitudes!=null) {
            latitude  = Double.valueOf(latitudes).doubleValue();
            longitude = Double.valueOf(longitudes).doubleValue();
            mLatLonPoint = new LatLonPoint(latitude, longitude);
            getAddressByLatLng(mLatLonPoint);
        }

    }


    private void init() {
        if (mAMap == null) {
            mAMap = mapView.getMap();
            geocodeSearch = new GeocodeSearch(this);
            geocodeSearch.setOnGeocodeSearchListener(this);
            setUpMap();
            progDialog = new ProgressDialog(this);
        }
    }
    private void setUpMap() {
        mAMap.setOnMarkerDragListener(this);
        mAMap.setOnMapLoadedListener(this);
        mAMap.setOnMarkerClickListener(this);
        mAMap.setOnInfoWindowClickListener(this);
        mAMap.setInfoWindowAdapter(this);
    }
    // 根据经纬度获取地址
    public void getAddressByLatLng(final LatLonPoint latLngPoint) {
        showDialog();
        RegeocodeQuery query = new RegeocodeQuery(latLngPoint, 200, GeocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(query);
    }

    public void showDialog() {
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage(getString(R.string.map_loading));
        progDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapLoaded() {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    public void run() {

    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        dismissDialog();
        if (rCode == 0) {
            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {
                addressName = result.getRegeocodeAddress().getFormatAddress() + "\n " + getDate() + " " + getTime();
                addPositionAnnotation(latitude, longitude, addressName);
            }
        } else {
            Toast.makeText(this, getString(R.string.map_neterr), Toast.LENGTH_SHORT).show();
        }
    }

    //获取系统日期
    public String getDate() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sDateFormat.format(new java.util.Date());
    }

    //获取系统时间
    public String getTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("hh:mm:ss");
        return sDateFormat.format(new java.util.Date());
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    public void addPositionAnnotation(double latitude, double longitude, String address) {
        /*
         *第一个参数是包含经纬度的对象
         * 第二个参数是地图的放大倍数
         */
        mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16));

        //地址标注
        Marker marker = mAMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(new LatLng(latitude, longitude))//地图上的位置
                .title(address)//要显示的内容
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .draggable(false));
        marker.showInfoWindow();
    }

    public void dismissDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }
}
