package com.cnitpm.z_login_registered.Login.page;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.utils.Utils;
import com.cnitpm.z_base.BaseActivity;
import com.cnitpm.z_base.BaseFragment;
import com.cnitpm.z_common.Custom.Dialog.LottieDialog;
import com.cnitpm.z_common.GlideUtil;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.Model.UserMessage;
import com.cnitpm.z_common.NET.ConvertersFractory.OutPutJsonConverterFactory;
import com.cnitpm.z_common.NET.ConvertersFractory.StringConverterFactory;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.NET.RetrofitServiceManager;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SharedPreferencesHelper;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_login_registered.Net.LRRequestService;
import com.cnitpm.z_login_registered.Net.LRRequestServiceFactory;
import com.cnitpm.z_login_registered.R;

import java.util.LinkedHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class DXFragment extends BaseFragment implements View.OnClickListener {
    private TextView Login_Forget;   //忘记密码
    private TextView Login_Registered;

    private ImageView login_code_image;  //图片
    private String imageCode;  //图形验证码
    private TextView login_code_but; //获取验证码
    private EditText login_Phone;  //手机号
    private TextView login_Submits;   //登录
    private EditText login_code;//验证码
    private EditText login_code_edit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_layout_dx,null,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setView(view);
        click();
        getCodeImage();
    }

    private void LoginRequest(){
        LRRequestServiceFactory.Login_MessCode(new RequestObserver.RequestObserverNext<AllDataState<UserMessage>>() {
            @Override
            public void Next(AllDataState<UserMessage> o) {
                if(o.getState()==0){
                    new SharedPreferencesHelper(getContext(),"User").putBase64("user",o.getData());
                   // RoutePageActivity.getMainPage();
                    getActivity().finish();
                    //((BaseActivity)getActivity()).finishAllActivity();
                }
               SimpleUtils.setToast(o.getMessage());
                LottieDialog.stopDialogView();
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },getContext(),login_Phone.getText().toString().trim(),login_code.getText().toString().trim());
    }

    /**获得图形验证码**/
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
                    GlideUtil.displayImage(activity,response.body().bytes(),login_code_image);
                    imageCode=response.headers().get("randomcode");
                }catch (Exception e){
                    Toast.makeText(activity, "图形验证码获取错误,点击刷新", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public void setView(View view){
        Login_Forget=view.findViewById(R.id.Login_Forget);
        Login_Registered=view.findViewById(R.id.Login_Registered);
        login_code_image=view.findViewById(R.id.login_code_image);
        login_code_but=view.findViewById(R.id.login_code_but);
        login_Phone=view.findViewById(R.id.login_Phone);
        login_Submits=view.findViewById(R.id.login_Submits);
        login_code=view.findViewById(R.id.login_code);
        login_code_edit=view.findViewById(R.id.login_code_edit);



    }

    public void click(){
        Login_Forget.setOnClickListener(this);
        Login_Registered.setOnClickListener(this);
        login_code_but.setOnClickListener(this);
        login_Submits.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.Login_Forget) {
            RoutePageActivity.getForgetPageActivity();
        }else if(i==R.id.Login_Registered){
            RoutePageActivity.getRegisteredActivity();
        }else if(i==R.id.login_code_image){
            getCodeImage();
        }else if (i==R.id.login_code_but){
            if (login_code_but.getText().equals("获取验证码"))
                sendsms(8,login_Phone.getText().toString().trim());
            else
                SimpleUtils.setToast("请勿频繁请求验证码");
        }else if (i==R.id.login_Submits){
            if (imageCode.equals(login_code_edit.getText().toString().trim())){
                LoginRequest();
            }else {
                Toast.makeText(activity, "图形验证码错误", Toast.LENGTH_SHORT).show();
            }
        }
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
        },getContext(),stype,mobile);
    }
    public void CountDownTimerStart(){
        countDownTimer.start();
    }
    private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            String value = String.valueOf((int) (millisUntilFinished / 1000));
            login_code_but.setText(value);
        }

        @Override
        public void onFinish() {
            login_code_but.setText("获取验证码");
        }
    };
}
