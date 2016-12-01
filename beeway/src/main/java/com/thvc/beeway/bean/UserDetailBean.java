package com.thvc.beeway.bean;

import java.util.List;

/**
 * 项目名称：Beeway
 * 类描述：蜂友详情bean
 * 创建人：谢庆华.
 * 创建时间：2015/9/19 10:55
 * 修改人：Administrator
 * 修改时间：2015/9/19 10:55
 * 修改备注：
 */
public class UserDetailBean {

    /**
     * 接口 http://www.hlfyb.com/rest.php/APP/User/detail?userid=48d02f9a3bf81795&friendid=86e704e9c3d02e1a&track=1
     * data : {"isfrid":1,"lastWant":{"_system":"1","latitude":"","cprice":"0.00","solevar":"74a2f0fc6bb70181","pid":"0","remark":"","type":"1","userid":"86e704e9c3d02e1a","good":"0","content":"年纪三十，没有女朋友，想去阳朔，看看能不能找到女朋友，请支援我，给我勇气，结婚时一定请你吃喜酒（不要礼金）[呲牙]","_deviceid":"F0EA0B13-517C-4794-B5C4-823AEAAAC01A","geohash":"","company":"260","share":"0","end":"1445077363","tip":"2","id":"9","isdel":"2","longitude":"","csolevar":"","mprice":"0.00","bill":"1","sprice":"1000.00","sort":"1","url":"","addtime":"1444472563","name":"阳朔十里画廊","_model":"iPhone","comment":"0","support":"0","trans":"0","status":"2"},"ismy":2,"check":1,"levelArr":{"lvImg":"","lvScore":500,"lvIntro":"","lvName":"宅巢蜂"},"userid":"48d02f9a3bf81795","_URL_":["APP","User","detail"],"relation":{"friendid":"86e704e9c3d02e1a","addtime":"1444471571","start":"2","remark":"","id":"16","userid":"48d02f9a3bf81795","status":"1"},"lastTrack":[{"_system":"2","flag":"","latitude":"28.21463","solevar":"ebaae4ad26fe8a19","remark":"","type":"1","userid":"86e704e9c3d02e1a","good":"0","tid":"0","content":"风景这么美，我现在处于当中","path":"0","_deviceid":"","istype":"4","top":"","geohash":"","company":"1","share":"0","id":"4","isdel":"2","adddate":"2015-09-20","longitude":"112.99979","area":"0","address":"橘子洲头","public_status":"2","bill":"1","weight":"0","iscom":"2","sort":"1","url":"","single":{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441784285","c_table":"Track","c_solevar":"ebaae4ad26fe8a19","fileurl":"avatar/D9B11365-F5A6-44DC-ADA4-30C97A30C09B+1441813068.jpg","addid":"0","company":"1","id":"2284","sort":"0"},"travelid":"0","addtime":"1441784285","comment":"0","_model":"","detail":"烈士公园","collect":"1","trans":"0"},{"_system":"2","flag":"","latitude":"28.20126","solevar":"e426065756794d24","remark":"","type":"1","userid":"86e704e9c3d02e1a","good":"0","tid":"0","content":"此刻就在大山之巅，晚霞笼罩着大地，仿佛整个大地都在你脚下","path":"0","_deviceid":"","istype":"4","top":"头条君，此时不上更待何时！","geohash":"","company":"1","share":"0","id":"7","isdel":"2","adddate":"2015-09-20","longitude":"112.974831","area":"0","address":"橘子洲头","public_status":"2","bill":"1","weight":"0","iscom":"2","sort":"1","url":"","single":{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441785012","c_table":"Track","c_solevar":"e426065756794d24","fileurl":"avatar/440894D0-869D-479B-A43B-F614ABBAF927+1441813730.jpg","addid":"0","company":"1","id":"2288","sort":"0"},"travelid":"0","addtime":"1441785012","comment":"0","_model":"","detail":"南起黑石铺大桥，北至月亮岛，共约26公里。","collect":"0","trans":"0"},{"_system":"2","flag":"","latitude":"27.034027","solevar":"74f612f5b1f7f46a","remark":"","type":"1","userid":"86e704e9c3d02e1a","good":"0","tid":"0","content":"画中的世界","path":"0","_deviceid":"","istype":"4","top":"","geohash":"","company":"3","share":"0","id":"9","isdel":"2","adddate":"2015-09-20","longitude":"100.270129","area":"0","address":"玉龙雪山","public_status":"2","bill":"1","weight":"0","iscom":"2","sort":"1","url":"","single":{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441785032","c_table":"Track","c_solevar":"74f612f5b1f7f46a","fileurl":"avatar/15004DC9-376C-4819-A500-AC389AD9F388+1441813798.jpg","addid":"0","company":"3","id":"2298","sort":"0"},"travelid":"0","addtime":"1441785032","comment":"0","_model":"","detail":"玉龙雪山","collect":"0","trans":"0"}],"friendid":"86e704e9c3d02e1a","friend":{"num_comment":"0","blog":"","totalfee":"0.00","edits":"6","geohash":"wt028cg88q6m","id":"86e704e9c3d02e1a","ishot":"2","isgeek":"0","longitude":"112.889342","qq":"","moditime":"1444792254","im":"1","level":"lv2","openid":"","totaljifen":"390","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"5","system":"2","usefee":"0.00","letter":"B","num_fans":"4","birthday":"2013-10-10","allow":"1","num_country":"0","num_want":"1","city":"长沙市","latitude":"28.222624","solevar":"86e704e9c3d02e1a","headpic":"Photo/Headpic/member_pic.jpg","content":"夺宝奇兵，就看贝贝","weibo":"","cardid":"0","nickname":"贝贝","vippwd":"","email":"842850059@qq.com","num_track":"62","address":"","sex":"1","num_city":"0","mobile":"15874005827","wechat":"","num_praise":"3","overdata":"1","num_collect":"5","realname":"曾小贝","addtime":"1444209198","age":"15","usejifen":"351"},"travleNum":"3","track":1}
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
         * lastWant : {"_system":"1","latitude":"","cprice":"0.00","solevar":"74a2f0fc6bb70181","pid":"0","remark":"","type":"1","userid":"86e704e9c3d02e1a","good":"0","content":"年纪三十，没有女朋友，想去阳朔，看看能不能找到女朋友，请支援我，给我勇气，结婚时一定请你吃喜酒（不要礼金）[呲牙]","_deviceid":"F0EA0B13-517C-4794-B5C4-823AEAAAC01A","geohash":"","company":"260","share":"0","end":"1445077363","tip":"2","id":"9","isdel":"2","longitude":"","csolevar":"","mprice":"0.00","bill":"1","sprice":"1000.00","sort":"1","url":"","addtime":"1444472563","name":"阳朔十里画廊","_model":"iPhone","comment":"0","support":"0","trans":"0","status":"2"}
         * ismy : 2
         * check : 1
         * levelArr : {"lvImg":"","lvScore":500,"lvIntro":"","lvName":"宅巢蜂"}
         * userid : 48d02f9a3bf81795
         * _URL_ : ["APP","User","detail"]
         * relation : {"friendid":"86e704e9c3d02e1a","addtime":"1444471571","start":"2","remark":"","id":"16","userid":"48d02f9a3bf81795","status":"1"}
         * lastTrack : [{"_system":"2","flag":"","latitude":"28.21463","solevar":"ebaae4ad26fe8a19","remark":"","type":"1","userid":"86e704e9c3d02e1a","good":"0","tid":"0","content":"风景这么美，我现在处于当中","path":"0","_deviceid":"","istype":"4","top":"","geohash":"","company":"1","share":"0","id":"4","isdel":"2","adddate":"2015-09-20","longitude":"112.99979","area":"0","address":"橘子洲头","public_status":"2","bill":"1","weight":"0","iscom":"2","sort":"1","url":"","single":{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441784285","c_table":"Track","c_solevar":"ebaae4ad26fe8a19","fileurl":"avatar/D9B11365-F5A6-44DC-ADA4-30C97A30C09B+1441813068.jpg","addid":"0","company":"1","id":"2284","sort":"0"},"travelid":"0","addtime":"1441784285","comment":"0","_model":"","detail":"烈士公园","collect":"1","trans":"0"},{"_system":"2","flag":"","latitude":"28.20126","solevar":"e426065756794d24","remark":"","type":"1","userid":"86e704e9c3d02e1a","good":"0","tid":"0","content":"此刻就在大山之巅，晚霞笼罩着大地，仿佛整个大地都在你脚下","path":"0","_deviceid":"","istype":"4","top":"头条君，此时不上更待何时！","geohash":"","company":"1","share":"0","id":"7","isdel":"2","adddate":"2015-09-20","longitude":"112.974831","area":"0","address":"橘子洲头","public_status":"2","bill":"1","weight":"0","iscom":"2","sort":"1","url":"","single":{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441785012","c_table":"Track","c_solevar":"e426065756794d24","fileurl":"avatar/440894D0-869D-479B-A43B-F614ABBAF927+1441813730.jpg","addid":"0","company":"1","id":"2288","sort":"0"},"travelid":"0","addtime":"1441785012","comment":"0","_model":"","detail":"南起黑石铺大桥，北至月亮岛，共约26公里。","collect":"0","trans":"0"},{"_system":"2","flag":"","latitude":"27.034027","solevar":"74f612f5b1f7f46a","remark":"","type":"1","userid":"86e704e9c3d02e1a","good":"0","tid":"0","content":"画中的世界","path":"0","_deviceid":"","istype":"4","top":"","geohash":"","company":"3","share":"0","id":"9","isdel":"2","adddate":"2015-09-20","longitude":"100.270129","area":"0","address":"玉龙雪山","public_status":"2","bill":"1","weight":"0","iscom":"2","sort":"1","url":"","single":{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441785032","c_table":"Track","c_solevar":"74f612f5b1f7f46a","fileurl":"avatar/15004DC9-376C-4819-A500-AC389AD9F388+1441813798.jpg","addid":"0","company":"3","id":"2298","sort":"0"},"travelid":"0","addtime":"1441785032","comment":"0","_model":"","detail":"玉龙雪山","collect":"0","trans":"0"}]
         * friendid : 86e704e9c3d02e1a
         * friend : {"num_comment":"0","blog":"","totalfee":"0.00","edits":"6","geohash":"wt028cg88q6m","id":"86e704e9c3d02e1a","ishot":"2","isgeek":"0","longitude":"112.889342","qq":"","moditime":"1444792254","im":"1","level":"lv2","openid":"","totaljifen":"390","groupid":"","bill":"1","num_scenic":"0","geektag":"","uppic":"2","sort":"1","num_treasure":"5","system":"2","usefee":"0.00","letter":"B","num_fans":"4","birthday":"2013-10-10","allow":"1","num_country":"0","num_want":"1","city":"长沙市","latitude":"28.222624","solevar":"86e704e9c3d02e1a","headpic":"Photo/Headpic/member_pic.jpg","content":"夺宝奇兵，就看贝贝","weibo":"","cardid":"0","nickname":"贝贝","vippwd":"","email":"842850059@qq.com","num_track":"62","address":"","sex":"1","num_city":"0","mobile":"15874005827","wechat":"","num_praise":"3","overdata":"1","num_collect":"5","realname":"曾小贝","addtime":"1444209198","age":"15","usejifen":"351"}
         * travleNum : 3
         * track : 1
         */
        private int isfrid;
        private LastWantEntity lastWant;
        private int ismy;
        private int check;
        private LevelArrEntity levelArr;
        private String userid;
        private List<String> _URL_;
        private RelationEntity relation;
        private List<LastTrackEntity> lastTrack;
        private String friendid;
        private FriendEntity friend;
        private String travleNum;
        private int track;

