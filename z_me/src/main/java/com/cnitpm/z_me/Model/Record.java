package com.cnitpm.z_me.Model;

import java.util.List;

public class Record {
    /**
     * Totalcnt : 0
     * TotalPage : 0
     * DataList : []
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

    public class DataListBean{
        private String title;
        private String zscore;
        private String uscore;
        private String intime;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getZscore() {
            return zscore;
        }

        public void setZscore(String zscore) {
            this.zscore = zscore;
        }

        public String getUscore() {
            return uscore;
        }

        public void setUscore(String uscore) {
            this.uscore = uscore;
        }

        public String getIntime() {
            return intime;
        }

        public void setIntime(String intime) {
            this.intime = intime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
