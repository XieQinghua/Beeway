package com.thvc.beeway.bean;

import java.util.List;

/**
 * 项目名称：Beeway
 * 类描述：好友详情Bean
 * 创建人：谢庆华.
 * 创建时间：2015/9/10 16:59
 * 修改人：Administrator
 * 修改时间：2015/9/10 16:59
 * 修改备注：
 */
public class FriendDetailBean {

    /**
     * data : {"isfrid":1,"lastTrack":null,"friendid":"91dc8c13b2ea2b61","ismy":2,"friend":{"birthday":"2000-01-01","allow":"1","num_country":"0","num_want":"3","latitude":"28.217345","num_comment":"0","solevar":"91dc8c13b2ea2b61","headpic":"Photo/Headpic/member_pic.jpg","content":"普通版晚上 毛将焉附天 玻利维亚我 火车人擦肩","totalfee":"1000.00","cardid":"0","edits":"17","geohash":"wt028bc3yyqg","nickname":"大Da","vippwd":"","id":"91dc8c13b2ea2b61","email":"wskddddht@qq.com","ishot":"2","num_track":"8","longitude":"112.886537","moditime":"1441875193","im":"1","level":"0","openid":"","totaljifen":"0","groupid":"","sex":"1","num_city":"0","mobile":"15581557239","bill":"1","num_scenic":"0","uppic":"2","sort":"1","num_praise":"4","overdata":"1","num_collect":"8","realname":"我是谁","num_treasure":"0","system":"2","usefee":"930.00","addtime":"1438308575","letter":"D","num_fans":"3","device":"","age":"15","usejifen":"0"},"check":1,"track":1,"userid":"c191a2c51bbd99da","_URL_":["APP","User","detail"],"relation":{"friendid":"91dc8c13b2ea2b61","addtime":"1438397467","start":"2","remark":"","id":"126","userid":"c191a2c51bbd99da","status":"1"}}
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
         * isfrid : 1
         * lastTrack : null
         * friendid : 91dc8c13b2ea2b61
         * ismy : 2
         * friend : {"birthday":"2000-01-01","allow":"1","num_country":"0","num_want":"3","latitude":"28.217345","num_comment":"0","solevar":"91dc8c13b2ea2b61","headpic":"Photo/Headpic/member_pic.jpg","content":"普通版晚上 毛将焉附天 玻利维亚我 火车人擦肩","totalfee":"1000.00","cardid":"0","edits":"17","geohash":"wt028bc3yyqg","nickname":"大Da","vippwd":"","id":"91dc8c13b2ea2b61","email":"wskddddht@qq.com","ishot":"2","num_track":"8","longitude":"112.886537","moditime":"1441875193","im":"1","level":"0","openid":"","totaljifen":"0","groupid":"","sex":"1","num_city":"0","mobile":"15581557239","bill":"1","num_scenic":"0","uppic":"2","sort":"1","num_praise":"4","overdata":"1","num_collect":"8","realname":"我是谁","num_treasure":"0","system":"2","usefee":"930.00","addtime":"1438308575","letter":"D","num_fans":"3","device":"","age":"15","usejifen":"0"}
         * check : 1
         * track : 1
         * userid : c191a2c51bbd99da
         * _URL_ : ["APP","User","detail"]
         * relation : {"friendid":"91dc8c13b2ea2b61","addtime":"1438397467","start":"2","remark":"","id":"126","userid":"c191a2c51bbd99da","status":"1"}
         */
        private int isfrid;
        private String lastTrack;
        private String friendid;
        private int ismy;
        private FriendEntity friend;
        private int check;
        private int track;
        private String userid;
        private List<String> _URL_;
        private RelationEntity relation;

        public void setIsfrid(int isfrid) {
            this.isfrid = isfrid;
        }

        public void setLastTrack(String lastTrack) {
            this.lastTrack = lastTrack;
        }

        public void setFriendid(String friendid) {
            this.friendid = friendid;
        }

        public void setIsmy(int ismy) {
            this.ismy = ismy;
        }

        public void setFriend(FriendEntity friend) {
            this.friend = friend;
        }

        public void setCheck(int check) {
            this.check = check;
        }

