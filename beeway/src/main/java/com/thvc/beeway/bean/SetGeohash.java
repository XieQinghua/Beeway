package com.thvc.beeway.bean;

import java.io.Serializable;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/21.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class SetGeohash implements Serializable{

    /**
     * data : {"area":{"formatted_address":"","addressComponent":{"country":"","country_code":0,"distance":"","province":"","city":"","street":"","district":"","street_number":"","cityid":"1","provinceid":"1","direction":""}},"moditime":1443001044,"city":"","latitude":"112.953432","geohash":"uxupcpvzyxyp","saveflag":1,"longitude":"28.177129"}
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
         * area : {"formatted_address":"","addressComponent":{"country":"","country_code":0,"distance":"","province":"","city":"","street":"","district":"","street_number":"","cityid":"1","provinceid":"1","direction":""}}
         * moditime : 1443001044
         * city :
         * latitude : 112.953432
         * geohash : uxupcpvzyxyp
         * saveflag : 1
         * longitude : 28.177129
         */
        private AreaEntity area;
        private int moditime;
        private String city;
        private String latitude;
        private String geohash;
        private int saveflag;
        private String longitude;

        public void setArea(AreaEntity area) {
            this.area = area;
        }

        public void setModitime(int moditime) {
            this.moditime = moditime;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setGeohash(String geohash) {
            this.geohash = geohash;
        }

        public void setSaveflag(int saveflag) {
            this.saveflag = saveflag;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public AreaEntity getArea() {
            return area;
        }

        public int getModitime() {
            return moditime;
        }

        public String getCity() {
            return city;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getGeohash() {
            return geohash;
        }

        public int getSaveflag() {
            return saveflag;
        }

        public String getLongitude() {
            return longitude;
        }

        public static class AreaEntity {
            /**
             * formatted_address :
             * addressComponent : {"country":"","country_code":0,"distance":"","province":"","city":"","street":"","district":"","street_number":"","cityid":"1","provinceid":"1","direction":""}
             */
            private String formatted_address;
            private AddressComponentEntity addressComponent;

            public void setFormatted_address(String formatted_address) {
                this.formatted_address = formatted_address;
            }

            public void setAddressComponent(AddressComponentEntity addressComponent) {
                this.addressComponent = addressComponent;
            }

            public String getFormatted_address() {
                return formatted_address;
            }

            public AddressComponentEntity getAddressComponent() {
                return addressComponent;
            }

            public static class AddressComponentEntity {
                /**
                 * country :
                 * country_code : 0
                 * distance :
                 * province :
                 * city :
                 * street :
                 * district :
                 * street_number :
                 * cityid : 1
                 * provinceid : 1
                 * direction :
                 */
                private String country;
                private int country_code;
                private String distance;
                private String province;
                private String city;
                private String street;
                private String district;
                private String street_number;
                private String cityid;
                private String provinceid;
                private String direction;

                public void setCountry(String country) {
                    this.country = country;
                }

                public void setCountry_code(int country_code) {
                    this.country_code = country_code;
                }

                public void setDistance(String distance) {
                    this.distance = distance;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public void setStreet(String street) {
                    this.street = street;
                }

                public void setDistrict(String district) {
                    this.district = district;
                }

                public void setStreet_number(String street_number) {
                    this.street_number = street_number;
                }

                public void setCityid(String cityid) {
                    this.cityid = cityid;
                }

                public void setProvinceid(String provinceid) {
                    this.provinceid = provinceid;
                }

                public void setDirection(String direction) {
                    this.direction = direction;
                }

                public String getCountry() {
                    return country;
                }

                public int getCountry_code() {
                    return country_code;
                }

                public String getDistance() {
                    return distance;
                }

                public String getProvince() {
                    return province;
                }

                public String getCity() {
                    return city;
                }

                public String getStreet() {
                    return street;
                }

                public String getDistrict() {
                    return district;
                }

                public String getStreet_number() {
                    return street_number;
                }

                public String getCityid() {
                    return cityid;
                }

                public String getProvinceid() {
                    return provinceid;
                }

                public String getDirection() {
                    return direction;
                }
            }
        }
    }
}
