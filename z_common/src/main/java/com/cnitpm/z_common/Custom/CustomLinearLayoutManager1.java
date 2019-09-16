package com.cnitpm.z_common.Custom;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

public class CustomLinearLayoutManager1 extends GridLayoutManager {
    private boolean isScrollEnabled = true;
    public CustomLinearLayoutManager1(Context context,int i) {
        super(context,i);
    }
    public void setScrollEnabled(boolean isScrollEnabled) {
        this.isScrollEnabled = isScrollEnabled;
    }
    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()"
        return isScrollEnabled && super.canScrollVertically();
    }
}