package com.cnitpm.z_home.Home.Page;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cnitpm.z_base.MvpFragment;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_home.R;

public class NewsFragment extends MvpFragment<NewsPresenter> implements NewsView {
    private RecyclerView News_Page_Recycler;
    private int DataType=0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_page_fragment,null,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter.attachView(this);
        getViews(view);
        mvpPresenter.init();
    }

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    public void getViews(View view) {
        News_Page_Recycler=view.findViewById(R.id.News_Page_Recycler);
    }

    @Override
    public RecyclerView getNews_Page_Recycler() {
        return News_Page_Recycler;
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
    public int getDateType() {
        return DataType;
    }

    @Override
    public void setDateType(int i) {
        this.DataType=i;
    }

}
