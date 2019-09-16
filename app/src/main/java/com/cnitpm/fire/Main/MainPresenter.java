package com.cnitpm.fire.Main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.fire.Model.VersionModel;
import com.cnitpm.fire.Net.MainRequestServiceFactory;
import com.cnitpm.fire.R;
import com.cnitpm.z_base.BaseActivity;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_base.MvpFragment;
import com.cnitpm.z_common.Custom.Dialog.DialogUtil;
import com.cnitpm.z_common.Custom.Dialog.LottieDialog;
import com.cnitpm.z_common.GlideUtil;
import com.cnitpm.z_common.MainPageJump;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.Model.UserMessage;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SharedPreferencesHelper;
import com.cnitpm.z_common.SimpleFragmentAdapter;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_course.Course.CourseFragment;
import com.cnitpm.z_home.Home.HomeFragment;
import com.cnitpm.z_home.Home.HomeView;
import com.cnitpm.z_me.Me.MeFragment;
import com.cnitpm.z_question.Question.QuestionFragment;
import com.cnitpm.z_subjects.Subjects.SubjectsFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class MainPresenter extends BasePresenter<MainView> {
    private int tabindex=0;
    private List<MvpFragment> fragments=new ArrayList<>();
    @Override
    public void init() {
        SimpleUtils.getPermissions();
        setView();
        VU();
    }

    @Override
    public void setView() {
        /**主界面的4个模块**/
        HomeFragment homeFragment=new HomeFragment();
        SubjectsFragment subjectsFragment=new SubjectsFragment();
        QuestionFragment questionFragment=new QuestionFragment();
        CourseFragment courseFragment=new CourseFragment();
        MeFragment meFragment=new MeFragment();
        homeFragment.PageJump((i, ii) -> {
            mvpView.getMain_TabLayout().getTabAt(i).select();
            switch (i){
                case 1: subjectsFragment.JumpPage(ii);  break;
            }
        });
        meFragment.PageJump((i, ii) -> {
            mvpView.getMain_TabLayout().getTabAt(i).select();
            switch (i){
                case 3: courseFragment.JumpPage(ii);  break;
            }
        });
        fragments.add(homeFragment);
        fragments.add(subjectsFragment);
        fragments.add(questionFragment);
        fragments.add(courseFragment);
        fragments.add(meFragment);
        SimpleFragmentAdapter simpleFragmentAdapter=new SimpleFragmentAdapter( ((FragmentActivity)mvpView.getThisActivity()).getSupportFragmentManager(),fragments);
        mvpView.getMain_ViewPager().setAdapter(simpleFragmentAdapter);
        /**加载页面数量**/
        mvpView.getMain_ViewPager().setOffscreenPageLimit(5);


        /**tab控制**/
        setTab("首页",R.mipmap.main_home1,true);
        setTab("科目",R.mipmap.main_subjects2,false);
        setTab("题库",R.mipmap.main_library2,false);
        setTab("课程",R.mipmap.main_course2,false);
        setTab("我的",R.mipmap.main_me2,false);
        mvpView.getMain_TabLayout().addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==4||tab.getPosition()==3){
                    UserMessage userMessage= SimpleUtils.getUserMessage();
                    if (userMessage==null){
                        SimpleUtils.setToast("暂未登录，请先登录！");
                        mvpView.getMain_TabLayout().getTabAt(tabindex).select();
                        RoutePageActivity.getLoginActivity();
                        return;
                    }
                }
                TextView textView=tab.getCustomView().findViewById(R.id.Main_TabLayout_Title);
                ImageView imageView=tab.getCustomView().findViewById(R.id.Main_TabLayout_Image);

                textView.setTextColor(mvpView.getActivityContext().getResources().getColor(R.color.Main_Tab_Text));
                /**菜单选中的效果**/
                switch (tab.getPosition()){
                    case 0: GlideUtil.displayImage(mvpView.getThisActivity(),R.mipmap.main_home1,imageView);break;
                    case 1: GlideUtil.displayImage(mvpView.getThisActivity(),R.mipmap.main_subjects1,imageView); break;
                    case 2: GlideUtil.displayImage(mvpView.getThisActivity(),R.mipmap.main_library1,imageView);break;
                    case 3: GlideUtil.displayImage(mvpView.getThisActivity(),R.mipmap.main_course1,imageView);break;
                    case 4: GlideUtil.displayImage(mvpView.getThisActivity(),R.mipmap.main_me1,imageView);break;
                }
                /**跳转到指定fragment**/
                mvpView.getMain_ViewPager().setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabindex=tab.getPosition();
                TextView textView=tab.getCustomView().findViewById(R.id.Main_TabLayout_Title);
                ImageView imageView=tab.getCustomView().findViewById(R.id.Main_TabLayout_Image);
                textView.setTextColor(mvpView.getActivityContext().getResources().getColor(R.color.TextColor1));
                switch (tab.getPosition()){
                    case 0: GlideUtil.displayImage(mvpView.getThisActivity(),R.mipmap.main_home2,imageView);break;
                    case 1: GlideUtil.displayImage(mvpView.getThisActivity(),R.mipmap.main_subjects2,imageView); break;
                    case 2: GlideUtil.displayImage(mvpView.getThisActivity(),R.mipmap.main_library2,imageView); break;
                    case 3: GlideUtil.displayImage(mvpView.getThisActivity(),R.mipmap.main_course2,imageView); break;
                    case 4: GlideUtil.displayImage(mvpView.getThisActivity(),R.mipmap.main_me2,imageView); break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}

        });

    }

    @Override
    public void CloseRequest() {

    }

    /**验证用户名信息 和版本**/
    public void VU(){
        MainRequestServiceFactory.AppVersion(mvpView.getActivityContext());
    }

    /**自定义的底部菜单item**/
    private void setTab(String title,int i,boolean b){
        TabLayout.Tab tab =  mvpView.getMain_TabLayout().newTab();
        View view = LayoutInflater.from(mvpView.getActivityContext()).inflate(R.layout.main_tablayout, null);
        TextView tv = view.findViewById(R.id.Main_TabLayout_Title);
        ImageView iv =view.findViewById(R.id.Main_TabLayout_Image);
        GlideUtil.displayImage(mvpView.getThisActivity(),i,iv);
        tv.setText(title);
        if (b){
            tv.setTextColor(mvpView.getActivityContext().getResources().getColor(R.color.Main_Tab_Text));
        }else {
            tv.setTextColor(mvpView.getActivityContext().getResources().getColor(R.color.TextColor1));
        }
        tab.setCustomView(view);
        mvpView.getMain_TabLayout().addTab(tab);
    }
}
