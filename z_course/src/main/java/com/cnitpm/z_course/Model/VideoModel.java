package com.cnitpm.z_course.Model;

public class VideoModel {

    /**
     * ClassTitle : 一级消防工程师培训课堂
     * mediaurl : media.cnitpm.com
     * ptorgq : mp4_20164272026
     * filename :
     * mobno : 1
     * StudyId : 510
     */

    private String ClassTitle;
    private String mediaurl;
    private String ptorgq;
    private String filename;
    private int mobno;
    private int StudyId;

    public String getClassTitle() {
        return ClassTitle;
    }

    public void setClassTitle(String ClassTitle) {
        this.ClassTitle = ClassTitle;
    }

    public String getMediaurl() {
        return mediaurl;
    }

    public void setMediaurl(String mediaurl) {
        this.mediaurl = mediaurl;
    }

    public String getPtorgq() {
        return ptorgq;
    }

    public void setPtorgq(String ptorgq) {
        this.ptorgq = ptorgq;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getMobno() {
        return mobno;
    }

    public void setMobno(int mobno) {
        this.mobno = mobno;
    }

    public int getStudyId() {
        return StudyId;
    }

    public void setStudyId(int StudyId) {
        this.StudyId = StudyId;
    }
}
