package com.thvc.beeway.Release;

/**
 * Created by Administrator on 2015/9/19.
 */
public class FengYouAddtravelText {

    private String userid;
    private String footprintid;
    private String content;
    private String pathlist;
    private String status;
    private String currentTime;

    public FengYouAddtravelText(String userid, String footprintid, String content, String pathlist,
                                String status,String currentTime){
        super();
        this.userid = userid;this.footprintid = footprintid;
        this.content = content;this.pathlist= pathlist;this.status = status;
        this.currentTime=currentTime;
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

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }


}
