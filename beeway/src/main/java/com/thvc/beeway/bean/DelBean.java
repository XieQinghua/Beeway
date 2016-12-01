package com.thvc.beeway.bean;

/**
 * 项目名称：Beeway
 * 类描述：删除操作返回json bean文件
 * 创建人：谢庆华.
 * 创建时间：2015/9/23 19:53
 * 修改人：Administrator
 * 修改时间：2015/9/23 19:53
 * 修改备注：
 */
public class DelBean {

    /**
     * data : 147
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
