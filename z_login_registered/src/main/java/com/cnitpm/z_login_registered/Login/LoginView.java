package com.cnitpm.z_login_registered.Login;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.cnitpm.z_base.BaseView;

public interface LoginView extends BaseView {
    TabLayout getLogin_TabLayout();
    ViewPager getLogin_ViewPage();
    ImageView getLogin_onBack();
}
