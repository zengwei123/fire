package com.cnitpm.fire.Download;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.airbnb.lottie.utils.Utils;
import com.cnitpm.fire.Net.MainRequestServiceFactory;
import com.cnitpm.fire.R;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.SimpleUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

import static android.os.Environment.DIRECTORY_DOWNLOADS;


public class VersionService extends Service {

    private NotificationCompat.Builder builder;
    private NotificationManager mNotificationManager ;
    final String CHANNEL_ID = "channel_id_1";
    final String CHANNEL_NAME = "channel_name_1";
    int notificationId=0;//通知的id

    private String Download= "https://m.cfeks.com/appcode/Download.ashx";
    private Intent intents;
    @Override
    public void onCreate() {
        super.onCreate();
        //提示后台运行下载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_NONE);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(getApplicationContext(),CHANNEL_ID).build();
            startForeground(1, notification);
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        intents=intent;
        mNotificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        setTongzhi(0);
        MainRequestServiceFactory.D(Download, new RequestObserver.RequestObserverNext<ResponseBody>() {
            @Override
            public void Next(ResponseBody o) {
                if(writeResponseBodyToDisk(o)){
                    install();
                    mNotificationManager.cancelAll();   //清除所有通知
                }
            }

            @Override
            public void onError() {
                SimpleUtils.setToast("下载失败");
            }

            @Override
            public void getDisposable(Disposable d) {

            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**提示后台运行后 显示下载通知**/
    private void setTongzhi(int i){
        if(builder!=null){
            builder .setProgress(100, i, false);
        }else {
            builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                    .setSmallIcon(R.mipmap.logoc)//左部图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logoc))
                    .setContentTitle("新版本下载")//上部标题
                    .setContentTitle("下载中...")//上部标题
                    .setOngoing(true)
                    .setAutoCancel(false);//点击通知后自动消失
        }
        mNotificationManager.notify(notificationId, builder.build());
    }

    /**apk安装**/
    private void install() {
        try {
            File apkFile = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).getAbsolutePath()+"/download.apk");
          SimpleUtils.setLog( "开始执行安装: " + apkFile.getName());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //  mvpView.getVersionActivity().showToast("版本大于 N ，开始使用 fileProvider 进行安装");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri contentUri = FileProvider.getUriForFile(VersionService.this, "com.cnitpm.fire.fileprovider", apkFile);
                SimpleUtils.setLog( "正常进行安装1");
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            } else {
                SimpleUtils.setLog( "正常进行安装");
                intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            }
            VersionService.this.startActivity(intent);
            stopService(intents);  //停止后台服务
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**文件保存**/
    private static boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            //判断文件夹是否存在
            File files = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).getAbsolutePath());//跟目录一个文件夹
            if (!files.exists()) {
                files.mkdirs(); //不存在就创建出来
            }
            //创建一个文件
            File futureStudioIconFile = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).getAbsolutePath() + "/download.apk");
            //初始化输入流
            InputStream inputStream = null;
            //初始化输出流
            OutputStream outputStream = null;
            try {
                //设置每次读写的字节
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                //请求返回的字节流
                inputStream = body.byteStream();
                //创建输出流
                outputStream = new FileOutputStream(futureStudioIconFile);
                //进行读取操作
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    //进行写入操作
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }

                //刷新
                outputStream.flush();
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    //关闭输入流
                    inputStream.close();
                }
                if (outputStream != null) {
                    //关闭输出流
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
