package com.cnitpm.z_login_registered.Login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_login_registered.R;
import com.zzhoujay.richtext.RichText;

@Route(path = "/Login/LoginActivity")
public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView {
    private TabLayout Login_TabLayout;
    private ViewPager Login_ViewPage;
    private ImageView Login_onBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
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
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void getViews() {
        Login_TabLayout=findViewById(R.id.Login_TabLayout);
        Login_ViewPage=findViewById(R.id.Login_ViewPage);
        Login_onBack=findViewById(R.id.Login_onBack);
    }

    @Override
    public TabLayout getLogin_TabLayout() {
        return Login_TabLayout;
    }

    @Override
    public ViewPager getLogin_ViewPage() {
        return Login_ViewPage;
    }

    @Override
    public ImageView getLogin_onBack() {
        return Login_onBack;
    }
}
