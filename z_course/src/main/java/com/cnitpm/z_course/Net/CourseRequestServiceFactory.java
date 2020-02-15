package com.cnitpm.z_course.Net;

import android.content.Context;
import android.widget.TextView;

import com.cnitpm.z_common.Custom.Dialog.LottieDialog;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.Model.UserMessage;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.NET.RetrofitServiceManager;
import com.cnitpm.z_common.SimpleUtils;
import com.zzhoujay.richtext.RichText;

import java.util.LinkedHashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class CourseRequestServiceFactory {
    private static CourseRequestService courseRequestService = RetrofitServiceManager.getInstance().create(CourseRequestService.class);
    private static UserMessage userMessage;
    static {

    }
    /**视频**/
    public static void shipin(RequestObserver.RequestObserverNext requestObserverNext,int type){
        userMessage= SimpleUtils.getUserMessage();
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("uid",userMessage.getUVAO().getUid()+"");
        linkedHashMap.put("vipstr",userMessage.getUVAO().getVipstr()+"");
        linkedHashMap.put("type",type+"");
        String sign= SimpleUtils.getSign(linkedHashMap);
        SimpleUtils.setLog("签名："+sign);
        SimpleUtils.setLog("用户名："+ userMessage.getUVAO().getUsername()+"---"+
                userMessage.getUVAO().getPassword()+"---"+
                userMessage.getUVAO().getUid()+"---"+userMessage.getUVAO().getVipstr()+"---"+type);
        Observable observable= courseRequestService.shipin(userMessage.getUVAO().getUsername(),
                userMessage.getUVAO().getPassword(),userMessage.getUVAO().getUid()+"",
                userMessage.getUVAO().getVipstr(),type+"",SimpleUtils.code,sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }



    /**视频**/
    public static void mob_play(RequestObserver.RequestObserverNext requestObserverNext, Context context, int type, int vsid, int gq){
        userMessage= SimpleUtils.getUserMessage();
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("uid",userMessage.getUVAO().getUid()+"");
        linkedHashMap.put("vipstr",userMessage.getUVAO().getVipstr()+"");
        linkedHashMap.put("type",type+"");
        linkedHashMap.put("vsid",vsid+"");
        String sign= SimpleUtils.getSign(linkedHashMap);
        SimpleUtils.setLog("签名："+sign);
        SimpleUtils.setLog("用户名："+ userMessage.getUVAO().getUsername()+"---"+
                userMessage.getUVAO().getPassword()+"---"+
                userMessage.getUVAO().getUid()+"---"
                +userMessage.getUVAO().getVipstr()+"---"+type+"---"+vsid);

        LottieDialog.setDialogWindow(context);
        Observable observable= courseRequestService.mob_play(userMessage.getUVAO().getUsername(),
                userMessage.getUVAO().getPassword(),userMessage.getUVAO().getUid()+"",
                userMessage.getUVAO().getVipstr(),vsid+"",gq+"",type+"",SimpleUtils.code,sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }
}
