package com.cnitpm.z_question.TrueTopic;

import android.graphics.Color;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
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

import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class TrueTopicPresenter extends BasePresenter<TrueTopicView> implements View.OnClickListener {
    @Override
    public void init() {
        setView();
        Click();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("历年真题");
        mvpView.getInclude_Title_Close().setOnClickListener(v -> mvpView.getThisActivity().finish());
        mvpView.getTrueTopic_item1().setText("技术实务");
        mvpView.getTrueTopic_item2().setText("综合能力");
        mvpView.getTrueTopic_item3().setText("案例分析");
        setRecycler(115);
    }

    @Override
    public void CloseRequest() {

    }

    private void Click(){
        mvpView.getTrueTopic_item1().setOnClickListener(this);
        mvpView.getTrueTopic_item2().setOnClickListener(this);
        mvpView.getTrueTopic_item3().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.TrueTopic_item1) {
            mvpView.getTrueTopic_item1().setTextColor(Color.parseColor("#FF4E50"));
            mvpView.getTrueTopic_item2().setTextColor(Color.parseColor("#2b2b2b"));
            mvpView.getTrueTopic_item3().setTextColor(Color.parseColor("#2b2b2b"));
            setRecycler(115);
        }else if(i == R.id.TrueTopic_item2){
            mvpView.getTrueTopic_item1().setTextColor(Color.parseColor("#2b2b2b"));
            mvpView.getTrueTopic_item2().setTextColor(Color.parseColor("#FF4E50"));
            mvpView.getTrueTopic_item3().setTextColor(Color.parseColor("#2b2b2b"));
            setRecycler(116);
        }else if(i == R.id.TrueTopic_item3){
            mvpView.getTrueTopic_item1().setTextColor(Color.parseColor("#2b2b2b"));
            mvpView.getTrueTopic_item2().setTextColor(Color.parseColor("#2b2b2b"));
            mvpView.getTrueTopic_item3().setTextColor(Color.parseColor("#FF4E50"));
            setRecycler(117);
        }
    }

    private void setRecycler(int Type_two){
        QuestionRequestServiceFactory.GetExamList(new RequestObserver.RequestObserverNext<AllDataState<List<TrueTopicModel>>>() {
            @Override
            public void Next(AllDataState<List<TrueTopicModel>> o) {
                SimpleRecyclerViewAdapter simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.truetopic_recycler_item, mvpView.getActivityContext(), o.getData(), (helper, item) -> {
                    TrueTopicModel trueTopicModel= (TrueTopicModel) item;
                    helper.setText(R.id.TrueTopic_Recycler_Title,trueTopicModel.getTitle());
                    helper.addOnClickListener(R.id.TrueTopic_Recycler_K).addOnClickListener(R.id.TrueTopic_Recycler_L);
                });
                mvpView.getTrueTopic_Recycler().setAdapter(simpleRecyclerViewAdapter);
                mvpView.getTrueTopic_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));

                simpleRecyclerViewAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                    if (view.getId()==R.id.TrueTopic_Recycler_K){
                        RoutePageActivity.getPageActivity(o.getData().get(position).getExamurl());
                    }else if(view.getId()==R.id.TrueTopic_Recycler_L){
                        RoutePageActivity.getPageActivity(o.getData().get(position).getUrl());
                    }
                });
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },mvpView.getActivityContext(),Type_two);
    }
}
