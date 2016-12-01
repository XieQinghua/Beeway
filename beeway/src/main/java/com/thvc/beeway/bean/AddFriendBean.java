package com.thvc.beeway.bean;

import java.util.List;

/**
 * 项目名称：Beeway
 * 类描述：添加蜂友，会话内根据搜素词搜索好友结果bean
 * 创建人：谢庆华.
 * 创建时间：2015/9/11 16:15
 * 修改人：Administrator
 * 修改时间：2015/9/11 16:15
 * 修改备注：
 * 接口：http://www.hlfyb.com/rest.php/APP/User/findFriend?userid=c191a2c51bbd99da&nickname=神秘蜂&p=0
 */
public class AddFriendBean {

    /**
     * data : {"totalPage":2,"count":"14","list":[{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"ws105x75ujw9","id":"157","ishot":"2","isgeek":"0","longitude":"114.064359","qq":"","moditime":"1444979313","im":"1","level":"lv2","openid":"","totaljifen":"105","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":7892998,"num_want":"0","city":"深圳市","latitude":"22.540499","solevar":"df84472a0a02720e","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"18998741236","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444978992","age":"15","usejifen":"105"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"","id":"156","ishot":"2","isgeek":"0","longitude":"","qq":"","moditime":"0","im":"1","level":"lv2","openid":"","totaljifen":"105","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":12257003,"num_want":"0","city":"","latitude":"","solevar":"36ff2ba7aea178e1","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"18911262254","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444978920","age":"15","usejifen":"105"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"","id":"155","ishot":"2","isgeek":"0","longitude":"","qq":"","moditime":"0","im":"1","level":"lv2","openid":"","totaljifen":"105","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":12257003,"num_want":"0","city":"","latitude":"","solevar":"15c429c1f9430656","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"15200917597","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444963190","age":"15","usejifen":"105"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"","id":"152","ishot":"2","isgeek":"0","longitude":"","qq":"","moditime":"0","im":"1","level":"lv2","openid":"","totaljifen":"105","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":12257003,"num_want":"0","city":"","latitude":"","solevar":"6f265b6b57e07e78","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"18570322333","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444875169","age":"15","usejifen":"105"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"","id":"151","ishot":"2","isgeek":"0","longitude":"","qq":"","moditime":"0","im":"1","level":"lv2","openid":"","totaljifen":"105","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":12257003,"num_want":"0","city":"","latitude":"","solevar":"0cedef1ee6db12ac","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"18570322642","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444874671","age":"15","usejifen":"105"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"","id":"150","ishot":"2","isgeek":"0","longitude":"","qq":"","moditime":"0","im":"1","level":"lv2","openid":"","totaljifen":"105","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":12257003,"num_want":"0","city":"","latitude":"","solevar":"959ee37125d19c94","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"15574642819","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444874641","age":"15","usejifen":"105"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"2","geohash":"wt02g0tzu3xq","id":"149","ishot":"2","isgeek":"0","longitude":"112.991487","qq":"","moditime":"1445080025","im":"1","level":"lv2","openid":"","totaljifen":"157","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"1","birthday":"2000-01-01","allow":"1","num_country":"0","distance":7359544,"num_want":"0","city":"长沙市","latitude":"28.260922","solevar":"e7ecc06cd0f05ee7","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂大朋鸟","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"13307481888","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444805972","age":"15","usejifen":"157"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"","id":"146","ishot":"2","isgeek":"0","longitude":"","qq":"","moditime":"0","im":"1","level":"lv1","openid":"","totaljifen":"100","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":12257003,"num_want":"0","city":"","latitude":"","solevar":"256fd91a7f1b3ee9","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"18229996702","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444800122","age":"15","usejifen":"100"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"ws105x75ujw9","id":"143","ishot":"2","isgeek":"0","longitude":"114.064359","qq":"","moditime":"1444966290","im":"1","level":"lv2","openid":"","totaljifen":"155","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"1","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":7892998,"num_want":"0","city":"深圳市","latitude":"22.540499","solevar":"808c29b811665fcf","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"3","address":"","sex":"1","num_city":"0","mobile":"17773163456","wechat":"","num_praise":"1","overdata":"1","num_collect":"0","realname":"","addtime":"1444724508","age":"15","usejifen":"129"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"wt028cu2rv15","id":"140","ishot":"2","isgeek":"0","longitude":"112.890692","qq":"","moditime":"1444697094","im":"1","level":"lv2","openid":"","totaljifen":"105","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":7367606,"num_want":"0","city":"长沙市","latitude":"28.222574","solevar":"4e7f50705bd53c6c","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"13755091095","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444697079","age":"15","usejifen":"105"}],"nowPage":1}
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
         * totalPage : 2
         * count : 14
         * list : [{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"ws105x75ujw9","id":"157","ishot":"2","isgeek":"0","longitude":"114.064359","qq":"","moditime":"1444979313","im":"1","level":"lv2","openid":"","totaljifen":"105","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":7892998,"num_want":"0","city":"深圳市","latitude":"22.540499","solevar":"df84472a0a02720e","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"18998741236","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444978992","age":"15","usejifen":"105"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"","id":"156","ishot":"2","isgeek":"0","longitude":"","qq":"","moditime":"0","im":"1","level":"lv2","openid":"","totaljifen":"105","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":12257003,"num_want":"0","city":"","latitude":"","solevar":"36ff2ba7aea178e1","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"18911262254","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444978920","age":"15","usejifen":"105"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"","id":"155","ishot":"2","isgeek":"0","longitude":"","qq":"","moditime":"0","im":"1","level":"lv2","openid":"","totaljifen":"105","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":12257003,"num_want":"0","city":"","latitude":"","solevar":"15c429c1f9430656","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"15200917597","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444963190","age":"15","usejifen":"105"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"","id":"152","ishot":"2","isgeek":"0","longitude":"","qq":"","moditime":"0","im":"1","level":"lv2","openid":"","totaljifen":"105","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":12257003,"num_want":"0","city":"","latitude":"","solevar":"6f265b6b57e07e78","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"18570322333","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444875169","age":"15","usejifen":"105"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"","id":"151","ishot":"2","isgeek":"0","longitude":"","qq":"","moditime":"0","im":"1","level":"lv2","openid":"","totaljifen":"105","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":12257003,"num_want":"0","city":"","latitude":"","solevar":"0cedef1ee6db12ac","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"18570322642","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444874671","age":"15","usejifen":"105"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"","id":"150","ishot":"2","isgeek":"0","longitude":"","qq":"","moditime":"0","im":"1","level":"lv2","openid":"","totaljifen":"105","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":12257003,"num_want":"0","city":"","latitude":"","solevar":"959ee37125d19c94","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"15574642819","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444874641","age":"15","usejifen":"105"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"2","geohash":"wt02g0tzu3xq","id":"149","ishot":"2","isgeek":"0","longitude":"112.991487","qq":"","moditime":"1445080025","im":"1","level":"lv2","openid":"","totaljifen":"157","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"1","birthday":"2000-01-01","allow":"1","num_country":"0","distance":7359544,"num_want":"0","city":"长沙市","latitude":"28.260922","solevar":"e7ecc06cd0f05ee7","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂大朋鸟","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"13307481888","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444805972","age":"15","usejifen":"157"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"","id":"146","ishot":"2","isgeek":"0","longitude":"","qq":"","moditime":"0","im":"1","level":"lv1","openid":"","totaljifen":"100","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":12257003,"num_want":"0","city":"","latitude":"","solevar":"256fd91a7f1b3ee9","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"18229996702","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444800122","age":"15","usejifen":"100"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"ws105x75ujw9","id":"143","ishot":"2","isgeek":"0","longitude":"114.064359","qq":"","moditime":"1444966290","im":"1","level":"lv2","openid":"","totaljifen":"155","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"1","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":7892998,"num_want":"0","city":"深圳市","latitude":"22.540499","solevar":"808c29b811665fcf","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"3","address":"","sex":"1","num_city":"0","mobile":"17773163456","wechat":"","num_praise":"1","overdata":"1","num_collect":"0","realname":"","addtime":"1444724508","age":"15","usejifen":"129"},{"num_comment":"0","blog":"","totalfee":"0.00","edits":"0","geohash":"wt028cu2rv15","id":"140","ishot":"2","isgeek":"0","longitude":"112.890692","qq":"","moditime":"1444697094","im":"1","level":"lv2","openid":"","totaljifen":"105","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"S","num_fans":"0","birthday":"2000-01-01","allow":"1","num_country":"0","distance":7367606,"num_want":"0","city":"长沙市","latitude":"28.222574","solevar":"4e7f50705bd53c6c","headpic":"Photo/Headpic/member_pic.jpg","content":"","weibo":"","cardid":"0","nickname":"神秘蜂","vippwd":"","email":"","num_track":"0","address":"","sex":"1","num_city":"0","mobile":"13755091095","wechat":"","num_praise":"0","overdata":"1","num_collect":"0","realname":"","addtime":"1444697079","age":"15","usejifen":"105"}]
         * nowPage : 1
         */
        private int totalPage;
        private String count;
        private List<ListEntity> list;
        private int nowPage;

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public void setNowPage(int nowPage) {
            this.nowPage = nowPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public String getCount() {
            return count;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public int getNowPage() {
            return nowPage;
        }

        public static class ListEntity {
            /**
             * num_comment : 0
             * blog :
             * totalfee : 0.00
             * edits : 0
             * geohash : ws105x75ujw9
             * id : 157
             * ishot : 2
             * isgeek : 0
             * longitude : 114.064359
             * qq :
             * moditime : 1444979313
             * im : 1
             * level : lv2
             * openid :
             * totaljifen : 105
             * groupid :
             * bill : 1
             * num_scenic : 0
             * geektag :
             * uppic : 2
             * sort : 1
             * num_treasure : 0
             * system : 2
             * usefee : 0.00
             * letter : S
             * num_fans : 0
             * birthday : 2000-01-01
             * allow : 1
             * num_country : 0
             * distance : 7892998
             * num_want : 0
             * city : 深圳市
             * latitude : 22.540499
             * solevar : df84472a0a02720e
             * headpic : Photo/Headpic/member_pic.jpg
             * content :
             * weibo :
             * cardid : 0
             * nickname : 神秘蜂
             * vippwd :
             * email :
             * num_track : 0
             * address :
             * sex : 1
             * num_city : 0
             * mobile : 18998741236
             * wechat :
             * num_praise : 0
             * overdata : 1
             * num_collect : 0
             * realname :
             * addtime : 1444978992
             * age : 15
             * usejifen : 105
             */
            private String num_comment;
            private String blog;
            private String totalfee;
            private String edits;
            private String geohash;
            private String id;
            private String ishot;
            private String isgeek;
            private String longitude;
            private String qq;
            private String moditime;
            private String im;
            private String level;
            private String openid;
            private String totaljifen;
            private String groupid;
            private String bill;
            private String num_scenic;
            private String geektag;
            private String uppic;
            private String sort;
            private String num_treasure;
            private String system;
            private String usefee;
            private String letter;
            private String num_fans;
            private String birthday;
            private String allow;
            private String num_country;
            private int distance;
            private String num_want;
            private String city;
            private String latitude;
            private String solevar;
            private String headpic;
            private String content;
            private String weibo;
            private String cardid;
            private String nickname;
            private String vippwd;
            private String email;
            private String num_track;
            private String address;
            private String sex;
            private String num_city;
            private String mobile;
            private String wechat;
            private String num_praise;
            private String overdata;
            private String num_collect;
            private String realname;
            private String addtime;
            private String age;
            private String usejifen;

            public void setNum_comment(String num_comment) {
                this.num_comment = num_comment;
            }

            public void setBlog(String blog) {
                this.blog = blog;
            }

            public void setTotalfee(String totalfee) {
                this.totalfee = totalfee;
            }

            public void setEdits(String edits) {
                this.edits = edits;
            }

            public void setGeohash(String geohash) {
                this.geohash = geohash;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setIshot(String ishot) {
                this.ishot = ishot;
            }

            public void setIsgeek(String isgeek) {
                this.isgeek = isgeek;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public void setModitime(String moditime) {
                this.moditime = moditime;
            }

            public void setIm(String im) {
                this.im = im;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public void setTotaljifen(String totaljifen) {
                this.totaljifen = totaljifen;
            }

            public void setGroupid(String groupid) {
                this.groupid = groupid;
            }

            public void setBill(String bill) {
                this.bill = bill;
            }

            public void setNum_scenic(String num_scenic) {
                this.num_scenic = num_scenic;
            }

            public void setGeektag(String geektag) {
                this.geektag = geektag;
            }

            public void setUppic(String uppic) {
                this.uppic = uppic;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public void setNum_treasure(String num_treasure) {
                this.num_treasure = num_treasure;
            }

            public void setSystem(String system) {
                this.system = system;
            }

            public void setUsefee(String usefee) {
                this.usefee = usefee;
            }

            public void setLetter(String letter) {
                this.letter = letter;
            }

            public void setNum_fans(String num_fans) {
                this.num_fans = num_fans;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public void setAllow(String allow) {
                this.allow = allow;
            }

            public void setNum_country(String num_country) {
                this.num_country = num_country;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public void setNum_want(String num_want) {
                this.num_want = num_want;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public void setSolevar(String solevar) {
                this.solevar = solevar;
            }

            public void setHeadpic(String headpic) {
                this.headpic = headpic;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setWeibo(String weibo) {
                this.weibo = weibo;
            }

            public void setCardid(String cardid) {
                this.cardid = cardid;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setVippwd(String vippwd) {
                this.vippwd = vippwd;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public void setNum_track(String num_track) {
                this.num_track = num_track;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public void setNum_city(String num_city) {
                this.num_city = num_city;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public void setWechat(String wechat) {
                this.wechat = wechat;
            }

            public void setNum_praise(String num_praise) {
                this.num_praise = num_praise;
            }

            public void setOverdata(String overdata) {
                this.overdata = overdata;
            }

            public void setNum_collect(String num_collect) {
                this.num_collect = num_collect;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public void setUsejifen(String usejifen) {
                this.usejifen = usejifen;
            }

            public String getNum_comment() {
                return num_comment;
            }

            public String getBlog() {
                return blog;
            }

            public String getTotalfee() {
                return totalfee;
            }

            public String getEdits() {
                return edits;
            }

            public String getGeohash() {
                return geohash;
            }

            public String getId() {
                return id;
            }

            public String getIshot() {
                return ishot;
            }

            public String getIsgeek() {
                return isgeek;
            }

            public String getLongitude() {
                return longitude;
            }

            public String getQq() {
                return qq;
            }

            public String getModitime() {
                return moditime;
            }

            public String getIm() {
                return im;
            }

            public String getLevel() {
                return level;
            }

            public String getOpenid() {
                return openid;
            }

            public String getTotaljifen() {
                return totaljifen;
            }

            public String getGroupid() {
                return groupid;
            }

            public String getBill() {
                return bill;
            }

            public String getNum_scenic() {
                return num_scenic;
            }

            public String getGeektag() {
                return geektag;
            }

            public String getUppic() {
                return uppic;
            }

            public String getSort() {
                return sort;
            }

            public String getNum_treasure() {
                return num_treasure;
            }

            public String getSystem() {
                return system;
            }

            public String getUsefee() {
                return usefee;
            }

            public String getLetter() {
                return letter;
            }

            public String getNum_fans() {
                return num_fans;
            }

            public String getBirthday() {
                return birthday;
            }

            public String getAllow() {
                return allow;
            }

            public String getNum_country() {
                return num_country;
            }

            public int getDistance() {
                return distance;
            }

            public String getNum_want() {
                return num_want;
            }

            public String getCity() {
                return city;
            }

            public String getLatitude() {
                return latitude;
            }

            public String getSolevar() {
                return solevar;
            }

            public String getHeadpic() {
                return headpic;
            }

            public String getContent() {
                return content;
            }

            public String getWeibo() {
                return weibo;
            }

            public String getCardid() {
                return cardid;
            }

            public String getNickname() {
                return nickname;
            }

            public String getVippwd() {
                return vippwd;
            }

            public String getEmail() {
                return email;
            }

            public String getNum_track() {
                return num_track;
            }

            public String getAddress() {
                return address;
            }

            public String getSex() {
                return sex;
            }

            public String getNum_city() {
                return num_city;
            }

            public String getMobile() {
                return mobile;
            }

            public String getWechat() {
                return wechat;
            }

            public String getNum_praise() {
                return num_praise;
            }

            public String getOverdata() {
                return overdata;
            }

            public String getNum_collect() {
                return num_collect;
            }

            public String getRealname() {
                return realname;
            }

            public String getAddtime() {
                return addtime;
            }

            public String getAge() {
                return age;
            }

            public String getUsejifen() {
                return usejifen;
            }
        }
    }
}