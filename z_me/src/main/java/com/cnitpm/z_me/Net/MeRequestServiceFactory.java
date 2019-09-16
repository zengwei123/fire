package com.cnitpm.z_me.Net;

import android.content.Context;
import android.widget.TextView;

import com.cnitpm.z_base.BaseActivity;
import com.cnitpm.z_common.Custom.Dialog.LottieDialog;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.Model.UserMessage;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.NET.RetrofitServiceManager;
import com.cnitpm.z_common.SharedPreferencesHelper;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.zzhoujay.richtext.RichText;

import java.util.LinkedHashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MeRequestServiceFactory {
    private static MeRequestService meRequestService = RetrofitServiceManager.getInstance().create(MeRequestService.class);
    private static UserMessage userMessage;
    static {
        userMessage= SimpleUtils.getUserMessage();
    }

    /**修改密码**/
    public static void MyPassWord(RequestObserver.RequestObserverNext requestObserverNext,Context context,String OldPassword,String NewPassword,String NewPassword1){
        if (!NewPassword.equals(NewPassword1)){
            SimpleUtils.setToast("两次新密码不一致");
            return;
        }
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("uid",userMessage.getUVAO().getUid()+"");
        linkedHashMap.put("oldpassword",SimpleUtils.toMD5(OldPassword)+"");
        linkedHashMap.put("newpassword",SimpleUtils.toMD5(NewPassword)+"");
        String sign= SimpleUtils.getSign(linkedHashMap);
        LottieDialog.setDialogWindow(context);
        Observable observable = meRequestService.MyPassWord(
                userMessage.getUVAO().getUsername(),
                userMessage.getUVAO().getPassword(),
                userMessage.getUVAO().getUid()+"",
                SimpleUtils.toMD5(OldPassword),
                SimpleUtils.toMD5(NewPassword),
                SimpleUtils.code,sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }


    /**答题记录**/
    public static void MyAnswerRecord(RequestObserver.RequestObserverNext requestObserverNext,Context context,int page){
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("uid",userMessage.getUVAO().getUid()+"");
        linkedHashMap.put("page",page+"");
        String sign= SimpleUtils.getSign(linkedHashMap);
        SimpleUtils.setLog("签名："+sign);
        SimpleUtils.setLog("用户名："+ userMessage.getUVAO().getUsername()+"---"+ userMessage.getUVAO().getPassword()+"---"+userMessage.getUVAO().getUid());
        LottieDialog.setDialogWindow(context);
        Observable observable = meRequestService.MyAnswerRecord(
                userMessage.getUVAO().getUsername(),
                userMessage.getUVAO().getPassword(),
                userMessage.getUVAO().getUid()+"",
                page+"",
                SimpleUtils.code,sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }

    /**错题记录**/
    public static void MyExamError(RequestObserver.RequestObserverNext requestObserverNext,Context context,int page){
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("uid",userMessage.getUVAO().getUid()+"");
        linkedHashMap.put("page",page+"");
        String sign= SimpleUtils.getSign(linkedHashMap);
        SimpleUtils.setLog("签名："+sign);
        SimpleUtils.setLog("用户名："+ userMessage.getUVAO().getUsername()+"---"+ userMessage.getUVAO().getPassword()+"---"+userMessage.getUVAO().getUid());
        LottieDialog.setDialogWindow(context);
        Observable observable = meRequestService.MyExamError(
                userMessage.getUVAO().getUsername(),
                userMessage.getUVAO().getPassword(),
                userMessage.getUVAO().getUid()+"",
                page+"",
                SimpleUtils.code,sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }

    /**题目解析内容**/
    public static void GetShitiExplain(TextView textView, int tid){
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("uid",userMessage.getUVAO().getUid()+"");
        linkedHashMap.put("tid",tid+"");
        String sign= SimpleUtils.getSign(linkedHashMap);
        SimpleUtils.setLog("签名："+sign);
        SimpleUtils.setLog("用户名："+ userMessage.getUVAO().getUsername()+"---"+ userMessage.getUVAO().getPassword()+"---"+userMessage.getUVAO().getUid());
        Observable observable = meRequestService.GetShitiExplain(
                userMessage.getUVAO().getUsername(),
                userMessage.getUVAO().getPassword(),
                userMessage.getUVAO().getUid()+"",
                tid+"",
                SimpleUtils.code,sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState<String>>(){
                    @Override
                    public void onNext(AllDataState<String> o) {
                        super.onNext(o);
                        if (o.getState()==0){
                            RichText.fromHtml("[解析]<br>"+o.getData())
                                    .autoPlay(true) // gif图片是否自动播放
                                    .noImage(false) // 不显示并且不加载图片
                                    .into(textView);
                            SimpleUtils.setLog(o.getData());
                            SimpleUtils.setToast("解析加载成功，下滑查看解析");
                        }else {
                            SimpleUtils.setToast(o.getMessage());
                        }
                    }
                });
    }

    /**错题删除**/
    public static void DelExamErrors(RequestObserver.RequestObserverNext requestObserverNext,Context context, int id){
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("uid",userMessage.getUVAO().getUid()+"");
        linkedHashMap.put("id",id+"");
        String sign= SimpleUtils.getSign(linkedHashMap);
        SimpleUtils.setLog("签名："+sign);
        SimpleUtils.setLog("用户名："+ userMessage.getUVAO().getUsername()+"---"+ userMessage.getUVAO().getPassword()+"---"+userMessage.getUVAO().getUid()+"---"+id);
        LottieDialog.setDialogWindow(context);
        Observable observable = meRequestService.DelExamErrors(
                userMessage.getUVAO().getUsername(),
                userMessage.getUVAO().getPassword(),
                userMessage.getUVAO().getUid()+"",
                id+"",
                SimpleUtils.code,sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState<String>>(requestObserverNext){});
    }
}
