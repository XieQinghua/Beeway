package com.thvc.beeway.bean;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/16.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class RepleBean {

    /**
     * dataurl : APP/Track/commentAdd
     * data : {"commnick":"曾哥","isdata":0,"dataid":"58","commid":"2ca16ab7e0af4280","cid":"10"}
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
         * commnick : 曾哥
         * isdata : 0
         * dataid : 58
         * commid : 2ca16ab7e0af4280
         * cid : 10
         */
        private String commnick;
        private int isdata;
        private String dataid;
        private String commid;
        private String cid;

        public void setCommnick(String commnick) {
            this.commnick = commnick;
        }

        public void setIsdata(int isdata) {
            this.isdata = isdata;
        }

        public void setDataid(String dataid) {
            this.dataid = dataid;
        }

        public void setCommid(String commid) {
            this.commid = commid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCommnick() {
            return commnick;
        }

        public int getIsdata() {
            return isdata;
        }

        public String getDataid() {
            return dataid;
        }

        public String getCommid() {
            return commid;
        }

        public String getCid() {
            return cid;
        }
    }
}
