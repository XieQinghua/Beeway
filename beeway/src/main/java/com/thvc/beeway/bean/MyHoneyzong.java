package com.thvc.beeway.bean;

/**
 * Created by Administrator on 2015/9/29.
 */
public class MyHoneyzong {

    /**
     * data : {"levelKey":{"lvImg":"","lvScore":100,"lvIntro":"新用户注册后获得的封号","lvName":"宅巢蜂"},"levelValue":"lv1"}
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
         * levelKey : {"lvImg":"","lvScore":100,"lvIntro":"新用户注册后获得的封号","lvName":"宅巢蜂"}
         * levelValue : lv1
         */
        private LevelKeyEntity levelKey;
        private String levelValue;

        public void setLevelKey(LevelKeyEntity levelKey) {
            this.levelKey = levelKey;
        }

        public void setLevelValue(String levelValue) {
            this.levelValue = levelValue;
        }

        public LevelKeyEntity getLevelKey() {
            return levelKey;
        }

        public String getLevelValue() {
            return levelValue;
        }

        public static class LevelKeyEntity {
            /**
             * lvImg :
             * lvScore : 100
             * lvIntro : 新用户注册后获得的封号
             * lvName : 宅巢蜂
             */
            private String lvImg;
            private int lvScore;
            private String lvIntro;
            private String lvName;

            public void setLvImg(String lvImg) {
                this.lvImg = lvImg;
            }

            public void setLvScore(int lvScore) {
                this.lvScore = lvScore;
            }

            public void setLvIntro(String lvIntro) {
                this.lvIntro = lvIntro;
            }

            public void setLvName(String lvName) {
                this.lvName = lvName;
            }

            public String getLvImg() {
                return lvImg;
            }

            public int getLvScore() {
                return lvScore;
            }

            public String getLvIntro() {
                return lvIntro;
            }

            public String getLvName() {
                return lvName;
            }
        }
    }
}
