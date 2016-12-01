package com.thvc.beeway.Release;

import java.util.Date;

/**
 * Created by Administrator on 2015/9/10.
 *
 *
 */
public class FengyouFootprintText {
    private String userid;
    private String footprintid;
    private String content;
    private String pathlist;
    private String status;
    private String type;
    private String currentTime;

    public FengyouFootprintText(String userid, String footprintid, String content, String pathlist,
                                String status, String type,String currentTime){
        super();
        this.userid = userid;this.footprintid = footprintid;
        this.content = content;this.pathlist= pathlist;this.status = status;
        this.type = type;this.currentTime=currentTime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFootprintid() {
        return footprintid;
    }

    public void setFootprintid(String footprintid) {
        this.footprintid = footprintid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPathlist() {
        return pathlist;
    }

    public void setPathlist(String pathlist) {
        this.pathlist = pathlist;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String  getAddtime() {
        return currentTime;
    }

    public void setAddtime(String currentTime) {
        this.currentTime = currentTime;
    }
}
