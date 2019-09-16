package com.cnitpm.z_course.Download;

import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTask;
import com.cnitpm.z_common.MyNotificationUtils;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_course.Model.VFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**下载  我旧不信**/
//  /data/data/com.cnitpm.WangKao/files/"+list.get(i).getTitle()+".mp4
public class AnyRunnModule {
    private Context mContext;
    private String mUrl;
    private NotificationCompat.Builder builder;
    private Map<Integer,String> map;
    private List<Integer> integers=new ArrayList<>();
    private int index=0;
    private String title;   //标题
    private int vid;  //视频id
    private boolean gq;  //是否高清
    public AnyRunnModule(Context context,Map<Integer,String> maps,String title,int vid,boolean gq) {
        Aria.download(this).register();
        this.mContext = context;
        this.map=maps;
        this.title=title;
        this.vid=vid;
        this.gq=gq;
        Set<Map.Entry<Integer, String>> set = map.entrySet();
        for (Map.Entry<Integer, String> me : set) {
            integers.add(me.getKey());
        }
        SimpleUtils.setToast("开始下载");
        start(map.get(integers.get(index)),title+"("+integers.get(index)+")");
    }

    @Download.onWait void onWait(DownloadTask task) {
        /**下载等待**/
    }

    @Download.onPre protected void onPre(DownloadTask task) {

    }

    @Download.onTaskStart void taskStart(DownloadTask task) {
        /**下载开始**/
        if (builder==null){
            builder = MyNotificationUtils.showNotificationProgressApkDown(mContext,"视频下载",title+"("+integers.get(index)+")",0);
        }else {
            builder.setContentText(title+"("+integers.get(index)+")");
        }
    }

    @Download.onTaskRunning void running(DownloadTask task) {
        /**下载进度**/
        MyNotificationUtils.setProgress(task.getPercent(),builder);
    }

    @Download.onTaskResume void taskResume(DownloadTask task) {
        /**下载回复**/
        SimpleUtils.setLog("下载555");
    }

    @Download.onTaskStop void taskStop(DownloadTask task) {
        /**下载停止**/
        SimpleUtils.setLog("下载444");
    }

    @Download.onTaskCancel void taskCancel(DownloadTask task) {
        /**下载取消**/
        SimpleUtils.setLog("下载333");
    }

    @Download.onTaskFail void taskFail(DownloadTask task) {
        /**下载失败**/
        SimpleUtils.setLog("下载222");
    }

    @Download.onTaskComplete void taskComplete(DownloadTask task) {
        String mid=null;
        if (gq){
            mid="B"+vid+map.get(integers.get(index));
        }else {
            mid="G"+vid+integers.get(index);
        }
        String name=title+"("+integers.get(index)+")";
        VFile vFile = new VFile(mid,
                map.get(integers.get(index)),
                name,
                mContext.getExternalCacheDir().getPath()+"/video/"+name+".mp4");
        vFile.save();
        /**下载完成**/
        if (index<=integers.size()-2){
            index++;
            start(map.get(integers.get(index)),title+"("+integers.get(index)+")");
        }else {
            MyNotificationUtils.setContentText("下载完成",builder);
            unRegister();
        }
        MyNotificationUtils.setProgress(100,builder);
    }


    void start(String url,String title) {
        mUrl = url;
        Aria.download(this)
                .load(url)
                .addHeader("Accept-Encoding", "gzip, deflate")
                .setFilePath(mContext.getExternalCacheDir().getPath()+"/video/"+title+".mp4")
                .resetState()
                .start();
    }

    void stop() {
        Aria.download(this).load(mUrl).stop();
    }

    void cancel() {
        Aria.download(this).load(mUrl).cancel();
    }

    void unRegister() {
        Aria.download(this).unRegister();
    }
}
