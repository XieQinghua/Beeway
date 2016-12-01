package com.thvc.beeway.bean;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/16.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class ShowShareBean {

    /**
     * data : {"imageurl":"http://7xj45w.com1.z0.glb.clouddn.com/avatar/BDB93022-7712-40D6-936C-5B8B2ADEA1C6+1442311360.jpg","description":"TT:冬季三环","defaultcontent":"冬季三环","title":"来自蜂友的分享","content":"冬季三环","url":"http://www.hlfyb.com/wechat.php/Share/track.html?id=58&&userid=a174cec97885719e"}
     * status : 1
     */
    private DataEntity data;
    private String status;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataEntity getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public static class DataEntity {
        /**
         * imageurl : http://7xj45w.com1.z0.glb.clouddn.com/avatar/BDB93022-7712-40D6-936C-5B8B2ADEA1C6+1442311360.jpg
         * description : TT:冬季三环
         * defaultcontent : 冬季三环
         * title : 来自蜂友的分享
         * content : 冬季三环
         * url : http://www.hlfyb.com/wechat.php/Share/track.html?id=58&&userid=a174cec97885719e
         */
        private String sharethumb;
        private String imageurl;
        private String description;
        private String defaultcontent;
        private String title;
        private String content;
        private String url;

        public String getSharethumb() {
            return sharethumb;
        }

        public void setSharethumb(String sharethumb) {
            this.sharethumb = sharethumb;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setDefaultcontent(String defaultcontent) {
            this.defaultcontent = defaultcontent;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImageurl() {
            return imageurl;
        }

        public String getDescription() {
            return description;
        }

        public String getDefaultcontent() {
            return defaultcontent;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getUrl() {
            return url;
        }
    }
}
