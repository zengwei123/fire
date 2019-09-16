package com.cnitpm.z_homeitem.News;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface NewsView extends BaseView {
    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();
    RecyclerView getNews_Recycler();
    TabLayout getNews_TabLayout();
}
