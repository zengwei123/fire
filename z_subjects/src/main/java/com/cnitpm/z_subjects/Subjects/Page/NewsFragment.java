package com.cnitpm.z_subjects.Subjects.Page;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cnitpm.z_base.MvpFragment;
import com.cnitpm.z_subjects.R;

public class NewsFragment extends MvpFragment<NewsPresenter> implements NewsView {
    private RecyclerView Subjects_New_Fragment_Recycler;
    private int DataType=0;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewPager viewPager;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.subjects_new_fragment,null,false);
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
    protected NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    public void getViews(View view) {
        Subjects_New_Fragment_Recycler=view.findViewById(R.id.Subjects_New_Fragment_Recycler);
    }

    @Override
    public RecyclerView getSubjects_New_Fragment_Recycler() {
        return Subjects_New_Fragment_Recycler;
    }

    @Override
    public int getDataType() {
        return DataType;
    }

    @Override
    public void setDataType(int i) {
        this.DataType=i;
    }

    @Override
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    @Override
    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout=swipeRefreshLayout;
    }

    @Override
    public void setViewPage(ViewPager viewPage) {
        this.viewPager=viewPage;
    }

    @Override
    public ViewPager getViewPage() {
        return viewPager;
    }
}
