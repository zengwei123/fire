package com.cnitpm.z_me.Model;

import java.util.List;

public class ErrorTopicModel {

    /**
     * Totalcnt : 218
     * TotalPage : 22
     * DataList : [{"id":286158,"tid":2713890,"tno":1,"tcontent":"建筑高度大于100m的民用建筑，其楼板的耐火极限不应低于(　　)。\r<br>A.1.00h\r<br>B.0.75h\r<br>C.2.00h\r<br>D.1.50h","TrueAnswer":"C","UserAnswer":"D"},{"id":286156,"tid":2713888,"tno":2,"tcontent":"某建筑发生火灾，造成15人死亡，60人重伤，直接财产损失达8000万元，该火灾属于(　　)。\r<br>A.特别重大火灾\r<br>B.重大火灾\r<br>C.较大火灾\r<br>D.一般火灾","TrueAnswer":"B","UserAnswer":"C"},{"id":286155,"tid":2713887,"tno":3,"tcontent":"下列属于不完全燃烧产物的是(　　)。\r<br>A.CO\r<br>B.C02\r<br>C.H20\r<br>D.S02","TrueAnswer":"A","UserAnswer":"C"},{"id":286154,"tid":2713886,"tno":4,"tcontent":"窒息灭火的灭火机理是(　　)。\r<br>A.降低温度\r<br>B.降低燃点\r<br>C.减少可燃物\r<br>D.降低氧浓度","TrueAnswer":"D","UserAnswer":"C"},{"id":286153,"tid":2713885,"tno":5,"tcontent":"木材燃烧属于(　　)。\r<br>A.蒸发燃烧\r<br>B.表面燃烧\r<br>C.分解燃烧\r<br>D.熏烟燃烧","TrueAnswer":"C","UserAnswer":"B"},{"id":286152,"tid":2713884,"tno":6,"tcontent":"若采用冷却灭火，对于汽油燃烧，将其冷却到(　　)之下时，燃烧就可能会中止。\r<br>A.燃点\r<br>B.自燃点\r<br>C.0℃\r<br>D.闪点","TrueAnswer":"D","UserAnswer":"C"},{"id":286150,"tid":2713882,"tno":7,"tcontent":"阴燃是(　　)燃烧的一种燃烧形式。\r<br>A.气体\r<br>B.液体\r<br>C.固体\r<br>D.气体、液体、固体","TrueAnswer":"C","UserAnswer":"B"},{"id":286148,"tid":2713922,"tno":8,"tcontent":"根据国家标准《火灾分类》的规定，火灾分为(　　)\r<br>A.固体物质火灾\r<br>B.液体或可熔化固体物质火灾\r<br>C.气体火灾\r<br>D.金属火灾\r<br>E.带电火灾","TrueAnswer":"A、B、C、D、E","UserAnswer":"A、B、D"}]
     */

    private int Totalcnt;
    private int TotalPage;
    private List<DataListBean> DataList;

    public int getTotalcnt() {
        return Totalcnt;
    }

    public void setTotalcnt(int Totalcnt) {
        this.Totalcnt = Totalcnt;
    }

    public int getTotalPage() {
        return TotalPage;
    }

    public void setTotalPage(int TotalPage) {
        this.TotalPage = TotalPage;
    }

    public List<DataListBean> getDataList() {
        return DataList;
    }

    public void setDataList(List<DataListBean> DataList) {
        this.DataList = DataList;
    }

    public static class DataListBean {
        /**
         * id : 286158
         * tid : 2713890
         * tno : 1
         * tcontent : 建筑高度大于100m的民用建筑，其楼板的耐火极限不应低于(　　)。<br>A.1.00h<br>B.0.75h<br>C.2.00h<br>D.1.50h
         * TrueAnswer : C
         * UserAnswer : D
         */

        private int id;
        private int tid;
        private int tno;
        private String tcontent;
        private String TrueAnswer;
        private String UserAnswer;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public int getTno() {
            return tno;
        }

        public void setTno(int tno) {
            this.tno = tno;
        }

        public String getTcontent() {
            return tcontent;
        }

        public void setTcontent(String tcontent) {
            this.tcontent = tcontent;
        }

        public String getTrueAnswer() {
            return TrueAnswer;
        }

        public void setTrueAnswer(String TrueAnswer) {
            this.TrueAnswer = TrueAnswer;
        }

        public String getUserAnswer() {
            return UserAnswer;
        }

        public void setUserAnswer(String UserAnswer) {
            this.UserAnswer = UserAnswer;
        }
    }
}
