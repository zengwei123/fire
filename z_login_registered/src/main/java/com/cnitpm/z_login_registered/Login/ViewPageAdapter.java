package com.cnitpm.z_login_registered.Login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class ViewPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    public ViewPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }
    @Override
    public int getCount() {
        return fragments.size();
    }

    //得到每个item
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }


    // 初始化每个页卡选项
    @Override
    public Object instantiateItem(ViewGroup arg0, int arg1) {
        return super.instantiateItem(arg0, arg1);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
