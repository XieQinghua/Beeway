package com.thvc.beeway.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/27.
 */
public class AddTraveLlistJsonBean {


    /**
     * Cost :
     * Feel :
     * List : [{"Address":"湖南长沙","Content":"123456","ResumeId":"199","Time":"1445858101","Url":"http://7xj45w.com1.z0.glb.clouddn.com/track/14458581002601442050741340.jpg-Thumb640"}]
     * SourcePaths :
     * Titles :
     */

    private String Cost;
    private String Feel;
    private String SourcePaths;
    private String Titles;
    /**
     * Address : 湖南长沙
     * Content : 123456
     * ResumeId : 199
     * Time : 1445858101
     * Url : http://7xj45w.com1.z0.glb.clouddn.com/track/14458581002601442050741340.jpg-Thumb640
     */

    private java.util.List<ListEntity> List;

    public static AddTraveLlistJsonBean objectFromData(String str) {

        return new Gson().fromJson(str, AddTraveLlistJsonBean.class);
    }

    public static AddTraveLlistJsonBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), AddTraveLlistJsonBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static java.util.List<AddTraveLlistJsonBean> arrayAddTraveLlistJsonBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<AddTraveLlistJsonBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static java.util.List<AddTraveLlistJsonBean> arrayAddTraveLlistJsonBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<AddTraveLlistJsonBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public void setCost(String Cost) {
        this.Cost = Cost;
    }

    public void setFeel(String Feel) {
        this.Feel = Feel;
    }

    public void setSourcePaths(String SourcePaths) {
        this.SourcePaths = SourcePaths;
    }

    public void setTitles(String Titles) {
        this.Titles = Titles;
    }

    public void setList(java.util.List<ListEntity> List) {
        this.List = List;
    }

    public String getCost() {
        return Cost;
    }

    public String getFeel() {
        return Feel;
    }

    public String getSourcePaths() {
        return SourcePaths;
    }

    public String getTitles() {
        return Titles;
    }

    public java.util.List<ListEntity> getList() {
        return List;
    }

    public static class ListEntity {
        private String Address;
        private String Content;
        private String ResumeId;
        private String Time;
        private String Url;

        public static ListEntity objectFromData(String str) {

            return new Gson().fromJson(str, ListEntity.class);
        }

        public static ListEntity objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), ListEntity.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static java.util.List<ListEntity> arrayListEntityFromData(String str) {

            Type listType = new TypeToken<ArrayList<ListEntity>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static java.util.List<ListEntity> arrayListEntityFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<ListEntity>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public void setResumeId(String ResumeId) {
            this.ResumeId = ResumeId;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public String getAddress() {
            return Address;
        }

        public String getContent() {
            return Content;
        }

        public String getResumeId() {
            return ResumeId;
        }

        public String getTime() {
            return Time;
        }

        public String getUrl() {
            return Url;
        }
    }
}
