package com.cnitpm.z_me.Me;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.MvpFragment;
import com.cnitpm.z_common.MainPageJump;
import com.cnitpm.z_me.R;

public class MeFragment extends MvpFragment<MePresenter> implements MeView{
    private RecyclerView Me_Recycler;
    private TextView Me_Name;  //用户名
    private TextView Me_UserIdentity;//用户身份
    private ImageView Me_Image;

    public MainPageJump mainPageJump;

    private TextView Me_other_item1;
    private TextView Me_other_item2;
    private TextView Me_other_item3;
    private TextView Me_other_item4;

    private boolean isOne=true;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.me_fragment,null,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter.attachView(this);
        getViews(view);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            mvpPresenter.init();
        }
    }

    @Override
    public Context getActivityContext() {
        return activity;
    }

    @Override
    public Activity getThisActivity() {
        return activity;
    }

    @Override
    protected MePresenter createPresenter() {
        return new MePresenter();
    }

    @Override
    public void getViews(View view) {
        Me_Recycler=view.findViewById(R.id.Me_Recycler);
        Me_Name=view.findViewById(R.id.Me_Name);
        Me_UserIdentity=view.findViewById(R.id.Me_UserIdentity);
        Me_Image=view.findViewById(R.id.Me_Image);

        Me_other_item1=view.findViewById(R.id.Me_other_item1);
        Me_other_item2=view.findViewById(R.id.Me_other_item2);
        Me_other_item3=view.findViewById(R.id.Me_other_item3);
        Me_other_item4=view.findViewById(R.id.Me_other_item4);
    }

    @Override
    public RecyclerView getMe_Recycler() {
        return Me_Recycler;
    }

    @Override
    public TextView getMe_Name(){
        return Me_Name;
    }

    @Override
    public TextView getMe_UserIdentity(){
        return Me_UserIdentity;
    }

    @Override
    public ImageView getMe_Image(){
        return Me_Image;
    }

    @Override
    public void PageJump(MainPageJump mainPageJump) {
        this.mainPageJump=mainPageJump;
    }

    @Override
    public TextView getMe_other_item1() {
        return Me_other_item1;
    }

    @Override
    public TextView getMe_other_item2() {
        return Me_other_item2;
    }

    @Override
    public TextView getMe_other_item3() {
        return Me_other_item3;
    }

    @Override
    public TextView getMe_other_item4() {
        return Me_other_item4;
    }

    @Override
    public MainPageJump getMainPageJump() {
        return mainPageJump;
    }

}
