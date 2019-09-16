package com.cnitpm.z_home.Home.Page;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.cnitpm.z_base.BaseView;

public interface NewsView extends BaseView {
    RecyclerView getNews_Page_Recycler();

    int getDateType();
    void setDateType(int i);
}