        public void setTrack(int track) {
            this.track = track;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void set_URL_(List<String> _URL_) {
            this._URL_ = _URL_;
        }

        public void setRelation(RelationEntity relation) {
            this.relation = relation;
        }

        public int getIsfrid() {
            return isfrid;
        }

        public String getLastTrack() {
            return lastTrack;
        }

        public String getFriendid() {
            return friendid;
        }

        public int getIsmy() {
            return ismy;
        }

        public FriendEntity getFriend() {
            return friend;
        }

        public int getCheck() {
            return check;
        }

        public int getTrack() {
            return track;
        }

        public String getUserid() {
            return userid;
        }

        public List<String> get_URL_() {
            return _URL_;
        }

        public RelationEntity getRelation() {
            return relation;
        }

        public static class FriendEntity {
            /**
             * birthday : 2000-01-01
             * allow : 1
             * num_country : 0
             * num_want : 3
             * latitude : 28.217345
             * num_comment : 0
             * solevar : 91dc8c13b2ea2b61
             * headpic : Photo/Headpic/member_pic.jpg
             * content : 普通版晚上 毛将焉附天 玻利维亚我 火车人擦肩
             * totalfee : 1000.00
             * cardid : 0
             * edits : 17
             * geohash : wt028bc3yyqg
             * nickname : 大Da
             * vippwd :
             * id : 91dc8c13b2ea2b61
             * email : wskddddht@qq.com
             * ishot : 2
             * num_track : 8
             * longitude : 112.886537
             * moditime : 1441875193
             * im : 1
             * level : 0
             * openid :
             * totaljifen : 0
             * groupid :
             * sex : 1
             * num_city : 0
             * mobile : 15581557239
             * bill : 1
             * num_scenic : 0
             * uppic : 2
             * sort : 1
             * num_praise : 4
             * overdata : 1
             * num_collect : 8
             * realname : 我是谁
             * num_treasure : 0
             * system : 2
             * usefee : 930.00
             * addtime : 1438308575
             * letter : D
             * num_fans : 3
             * device :
             * age : 15
             * usejifen : 0
             */
            private String birthday;
            private String allow;
            private String num_country;
            private String num_want;
            private String latitude;
            private String num_comment;
            private String solevar;
            private String headpic;
            private String content;
            private String totalfee;
            private String cardid;
            private String edits;
            private String geohash;
            private String nickname;
            private String vippwd;
            private String id;
            private String email;
            private String ishot;
            private String num_track;
            private String longitude;
            private String moditime;
            private String im;
            private String level;
            private String openid;
            private String totaljifen;
            private String groupid;
            private String sex;
            private String num_city;
            private String mobile;
            private String bill;
            private String num_scenic;
            private String uppic;
            private String sort;
            private String num_praise;
            private String overdata;
            private String num_collect;
            private String realname;
            private String num_treasure;
            private String system;
            private String usefee;
            private String addtime;
            private String letter;
            private String num_fans;
            private String device;
            private String age;
            private String usejifen;

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public void setAllow(String allow) {
                this.allow = allow;
            }

            public void setNum_country(String num_country) {
                this.num_country = num_country;
            }

            public void setNum_want(String num_want) {
                this.num_want = num_want;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public void setNum_comment(String num_comment) {
                this.num_comment = num_comment;
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

            public void setTotalfee(String totalfee) {
                this.totalfee = totalfee;
            }

            public void setCardid(String cardid) {
                this.cardid = cardid;
            }

            public void setEdits(String edits) {
                this.edits = edits;
            }

            public void setGeohash(String geohash) {
                this.geohash = geohash;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setVippwd(String vippwd) {
                this.vippwd = vippwd;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public void setIshot(String ishot) {
                this.ishot = ishot;
            }

            public void setNum_track(String num_track) {
                this.num_track = num_track;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
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

            public void setSex(String sex) {
                this.sex = sex;
            }

            public void setNum_city(String num_city) {
                this.num_city = num_city;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public void setBill(String bill) {
                this.bill = bill;
            }

            public void setNum_scenic(String num_scenic) {
                this.num_scenic = num_scenic;
            }

            public void setUppic(String uppic) {
                this.uppic = uppic;
            }

            public void setSort(String sort) {
                this.sort = sort;
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

            public void setNum_treasure(String num_treasure) {
                this.num_treasure = num_treasure;
            }

            public void setSystem(String system) {
                this.system = system;
            }

            public void setUsefee(String usefee) {
                this.usefee = usefee;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public void setLetter(String letter) {
                this.letter = letter;
            }

            public void setNum_fans(String num_fans) {
                this.num_fans = num_fans;
            }

            public void setDevice(String device) {
                this.device = device;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public void setUsejifen(String usejifen) {
                this.usejifen = usejifen;
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

            public String getNum_want() {
                return num_want;
            }

            public String getLatitude() {
                return latitude;
            }

            public String getNum_comment() {
                return num_comment;
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

            public String getTotalfee() {
                return totalfee;
            }

            public String getCardid() {
                return cardid;
            }

            public String getEdits() {
                return edits;
            }

            public String getGeohash() {
                return geohash;
            }

            public String getNickname() {
                return nickname;
            }

            public String getVippwd() {
                return vippwd;
            }

            public String getId() {
                return id;
            }

            public String getEmail() {
                return email;
            }

            public String getIshot() {
                return ishot;
            }

            public String getNum_track() {
                return num_track;
            }

            public String getLongitude() {
                return longitude;
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

            public String getSex() {
                return sex;
            }

            public String getNum_city() {
                return num_city;
            }

            public String getMobile() {
                return mobile;
            }

            public String getBill() {
                return bill;
            }

            public String getNum_scenic() {
                return num_scenic;
            }

            public String getUppic() {
                return uppic;
            }

            public String getSort() {
                return sort;
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

            public String getNum_treasure() {
                return num_treasure;
            }

            public String getSystem() {
                return system;
            }

            public String getUsefee() {
                return usefee;
            }

            public String getAddtime() {
                return addtime;
            }

            public String getLetter() {
                return letter;
            }

            public String getNum_fans() {
                return num_fans;
            }

            public String getDevice() {
                return device;
            }

            public String getAge() {
                return age;
            }

            public String getUsejifen() {
                return usejifen;
            }
        }

        public static class RelationEntity {
            /**
             * friendid : 91dc8c13b2ea2b61
             * addtime : 1438397467
             * start : 2
             * remark :
             * id : 126
             * userid : c191a2c51bbd99da
             * status : 1
             */
            private String friendid;
            private String addtime;
            private String start;
            private String remark;
            private String id;
            private String userid;
            private String status;

            public void setFriendid(String friendid) {
                this.friendid = friendid;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public void setStart(String start) {
                this.start = start;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getFriendid() {
                return friendid;
            }

            public String getAddtime() {
                return addtime;
            }

            public String getStart() {
                return start;
            }

            public String getRemark() {
                return remark;
            }

            public String getId() {
                return id;
            }

            public String getUserid() {
                return userid;
            }

            public String getStatus() {
                return status;
            }
        }
    }
}
