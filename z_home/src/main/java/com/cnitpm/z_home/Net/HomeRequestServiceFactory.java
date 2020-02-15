package com.cnitpm.z_home.Net;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.cnitpm.z_common.Custom.Dialog.DialogUtil;
import com.cnitpm.z_common.Custom.Dialog.LottieDialog;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.Model.UserMessage;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.NET.RetrofitServiceManager;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_home.Model.HomeHead;
import com.cnitpm.z_home.Model.P_Return;
import com.cnitpm.z_home.Model.P_WX;
import com.cnitpm.z_home.Model.P_ZFB;
import com.cnitpm.z_home.PayPage.ZFB.AuthResult;
import com.cnitpm.z_home.PayPage.ZFB.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zzhoujay.richtext.RichText;

import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeRequestServiceFactory {
    private static HomeRequestService homeRequestService = RetrofitServiceManager.getInstance().create(HomeRequestService.class);
    private static UserMessage userMessage;
    public static void HomePage(RequestObserver.RequestObserverNext requestObserverNext,TextView textView){
        userMessage= SimpleUtils.getUserMessage();
        Observable observable = homeRequestService.HomePage1("1",SimpleUtils.code);
        Observable observable1 = homeRequestService.HomePage2("2",SimpleUtils.code);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState<HomeHead>>(requestObserverNext){});
        observable1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState<String>>(){
                    @Override
                    public void onNext(AllDataState<String> o) {
                        super.onNext(o);
                        RichText.fromHtml("&nbsp;&nbsp&nbsp;&nbsp;距离考试开始还有：<font color='#FF4E50'>"+o.getData()+"</font> 天").into(textView);
                    }
                });
    }

    public static void HomePage3(RequestObserver.RequestObserverNext requestObserverNext,Context context){
        userMessage= SimpleUtils.getUserMessage();
        LottieDialog.setDialogWindow(context);
        Observable observable = homeRequestService.HomePage3("4",SimpleUtils.code);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState<HomeHead>>(requestObserverNext){});
    }

    /**最近更新 章节练习 考试练习**/
    public static void GetClassList(RequestObserver.RequestObserverNext requestObserverNext,Context context,int type,String type_two,int page){
        userMessage= SimpleUtils.getUserMessage();
        LottieDialog.setDialogWindow(context);
        Observable observable;
        if (userMessage!=null){
            observable = homeRequestService.GetClassList(userMessage.getUVAO().getUsername(),
                    userMessage.getUVAO().getPassword(),
                    userMessage.getUVAO().getUid()+"",type+"",type_two+"",page+"",SimpleUtils.code);
        }else {
            observable = homeRequestService.GetClassList(null,
                    null,
                    null,type+"",type_two+"",page+"",SimpleUtils.code);
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }

    /**推荐专题**/
    public static void GetExamList(RequestObserver.RequestObserverNext requestObserverNext,Context context,int type){
        userMessage= SimpleUtils.getUserMessage();
        LottieDialog.setDialogWindow(context);
        Observable observable;
        if (userMessage!=null){
            observable = homeRequestService.GetExamList(userMessage.getUVAO().getUsername(),
                    userMessage.getUVAO().getPassword(),
                    userMessage.getUVAO().getUid()+"",type+"",SimpleUtils.code);
        }else {
            observable = homeRequestService.GetExamList(null,
                    null,
                    null,type+"",SimpleUtils.code);
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }


    /**支付信息**/
    public static void pay_inform(RequestObserver.RequestObserverNext requestObserverNext,Context context){
        userMessage= SimpleUtils.getUserMessage();
        LottieDialog.setDialogWindow(context);
        Observable observable= homeRequestService.pay_inform(SimpleUtils.code);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState>(requestObserverNext){});
    }

    /**支付结果校验**/
    public static void pay_return_url(String TradeNo,Context context){
        userMessage= SimpleUtils.getUserMessage();
        LottieDialog.setDialogWindow(context);
        Observable observable= homeRequestService.pay_return_url(TradeNo,SimpleUtils.code);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState<P_Return>>(){
                    @Override
                    public void onNext(AllDataState<P_Return> o) {
                        super.onNext(o);
                        if (o.getState()==0){
                            String s=o.getMessage()+"\n\t订单号："
                                    +o.getData().getTradeNo()+"\n\t金额："
                                    +o.getData().getPrice()+"\n\t"+
                                    "1、如果您是报名培训班，请联系我们的客服人员开通相关权限。培训客服QQ：3007309961\n\t" +
                                    "2、如果您是报名试题解析服务，则系统已自动开通服务，请退出重新登录。\n\t"+
                                    "3、全国统一客服服务热线：400-880-6318";
                            new DialogUtil().show("支付结果",s,context,"确定",null);
                        }else {
                            String s="支付失败!如有疑问请联系客服，全国统一服务QQ：941723749";
                            new DialogUtil().show("支付结果",s,context,"确定",null);
                        }
                    }
                });
    }
    /**支付宝支付**/
    private static final int SDK_PAY_FLAG = 1;   //打开支付宝app还是网页支付
    private static final int SDK_AUTH_FLAG = 2;
    private static Context contextz ;
    private static String TradeNo;
    public static void alipay(int type,String yhm,String xm,String uid,Context context){
        userMessage= SimpleUtils.getUserMessage();
        contextz=context;
        LottieDialog.setDialogWindow(context);
        Observable observable= homeRequestService.alipay(type,yhm,xm,"",uid,SimpleUtils.code);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState<P_ZFB>>(){
                    @Override
                    public void onNext(AllDataState<P_ZFB> o) {
                        super.onNext(o);
                        String orderInfo=o.getData().getParms(); /**获得签名**/
                        TradeNo=o.getData().getTradeNo(); /**订单号**/
                        final Runnable payRunnable = () -> {
                            /**调起支付界面**/
                            PayTask alipay = new PayTask((Activity) context);
                            Map<String, String> result = alipay.payV2(orderInfo, true);
                            result.put("TradeNo",o.getData().getTradeNo());
                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;   //参数信息
                            mHandler.sendMessage(msg);
                        };
                        // 必须异步调用
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    }
                });
    }
    private static Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                /**app支付**/
                case SDK_PAY_FLAG: {
                    Map<String, String> result = (Map<String, String>) msg.obj;
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult(result);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    pay_return_url(TradeNo,contextz);
                    break;
                }
                /**网页支付**/
                case SDK_AUTH_FLAG: {
                    Map<String, String> result = (Map<String, String>) msg.obj;
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult(result, true);
                    String resultStatus = authResult.getResultStatus();
                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    pay_return_url(TradeNo,contextz);
                    break;
                }
                default:
                    break;
            }
        };
    };
    /**支付宝支付**/

    /**微信支付**/
    private static IWXAPI api;
    private static PayReq req;
    public static void WxPay(int type,String yhm,String xm,String uid,Context context){
        userMessage= SimpleUtils.getUserMessage();
        api = WXAPIFactory.createWXAPI(context, "wxAPPID",false);
        LottieDialog.setDialogWindow(context);
        Observable observable= homeRequestService.WxPay(type,yhm,xm,"",uid,SimpleUtils.code);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestObserver<AllDataState<P_WX>>(){
                    @Override
                    public void onNext(AllDataState<P_WX> o) {
                        super.onNext(o);
                        req = new PayReq();
                        req.appId = o.getData().getAppid();//APPID
                        req.partnerId = o.getData().getPrepayid();//    商户号
                        req.prepayId = o.getData().getPrepayid();//  预付款ID
                        req.nonceStr = o.getData().getNoncestr();//随机数
                        req.timeStamp = o.getData().getTimeStamp();//时间戳
                        req.packageValue = "Sign=WXPay";//固定值Sign=WXPay
                        req.sign = o.getData().getSign();//签名
                        api.registerApp("d2971743d75dd412b132b7d9b6d62f24");
                        api.sendReq(req);
                    }
                });
    }
}
