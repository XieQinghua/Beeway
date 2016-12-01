package com.thvc.beeway.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.navi.view.RouteOverLay;
import com.thvc.beeway.R;
import com.thvc.beeway.application.BeewayApplication;
import com.thvc.beeway.utils.MyStatic;
import com.thvc.beeway.utils.Utils;

import java.util.ArrayList;


/**
 * 一键导航页面
 */
public class SimpleNaviRouteActivity extends Activity implements AMapNaviListener {

    // 地图和导航资源
    private MapView mMapView;
    private AMap mAMap;
    private AMapNavi mAMapNavi;

    // 起点终点坐标
    private static NaviLatLng mNaviStart = null;
    private static NaviLatLng mNaviEnd = null;
    // 起点终点列表
    private ArrayList<NaviLatLng> mStartPoints = new ArrayList<NaviLatLng>();
    private ArrayList<NaviLatLng> mEndPoints = new ArrayList<NaviLatLng>();
    // 规划线路
    private RouteOverLay mRouteOverLay;
    // 是否驾车和是否计算成功的标志
    private boolean mIsDriveMode = true;
    private boolean mIsCalculateRouteSuccess = false;

    private RadioButton btn_driving;
    private RadioButton btn_walk;

    private static double lat;
    private static double x_pi;
    private static double lon;


    //定位管理服务
    private LocationManagerProxy mLocationManagerProxy;
    private LocationSource.OnLocationChangedListener mListener;
    private LocationManagerProxy mAMapLocationManager;

