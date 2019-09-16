package com.cnitpm.z_home.Home.Page;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_base.BaseView;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapterCallback;
import com.cnitpm.z_home.Model.ExamModel;
import com.cnitpm.z_home.Model.NewsModel;
import com.cnitpm.z_home.Net.HomeRequestServiceFactory;
import com.cnitpm.z_home.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class NewsPresenter extends BasePresenter<NewsView> {
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {


        switch (mvpView.getDateType()){
            case 0:setRecycler_01(1,1);break;
            case 1:setRecycler_01(4,216);break;
            case 2:setRecycler_2(1);break;
            case 3:setRecycler_01(100,0);break;
        }
    }

    @Override
    public void CloseRequest() {

    }

    private void setRecycler_01(int i,int ii){
        HomeRequestServiceFactory.GetClassList(new RequestObserver.RequestObserverNext<AllDataState<NewsModel>>() {
            @Override
            public void Next(AllDataState<NewsModel> o) {
                List<NewsModel.DatalistBean> datalistBeans=new ArrayList<>();
                for (int i=0;i<10;i++){
                    datalistBeans.add(o.getData().getDatalist().get(i));
                }
                SimpleRecyclerViewAdapter simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.news_page_fragment_item,
                        mvpView.getActivityContext(),datalistBeans , (helper, item) -> {
                    NewsModel.DatalistBean datalistBean= (NewsModel.DatalistBean) item;
                    helper.setText(R.id.News_Page_Fragment_item_TextView,datalistBean.getTitle());
                });
                mvpView.getNews_Page_Recycler().setAdapter(simpleRecyclerViewAdapter);
                mvpView.getNews_Page_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager1(true,0,false));
                simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
                    SimpleUtils.setLog(datalistBeans.get(position).getUrl());
                    RoutePageActivity.getPageActivity(datalistBeans.get(position).getUrl());
                });
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },mvpView.getActivityContext(),i,ii+"",1);
    }
    private void setRecycler_2(int i){
        HomeRequestServiceFactory.GetExamList(new RequestObserver.RequestObserverNext<AllDataState<List<ExamModel>>>() {
            @Override
            public void Next(AllDataState<List<ExamModel>> o) {
                List<ExamModel> examModels=new ArrayList<>();
                for (int i=0;i<10;i++){
                    examModels.add(o.getData().get(i));
                }
                SimpleRecyclerViewAdapter simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.news_page_fragment_item,
                        mvpView.getActivityContext(),examModels , (helper, item) -> {
                    ExamModel examModel= (ExamModel) item;
                    helper.setText(R.id.News_Page_Fragment_item_TextView,examModel.getTitle());
                });
                mvpView.getNews_Page_Recycler().setAdapter(simpleRecyclerViewAdapter);
                mvpView.getNews_Page_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager1(true,0,false));
                simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
                    SimpleUtils.setLog(examModels.get(position).getUrl());
                    RoutePageActivity.getPageActivity(examModels.get(position).getUrl());
                });
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },mvpView.getActivityContext(),i);
    }
}
