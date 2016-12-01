package com.thvc.beeway.bean;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/16.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class CommentBean {
    /**
     * dataurl : APP/Track/commentAdd
     * data : {"dataid":"58","isdata":1}
     * status : 1
     */
    private String dataurl;
    private DataEntity data;
    private String status;

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
         * dataid : 58
         * isdata : 1
         */
        private String dataid;
        private int isdata;

        public void setDataid(String dataid) {
            this.dataid = dataid;
        }

        public void setIsdata(int isdata) {
            this.isdata = isdata;
        }

        public String getDataid() {
            return dataid;
        }

        public int getIsdata() {
            return isdata;
        }
    }
}
