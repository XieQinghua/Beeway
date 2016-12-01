package com.easemob.chatui.db;

import android.content.Context;

import com.thvc.beeway.bean.Friend;

import java.util.List;

/**
 * 项目名称：Beeway
 * 类描述：
 * 创建人：谢庆华.
 * 创建时间：2015/9/15 18:57
 * 修改人：Administrator
 * 修改时间：2015/9/15 18:57
 * 修改备注：
 */
public class FriendDao {
    /**
     * subTitleColor : #666666
     * img : http://7xj45w.com1.z0.glb.clouddn.com/Photo/Headpic/member_pic.jpg
     * index : 0
     * titleSize : 16
     * title : 大Da
     * userid : c191a2c51bbd99da
     * subTitleSize : 14
     * placeholderImg : widget://Res/avatar.jpg
     * friendid : 91dc8c13b2ea2b61
     * subTitle : 普通版晚上 毛将焉附天 玻利维亚我 火车人擦肩
     * titleColor : #333333
     * letter : D
     * id : 91dc8c13b2ea2b61
     */
    public static final String FRIEND_TABLE_NAME = "friend";
    public static final String FRIEND_ID = "id";
    public static final String FRIEND_TITLE = "title";
    public static final String FRIEND_SUBTITLECOLOR = "subTitleColor";
    public static final String FRIEND_IMG = "img";
    //    public static final String FRIEND_INDEX = "index";
    public static final String FRIEND_TITLESIZE = "titleSize";
    public static final String FRIEND_USERID = "userid";
    public static final String FRIEND_SUBTITLESIZE = "subTitleSize";
    public static final String FRIEND_PLACEHOLDERIMG = "placeholderImg";
    public static final String FRIEND_TITLECOLOR = "titleColor";
    public static final String FRIEND_LETTER = "letter";
    public static final String FRIEND_SUBTITLE = "subtitle";
    public static final String FRIEND_FRIENDID = "friendid";

    public FriendDao(Context context) {
        DemoDBManager.getInstance().onInit(context);
    }

    /**
     * 保存一个蜂友到db
     *
     * @param friend
     */

    public void setFriend(Friend friend) {
        DemoDBManager.getInstance().setFriend(friend);
    }

    /**
     * 保存我的蜂友列表
     *
     * @param friendList
     */
    public void setFriendList(List<Friend> friendList) {
        DemoDBManager.getInstance().setFriendList(friendList);
    }
    /**
     * 删除一个本地蜂友
     *
     * @param username
     */
    public void deleteFriend(String username) {
        DemoDBManager.getInstance().deleteFriend(username);
    }
    /**
     * 通过username查询指定friend的信息
     * xieqinghua
     *
     * @param username
     * @return friend
     */
    public Friend getFriend(String username) {
        return DemoDBManager.getInstance().getFriend(username);
    }
}


