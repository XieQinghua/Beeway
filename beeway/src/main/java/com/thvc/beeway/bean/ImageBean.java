package com.thvc.beeway.bean;

import java.util.List;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/16.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class ImageBean {

    /**
     * data : {"data":[{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282565","c_table":"Track","c_solevar":"0f6ea6a41bfdacd9","fileurl":"avatar/BDB93022-7712-40D6-936C-5B8B2ADEA1C6+1442311360.jpg","addid":"0","company":"0","id":"2396","sort":"0"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282566","c_table":"Track","c_solevar":"0f6ea6a41bfdacd9","fileurl":"avatar/254AB1E2-234A-4A16-871A-4E059C1704A0+1442311360.jpg","addid":"0","company":"0","id":"2397","sort":"1"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282567","c_table":"Track","c_solevar":"0f6ea6a41bfdacd9","fileurl":"avatar/90FD1AB5-510A-4E25-A28C-1AD09BEBFEF0+1442311360.jpg","addid":"0","company":"0","id":"2398","sort":"2"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282568","c_table":"Track","c_solevar":"0f6ea6a41bfdacd9","fileurl":"avatar/DA8D470E-6B4D-4DA6-968D-AEC8D20C87D7+1442311360.jpg","addid":"0","company":"0","id":"2399","sort":"3"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282569","c_table":"Track","c_solevar":"0f6ea6a41bfdacd9","fileurl":"avatar/AF324282-8863-46C5-8B0B-EDEFF28F11C0+1442311360.jpg","addid":"0","company":"0","id":"2400","sort":"4"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282570","c_table":"Track","c_solevar":"0f6ea6a41bfdacd9","fileurl":"avatar/FC2EF152-2168-42A5-9299-EDA6F8183E97+1442311360.jpg","addid":"0","company":"0","id":"2401","sort":"5"}],"index":0}
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
         * data : [{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282565","c_table":"Track","c_solevar":"0f6ea6a41bfdacd9","fileurl":"avatar/BDB93022-7712-40D6-936C-5B8B2ADEA1C6+1442311360.jpg","addid":"0","company":"0","id":"2396","sort":"0"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282566","c_table":"Track","c_solevar":"0f6ea6a41bfdacd9","fileurl":"avatar/254AB1E2-234A-4A16-871A-4E059C1704A0+1442311360.jpg","addid":"0","company":"0","id":"2397","sort":"1"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282567","c_table":"Track","c_solevar":"0f6ea6a41bfdacd9","fileurl":"avatar/90FD1AB5-510A-4E25-A28C-1AD09BEBFEF0+1442311360.jpg","addid":"0","company":"0","id":"2398","sort":"2"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282568","c_table":"Track","c_solevar":"0f6ea6a41bfdacd9","fileurl":"avatar/DA8D470E-6B4D-4DA6-968D-AEC8D20C87D7+1442311360.jpg","addid":"0","company":"0","id":"2399","sort":"3"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282569","c_table":"Track","c_solevar":"0f6ea6a41bfdacd9","fileurl":"avatar/AF324282-8863-46C5-8B0B-EDEFF28F11C0+1442311360.jpg","addid":"0","company":"0","id":"2400","sort":"4"},{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1442282570","c_table":"Track","c_solevar":"0f6ea6a41bfdacd9","fileurl":"avatar/FC2EF152-2168-42A5-9299-EDA6F8183E97+1442311360.jpg","addid":"0","company":"0","id":"2401","sort":"5"}]
         * index : 0
         */
        private List<Data> data;
        private int index;

        public void setData(List<Data> data) {
            this.data = data;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public List<Data> getData() {
            return data;
        }

        public int getIndex() {
            return index;
        }

        public static class Data {
            /**
             * filekeys :
             * c_title :
             * c_affect : atlas
             * addtime : 1442282565
             * c_table : Track
             * c_solevar : 0f6ea6a41bfdacd9
             * fileurl : avatar/BDB93022-7712-40D6-936C-5B8B2ADEA1C6+1442311360.jpg
             * addid : 0
             * company : 0
             * id : 2396
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
