package com.thvc.beeway.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/9/30.
 */
public class ExChangeRecordBean {

    /**
     * status : 1
     * data : {"count":"2","nowPage":1,"totalPage":1,"list":[{"id":"25","solevar":"e0e1177ecc678f44","title":"黄山香溪漂流 随时预定随到随进 黄山天湖漂流门票","ctitle":"","pid":"12","userid":"109","nickname":"神秘蜂","mobile":"15581558777","price":"0.00","credit":"13","express":"","billno":"","issend":"1","area":null,"path":null,"address":"深圳市","detail":null,"sort":"1","addtime":"1443603824","isdel":"2","image":[{"id":"2894","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/MDHZy2iF7bDx","filekeys":"","company":"0","addid":"0","addtime":"1443603221","sort":"2"},{"id":"2893","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/GQcjrepCJ7Ta","filekeys":"","company":"0","addid":"0","addtime":"1443603220","sort":"1"},{"id":"2892","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/58YndjRzfMNB","filekeys":"","company":"0","addid":"0","addtime":"1443603219","sort":"0"}]},{"id":"23","solevar":"e0e1177ecc678f44","title":"黄山香溪漂流 随时预定随到随进 黄山天湖漂流门票","ctitle":"","pid":"12","userid":"109","nickname":null,"mobile":"15581558777","price":"0.00","credit":"13","express":"","billno":"","issend":"1","area":null,"path":null,"address":"深圳市","detail":null,"sort":"1","addtime":"1443603470","isdel":"2","image":[{"id":"2894","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/MDHZy2iF7bDx","filekeys":"","company":"0","addid":"0","addtime":"1443603221","sort":"2"},{"id":"2893","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/GQcjrepCJ7Ta","filekeys":"","company":"0","addid":"0","addtime":"1443603220","sort":"1"},{"id":"2892","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/58YndjRzfMNB","filekeys":"","company":"0","addid":"0","addtime":"1443603219","sort":"0"}]}]}
     * info :
     */

