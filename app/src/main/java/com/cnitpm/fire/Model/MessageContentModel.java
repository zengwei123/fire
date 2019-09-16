package com.cnitpm.fire.Model;

import java.util.List;

public class MessageContentModel {
    private String Title;
    private String Content;
    private String Intime;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getIntime() {
        return Intime;
    }

    public void setIntime(String intime) {
        Intime = intime;
    }

    @Override
    public String toString() {
        return "MessageContentModel{" +
                "Title='" + Title + '\'' +
                ", Content='" + Content + '\'' +
                ", Intime='" + Intime + '\'' +
                '}';
    }
}
