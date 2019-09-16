package com.cnitpm.z_login_registered.Net;

import android.content.Context;
import android.widget.Toast;

import com.airbnb.lottie.utils.Utils;
import com.cnitpm.z_common.Custom.Dialog.LottieDialog;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.NET.RetrofitServiceManager;
import com.cnitpm.z_common.SimpleUtils;

import java.util.LinkedHashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LRRequestServiceFactory {
    private static LRRequestService lrRequestService = RetrofitServiceManager.getInstance().create(LRRequestService.class);

    /**
     * 获取验证码
     */
    public static void  sendsms(RequestObserver.RequestObserverNext requestObserverNext,Context context,int stype,String mobile){
        LottieDialog.setDialogWindow(context);
        if (!SimpleUtils.IsPhone(mobile)){
            SimpleUtils.setToast("手机号为空或格式错误");
            return;
        }
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("stype",stype+"");
        linkedHashMap.put("mobile",mobile+"");
        String sign= SimpleUtils.getSign(linkedHashMap);
        Observable observable= lrRequestService.sendsms(stype,mobile,SimpleUtils.code,sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }

    /**
     * 短信登录接口
     */
    public static void  Login_MessCode(RequestObserver.RequestObserverNext requestObserverNext, Context context, String tel, String yzcode){
        if (!SimpleUtils.IsPhone(tel)){
            SimpleUtils.setToast("手机号为空或格式错误");
            return;
        }
        if (yzcode==null||yzcode.equals("")){
            SimpleUtils.setToast("验证码为空");
            return;
        }
        LottieDialog.setDialogWindow(context);
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("tel",tel);
        linkedHashMap.put("yzcode",yzcode+"");
        String sign= SimpleUtils.getSign(linkedHashMap);
        Observable observable= lrRequestService.Login_MessCode(tel,yzcode,SimpleUtils.code,sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }

    /**
     * 短信登录接口
     */
    public static void  Login(RequestObserver.RequestObserverNext requestObserverNext, Context context, String Username, String password){
        if (Username==null||Username.equals("")){
            SimpleUtils.setToast("手机号/用户名为空");
            return;
        }
        if (password==null||password.equals("")){
            SimpleUtils.setToast("密码不能为空");
            return;
        }
        LottieDialog.setDialogWindow(context);
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("username",Username);
        linkedHashMap.put("password",SimpleUtils.toMD5(password)+"");
        String sign= SimpleUtils.getSign(linkedHashMap);
        Observable observable= lrRequestService.Login(Username,SimpleUtils.toMD5(password),SimpleUtils.code,sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }



    /**
     * 注册
     */
    public static void  RegUser(RequestObserver.RequestObserverNext requestObserverNext, Context context,
                                String userphone, String userpass,String userpass1,String userqq, String smscode){
        if (!SimpleUtils.IsPhone(userphone)){
            SimpleUtils.setToast("手机号为空或格式错误");
            return;
        }
        if (userpass1==null||userpass1.equals("")||userpass==null||userpass.equals("")){
            SimpleUtils.setToast("密码为空");
            return;
        }else if (!userpass1.equals(userpass)){
            SimpleUtils.setToast("两次密码不一致");
            return;
        }
        if (userqq==null||userqq.equals("")){
            SimpleUtils.setToast("请输入QQ号");
            return;
        }
        if (smscode==null||smscode.equals("")){
            SimpleUtils.setToast("验证码为空");
            return;
        }
        LottieDialog.setDialogWindow(context);
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("userphone",userphone);
        linkedHashMap.put("userpass",SimpleUtils.toMD5(userpass)+"");
        linkedHashMap.put("userqq",userqq+"");
        linkedHashMap.put("smscode",smscode+"");
        String sign= SimpleUtils.getSign(linkedHashMap);
        SimpleUtils.setLog("userphone:"+userphone+"--"+"userpass:"+SimpleUtils.toMD5(userpass)+"--"+"userqq:"+userqq+"--"+"smscode:"+smscode+"--"+sign);

        Observable observable= lrRequestService.RegUser(userphone,SimpleUtils.toMD5(userpass),userqq,smscode,0+"",SimpleUtils.code,sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }

    /**
     * 修改密码
     */
    public static void  PasswordForgot(RequestObserver.RequestObserverNext requestObserverNext, Context context,
                                String userphone, String userpass, String smscode){
        if (userphone==null||userphone.equals("")){
            SimpleUtils.setToast("手机号/用户名为空");
            return;
        }
        if (userpass==null||userpass.equals("")){
            SimpleUtils.setToast("密码为空");
            return;
        }
        if (smscode==null||smscode.equals("")){
            SimpleUtils.setToast("验证码为空");
            return;
        }
        LottieDialog.setDialogWindow(context);
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("userphone",userphone);
        linkedHashMap.put("userpass",SimpleUtils.toMD5(userpass)+"");
        linkedHashMap.put("smscode",smscode+"");
        String sign= SimpleUtils.getSign(linkedHashMap);
        Observable observable= lrRequestService.PasswordForgot(userphone,SimpleUtils.toMD5(userpass),smscode,SimpleUtils.code,sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }
}
