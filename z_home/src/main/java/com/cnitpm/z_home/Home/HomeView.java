package com.cnitpm.z_home.Home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;
import com.cnitpm.z_common.MainPageJump;

import cn.bingoogolapple.bgabanner.BGABanner;

public interface HomeView extends BaseView {
    BGABanner getHome_Fragment_BGABanner();   //轮播图
    RecyclerView getHome_Fragment_RecyclerView_Menu();   //菜单mune
    TextView getHome_Apply_Title1();  //报名倒计时
    TextView getHome_Apply_Submit1();   //倒计时报名呢按钮
    TabLayout getHome_New_TabLayout();  //资讯的tablayout
    ViewPager getHome_New_ViewPage();
    RecyclerView getHome_Optimal_Recycler();

    ImageView getModelTitle_Message();
    ImageView getModelTitle_Search();

    void PageJump(MainPageJump mainPageJump);
    void setPageJump(int ii);
    MainPageJump getMainPageJump();

    TextView getHome_More_TextView();

    TextView getOptimal_Consulting();
    TextView getOptimal_Sign();

    SwipeRefreshLayout getHome_SwipeRefreshLayout();

}
