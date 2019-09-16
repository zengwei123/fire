package com.cnitpm.z_webpage.Page;

import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface PageView extends BaseView {
    WebView getPage_WebView();
    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();

    String getUrl();
}
