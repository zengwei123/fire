package com.cnitpm.z_question.TrueTopic;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;
import com.cnitpm.z_question.R;

public interface TrueTopicView extends BaseView {
    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();
    TextView getTrueTopic_item1();
    TextView getTrueTopic_item2();
    TextView getTrueTopic_item3();
    RecyclerView getTrueTopic_Recycler();
}
