package com.cnitpm.fire.Main;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.arialyy.aria.core.Aria;
import com.cnitpm.fire.R;
import com.cnitpm.z_base.BaseActivity;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.zzhoujay.richtext.RichText;

@Route(path = "/mains/MainPage")
public class MainActivity extends MvpActivity<MainPresenter> implements MainView {
    private TabLayout Main_TabLayout;
    private ViewPager Main_ViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mvpPresenter.attachView(this);
        getViews();
        mvpPresenter.init();
        RichText.initCacheDir(getActivityContext());

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(getApplication()); // 尽可能早，推荐在Application中初始化

        super.addActivity(this);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void getViews() {
        Main_TabLayout=findViewById(R.id.Main_TabLayout);
        Main_ViewPager=findViewById(R.id.Main_ViewPager);
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public Activity getThisActivity() {
        return this;
    }

    @Override
    public TabLayout getMain_TabLayout() {
        return Main_TabLayout;
    }

    @Override
    public ViewPager getMain_ViewPager() {
        return Main_ViewPager;
    }

    @Override
    public void onBackPressed() {
        OnTwoBack();
    }
}
