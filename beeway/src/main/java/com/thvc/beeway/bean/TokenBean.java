package com.thvc.beeway.bean;

/**
 * 项目名称：Beeway
 * 类描述：七牛上传图片的服务器返回token
 * 创建人：谢庆华.
 * 创建时间：2015/8/28 11:06
 * 修改人：Administrator
 * 修改时间：2015/8/28 11:06
 * 修改备注：
 */
public class TokenBean {

    /**
     * data : {"upToken":"rCggkHTdmf26FEoXGUlXqdcmdsoxyIeZMwtVkq49:dVfXzwkaOGpE63G75oLBurEOr7s=:eyJzY29wZSI6ImZlbmd5b3U6MTIzNDU2IiwiZGVhZGxpbmUiOjE0NDA3MzQ3NzZ9"}
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
         * upToken : rCggkHTdmf26FEoXGUlXqdcmdsoxyIeZMwtVkq49:dVfXzwkaOGpE63G75oLBurEOr7s=:eyJzY29wZSI6ImZlbmd5b3U6MTIzNDU2IiwiZGVhZGxpbmUiOjE0NDA3MzQ3NzZ9
         */
        private String upToken;

        public void setUpToken(String upToken) {
            this.upToken = upToken;
        }

        public String getUpToken() {
            return upToken;
        }
    }
}

