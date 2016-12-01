package com.thvc.beeway.bean;

import java.util.List;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/8/20.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class TrackImageBean {

    /**
     * status : 1
     * data : [{"site":null,"solevar":"7aac8408d27167ad","cateid":"2","single":{"id":"177","addtime":"1434619239","c_table":"Admarket","sort":"0","filekeys":"","c_solevar":"7aac8408d27167ad","company":"0","fileurl":"Commonjoyyxotc/5582826627821.jpg","c_affect":"single","path":"http://api.yidake.com/Upload/","c_title":"banner06.jpg","addid":"0"},"keywords":"首页_banner图片01","sort":"999","columnid":"0","lang":"","url":"#","id":"1","addtime":"1434619239","title":"首页_banner图片01","description":"首页_banner图片01","isdel":"2","key":null,"addid":"1"},{"site":null,"solevar":"207d115a8280f9d0","cateid":"2","single":{"id":"178","addtime":"1434619261","c_table":"Admarket","sort":"0","filekeys":"","c_solevar":"207d115a8280f9d0","company":"0","fileurl":"Commonjoyyxotc/558282677b92a.jpg","c_affect":"single","path":"http://api.yidake.com/Upload/","c_title":"banner07.jpg","addid":"0"},"keywords":"首页_banner图片02","sort":"997","columnid":"0","lang":"","url":"#","id":"2","addtime":"1434619261","title":"首页_banner图片02","description":"首页_banner图片02","isdel":"2","key":null,"addid":"1"}]
     * info :
     */
    private int status;
    private List<DataEntity> data;
    private String info;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public static class DataEntity {
        /**
         * site : null
         * solevar : 7aac8408d27167ad
         * cateid : 2
         * single : {"id":"177","addtime":"1434619239","c_table":"Admarket","sort":"0","filekeys":"","c_solevar":"7aac8408d27167ad","company":"0","fileurl":"Commonjoyyxotc/5582826627821.jpg","c_affect":"single","path":"http://api.yidake.com/Upload/","c_title":"banner06.jpg","addid":"0"}
         * keywords : 首页_banner图片01
         * sort : 999
         * columnid : 0
         * lang :
         * url : #
         * id : 1
         * addtime : 1434619239
         * title : 首页_banner图片01
         * description : 首页_banner图片01
         * isdel : 2
         * key : null
         * addid : 1
         */
        private String site;
        private String solevar;
        private String cateid;
        private SingleEntity single;
        private String keywords;
        private String sort;
        private String columnid;
        private String lang;
        private String url;
        private String id;
        private String addtime;
        private String title;
        private String description;
        private String isdel;
        private String key;
        private String addid;

        public void setSite(String site) {
            this.site = site;
        }

        public void setSolevar(String solevar) {
            this.solevar = solevar;
        }

        public void setCateid(String cateid) {
            this.cateid = cateid;
        }

        public void setSingle(SingleEntity single) {
            this.single = single;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public void setColumnid(String columnid) {
            this.columnid = columnid;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setIsdel(String isdel) {
            this.isdel = isdel;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setAddid(String addid) {
            this.addid = addid;
        }

        public String getSite() {
            return site;
        }

        public String getSolevar() {
            return solevar;
        }

        public String getCateid() {
            return cateid;
        }

        public SingleEntity getSingle() {
            return single;
        }

        public String getKeywords() {
            return keywords;
        }

        public String getSort() {
            return sort;
        }

        public String getColumnid() {
            return columnid;
        }

        public String getLang() {
            return lang;
        }

        public String getUrl() {
            return url;
        }

        public String getId() {
            return id;
        }

        public String getAddtime() {
            return addtime;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getIsdel() {
            return isdel;
        }

        public String getKey() {
            return key;
        }

        public String getAddid() {
            return addid;
        }

        public static class SingleEntity {
            /**
             * id : 177
             * addtime : 1434619239
             * c_table : Admarket
             * sort : 0
             * filekeys :
             * c_solevar : 7aac8408d27167ad
             * company : 0
             * fileurl : Commonjoyyxotc/5582826627821.jpg
             * c_affect : single
             * path : http://api.yidake.com/Upload/
             * c_title : banner06.jpg
             * addid : 0
             */
            private String id;
            private String addtime;
            private String c_table;
            private String sort;
            private String filekeys;
            private String c_solevar;
            private String company;
            private String fileurl;
            private String c_affect;
            private String path;
            private String c_title;
            private String addid;

            public void setId(String id) {
                this.id = id;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public void setC_table(String c_table) {
                this.c_table = c_table;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public void setFilekeys(String filekeys) {
                this.filekeys = filekeys;
            }

            public void setC_solevar(String c_solevar) {
                this.c_solevar = c_solevar;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public void setFileurl(String fileurl) {
                this.fileurl = fileurl;
            }

            public void setC_affect(String c_affect) {
                this.c_affect = c_affect;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public void setC_title(String c_title) {
                this.c_title = c_title;
            }

            public void setAddid(String addid) {
                this.addid = addid;
            }

            public String getId() {
                return id;
            }

            public String getAddtime() {
                return addtime;
            }

            public String getC_table() {
                return c_table;
            }

            public String getSort() {
                return sort;
            }

            public String getFilekeys() {
                return filekeys;
            }

            public String getC_solevar() {
                return c_solevar;
            }

            public String getCompany() {
                return company;
            }

            public String getFileurl() {
                return fileurl;
            }

            public String getC_affect() {
                return c_affect;
            }

            public String getPath() {
                return path;
            }

            public String getC_title() {
                return c_title;
            }

            public String getAddid() {
                return addid;
            }
        }
    }
}
