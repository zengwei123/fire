package com.cnitpm.z_subjects.JZA;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface JZAView extends BaseView {
    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();
    RecyclerView getJZA_Recycler();
    int getDataType();
    void setDataType(int i);
    TabLayout getJZA_TabLayout();
}
