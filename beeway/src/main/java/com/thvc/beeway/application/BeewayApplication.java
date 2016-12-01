package com.thvc.beeway.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.easemob.EMCallBack;
import com.easemob.chatui.DemoHXSDKHelper;
import com.easemob.chatui.domain.User;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.tencent.bugly.crashreport.CrashReport;
import com.thvc.beeway.bean.Friend;
import com.thvc.beeway.utils.CustomConstants;
import com.thvc.beeway.utils.GlobalParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

//import cn.jpush.android.api.JPushInterface;

//import cn.jpush.android.api.JPushInterface;

/**
 * 项目名称：Beeway
 * 类描述：
 * 创建人：谢庆华
 * 创建时间：2015/8/12 10:07
 * 修改人：Administrator
 * 修改时间：2015/8/12 10:07
 * 修改备注：
 */
public class BeewayApplication extends Application {
    public static BeewayApplication myApp;
    private static String TAG = "BeewayApplication";
    /***********************
     * 百度定位功能
     ***********************/
    public LocationClient mLocationClient;//百度定位服务的客户端
    public MyLocationListener mMyLocationListener;//百度实时位置监听器
    /*********************
     * 后面加入环信的相关功能
     ********************/
    public static Context applicationContext;
    private Map<String, User> contactList;

    private static List<Friend> friendlist;//服务器保存的好友信息
    /**
     * 当前用户nickname,为了苹果推送不是userid而是昵称
     */
    public static String currentUserNick = "";
    public static DemoHXSDKHelper hxSDKHelper = new DemoHXSDKHelper();


    private ArrayList<Activity>activities=new ArrayList<Activity>();
    private static BeewayApplication instance;

    //添加Activity到容器中
    public void addActivity(Activity activity)
    {
        activities.add(activity);
    }
    public void deleteActivity(Activity activity){
        activities.remove(activity);
    }
    //finish
    public void exit()
    {
        for(Activity activity:activities){
            activity.finish();
        }
        activities.clear();

    }


    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        instance = this;
        applicationContext = this;
        /*******************
         * 腾讯Bugly插件初始化
         ********************/
        CrashReport.initCrashReport(this, "900009659", false);
//        CrashReport.testJavaCrash(); // Bugly测试，解除注释将产生一个Crash
        removeTempFromPref();
        /********************
         * 百度定位功能设置开始
         *********************/
        mLocationClient = new LocationClient(this);
        initLocation();//初始化百度定位参数
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);//注册位置监听器
        mLocationClient.start();// 启动定位SDK


        //极光推送
        JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this); // 初始化 JPush

        /**
         * this function will initialize the HuanXin SDK
         *
         * @return boolean true if caller can continue to call HuanXin related APIs after calling onInit, otherwise false.
         *
         * 环信初始化SDK帮助函数
         * 返回true如果正确初始化，否则false，如果返回为false，请在后续的调用中不要调用任何和环信相关的代码
         *
         * for example:
         * 例子：
         *
         * public class DemoHXSDKHelper extends HXSDKHelper
         *
         * HXHelper = new DemoHXSDKHelper();
         * if(HXHelper.onInit(context)){
         *     // do HuanXin related work
         * }
         */
        hxSDKHelper.onInit(applicationContext);
        imageloader();//ImageLoader加载图片

    }


    private void removeTempFromPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        sp.edit().remove(CustomConstants.PREF_TEMP_IMAGES).commit();
    }

    /*位置监听器*/
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            /**
             *只要进入该方法即获得百度定位结果，同时停止定位服务
             */
            mLocationClient.stop();
