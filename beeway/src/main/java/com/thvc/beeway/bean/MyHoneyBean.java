package com.thvc.beeway.bean;

import java.util.List;

/**
 * 项目名称：Beeway
 * 类描述：
 * 创建人：颜松梁.
 * 创建时间：2015/9/29 18:55
 * 修改人：Administrator
 * 修改时间：2015/9/29 18:55
 * 修改备注：
 */
public class MyHoneyBean {


    /**
     * status : 1
     * data : {"count":"346","nowPage":1,"totalPage":35,"list":[{"id":"414","scoreType":"1","scoreTag":"active_login","scoreNum":"5","gainId":"007cc15411e3dd82","fromId":"","info":"登陆奖励了5分","addtime":"1444353608"},{"id":"413","scoreType":"1","scoreTag":"active_login","scoreNum":"5","gainId":"fee16a3b4f4760e3","fromId":"","info":"登陆奖励了5分","addtime":"1444353594"},{"id":"412","scoreType":"1","scoreTag":"active_login","scoreNum":"5","gainId":"91dc8c13b2ea2b61","fromId":"","info":"登陆奖励了5分","addtime":"1444352222"},{"id":"411","scoreType":"1","scoreTag":"active_login","scoreNum":"5","gainId":"80ff42514d5eb916","fromId":"","info":"登陆奖励了5分","addtime":"1444226520"},{"id":"410","scoreType":"2","scoreTag":"","scoreNum":"13","gainId":"86e704e9c3d02e1a","fromId":"0","info":"兑换[黄山香溪漂流 随时预定随到随进 黄山天湖漂流门票]用掉了13积分","addtime":"1444225850"},{"id":"409","scoreType":"1","scoreTag":"active_login","scoreNum":"5","gainId":"91dc8c13b2ea2b61","fromId":"","info":"登陆奖励了5分","addtime":"1444211139"},{"id":"408","scoreType":"1","scoreTag":"user_info_complete","scoreNum":"50","gainId":"86e704e9c3d02e1a","fromId":"","info":"完善个人资料奖励了50分","addtime":"1444209390"},{"id":"406","scoreType":"1","scoreTag":"user_reg","scoreNum":"100","gainId":"86e704e9c3d02e1a","fromId":"","info":"用户注册奖励了100分","addtime":"1444209198"},{"id":"407","scoreType":"1","scoreTag":"active_login","scoreNum":"5","gainId":"86e704e9c3d02e1a","fromId":"","info":"登陆奖励了5分","addtime":"1444209198"},{"id":"405","scoreType":"1","scoreTag":"active_praise","scoreNum":"1","gainId":"80ff42514d5eb916","fromId":"bda13618c2c084d5","info":"帮别人点赞奖励了1分","addtime":"1444206023"}]}
     * info :
     */

    private int status;
    private DataEntity data;
    private String info;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public DataEntity getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public static class DataEntity {
        /**
         * count : 346
         * nowPage : 1
         * totalPage : 35
         * list : [{"id":"414","scoreType":"1","scoreTag":"active_login","scoreNum":"5","gainId":"007cc15411e3dd82","fromId":"","info":"登陆奖励了5分","addtime":"1444353608"},{"id":"413","scoreType":"1","scoreTag":"active_login","scoreNum":"5","gainId":"fee16a3b4f4760e3","fromId":"","info":"登陆奖励了5分","addtime":"1444353594"},{"id":"412","scoreType":"1","scoreTag":"active_login","scoreNum":"5","gainId":"91dc8c13b2ea2b61","fromId":"","info":"登陆奖励了5分","addtime":"1444352222"},{"id":"411","scoreType":"1","scoreTag":"active_login","scoreNum":"5","gainId":"80ff42514d5eb916","fromId":"","info":"登陆奖励了5分","addtime":"1444226520"},{"id":"410","scoreType":"2","scoreTag":"","scoreNum":"13","gainId":"86e704e9c3d02e1a","fromId":"0","info":"兑换[黄山香溪漂流 随时预定随到随进 黄山天湖漂流门票]用掉了13积分","addtime":"1444225850"},{"id":"409","scoreType":"1","scoreTag":"active_login","scoreNum":"5","gainId":"91dc8c13b2ea2b61","fromId":"","info":"登陆奖励了5分","addtime":"1444211139"},{"id":"408","scoreType":"1","scoreTag":"user_info_complete","scoreNum":"50","gainId":"86e704e9c3d02e1a","fromId":"","info":"完善个人资料奖励了50分","addtime":"1444209390"},{"id":"406","scoreType":"1","scoreTag":"user_reg","scoreNum":"100","gainId":"86e704e9c3d02e1a","fromId":"","info":"用户注册奖励了100分","addtime":"1444209198"},{"id":"407","scoreType":"1","scoreTag":"active_login","scoreNum":"5","gainId":"86e704e9c3d02e1a","fromId":"","info":"登陆奖励了5分","addtime":"1444209198"},{"id":"405","scoreType":"1","scoreTag":"active_praise","scoreNum":"1","gainId":"80ff42514d5eb916","fromId":"bda13618c2c084d5","info":"帮别人点赞奖励了1分","addtime":"1444206023"}]
         */

        private String count;
        private int nowPage;
        private int totalPage;
        private List<ListEntity> list;

        public void setCount(String count) {
            this.count = count;
        }

        public void setNowPage(int nowPage) {
            this.nowPage = nowPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public String getCount() {
            return count;
        }

        public int getNowPage() {
            return nowPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class ListEntity {
            /**
             * id : 414
             * scoreType : 1
             * scoreTag : active_login
             * scoreNum : 5
             * gainId : 007cc15411e3dd82
             * fromId :
             * info : 登陆奖励了5分
             * addtime : 1444353608
             */

            private String id;
            private String scoreType;
            private String scoreTag;
            private String scoreNum;
            private String gainId;
            private String fromId;
            private String info;
            private String addtime;

            public void setId(String id) {
                this.id = id;
            }

            public void setScoreType(String scoreType) {
                this.scoreType = scoreType;
            }

            public void setScoreTag(String scoreTag) {
                this.scoreTag = scoreTag;
            }

            public void setScoreNum(String scoreNum) {
                this.scoreNum = scoreNum;
            }

            public void setGainId(String gainId) {
                this.gainId = gainId;
            }

            public void setFromId(String fromId) {
                this.fromId = fromId;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getId() {
                return id;
            }

            public String getScoreType() {
                return scoreType;
            }

            public String getScoreTag() {
                return scoreTag;
            }

            public String getScoreNum() {
                return scoreNum;
            }

            public String getGainId() {
                return gainId;
            }

            public String getFromId() {
                return fromId;
            }

            public String getInfo() {
                return info;
            }

            public String getAddtime() {
                return addtime;
            }
        }
    }
}
