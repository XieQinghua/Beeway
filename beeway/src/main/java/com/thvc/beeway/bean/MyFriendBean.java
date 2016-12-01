package com.thvc.beeway.bean;

import java.util.List;

/**
 * 项目名称：Beeway
 * 类描述：
 * 创建人：谢庆华.
 * 创建时间：2015/9/15 14:20
 * 修改人：Administrator
 * 修改时间：2015/9/15 14:20
 * 修改备注：
 */
public class MyFriendBean {


    /**
     * data : {"list":[{"subTitleColor":"#666666","img":"http://7xj45w.com1.z0.glb.clouddn.com/Photo/Headpic/member_pic.jpg","index":"0","titleSize":"16","title":"大B","userid":"91dc8c13b2ea2b61","subTitleSize":"14","placeholderImg":"widget://Res/avatar.jpg","friendid":"c191a2c51bbd99da","subTitle":"给哥哥哥哥","titleColor":"#333333","letter":"D","id":"c191a2c51bbd99da"},{"subTitleColor":"#666666","img":"http://7xj45w.com1.z0.glb.clouddn.com/Photo/Headpic/member_pic.jpg","index":"1","titleSize":"16","title":"D嗒滴","userid":"91dc8c13b2ea2b61","subTitleSize":"14","placeholderImg":"widget://Res/avatar.jpg","friendid":"286cc02a8fd6a895","subTitle":"天亮了","titleColor":"#333333","letter":"D","id":"286cc02a8fd6a895"},{"subTitleColor":"#666666","img":"http://7xj45w.com1.z0.glb.clouddn.com/Photo/Headpic/member_pic.jpg","index":"0","titleSize":"16","title":"和大大","userid":"91dc8c13b2ea2b61","subTitleSize":"14","placeholderImg":"widget://Res/avatar.jpg","friendid":"4bb3d8bd57ab9a99","subTitle":"春天里","titleColor":"#333333","letter":"H","id":"4bb3d8bd57ab9a99"},{"subTitleColor":"#666666","img":"http://7xj45w.com1.z0.glb.clouddn.com/Photo/Headpic/member_pic.jpg","index":"0","titleSize":"16","title":"yuao","userid":"91dc8c13b2ea2b61","subTitleSize":"14","placeholderImg":"widget://Res/avatar.jpg","friendid":"42fb18143660c0fa","subTitle":"我是歌手","titleColor":"#333333","letter":"Y","id":"42fb18143660c0fa"},{"subTitleColor":"#666666","img":"http://7xj45w.com1.z0.glb.clouddn.com/Photo/Headpic/member_pic.jpg","index":"1","titleSize":"16","title":"yuao","userid":"91dc8c13b2ea2b61","subTitleSize":"14","placeholderImg":"widget://Res/avatar.jpg","friendid":"42fb18143660c0fa","subTitle":"我是歌手","titleColor":"#333333","letter":"Y","id":"42fb18143660c0fa"}]}
     * status : 1
     * info :
     */
    private DataEntity data;
    private int status;
    private String info;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public DataEntity getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public static class DataEntity {
        /**
         * list : [{"subTitleColor":"#666666","img":"http://7xj45w.com1.z0.glb.clouddn.com/Photo/Headpic/member_pic.jpg","index":"0","titleSize":"16","title":"大B","userid":"91dc8c13b2ea2b61","subTitleSize":"14","placeholderImg":"widget://Res/avatar.jpg","friendid":"c191a2c51bbd99da","subTitle":"给哥哥哥哥","titleColor":"#333333","letter":"D","id":"c191a2c51bbd99da"},{"subTitleColor":"#666666","img":"http://7xj45w.com1.z0.glb.clouddn.com/Photo/Headpic/member_pic.jpg","index":"1","titleSize":"16","title":"D嗒滴","userid":"91dc8c13b2ea2b61","subTitleSize":"14","placeholderImg":"widget://Res/avatar.jpg","friendid":"286cc02a8fd6a895","subTitle":"天亮了","titleColor":"#333333","letter":"D","id":"286cc02a8fd6a895"},{"subTitleColor":"#666666","img":"http://7xj45w.com1.z0.glb.clouddn.com/Photo/Headpic/member_pic.jpg","index":"0","titleSize":"16","title":"和大大","userid":"91dc8c13b2ea2b61","subTitleSize":"14","placeholderImg":"widget://Res/avatar.jpg","friendid":"4bb3d8bd57ab9a99","subTitle":"春天里","titleColor":"#333333","letter":"H","id":"4bb3d8bd57ab9a99"},{"subTitleColor":"#666666","img":"http://7xj45w.com1.z0.glb.clouddn.com/Photo/Headpic/member_pic.jpg","index":"0","titleSize":"16","title":"yuao","userid":"91dc8c13b2ea2b61","subTitleSize":"14","placeholderImg":"widget://Res/avatar.jpg","friendid":"42fb18143660c0fa","subTitle":"我是歌手","titleColor":"#333333","letter":"Y","id":"42fb18143660c0fa"},{"subTitleColor":"#666666","img":"http://7xj45w.com1.z0.glb.clouddn.com/Photo/Headpic/member_pic.jpg","index":"1","titleSize":"16","title":"yuao","userid":"91dc8c13b2ea2b61","subTitleSize":"14","placeholderImg":"widget://Res/avatar.jpg","friendid":"42fb18143660c0fa","subTitle":"我是歌手","titleColor":"#333333","letter":"Y","id":"42fb18143660c0fa"}]
         */
        private List<Friend> list;

        public void setList(List<Friend> list) {
            this.list = list;
        }

        public List<Friend> getList() {
            return list;
        }

    }
}
