package com.thvc.beeway.bean;

import java.io.Serializable;
import java.util.List;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/10/15.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class TreasureBean implements Serializable{

    /**
     * data : {"totalPage":1,"count":"2","list":[{"_system":"2","city":"3","latitude":"","privacy":"1","solevar":"f1b86d75f69504f1","userid":"da383f2b0aef4de6","isempty":"2","content":"少年，我看你骨骼清奇，聪慧过人，这里有葵花宝典一部，劝你勤加练习，将来拯救世界就靠你啦！","path":"-0-1-3-","_deviceid":"","finds":"0","province":"3","geohash":"","searchs":"0","company":"0","id":"12","isdel":"2","longitude":"","area":"0","address":"湖南省长沙市天心区坡子街街道","hits":"0","addtime":"1444891206","district":"0","_model":"","detail":"","prompt":"骚年，我看你骨骼清奇，这里有宝藏一份，能否找到，看你缘分！"},{"_system":"2","city":"3","latitude":"28.20126","privacy":"1","solevar":"922f196f0ae74b99","userid":"da383f2b0aef4de6","isempty":"2","content":"如果你找到了这个宝藏，就意味着你可以与我共度晚餐，不过需要你来买单。","path":"-0-1-3-","_deviceid":"","finds":"1","province":"3","geohash":"","searchs":"4","company":"1","id":"11","isdel":"2","longitude":"112.974831","area":"0","address":"橘子洲头","hits":"0","addtime":"1444891176","district":"0","_model":"","detail":"南起黑石铺大桥，北至月亮岛，共约26公里。","prompt":"那个男同学，不要挖这个宝藏，我只属于萌妹子^-^"}],"nowPage":1}
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
         * totalPage : 1
         * count : 2
         * list : [{"_system":"2","city":"3","latitude":"","privacy":"1","solevar":"f1b86d75f69504f1","userid":"da383f2b0aef4de6","isempty":"2","content":"少年，我看你骨骼清奇，聪慧过人，这里有葵花宝典一部，劝你勤加练习，将来拯救世界就靠你啦！","path":"-0-1-3-","_deviceid":"","finds":"0","province":"3","geohash":"","searchs":"0","company":"0","id":"12","isdel":"2","longitude":"","area":"0","address":"湖南省长沙市天心区坡子街街道","hits":"0","addtime":"1444891206","district":"0","_model":"","detail":"","prompt":"骚年，我看你骨骼清奇，这里有宝藏一份，能否找到，看你缘分！"},{"_system":"2","city":"3","latitude":"28.20126","privacy":"1","solevar":"922f196f0ae74b99","userid":"da383f2b0aef4de6","isempty":"2","content":"如果你找到了这个宝藏，就意味着你可以与我共度晚餐，不过需要你来买单。","path":"-0-1-3-","_deviceid":"","finds":"1","province":"3","geohash":"","searchs":"4","company":"1","id":"11","isdel":"2","longitude":"112.974831","area":"0","address":"橘子洲头","hits":"0","addtime":"1444891176","district":"0","_model":"","detail":"南起黑石铺大桥，北至月亮岛，共约26公里。","prompt":"那个男同学，不要挖这个宝藏，我只属于萌妹子^-^"}]
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
             * _system : 2
             * city : 3
             * latitude :
             * privacy : 1
             * solevar : f1b86d75f69504f1
             * userid : da383f2b0aef4de6
             * isempty : 2
             * content : 少年，我看你骨骼清奇，聪慧过人，这里有葵花宝典一部，劝你勤加练习，将来拯救世界就靠你啦！
             * path : -0-1-3-
             * _deviceid :
             * finds : 0
             * province : 3
             * geohash :
             * searchs : 0
             * company : 0
             * id : 12
             * isdel : 2
             * longitude :
             * area : 0
             * address : 湖南省长沙市天心区坡子街街道
             * hits : 0
             * addtime : 1444891206
             * district : 0
             * _model :
             * detail :
             * prompt : 骚年，我看你骨骼清奇，这里有宝藏一份，能否找到，看你缘分！
             */
            private String _system;
            private String city;
            private String latitude;
            private String privacy;
            private String solevar;
            private String userid;
            private String isempty;
            private String content;
            private String path;
            private String _deviceid;
            private String finds;
            private String province;
            private String geohash;
            private String searchs;
            private String company;
            private String id;
            private String isdel;
            private String longitude;
            private String area;
            private String address;
            private String hits;
            private String addtime;
            private String district;
            private String _model;
            private String detail;
            private String prompt;

            public void set_system(String _system) {
                this._system = _system;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public void setPrivacy(String privacy) {
                this.privacy = privacy;
            }

            public void setSolevar(String solevar) {
                this.solevar = solevar;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public void setIsempty(String isempty) {
                this.isempty = isempty;
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

            public void setFinds(String finds) {
                this.finds = finds;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public void setGeohash(String geohash) {
                this.geohash = geohash;
            }

            public void setSearchs(String searchs) {
                this.searchs = searchs;
            }

            public void setCompany(String company) {
                this.company = company;
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

            public void setArea(String area) {
                this.area = area;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setHits(String hits) {
                this.hits = hits;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public void set_model(String _model) {
                this._model = _model;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public void setPrompt(String prompt) {
                this.prompt = prompt;
            }

            public String get_system() {
                return _system;
            }

            public String getCity() {
                return city;
            }

            public String getLatitude() {
                return latitude;
            }

            public String getPrivacy() {
                return privacy;
            }

            public String getSolevar() {
                return solevar;
            }

            public String getUserid() {
                return userid;
            }

            public String getIsempty() {
                return isempty;
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

            public String getFinds() {
                return finds;
            }

            public String getProvince() {
                return province;
            }

            public String getGeohash() {
                return geohash;
            }

            public String getSearchs() {
                return searchs;
            }

            public String getCompany() {
                return company;
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

            public String getArea() {
                return area;
            }

            public String getAddress() {
                return address;
            }

            public String getHits() {
                return hits;
            }

            public String getAddtime() {
                return addtime;
            }

            public String getDistrict() {
                return district;
            }

            public String get_model() {
                return _model;
            }

            public String getDetail() {
                return detail;
            }

            public String getPrompt() {
                return prompt;
            }
        }
    }
}
