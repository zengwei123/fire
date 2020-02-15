package com.cnitpm.z_homeitem.Net;

import android.content.Context;

import com.cnitpm.z_common.Custom.Dialog.LottieDialog;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.Model.UserMessage;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.NET.RetrofitServiceManager;
import com.cnitpm.z_common.SimpleUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class NewsRequestServiceFactory {
    private static NewsRequestService newsRequestService = RetrofitServiceManager.getInstance().create(NewsRequestService.class);
    private static UserMessage userMessage;
    /**考试资讯   备考经验**/
    public static void GetClassList(RequestObserver.RequestObserverNext requestObserverNext,Context context,int type,String type_two,int page){
        userMessage= SimpleUtils.getUserMessage();
        LottieDialog.setDialogWindow(context);
        Observable observable;
        if (userMessage!=null){
            observable = newsRequestService.GetClassList(userMessage.getUVAO().getUsername(),
                    userMessage.getUVAO().getPassword(),
                    userMessage.getUVAO().getUid()+"",type+"",type_two+"",page+"",SimpleUtils.code);
        }else {
            observable = newsRequestService.GetClassList(null,
                    null,
                    null,type+"",type_two+"",page+"",SimpleUtils.code);
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }

}
