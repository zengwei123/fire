package com.cnitpm.z_login_registered.Login;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cnitpm.z_base.BaseFragment;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_base.MvpFragment;
import com.cnitpm.z_common.GlideUtil;
import com.cnitpm.z_common.SimpleFragmentAdapter;
import com.cnitpm.z_login_registered.Login.page.DXFragment;
import com.cnitpm.z_login_registered.Login.page.ZHFragment;
import com.cnitpm.z_login_registered.R;

import java.util.ArrayList;
import java.util.List;

public class LoginPresenter extends BasePresenter<LoginView> {
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {

        setTab("账号密码登录",true);
        setTab("短信验证码登录",false);
        mvpView.getLogin_TabLayout().addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    tab.getCustomView().setBackgroundResource(R.drawable.login_tablayout_style);
                    TextView textView=tab.getCustomView().findViewById(R.id.Login_Table_Item_Title);
                    textView.setTextColor(Color.parseColor("#FF4E50"));
                    mvpView.getLogin_ViewPage().setCurrentItem(tab.getPosition());
                }catch (Exception E){}
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                try {
                    tab.getCustomView().setBackgroundResource(R.drawable.login_tablayout_style1);
                    TextView textView=tab.getCustomView().findViewById(R.id.Login_Table_Item_Title);
                    textView.setTextColor(Color.parseColor("#666666"));
                }catch (Exception e){}
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        List<Fragment> baseFragments=new ArrayList<>();
        baseFragments.add(new ZHFragment());
        baseFragments.add(new DXFragment());
        mvpView.getLogin_ViewPage().setAdapter(new ViewPageAdapter(((FragmentActivity)mvpView.getThisActivity()).getSupportFragmentManager(),baseFragments));
        mvpView.getLogin_ViewPage().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mvpView.getLogin_TabLayout().getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mvpView.getLogin_onBack().setOnClickListener(v -> mvpView.getThisActivity().finish());
    }

    @Override
    public void CloseRequest() {

    }

    /**自定义的底部菜单item**/
    private void setTab(String title,boolean b){
        TabLayout.Tab tab =  mvpView.getLogin_TabLayout().newTab();
        View view = LayoutInflater.from(mvpView.getActivityContext()).inflate(R.layout.login_table_select, null);
        TextView tv = view.findViewById(R.id.Login_Table_Item_Title);
        tv.setText(title);
        if (b){
            tv.setTextColor(mvpView.getActivityContext().getResources().getColor(R.color.Main_Tab_Text));
            view.setBackgroundResource(R.drawable.login_tablayout_style);
        }else {
            tv.setTextColor(mvpView.getActivityContext().getResources().getColor(R.color.TextColor1));
            view.setBackgroundResource(R.drawable.login_tablayout_style1);
        }
        tab.setCustomView(view);
        mvpView.getLogin_TabLayout().addTab(tab);
    }
}
