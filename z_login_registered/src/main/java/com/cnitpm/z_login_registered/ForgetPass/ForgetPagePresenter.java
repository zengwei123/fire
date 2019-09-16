package com.cnitpm.z_login_registered.ForgetPass;

import android.os.CountDownTimer;
import android.view.View;

import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_login_registered.Net.LRRequestServiceFactory;

import io.reactivex.disposables.Disposable;

public class ForgetPagePresenter extends BasePresenter<ForgetPageView> implements View.OnClickListener {
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("修改密码");
        mvpView.getInclude_Title_Close().setOnClickListener(v -> mvpView.getThisActivity().finish());

        mvpView.getForever_code_but().setOnClickListener(v -> {
            if (mvpView.getForever_code_but().getText().equals("获取验证码"))
                sendsms(3,mvpView.getForever_Phone().getText().toString().trim());
            else
                SimpleUtils.setToast("请勿频繁请求验证码");
        });

        mvpView.getForever_Submits().setOnClickListener(v -> {
            LRRequestServiceFactory.PasswordForgot(new RequestObserver.RequestObserverNext<AllDataState>() {
                @Override
                public void Next(AllDataState o) {
                    if (o.getState()==0){
                        SimpleUtils.setToast("密码修改成功");
                    }else {
                        SimpleUtils.setToast(o.getMessage());
                    }
                }

                @Override
                public void onError() {

                }

                @Override
                public void getDisposable(Disposable d) {

                }
            },mvpView.getActivityContext(),mvpView.getForever_Phone().getText().toString().trim()
                    ,mvpView.getForever_Pass().getText().toString().trim()
                    ,mvpView.getForever_Code().getText().toString().trim());
        });
    }

    @Override
    public void CloseRequest() {

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
            try {
                String value = String.valueOf((int) (millisUntilFinished / 1000));
                mvpView.getForever_code_but().setText(value);
            }catch (Exception e){

            }

        }

        @Override
        public void onFinish() {
            try {
                mvpView.getForever_code_but().setText("获取验证码");
            }catch (Exception e){
            }
        }
    };

    @Override
    public void onClick(View v) {

    }
}
