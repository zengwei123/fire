package com.cnitpm.z_common.Custom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class CustomLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;
    public CustomLinearLayoutManager(Context context) {
        super(context);
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