package com.thvc.beeway.bean;

import java.io.Serializable;
import java.util.List;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/1.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class CustomerBean implements Serializable {

    /**
     * data : {"totalPage":3,"count":"27","list":[{"csolevar":"","mprice":"10.00","sex":"1","cprice":"0.00","bill":"1","solevar":"80b2dbf639f8cf27","sprice":"100.00","userid":"4bb3d8bd57ab9a99","headpic":"Photo/Headpic/member_pic.jpg","content":"就一句话，给我钱吗？","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1440746745","c_table":"Want","c_solevar":"80b2dbf639f8cf27","fileurl":"avatar/BF64DD10-3D53-4376-B6D6-8A9B3E32B3C4+1440775540.jpg","addid":"0","company":"101","id":"1572","sort":"0"},"addtime":"1440746745","name":"铜官镇","nickname":"和大大","id":"29","isdel":"2","support":"1","status":"2"},{"csolevar":"","mprice":"555.00","sex":"1","cprice":"0.00","bill":"1","solevar":"bbb342af060b5431","sprice":"225.00","userid":"c191a2c51bbd99da","headpic":"Photo/Headpic/member_pic.jpg","content":"旅行没有那么多借口，打赏没有那么多理由","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1440125408","c_table":"Want","c_solevar":"bbb342af060b5431","fileurl":"avatar/33511781-CC63-4DDE-9CFC-54F021210F51+1440154165.jpg","addid":"0","company":"99","id":"1563","sort":"0"},"addtime":"1440125408","name":"中国花炮博物馆","nickname":"大B","id":"28","isdel":"2","support":"1","status":"1"},{"csolevar":"","mprice":"20.00","sex":"1","cprice":"0.00","bill":"1","solevar":"84210abd65d6bf07","sprice":"55.00","userid":"c191a2c51bbd99da","headpic":"Photo/Headpic/member_pic.jpg","content":"ttttt快来","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1440122881","c_table":"Want","c_solevar":"84210abd65d6bf07","fileurl":"avatar/5F38D0F7-DC57-48D8-880F-31DDF1B6B34E+1440151663.jpg","addid":"0","company":"66","id":"1562","sort":"0"},"addtime":"1440122881","name":"道吾山风景名胜区","nickname":"大B","id":"27","isdel":"2","support":"1","status":"2"},{"csolevar":"","mprice":"70.00","sex":"1","cprice":"0.00","bill":"1","solevar":"7eb34acdcc585b47","sprice":"55.00","userid":"91dc8c13b2ea2b61","headpic":"Photo/Headpic/member_pic.jpg","content":"卖艺赚雪橇，有想听两只老虎的吗？","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1439792116","c_table":"Want","c_solevar":"7eb34acdcc585b47","fileurl":"avatar/54C23AFE-434D-44B4-9659-490E3964B9A6+1439820911.jpg","addid":"0","company":"1","id":"1556","sort":"0"},"addtime":"1439792116","name":"橘子洲头","nickname":"大Da","id":"26","isdel":"2","support":"2","status":"1"},{"csolevar":"","mprice":"50.00","sex":"1","cprice":"0.00","bill":"1","solevar":"458253042ed26acc","sprice":"2222.00","userid":"4bb3d8bd57ab9a99","headpic":"Photo/Headpic/member_pic.jpg","content":"[呲牙][呲牙][呲牙][呲牙][呲牙][呲牙][呲牙][呲牙][呲牙]","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1439537115","c_table":"Want","c_solevar":"458253042ed26acc","fileurl":"avatar/67FC5818-FBC9-4530-A6D3-4F134AC1F806+1439565910.jpg","addid":"0","company":"7","id":"1555","sort":"0"},"addtime":"1439537115","name":"长沙洋湖垸湿地公园","nickname":"和大大","id":"25","isdel":"2","support":"1","status":"2"},{"csolevar":"","mprice":"0.00","cprice":"0.00","bill":"1","solevar":"e2f543aefa9c9536","sprice":"0.00","userid":"","content":"frr","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1439283021","c_table":"Want","c_solevar":"e2f543aefa9c9536","fileurl":"/System/Want_0.jpg","addid":"0","company":"3","id":"1481","sort":"0"},"addtime":"1439283021","name":"玉龙雪山","id":"23","isdel":"2","support":"0","status":"2"},{"csolevar":"","mprice":"0.00","cprice":"0.00","bill":"1","solevar":"433e1688f6cc978a","sprice":"0.00","userid":"","content":"frr","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1439283021","c_table":"Want","c_solevar":"433e1688f6cc978a","fileurl":"/System/Want_0.jpg","addid":"0","company":"3","id":"1482","sort":"0"},"addtime":"1439283021","name":"玉龙雪山","id":"24","isdel":"2","support":"0","status":"2"},{"csolevar":"","mprice":"0.00","sex":"1","cprice":"0.00","bill":"1","solevar":"60f4d7883291b38b","sprice":"222.00","userid":"c191a2c51bbd99da","headpic":"Photo/Headpic/member_pic.jpg","content":"雪中送炭的你在哪里","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1439019445","c_table":"Want","c_solevar":"60f4d7883291b38b","fileurl":"avatar/E2B35B5F-EE27-420F-8884-76FC48389B51+1439048232.jpg","addid":"0","company":"1","id":"1339","sort":"0"},"addtime":"1439019445","name":"橘子洲头","nickname":"大B","id":"22","isdel":"2","support":"0","status":"2"},{"csolevar":"","mprice":"0.00","sex":"1","cprice":"0.00","bill":"1","solevar":"892aef6c60be3d03","sprice":"20000.00","userid":"c191a2c51bbd99da","headpic":"Photo/Headpic/member_pic.jpg","content":"子欲出行，奈何钱少，如若宽裕，赏我可好。","single":{"fileurl":"/System/Want_0.jpg"},"addtime":"1439018132","name":"长沙洋湖垸湿地公园","nickname":"大B","id":"21","isdel":"2","support":"0","status":"2"},{"csolevar":"","mprice":"0.00","sex":"1","cprice":"0.00","bill":"1","solevar":"34edb7a8bf5262b6","sprice":"1.00","userid":"c191a2c51bbd99da","headpic":"Photo/Headpic/member_pic.jpg","content":"贫僧怀有去西天取经之愿，不知施主是否可助我一臂之力。","single":{"fileurl":"/System/Want_0.jpg"},"addtime":"1439017303","name":"橘子洲头","nickname":"大B","id":"20","isdel":"2","support":"0","status":"2"}],"nowPage":1}
     * status : 1
     * info :
     */
    private DataEntity data;
    private int status;
    private String info;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public DataEntity getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public static class DataEntity {
        /**
         * totalPage : 3
         * count : 27
         * list : [{"csolevar":"","mprice":"10.00","sex":"1","cprice":"0.00","bill":"1","solevar":"80b2dbf639f8cf27","sprice":"100.00","userid":"4bb3d8bd57ab9a99","headpic":"Photo/Headpic/member_pic.jpg","content":"就一句话，给我钱吗？","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1440746745","c_table":"Want","c_solevar":"80b2dbf639f8cf27","fileurl":"avatar/BF64DD10-3D53-4376-B6D6-8A9B3E32B3C4+1440775540.jpg","addid":"0","company":"101","id":"1572","sort":"0"},"addtime":"1440746745","name":"铜官镇","nickname":"和大大","id":"29","isdel":"2","support":"1","status":"2"},{"csolevar":"","mprice":"555.00","sex":"1","cprice":"0.00","bill":"1","solevar":"bbb342af060b5431","sprice":"225.00","userid":"c191a2c51bbd99da","headpic":"Photo/Headpic/member_pic.jpg","content":"旅行没有那么多借口，打赏没有那么多理由","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1440125408","c_table":"Want","c_solevar":"bbb342af060b5431","fileurl":"avatar/33511781-CC63-4DDE-9CFC-54F021210F51+1440154165.jpg","addid":"0","company":"99","id":"1563","sort":"0"},"addtime":"1440125408","name":"中国花炮博物馆","nickname":"大B","id":"28","isdel":"2","support":"1","status":"1"},{"csolevar":"","mprice":"20.00","sex":"1","cprice":"0.00","bill":"1","solevar":"84210abd65d6bf07","sprice":"55.00","userid":"c191a2c51bbd99da","headpic":"Photo/Headpic/member_pic.jpg","content":"ttttt快来","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1440122881","c_table":"Want","c_solevar":"84210abd65d6bf07","fileurl":"avatar/5F38D0F7-DC57-48D8-880F-31DDF1B6B34E+1440151663.jpg","addid":"0","company":"66","id":"1562","sort":"0"},"addtime":"1440122881","name":"道吾山风景名胜区","nickname":"大B","id":"27","isdel":"2","support":"1","status":"2"},{"csolevar":"","mprice":"70.00","sex":"1","cprice":"0.00","bill":"1","solevar":"7eb34acdcc585b47","sprice":"55.00","userid":"91dc8c13b2ea2b61","headpic":"Photo/Headpic/member_pic.jpg","content":"卖艺赚雪橇，有想听两只老虎的吗？","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1439792116","c_table":"Want","c_solevar":"7eb34acdcc585b47","fileurl":"avatar/54C23AFE-434D-44B4-9659-490E3964B9A6+1439820911.jpg","addid":"0","company":"1","id":"1556","sort":"0"},"addtime":"1439792116","name":"橘子洲头","nickname":"大Da","id":"26","isdel":"2","support":"2","status":"1"},{"csolevar":"","mprice":"50.00","sex":"1","cprice":"0.00","bill":"1","solevar":"458253042ed26acc","sprice":"2222.00","userid":"4bb3d8bd57ab9a99","headpic":"Photo/Headpic/member_pic.jpg","content":"[呲牙][呲牙][呲牙][呲牙][呲牙][呲牙][呲牙][呲牙][呲牙]","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1439537115","c_table":"Want","c_solevar":"458253042ed26acc","fileurl":"avatar/67FC5818-FBC9-4530-A6D3-4F134AC1F806+1439565910.jpg","addid":"0","company":"7","id":"1555","sort":"0"},"addtime":"1439537115","name":"长沙洋湖垸湿地公园","nickname":"和大大","id":"25","isdel":"2","support":"1","status":"2"},{"csolevar":"","mprice":"0.00","cprice":"0.00","bill":"1","solevar":"e2f543aefa9c9536","sprice":"0.00","userid":"","content":"frr","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1439283021","c_table":"Want","c_solevar":"e2f543aefa9c9536","fileurl":"/System/Want_0.jpg","addid":"0","company":"3","id":"1481","sort":"0"},"addtime":"1439283021","name":"玉龙雪山","id":"23","isdel":"2","support":"0","status":"2"},{"csolevar":"","mprice":"0.00","cprice":"0.00","bill":"1","solevar":"433e1688f6cc978a","sprice":"0.00","userid":"","content":"frr","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1439283021","c_table":"Want","c_solevar":"433e1688f6cc978a","fileurl":"/System/Want_0.jpg","addid":"0","company":"3","id":"1482","sort":"0"},"addtime":"1439283021","name":"玉龙雪山","id":"24","isdel":"2","support":"0","status":"2"},{"csolevar":"","mprice":"0.00","sex":"1","cprice":"0.00","bill":"1","solevar":"60f4d7883291b38b","sprice":"222.00","userid":"c191a2c51bbd99da","headpic":"Photo/Headpic/member_pic.jpg","content":"雪中送炭的你在哪里","single":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1439019445","c_table":"Want","c_solevar":"60f4d7883291b38b","fileurl":"avatar/E2B35B5F-EE27-420F-8884-76FC48389B51+1439048232.jpg","addid":"0","company":"1","id":"1339","sort":"0"},"addtime":"1439019445","name":"橘子洲头","nickname":"大B","id":"22","isdel":"2","support":"0","status":"2"},{"csolevar":"","mprice":"0.00","sex":"1","cprice":"0.00","bill":"1","solevar":"892aef6c60be3d03","sprice":"20000.00","userid":"c191a2c51bbd99da","headpic":"Photo/Headpic/member_pic.jpg","content":"子欲出行，奈何钱少，如若宽裕，赏我可好。","single":{"fileurl":"/System/Want_0.jpg"},"addtime":"1439018132","name":"长沙洋湖垸湿地公园","nickname":"大B","id":"21","isdel":"2","support":"0","status":"2"},{"csolevar":"","mprice":"0.00","sex":"1","cprice":"0.00","bill":"1","solevar":"34edb7a8bf5262b6","sprice":"1.00","userid":"c191a2c51bbd99da","headpic":"Photo/Headpic/member_pic.jpg","content":"贫僧怀有去西天取经之愿，不知施主是否可助我一臂之力。","single":{"fileurl":"/System/Want_0.jpg"},"addtime":"1439017303","name":"橘子洲头","nickname":"大B","id":"20","isdel":"2","support":"0","status":"2"}]
         * nowPage : 1
         */
        private int totalPage;
        private String count;
        private List<ListEntity> list;
        private int nowPage;

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public void setNowPage(int nowPage) {
            this.nowPage = nowPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public String getCount() {
            return count;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public int getNowPage() {
            return nowPage;
        }

        public static class ListEntity {
            /**
             * csolevar :
             * mprice : 10.00
             * sex : 1
             * cprice : 0.00
             * bill : 1
             * solevar : 80b2dbf639f8cf27
             * sprice : 100.00
             * userid : 4bb3d8bd57ab9a99
             * headpic : Photo/Headpic/member_pic.jpg
             * content : 就一句话，给我钱吗？
             * single : {"filekeys":"","c_title":"","c_affect":"single","addtime":"1440746745","c_table":"Want","c_solevar":"80b2dbf639f8cf27","fileurl":"avatar/BF64DD10-3D53-4376-B6D6-8A9B3E32B3C4+1440775540.jpg","addid":"0","company":"101","id":"1572","sort":"0"}
             * addtime : 1440746745
             * name : 铜官镇
             * nickname : 和大大
             * id : 29
             * isdel : 2
             * support : 1
             * status : 2
             */
            private String csolevar;
            private String mprice;
            private String sex;
            private String cprice;
            private String bill;
            private String solevar;
            private String sprice;
            private String userid;
            private String headpic;
            private String content;
            private SingleEntity single;
            private String addtime;
            private String name;
            private String nickname;
            private String id;
            private String isdel;
            private String support;
            private String status;

            public void setCsolevar(String csolevar) {
                this.csolevar = csolevar;
            }

            public void setMprice(String mprice) {
                this.mprice = mprice;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public void setCprice(String cprice) {
                this.cprice = cprice;
            }

            public void setBill(String bill) {
                this.bill = bill;
            }

            public void setSolevar(String solevar) {
                this.solevar = solevar;
            }

            public void setSprice(String sprice) {
                this.sprice = sprice;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public void setHeadpic(String headpic) {
                this.headpic = headpic;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setSingle(SingleEntity single) {
                this.single = single;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setIsdel(String isdel) {
                this.isdel = isdel;
            }

            public void setSupport(String support) {
                this.support = support;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCsolevar() {
                return csolevar;
            }

            public String getMprice() {
                return mprice;
            }

            public String getSex() {
                return sex;
            }

            public String getCprice() {
                return cprice;
            }

            public String getBill() {
                return bill;
            }

            public String getSolevar() {
                return solevar;
            }

            public String getSprice() {
                return sprice;
            }

            public String getUserid() {
                return userid;
            }

            public String getHeadpic() {
                return headpic;
            }

            public String getContent() {
                return content;
            }

            public SingleEntity getSingle() {
                return single;
            }

            public String getAddtime() {
                return addtime;
            }

            public String getName() {
                return name;
            }

            public String getNickname() {
                return nickname;
            }

            public String getId() {
                return id;
            }

            public String getIsdel() {
                return isdel;
            }

            public String getSupport() {
                return support;
            }

            public String getStatus() {
                return status;
            }

            public static class SingleEntity {
                /**
                 * filekeys :
                 * c_title :
                 * c_affect : single
                 * addtime : 1440746745
                 * c_table : Want
                 * c_solevar : 80b2dbf639f8cf27
                 * fileurl : avatar/BF64DD10-3D53-4376-B6D6-8A9B3E32B3C4+1440775540.jpg
                 * addid : 0
                 * company : 101
                 * id : 1572
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
