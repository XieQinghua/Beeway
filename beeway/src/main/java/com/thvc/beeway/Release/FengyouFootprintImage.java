package com.thvc.beeway.Release;


/**
 * Created by Administrator on 2015/9/11.
 */
public class FengyouFootprintImage {
    private  String status;
    private  String userid;
    private  String footprintid;
    private  String content;
    private  String filePath;
    private  String currentTime;

    public FengyouFootprintImage (String userid,String footprintid,String filePath ,
                                  String content,String status, String  currentTime){
        this.status = status;this.userid=userid;this.footprintid=footprintid;
        this.content=content;this.filePath = filePath;this.currentTime=currentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String  getCurrentTime() {
        return currentTime;
    }
    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }






}
