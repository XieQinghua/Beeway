package com.thvc.beeway.bean;

import java.io.Serializable;
import java.util.List;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/8.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class ScenicAreaBean implements Serializable {
    private int status;
    private String info;
    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public int getStatus() {
        return status;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataEntity {
        private AreaEntity  area;
        private List<ProvinceEntity> province;

        public AreaEntity getArea() {
            return area;
        }

        public List<ProvinceEntity> getProvince() {
            return province;
        }

        public void setArea(AreaEntity area) {
            this.area = area;
        }

        public void setProvince(List<ProvinceEntity> province) {
            this.province = province;
        }

        public static class AreaEntity {
            private String latitude;
            private String longitude;
            private String geohash;
            private String city;
            private String province;
            private String detail;
            private String address;

            public String getAddress() {
                return address;
            }

            public String getCity() {
                return city;
            }

            public String getGeohash() {
                return geohash;
            }

            public String getDetail() {
                return detail;
            }

            public String getLatitude() {
                return latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public String getProvince() {
                return province;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public void setGeohash(String geohash) {
                this.geohash = geohash;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public void setProvince(String province) {
                this.province = province;
            }
        }
        public static class ProvinceEntity {

            /**
             * pid : 0
             * id : 1
             * title : 湖南省
             * citys : [{"hover":1,"display":0,"pid":"1","id":"3","title":"长沙市"},{"display":0,"pid":"1","id":"13","title":"张家界市"},{"display":0,"pid":"1","id":"12","title":"岳阳市"},{"display":0,"pid":"1","id":"8","title":"湘潭市"},{"display":0,"pid":"1","id":"7","title":"株洲市"},{"display":0,"pid":"1","id":"5","title":"怀化市"},{"display":0,"pid":"1","id":"3436","title":"湘西州"}]
             */
            private String pid;
            private String id;
            private String title;
            private List<CitysEntity> citys;

            public void setPid(String pid) {
                this.pid = pid;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setCitys(List<CitysEntity> citys) {
                this.citys = citys;
            }

            public String getPid() {
                return pid;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public List<CitysEntity> getCitys() {
                return citys;
            }

            public static class CitysEntity {
                /**
                 * hover : 1
                 * display : 0
                 * pid : 1
                 * id : 3
                 * title : 长沙市
                 */
                private int hover;
                private int display;
                private String pid;
                private String id;
                private String title;

                public void setHover(int hover) {
                    this.hover = hover;
                }

                public void setDisplay(int display) {
                    this.display = display;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getHover() {
                    return hover;
                }

                public int getDisplay() {
                    return display;
                }

                public String getPid() {
                    return pid;
                }

                public String getId() {
                    return id;
                }

                public String getTitle() {
                    return title;
                }
            }
        }
    }

}
