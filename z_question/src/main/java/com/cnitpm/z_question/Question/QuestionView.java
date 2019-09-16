package com.cnitpm.z_question.Question;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface QuestionView extends BaseView {
    TextView getSubjects_Title1();
    TabLayout getQuestion_TabLayout();
    TextView getQuestion_DayQuestion();
    TextView getQuestion_TrueTopic();
    TextView getQuestion_Parsing();
    TextView getQuestion_Simulation();
    TextView getQuestion_Apply_Submit1();

    ImageView getModelTitle_Message();
    ImageView getModelTitle_Search();

    SwipeRefreshLayout getQuestion_SwipeRefreshLayout();

    RecyclerView getQuestion_Recycler();
    AppBarLayout getQuestion_AppBar();

}