    private int status;
    private DataEntity data;
    private String info;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public DataEntity getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public static class DataEntity {
        /**
         * count : 2
         * nowPage : 1
         * totalPage : 1
         * list : [{"id":"25","solevar":"e0e1177ecc678f44","title":"黄山香溪漂流 随时预定随到随进 黄山天湖漂流门票","ctitle":"","pid":"12","userid":"109","nickname":"神秘蜂","mobile":"15581558777","price":"0.00","credit":"13","express":"","billno":"","issend":"1","area":null,"path":null,"address":"深圳市","detail":null,"sort":"1","addtime":"1443603824","isdel":"2","image":[{"id":"2894","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/MDHZy2iF7bDx","filekeys":"","company":"0","addid":"0","addtime":"1443603221","sort":"2"},{"id":"2893","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/GQcjrepCJ7Ta","filekeys":"","company":"0","addid":"0","addtime":"1443603220","sort":"1"},{"id":"2892","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/58YndjRzfMNB","filekeys":"","company":"0","addid":"0","addtime":"1443603219","sort":"0"}]},{"id":"23","solevar":"e0e1177ecc678f44","title":"黄山香溪漂流 随时预定随到随进 黄山天湖漂流门票","ctitle":"","pid":"12","userid":"109","nickname":null,"mobile":"15581558777","price":"0.00","credit":"13","express":"","billno":"","issend":"1","area":null,"path":null,"address":"深圳市","detail":null,"sort":"1","addtime":"1443603470","isdel":"2","image":[{"id":"2894","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/MDHZy2iF7bDx","filekeys":"","company":"0","addid":"0","addtime":"1443603221","sort":"2"},{"id":"2893","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/GQcjrepCJ7Ta","filekeys":"","company":"0","addid":"0","addtime":"1443603220","sort":"1"},{"id":"2892","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/58YndjRzfMNB","filekeys":"","company":"0","addid":"0","addtime":"1443603219","sort":"0"}]}]
         */

        private String count;
        private int nowPage;
        private int totalPage;
        private List<ListEntity> list;

        public void setCount(String count) {
            this.count = count;
        }

        public void setNowPage(int nowPage) {
            this.nowPage = nowPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public String getCount() {
            return count;
        }

        public int getNowPage() {
            return nowPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class ListEntity {
            /**
             * id : 25
             * solevar : e0e1177ecc678f44
             * title : 黄山香溪漂流 随时预定随到随进 黄山天湖漂流门票
             * ctitle :
             * pid : 12
             * userid : 109
             * nickname : 神秘蜂
             * mobile : 15581558777
             * price : 0.00
             * credit : 13
             * express :
             * billno :
             * issend : 1
             * area : null
             * path : null
             * address : 深圳市
             * detail : null
             * sort : 1
             * addtime : 1443603824
             * isdel : 2
             * image : [{"id":"2894","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/MDHZy2iF7bDx","filekeys":"","company":"0","addid":"0","addtime":"1443603221","sort":"2"},{"id":"2893","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/GQcjrepCJ7Ta","filekeys":"","company":"0","addid":"0","addtime":"1443603220","sort":"1"},{"id":"2892","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/58YndjRzfMNB","filekeys":"","company":"0","addid":"0","addtime":"1443603219","sort":"0"}]
             */

            private String id;
            private String solevar;
            private String title;
            private String ctitle;
            private String pid;
            private String userid;
            private String nickname;
            private String mobile;
            private String price;
            private String credit;
            private String express;
            private String billno;
            private String issend;
            private Object area;
            private Object path;
            private String address;
            private Object detail;
            private String sort;
            private String addtime;
            private String isdel;
            private List<ImageEntity> image;

            public void setId(String id) {
                this.id = id;
            }

            public void setSolevar(String solevar) {
                this.solevar = solevar;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setCtitle(String ctitle) {
                this.ctitle = ctitle;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setCredit(String credit) {
                this.credit = credit;
            }

            public void setExpress(String express) {
                this.express = express;
            }

            public void setBillno(String billno) {
                this.billno = billno;
            }

            public void setIssend(String issend) {
                this.issend = issend;
            }

            public void setArea(Object area) {
                this.area = area;
            }

            public void setPath(Object path) {
                this.path = path;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setDetail(Object detail) {
                this.detail = detail;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public void setIsdel(String isdel) {
                this.isdel = isdel;
            }

            public void setImage(List<ImageEntity> image) {
                this.image = image;
            }

            public String getId() {
                return id;
            }

            public String getSolevar() {
                return solevar;
            }

            public String getTitle() {
                return title;
            }

            public String getCtitle() {
                return ctitle;
            }

            public String getPid() {
                return pid;
            }

            public String getUserid() {
                return userid;
            }

            public String getNickname() {
                return nickname;
            }

            public String getMobile() {
                return mobile;
            }

            public String getPrice() {
                return price;
            }

            public String getCredit() {
                return credit;
            }

            public String getExpress() {
                return express;
            }

            public String getBillno() {
                return billno;
            }

            public String getIssend() {
                return issend;
            }

            public Object getArea() {
                return area;
            }

            public Object getPath() {
                return path;
            }

            public String getAddress() {
                return address;
            }

            public Object getDetail() {
                return detail;
            }

            public String getSort() {
                return sort;
            }

            public String getAddtime() {
                return addtime;
            }

            public String getIsdel() {
                return isdel;
            }

            public List<ImageEntity> getImage() {
                return image;
            }

            public static class ImageEntity {
                /**
                 * id : 2894
                 * c_table : Integral
                 * c_solevar : e0e1177ecc678f44
                 * c_title :
                 * c_affect : atlas
                 * fileurl : integral/MDHZy2iF7bDx
                 * filekeys :
                 * company : 0
                 * addid : 0
                 * addtime : 1443603221
                 * sort : 2
                 */

                private String id;
                private String c_table;
                private String c_solevar;
                private String c_title;
                private String c_affect;
                private String fileurl;
                private String filekeys;
                private String company;
                private String addid;
                private String addtime;
                private String sort;

                public void setId(String id) {
                    this.id = id;
                }

                public void setC_table(String c_table) {
                    this.c_table = c_table;
                }

                public void setC_solevar(String c_solevar) {
                    this.c_solevar = c_solevar;
                }

                public void setC_title(String c_title) {
                    this.c_title = c_title;
                }

                public void setC_affect(String c_affect) {
                    this.c_affect = c_affect;
                }

                public void setFileurl(String fileurl) {
                    this.fileurl = fileurl;
                }

                public void setFilekeys(String filekeys) {
                    this.filekeys = filekeys;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public void setAddid(String addid) {
                    this.addid = addid;
                }

                public void setAddtime(String addtime) {
                    this.addtime = addtime;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getId() {
                    return id;
                }

                public String getC_table() {
                    return c_table;
                }

                public String getC_solevar() {
                    return c_solevar;
                }

                public String getC_title() {
                    return c_title;
                }

                public String getC_affect() {
                    return c_affect;
                }

                public String getFileurl() {
                    return fileurl;
                }

                public String getFilekeys() {
                    return filekeys;
                }

                public String getCompany() {
                    return company;
                }

                public String getAddid() {
                    return addid;
                }

                public String getAddtime() {
                    return addtime;
                }

                public String getSort() {
                    return sort;
                }
            }
        }
    }
}
