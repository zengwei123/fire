package com.cnitpm.z_subjects.Model;

import java.util.List;

public class SubjectsModel {
    /**
     * TotalPage : 1
     * Totalcnt : 24
     * Datalist : [{"title":"一级注册消防工程师每日一练（2019/8/30）","url":"/Artle/20190830/1917611.html","intime":"2019-08-30"},{"title":"一级注册消防工程师每日一练（2019/8/29）","url":"/Artle/20190829/1914115.html","intime":"2019-08-29"},{"title":"一级注册消防工程师每日一练（2019/8/28）","url":"/Artle/20190828/1911652.html","intime":"2019-08-28"},{"title":"一级注册消防工程师每日一练（2019/8/27）","url":"/Artle/20190827/1909158.html","intime":"2019-08-27"},{"title":"一级注册消防工程师每日一练（2019/8/26）","url":"/Artle/20190826/1909102.html","intime":"2019-08-26"},{"title":"一级注册消防工程师每日一练（2019/8/24）","url":"/Artle/20190824/1909048.html","intime":"2019-08-24"},{"title":"一级注册消防工程师每日一练（2019/8/23）","url":"/Artle/20190823/1908995.html","intime":"2019-08-23"},{"title":"一级注册消防工程师每日一练（2019/8/22）","url":"/Artle/20190822/1908946.html","intime":"2019-08-22"},{"title":"一级注册消防工程师每日一练（2019/8/21）","url":"/Artle/20190821/1908897.html","intime":"2019-08-21"},{"title":"一级注册消防工程师每日一练（2019/8/20）","url":"/Artle/20190820/1908851.html","intime":"2019-08-20"},{"title":"一级注册消防工程师每日一练（2019/8/19）","url":"/Artle/20190819/1908807.html","intime":"2019-08-19"},{"title":"一级注册消防工程师每日一练（2019/8/16）","url":"/Artle/20190816/1908546.html","intime":"2019-08-16"},{"title":"一级注册消防工程师每日一练（2019/8/15）","url":"/Artle/20190815/1903849.html","intime":"2019-08-15"},{"title":"一级注册消防工程师每日一练（2019/8/14）","url":"/Artle/20190814/1903790.html","intime":"2019-08-14"},{"title":"一级注册消防工程师每日一练（2019/8/13）","url":"/Artle/20190813/1899480.html","intime":"2019-08-13"},{"title":"一级注册消防工程师每日一练（2019/8/12）","url":"/Artle/20190812/1896422.html","intime":"2019-08-12"},{"title":"一级注册消防工程师每日一练（2019/8/10）","url":"/Artle/20190810/1895477.html","intime":"2019-08-10"},{"title":"一级注册消防工程师每日一练（2019/8/9）","url":"/Artle/20190809/1891449.html","intime":"2019-08-09"},{"title":"一级注册消防工程师每日一练（2019/8/8）","url":"/Artle/20190808/1889511.html","intime":"2019-08-08"},{"title":"一级注册消防工程师每日一练（2019/8/7）","url":"/Artle/20190807/1888331.html","intime":"2019-08-07"},{"title":"一级注册消防工程师每日一练（2019/8/6）","url":"/Artle/20190806/1886048.html","intime":"2019-08-06"},{"title":"一级注册消防工程师每日一练（2019/8/5）","url":"/Artle/20190805/1881637.html","intime":"2019-08-05"},{"title":"一级注册消防工程师每日一练（2019/8/2）","url":"/Artle/20190802/1878932.html","intime":"2019-08-02"},{"title":"一级注册消防工程师每日一练（2019/8/1）","url":"/Artle/20190801/1876317.html","intime":"2019-08-01"}]
     */

    private int TotalPage;
    private int Totalcnt;
    private List<DatalistBean> Datalist;

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

    public List<DatalistBean> getDatalist() {
        return Datalist;
    }

    public void setDatalist(List<DatalistBean> Datalist) {
        this.Datalist = Datalist;
    }

    public static class DatalistBean {
        /**
         * title : 一级注册消防工程师每日一练（2019/8/30）
         * url : /Artle/20190830/1917611.html
         * intime : 2019-08-30
         */

        private String title;
        private String url;
        private String intime;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIntime() {
            return intime;
        }

        public void setIntime(String intime) {
            this.intime = intime;
        }
    }
}