    private ImageView iv_back;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_route);
        BeewayApplication.getInstance().addActivity(this);
        String latitude = getIntent().getStringExtra("latitude");
        String longitude = getIntent().getStringExtra("longitude");

        lat = Double.valueOf(latitude).doubleValue();
        lon = Double.valueOf(longitude).doubleValue();



        btn_driving = (RadioButton) this.findViewById(R.id.btn_driving);//驾车
        btn_walk = (RadioButton) this.findViewById(R.id.btn_walk);//步行


        //返回按钮
        iv_back = (ImageView) this.findViewById(R.id.iv_backs);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleNaviRouteActivity.this,
                        ScenicDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                mListener = null;
                if (mAMapLocationManager != null) {
                    mAMapLocationManager.removeUpdates((AMapLocationListener) SimpleNaviRouteActivity.this);
                    mAMapLocationManager.destroy();
                }
                finish();
                BeewayApplication.getInstance().deleteActivity(SimpleNaviRouteActivity.this);
            }
        });
        x_pi = lat * lon / 180.0;
        bd_decrypt(lat, lon);
        initView(savedInstanceState);
    }

    //解密成为火星坐标
    public static void bd_decrypt(double bd_lat, double bd_lon) {
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double gg_lon = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        mNaviEnd = new NaviLatLng(gg_lat, gg_lon);
    }

    public void SearchButtonProcess(View v) {
        if (v.getId() == R.id.btn_driving) {
            btn_driving.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_view_rounded_left));
            btn_driving.setTextColor(getResources().getColor(R.color.beeway_title_bule));
            btn_walk.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_view_rounded_transparent));
            btn_walk.setTextColor(getResources().getColor(R.color.white));
            mIsCalculateRouteSuccess = false;
            mIsDriveMode = true;
            calculateDriveRoute();
        } else if (v.getId() == R.id.btn_walk) {
            btn_walk.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_view_rounded_right));
            btn_walk.setTextColor(getResources().getColor(R.color.beeway_title_bule));
            btn_driving.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_view_rounded_transparent));
            btn_driving.setTextColor(getResources().getColor(R.color.white));
            if (mNaviStart!=null&&mNaviEnd!=null){
            mIsCalculateRouteSuccess = false;
            mIsDriveMode = false;
            calculateFootRoute();}
        }
    }

    // 初始化View
    private void initView(Bundle savedInstanceState) {
        mNaviStart = new NaviLatLng(MyStatic.geoLat, MyStatic.geoLng);
        mAMapNavi = AMapNavi.getInstance(this);
        mAMapNavi.setAMapNaviListener(this);
        mStartPoints.add(mNaviStart);
        mEndPoints.add(mNaviEnd);

        mMapView = (MapView) findViewById(R.id.simple_route_map);
        mMapView.onCreate(savedInstanceState);
        mAMap = mMapView.getMap();
        mRouteOverLay = new RouteOverLay(mAMap, null);

        mIsCalculateRouteSuccess = false;
        mIsDriveMode = true;
        calculateDriveRoute();
    }

    //计算驾车路线
    private void calculateDriveRoute() {
        boolean isSuccess = mAMapNavi.calculateDriveRoute(mStartPoints,
                mEndPoints, null, AMapNavi.DrivingDefault);
        if (!isSuccess) {
            showToast("路线计算失败,检查参数情况");
        } else {
            AlertDialog("DriveRoute");
        }
    }

    //计算步行路线
    private void calculateFootRoute() {
        boolean isSuccess = mAMapNavi.calculateWalkRoute(mNaviStart, mNaviEnd);
        if (!isSuccess) {
            showToast("路线计算失败,检查参数情况");
        } else {
            AlertDialog("FootRoute");
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void AlertDialog(final String str) {
        //1获取一个对话框的创建器
        AlertDialog.Builder builder = new AlertDialog.Builder(SimpleNaviRouteActivity.this, AlertDialog.THEME_HOLO_LIGHT);
        //2所有builder设置一些参数
        builder.setTitle("提示");
        builder.setMessage("路线规划完成，是否立即导航。");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (str.equals("DriveRoute")) {
                    startGPSNavi(true);
                } else if (str.equals("FootRoute")) {
                    startGPSNavi(false);
                }
            }
        });
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private void startGPSNavi(boolean isDrive) {
        mListener = null;
        if (mAMapLocationManager != null) {
            mAMapLocationManager.removeUpdates((AMapLocationListener) SimpleNaviRouteActivity.this);
            mAMapLocationManager.destroy();
        }
        mAMapLocationManager = null;
        if ((isDrive && mIsDriveMode && mIsCalculateRouteSuccess)
                || (!isDrive && !mIsDriveMode && mIsCalculateRouteSuccess)) {
            Intent gpsIntent = new Intent(SimpleNaviRouteActivity.this,
                    SimpleNaviActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean(Utils.ISEMULATOR, false);
            bundle.putInt(Utils.ACTIVITYINDEX, Utils.SIMPLEROUTENAVI);
            gpsIntent.putExtras(bundle);
            gpsIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(gpsIntent);
        } else {
            showToast("请先进行相对应的路径规划，再进行导航");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mListener = null;
            if (mAMapLocationManager != null) {
                mAMapLocationManager.removeUpdates((AMapLocationListener) SimpleNaviRouteActivity.this);
                mAMapLocationManager.destroy();
            }
            SimpleNaviRouteActivity.this.finish();
            BeewayApplication.getInstance().deleteActivity(this);

        }
        return super.onKeyDown(keyCode, event);
    }

    //--------------------导航监听回调事件-----------------------------
    @Override
    public void onArriveDestination() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onArrivedWayPoint(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onCalculateRouteFailure(int arg0) {
        showToast("路径规划出错" + arg0);
        mIsCalculateRouteSuccess = false;
    }

    @Override
    public void onCalculateRouteSuccess() {
        AMapNaviPath naviPath = mAMapNavi.getNaviPath();
        if (naviPath == null) {
            return;
        }
        // 获取路径规划线路，显示到地图上
        mRouteOverLay.setRouteInfo(naviPath);
        mRouteOverLay.addToMap();
        mIsCalculateRouteSuccess = true;
    }

    @Override
    public void onEndEmulatorNavi() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onGetNavigationText(int arg0, String arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onGpsOpenStatus(boolean arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onInitNaviFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onInitNaviSuccess() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLocationChange(AMapNaviLocation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onNaviInfoUpdated(AMapNaviInfo arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onReCalculateRouteForTrafficJam() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onReCalculateRouteForYaw() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStartNavi(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTrafficStatusUpdate() {
        // TODO Auto-generated method stub

    }

//------------------生命周期重写函数---------------------------	

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        //删除监听
        AMapNavi.getInstance(this).removeAMapNaviListener(this);

    }

    @Override
    public void onNaviInfoUpdate(NaviInfo arg0) {

        // TODO Auto-generated method stub

    }

}
