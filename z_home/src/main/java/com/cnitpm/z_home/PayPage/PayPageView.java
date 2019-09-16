package com.cnitpm.z_home.PayPage;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface PayPageView extends BaseView {
    RadioButton getPay_zhifubao();
    RadioButton getPay_weixin();
    LinearLayout getPay_zhifubaos();
    LinearLayout getPay_weixins();

    int getpaytype();
    Spinner getPay_Spinner();
    TextView getPay_paytext();
    TextView getPay_services();
    TextView getPay_Price();

    EditText getPay_UserName();
    EditText getPay_Name();
    TextView getPay_Determine();

    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();
}
