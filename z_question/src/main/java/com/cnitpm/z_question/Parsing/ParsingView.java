package com.cnitpm.z_question.Parsing;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface ParsingView extends BaseView {
    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();
    RecyclerView getParsing_Recycler();
}
