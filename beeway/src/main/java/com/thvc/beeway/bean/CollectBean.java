package com.thvc.beeway.bean;

import java.io.Serializable;
import java.util.List;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/10/12.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class CollectBean implements Serializable{

    /**
     * data : {"totalPage":1,"count":"3","list":[{"image":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1444455971","c_table":"TrackTravel","c_solevar":"ac38f0c27589758e","fileurl":"travel/DA4CCC32-9BC5-406B-BB4D-3351E140A19D_1444484770.jpg","addid":"0","company":"0","id":"3321","sort":"0"},"data":{"description":"期待下一站","solevar":"ac38f0c27589758e","title":"2015年走过的地方","userid":"61aa87b225f67d18"},"num":"1","solevar":"ac38f0c27589758e","remark":"","userid":"da383f2b0aef4de6","content":"","dataid":"5","addtime":"1444620361","tableid":"TrackTravel","id":"18","user":{"nickname":"TT","solevar":"61aa87b225f67d18","headpic":"Photo/Headpic/member_pic.jpg"},"status":"2"},{"image":{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1444456484","c_table":"Track","c_solevar":"f9793b1b52b85a0a","fileurl":"track/E64F9F2F-E725-4C6E-A7BE-96E987958061_1444485283.jpg","addid":"0","company":"0","id":"3323","sort":"0"},"data":{"solevar":"f9793b1b52b85a0a","userid":"61aa87b225f67d18","content":"一碗牛肉面代表我的心"},"num":"1","solevar":"f9793b1b52b85a0a","remark":"","userid":"da383f2b0aef4de6","content":"一碗牛肉面代表我的心","dataid":"156","addtime":"1444620328","tableid":"TrackNote","id":"17","user":{"nickname":"TT","solevar":"61aa87b225f67d18","headpic":"Photo/Headpic/member_pic.jpg"},"status":"2"},{"image":{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1444618722","c_table":"Track","c_solevar":"8bd08fb00e878047","fileurl":"track/B1AB3E50-9D5E-45A3-8F73-1D4D96001AFE_1444647527.jpg","addid":"0","company":"0","id":"3457","sort":"0"},"data":{"solevar":"8bd08fb00e878047","userid":"9993f448e86042ba","content":"郴州东江湖[调皮][调皮][调皮]"},"num":"1","solevar":"8bd08fb00e878047","remark":"","userid":"da383f2b0aef4de6","content":"郴州东江湖[调皮][调皮][调皮]","dataid":"160","addtime":"1444620321","tableid":"TrackNote","id":"16","user":{"nickname":"小小T爷","solevar":"9993f448e86042ba","headpic":"Photo/Headpic/member_pic.jpg"},"status":"2"}],"nowPage":1}
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
         * totalPage : 1
         * count : 3
         * list : [{"image":{"filekeys":"","c_title":"","c_affect":"single","addtime":"1444455971","c_table":"TrackTravel","c_solevar":"ac38f0c27589758e","fileurl":"travel/DA4CCC32-9BC5-406B-BB4D-3351E140A19D_1444484770.jpg","addid":"0","company":"0","id":"3321","sort":"0"},"data":{"description":"期待下一站","solevar":"ac38f0c27589758e","title":"2015年走过的地方","userid":"61aa87b225f67d18"},"num":"1","solevar":"ac38f0c27589758e","remark":"","userid":"da383f2b0aef4de6","content":"","dataid":"5","addtime":"1444620361","tableid":"TrackTravel","id":"18","user":{"nickname":"TT","solevar":"61aa87b225f67d18","headpic":"Photo/Headpic/member_pic.jpg"},"status":"2"},{"image":{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1444456484","c_table":"Track","c_solevar":"f9793b1b52b85a0a","fileurl":"track/E64F9F2F-E725-4C6E-A7BE-96E987958061_1444485283.jpg","addid":"0","company":"0","id":"3323","sort":"0"},"data":{"solevar":"f9793b1b52b85a0a","userid":"61aa87b225f67d18","content":"一碗牛肉面代表我的心"},"num":"1","solevar":"f9793b1b52b85a0a","remark":"","userid":"da383f2b0aef4de6","content":"一碗牛肉面代表我的心","dataid":"156","addtime":"1444620328","tableid":"TrackNote","id":"17","user":{"nickname":"TT","solevar":"61aa87b225f67d18","headpic":"Photo/Headpic/member_pic.jpg"},"status":"2"},{"image":{"filekeys":"","c_title":"","c_affect":"atlas","addtime":"1444618722","c_table":"Track","c_solevar":"8bd08fb00e878047","fileurl":"track/B1AB3E50-9D5E-45A3-8F73-1D4D96001AFE_1444647527.jpg","addid":"0","company":"0","id":"3457","sort":"0"},"data":{"solevar":"8bd08fb00e878047","userid":"9993f448e86042ba","content":"郴州东江湖[调皮][调皮][调皮]"},"num":"1","solevar":"8bd08fb00e878047","remark":"","userid":"da383f2b0aef4de6","content":"郴州东江湖[调皮][调皮][调皮]","dataid":"160","addtime":"1444620321","tableid":"TrackNote","id":"16","user":{"nickname":"小小T爷","solevar":"9993f448e86042ba","headpic":"Photo/Headpic/member_pic.jpg"},"status":"2"}]
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
             * image : {"filekeys":"","c_title":"","c_affect":"single","addtime":"1444455971","c_table":"TrackTravel","c_solevar":"ac38f0c27589758e","fileurl":"travel/DA4CCC32-9BC5-406B-BB4D-3351E140A19D_1444484770.jpg","addid":"0","company":"0","id":"3321","sort":"0"}
             * data : {"description":"期待下一站","solevar":"ac38f0c27589758e","title":"2015年走过的地方","userid":"61aa87b225f67d18"}
             * num : 1
             * solevar : ac38f0c27589758e
             * remark :
             * userid : da383f2b0aef4de6
             * content :
             * dataid : 5
             * addtime : 1444620361
             * tableid : TrackTravel
             * id : 18
             * user : {"nickname":"TT","solevar":"61aa87b225f67d18","headpic":"Photo/Headpic/member_pic.jpg"}
             * status : 2
             */
            private ImageEntity image;
            private Data data;
            private String num;
            private String solevar;
            private String remark;
            private String userid;
            private String content;
            private String dataid;
            private String addtime;
            private String tableid;
            private String id;
            private UserEntity user;
            private String status;

            public void setImage(ImageEntity image) {
                this.image = image;
            }

            public void setData(Data data) {
                this.data = data;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public void setSolevar(String solevar) {
                this.solevar = solevar;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setDataid(String dataid) {
                this.dataid = dataid;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public void setTableid(String tableid) {
                this.tableid = tableid;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setUser(UserEntity user) {
                this.user = user;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public ImageEntity getImage() {
                return image;
            }

            public Data getData() {
                return data;
            }

            public String getNum() {
                return num;
            }

            public String getSolevar() {
                return solevar;
            }

            public String getRemark() {
                return remark;
            }

            public String getUserid() {
                return userid;
            }

            public String getContent() {
                return content;
            }

            public String getDataid() {
                return dataid;
            }

            public String getAddtime() {
                return addtime;
            }

            public String getTableid() {
                return tableid;
            }

            public String getId() {
                return id;
            }

            public UserEntity getUser() {
                return user;
            }

            public String getStatus() {
                return status;
            }

            public static class ImageEntity {
                /**
                 * filekeys :
                 * c_title :
                 * c_affect : single
                 * addtime : 1444455971
                 * c_table : TrackTravel
                 * c_solevar : ac38f0c27589758e
                 * fileurl : travel/DA4CCC32-9BC5-406B-BB4D-3351E140A19D_1444484770.jpg
                 * addid : 0
                 * company : 0
                 * id : 3321
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

            public static class Data {
                /**
                 * description : 期待下一站
                 * solevar : ac38f0c27589758e
                 * title : 2015年走过的地方
                 * userid : 61aa87b225f67d18
                 */
                private String description;
                private String solevar;
                private String title;
                private String userid;

                public void setDescription(String description) {
                    this.description = description;
                }

                public void setSolevar(String solevar) {
                    this.solevar = solevar;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public String getDescription() {
                    return description;
                }

                public String getSolevar() {
                    return solevar;
                }

                public String getTitle() {
                    return title;
                }

                public String getUserid() {
                    return userid;
                }
            }

            public static class UserEntity {
                /**
                 * nickname : TT
                 * solevar : 61aa87b225f67d18
                 * headpic : Photo/Headpic/member_pic.jpg
                 */
                private String nickname;
                private String solevar;
                private String headpic;

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public void setSolevar(String solevar) {
                    this.solevar = solevar;
                }

                public void setHeadpic(String headpic) {
                    this.headpic = headpic;
                }

                public String getNickname() {
                    return nickname;
                }

                public String getSolevar() {
                    return solevar;
                }

                public String getHeadpic() {
                    return headpic;
                }
            }
        }
    }
}
