package com.thvc.beeway.bean;

import java.io.Serializable;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/23.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class IsCollectBean implements Serializable{

    /**
     * dataurl : APP/User/collectAdds
     * data : {"dataid":"71","tableid":"TrackNote","userid":"2ca16ab7e0af4280"}
     * status : 1
     */
    private String dataurl;
    private DataEntity data;
    private String status;
    private String url;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDataurl(String dataurl) {
        this.dataurl = dataurl;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataurl() {
        return dataurl;
    }

    public DataEntity getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public static class DataEntity {
        /**
         * dataid : 71
         * tableid : TrackNote
         * userid : 2ca16ab7e0af4280
         */
        private String dataid;
        private String tableid;
        private String userid;

        public void setDataid(String dataid) {
            this.dataid = dataid;
        }

        public void setTableid(String tableid) {
            this.tableid = tableid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getDataid() {
            return dataid;
        }

        public String getTableid() {
            return tableid;
        }

        public String getUserid() {
            return userid;
        }
    }
}
