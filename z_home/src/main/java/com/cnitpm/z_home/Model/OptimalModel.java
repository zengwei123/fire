package com.cnitpm.z_home.Model;

import java.util.List;

public class OptimalModel {
    private List<QkBean> qk;
    private List<DkBean> dk;

    public List<QkBean> getQk() {
        return qk;
    }

    public void setQk(List<QkBean> qk) {
        this.qk = qk;
    }

    public List<DkBean> getDk() {
        return dk;
    }

    public void setDk(List<DkBean> dk) {
        this.dk = dk;
    }

    public static class QkBean {
        /**
         * title : 全科真题班
         * des : 实务+案例+综合
         * Price : 388
         * paytype : 6
         */

        private String title;
        private String des;
        private String Price;
        private int paytype;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public int getPaytype() {
            return paytype;
        }

        public void setPaytype(int paytype) {
            this.paytype = paytype;
        }
    }

    public static class DkBean {
        /**
         * title : 单科
         * des : 技术实务
         * Price : 288
         * paytype : 3
         */

        private String title;
        private String des;
        private String Price;
        private int paytype;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public int getPaytype() {
            return paytype;
        }

        public void setPaytype(int paytype) {
            this.paytype = paytype;
        }
    }
//    private String Title;
//    private String Message;
//    private int Price;
//
//    public OptimalModel(String title, String message, int price) {
//        Title = title;
//        Message = message;
//        Price = price;
//    }
//
//    public String getTitle() {
//        return Title;
//    }
//
//    public void setTitle(String title) {
//        Title = title;
//    }
//
//    public String getMessage() {
//        return Message;
//    }
//
//    public void setMessage(String message) {
//        Message = message;
//    }
//
//    public int getPrice() {
//        return Price;
//    }
//
//    public void setPrice(int price) {
//        Price = price;
//    }


}
