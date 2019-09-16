package com.cnitpm.z_question.Parsing;

import android.content.Context;

import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_question.Model.ParsingModel;
import com.cnitpm.z_question.Net.QuestionRequestServiceFactory;
import com.cnitpm.z_question.R;

import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class ParsingPresenter extends BasePresenter<ParsingView> {
    private   SimpleRecyclerViewAdapter simpleRecyclerViewAdapter;
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("试题解析");
        mvpView.getInclude_Title_Close().setOnClickListener(v -> mvpView.getThisActivity().finish());
        setRecycler(1,mvpView.getActivityContext());
    }

    @Override
    public void CloseRequest() {

    }

    private void setRecycler(int page, Context context){
        QuestionRequestServiceFactory.GetClassList(new RequestObserver.RequestObserverNext<AllDataState<ParsingModel>>() {
            @Override
            public void Next(AllDataState<ParsingModel> o) {
                if (page==1){
                    List<ParsingModel.DatalistBean> datalistBean=o.getData().getDatalist();
                    simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.parsing_recycler_item, mvpView.getActivityContext(), datalistBean, (helper, item) -> {
                        ParsingModel.DatalistBean datalistBean1 = (ParsingModel.DatalistBean) item;
                        helper.setText(R.id.Parsing_Recycler_Item_Title, datalistBean1.getTitle());
                        helper.setText(R.id.Parsing_Recycler_Item_Time,datalistBean1.getIntime());
                    });
                    mvpView.getParsing_Recycler().setAdapter(simpleRecyclerViewAdapter);
                    mvpView.getParsing_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));
                    simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
                        RoutePageActivity.getPageActivity(datalistBean.get(position).getUrl());
                    });
                }else {
                    simpleRecyclerViewAdapter.loadMoreComplete();
                    for (ParsingModel.DatalistBean datalistBean:o.getData().getDatalist()){
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
                }, mvpView.getParsing_Recycler());
                simpleRecyclerViewAdapter.disableLoadMoreIfNotFullPage();
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },context,102,page);
    }
}
