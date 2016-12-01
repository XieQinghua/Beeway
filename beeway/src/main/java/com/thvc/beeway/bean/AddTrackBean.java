package com.thvc.beeway.bean;

/**
 * 项目名称：Beeway
 * 类描述：发布足迹返回对象bean
 * 创建人：谢庆华.
 * 创建时间：2015/9/1 16:53
 * 修改人：Administrator
 * 修改时间：2015/9/1 16:53
 * 修改备注：
 */
public class AddTrackBean {

    /**
     * data : {"address":"%s","flag":"","city":"","public_status":1,"latitude":"%s","solevar":"d5baa2a4804bb5a6","type":1,"userid":"%s","content":"%s","istype":1,"addtime":1441096599,"geohash":"","detail":"%s","prov":"","longitude":"%s"}
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
         * address : %s
         * flag :
         * city :
         * public_status : 1
         * latitude : %s
         * solevar : d5baa2a4804bb5a6
         * type : 1
         * userid : %s
         * content : %s
         * istype : 1
         * addtime : 1441096599
         * geohash :
         * detail : %s
         * prov :
         * longitude : %s
         */
        private String address;
        private String flag;
        private String city;
        private int public_status;
        private String latitude;
        private String solevar;
        private int type;
        private String userid;
        private String content;
        private int istype;
        private int addtime;
        private String geohash;
        private String detail;
        private String prov;
        private String longitude;


        public void setAddress(String address) {
            this.address = address;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setPublic_status(int public_status) {
            this.public_status = public_status;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setSolevar(String solevar) {
            this.solevar = solevar;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setIstype(int istype) {
            this.istype = istype;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public void setGeohash(String geohash) {
            this.geohash = geohash;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public void setProv(String prov) {
            this.prov = prov;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getAddress() {
            return address;
        }

        public String getFlag() {
            return flag;
        }

        public String getCity() {
            return city;
        }

        public int getPublic_status() {
            return public_status;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getSolevar() {
            return solevar;
        }

        public int getType() {
            return type;
        }

        public String getUserid() {
            return userid;
        }

        public String getContent() {
            return content;
        }

        public int getIstype() {
            return istype;
        }

        public int getAddtime() {
            return addtime;
        }

        public String getGeohash() {
            return geohash;
        }

        public String getDetail() {
            return detail;
        }

        public String getProv() {
            return prov;
        }

        public String getLongitude() {
            return longitude;
        }
    }
}
