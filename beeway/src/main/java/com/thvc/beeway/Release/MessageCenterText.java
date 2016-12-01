package com.thvc.beeway.Release;

/**
 * Created by Administrator on 2015/10/21.
 */
public class MessageCenterText {
    private String userid;
    private String content;
    private String status;
    private String addtime;
    private String footprintid;


    public MessageCenterText( String userid,String footprintid, String content, String status, String addtime) {
        this.content = content;
        this.status = status;
        this.addtime = addtime;
        this.userid = userid;
        this.footprintid = footprintid;
    }

    public String getFootprintid() {
        return footprintid;
    }

    public void setFootprintid(String footprintid) {
        this.footprintid = footprintid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
