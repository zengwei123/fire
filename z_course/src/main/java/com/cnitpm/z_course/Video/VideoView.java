package com.cnitpm.z_course.Video;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.cnitpm.z_base.BaseView;

public interface VideoView extends BaseView {
    TextView getInclude_Title_Text();
    ImageView getInclude_Title_Close();

    MyVideoView getVideo_MyVideoView();
    RecyclerView getVideo_RecyclerView();
    ImageView getVideo_XiaZai();
    RecyclerView getVideo_Recycler_XiaZai();
    RelativeLayout getVideo_Download_layout();
    TextView getVideo_Download_Text();
     int gettype();
     int getvsid();
     int getgq();
}
