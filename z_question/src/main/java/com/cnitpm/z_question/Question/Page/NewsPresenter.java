package com.cnitpm.z_question.Question.Page;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapterCallback;
import com.cnitpm.z_question.Model.TrueTopicModel;
import com.cnitpm.z_question.Net.QuestionRequestServiceFactory;
import com.cnitpm.z_question.R;

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
        if (mvpView.getDataType()==117){
            //因为这是最后一个运行的接口  所以启动加载提示框  同时加载多个接口 会运行多个提示框 这样就会多次启动不好 所以前面的都不启动  就最后一个接口启动一次
            //还有中做法  判断加载提示框是否显示  如果显示就将他关闭然后再次启动 这样就不管调用几次提示都可以关闭
            setRecycler(mvpView.getDataType(),mvpView.getActivityContext());
        }else {
            setRecycler(mvpView.getDataType(),null);
        }
    }

    @Override
    public void CloseRequest() {

    }

    private void setRecycler(int Type_two, Context context){
        QuestionRequestServiceFactory.GetExamList(new RequestObserver.RequestObserverNext<AllDataState<List<TrueTopicModel>>>() {
            @Override
            public void Next(AllDataState<List<TrueTopicModel>> o) {
                SimpleRecyclerViewAdapter simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.question_news_page_item, mvpView.getActivityContext(), o.getData(), (helper, item) -> {
                    TrueTopicModel trueTopicModel= (TrueTopicModel) item;
                    helper.setText(R.id.Question_TextView_Item1,"历年\n真题");
                    helper.setText(R.id.Question_NewPage_Title,trueTopicModel.getTitle());
                });
                mvpView.getQuestion_News_Page_Recycler().setAdapter(simpleRecyclerViewAdapter);
                mvpView.getQuestion_News_Page_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager1(true,0,false));
                simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
                    RoutePageActivity.getPageActivity(o.getData().get(position).getExamurl());
                });
                if (mvpView.getSwipeRefreshLayout()!=null){
                    mvpView.getSwipeRefreshLayout().setRefreshing(false);
                }
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },context,Type_two);
    }
}
