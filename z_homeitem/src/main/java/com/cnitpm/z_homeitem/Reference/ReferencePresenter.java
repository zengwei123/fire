package com.cnitpm.z_homeitem.Reference;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapterCallback;
import com.cnitpm.z_homeitem.Model.NewsModel;
import com.cnitpm.z_homeitem.Model.ReferenceModel;
import com.cnitpm.z_homeitem.Net.NewsRequestServiceFactory;
import com.cnitpm.z_homeitem.R;

import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class ReferencePresenter extends BasePresenter<ReferenceView> {
    private SimpleRecyclerViewAdapter simpleRecyclerViewAdapter;
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("备考经验");
        mvpView.getInclude_Title_Close().setOnClickListener(v -> mvpView.getThisActivity().finish());
        setRecycler(1,mvpView.getActivityContext());
    }

    @Override
    public void CloseRequest() {

    }

    private void setRecycler(int page, Context context){
        NewsRequestServiceFactory.GetClassList(new RequestObserver.RequestObserverNext<AllDataState<NewsModel>>() {
            @Override
            public void Next(AllDataState<NewsModel> o) {
                if (page==1){
                    simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.reference_recycler_item, mvpView.getThisActivity(), o.getData().getDatalist(), (helper, item) -> {
                        NewsModel.DatalistBean newsModel=(NewsModel.DatalistBean) item;
                        helper.setText(R.id.News_Recycler_Item_Title,newsModel.getTitle());
                        helper.setText(R.id.News_Recycler_Item_Time,"2019-08-08");
                    });
                    mvpView.getReference_Recycler().setAdapter(simpleRecyclerViewAdapter);
                    mvpView.getReference_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));
                    simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
                        RoutePageActivity.getPageActivity("https://m.cfeks.com"+o.getData().getDatalist().get(position).getUrl());
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
                        setRecycler(pages,null);
                    }else {
                        simpleRecyclerViewAdapter.loadMoreEnd();
                    }
                }, mvpView.getReference_Recycler());
                simpleRecyclerViewAdapter.disableLoadMoreIfNotFullPage();

            }

            @Override
            public void onError() {
                simpleRecyclerViewAdapter.loadMoreFail();

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },context,3,0+"",page);
    }
}
