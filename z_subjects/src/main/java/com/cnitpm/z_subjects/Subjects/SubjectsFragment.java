package com.cnitpm.z_subjects.Subjects;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.MvpFragment;
import com.cnitpm.z_common.MainPageJump;
import com.cnitpm.z_subjects.R;

public class SubjectsFragment extends MvpFragment<SubjectsPresenter> implements SubjectsView {
    private TabLayout Subjects_TabLayout;
    private TextView Subjects_Title1;     //考试时间
    private TextView Home_Apply_Submit1;
    private RecyclerView Subjects_RecyclerView;

    public TextView Subjects_DayPractice;    //每日一练
    public TextView Subjects_Chapter;    //章节练习
    private TextView Subjects_TrueTopic;    //历年真题
    private TextView Subject_Training;    //培训课程

    private AppBarLayout Subject_AppBar;

    private ImageView ModelTitle_Message;   //会员消息
    private ImageView ModelTitle_Search;   //搜索

    private SwipeRefreshLayout Subject_SwipeRefreshLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.subjects_fragment,null,false);
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
    protected SubjectsPresenter createPresenter() {
        return new SubjectsPresenter();
    }

    @Override
    public void getViews(View view) {
        Subjects_TabLayout=view.findViewById(R.id.Subjects_TabLayout);
        Subjects_Title1=view.findViewById(R.id.Subjects_Title1);
        Home_Apply_Submit1=view.findViewById(R.id.Home_Apply_Submit1);
        Subjects_RecyclerView=view.findViewById(R.id.Subjects_RecyclerView);
        Subjects_DayPractice=view.findViewById(R.id.Subjects_DayPractice);
        Subjects_Chapter=view.findViewById(R.id.Subjects_Chapter);
        Subjects_TrueTopic=view.findViewById(R.id.Subjects_TrueTopic);
        Subject_Training=view.findViewById(R.id.Subject_Training);
        Subject_AppBar=view.findViewById(R.id.Subject_AppBar);

        ModelTitle_Message=view.findViewById(R.id.ModelTitle_Message);
        ModelTitle_Search=view.findViewById(R.id.ModelTitle_Search);

        Subject_SwipeRefreshLayout=view.findViewById(R.id.Subject_SwipeRefreshLayout);
    }

    @Override
    public TabLayout getSubjects_TabLayout() {
        return Subjects_TabLayout;
    }

    @Override
    public TextView getSubjects_Title1() {
        return Subjects_Title1;
    }

    @Override
    public TextView getHome_Apply_Submit1() {
        return Home_Apply_Submit1;
    }



    @Override
    public RecyclerView getSubjects_RecyclerView() {
        return Subjects_RecyclerView;
    }

    @Override
    public TextView getSubjects_DayPractice() {
        return Subjects_DayPractice;
    }

    @Override
    public TextView getSubjects_Chapter() {
        return Subjects_Chapter;
    }

    @Override
    public TextView getSubjects_TrueTopic() {
        return Subjects_TrueTopic;
    }

    @Override
    public TextView getSubject_Training() {
        return Subject_Training;
    }

    @Override
    public void JumpPage(int i) {
        mvpPresenter.JumpPage(i);
    }

    @Override
    public AppBarLayout getSubject_AppBar() {
        return Subject_AppBar;
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
    public SwipeRefreshLayout getSubject_SwipeRefreshLayout() {
        return Subject_SwipeRefreshLayout;
    }
}
