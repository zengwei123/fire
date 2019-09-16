package com.cnitpm.fire.Search;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface SearchView extends BaseView {
    EditText getSearch_EditText();
    RecyclerView getSearch_Recycler();
    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();
}
