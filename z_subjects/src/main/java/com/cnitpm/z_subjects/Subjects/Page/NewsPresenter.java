package com.cnitpm.z_subjects.Subjects.Page;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_base.BaseView;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapterCallback;
import com.cnitpm.z_subjects.Model.SubjectsModel;
import com.cnitpm.z_subjects.Net.SubjectsRequestServiceFactory;
import com.cnitpm.z_subjects.R;

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
        setRecycler(1);
    }

    @Override
    public void CloseRequest() {

    }

    private void setRecycler(int page){
        SubjectsRequestServiceFactory.GetClassList(new RequestObserver.RequestObserverNext<AllDataState<SubjectsModel>>() {
            @Override
            public void Next(AllDataState<SubjectsModel> o) {
                SimpleRecyclerViewAdapter simpleRecyclerViewAdapter= new SimpleRecyclerViewAdapter(R.layout.subject_news_fragment_item, mvpView.getActivityContext(), o.getData().getDatalist(), (helper, item) -> {
                    SubjectsModel.DatalistBean datalistBean = (SubjectsModel.DatalistBean) item;
                    helper.setText(R.id.Subjects_Page_Fragment_item_TextView, datalistBean.getTitle());
                    helper.setText(R.id.Subjects_Page_Fragment_item_Time, datalistBean.getIntime());
                });
                mvpView.getSubjects_New_Fragment_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager1(true, 0, false));
                mvpView.getSubjects_New_Fragment_Recycler().setAdapter(simpleRecyclerViewAdapter);
                simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
                    RoutePageActivity.getPageActivity(o.getData().getDatalist().get(position).getUrl());
                });
                /**下拉刷新**/
                if (mvpView.getSwipeRefreshLayout()!=null){
                    mvpView.getSwipeRefreshLayout().setRefreshing(false);
                }

                ViewGroup.LayoutParams layoutParams=mvpView.getViewPage().getLayoutParams();
                layoutParams.height=o.getData().getDatalist().size()*SimpleUtils.dip2px(mvpView.getActivityContext(),65);
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },null,mvpView.getDataType(),page);
    }
}
