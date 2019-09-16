package com.cnitpm.z_login_registered.ForgetPass;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface ForgetPageView extends BaseView {
    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();

     EditText getForever_Phone();
     EditText getForever_Code();
     TextView getForever_code_but();
     EditText getForever_Pass();
     TextView getForever_Submits();
}
