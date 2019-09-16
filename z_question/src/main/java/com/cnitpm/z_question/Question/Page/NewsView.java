package com.cnitpm.z_question.Question.Page;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.cnitpm.z_base.BaseView;

public interface NewsView extends BaseView {
    RecyclerView getQuestion_News_Page_Recycler();
    int getDataType();
    void setDataType(int i);


    SwipeRefreshLayout getSwipeRefreshLayout();
    void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout);
}
