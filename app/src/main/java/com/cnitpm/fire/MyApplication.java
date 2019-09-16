package com.cnitpm.fire;

import android.app.Application;
import android.graphics.Movie;

import com.arialyy.aria.core.Aria;
import com.cnitpm.z_course.Model.VFile;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;
import org.litepal.LitePalBase;
import org.litepal.LitePalDB;
import org.litepal.crud.LitePalSupport;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        Aria.init(this);
    }
}