        public void setIsfrid(int isfrid) {
            this.isfrid = isfrid;
        }

        public void setLastWant(LastWantEntity lastWant) {
            this.lastWant = lastWant;
        }

        public void setIsmy(int ismy) {
            this.ismy = ismy;
        }

        public void setCheck(int check) {
            this.check = check;
        }

        public void setLevelArr(LevelArrEntity levelArr) {
            this.levelArr = levelArr;
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

        public void setLastTrack(List<LastTrackEntity> lastTrack) {
            this.lastTrack = lastTrack;
        }

        public void setFriendid(String friendid) {
            this.friendid = friendid;
        }

        public void setFriend(FriendEntity friend) {
            this.friend = friend;
        }

        public void setTravleNum(String travleNum) {
            this.travleNum = travleNum;
        }

        public void setTrack(int track) {
            this.track = track;
        }

        public int getIsfrid() {
            return isfrid;
        }

        public LastWantEntity getLastWant() {
            return lastWant;
        }

        public int getIsmy() {
            return ismy;
        }

        public int getCheck() {
            return check;
        }

        public LevelArrEntity getLevelArr() {
            return levelArr;
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

        public List<LastTrackEntity> getLastTrack() {
            return lastTrack;
        }

        public String getFriendid() {
            return friendid;
        }

        public FriendEntity getFriend() {
            return friend;
        }

        public String getTravleNum() {
            return travleNum;
        }

        public int getTrack() {
            return track;
        }

        public static class LastWantEntity {
            /**
             * _system : 1
             * latitude :
             * cprice : 0.00
             * solevar : 74a2f0fc6bb70181
             * pid : 0
             * remark :
             * type : 1
             * userid : 86e704e9c3d02e1a
             * good : 0
             * content : 年纪三十，没有女朋友，想去阳朔，看看能不能找到女朋友，请支援我，给我勇气，结婚时一定请你吃喜酒（不要礼金）[呲牙]
             * _deviceid : F0EA0B13-517C-4794-B5C4-823AEAAAC01A
             * geohash :
             * company : 260
             * share : 0
             * end : 1445077363
             * tip : 2
             * id : 9
             * isdel : 2
             * longitude :
             * csolevar :
             * mprice : 0.00
             * bill : 1
             * sprice : 1000.00
             * sort : 1
             * url :
             * addtime : 1444472563
             * name : 阳朔十里画廊
             * _model : iPhone
             * comment : 0
             * support : 0
             * trans : 0
             * status : 2
             */
            private String _system;
            private String latitude;
            private String cprice;
            private String solevar;
            private String pid;
            private String remark;
            private String type;
            private String userid;
            private String good;
            private String content;
            private String _deviceid;
            private String geohash;
            private String company;
            private String share;
            private String end;
            private String tip;
            private String id;
            private String isdel;
            private String longitude;
            private String csolevar;
            private String mprice;
            private String bill;
            private String sprice;
            private String sort;
            private String url;
            private String addtime;
            private String name;
            private String _model;
            private String comment;
            private String support;
            private String trans;
            private String status;

            public void set_system(String _system) {
                this._system = _system;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public void setCprice(String cprice) {
                this.cprice = cprice;
            }

            public void setSolevar(String solevar) {
                this.solevar = solevar;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public void setGood(String good) {
                this.good = good;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void set_deviceid(String _deviceid) {
                this._deviceid = _deviceid;
            }

            public void setGeohash(String geohash) {
                this.geohash = geohash;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public void setShare(String share) {
                this.share = share;
            }

            public void setEnd(String end) {
                this.end = end;
            }

            public void setTip(String tip) {
                this.tip = tip;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setIsdel(String isdel) {
                this.isdel = isdel;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public void setCsolevar(String csolevar) {
                this.csolevar = csolevar;
            }

            public void setMprice(String mprice) {
                this.mprice = mprice;
            }

            public void setBill(String bill) {
                this.bill = bill;
            }

            public void setSprice(String sprice) {
                this.sprice = sprice;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void set_model(String _model) {
                this._model = _model;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public void setSupport(String support) {
                this.support = support;
            }

            public void setTrans(String trans) {
                this.trans = trans;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String get_system() {
                return _system;
            }

            public String getLatitude() {
                return latitude;
            }

            public String getCprice() {
                return cprice;
            }

            public String getSolevar() {
                return solevar;
            }

            public String getPid() {
                return pid;
            }

            public String getRemark() {
                return remark;
            }

            public String getType() {
                return type;
            }

            public String getUserid() {
                return userid;
            }

            public String getGood() {
                return good;
            }

            public String getContent() {
                return content;
            }

            public String get_deviceid() {
                return _deviceid;
            }

            public String getGeohash() {
                return geohash;
            }

            public String getCompany() {
                return company;
            }

            public String getShare() {
                return share;
            }

            public String getEnd() {
                return end;
            }

            public String getTip() {
                return tip;
            }

            public String getId() {
                return id;
            }

            public String getIsdel() {
                return isdel;
            }

            public String getLongitude() {
                return longitude;
            }

            public String getCsolevar() {
                return csolevar;
            }

            public String getMprice() {
                return mprice;
            }

            public String getBill() {
                return bill;
            }

            public String getSprice() {
                return sprice;
            }

            public String getSort() {
                return sort;
            }

            public String getUrl() {
                return url;
            }

            public String getAddtime() {
                return addtime;
            }

            public String getName() {
                return name;
            }

            public String get_model() {
                return _model;
            }

            public String getComment() {
                return comment;
            }

            public String getSupport() {
                return support;
            }

            public String getTrans() {
                return trans;
            }

            public String getStatus() {
                return status;
            }
        }

        public static class LevelArrEntity {
            /**
             * lvImg :
             * lvScore : 500
             * lvIntro :
             * lvName : 宅巢蜂
             */
            private String lvImg;
            private int lvScore;
            private String lvIntro;
            private String lvName;

            public void setLvImg(String lvImg) {
                this.lvImg = lvImg;
            }

            public void setLvScore(int lvScore) {
                this.lvScore = lvScore;
            }

            public void setLvIntro(String lvIntro) {
                this.lvIntro = lvIntro;
            }

            public void setLvName(String lvName) {
                this.lvName = lvName;
            }

            public String getLvImg() {
                return lvImg;
            }

            public int getLvScore() {
                return lvScore;
            }

            public String getLvIntro() {
                return lvIntro;
            }

            public String getLvName() {
                return lvName;
            }
        }

        public static class RelationEntity {
            /**
             * friendid : 86e704e9c3d02e1a
             * addtime : 1444471571
             * start : 2
             * remark :
             * id : 16
             * userid : 48d02f9a3bf81795
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

        public static class LastTrackEntity {
            /**
             * _system : 2
             * flag :
             * latitude : 28.21463
             * solevar : ebaae4ad26fe8a19
             * remark :
             * type : 1
             * userid : 86e704e9c3d02e1a
             * good : 0
             * tid : 0
             * content : 风景这么美，我现在处于当中
             * path : 0
             * _deviceid :
             * istype : 4
             * top :
             * geohash :
             * company : 1
             * share : 0
             * id : 4
             * isdel : 2
             * adddate : 2015-09-20
             * longitude : 112.99979
             * area : 0
             * address : 橘子洲头
             * public_status : 2
             * bill : 1
             * weight : 0
             * iscom : 2
             * sort : 1
             * url :
             * single : {"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441784285","c_table":"Track","c_solevar":"ebaae4ad26fe8a19","fileurl":"avatar/D9B11365-F5A6-44DC-ADA4-30C97A30C09B+1441813068.jpg","addid":"0","company":"1","id":"2284","sort":"0"}
             * travelid : 0
             * addtime : 1441784285
             * comment : 0
             * _model :
             * detail : 烈士公园
             * collect : 1
             * trans : 0
             */
            private String _system;
            private String flag;
            private String latitude;
            private String solevar;
            private String remark;
            private String type;
            private String userid;
            private String good;
            private String tid;
            private String content;
            private String path;
            private String _deviceid;
            private String istype;
            private String top;
            private String geohash;
            private String company;
            private String share;
            private String id;
            private String isdel;
            private String adddate;
            private String longitude;
            private String area;
            private String address;
            private String public_status;
            private String bill;
            private String weight;
            private String iscom;
            private String sort;
            private String url;
            private SingleEntity single;
            private String travelid;
            private String addtime;
            private String comment;
            private String _model;
            private String detail;
            private String collect;
            private String trans;

            public void set_system(String _system) {
                this._system = _system;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public void setSolevar(String solevar) {
                this.solevar = solevar;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public void setGood(String good) {
                this.good = good;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public void set_deviceid(String _deviceid) {
                this._deviceid = _deviceid;
            }

            public void setIstype(String istype) {
                this.istype = istype;
            }

            public void setTop(String top) {
                this.top = top;
            }

            public void setGeohash(String geohash) {
                this.geohash = geohash;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public void setShare(String share) {
                this.share = share;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setIsdel(String isdel) {
                this.isdel = isdel;
            }

            public void setAdddate(String adddate) {
                this.adddate = adddate;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setPublic_status(String public_status) {
                this.public_status = public_status;
            }

            public void setBill(String bill) {
                this.bill = bill;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public void setIscom(String iscom) {
                this.iscom = iscom;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setSingle(SingleEntity single) {
                this.single = single;
            }

            public void setTravelid(String travelid) {
                this.travelid = travelid;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public void set_model(String _model) {
                this._model = _model;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public void setCollect(String collect) {
                this.collect = collect;
            }

            public void setTrans(String trans) {
                this.trans = trans;
            }

            public String get_system() {
                return _system;
            }

            public String getFlag() {
                return flag;
            }

            public String getLatitude() {
                return latitude;
            }

            public String getSolevar() {
                return solevar;
            }

            public String getRemark() {
                return remark;
            }

            public String getType() {
                return type;
            }

            public String getUserid() {
                return userid;
            }

            public String getGood() {
                return good;
            }

            public String getTid() {
                return tid;
            }

            public String getContent() {
                return content;
            }

            public String getPath() {
                return path;
            }

            public String get_deviceid() {
                return _deviceid;
            }

            public String getIstype() {
                return istype;
            }

            public String getTop() {
                return top;
            }

            public String getGeohash() {
                return geohash;
            }

            public String getCompany() {
                return company;
            }

            public String getShare() {
                return share;
            }

            public String getId() {
                return id;
            }

            public String getIsdel() {
                return isdel;
            }

            public String getAdddate() {
                return adddate;
            }

            public String getLongitude() {
                return longitude;
            }

            public String getArea() {
                return area;
            }

            public String getAddress() {
                return address;
            }

            public String getPublic_status() {
                return public_status;
            }

            public String getBill() {
                return bill;
            }

            public String getWeight() {
                return weight;
            }

            public String getIscom() {
                return iscom;
            }

            public String getSort() {
                return sort;
            }

            public String getUrl() {
                return url;
            }

            public SingleEntity getSingle() {
                return single;
            }

            public String getTravelid() {
                return travelid;
            }

            public String getAddtime() {
                return addtime;
            }

            public String getComment() {
                return comment;
            }

            public String get_model() {
                return _model;
            }

            public String getDetail() {
                return detail;
            }

            public String getCollect() {
                return collect;
            }

            public String getTrans() {
                return trans;
            }

            public static class SingleEntity {
                /**
                 * filekeys :
                 * c_title :
                 * c_affect : atlas
                 * addtime : 1441784285
                 * c_table : Track
                 * c_solevar : ebaae4ad26fe8a19
                 * fileurl : avatar/D9B11365-F5A6-44DC-ADA4-30C97A30C09B+1441813068.jpg
                 * addid : 0
                 * company : 1
                 * id : 2284
                 * sort : 0
                 */
                private String filekeys;
                private String c_title;
                private String c_affect;
                private String addtime;
                private String c_table;
                private String c_solevar;
                private String fileurl;
                private String addid;
                private String company;
                private String id;
                private String sort;

                public void setFilekeys(String filekeys) {
                    this.filekeys = filekeys;
                }

                public void setC_title(String c_title) {
                    this.c_title = c_title;
                }

                public void setC_affect(String c_affect) {
                    this.c_affect = c_affect;
                }

                public void setAddtime(String addtime) {
                    this.addtime = addtime;
                }

                public void setC_table(String c_table) {
                    this.c_table = c_table;
                }

                public void setC_solevar(String c_solevar) {
                    this.c_solevar = c_solevar;
                }

                public void setFileurl(String fileurl) {
                    this.fileurl = fileurl;
                }

                public void setAddid(String addid) {
                    this.addid = addid;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getFilekeys() {
                    return filekeys;
                }

                public String getC_title() {
                    return c_title;
                }

                public String getC_affect() {
                    return c_affect;
                }

                public String getAddtime() {
                    return addtime;
                }

                public String getC_table() {
                    return c_table;
                }

                public String getC_solevar() {
                    return c_solevar;
                }

                public String getFileurl() {
                    return fileurl;
                }

                public String getAddid() {
                    return addid;
                }

                public String getCompany() {
                    return company;
                }

                public String getId() {
                    return id;
                }

                public String getSort() {
                    return sort;
                }
            }
        }

        public static class FriendEntity {
            /**
             * num_comment : 0
             * blog :
             * totalfee : 0.00
             * edits : 6
             * geohash : wt028cg88q6m
             * id : 86e704e9c3d02e1a
             * ishot : 2
             * isgeek : 0
             * longitude : 112.889342
             * qq :
             * moditime : 1444792254
             * im : 1
             * level : lv2
             * openid :
             * totaljifen : 390
             * groupid :
             * bill : 1
             * num_scenic : 0
             * geektag :
             * uppic : 2
             * sort : 1
             * num_treasure : 5
             * system : 2
             * usefee : 0.00
             * letter : B
             * num_fans : 4
             * birthday : 2013-10-10
             * allow : 1
             * num_country : 0
             * num_want : 1
             * city : 长沙市
             * latitude : 28.222624
             * solevar : 86e704e9c3d02e1a
             * headpic : Photo/Headpic/member_pic.jpg
             * content : 夺宝奇兵，就看贝贝
             * weibo :
             * cardid : 0
             * nickname : 贝贝
             * vippwd :
             * email : 842850059@qq.com
             * num_track : 62
             * address :
             * sex : 1
             * num_city : 0
             * mobile : 15874005827
             * wechat :
             * num_praise : 3
             * overdata : 1
             * num_collect : 5
             * realname : 曾小贝
             * addtime : 1444209198
             * age : 15
             * usejifen : 351
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