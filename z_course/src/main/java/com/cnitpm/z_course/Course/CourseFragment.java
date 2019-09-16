package com.cnitpm.z_course.Course;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.MvpFragment;
import com.cnitpm.z_course.Download.AnyRunnModule;
import com.cnitpm.z_course.R;

public class CourseFragment extends MvpFragment<CoursePresenter> implements CourseView {
    private TextView Course_item1;
    private TextView Course_item2;
    private TextView Course_item3;
    private RecyclerView Course_Recycler;

    private ImageView ModelTitle_Message;   //会员消息
    private ImageView ModelTitle_Search;   //搜索
    private boolean isOne=true;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.course_fragment,null,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter.attachView(this);
        getViews(view);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if (isOne){
                mvpPresenter.init();
                isOne=false;
            }
        }
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
    protected CoursePresenter createPresenter() {
        return new CoursePresenter();
    }

    @Override
    public void getViews(View view) {
        Course_item1=view.findViewById(R.id.Course_item1);
        Course_item2=view.findViewById(R.id.Course_item2);
        Course_item3=view.findViewById(R.id.Course_item3);
        Course_Recycler=view.findViewById(R.id.Course_Recycler);

        ModelTitle_Message=view.findViewById(R.id.ModelTitle_Message);
        ModelTitle_Search=view.findViewById(R.id.ModelTitle_Search);
    }

    @Override
    public TextView getCourse_item1() {
        return Course_item1;
    }

    @Override
    public TextView getCourse_item2() {
        return Course_item2;
    }

    @Override
    public TextView getCourse_item3() {
        return Course_item3;
    }

    @Override
    public RecyclerView getCourse_Recycler() {
        return Course_Recycler;
    }

    @Override
    public void JumpPage(int i) {
        mvpPresenter.JumpPage(i);
    }

    @Override
    public ImageView getModelTitle_Message() {
        return ModelTitle_Message;
    }

    @Override
    public ImageView getModelTitle_Search() {
        return ModelTitle_Search;
    }
}
