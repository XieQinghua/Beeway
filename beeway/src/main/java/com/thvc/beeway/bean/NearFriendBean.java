package com.thvc.beeway.bean;

import java.util.List;

/**
 * 项目名称：Beeway
 * 类描述：
 * 创建人：谢庆华.
 * 创建时间：2015/9/17 19:08
 * 修改人：Administrator
 * 修改时间：2015/9/17 19:08
 * 修改备注：
 * http://www.hlfyb.com/rest.php/APP/User/nearby?userid=c191a2c51bbd99da&longitude=112.891795&latitude=28.213868&p=0
 */
public class NearFriendBean {

    /**
     * data : {"totalPage":4,"count":"37","list":[{"distance":1004,"city":"长沙市","level":"lv2","latitude":"28.222624","sex":"1","solevar":"86e704e9c3d02e1a","headpic":"Photo/Headpic/member_pic.jpg","content":"夺宝奇兵，就看贝贝","lvImg":"","lvScore":500,"geohash":"wt028cg88q6m","lvIntro":"","nickname":"贝贝","lvName":"宅巢蜂","longitude":"112.889342"},{"distance":1028,"city":"长沙市","level":"lv2","latitude":"28.223018","sex":"1","solevar":"91dc8c13b2ea2b61","headpic":"Photo/Headpic/member_pic.jpg","content":"普通版晚上毛将焉附天玻利维亚我火车人擦肩","lvImg":"","lvScore":500,"geohash":"wt028cy6fxbh","lvIntro":"","nickname":"说夏","lvName":"宅巢蜂","longitude":"112.893212"},{"distance":7886,"city":"长沙市","level":"lv2","latitude":"28.233585","sex":"1","solevar":"61aa87b225f67d18","headpic":"Photo/Headpic/member_pic.jpg","content":"","lvImg":"","lvScore":500,"geohash":"wt02dev88cdy","lvIntro":"","nickname":"TT","lvName":"宅巢蜂","longitude":"112.969015"},{"distance":641960,"city":"","level":"lv1","latitude":"22.546162","sex":"1","solevar":"1111b4f77339c574","headpic":"avatar/39cab4f77339c574.jpg","content":"世界这么大，你不写点啥？","lvImg":"","lvScore":100,"geohash":"ws1078ruuv1p","lvIntro":"新用户注册后获得的封号","nickname":"华旅1","lvName":"宅巢蜂","longitude":"114.070907"},{"distance":1031,"city":"长沙市","level":"lv2","latitude":"28.223036","sex":"1","solevar":"286cc02a8fd6a895","headpic":"Photo/Headpic/member_pic.jpg","content":"天亮了雨却为到","lvImg":"","lvScore":500,"geohash":"wt028cy7h71x","lvIntro":"","nickname":"开拓者","lvName":"宅巢蜂","longitude":"112.893289"},{"distance":930,"city":"","level":"lv1","latitude":"28.219982","sex":"1","solevar":"3333ead7c63be01c","headpic":"avatar/b023ead7c63be01c.jpg","content":"世界这么大，你不写点啥？","lvImg":"","lvScore":100,"geohash":"wt02913crcvm","lvIntro":"新用户注册后获得的封号","nickname":"华旅3","lvName":"宅巢蜂","longitude":"112.898251"},{"distance":936,"city":"","level":"lv1","latitude":"28.220069","sex":"1","solevar":"4444ae5b0b28b3c9","headpic":"avatar/b615ae5b0b28b3c9.jpg","content":"世界这么大，你不写点啥？","lvImg":"","lvScore":100,"geohash":"wt02913czd4k","lvIntro":"新用户注册后获得的封号","nickname":"华旅4","lvName":"宅巢蜂","longitude":"112.898236"},{"distance":1037,"city":"长沙市","level":"lv1","latitude":"28.223106","sex":"1","solevar":"22226a3b4f4760e3","headpic":"avatar/fee16a3b4f4760e3.jpg","content":"世界这么大，你不写点啥？","lvImg":"","lvScore":100,"geohash":"wt028cy78879","lvIntro":"新用户注册后获得的封号","nickname":"华旅2","lvName":"宅巢蜂","longitude":"112.893131"},{"distance":7368438,"city":"","level":"lv2","latitude":"112.953432","sex":"1","solevar":"23e093f91d894e5b","headpic":"Photo/Headpic/member_pic.jpg","content":"","lvImg":"","lvScore":500,"geohash":"uxupcpvzyxyp","lvIntro":"","nickname":"肖然","lvName":"宅巢蜂","longitude":"28.177129"},{"distance":1104579,"city":"阿坝藏族羌族自治州","level":"lv2","latitude":"33.540087","sex":"1","solevar":"2d8f45969e828b59","headpic":"Photo/Headpic/member_pic.jpg","content":"立志如山，行道如水","lvImg":"","lvScore":500,"geohash":"wmcqf9yc0jqk","lvIntro":"","nickname":"周子莘","lvName":"宅巢蜂","longitude":"103.126954"}],"nowPage":1}
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
         * totalPage : 4
         * count : 37
         * list : [{"distance":1004,"city":"长沙市","level":"lv2","latitude":"28.222624","sex":"1","solevar":"86e704e9c3d02e1a","headpic":"Photo/Headpic/member_pic.jpg","content":"夺宝奇兵，就看贝贝","lvImg":"","lvScore":500,"geohash":"wt028cg88q6m","lvIntro":"","nickname":"贝贝","lvName":"宅巢蜂","longitude":"112.889342"},{"distance":1028,"city":"长沙市","level":"lv2","latitude":"28.223018","sex":"1","solevar":"91dc8c13b2ea2b61","headpic":"Photo/Headpic/member_pic.jpg","content":"普通版晚上毛将焉附天玻利维亚我火车人擦肩","lvImg":"","lvScore":500,"geohash":"wt028cy6fxbh","lvIntro":"","nickname":"说夏","lvName":"宅巢蜂","longitude":"112.893212"},{"distance":7886,"city":"长沙市","level":"lv2","latitude":"28.233585","sex":"1","solevar":"61aa87b225f67d18","headpic":"Photo/Headpic/member_pic.jpg","content":"","lvImg":"","lvScore":500,"geohash":"wt02dev88cdy","lvIntro":"","nickname":"TT","lvName":"宅巢蜂","longitude":"112.969015"},{"distance":641960,"city":"","level":"lv1","latitude":"22.546162","sex":"1","solevar":"1111b4f77339c574","headpic":"avatar/39cab4f77339c574.jpg","content":"世界这么大，你不写点啥？","lvImg":"","lvScore":100,"geohash":"ws1078ruuv1p","lvIntro":"新用户注册后获得的封号","nickname":"华旅1","lvName":"宅巢蜂","longitude":"114.070907"},{"distance":1031,"city":"长沙市","level":"lv2","latitude":"28.223036","sex":"1","solevar":"286cc02a8fd6a895","headpic":"Photo/Headpic/member_pic.jpg","content":"天亮了雨却为到","lvImg":"","lvScore":500,"geohash":"wt028cy7h71x","lvIntro":"","nickname":"开拓者","lvName":"宅巢蜂","longitude":"112.893289"},{"distance":930,"city":"","level":"lv1","latitude":"28.219982","sex":"1","solevar":"3333ead7c63be01c","headpic":"avatar/b023ead7c63be01c.jpg","content":"世界这么大，你不写点啥？","lvImg":"","lvScore":100,"geohash":"wt02913crcvm","lvIntro":"新用户注册后获得的封号","nickname":"华旅3","lvName":"宅巢蜂","longitude":"112.898251"},{"distance":936,"city":"","level":"lv1","latitude":"28.220069","sex":"1","solevar":"4444ae5b0b28b3c9","headpic":"avatar/b615ae5b0b28b3c9.jpg","content":"世界这么大，你不写点啥？","lvImg":"","lvScore":100,"geohash":"wt02913czd4k","lvIntro":"新用户注册后获得的封号","nickname":"华旅4","lvName":"宅巢蜂","longitude":"112.898236"},{"distance":1037,"city":"长沙市","level":"lv1","latitude":"28.223106","sex":"1","solevar":"22226a3b4f4760e3","headpic":"avatar/fee16a3b4f4760e3.jpg","content":"世界这么大，你不写点啥？","lvImg":"","lvScore":100,"geohash":"wt028cy78879","lvIntro":"新用户注册后获得的封号","nickname":"华旅2","lvName":"宅巢蜂","longitude":"112.893131"},{"distance":7368438,"city":"","level":"lv2","latitude":"112.953432","sex":"1","solevar":"23e093f91d894e5b","headpic":"Photo/Headpic/member_pic.jpg","content":"","lvImg":"","lvScore":500,"geohash":"uxupcpvzyxyp","lvIntro":"","nickname":"肖然","lvName":"宅巢蜂","longitude":"28.177129"},{"distance":1104579,"city":"阿坝藏族羌族自治州","level":"lv2","latitude":"33.540087","sex":"1","solevar":"2d8f45969e828b59","headpic":"Photo/Headpic/member_pic.jpg","content":"立志如山，行道如水","lvImg":"","lvScore":500,"geohash":"wmcqf9yc0jqk","lvIntro":"","nickname":"周子莘","lvName":"宅巢蜂","longitude":"103.126954"}]
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
             * distance : 1004
             * city : 长沙市
             * level : lv2
             * latitude : 28.222624
             * sex : 1
             * solevar : 86e704e9c3d02e1a
             * headpic : Photo/Headpic/member_pic.jpg
             * content : 夺宝奇兵，就看贝贝
             * lvImg :
             * lvScore : 500
             * geohash : wt028cg88q6m
             * lvIntro :
             * nickname : 贝贝
             * lvName : 宅巢蜂
             * longitude : 112.889342
             */
            private int distance;
            private String city;
            private String level;
            private String latitude;
            private String sex;
            private String solevar;
            private String headpic;
            private String content;
            private String lvImg;
            private int lvScore;
            private String geohash;
            private String lvIntro;
            private String nickname;
            private String lvName;
            private String longitude;

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public void setSolevar(String solevar) {
                this.solevar = solevar;
            }

            public void setHeadpic(String headpic) {
                this.headpic = headpic;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setLvImg(String lvImg) {
                this.lvImg = lvImg;
            }

            public void setLvScore(int lvScore) {
                this.lvScore = lvScore;
            }

            public void setGeohash(String geohash) {
                this.geohash = geohash;
            }

            public void setLvIntro(String lvIntro) {
                this.lvIntro = lvIntro;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setLvName(String lvName) {
                this.lvName = lvName;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public int getDistance() {
                return distance;
            }

            public String getCity() {
                return city;
            }

            public String getLevel() {
                return level;
            }

            public String getLatitude() {
                return latitude;
            }

            public String getSex() {
                return sex;
            }

            public String getSolevar() {
                return solevar;
            }

            public String getHeadpic() {
                return headpic;
            }

            public String getContent() {
                return content;
            }

            public String getLvImg() {
                return lvImg;
            }

            public int getLvScore() {
                return lvScore;
            }

            public String getGeohash() {
                return geohash;
            }

            public String getLvIntro() {
                return lvIntro;
            }

            public String getNickname() {
                return nickname;
            }

            public String getLvName() {
                return lvName;
            }

            public String getLongitude() {
                return longitude;
            }
        }
    }
}
