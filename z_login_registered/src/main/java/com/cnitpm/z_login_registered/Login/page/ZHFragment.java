package com.cnitpm.z_login_registered.Login.page;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.utils.Utils;
import com.cnitpm.z_base.BaseActivity;
import com.cnitpm.z_base.BaseFragment;
import com.cnitpm.z_base.ViewBind;
import com.cnitpm.z_common.Custom.Dialog.LottieDialog;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.Model.UserMessage;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.NET.RetrofitServiceManager;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SharedPreferencesHelper;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_login_registered.Net.LRRequestServiceFactory;
import com.cnitpm.z_login_registered.R;

import java.util.LinkedHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZHFragment extends BaseFragment implements View.OnClickListener{
    private EditText Login_User;
    private EditText Login_Pass;
    private TextView Login_Forget;   //忘记密码
    private TextView Login_Registered;
    private TextView Login_Submit;   //登录

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_layout_zh,null,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
        setView(view);
        click();
    }

    public void setView(View view){
        Login_Forget=view.findViewById(R.id.Login_Forget);
        Login_Registered=view.findViewById(R.id.Login_Registered);
        Login_Pass=view.findViewById(R.id.Login_Pass);
        Login_User=view.findViewById(R.id.Login_User);
        Login_Submit=view.findViewById(R.id.Login_Submit);
    }

    public void click(){
        Login_Forget.setOnClickListener(this);
        Login_Registered.setOnClickListener(this);
        Login_Submit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.Login_Forget) {
            RoutePageActivity.getForgetPageActivity();
        }else if(i==R.id.Login_Registered){
            RoutePageActivity.getRegisteredActivity();
        }else if(i==R.id.Login_Submit){
            LoginRequest();
        }
    }

    private void LoginRequest(){
        LRRequestServiceFactory.Login(new RequestObserver.RequestObserverNext<AllDataState<UserMessage>>() {
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
        },getContext(),Login_User.getText().toString().trim(),Login_Pass.getText().toString().trim());
    }
}
