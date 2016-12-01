package com.thvc.beeway.bean;

/**
 * Created by Administrator on 2015/9/14.
 *
 * 创建人：颜松梁
 *
 * 封装数据对象
 */
public class JsonArrayBean {

    String public_status;
    String istype;

    String addtime;
    String tv_tracklabel;
    String latitude;
    String longitude;
    String company;
    String area;
    String detail;
    String content;
    String userid;
    String address;

    public JsonArrayBean(String public_status, String istype,
                         String addtime, String tv_tracklabel, String latitude,
                         String longitude, String company, String area, String detail,
                         String content, String userid, String address) {
        this.public_status = public_status;
        this.istype = istype;
        this.addtime = addtime;
        this.tv_tracklabel = tv_tracklabel;
        this.latitude = latitude;
        this.longitude = longitude;
        this.company = company;
        this.area = area;
        this.detail = detail;
        this.content = content;
        this.userid = userid;
        this.address = address;
    }


    public String getPublic_status() {
        return public_status;
    }

    public void setPublic_status(String public_status) {
        this.public_status = public_status;
    }

    public String getIstype() {
        return istype;
    }

    public void setIstype(String istype) {
        this.istype = istype;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getTv_tracklabel() {
        return tv_tracklabel;
    }

    public void setTv_tracklabel(String tv_tracklabel) {
        this.tv_tracklabel = tv_tracklabel;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
