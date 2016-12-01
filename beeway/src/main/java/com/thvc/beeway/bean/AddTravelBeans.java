package com.thvc.beeway.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/10/27.
 */
public class AddTravelBeans {
    private String sourcePaths;
    private String titles;
    private String cost;
    private String feel;
    private List<AddFootprintbean> list;

    public AddTravelBeans(String sourcePaths, String titles, String cost, String feel, List<AddFootprintbean> list) {
        this.sourcePaths = sourcePaths;
        this.titles = titles;
        this.cost = cost;
        this.feel = feel;
        this.list = list;
    }

    public List<AddFootprintbean> getList() {
        return list;
    }

    public void setList(List<AddFootprintbean> list) {
        this.list = list;
    }

    public String getSourcePaths() {
        return sourcePaths;
    }

    public void setSourcePaths(String sourcePaths) {
        this.sourcePaths = sourcePaths;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
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
