package com.thvc.beeway.bean;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/19.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class RepleSendBean {

    /**
     * data : 评论成功
     * status : 1
     * info :
     */
    private String data;
    private int status;
    private String info;

    public void setData(String data) {
        this.data = data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }
}
