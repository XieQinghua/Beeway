package com.thvc.beeway.bean;

import java.io.Serializable;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/10/16.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class UserLevelBean implements Serializable{

    /**
     * data : {"levelKey":{"lvImg":"","lvScore":500,"lvIntro":"","lvName":"宅巢蜂"},"userScore":"263","levelValue":"lv2","usejifen":"263"}
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
         * levelKey : {"lvImg":"","lvScore":500,"lvIntro":"","lvName":"宅巢蜂"}
         * userScore : 263
         * levelValue : lv2
         * usejifen : 263
         */
        private LevelKeyEntity levelKey;
        private String userScore;
        private String levelValue;
        private String usejifen;

        public void setLevelKey(LevelKeyEntity levelKey) {
            this.levelKey = levelKey;
        }

        public void setUserScore(String userScore) {
            this.userScore = userScore;
        }

        public void setLevelValue(String levelValue) {
            this.levelValue = levelValue;
        }

        public void setUsejifen(String usejifen) {
            this.usejifen = usejifen;
        }

        public LevelKeyEntity getLevelKey() {
            return levelKey;
        }

        public String getUserScore() {
            return userScore;
        }

        public String getLevelValue() {
            return levelValue;
        }

        public String getUsejifen() {
            return usejifen;
        }

        public static class LevelKeyEntity {
            /**
             * lvImg :
             * lvScore : 500
             * lvIntro :
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
