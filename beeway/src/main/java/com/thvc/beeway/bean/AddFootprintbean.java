package com.thvc.beeway.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/21.
 */
public class AddFootprintbean implements Serializable {
    private String resumeId;
    private String time;
    private String address;
    private String content;
    private String url;

    public AddFootprintbean(String resumeId, String time, String address, String content, String url) {
        this.resumeId = resumeId;
        this.time = time;
        this.address = address;
        this.content = content;
        this.url = url;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
