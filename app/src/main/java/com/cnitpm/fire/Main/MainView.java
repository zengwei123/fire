package com.cnitpm.fire.Main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TableLayout;

import com.cnitpm.z_base.BaseView;

public interface MainView extends BaseView {
    TabLayout getMain_TabLayout();
    ViewPager getMain_ViewPager();
}
