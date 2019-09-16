package com.cnitpm.z_homeitem.Reference;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface ReferenceView extends BaseView {
    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();
    RecyclerView getReference_Recycler();
}
