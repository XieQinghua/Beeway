package com.thvc.beeway.bean;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/16.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class PersonalDataBean {

    /**
     * data : {"solevar":"a174cec97885719e"}
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
         * solevar : a174cec97885719e
         */
        private String solevar;

        public void setSolevar(String solevar) {
            this.solevar = solevar;
        }

        public String getSolevar() {
            return solevar;
        }
    }
}
