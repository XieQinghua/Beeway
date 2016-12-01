package com.thvc.beeway.http;

import android.content.Context;

import com.thvc.beeway.application.BeewayApplication;

import java.text.SimpleDateFormat;

/**
 * com.thvc.beeway.http
 * 创建日期： 2015/8/14.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class UrlPools {
    private static final String HOST = "http://www.hlfyb.com/rest.php/";
    //七牛图片加载
    public static final String QINIU = "http://7xj45w.com1.z0.glb.clouddn.com/";

    /**
     * 获得个人头像的Url，传上下文参数
     *
     * @param context
     * @return
     */
    public static String getAvatarUrl(Context context) {
        String avatarUrl = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String time = sdf.format(new java.util.Date());
        avatarUrl = UrlPools.QINIU + "avatar/" + BeewayApplication.getInstance().getmUserid(context) + ".jpg?" + time;
        return avatarUrl;
    }

    /**
     * 获得好友头像的Url，每小时更新
     *
     * @param context
     * @param friendid
     * @return
     */
    public static String getFriendAvatarUrl(Context context, String friendid) {
        String friendAvatarUrl = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");
        String time = sdf.format(new java.util.Date());
        friendAvatarUrl = UrlPools.QINIU + "avatar/" + friendid + ".jpg?" + time;
        return friendAvatarUrl;
    }

    public static final String getHost() {
        return HOST;
    }

    //手机号码登录接口
    public static final String LOGIN_HOME = getHost() + "BDC/Account/login";

    //验证码登录接口
    public static final String LOGIN_CODE = getHost() + "BDC/Account/loginCode";

    //注册
    public static final String LOGIN_ADD = getHost() + "BDC/Account/reg";

    //获取验证码
    public static final String CODE = getHost() + "WMS/TextSms/sendCode";

    //找回密码
    public static final String FORGETPOSSWORD = getHost() + "BDC/Account/fpwd";

    //修改密码
    public static final String CHANGE_PWD = getHost() + "BDC/Account/password";

    //首页
    public static final String HOME = getHost() + "APP/Home/index";

    //足迹列表
    public static final String TRACKALL = getHost() + "APP/Track/all";

    //游记列表
    public static final String TRACKALL2_3 = getHost() + "APP/Travel/index";

    //我的足迹列表
    public static final String MYTRACKALL = getHost() + "APP/Track/all";

    //图片轮播
    public static final String TUPIANLUNBO = getHost() + "BDC/Content/getAdmarket";

    //获取当前经纬度下得地理位置
    public static final String LOCATION = getHost() + "APP/Location/setGeohash";

    //获取当前区下景点APP/Scenic/location  area:区id
    public static final String SCENICLOCATION = getHost() + "APP/Scenic/location";

    //获取七牛token
    public static final String GETTOKEN = getHost() + "APP/Upload/createToken";

    //获取景点列表
    public static final String SCENIC = getHost() + "APP/Scenic/index";

    //获取景点列表的省市
    public static final String SCENICAREA = getHost() + "APP/Scenic/findArea/findArea/1";

    //添加收藏
    public static final String COLLECTION = getHost() + "APP/User/collectAdds";

    //点赞
    public static final String ISGOOD = getHost() + "APP/Track/good";

    //点赞
    public static final String REPLE = getHost() + "APP/Track/commentAdd";

    //众筹
    public static final String CUSTOMER = getHost() + "APP/Want/all";

    //结束众筹
    public static final String OVERCUSTOMER = getHost() + "APP/Want/over";

    //发布足迹
    public static final String ADD_TRACK = getHost() + "APP/Track/add";

    //查看蜂友详情
    public static final String USER_DETAIL = getHost() +
            "APP/User/detail";

    //查找蜂友
    public static final String FIND_FRIEND = getHost() +
            "APP/User/findFriend";

    //我的好友
    public static final String MY_FRIEND = getHost() +
            "APP/User/my";

    //附近蜂友
    public static final String NEARBY_FRIEND = getHost() + "APP/User/nearby";

    //我的好友
    public static final String SUPPORT = getHost() + "APP/Want/support";

    //好友申请发送，申请加好友
    public static final String APPLYSEND = getHost() + "APP/User/applySend";

    //确认添加蜂友
    public static final String APPLYCONFIRM = getHost() + "APP/User/applyConfirm";

    //删除好友
    public static final String DEL_FRIEND = getHost() + "APP/User/del";

    //发布游记
    public static final String ADD_TRAVEL = getHost() + "APP/Travel/add";

    //发布众筹
    public static final String ISSUECUTOMER = getHost() + "APP/Want/add";

    //发布寻宝
    public static final String TREASURE = getHost() + "APP/Treasure/add";

    //发布意见反馈
    public static final String FEEDBACK = getHost() + "APP/System/feedbackAdd";

    //更新用户当前地理位置
    public static final String SETGEOHASH = getHost() + "APP/Location/setGeohash";

    //我的余额初始化
    public static final String MYWALLET = getHost() + "APP/Wallet/myWallet";

    //银行卡列表
    public static final String BANKCARD = getHost() + "APP/Wallet/bankList";

    //添加银行卡
    public static final String BANKADD = getHost() + "APP/Wallet/bankAdd";

    //删除银行卡
    public static final String BANKDEL = getHost() + "APP/Wallet/bankDel";

    //删除银行卡
    public static final String BANKMODI = getHost() + "APP/Wallet/bankModi";

    //申请提现
    public static final String MONEYPICK = getHost() + "APP/Wallet/moneyPick";

    //积分商城列表
    public static final String INTEGRAlMALL = getHost() + "APP/Integral/index";

    //增减积分
    public static final String INCREASEINTEGRAL = getHost() + "APP/Usys/scoreRecord";

    //积分兑换记录
    public static final String EXCHANRECORD = getHost() + "APP/Integral/convertList";

    //提现记录
    public static final String PICKRECORD = getHost() + "APP/Wallet/pickList";

    //提现记录
    public static final String ONWALLET = "http://www.hlfyb.com/wechat.php/Artonce/content_reserve";

    //我的收藏列表
    public static final String COLLECT = getHost() + "APP/User/collect";

    //修改个人资料
    public static final String EDITDATA = getHost() + "BDC/Account/edit";

    //我埋的宝藏
    public static final String MYTREASURE = getHost() + "APP/Treasure/my";

    //我的积分
    public static final String USERJIFEN = getHost() + "APP/Usys/userLevel";

    //加入群组
    public static final String GROUP_USER_ADD = getHost() + "APP/IM/groupUserAdd";

    //退出群组
    public static final String GROUP_USER_DEL = getHost() + "APP/IM/groupUserDel";
}
