package com.cnitpm.z_subjects.Subjects;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;
import com.cnitpm.z_common.MainPageJump;

public interface SubjectsView extends BaseView {
    TabLayout getSubjects_TabLayout();
    TextView getSubjects_Title1();
    TextView getHome_Apply_Submit1();
    RecyclerView getSubjects_RecyclerView();
    TextView getSubjects_DayPractice();
    TextView getSubjects_Chapter();
    TextView getSubjects_TrueTopic();
    TextView getSubject_Training();

    void JumpPage(int i);

    AppBarLayout getSubject_AppBar();
    ImageView getModelTitle_Message();
    ImageView getModelTitle_Search();
    SwipeRefreshLayout getSubject_SwipeRefreshLayout();
}
