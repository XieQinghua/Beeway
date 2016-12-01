package com.thvc.beeway.bean;

/**
 * Created by Administrator on 2015/9/18.
 */
public class AddTravelJsonArrayBean {
    private String title;
    private String cost;
    private String feel;
    private String trackid;



    public AddTravelJsonArrayBean(String title, String cost, String feel,String trackid) {
        this.title = title;
        this.cost = cost;
        this.feel = feel;
    }

    public String getTrackid() {
        return trackid;
    }

    public void setTrackid(String trackid) {
        this.trackid = trackid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }
}
