package com.cnitpm.z_me.Questions_Record;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface Questions_RecordView extends BaseView {
    RecyclerView getQuestions_Record_RecyclerView();
    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();
}
