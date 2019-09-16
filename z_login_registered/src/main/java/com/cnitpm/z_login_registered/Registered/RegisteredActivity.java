package com.cnitpm.z_login_registered.Registered;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_login_registered.R;
import com.zzhoujay.richtext.RichText;

import java.util.LinkedHashMap;

@Route(path = "/LR/RegisteredActivity")
public class RegisteredActivity extends MvpActivity<RegisteredPresenter> implements RegisteredView {
    private TextView Include_Title_Text;  //标题
    private ImageView Include_Title_Close;  //关闭按钮

    private EditText Registered_Phone;   //手机号
    private EditText Registered_code_edit;  //图形验证码
    private ImageView Registered_code_image; //图形验证码图片
    private EditText Registered_code;  //手机验证码
    private TextView Registered_code_but;   //获取验证码
    private EditText Registered_Pass;
    private EditText Registered_Pass1;
    private EditText Registered_QQ;   //qq
    private TextView Registered_Submits;
    private TextView Registered_Agreement;// 用户协议

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_layout);
        mvpPresenter.attachView(this);
        getViews();
        mvpPresenter.init();
        RichText.initCacheDir(getActivityContext());
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public Activity getThisActivity() {
        return this;
    }

    @Override
    protected RegisteredPresenter createPresenter() {
        return new RegisteredPresenter();
    }

    @Override
    public void getViews() {
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);
        Registered_Phone=findViewById(R.id.Registered_Phone);
        Registered_code_edit=findViewById(R.id.Registered_code_edit);
        Registered_code_image=findViewById(R.id.Registered_code_image);
        Registered_code=findViewById(R.id.Registered_code);
        Registered_code_but=findViewById(R.id.Registered_code_but);
        Registered_Pass=findViewById(R.id.Registered_Pass);
        Registered_Pass1=findViewById(R.id.Registered_Pass1);
        Registered_QQ=findViewById(R.id.Registered_QQ);
        Registered_Submits=findViewById(R.id.Registered_Submits);
        Registered_Agreement=findViewById(R.id.Registered_Agreement);


        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("username","13085486819");
        linkedHashMap.put("password","123456");
        String sign= SimpleUtils.getSign(linkedHashMap);
        SimpleUtils.setLog("密码："+SimpleUtils.toMD5("123456"));
        SimpleUtils.setLog("测试接口："+sign);


    }

    @Override
    public TextView getInclude_Title_Text() {
        return Include_Title_Text;
    }

    @Override
    public ImageView getInclude_Title_Close() {
        return Include_Title_Close;
    }

    @Override
    public EditText getRegistered_Phone() {
        return Registered_Phone;
    }

    @Override
    public EditText getRegistered_code_edit() {
        return Registered_code_edit;
    }

    @Override
    public ImageView getRegistered_code_image() {
        return Registered_code_image;
    }

    @Override
    public EditText getRegistered_code() {
        return Registered_code;
    }

    @Override
    public TextView getRegistered_code_but() {
        return Registered_code_but;
    }

    @Override
    public EditText getRegistered_Pass() {
        return Registered_Pass;
    }

    @Override
    public EditText getRegistered_Pass1() {
        return Registered_Pass1;
    }

    @Override
    public EditText getRegistered_QQ() {
        return Registered_QQ;
    }

    @Override
    public TextView getRegistered_Submits() {
        return Registered_Submits;
    }

    @Override
    public TextView getRegistered_Agreement() {
        return Registered_Agreement;
    }
}
