package com.thvc.beeway.bean;

import java.io.Serializable;
import java.util.List;

/**
 * com.thvc.beeway.bean
 * 创建日期： 2015/9/29.
 * 版权：天合融创
 * 作者：喻亚龙.
 * 版本号：1.0.
 * 修改历史：
 */
public class PickRecordBean implements Serializable{

    /**
     * data : {"totalPage":1,"count":"9","list":[{"addtime":"1443405331","pickfee":"200.00","owners":"曾文兵","remark":"","id":"11","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442292281","pickfee":"200.00","owners":"曾文兵","remark":"","id":"9","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442240074","pickfee":"200.00","owners":"曾文兵","remark":"","id":"8","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442240073","pickfee":"200.00","owners":"曾文兵","remark":"","id":"6","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442240073","pickfee":"200.00","owners":"曾文兵","remark":"","id":"7","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442240025","pickfee":"200.00","owners":"曾文兵","remark":"","id":"5","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442240024","pickfee":"200.00","owners":"曾文兵","remark":"","id":"3","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442240024","pickfee":"200.00","owners":"曾文兵","remark":"","id":"4","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442239846","pickfee":"200.00","owners":"曾文兵","remark":"","id":"2","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"}],"nowPage":1}
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
         * count : 9
         * list : [{"addtime":"1443405331","pickfee":"200.00","owners":"曾文兵","remark":"","id":"11","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442292281","pickfee":"200.00","owners":"曾文兵","remark":"","id":"9","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442240074","pickfee":"200.00","owners":"曾文兵","remark":"","id":"8","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442240073","pickfee":"200.00","owners":"曾文兵","remark":"","id":"6","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442240073","pickfee":"200.00","owners":"曾文兵","remark":"","id":"7","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442240025","pickfee":"200.00","owners":"曾文兵","remark":"","id":"5","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442240024","pickfee":"200.00","owners":"曾文兵","remark":"","id":"3","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442240024","pickfee":"200.00","owners":"曾文兵","remark":"","id":"4","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"},{"addtime":"1442239846","pickfee":"200.00","owners":"曾文兵","remark":"","id":"2","bankname":"支付宝","cardnumber":"shiyewenzi@126.com","userid":"2ca16ab7e0af4280","status":"1"}]
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
             * addtime : 1443405331
             * pickfee : 200.00
             * owners : 曾文兵
             * remark :
             * id : 11
             * bankname : 支付宝
             * cardnumber : shiyewenzi@126.com
             * userid : 2ca16ab7e0af4280
             * status : 1
             */
            private String addtime;
            private String pickfee;
            private String owners;
            private String remark;
            private String id;
            private String bankname;
            private String cardnumber;
            private String userid;
            private String status;

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public void setPickfee(String pickfee) {
                this.pickfee = pickfee;
            }

            public void setOwners(String owners) {
                this.owners = owners;
            }

            public void setRemark(String remark) {
                this.remark = remark;
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

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAddtime() {
                return addtime;
            }

            public String getPickfee() {
                return pickfee;
            }

            public String getOwners() {
                return owners;
            }

            public String getRemark() {
                return remark;
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

            public String getStatus() {
                return status;
            }
        }
    }
}
