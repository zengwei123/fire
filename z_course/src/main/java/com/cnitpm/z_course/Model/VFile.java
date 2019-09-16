package com.cnitpm.z_course.Model;

import org.litepal.crud.LitePalSupport;

public class VFile extends LitePalSupport {
    String Mid;   //自己命名的id
    String url;   //下载地址
    String Name;  //名字
    String path;  //路径

    public VFile(String mid, String url, String name, String path) {
        Mid = mid;
        this.url = url;
        Name = name;
        this.path = path;
    }

    public String getMid() {
        return Mid;
    }

    public void setMid(String mid) {
        Mid = mid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
