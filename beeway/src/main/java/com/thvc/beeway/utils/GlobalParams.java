package com.thvc.beeway.utils;

import com.thvc.beeway.bean.MyUserDetailsBean;
import com.thvc.beeway.bean.UserInfo;

/**
 * 项目名称：Beeway
 * 类描述：存放供整个程序调用的通用静态变量类
 * 创建人：谢庆华.
 * 创建时间：2015/8/14 16:51
 * 修改人：Administrator
 * 修改时间：2015/8/14 16:51
 * 修改备注：
 */

public class GlobalParams {

    //服务器返回的loginUserId
    public static String loginUserId;
    //服务器返回的geohash
    public static String geohash;
    //服务器返回的nickname
    public static String nickname;
    //百度定位到的位置信息
    public static String latitude;//纬度
    public static String longitude;//经度
    public static String province;//省份
    public static String city;//城市
    public static String detail;//详细地址
    public static String Geohash;//获取当前位置的Geohash
    public static int country_code;//获取当前位置的地区号
    //保存登录返回的个人详细资料
    public static UserInfo mUserInfo;

    //保存我的个人详细资料
    public static MyUserDetailsBean myUserDetailsBean;
}
