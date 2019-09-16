package com.cnitpm.z_daypractice.DayPractice;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;
import com.cnitpm.z_daypractice.R;

public interface DayPracticeView extends BaseView {
    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();

    TextView getDayPractice_item1();
    TextView getDayPractice_item2();
    TextView getDayPractice_item3();

    RecyclerView getDayPractice_Recycler();

    TextView getDayPractice_Year1();
    TextView getDayPractice_Month1();
    TextView getDayPractice_Year2();
    TextView getDayPractice_Month2();
    TextView getDayPractice_Year3();
    TextView getDayPractice_Month3();
    TextView getDayPractice_Year4();
    TextView getDayPractice_Month4();
    LinearLayout getDayPractice_Date1();
    LinearLayout getDayPractice_Date2();
    LinearLayout getDayPractice_Date3();
    LinearLayout getDayPractice_Date4();
}
