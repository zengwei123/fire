package com.cnitpm.z_question.Simulation;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapterCallback;
import com.cnitpm.z_question.Model.ParsingModel;
import com.cnitpm.z_question.Model.SimulationModel;
import com.cnitpm.z_question.Net.QuestionRequestServiceFactory;
import com.cnitpm.z_question.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class SimulationPresenter extends BasePresenter<SimulationView> {
    private   SimpleRecyclerViewAdapter simpleRecyclerViewAdapter;
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("模拟试题");
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
                    simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.simulation_recycler_item, mvpView.getActivityContext(), datalistBean, (helper, item) -> {
                        ParsingModel.DatalistBean datalistBean1 = (ParsingModel.DatalistBean) item;
                        helper.setText(R.id.simulation_recycler_Title, datalistBean1.getTitle());
                        helper.setText(R.id.simulation_recycler_Time,datalistBean1.getIntime());
                    });
                    mvpView.getSimulation_Recycler().setAdapter(simpleRecyclerViewAdapter);
                    mvpView.getSimulation_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));
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
                }, mvpView.getSimulation_Recycler());
                simpleRecyclerViewAdapter.disableLoadMoreIfNotFullPage();
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },context,45,page);
    }
}
