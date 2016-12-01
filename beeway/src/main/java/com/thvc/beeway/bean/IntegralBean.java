package com.thvc.beeway.bean;


import java.util.List;

public class IntegralBean {

    /**
     * status : 1
     * data : {"count":"3","nowPage":1,"totalPage":1,"list":[{"id":"12","solevar":"e0e1177ecc678f44","title":"黄山香溪漂流 随时预定随到随进 黄山天湖漂流门票","ctitle":"","credit":"13","addtime":"1443017765","total":"20","buynum":"6","isdel":"2","isrec":"2","image":[{"id":"2794","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/58YndjRzfMNB","filekeys":"","company":"0","addid":"0","addtime":"1443017765","sort":"0"},{"id":"2795","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/GQcjrepCJ7Ta","filekeys":"","company":"0","addid":"0","addtime":"1443017766","sort":"1"},{"id":"2796","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/MDHZy2iF7bDx","filekeys":"","company":"0","addid":"0","addtime":"1443017767","sort":"2"}]},{"id":"11","solevar":"0b6654191338abe8","title":"毛主席像章 毛泽东胸章徽章 直径3厘米[镀真金] 文革纪念章收藏 ","ctitle":"","credit":"15","addtime":"1443017415","total":"15","buynum":"4","isdel":"2","isrec":"2","image":[{"id":"2793","c_table":"Integral","c_solevar":"0b6654191338abe8","c_title":"","c_affect":"atlas","fileurl":"integral/XmYFkecnPy8p","filekeys":"","company":"0","addid":"0","addtime":"1443017530","sort":"2"},{"id":"2792","c_table":"Integral","c_solevar":"0b6654191338abe8","c_title":"","c_affect":"atlas","fileurl":"integral/H8d34K2sadwd","filekeys":"","company":"0","addid":"0","addtime":"1443017529","sort":"1"},{"id":"2791","c_table":"Integral","c_solevar":"0b6654191338abe8","c_title":"","c_affect":"atlas","fileurl":"integral/rjrKRsW2QMwJ","filekeys":"","company":"0","addid":"0","addtime":"1443017528","sort":"0"}]},{"id":"10","solevar":"65657fff247db313","title":"100元移动充值卡","ctitle":"","credit":"10","addtime":"1443017250","total":"50","buynum":"1","isdel":"2","isrec":"2","image":[{"id":"2787","c_table":"Integral","c_solevar":"65657fff247db313","c_title":"","c_affect":"atlas","fileurl":"integral/dA36KyFayEjW","filekeys":"","company":"0","addid":"0","addtime":"1443017250","sort":"0"}]}]}
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
         * count : 3
         * nowPage : 1
         * totalPage : 1
         * list : [{"id":"12","solevar":"e0e1177ecc678f44","title":"黄山香溪漂流 随时预定随到随进 黄山天湖漂流门票","ctitle":"","credit":"13","addtime":"1443017765","total":"20","buynum":"6","isdel":"2","isrec":"2","image":[{"id":"2794","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/58YndjRzfMNB","filekeys":"","company":"0","addid":"0","addtime":"1443017765","sort":"0"},{"id":"2795","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/GQcjrepCJ7Ta","filekeys":"","company":"0","addid":"0","addtime":"1443017766","sort":"1"},{"id":"2796","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/MDHZy2iF7bDx","filekeys":"","company":"0","addid":"0","addtime":"1443017767","sort":"2"}]},{"id":"11","solevar":"0b6654191338abe8","title":"毛主席像章 毛泽东胸章徽章 直径3厘米[镀真金] 文革纪念章收藏 ","ctitle":"","credit":"15","addtime":"1443017415","total":"15","buynum":"4","isdel":"2","isrec":"2","image":[{"id":"2793","c_table":"Integral","c_solevar":"0b6654191338abe8","c_title":"","c_affect":"atlas","fileurl":"integral/XmYFkecnPy8p","filekeys":"","company":"0","addid":"0","addtime":"1443017530","sort":"2"},{"id":"2792","c_table":"Integral","c_solevar":"0b6654191338abe8","c_title":"","c_affect":"atlas","fileurl":"integral/H8d34K2sadwd","filekeys":"","company":"0","addid":"0","addtime":"1443017529","sort":"1"},{"id":"2791","c_table":"Integral","c_solevar":"0b6654191338abe8","c_title":"","c_affect":"atlas","fileurl":"integral/rjrKRsW2QMwJ","filekeys":"","company":"0","addid":"0","addtime":"1443017528","sort":"0"}]},{"id":"10","solevar":"65657fff247db313","title":"100元移动充值卡","ctitle":"","credit":"10","addtime":"1443017250","total":"50","buynum":"1","isdel":"2","isrec":"2","image":[{"id":"2787","c_table":"Integral","c_solevar":"65657fff247db313","c_title":"","c_affect":"atlas","fileurl":"integral/dA36KyFayEjW","filekeys":"","company":"0","addid":"0","addtime":"1443017250","sort":"0"}]}]
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
             * id : 12
             * solevar : e0e1177ecc678f44
             * title : 黄山香溪漂流 随时预定随到随进 黄山天湖漂流门票
             * ctitle :
             * credit : 13
             * addtime : 1443017765
             * total : 20
             * buynum : 6
             * isdel : 2
             * isrec : 2
             * image : [{"id":"2794","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/58YndjRzfMNB","filekeys":"","company":"0","addid":"0","addtime":"1443017765","sort":"0"},{"id":"2795","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/GQcjrepCJ7Ta","filekeys":"","company":"0","addid":"0","addtime":"1443017766","sort":"1"},{"id":"2796","c_table":"Integral","c_solevar":"e0e1177ecc678f44","c_title":"","c_affect":"atlas","fileurl":"integral/MDHZy2iF7bDx","filekeys":"","company":"0","addid":"0","addtime":"1443017767","sort":"2"}]
             */

            private String id;
            private String solevar;
            private String title;
            private String ctitle;
            private String credit;
            private String addtime;
            private String total;
            private String buynum;
            private String isdel;
            private String isrec;
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

            public void setCredit(String credit) {
                this.credit = credit;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public void setBuynum(String buynum) {
                this.buynum = buynum;
            }

            public void setIsdel(String isdel) {
                this.isdel = isdel;
            }

            public void setIsrec(String isrec) {
                this.isrec = isrec;
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

            public String getCredit() {
                return credit;
            }

            public String getAddtime() {
                return addtime;
            }

            public String getTotal() {
                return total;
            }

            public String getBuynum() {
                return buynum;
            }

            public String getIsdel() {
                return isdel;
            }

            public String getIsrec() {
                return isrec;
            }

            public List<ImageEntity> getImage() {
                return image;
            }

            public static class ImageEntity {
                /**
                 * id : 2794
                 * c_table : Integral
                 * c_solevar : e0e1177ecc678f44
                 * c_title :
                 * c_affect : atlas
                 * fileurl : integral/58YndjRzfMNB
                 * filekeys :
                 * company : 0
                 * addid : 0
                 * addtime : 1443017765
                 * sort : 0
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
