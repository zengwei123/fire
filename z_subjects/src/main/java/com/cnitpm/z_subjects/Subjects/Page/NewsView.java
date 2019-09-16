package com.cnitpm.z_subjects.Subjects.Page;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface NewsView extends BaseView {
    RecyclerView getSubjects_New_Fragment_Recycler();
    int getDataType();
    void setDataType(int i);
    SwipeRefreshLayout getSwipeRefreshLayout();
    void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout);

    void setViewPage(ViewPager viewPage);
    ViewPager getViewPage();
}
