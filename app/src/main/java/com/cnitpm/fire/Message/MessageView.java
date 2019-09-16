package com.cnitpm.fire.Message;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface MessageView extends BaseView {
    RecyclerView getMessage_Recycler();
    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();
}
