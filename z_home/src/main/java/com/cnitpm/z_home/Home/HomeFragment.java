package com.cnitpm.z_home.Home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_base.MvpFragment;
import com.cnitpm.z_common.GlideUtil;
import com.cnitpm.z_common.MainPageJump;
import com.cnitpm.z_home.R;

import cn.bingoogolapple.bgabanner.BGABanner;

public class HomeFragment extends MvpFragment<HomePresenter> implements HomeView{
    private BGABanner Home_Fragment_BGABanner;   //轮播图
    private RecyclerView Home_Fragment_RecyclerView_Menu;
    private TextView Home_Apply_Title1;   //考试时间
    private TextView Home_Apply_Submit1;  //报名按钮
    private TabLayout Home_New_TabLayout;
    private ViewPager Home_New_ViewPage;
    private RecyclerView Home_Optimal_Recycler;

    private ImageView ModelTitle_Message;   //会员消息
    private ImageView ModelTitle_Search;   //搜索

    public MainPageJump mainPageJump;

    public TextView Home_More_TextView;    //查看更多

    private TextView Optimal_Consulting;  //立即资讯
    private TextView Optimal_Sign;  //立即报名

    private SwipeRefreshLayout Home_SwipeRefreshLayout;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,null,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter.attachView(this);
        getViews(view);
        mvpPresenter.init();
    }

    @Override
    public Context getActivityContext() {
        return activity;
    }

    @Override
    public Activity getThisActivity() {
        return activity;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void getViews(View view) {
        Home_Fragment_BGABanner=view.findViewById(R.id.Home_Fragment_BGABanner);
        Home_Fragment_RecyclerView_Menu=view.findViewById(R.id.Home_Fragment_RecyclerView_Menu);
        Home_Apply_Title1=view.findViewById(R.id.Home_Apply_Title1);
        Home_Apply_Submit1=view.findViewById(R.id.Home_Apply_Submit1);
        Home_New_TabLayout=view.findViewById(R.id.Home_New_TabLayout);
        Home_New_ViewPage=view.findViewById(R.id.Home_New_ViewPage);
        Home_Optimal_Recycler=view.findViewById(R.id.Home_Optimal_Recycler);

        ModelTitle_Message=view.findViewById(R.id.ModelTitle_Message);
        ModelTitle_Search=view.findViewById(R.id.ModelTitle_Search);

        Home_More_TextView=view.findViewById(R.id.Home_More_TextView);
        Optimal_Consulting=view.findViewById(R.id.Optimal_Consulting);
        Optimal_Sign=view.findViewById(R.id.Optimal_Sign);

        Home_SwipeRefreshLayout=view.findViewById(R.id.Home_SwipeRefreshLayout);
    }

    @Override
    public BGABanner getHome_Fragment_BGABanner() {
        return Home_Fragment_BGABanner;
    }

    @Override
    public RecyclerView getHome_Fragment_RecyclerView_Menu() {
        return Home_Fragment_RecyclerView_Menu;
    }

    @Override
    public TextView getHome_Apply_Title1() {
        return Home_Apply_Title1;
    }

    @Override
    public TextView getHome_Apply_Submit1() {
        return Home_Apply_Submit1;
    }

    @Override
    public TabLayout getHome_New_TabLayout() {
        return Home_New_TabLayout;
    }

    @Override
    public ViewPager getHome_New_ViewPage() {
        return Home_New_ViewPage;
    }

    @Override
    public RecyclerView getHome_Optimal_Recycler() {
        return Home_Optimal_Recycler;
    }

    @Override
    public ImageView getModelTitle_Message() {
        return ModelTitle_Message;
    }

    @Override
    public ImageView getModelTitle_Search() {
        return ModelTitle_Search;
    }

    @Override
    public void PageJump(MainPageJump mainPageJump) {
        this.mainPageJump=mainPageJump;
    }

    @Override
    public void setPageJump(int ii) {

    }

    @Override
    public MainPageJump getMainPageJump() {
        return mainPageJump;
    }

    @Override
    public TextView getHome_More_TextView() {
        return Home_More_TextView;
    }

    @Override
    public TextView getOptimal_Consulting() {
        return Optimal_Consulting;
    }

    @Override
    public TextView getOptimal_Sign() {
        return Optimal_Sign;
    }

    @Override
    public SwipeRefreshLayout getHome_SwipeRefreshLayout() {
        return Home_SwipeRefreshLayout;
    }


}
