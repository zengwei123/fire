package com.cnitpm.z_login_registered.Registered;

import android.os.CountDownTimer;
import android.widget.Toast;

import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.GlideUtil;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.Model.UserMessage;
import com.cnitpm.z_common.NET.ConvertersFractory.OutPutJsonConverterFactory;
import com.cnitpm.z_common.NET.ConvertersFractory.StringConverterFactory;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SharedPreferencesHelper;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UserFule;
import com.cnitpm.z_login_registered.Net.LRRequestService;
import com.cnitpm.z_login_registered.Net.LRRequestServiceFactory;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnUrlClickListener;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisteredPresenter extends BasePresenter<RegisteredView> {
    private String imageCode;   //图形验证码的code
    @Override
    public void init() {
        setView();
        getCodeImage();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("用户注册");
        mvpView.getInclude_Title_Close().setOnClickListener(v -> mvpView.getThisActivity().finish());
        mvpView.getRegistered_code_but().setOnClickListener(v -> {
            if (mvpView.getRegistered_code_but().getText().equals("获取验证码"))
                sendsms(6,mvpView.getRegistered_Phone().getText().toString().trim());
            else
                SimpleUtils.setToast("请勿频繁请求验证码");
        });

        mvpView.getRegistered_Submits().setOnClickListener(v -> {
            if (mvpView.getRegistered_code_edit().getText().toString().trim().equals(imageCode)){
                RegUser();
            }else {
                SimpleUtils.setToast("图形验证码错误或为空");
            }
        });

        RichText.fromHtml("注册代表您已同意<font color='#FF4E50'><a style='texte-decoration:'none'' href='https://www.cfeks.com/about/shengming.html'>《消防考试网用户协议》</a></font>")
                .urlClick(url -> {
                    RoutePageActivity.getPageActivity(url);
                    return true;
                })
                .into(mvpView.getRegistered_Agreement());
    }

    @Override
    public void CloseRequest() {

    }


    /**获取图形验证码**/
    private void getCodeImage(){
        retrofit2.Retrofit retrofit=new retrofit2.Retrofit.Builder()
                .baseUrl("https://m.cnitpm.com")  //192.168.43.91  192.168.31.74
                .addConverterFactory(StringConverterFactory.create())       //字符串解析器
                .addConverterFactory(OutPutJsonConverterFactory.create())   //自定义的bean解析器
                .addConverterFactory(GsonConverterFactory.create())        //gson解析器
                .build();
        Call<ResponseBody> putModelCall= retrofit.create(LRRequestService.class).downloadPicFromNet("https://m.cnitpm.com/appcode/IdentifyingCode.aspx");
        putModelCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    GlideUtil.displayImage(mvpView.getThisActivity(),response.body().bytes(),mvpView.getRegistered_code_image());
                    imageCode=response.headers().get("randomcode");
                }catch (Exception e){
                    Toast.makeText(mvpView.getThisActivity(), "图形验证码获取错误,点击刷新", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    private void RegUser(){
        LRRequestServiceFactory.RegUser(new RequestObserver.RequestObserverNext<AllDataState<UserMessage>>() {
            @Override
            public void Next(AllDataState<UserMessage> o) {
                if (o.getState()==0){
                    UserFule.PutUser(o.getData());
                    mvpView.getThisActivity().finish();
                }
                SimpleUtils.setToast(o.getMessage());
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },mvpView.getActivityContext(),mvpView.getRegistered_Phone().getText().toString().trim(),
                mvpView.getRegistered_Pass().getText().toString().trim(),
                mvpView.getRegistered_Pass1().getText().toString().trim(),
                mvpView.getRegistered_QQ().getText().toString().trim(),
                mvpView.getRegistered_code().getText().toString().trim());
    }

    /**发送验证码**/
    private void sendsms(int stype, String mobile){
        LRRequestServiceFactory.sendsms(new RequestObserver.RequestObserverNext<AllDataState>() {
            @Override
            public void Next(AllDataState o) {
                SimpleUtils.setToast(o.getMessage());
                if (o.getState()==0){
                    CountDownTimerStart();
                }
            }
            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },mvpView.getActivityContext(),stype,mobile);
    }
    public void CountDownTimerStart(){
        countDownTimer.start();
    }
    private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            String value = String.valueOf((int) (millisUntilFinished / 1000));
            mvpView.getRegistered_code_but().setText(value);
        }

        @Override
        public void onFinish() {
            mvpView.getRegistered_code_but().setText("获取验证码");
        }
    };
}
