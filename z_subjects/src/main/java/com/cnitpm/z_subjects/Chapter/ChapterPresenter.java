package com.cnitpm.z_subjects.Chapter;

import android.content.Context;

import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_subjects.Model.SubjectsModel;
import com.cnitpm.z_subjects.Net.SubjectsRequestServiceFactory;
import com.cnitpm.z_subjects.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class ChapterPresenter extends BasePresenter<ChapterView> {
    private SimpleRecyclerViewAdapter simpleRecyclerViewAdapter;
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("章节练习");
        mvpView.getInclude_Title_Close().setOnClickListener(v -> mvpView.getThisActivity().finish());
        setRecycler(1,mvpView.getActivityContext());
    }

    @Override
    public void CloseRequest() {

    }

    private void setRecycler(int page, Context context){
        SubjectsRequestServiceFactory.GetClassList1(new RequestObserver.RequestObserverNext<AllDataState<SubjectsModel>>() {
            @Override
            public void Next(AllDataState<SubjectsModel> o) {
                if (page==1){
                    List<SubjectsModel.DatalistBean> datalistBeans=o.getData().getDatalist();
                    simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.chapter_recycler_layout, mvpView.getActivityContext(), datalistBeans, (helper, item) -> {
                        SubjectsModel.DatalistBean datalistBean= ( SubjectsModel.DatalistBean) item;
                        helper.setText(R.id.Chapter_Recycler_Item_Title,datalistBean.getTitle());
                        helper.setText(R.id.Chapter_Recycler_Item_Time,datalistBean.getIntime());
                    });
                    mvpView.getChapter_Recycler().setAdapter(simpleRecyclerViewAdapter);
                    mvpView.getChapter_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));
                    simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
                        RoutePageActivity.getPageActivity(datalistBeans.get(position).getUrl());
                    });
                }else {
                    simpleRecyclerViewAdapter.loadMoreComplete();
                    for (SubjectsModel.DatalistBean datalistBean:o.getData().getDatalist()){
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
                }, mvpView.getChapter_Recycler());
                simpleRecyclerViewAdapter.disableLoadMoreIfNotFullPage();
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },context,page);
    }
}
