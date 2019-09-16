package com.cnitpm.z_subjects.JZA;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
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

public class JZAPresenter extends BasePresenter<JZAView> {
    private  SimpleRecyclerViewAdapter simpleRecyclerViewAdapter;
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("科目资讯");
        mvpView.getInclude_Title_Close().setOnClickListener(v -> mvpView.getThisActivity().finish());
        setRecycler(1,mvpView.getActivityContext());

        switch (mvpView.getDataType()){
            case 527:
                mvpView.getJZA_TabLayout().getTabAt(0).select();
                break;
            case 528:
                mvpView.getJZA_TabLayout().getTabAt(1).select();
                break;
            case 529:
                mvpView.getJZA_TabLayout().getTabAt(2).select();
                break;
        }

        mvpView.getJZA_TabLayout().addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                       mvpView.setDataType(527);
                        break;
                    case 1:
                        mvpView.setDataType(528);
                        break;
                    case 2:
                        mvpView.setDataType(529);
                        break;
                }
                setRecycler(1,mvpView.getActivityContext());
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

    private void setRecycler(int page, Context context){
        SubjectsRequestServiceFactory.GetClassList(new RequestObserver.RequestObserverNext<AllDataState<SubjectsModel>>() {
            @Override
            public void Next(AllDataState<SubjectsModel> o) {
                if (page==1){
                    List<SubjectsModel.DatalistBean> datalistBeans=o.getData().getDatalist();
                    simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.subject_news_fragment_item, mvpView.getActivityContext(), datalistBeans, (helper, item) -> {
                        SubjectsModel.DatalistBean datalistBean= (SubjectsModel.DatalistBean) item;
                        helper.setText(R.id.Subjects_Page_Fragment_item_TextView,datalistBean.getTitle());
                        helper.setText(R.id.Subjects_Page_Fragment_item_Time,datalistBean.getIntime());
                    });
                    mvpView.getJZA_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));
                    mvpView.getJZA_Recycler().setAdapter(simpleRecyclerViewAdapter);

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
                }, mvpView.getJZA_Recycler());
                simpleRecyclerViewAdapter.disableLoadMoreIfNotFullPage();
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },context,mvpView.getDataType(),page);
    }
}
