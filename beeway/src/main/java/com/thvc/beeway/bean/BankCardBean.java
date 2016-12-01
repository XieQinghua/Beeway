package com.thvc.beeway.bean;

import java.io.Serializable;
import java.util.List;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/24.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class BankCardBean implements Serializable{
    /**
     * data : [{"addtime":"1442240170","owners":"曾文兵","id":"2","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280"},{"addtime":"1442240170","owners":"曾文兵","id":"3","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280"},{"addtime":"1442240171","owners":"曾文兵","id":"4","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280"},{"addtime":"1442240193","owners":"曾文兵","id":"5","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280"},{"addtime":"1442240193","owners":"曾文兵","id":"6","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280"},{"addtime":"1442240194","owners":"曾文兵","id":"7","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280"},{"addtime":"1442240232","owners":"曾文兵","id":"8","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280"},{"addtime":"1442240233","owners":"曾文兵","id":"9","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280"},{"addtime":"1442240233","owners":"曾文兵","id":"10","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280"}]
     * status : 1
     * info :
     */
    private List<DataEntity> data;
    private int status;
    private String info;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DataEntity> getData() {
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
         * addtime : 1442240170
         * owners : 曾文兵
         * id : 2
         * bankname : 支付宝
         * cardnumber : shiyewenzi@126.com
         * userid : 2ca16ab7e0af4280
         */
        private String addtime;
        private String owners;
        private String id;
        private String bankname;
        private String cardnumber;
        private String userid;

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public void setOwners(String owners) {
            this.owners = owners;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

        public void setCardnumber(String cardnumber) {
            this.cardnumber = cardnumber;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getAddtime() {
            return addtime;
        }

        public String getOwners() {
            return owners;
        }

        public String getId() {
            return id;
        }

        public String getBankname() {
            return bankname;
        }

        public String getCardnumber() {
            return cardnumber;
        }

        public String getUserid() {
            return userid;
        }
    }
}
