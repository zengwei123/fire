package com.cnitpm.z_daypractice.DayPractice;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapterCallback;
import com.cnitpm.z_daypractice.Model.DayModel;
import com.cnitpm.z_daypractice.Net.DayRequestServiceFactory;
import com.cnitpm.z_daypractice.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class DayPracticePresenter extends BasePresenter<DayPracticeView> implements View.OnClickListener {
    private  String[][] ints;
    private int kemux=115;
    private String string;
    @Override
    public void init() {
        setView();
        Click();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("每日一练");
        mvpView.getDayPractice_item1().setText("技术实务");
        mvpView.getDayPractice_item2().setText("综合能力");
        mvpView.getDayPractice_item3().setText("案例分析");
        ints =SimpleUtils.getMonth(4);
        string=ints[0][0]+"-"+ints[0][1];
        DayRecycler(kemux,string,1);
        setMonth();
        setMonthClick(1);
    }

    @Override
    public void CloseRequest() {

    }

    /**每日一练数据**/
    private void DayRecycler(int kemuX,String Month,int page){
        DayRequestServiceFactory.GetClassList(new RequestObserver.RequestObserverNext<AllDataState<DayModel>>() {
            @Override
            public void Next(AllDataState<DayModel> o) {
                SimpleRecyclerViewAdapter simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.daypractice_recycler_item,
                        mvpView.getActivityContext(), o.getData().getDatalist(), (helper, item) -> {
                    DayModel.DatalistBean dayModel=(DayModel.DatalistBean) item;
                    helper.setText(R.id.DayPractice_recycler_Title,dayModel.getTitle());
                    helper.setText(R.id.DayPractice_Time,dayModel.getIntime());
                });
                mvpView.getDayPractice_Recycler().setAdapter(simpleRecyclerViewAdapter);
                mvpView.getDayPractice_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));

                simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
                    RoutePageActivity.getPageActivity(o.getData().getDatalist().get(position).getUrl());
                });
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },mvpView.getActivityContext(),kemuX,Month,page);
    }
    /**获取月份**/
    private void setMonth(){
        mvpView.getDayPractice_Year1().setText(ints[0][0]+"年");
        mvpView.getDayPractice_Month1().setText(ints[0][1]+"月");
        mvpView.getDayPractice_Year2().setText(ints[1][0]+"年");
        mvpView.getDayPractice_Month2().setText(ints[1][1]+"月");
        mvpView.getDayPractice_Year3().setText(ints[2][0]+"年");
        mvpView.getDayPractice_Month3().setText(ints[2][1]+"月");
        mvpView.getDayPractice_Year4().setText(ints[3][0]+"年");
        mvpView.getDayPractice_Month4().setText(ints[3][1]+"月");
    }
    private void setMonthClick(int iz){
        mvpView.getDayPractice_Year1().setTextColor(Color.parseColor("#999999"));
        mvpView.getDayPractice_Month1().setTextColor(Color.parseColor("#999999"));
        mvpView.getDayPractice_Year2().setTextColor(Color.parseColor("#999999"));
        mvpView.getDayPractice_Month2().setTextColor(Color.parseColor("#999999"));
        mvpView.getDayPractice_Year3().setTextColor(Color.parseColor("#999999"));
        mvpView.getDayPractice_Month3().setTextColor(Color.parseColor("#999999"));
        mvpView.getDayPractice_Year4().setTextColor(Color.parseColor("#999999"));
        mvpView.getDayPractice_Month4().setTextColor(Color.parseColor("#999999"));
        mvpView.getDayPractice_Date1().setBackgroundResource(R.drawable.daypractice_date_style1);
        mvpView.getDayPractice_Date2().setBackgroundResource(R.drawable.daypractice_date_style1);
        mvpView.getDayPractice_Date3().setBackgroundResource(R.drawable.daypractice_date_style1);
        mvpView.getDayPractice_Date4().setBackgroundResource(R.drawable.daypractice_date_style1);
        switch (iz){
            case 1:
                mvpView.getDayPractice_Date1().setBackgroundResource(R.drawable.daypractice_date_style2);
                mvpView.getDayPractice_Year1().setTextColor(Color.parseColor("#FF4E50"));
                mvpView.getDayPractice_Month1().setTextColor(Color.parseColor("#FF4E50"));
                break;
            case 2:
                mvpView.getDayPractice_Date2().setBackgroundResource(R.drawable.daypractice_date_style2);
                mvpView.getDayPractice_Year2().setTextColor(Color.parseColor("#FF4E50"));
                mvpView.getDayPractice_Month2().setTextColor(Color.parseColor("#FF4E50"));
                break;
            case 3:
                mvpView.getDayPractice_Date3().setBackgroundResource(R.drawable.daypractice_date_style2);
                mvpView.getDayPractice_Year3().setTextColor(Color.parseColor("#FF4E50"));
                mvpView.getDayPractice_Month3().setTextColor(Color.parseColor("#FF4E50"));
                break;
            case 4:
                mvpView.getDayPractice_Date4().setBackgroundResource(R.drawable.daypractice_date_style2);
                mvpView.getDayPractice_Year4().setTextColor(Color.parseColor("#FF4E50"));
                mvpView.getDayPractice_Month4().setTextColor(Color.parseColor("#FF4E50"));
                break;
        }

    }
    private void Click(){
        mvpView.getDayPractice_item1().setOnClickListener(this);
        mvpView.getDayPractice_item2().setOnClickListener(this);
        mvpView.getDayPractice_item3().setOnClickListener(this);
        mvpView.getInclude_Title_Close().setOnClickListener(this);
        mvpView.getDayPractice_Date1().setOnClickListener(this);
        mvpView.getDayPractice_Date2().setOnClickListener(this);
        mvpView.getDayPractice_Date3().setOnClickListener(this);
        mvpView.getDayPractice_Date4().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.DayPractice_item1) {
            mvpView.getDayPractice_item1().setTextColor(Color.parseColor("#FF4E50"));
            mvpView.getDayPractice_item2().setTextColor(Color.parseColor("#2b2b2b"));
            mvpView.getDayPractice_item3().setTextColor(Color.parseColor("#2b2b2b"));
            kemux=115;
            DayRecycler(kemux,string,1);
        }else if(i == R.id.DayPractice_item2){
            mvpView.getDayPractice_item1().setTextColor(Color.parseColor("#2b2b2b"));
            mvpView.getDayPractice_item2().setTextColor(Color.parseColor("#FF4E50"));
            mvpView.getDayPractice_item3().setTextColor(Color.parseColor("#2b2b2b"));
            kemux=116;
            DayRecycler(kemux,string,1);
        }else if(i == R.id.DayPractice_item3){
            mvpView.getDayPractice_item1().setTextColor(Color.parseColor("#2b2b2b"));
            mvpView.getDayPractice_item2().setTextColor(Color.parseColor("#2b2b2b"));
            mvpView.getDayPractice_item3().setTextColor(Color.parseColor("#FF4E50"));
            kemux=117;
            DayRecycler(kemux,string,1);
        }else if(i==R.id.Include_Title_Close){
            mvpView.getThisActivity().finish();
        }


        else if (i==R.id.DayPractice_Date1){
            string=ints[0][0]+"-"+ints[0][1];
            setMonthClick(1);
            DayRecycler(kemux,string,1);
        } else if (i==R.id.DayPractice_Date2){
            string=ints[1][0]+"-"+ints[1][1];
            setMonthClick(2);
            DayRecycler(kemux,string,1);
        } else if (i==R.id.DayPractice_Date3){
            string=ints[2][0]+"-"+ints[2][1];
            setMonthClick(3);
            DayRecycler(kemux,string,1);
        } else if (i==R.id.DayPractice_Date4){
            string=ints[3][0]+"-"+ints[3][1];
            setMonthClick(4);
            DayRecycler(kemux,string,1);
        }
    }

    public void setTextColor(String color, TextView textView,String str){
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor(color)), 3, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }
}
