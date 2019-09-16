package com.cnitpm.z_login_registered.Registered;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface RegisteredView extends BaseView {
    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();

    EditText getRegistered_Phone();
    EditText getRegistered_code_edit();
    ImageView getRegistered_code_image();
    EditText getRegistered_code();
    TextView getRegistered_code_but();
    EditText getRegistered_Pass();
    EditText getRegistered_Pass1();
    EditText getRegistered_QQ();
    TextView getRegistered_Submits();
    TextView getRegistered_Agreement();

}
