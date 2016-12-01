package com.thvc.beeway.bean;

/**
 * 项目名称：Beeway
 * 类描述：我的详细资料bean
 * 创建人：谢庆华.
 * 创建时间：2015/10/10 17:33
 * 修改人：Administrator
 * 修改时间：2015/10/10 17:33
 * 修改备注：
 */
public class MyUserDetailsBean {

    /**
     * data : {"logintimes":"1","num_comment":"0","blog":"12345678","userid":"48d02f9a3bf81795","lastaddr":"","password":"c8a0b904a3202681c653b1d344a88505","edits":2,"geohash":"wt028bjmpwvj","actwechat":"2","id":"137","ishot":"2","isgeek":"0","longitude":"112.89206","qq":"12345678","moditime":"1444705271","im":"1","level":"lv2","openid":"","totaljifen":"175","active":"1","bill":"1","num_scenic":"0","geektag":"","uppic":"2","actmobile":"2","sort":"1","num_treasure":"0","system":"2","usefee":"0.00","letter":"D","num_fans":"0","status":"1","birthday":"2000-01-01","allow":"1","_system":"2","num_country":"0","num_want":"0","city":"长沙市","latitude":"28.213786","solevar":"48d02f9a3bf81795","headpic":"Photo/Headpic/member_pic.jpg","content":"人在塔在！","_deviceid":"","weibo":"","nickname":"德玛西亚","email":"","num_track":"0","daylast":"2015-10-13","address":"","lastip":"","sex":"1","num_city":"0","mobile":"15200917596","wechat":"","daytimes":"1","num_praise":"0","overdata":"1","num_collect":"0","realname":"盖伦","lasttime":"0","_model":"","age":"15","username":"15200917596","usejifen":"175"}
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
         * logintimes : 1
         * num_comment : 0
         * blog : 12345678
         * userid : 48d02f9a3bf81795
         * lastaddr :
         * password : c8a0b904a3202681c653b1d344a88505
         * edits : 2
         * geohash : wt028bjmpwvj
         * actwechat : 2
         * id : 137
         * ishot : 2
         * isgeek : 0
         * longitude : 112.89206
         * qq : 12345678
         * moditime : 1444705271
         * im : 1
         * level : lv2
         * openid :
         * totaljifen : 175
         * active : 1
         * bill : 1
         * num_scenic : 0
         * geektag :
         * uppic : 2
         * actmobile : 2
         * sort : 1
         * num_treasure : 0
         * system : 2
         * usefee : 0.00
         * letter : D
         * num_fans : 0
         * status : 1
         * birthday : 2000-01-01
         * allow : 1
         * _system : 2
         * num_country : 0
         * num_want : 0
         * city : 长沙市
         * latitude : 28.213786
         * solevar : 48d02f9a3bf81795
         * headpic : Photo/Headpic/member_pic.jpg
         * content : 人在塔在！
         * _deviceid :
         * weibo :
         * nickname : 德玛西亚
         * email :
         * num_track : 0
         * daylast : 2015-10-13
         * address :
         * lastip :
         * sex : 1
         * num_city : 0
         * mobile : 15200917596
         * wechat :
         * daytimes : 1
         * num_praise : 0
         * overdata : 1
         * num_collect : 0
         * realname : 盖伦
         * lasttime : 0
         * _model :
         * age : 15
         * username : 15200917596
         * usejifen : 175
         */
        private String logintimes;
        private String num_comment;
        private String blog;
        private String userid;
        private String lastaddr;
        private String password;
        private int edits;
        private String geohash;
        private String actwechat;
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
        private String active;
        private String bill;
        private String num_scenic;
        private String geektag;
        private String uppic;
        private String actmobile;
        private String sort;
        private String num_treasure;
        private String system;
        private String usefee;
        private String letter;
        private String num_fans;
        private String status;
        private String birthday;
        private String allow;
        private String _system;
        private String num_country;
        private String num_want;
        private String city;
        private String latitude;
        private String solevar;
        private String headpic;
        private String content;
        private String _deviceid;
        private String weibo;
        private String nickname;
        private String email;
        private String num_track;
        private String daylast;
        private String address;
        private String lastip;
        private String sex;
        private String num_city;
        private String mobile;
        private String wechat;
        private String daytimes;
        private String num_praise;
        private String overdata;
        private String num_collect;
        private String realname;
        private String lasttime;
        private String _model;
        private String age;
        private String username;
        private String usejifen;

        public void setLogintimes(String logintimes) {
            this.logintimes = logintimes;
        }

        public void setNum_comment(String num_comment) {
            this.num_comment = num_comment;
        }

        public void setBlog(String blog) {
            this.blog = blog;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setLastaddr(String lastaddr) {
            this.lastaddr = lastaddr;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setEdits(int edits) {
            this.edits = edits;
        }

        public void setGeohash(String geohash) {
            this.geohash = geohash;
        }

        public void setActwechat(String actwechat) {
            this.actwechat = actwechat;
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

        public void setActive(String active) {
            this.active = active;
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

        public void setActmobile(String actmobile) {
            this.actmobile = actmobile;
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

        public void setStatus(String status) {
            this.status = status;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setAllow(String allow) {
            this.allow = allow;
        }

        public void set_system(String _system) {
            this._system = _system;
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

        public void set_deviceid(String _deviceid) {
            this._deviceid = _deviceid;
        }

        public void setWeibo(String weibo) {
            this.weibo = weibo;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setNum_track(String num_track) {
            this.num_track = num_track;
        }

        public void setDaylast(String daylast) {
            this.daylast = daylast;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setLastip(String lastip) {
            this.lastip = lastip;
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

        public void setDaytimes(String daytimes) {
            this.daytimes = daytimes;
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

        public void setLasttime(String lasttime) {
            this.lasttime = lasttime;
        }

        public void set_model(String _model) {
            this._model = _model;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setUsejifen(String usejifen) {
            this.usejifen = usejifen;
        }

        public String getLogintimes() {
            return logintimes;
        }

        public String getNum_comment() {
            return num_comment;
        }

        public String getBlog() {
            return blog;
        }

        public String getUserid() {
            return userid;
        }

        public String getLastaddr() {
            return lastaddr;
        }

        public String getPassword() {
            return password;
        }

        public int getEdits() {
            return edits;
        }

        public String getGeohash() {
            return geohash;
        }

        public String getActwechat() {
            return actwechat;
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

        public String getActive() {
            return active;
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

        public String getActmobile() {
            return actmobile;
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

        public String getStatus() {
            return status;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getAllow() {
            return allow;
        }

        public String get_system() {
            return _system;
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

        public String get_deviceid() {
            return _deviceid;
        }

        public String getWeibo() {
            return weibo;
        }

        public String getNickname() {
            return nickname;
        }

        public String getEmail() {
            return email;
        }

        public String getNum_track() {
            return num_track;
        }

        public String getDaylast() {
            return daylast;
        }

        public String getAddress() {
            return address;
        }

        public String getLastip() {
            return lastip;
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

        public String getDaytimes() {
            return daytimes;
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

        public String getLasttime() {
            return lasttime;
        }

        public String get_model() {
            return _model;
        }

        public String getAge() {
            return age;
        }

        public String getUsername() {
            return username;
        }

        public String getUsejifen() {
            return usejifen;
        }
    }
}