package com.thvc.beeway.bean;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/18.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class SubsidizeYouBean {

    /**
     * data : {"noteid":"3"}
     * status : 1
     */
    private DataEntity data;
    private String status;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataEntity getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public static class DataEntity {
        /**
         * noteid : 3
         */
        private String noteid;

        public void setNoteid(String noteid) {
            this.noteid = noteid;
        }

        public String getNoteid() {
            return noteid;
        }
    }
}
