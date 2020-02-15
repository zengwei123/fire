package com.cnitpm.z_subjects.Net;

import android.content.Context;
import android.widget.TextView;

import com.cnitpm.z_common.Custom.Dialog.LottieDialog;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.Model.UserMessage;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.NET.RetrofitServiceManager;
import com.cnitpm.z_common.SimpleUtils;
import com.zzhoujay.richtext.RichText;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SubjectsRequestServiceFactory {
    private static SubjectsRequestService subjectsRequestService = RetrofitServiceManager.getInstance().create(SubjectsRequestService.class);
    private static UserMessage userMessage;
    static {

    }
    /**考试资讯   备考经验**/
    public static void GetClassList(RequestObserver.RequestObserverNext requestObserverNext,Context context,int type,int page){
        userMessage= SimpleUtils.getUserMessage();
        LottieDialog.setDialogWindow(context);
        Observable observable;
        if (userMessage!=null){
            observable = subjectsRequestService.GetClassList(userMessage.getUVAO().getUsername(),
                    userMessage.getUVAO().getPassword(),
                    userMessage.getUVAO().getUid()+"",type+"",page,SimpleUtils.code);
        }else {
            observable = subjectsRequestService.GetClassList(null,
                    null,
                    null,type+"",page,SimpleUtils.code);
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }

    /**考试时间**/
    public static void HomePage(TextView textView){
        userMessage= SimpleUtils.getUserMessage();
        Observable observable = subjectsRequestService.HomePage2("2",SimpleUtils.code);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState<String>>(){
                    @Override
                    public void onNext(AllDataState<String> o) {
                        super.onNext(o);
                        RichText.fromHtml("&nbsp;&nbsp&nbsp;&nbsp;距离考试开始还有：<font color='#FF4E50'>"+o.getData()+"</font> 天").into(textView);
                    }
                });
    }

    /**章节练习**/
    public static void GetClassList1(RequestObserver.RequestObserverNext requestObserverNext,Context context,int page){
        userMessage= SimpleUtils.getUserMessage();
        LottieDialog.setDialogWindow(context);
        Observable observable;
        if (userMessage!=null){
            observable = subjectsRequestService.GetClassList1(userMessage.getUVAO().getUsername(),
                    userMessage.getUVAO().getPassword(),
                    userMessage.getUVAO().getUid()+"",4+"",216+"",page,SimpleUtils.code);
        }else {
            observable = subjectsRequestService.GetClassList1(null,
                    null,
                    null,4+"",216+"",page,SimpleUtils.code);
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }

}
