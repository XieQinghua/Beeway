package com.thvc.beeway.bean;

/**
 * 项目名称：Beeway
 * 类描述：加入景点聊天室bean
 * 创建人：谢庆华.
 * 创建时间：2015/10/22 16:13
 * 修改人：Administrator
 * 修改时间：2015/10/22 16:13
 * 修改备注：
 */
public class JoinChatGroupBean {

    /**
     * data : {"isopen":"2","max":"3000","description":"欢迎大家进入橘子洲头聊天室①，此群为公开群哦！","solevar":"","sort":"1","title":"橘子洲头聊天室①","roomid":"","single":{"path":"http://www.hlfyb.com/Upload/","fileurl":"/System/Crowd_0.jpg"},"number":"1","dataid":"115540299157082604","addtime":"1445417033","adminid":"d192a4c4e49e1234","company":"1","id":"1","isdel":"2","isexist":2}
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
         * isopen : 2
         * max : 3000
         * description : 欢迎大家进入橘子洲头聊天室①，此群为公开群哦！
         * solevar :
         * sort : 1
         * title : 橘子洲头聊天室①
         * roomid :
         * single : {"path":"http://www.hlfyb.com/Upload/","fileurl":"/System/Crowd_0.jpg"}
         * number : 1
         * dataid : 115540299157082604
         * addtime : 1445417033
         * adminid : d192a4c4e49e1234
         * company : 1
         * id : 1
         * isdel : 2
         * isexist : 2
         */
        private String isopen;
        private String max;
        private String description;
        private String solevar;
        private String sort;
        private String title;
        private String roomid;
        private SingleEntity single;
        private String number;
        private String dataid;
        private String addtime;
        private String adminid;
        private String company;
        private String id;
        private String isdel;
        private int isexist;

        public void setIsopen(String isopen) {
            this.isopen = isopen;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setSolevar(String solevar) {
            this.solevar = solevar;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setRoomid(String roomid) {
            this.roomid = roomid;
        }

        public void setSingle(SingleEntity single) {
            this.single = single;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setDataid(String dataid) {
            this.dataid = dataid;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public void setAdminid(String adminid) {
            this.adminid = adminid;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setIsdel(String isdel) {
            this.isdel = isdel;
        }

        public void setIsexist(int isexist) {
            this.isexist = isexist;
        }

        public String getIsopen() {
            return isopen;
        }

        public String getMax() {
            return max;
        }

        public String getDescription() {
            return description;
        }

        public String getSolevar() {
            return solevar;
        }

        public String getSort() {
            return sort;
        }

        public String getTitle() {
            return title;
        }

        public String getRoomid() {
            return roomid;
        }

        public SingleEntity getSingle() {
            return single;
        }

        public String getNumber() {
            return number;
        }

        public String getDataid() {
            return dataid;
        }

        public String getAddtime() {
            return addtime;
        }

        public String getAdminid() {
            return adminid;
        }

        public String getCompany() {
            return company;
        }

        public String getId() {
            return id;
        }

        public String getIsdel() {
            return isdel;
        }

        public int getIsexist() {
            return isexist;
        }

        public static class SingleEntity {
            /**
             * path : http://www.hlfyb.com/Upload/
             * fileurl : /System/Crowd_0.jpg
             */
            private String path;
            private String fileurl;

            public void setPath(String path) {
                this.path = path;
            }

            public void setFileurl(String fileurl) {
                this.fileurl = fileurl;
            }

            public String getPath() {
                return path;
            }

            public String getFileurl() {
                return fileurl;
            }
        }
    }
}
