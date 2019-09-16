package com.cnitpm.z_me.DownLoadPage;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;

public interface DownLoadPageView extends BaseView {
    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();

    MyVideoView getDownLoadPage_MyVideoView();
    RecyclerView getDownLoadPage_RecyclerView();

}
