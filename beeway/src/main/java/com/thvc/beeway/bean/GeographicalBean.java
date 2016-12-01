package com.thvc.beeway.bean;

/**
 * Created by Administrator on 2015/9/24.
 */
public class GeographicalBean {
    //{"status":"1","map":"1","data":{"latitude":"28.305912","longitude":"112.943052"}}
     private   String status;
     private    String map;
    private  DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }


    public static class DataEntity {
        private String latitude;
        private String longitude;
        public String getLatitude() {
            return latitude;
        }
        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }

}
