package com.thvc.beeway.Release;

/**
 * Created by Administrator on 2015/10/27.
 */
public class AddTravelText {
    private String userid;
    private String footprintid;
    private String content;
    private String listjson;

    public AddTravelText(String userid, String footprintid, String content, String listjson) {
        this.userid = userid;
        this.footprintid = footprintid;
        this.content = content;
        this.listjson = listjson;
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

    public String getListjson() {
        return listjson;
    }

    public void setListjson(String listjson) {
        this.listjson = listjson;
    }
}
