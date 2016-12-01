package com.thvc.beeway.bean;

/**
 * Created by Administrator on 2015/9/19.
 */
public class IssueCutomerBean {
    private String title;
    private String issedit;
    private String issreason;
    private String status;
    private String data;
    private String info;

    public String getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public IssueCutomerBean(String title, String issedit, String issreason) {
        this.title = title;
        this.issedit = issedit;
        this.issreason = issreason;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssedit() {
        return issedit;
    }

    public void setIssedit(String issedit) {
        this.issedit = issedit;
    }

    public String getIssreason() {
        return issreason;
    }

    public void setIssreason(String issreason) {
        this.issreason = issreason;
    }
}
