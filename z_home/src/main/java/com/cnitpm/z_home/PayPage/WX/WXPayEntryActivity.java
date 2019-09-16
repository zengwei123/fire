package com.cnitpm.z_home.PayPage.WX;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.utils.Utils;
import com.cnitpm.z_common.NET.RetrofitServiceManager;
import com.cnitpm.z_common.SharedPreferencesHelper;
import com.cnitpm.z_home.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

    }
//    private IWXAPI api;
//    private TextView textView;
//    private ImageView Pay_Image;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pay_successful_layout);
//        textView=findViewById(R.id.pay_successful_text);
//        Pay_Image=findViewById(R.id.Pay_Image);
//
//        api = WXAPIFactory.createWXAPI(this, "wx3732146d5112e69c");
//        api.handleIntent(getIntent(), this);
//        Pay_Image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    @Override
//    public void onReq(BaseReq baseReq) {
//
//    }
//
//    @Override
//    public void onResp(BaseResp baseResp) {
//        String str= (String) new SharedPreferencesHelper(getApplicationContext(),"WX").getSharedPreference("key","null");
//        if(baseResp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
//
//        }
//        switch (baseResp.errCode) {
//            case 0:
//                if(!str.equals("null")){
//                    request(str);
//                }else {
//                    textView.setText("提示：\n" +
//                            "未查询到订单!请联系客服，全国统一服务QQ：941723749");
//                }
//                break;
//            case -1:
//                //失败
//                textView.setText("提示：\n" +
//                        "支付异常!如有问题请联系客服，全国统一服务QQ：941723749");
//                break;
//            case -2:
//                textView.setText("提示：\n" +
//                        "支付失败!如有疑问请联系客服，全国统一服务QQ：941723749");
//                break;
//        }
//    }
//    private void request(String s){
//        LoginModel loginModel= Utils.getLoginModel(getApplicationContext());
//        String pass=Utils.getPass(getApplicationContext());
//        RetrofitRequestService retrofitRequestService = RetrofitServiceManager.getInstance().create(RetrofitRequestService.class);
//        Observable observable= retrofitRequestService.return_url(loginModel.getUVAO().getUsername(),pass,loginModel.getUVAO().getUid()+"",s);
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<PutModel<Pay_return>>() {
//                    private Disposable disposable;  //用来取消订阅
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        disposable=d;
//                    }
//
//                    @Override
//                    public void onNext(PutModel<Pay_return> o) {
//                        if(o.getState()==0){
//                            textView.setText("提示：支付成功！\n订单编号：\n" +s+"\n"+
//                                    "1、如果您是报名培训班，请联系我们的客服人员开通相关权限。培训客服QQ：3007309961\n" +
//                                    "2、如果您是报名试题解析服务，则系统已自动开通服务，请退出重新登录。\n"+
//                                    "3、全国统一客服服务QQ：941723749\n");
//                        }else {
//                            textView.setText("提示：\n" +
//                                    "支付失败!如有疑问请联系客服，全国统一服务QQ：941723749");
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        //请求结束取消订阅 防止内存泄漏
//                        disposable.dispose();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        //请求结束取消订阅 防止内存泄漏
//                        disposable.dispose();
//                    }
//                });
//    }
}
