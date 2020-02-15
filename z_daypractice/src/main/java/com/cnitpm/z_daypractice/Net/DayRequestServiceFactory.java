package com.cnitpm.z_daypractice.Net;

import android.content.Context;
import android.widget.TextView;

import com.cnitpm.z_common.Custom.Dialog.LottieDialog;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.Model.UserMessage;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.NET.RetrofitServiceManager;
import com.cnitpm.z_common.SimpleUtils;

import java.util.LinkedHashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class DayRequestServiceFactory {
    private static DayRequestService dayRequestService = RetrofitServiceManager.getInstance().create(DayRequestService.class);
    private static UserMessage userMessage;
    public static void GetClassList(RequestObserver.RequestObserverNext requestObserverNext,Context context,int kemuX,String Month,int page){
        userMessage= SimpleUtils.getUserMessage();
        LottieDialog.setDialogWindow(context);
        Observable observable;
        if (userMessage!=null){
            observable = dayRequestService.GetClassList(userMessage.getUVAO().getUsername(),
                    userMessage.getUVAO().getPassword(),
                    userMessage.getUVAO().getUid()+"",4+"",27+"",kemuX+"",Month+"",page+"",SimpleUtils.code);
        }else {
            observable = dayRequestService.GetClassList(null,
                    null,
                    null,4+"",27+"",kemuX+"",Month+"",page+"",SimpleUtils.code);
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }
}
