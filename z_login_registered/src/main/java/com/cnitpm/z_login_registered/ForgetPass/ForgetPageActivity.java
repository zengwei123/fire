package com.cnitpm.z_login_registered.ForgetPass;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_login_registered.R;
import com.zzhoujay.richtext.RichText;

@Route(path = "/LR/ForgetPageActivity")
public class ForgetPageActivity extends MvpActivity<ForgetPagePresenter> implements ForgetPageView {
    private TextView Include_Title_Text;  //标题
    private ImageView Include_Title_Close;  //关闭按钮

    private EditText Forever_Phone;
    private EditText Forever_Code;
    private TextView Forever_code_but;
    private EditText Forever_Pass;
    private TextView Forever_Submits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpage_layout);
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
    protected ForgetPagePresenter createPresenter() {
        return new ForgetPagePresenter();
    }

    @Override
    public void getViews() {
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);

        Forever_Phone=findViewById(R.id.Forever_Phone);
        Forever_Code=findViewById(R.id.Forever_Code);
        Forever_code_but=findViewById(R.id.Forever_code_but);
        Forever_Pass=findViewById(R.id.Forever_Pass);
        Forever_Submits=findViewById(R.id.Forever_Submits);
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
    public EditText getForever_Phone() {
        return Forever_Phone;
    }

    @Override
    public EditText getForever_Code() {
        return Forever_Code;
    }

    @Override
    public TextView getForever_code_but() {
        return Forever_code_but;
    }

    @Override
    public EditText getForever_Pass() {
        return Forever_Pass;
    }

    @Override
    public TextView getForever_Submits() {
        return Forever_Submits;
    }
}
