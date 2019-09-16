package com.cnitpm.z_homeitem.News;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.z_base.BaseActivity;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapterCallback;
import com.cnitpm.z_homeitem.Model.NewsModel;
import com.cnitpm.z_homeitem.Net.NewsRequestServiceFactory;
import com.cnitpm.z_homeitem.R;

import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class NewsPresenter extends BasePresenter<NewsView> {
    private SimpleRecyclerViewAdapter simpleRecyclerViewAdapter;
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("考试资讯");
        mvpView.getInclude_Title_Close().setOnClickListener(v -> mvpView.getThisActivity().finish());
        setRecycler(1,1,mvpView.getThisActivity());
        mvpView.getNews_TabLayout().addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:setRecycler(1,1,mvpView.getThisActivity());break;
                    case 1:setRecycler(2,1,mvpView.getThisActivity());break;
                    case 2:setRecycler(9852,1,mvpView.getThisActivity());break;
                    case 3:setRecycler(9853,1,mvpView.getThisActivity());break;
                    case 4:setRecycler(9854,1,mvpView.getThisActivity());break;
                    case 5:setRecycler(9857,1,mvpView.getThisActivity());break;
                    case 6:setRecycler(9858,1,mvpView.getThisActivity());break;
                    case 7:setRecycler(9855,1,mvpView.getThisActivity());break;
                    case 8:setRecycler(9856,1,mvpView.getThisActivity());break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void CloseRequest() {

    }

    private void setRecycler(int two, int page, Context context){
        NewsRequestServiceFactory.GetClassList(new RequestObserver.RequestObserverNext<AllDataState<NewsModel>>() {
            @Override
            public void Next(AllDataState<NewsModel> o) {
                if (page==1){
                    simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.news_recycler_item, mvpView.getThisActivity(), o.getData().getDatalist(), (helper, item) -> {
                        NewsModel.DatalistBean newsModel=(NewsModel.DatalistBean) item;
                        helper.setText(R.id.News_Recycler_Item_Title,newsModel.getTitle());
                        helper.setText(R.id.News_Recycler_Item_Time,"2019-08-08");
                    });
                    mvpView.getNews_Recycler().setAdapter(simpleRecyclerViewAdapter);
                    mvpView.getNews_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));
                    simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
                        RoutePageActivity.getPageActivity(o.getData().getDatalist().get(position).getUrl());
                    });
                }else {
                    simpleRecyclerViewAdapter.loadMoreComplete();
                    for (NewsModel.DatalistBean datalistBean:o.getData().getDatalist()){
                        simpleRecyclerViewAdapter.addData(datalistBean);
                    }
                }

                /**上拉加载**/
                simpleRecyclerViewAdapter.setOnLoadMoreListener(() -> {
                    if (o.getData().getTotalPage()>page){
                        int pages=page+1;
                        setRecycler(two,pages,null);
                    }else {
                        simpleRecyclerViewAdapter.loadMoreEnd();
                    }
                }, mvpView.getNews_Recycler());
                simpleRecyclerViewAdapter.disableLoadMoreIfNotFullPage();

            }

            @Override
            public void onError() {
                simpleRecyclerViewAdapter.loadMoreFail();

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },context,1,two+"",page);
    }
}
