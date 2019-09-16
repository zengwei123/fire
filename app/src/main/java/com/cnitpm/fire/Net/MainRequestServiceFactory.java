package com.cnitpm.fire.Net;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.cnitpm.fire.Download.VersionService;
import com.cnitpm.fire.Main.MainActivity;
import com.cnitpm.fire.Model.MessageContentModel;
import com.cnitpm.fire.Model.VersionModel;
import com.cnitpm.z_base.BaseActivity;
import com.cnitpm.z_common.Custom.Dialog.DialogUtil;
import com.cnitpm.z_common.Custom.Dialog.LottieDialog;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.Model.UserMessage;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.NET.RetrofitServiceManager;
import com.cnitpm.z_common.SimpleUtils;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class MainRequestServiceFactory {
    private static MainRequestService mainRequestService = RetrofitServiceManager.getInstance().create(MainRequestService.class);
    private static UserMessage userMessage;
    static {
        userMessage= SimpleUtils.getUserMessage();
    }

    /**获取会员消息**/
    public static void Mymsg(RequestObserver.RequestObserverNext requestObserverNext,Context context){
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("uid",userMessage.getUVAO().getUid()+"");
        linkedHashMap.put("vipstr",userMessage.getUVAO().getVipstr()+"");
        String sign= SimpleUtils.getSign(linkedHashMap);
        LottieDialog.setDialogWindow(context);
        Observable observable = mainRequestService.Mymsg(
                userMessage.getUVAO().getUsername(),
                userMessage.getUVAO().getPassword(),
                userMessage.getUVAO().getUid()+"",
                userMessage.getUVAO().getVipstr()+"",
                0,
                SimpleUtils.code,sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }
    /**获取会员消息详细内容**/
    public static void MymsgInfo(TextView MessageContent_Title,Context context,TextView MessageContent_Time,TextView MessageContent_Content,int id){
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("uid",userMessage.getUVAO().getUid()+"");
        linkedHashMap.put("id",id+"");
        String sign= SimpleUtils.getSign(linkedHashMap);
        LottieDialog.setDialogWindow(context);
        Observable observable = mainRequestService.MymsgInfo(
                userMessage.getUVAO().getUsername(),
                userMessage.getUVAO().getPassword(),
                userMessage.getUVAO().getUid()+"",
                id+"",
                0,
                SimpleUtils.code,sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState<MessageContentModel>>(){
                    @Override
                    public void onNext(AllDataState<MessageContentModel> o) {
                        super.onNext(o);
                        try {
                            MessageContent_Title.setText(o.getData().getTitle());
                            MessageContent_Time.setText(o.getData().getIntime());
                            MessageContent_Content.setText(o.getData().getContent());
                        }catch (Exception e){ }
                    }
                });

    }
    /**搜索**/
    public static void search(RequestObserver.RequestObserverNext requestObserverNext,Context context,String keyword){
        LottieDialog.setDialogWindow(context);
        Observable observable = mainRequestService.search(
                keyword, SimpleUtils.code);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }

    /**App版本信息接口 及用户信息变更**/
    public static void AppVersion(Context context){
        Observable observable;
        if(userMessage!=null){
            observable = mainRequestService.AppVersion(userMessage.getUVAO().getVipstr(),userMessage.getUVAO().getUid()+"",userMessage.getSf(), SimpleUtils.code);
        }else {
            observable = mainRequestService.AppVersion(null,null,null, SimpleUtils.code);
        }
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState<VersionModel>>(){
                    @Override
                    public void onNext(AllDataState<VersionModel> o) {
                        super.onNext(o);
                        if (o.getData().getUserState().equals("2")){
                            SimpleUtils.setToast("用户信息变更，请重新登录！");
                            SimpleUtils.removeUserMessage();
                        }
                        if (!SimpleUtils.getAppVersion().equals(o.getData().getNewVersion())){
                            new DialogUtil().show("更新","应用更新：\n"+o.getData().getUpdateDescription(),context ,"更新", new DialogUtil.DialogButtonListener() {
                                @Override
                                public void sure() {
                                    Intent i = new Intent(context, VersionService.class);
                                    Bundle bundle=new Bundle();
                                    bundle.putString("url","");
                                    i.putExtra("KEY",bundle);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                        context.startForegroundService(i);
                                    } else {
                                        context.startService(i);
                                    }
                                }
                                @Override
                                public void cancel() {

                                }
                            });
                        }
                    }
                });
    }

    /**文件下载**/
    public static void D(String url,RequestObserver.RequestObserverNext requestObserverNext){
        LottieDialog.setDialogWindow(null);
        Observable observable = mainRequestService.downLoadFile(url);
        observable.subscribeOn(Schedulers.io())
                .subscribe(new RequestObserver<ResponseBody>(requestObserverNext){});
    }

}
