package com.cnitpm.fire.Model;

import java.util.List;

public class MessageModel {

    /**
     * TotalPage : 0
     * Totalcnt : 0
     * DataList : []
     */

    private int TotalPage;
    private int Totalcnt;
    private List<DataList> DataList;

    public int getTotalPage() {
        return TotalPage;
    }

    public void setTotalPage(int TotalPage) {
        this.TotalPage = TotalPage;
    }

    public int getTotalcnt() {
        return Totalcnt;
    }

    public void setTotalcnt(int Totalcnt) {
        this.Totalcnt = Totalcnt;
    }

    public List<DataList> getDataList() {
        return DataList;
    }

    public void setDataList(List<DataList> DataList) {
        this.DataList = DataList;
    }

    public class DataList{
        private int Id;
        private String title;
        private String Msgtype;
        private String intime;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            title = title;
        }

        public String getMsgtype() {
            return Msgtype;
        }

        public void setMsgtype(String msgtype) {
            Msgtype = msgtype;
        }

        public String getIntime() {
            return intime;
        }

        public void setIntime(String intime) {
            intime = intime;
        }
    }
}
