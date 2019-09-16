package com.cnitpm.z_daypractice.DayPractice;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_daypractice.R;
import com.zzhoujay.richtext.RichText;

@Route(path = "/Apage/DayPracticeActivity")
public class DayPracticeActivity extends MvpActivity<DayPracticePresenter> implements DayPracticeView {
    private TextView DayPractice_item1;
    private TextView DayPractice_item2;
    private TextView DayPractice_item3;

    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;

    private RecyclerView DayPractice_Recycler;

    //月份itme 懒
    private TextView DayPractice_Year1,DayPractice_Month1;
    private TextView DayPractice_Year2,DayPractice_Month2;
    private TextView DayPractice_Year3,DayPractice_Month3;
    private TextView DayPractice_Year4,DayPractice_Month4;
    private LinearLayout DayPractice_Date1,DayPractice_Date2,DayPractice_Date3,DayPractice_Date4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daypractice_layout);
        mvpPresenter.attachView(this);
        getViews();
        mvpPresenter.init();
        RichText.initCacheDir(getActivityContext());

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
    protected DayPracticePresenter createPresenter() {
        return new DayPracticePresenter();
    }

    @Override
    public void getViews() {
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);

        DayPractice_item1=findViewById(R.id.DayPractice_item1);
        DayPractice_item2=findViewById(R.id.DayPractice_item2);
        DayPractice_item3=findViewById(R.id.DayPractice_item3);

        DayPractice_Recycler=findViewById(R.id.DayPractice_Recycler);


        DayPractice_Year1=findViewById(R.id.DayPractice_Year1);
        DayPractice_Year2=findViewById(R.id.DayPractice_Year2);
        DayPractice_Year3=findViewById(R.id.DayPractice_Year3);
        DayPractice_Year4=findViewById(R.id.DayPractice_Year4);
        DayPractice_Month1=findViewById(R.id.DayPractice_Month1);
        DayPractice_Month2=findViewById(R.id.DayPractice_Month2);
        DayPractice_Month3=findViewById(R.id.DayPractice_Month3);
        DayPractice_Month4=findViewById(R.id.DayPractice_Month4);
        DayPractice_Date1=findViewById(R.id.DayPractice_Date1);
        DayPractice_Date2=findViewById(R.id.DayPractice_Date2);
        DayPractice_Date3=findViewById(R.id.DayPractice_Date3);
        DayPractice_Date4=findViewById(R.id.DayPractice_Date4);
    }

    @Override
    public TextView getInclude_Title_Text() {
        return Include_Title_Text;
    }

    @Override
    public ImageView getInclude_Title_Close() {
        return Include_Title_Close;
    }

    @Override
    public TextView getDayPractice_item1() {
        return DayPractice_item1;
    }

    @Override
    public TextView getDayPractice_item2() {
        return DayPractice_item2;
    }

    @Override
    public TextView getDayPractice_item3() {
        return DayPractice_item3;
    }

    @Override
    public RecyclerView getDayPractice_Recycler() {
        return DayPractice_Recycler;
    }

    @Override
    public TextView getDayPractice_Year1() {
        return DayPractice_Year1;
    }

    @Override
    public TextView getDayPractice_Month1() {
        return DayPractice_Month1;
    }

    @Override
    public TextView getDayPractice_Year2() {
        return DayPractice_Year2;
    }

    @Override
    public TextView getDayPractice_Month2() {
        return DayPractice_Month2;
    }

    @Override
    public TextView getDayPractice_Year3() {
        return DayPractice_Year3;
    }

    @Override
    public TextView getDayPractice_Month3() {
        return DayPractice_Month3;
    }

    @Override
    public TextView getDayPractice_Year4() {
        return DayPractice_Year4;
    }

    @Override
    public TextView getDayPractice_Month4() {
        return DayPractice_Month4;
    }

    @Override
    public LinearLayout getDayPractice_Date1() {
        return DayPractice_Date1;
    }

    @Override
    public LinearLayout getDayPractice_Date2() {
        return DayPractice_Date2;
    }

    @Override
    public LinearLayout getDayPractice_Date3() {
        return DayPractice_Date3;
    }

    @Override
    public LinearLayout getDayPractice_Date4() {
        return DayPractice_Date4;
    }
}
