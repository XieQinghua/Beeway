package com.thvc.beeway.bean;

/**
 * Created by Administrator on 2015/10/21.
 * 解析消息推送
 */
public class MessageCenterBean {
    /**
     * id : 12
     * type : TrackNote
     * fromuserid : baa5f505482a0716
     * content : contetn
     */
    private String id;//足迹游记 景点 众筹ID
    private String content;//推送内容
    private String type;//推送类型
    private String title;//标题
    private String fromuserid;//用户id
    private String fileurl;//图片地址
    private String image;// 里面包含 fileurl图片地址
    private String url;//网络图片地址
    private String appurlandroid;//Android市场地址
    private String httpurl;//网页地址
    private String vtitle;//网页标题

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppurlandroid() {
        return appurlandroid;
    }

    public void setAppurlandroid(String appurlandroid) {
        this.appurlandroid = appurlandroid;
    }

    public String getHttpurl() {
        return httpurl;
    }

    public void setHttpurl(String httpurl) {
        this.httpurl = httpurl;
    }

    public String getVtitle() {
        return vtitle;
    }

    public void setVtitle(String vtitle) {
        this.vtitle = vtitle;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFromuserid(String fromuserid) {
        this.fromuserid = fromuserid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getFromuserid() {
        return fromuserid;
    }

    public String getContent() {
        return content;
    }
}
