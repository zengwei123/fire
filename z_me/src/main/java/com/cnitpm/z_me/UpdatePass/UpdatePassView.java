package com.cnitpm.z_me.UpdatePass;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface UpdatePassView extends BaseView {
    ImageView getInclude_Title_Close();
    TextView getInclude_Title_Text();

    EditText getUpdatePass_Old();
    EditText getUpdatePass_Nwe1();
    EditText getUpdatePass_Nwe2();
    TextView getUpdatePass_Submits();
}
