package com.thvc.beeway.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：Beeway
 * 类描述：
 * 创建人：谢庆华.
 * 创建时间：2015/8/29 17:00
 * 修改人：Administrator
 * 修改时间：2015/8/29 17:00
 * 修改备注：
 */
public class ScenicBean implements Serializable {
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
        private List<DatasEntity> datas;
        private int totalPage;
        private String count;
        private int nowPage;

        public void setDatas(List<DatasEntity> datas) {
            this.datas = datas;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public void setCount(String count) {
            this.count = count;
        }


        public void setNowPage(int nowPage) {
            this.nowPage = nowPage;
        }

        public List<DatasEntity> getDatas() {
            return datas;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public String getCount() {
            return count;
        }

        public int getNowPage() {
            return nowPage;
        }
        public static class DatasEntity {
            private String id;
            private String score;
            private String feature;
            private String area;
            private String address;
            private String detail;
            private String path;
            private String longitude;
            private String title;
            private String latitude;
            private String want;
            private String track;
            private String solevar;
            private String collect;
            private String addtime;
            private Single single;
            private int distances;

            public int getDistances() {
                return distances;
            }

            public void setDistances(int distances) {
                this.distances = distances;
            }

            public String getAddress() {
                return address;
            }

            public String getAddtime() {
                return addtime;
            }

            public String getCollect() {
                return collect;
            }

            public String getArea() {
                return area;
            }

            public String getDetail() {
                return detail;
            }

            public String getFeature() {
                return feature;
            }

            public String getId() {
                return id;
            }

            public String getLatitude() {
                return latitude;
            }

            public String getScore() {
                return score;
            }

            public String getSolevar() {
                return solevar;
            }

            public String getTrack() {
                return track;
            }

            public String getWant() {
                return want;
            }

            public String getTitle() {
                return title;
            }

            public Single getSingle() {
                return single;
            }

            public String getPath() {
                return path;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public void setCollect(String collect) {
                this.collect = collect;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public void setFeature(String feature) {
                this.feature = feature;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public void setSingle(Single single) {
                this.single = single;
            }

            public void setSolevar(String solevar) {
                this.solevar = solevar;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setTrack(String track) {
                this.track = track;
            }

            public void setWant(String want) {
                this.want = want;
            }

            public static class Single {
                private String fileurl;
                private String path;

                public String getFileurl() {
                    return fileurl;
                }

                public String getPath() {
                    return path;
                }

                public void setFileurl(String fileurl) {
                    this.fileurl = fileurl;
                }

                public void setPath(String path) {
                    this.path = path;
                }
            }
        }
    }
}
