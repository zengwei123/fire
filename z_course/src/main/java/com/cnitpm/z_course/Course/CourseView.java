package com.cnitpm.z_course.Course;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface CourseView  extends BaseView {
    TextView getCourse_item1();
    TextView getCourse_item2();
    TextView getCourse_item3();
    RecyclerView getCourse_Recycler();

    void JumpPage(int i);

    ImageView getModelTitle_Message();
    ImageView getModelTitle_Search();
}
