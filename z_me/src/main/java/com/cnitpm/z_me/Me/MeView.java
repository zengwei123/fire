package com.cnitpm.z_me.Me;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BaseView;
import com.cnitpm.z_common.MainPageJump;

public interface MeView extends BaseView {
    RecyclerView getMe_Recycler();
    TextView getMe_Name();
    TextView getMe_UserIdentity();
    ImageView getMe_Image();

    void PageJump(MainPageJump mainPageJump);
    MainPageJump getMainPageJump();
    TextView getMe_other_item1();
    TextView getMe_other_item2();
    TextView getMe_other_item3();
    TextView getMe_other_item4();
}
