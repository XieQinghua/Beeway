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
public class MyWalletBean implements Serializable{

    /**
     * data : {"usefee":"1728.00","moneyList":{"totalPage":1,"count":"4","list":[{"val":"200.00","addtype":"1","remark":"通过支付宝充值","userid":"2ca16ab7e0af4280","act":"1","dataid":"","money":"0.00","cuttype":"1","frost":"2","addtime":"1442310179","cardid":"0","tableid":"","addid":"0","id":"138"},{"val":"200.00","addtype":"1","remark":"通过支付宝充值","userid":"2ca16ab7e0af4280","act":"1","dataid":"","money":"0.00","cuttype":"1","frost":"2","addtime":"1442292234","cardid":"0","tableid":"","addid":"0","id":"137"},{"val":"200.00","addtype":"1","remark":"通过支付宝充值","userid":"2ca16ab7e0af4280","act":"1","dataid":"","money":"0.00","cuttype":"1","frost":"2","addtime":"1442239664","cardid":"0","tableid":"","addid":"0","id":"136"},{"val":"200.00","addtype":"1","remark":"测试充值","userid":"2ca16ab7e0af4280","act":"1","dataid":"","money":"0.00","cuttype":"1","frost":"2","addtime":"1442239600","cardid":"0","tableid":"","addid":"0","id":"135"}],"nowPage":1},"now_poornum":"0","can_wantfee":"0.00"}
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
         * usefee : 1728.00
         * moneyList : {"totalPage":1,"count":"4","list":[{"val":"200.00","addtype":"1","remark":"通过支付宝充值","userid":"2ca16ab7e0af4280","act":"1","dataid":"","money":"0.00","cuttype":"1","frost":"2","addtime":"1442310179","cardid":"0","tableid":"","addid":"0","id":"138"},{"val":"200.00","addtype":"1","remark":"通过支付宝充值","userid":"2ca16ab7e0af4280","act":"1","dataid":"","money":"0.00","cuttype":"1","frost":"2","addtime":"1442292234","cardid":"0","tableid":"","addid":"0","id":"137"},{"val":"200.00","addtype":"1","remark":"通过支付宝充值","userid":"2ca16ab7e0af4280","act":"1","dataid":"","money":"0.00","cuttype":"1","frost":"2","addtime":"1442239664","cardid":"0","tableid":"","addid":"0","id":"136"},{"val":"200.00","addtype":"1","remark":"测试充值","userid":"2ca16ab7e0af4280","act":"1","dataid":"","money":"0.00","cuttype":"1","frost":"2","addtime":"1442239600","cardid":"0","tableid":"","addid":"0","id":"135"}],"nowPage":1}
         * now_poornum : 0
         * can_wantfee : 0.00
         */
        private String usefee;
        private MoneyListEntity moneyList;
        private String now_poornum;
        private String can_wantfee;

        public void setUsefee(String usefee) {
            this.usefee = usefee;
        }

        public void setMoneyList(MoneyListEntity moneyList) {
            this.moneyList = moneyList;
        }

        public void setNow_poornum(String now_poornum) {
            this.now_poornum = now_poornum;
        }

        public void setCan_wantfee(String can_wantfee) {
            this.can_wantfee = can_wantfee;
        }

        public String getUsefee() {
            return usefee;
        }

        public MoneyListEntity getMoneyList() {
            return moneyList;
        }

        public String getNow_poornum() {
            return now_poornum;
        }

        public String getCan_wantfee() {
            return can_wantfee;
        }

        public static class MoneyListEntity {
            /**
             * totalPage : 1
             * count : 4
             * list : [{"val":"200.00","addtype":"1","remark":"通过支付宝充值","userid":"2ca16ab7e0af4280","act":"1","dataid":"","money":"0.00","cuttype":"1","frost":"2","addtime":"1442310179","cardid":"0","tableid":"","addid":"0","id":"138"},{"val":"200.00","addtype":"1","remark":"通过支付宝充值","userid":"2ca16ab7e0af4280","act":"1","dataid":"","money":"0.00","cuttype":"1","frost":"2","addtime":"1442292234","cardid":"0","tableid":"","addid":"0","id":"137"},{"val":"200.00","addtype":"1","remark":"通过支付宝充值","userid":"2ca16ab7e0af4280","act":"1","dataid":"","money":"0.00","cuttype":"1","frost":"2","addtime":"1442239664","cardid":"0","tableid":"","addid":"0","id":"136"},{"val":"200.00","addtype":"1","remark":"测试充值","userid":"2ca16ab7e0af4280","act":"1","dataid":"","money":"0.00","cuttype":"1","frost":"2","addtime":"1442239600","cardid":"0","tableid":"","addid":"0","id":"135"}]
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
                 * val : 200.00
                 * addtype : 1
                 * remark : 通过支付宝充值
                 * userid : 2ca16ab7e0af4280
                 * act : 1
                 * dataid :
                 * money : 0.00
                 * cuttype : 1
                 * frost : 2
                 * addtime : 1442310179
                 * cardid : 0
                 * tableid :
                 * addid : 0
                 * id : 138
                 */
                private String val;
                private String addtype;
                private String remark;
                private String userid;
                private String act;
                private String dataid;
                private String money;
                private String cuttype;
                private String frost;
                private String addtime;
                private String cardid;
                private String tableid;
                private String addid;
                private String id;

                public void setVal(String val) {
                    this.val = val;
                }

                public void setAddtype(String addtype) {
                    this.addtype = addtype;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public void setAct(String act) {
                    this.act = act;
                }

                public void setDataid(String dataid) {
                    this.dataid = dataid;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public void setCuttype(String cuttype) {
                    this.cuttype = cuttype;
                }

                public void setFrost(String frost) {
                    this.frost = frost;
                }

                public void setAddtime(String addtime) {
                    this.addtime = addtime;
                }

                public void setCardid(String cardid) {
                    this.cardid = cardid;
                }

                public void setTableid(String tableid) {
                    this.tableid = tableid;
                }

                public void setAddid(String addid) {
                    this.addid = addid;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getVal() {
                    return val;
                }

                public String getAddtype() {
                    return addtype;
                }

                public String getRemark() {
                    return remark;
                }

                public String getUserid() {
                    return userid;
                }

                public String getAct() {
                    return act;
                }

                public String getDataid() {
                    return dataid;
                }

                public String getMoney() {
                    return money;
                }

                public String getCuttype() {
                    return cuttype;
                }

                public String getFrost() {
                    return frost;
                }

                public String getAddtime() {
                    return addtime;
                }

                public String getCardid() {
                    return cardid;
                }

                public String getTableid() {
                    return tableid;
                }

                public String getAddid() {
                    return addid;
                }

                public String getId() {
                    return id;
                }
            }
        }
    }
}
