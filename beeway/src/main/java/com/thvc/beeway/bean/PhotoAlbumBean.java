package com.thvc.beeway.bean;

import java.io.Serializable;
import java.util.List;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/19.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class PhotoAlbumBean implements Serializable{

    /**
     * data : {"image":{"atlas":{"data":[{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441802945","c_table":"Track","c_solevar":"e4bac6657a97c06a","fileurl":"avatar/F636A5B7-5F57-4358-9183-D981C45C5D1E+1441831740.jpg","addid":"0","company":"1","id":"2344","sort":"0"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441802946","c_table":"Track","c_solevar":"e4bac6657a97c06a","fileurl":"avatar/8B47C593-7D18-40E3-B830-29BDE018416C+1441831740.jpg","addid":"0","company":"1","id":"2345","sort":"1"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441802947","c_table":"Track","c_solevar":"e4bac6657a97c06a","fileurl":"avatar/CEA68EB1-CB76-42EB-BBE3-523C4FA7AF91+1441831740.jpg","addid":"0","company":"1","id":"2346","sort":"2"}]}},"address":"橘子洲头","public_status":"2","solevar":"e4bac6657a97c06a","iscom":"2","sort":"1","userid":"cbe94293bb6fe8e7","good":"1","content":"八月的油菜花，盛开在高原上，蓝天下白云悠悠，真想躺在青草中，睡个懒觉","istype":"2","addtime":"1441802945","travelid":"2","company":"1","comment":"1","share":"0","id":"39","device":"","collect":"1"}
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
         * image : {"atlas":{"data":[{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441802945","c_table":"Track","c_solevar":"e4bac6657a97c06a","fileurl":"avatar/F636A5B7-5F57-4358-9183-D981C45C5D1E+1441831740.jpg","addid":"0","company":"1","id":"2344","sort":"0"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441802946","c_table":"Track","c_solevar":"e4bac6657a97c06a","fileurl":"avatar/8B47C593-7D18-40E3-B830-29BDE018416C+1441831740.jpg","addid":"0","company":"1","id":"2345","sort":"1"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441802947","c_table":"Track","c_solevar":"e4bac6657a97c06a","fileurl":"avatar/CEA68EB1-CB76-42EB-BBE3-523C4FA7AF91+1441831740.jpg","addid":"0","company":"1","id":"2346","sort":"2"}]}}
         * address : 橘子洲头
         * public_status : 2
         * solevar : e4bac6657a97c06a
         * iscom : 2
         * sort : 1
         * userid : cbe94293bb6fe8e7
         * good : 1
         * content : 八月的油菜花，盛开在高原上，蓝天下白云悠悠，真想躺在青草中，睡个懒觉
         * istype : 2
         * addtime : 1441802945
         * travelid : 2
         * company : 1
         * comment : 1
         * share : 0
         * id : 39
         * device :
         * collect : 1
         */
        private ImageEntity image;
        private String address;
        private String public_status;
        private String solevar;
        private String iscom;
        private String sort;
        private String userid;
        private String good;
        private String content;
        private String istype;
        private String addtime;
        private String travelid;
        private String company;
        private String comment;
        private String share;
        private String id;
        private String device;
        private String collect;

        public void setImage(ImageEntity image) {
            this.image = image;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setGood(String good) {
            this.good = good;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setIstype(String istype) {
            this.istype = istype;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public void setTravelid(String travelid) {
            this.travelid = travelid;
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

        public ImageEntity getImage() {
            return image;
        }

        public String getAddress() {
            return address;
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

        public String getUserid() {
            return userid;
        }

        public String getGood() {
            return good;
        }

        public String getContent() {
            return content;
        }

        public String getIstype() {
            return istype;
        }

        public String getAddtime() {
            return addtime;
        }

        public String getTravelid() {
            return travelid;
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

        public static class ImageEntity {
            /**
             * atlas : {"data":[{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441802945","c_table":"Track","c_solevar":"e4bac6657a97c06a","fileurl":"avatar/F636A5B7-5F57-4358-9183-D981C45C5D1E+1441831740.jpg","addid":"0","company":"1","id":"2344","sort":"0"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441802946","c_table":"Track","c_solevar":"e4bac6657a97c06a","fileurl":"avatar/8B47C593-7D18-40E3-B830-29BDE018416C+1441831740.jpg","addid":"0","company":"1","id":"2345","sort":"1"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441802947","c_table":"Track","c_solevar":"e4bac6657a97c06a","fileurl":"avatar/CEA68EB1-CB76-42EB-BBE3-523C4FA7AF91+1441831740.jpg","addid":"0","company":"1","id":"2346","sort":"2"}]}
             */
            private AtlasEntity atlas;

            public void setAtlas(AtlasEntity atlas) {
                this.atlas = atlas;
            }

            public AtlasEntity getAtlas() {
                return atlas;
            }

            public static class AtlasEntity {
                /**
                 * data : [{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441802945","c_table":"Track","c_solevar":"e4bac6657a97c06a","fileurl":"avatar/F636A5B7-5F57-4358-9183-D981C45C5D1E+1441831740.jpg","addid":"0","company":"1","id":"2344","sort":"0"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441802946","c_table":"Track","c_solevar":"e4bac6657a97c06a","fileurl":"avatar/8B47C593-7D18-40E3-B830-29BDE018416C+1441831740.jpg","addid":"0","company":"1","id":"2345","sort":"1"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1441802947","c_table":"Track","c_solevar":"e4bac6657a97c06a","fileurl":"avatar/CEA68EB1-CB76-42EB-BBE3-523C4FA7AF91+1441831740.jpg","addid":"0","company":"1","id":"2346","sort":"2"}]
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
                     * addtime : 1441802945
                     * c_table : Track
                     * c_solevar : e4bac6657a97c06a
                     * fileurl : avatar/F636A5B7-5F57-4358-9183-D981C45C5D1E+1441831740.jpg
                     * addid : 0
                     * company : 1
                     * id : 2344
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
}
