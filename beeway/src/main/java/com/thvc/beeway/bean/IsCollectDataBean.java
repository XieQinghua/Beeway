package com.thvc.beeway.bean;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/23.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class IsCollectDataBean {

    /**
     * data : {"msg":"已收藏","data":1}
     * status : 1
     * info : 添加收藏成功
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
         * msg : 已收藏
         * data : 1
         */
        private String msg;
        private int data;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public void setData(int data) {
            this.data = data;
        }

        public String getMsg() {
            return msg;
        }

        public int getData() {
            return data;
        }
    }
}
