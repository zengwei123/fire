package com.cnitpm.z_question.Question;

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
import com.cnitpm.z_question.R;

public class QuestionFragment extends MvpFragment<QuestionPresenter> implements QuestionView {
    private TextView Subjects_Title1;
    private TabLayout Question_TabLayout;

    private TextView Question_DayQuestion;  //每日一练
    private TextView Question_TrueTopic;    //历年真题
    private TextView Question_Parsing;      //试题解析
    private TextView Question_Simulation;
    private TextView Question_Apply_Submit1;  //报名

    private ImageView ModelTitle_Message;   //会员消息
    private ImageView ModelTitle_Search;   //搜索

    private SwipeRefreshLayout Question_SwipeRefreshLayout;

    private AppBarLayout Question_AppBar;
    private RecyclerView Question_Recycler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.question_fragment,null,false);
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
    protected QuestionPresenter createPresenter() {
        return new QuestionPresenter();
    }

    @Override
    public void getViews(View view) {
        Subjects_Title1=view.findViewById(R.id.Subjects_Title1);
        Question_TabLayout=view.findViewById(R.id.Question_TabLayout);

        Question_DayQuestion=view.findViewById(R.id.Question_DayQuestion);
        Question_TrueTopic=view.findViewById(R.id.Question_TrueTopic);
        Question_Parsing=view.findViewById(R.id.Question_Parsing);
        Question_Simulation=view.findViewById(R.id.Question_Simulation);
        Question_Apply_Submit1=view.findViewById(R.id.Question_Apply_Submit1);

        ModelTitle_Message=view.findViewById(R.id.ModelTitle_Message);
        ModelTitle_Search=view.findViewById(R.id.ModelTitle_Search);

        Question_SwipeRefreshLayout=view.findViewById(R.id.Question_SwipeRefreshLayout);

        Question_AppBar=view.findViewById(R.id.Question_AppBar);
        Question_Recycler=view.findViewById(R.id.Question_Recycler);
    }

    @Override
    public TextView getSubjects_Title1() {
        return Subjects_Title1;
    }

    @Override
    public TabLayout getQuestion_TabLayout() {
        return Question_TabLayout;
    }



    @Override
    public TextView getQuestion_DayQuestion() {
        return Question_DayQuestion;
    }

    @Override
    public TextView getQuestion_TrueTopic() {
        return Question_TrueTopic;
    }

    @Override
    public TextView getQuestion_Parsing() {
        return Question_Parsing;
    }

    @Override
    public TextView getQuestion_Simulation() {
        return Question_Simulation;
    }

    @Override
    public TextView getQuestion_Apply_Submit1() {
        return Question_Apply_Submit1;
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
    public SwipeRefreshLayout getQuestion_SwipeRefreshLayout() {
        return Question_SwipeRefreshLayout;
    }

    @Override
    public RecyclerView getQuestion_Recycler() {
        return Question_Recycler;
    }

    @Override
    public AppBarLayout getQuestion_AppBar() {
        return Question_AppBar;
    }
}
