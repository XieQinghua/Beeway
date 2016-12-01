package com.thvc.beeway.bean;

import java.util.List;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/18.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class ScenicToTrackDetailBean {

    /**
     * data : {"address":"橘子洲头","atlas":{"data":[{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282425","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/9F26283F-1A89-4AC0-B138-CB3663396B66+1442311211.jpg","addid":"0","company":"1","id":"2390","sort":"0"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282426","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/12C6B728-A026-4C1B-A547-EF81D1DBEAF0+1442311211.jpg","addid":"0","company":"1","id":"2391","sort":"1"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282427","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/388F2345-7391-4B44-BF52-EC541EED1961+1442311211.jpg","addid":"0","company":"1","id":"2392","sort":"2"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282428","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/237E486F-AC37-4393-BC87-87D526DC9B5A+1442311211.jpg","addid":"0","company":"1","id":"2393","sort":"3"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282429","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/91170A40-4C8A-4D00-8702-59C90558127F+1442311211.jpg","addid":"0","company":"1","id":"2394","sort":"4"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282430","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/68CA970D-FB3E-43A7-BCC0-3EF05BB75F60+1442311211.jpg","addid":"0","company":"1","id":"2395","sort":"5"}]},"public_status":"2","solevar":"ea124727d7136d76","iscom":"2","sort":"1","collection":0,"title":"感慨","userid":"a174cec97885719e","good":"2","headpic":"Photo/Headpic/member_pic.jpg","content":"HK","realname":"TT","istype":"5","stitle":"橘子洲头","addtime":"1442282425","isgood":-2,"nickname":"TT","company":"1","comment":"0","share":"0","id":"57","device":"","collect":"0"}
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
         * address : 橘子洲头
         * atlas : {"data":[{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282425","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/9F26283F-1A89-4AC0-B138-CB3663396B66+1442311211.jpg","addid":"0","company":"1","id":"2390","sort":"0"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282426","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/12C6B728-A026-4C1B-A547-EF81D1DBEAF0+1442311211.jpg","addid":"0","company":"1","id":"2391","sort":"1"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282427","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/388F2345-7391-4B44-BF52-EC541EED1961+1442311211.jpg","addid":"0","company":"1","id":"2392","sort":"2"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282428","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/237E486F-AC37-4393-BC87-87D526DC9B5A+1442311211.jpg","addid":"0","company":"1","id":"2393","sort":"3"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282429","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/91170A40-4C8A-4D00-8702-59C90558127F+1442311211.jpg","addid":"0","company":"1","id":"2394","sort":"4"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282430","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/68CA970D-FB3E-43A7-BCC0-3EF05BB75F60+1442311211.jpg","addid":"0","company":"1","id":"2395","sort":"5"}]}
         * public_status : 2
         * solevar : ea124727d7136d76
         * iscom : 2
         * sort : 1
         * collection : 0
         * title : 感慨
         * userid : a174cec97885719e
         * good : 2
         * headpic : Photo/Headpic/member_pic.jpg
         * content : HK
         * realname : TT
         * istype : 5
         * stitle : 橘子洲头
         * addtime : 1442282425
         * isgood : -2
         * nickname : TT
         * company : 1
         * comment : 0
         * share : 0
         * id : 57
         * device :
         * collect : 0
         */
        private String address;
        private AtlasEntity atlas;
        private String public_status;
        private String solevar;
        private String iscom;
        private String sort;
        private int collection;
        private String title;
        private String userid;
        private String good;
        private String headpic;
        private String content;
        private String realname;
        private String istype;
        private String stitle;
        private String addtime;
        private int isgood;
        private String nickname;
        private String company;
        private String comment;
        private String share;
        private String id;
        private String device;
        private String collect;

        public void setAddress(String address) {
            this.address = address;
        }

        public void setAtlas(AtlasEntity atlas) {
            this.atlas = atlas;
        }

        public void setPublic_status(String public_status) {
            this.public_status = public_status;
        }

        public void setSolevar(String solevar) {
            this.solevar = solevar;
        }

        public void setIscom(String iscom) {
            this.iscom = iscom;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setGood(String good) {
            this.good = good;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public void setIstype(String istype) {
            this.istype = istype;
        }

        public void setStitle(String stitle) {
            this.stitle = stitle;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public void setIsgood(int isgood) {
            this.isgood = isgood;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public void setCollect(String collect) {
            this.collect = collect;
        }

        public String getAddress() {
            return address;
        }

        public AtlasEntity getAtlas() {
            return atlas;
        }

        public String getPublic_status() {
            return public_status;
        }

        public String getSolevar() {
            return solevar;
        }

        public String getIscom() {
            return iscom;
        }

        public String getSort() {
            return sort;
        }

        public int getCollection() {
            return collection;
        }

        public String getTitle() {
            return title;
        }

        public String getUserid() {
            return userid;
        }

        public String getGood() {
            return good;
        }

        public String getHeadpic() {
            return headpic;
        }

        public String getContent() {
            return content;
        }

        public String getRealname() {
            return realname;
        }

        public String getIstype() {
            return istype;
        }

        public String getStitle() {
            return stitle;
        }

        public String getAddtime() {
            return addtime;
        }

        public int getIsgood() {
            return isgood;
        }

        public String getNickname() {
            return nickname;
        }

        public String getCompany() {
            return company;
        }

        public String getComment() {
            return comment;
        }

        public String getShare() {
            return share;
        }

        public String getId() {
            return id;
        }

        public String getDevice() {
            return device;
        }

        public String getCollect() {
            return collect;
        }

        public static class AtlasEntity {
            /**
             * data : [{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282425","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/9F26283F-1A89-4AC0-B138-CB3663396B66+1442311211.jpg","addid":"0","company":"1","id":"2390","sort":"0"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282426","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/12C6B728-A026-4C1B-A547-EF81D1DBEAF0+1442311211.jpg","addid":"0","company":"1","id":"2391","sort":"1"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282427","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/388F2345-7391-4B44-BF52-EC541EED1961+1442311211.jpg","addid":"0","company":"1","id":"2392","sort":"2"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282428","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/237E486F-AC37-4393-BC87-87D526DC9B5A+1442311211.jpg","addid":"0","company":"1","id":"2393","sort":"3"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282429","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/91170A40-4C8A-4D00-8702-59C90558127F+1442311211.jpg","addid":"0","company":"1","id":"2394","sort":"4"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282430","c_table":"Track","c_solevar":"ea124727d7136d76","fileurl":"avatar/68CA970D-FB3E-43A7-BCC0-3EF05BB75F60+1442311211.jpg","addid":"0","company":"1","id":"2395","sort":"5"}]
             */
            private List<Data> data;

            public void setData(List<Data> data) {
                this.data = data;
            }

            public List<Data> getData() {
                return data;
            }

            public static class Data {
                /**
                 * filekeys :
                 * c_title :
                 * c_affect : atlas
                 * addtime : 1442282425
                 * c_table : Track
                 * c_solevar : ea124727d7136d76
                 * fileurl : avatar/9F26283F-1A89-4AC0-B138-CB3663396B66+1442311211.jpg
                 * addid : 0
                 * company : 1
                 * id : 2390
                 * sort : 0
                 */
                private String filekeys;
                private String c_title;
                private String c_affect;
                private String addtime;
                private String c_table;
                private String c_solevar;
                private String fileurl;
                private String addid;
                private String company;
                private String id;
                private String sort;

                public void setFilekeys(String filekeys) {
                    this.filekeys = filekeys;
                }

                public void setC_title(String c_title) {
                    this.c_title = c_title;
                }

                public void setC_affect(String c_affect) {
                    this.c_affect = c_affect;
                }

                public void setAddtime(String addtime) {
                    this.addtime = addtime;
                }

                public void setC_table(String c_table) {
                    this.c_table = c_table;
                }

                public void setC_solevar(String c_solevar) {
                    this.c_solevar = c_solevar;
                }

                public void setFileurl(String fileurl) {
                    this.fileurl = fileurl;
                }

                public void setAddid(String addid) {
                    this.addid = addid;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getFilekeys() {
                    return filekeys;
                }

                public String getC_title() {
                    return c_title;
                }

                public String getC_affect() {
                    return c_affect;
                }

                public String getAddtime() {
                    return addtime;
                }

                public String getC_table() {
                    return c_table;
                }

                public String getC_solevar() {
                    return c_solevar;
                }

                public String getFileurl() {
                    return fileurl;
                }

                public String getAddid() {
                    return addid;
                }

                public String getCompany() {
                    return company;
                }

                public String getId() {
                    return id;
                }

                public String getSort() {
                    return sort;
                }
            }
        }
    }
}
