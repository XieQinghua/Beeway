package com.thvc.beeway.bean;

import java.io.Serializable;
import java.util.List;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/8/15.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class RegisterBean implements Serializable {

    /**
     * data : {"_system":"2","code":"1234","im":1,"mobile":"18570322333","active":1,"solevar":"6f265b6b57e07e78","_URL_":["BDC","Account","reg"],"headpic":"Photo/Headpic/member_pic.jpg","userid":152,"accountid":152,"password":"0171b31845c59e7f266779dc18cc54d2","_deviceid":"","system":2,"addtime":1444875169,"letter":"S","nickname":"神秘蜂","_model":"","username":"18570322333"}
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
         * _system : 2
         * code : 1234
         * im : 1
         * mobile : 18570322333
         * active : 1
         * solevar : 6f265b6b57e07e78
         * _URL_ : ["BDC","Account","reg"]
         * headpic : Photo/Headpic/member_pic.jpg
         * userid : 152
         * accountid : 152
         * password : 0171b31845c59e7f266779dc18cc54d2
         * _deviceid :
         * system : 2
         * addtime : 1444875169
         * letter : S
         * nickname : 神秘蜂
         * _model :
         * username : 18570322333
         */
        private String _system;
        private String code;
        private int im;
        private String mobile;
        private int active;
        private String solevar;
        private List<String> _URL_;
        private String headpic;
        private int userid;
        private int accountid;
        private String password;
        private String _deviceid;
        private int system;
        private int addtime;
        private String letter;
        private String nickname;
        private String _model;
        private String username;

        public void set_system(String _system) {
            this._system = _system;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setIm(int im) {
            this.im = im;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public void setSolevar(String solevar) {
            this.solevar = solevar;
        }

        public void set_URL_(List<String> _URL_) {
            this._URL_ = _URL_;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public void setAccountid(int accountid) {
            this.accountid = accountid;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void set_deviceid(String _deviceid) {
            this._deviceid = _deviceid;
        }

        public void setSystem(int system) {
            this.system = system;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void set_model(String _model) {
            this._model = _model;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String get_system() {
            return _system;
        }

        public String getCode() {
            return code;
        }

        public int getIm() {
            return im;
        }

        public String getMobile() {
            return mobile;
        }

        public int getActive() {
            return active;
        }

        public String getSolevar() {
            return solevar;
        }

        public List<String> get_URL_() {
            return _URL_;
        }

        public String getHeadpic() {
            return headpic;
        }

        public int getUserid() {
            return userid;
        }

        public int getAccountid() {
            return accountid;
        }

        public String getPassword() {
            return password;
        }

        public String get_deviceid() {
            return _deviceid;
        }

        public int getSystem() {
            return system;
        }

        public int getAddtime() {
            return addtime;
        }

        public String getLetter() {
            return letter;
        }

        public String getNickname() {
            return nickname;
        }

        public String get_model() {
            return _model;
        }

        public String getUsername() {
            return username;
        }
    }
}
