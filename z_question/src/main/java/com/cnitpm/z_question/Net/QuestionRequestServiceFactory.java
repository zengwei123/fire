package com.cnitpm.z_question.Net;

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


public class QuestionRequestServiceFactory {
    private static QuestionRequestService questionRequestService = RetrofitServiceManager.getInstance().create(QuestionRequestService.class);
    private static UserMessage userMessage;
    static {
        userMessage= SimpleUtils.getUserMessage();
    }

    /**考试时间**/
    public static void HomePage(TextView textView){
        Observable observable = questionRequestService.HomePage2("2",SimpleUtils.code);
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


    /**历年真题**/
    public static void GetExamList(RequestObserver.RequestObserverNext requestObserverNext, Context context,int type_two){
        LottieDialog.setDialogWindow(context);
        Observable observable;
        if (userMessage!=null){
            observable = questionRequestService.GetExamList(userMessage.getUVAO().getUsername(),
                    userMessage.getUVAO().getPassword(),
                    userMessage.getUVAO().getUid()+"",2,type_two,SimpleUtils.code);
        }else {
            observable = questionRequestService.GetExamList(null,
                    null,
                    null,2,type_two,SimpleUtils.code);
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }

    /**试题解析 模拟试题**/
    public static void GetClassList(RequestObserver.RequestObserverNext requestObserverNext,Context context,int type_two,int page){
        LottieDialog.setDialogWindow(context);
        Observable observable;
        if (userMessage!=null){
            observable = questionRequestService.GetClassList(userMessage.getUVAO().getUsername(),
                    userMessage.getUVAO().getPassword(),
                    userMessage.getUVAO().getUid()+"",4,type_two,page,SimpleUtils.code);
        }else {
            observable = questionRequestService.GetClassList(null,
                    null,
                    null,4,type_two,page,SimpleUtils.code);
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }
}