//            Log.e("经度", location.getLongitude() + "");
//            Log.e("纬度", location.getLatitude() + "");
//            Log.e("省份", location.getProvince());
//            Log.e("城市", location.getCity());
//            Log.e("详细位置", location.getAddrStr());
            GlobalParams.latitude = location.getLatitude() + "";
            GlobalParams.longitude = location.getLongitude() + "";
            GlobalParams.province = location.getProvince();
            GlobalParams.city = location.getCity();
            GlobalParams.detail = location.getAddrStr();
        }
    }


    /**
     * 配置百度定位SDK参数
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 定位模式设置为高精度
        option.setCoorType("gcj02");// 坐标系设置为国家测绘局标准
        option.setScanSpan(5000);// 设置发起定位请求的间隔需要大于等于5000ms才是有效的
        option.setIsNeedAddress(true);// 设置需要地址信息
        option.setOpenGps(false);// 设置使用gps，默认关闭
        option.setLocationNotify(true);// 设置当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);// 定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setEnableSimulateGps(false);// 设置需要过滤gps仿真结果，默认需要
        option.setIsNeedLocationDescribe(true);// 设置需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mLocationClient.setLocOption(option);
    }

    public static BeewayApplication getContext() {
        return myApp;
    }

    public static BeewayApplication getInstance() {
        return instance;
    }


    /**
     * 获取内存中好友user list
     *
     * @return
     */
    public Map<String, User> getContactList() {
        return hxSDKHelper.getContactList();
    }

    /**
     * 设置好友user list到内存中
     *
     * @param contactList
     */
    public void setContactList(Map<String, User> contactList) {
        hxSDKHelper.setContactList(contactList);
    }

    /**
     * 设置我的好友friendlist到内存中
     *
     * @param friendList
     */
    public void setFriendList(List<Friend> friendList) {
        this.friendlist = friendList;
    }

    /**
     * 获取当前登陆用户名
     *
     * @return
     */
    public String getUserName() {
        return hxSDKHelper.getHXId();
    }

    /**
     * 获取密码
     *
     * @return
     */
    public String getPassword() {
        return hxSDKHelper.getPassword();
    }

    /**
     * 设置用户名
     *
     * @param username
     */
    public void setUserName(String username) {
        hxSDKHelper.setHXId(username);
    }

    /**
     * 设置密码 下面的实例代码 只是demo，实际的应用中需要加password 加密后存入 preference 环信sdk
     * 内部的自动登录需要的密码，已经加密存储了
     *
     * @param pwd
     */
    public void setPassword(String pwd) {
        hxSDKHelper.setPassword(pwd);
    }

    /**
     * 退出登录,清空数据
     */
    public void logout(final EMCallBack emCallBack) {
        // 先调用sdk logout，在清理app中自己的数据
        hxSDKHelper.logout(emCallBack);
    }

    private String mUserName = "test_cy";
    private boolean autologin = false;
    private String userpassword;
    private String mUserid;
    private String mNickname;
    private String sex;
    private String content;
    private String realname;
    private String birthday;
    private String email;
    private String wechat;
    private String qq;
    private String weibo;

    public void setmNickname(String mNickname) {
        this.mNickname = mNickname;
    }

    public String getmNickname(Context con) {
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        mNickname = sp.getString("nickname", "");
        return mNickname;
    }

    public boolean getAutologin(Context con) {
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        autologin = sp.getBoolean("autologin", false);
        return autologin;
    }

    public void setAutologin(Context con, boolean autologin) {
        this.autologin = autologin;
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        sp.edit().putBoolean("autologin", autologin).commit();
    }

    public String getmUserName(Context con) {
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        mUserName = sp.getString("Name", "");

        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getUserPassword(Context con) {
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        userpassword = sp.getString("password", "1");
        return userpassword;
    }

    public void setUserPassword(String password) {
        this.userpassword = password;
    }


    public String getmUserid(Context con) {
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        mUserid = sp.getString("userid", "1");
        return mUserid;
    }

    public void setmUserid(String mUserid) {
        this.mUserid = mUserid;
    }

    public String getEmall(Context con) {
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        email = sp.getString("email", "");
        return email;
    }

    public void setEmall(String email) {
        this.email = email;
    }

    public String getQq(Context con) {
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        qq = sp.getString("qq", "");
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getContent(Context con) {
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        content = sp.getString("content", "");
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRealname(Context con) {
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        realname = sp.getString("realname", "");
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex(Context con) {
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        sex = sp.getString("sex", "");
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday(Context con) {
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        birthday = sp.getString("birthday", "");
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWechat(Context con) {
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        wechat = sp.getString("wechat", "");
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWeibo(Context con) {
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        weibo = sp.getString("weibo", "");
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }


    /**
     * 保存用户的详细信息
     *
     * @param context
     * @param username
     * @param password
     * @param autologin
     * @param mUserid
     * @param mNickname
     * @param sex
     * @param content
     * @param realname
     * @param birthday
     * @param email
     * @param wechat
     * @param qq
     * @param weibo
     */
    public void saveParam(Context context, String username, String password, boolean autologin,
                          String mUserid, String mNickname, String sex, String content, String realname,
                          String birthday, String email, String wechat, String qq, String weibo) {
        SharedPreferences sp = context.getSharedPreferences("loginparam", Activity.MODE_PRIVATE);
        sp.edit().putString("Name", username)
                .putBoolean("autologin", autologin)
                .putString("password", password)
                .putString("userid", mUserid)
                .putString("nickname", mNickname)
                .putString("sex", sex)
                .putString("content", content)
                .putString("realname", realname)
                .putString("birthday", birthday)
                .putString("email", email)
                .putString("wechat", wechat)
                .putString("qq", qq)
                .putString("weibo", weibo).commit();
    }


    public void clearsaveParam(Context con) {
        SharedPreferences sp = con.getSharedPreferences("loginparam",
                Activity.MODE_PRIVATE);
        sp.edit().clear();
    }

    private void imageloader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory()  //1.8.6包使用时候，括号里面传入参数true
                .cacheOnDisc()    //同上

                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext())
                .defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }
}