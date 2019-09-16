package com.cnitpm.z_me.Questions_Record;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapterCallback;
import com.cnitpm.z_me.Model.Record;
import com.cnitpm.z_me.Net.MeRequestServiceFactory;
import com.cnitpm.z_me.R;

import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class Questions_RecordPresenter extends BasePresenter<Questions_RecordView> {
    private SimpleRecyclerViewAdapter simpleRecyclerViewAdapter;
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("答题记录");
        mvpView.getInclude_Title_Close().setOnClickListener(v -> mvpView.getThisActivity().finish());
        setRecycler(1,mvpView.getActivityContext());
    }

    @Override
    public void CloseRequest() {

    }


    private void setRecycler(int page, Context context){
        MeRequestServiceFactory.MyAnswerRecord(new RequestObserver.RequestObserverNext<AllDataState<Record>>() {
            @Override
            public void Next(AllDataState<Record> o) {
                if (page==1){
                    List<Record.DataListBean> dataListBeans=o.getData().getDataList();
                    simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.questions_record_recycler_item, mvpView.getActivityContext(), dataListBeans, (helper, item) -> {
                        Record.DataListBean dataListBean= (Record.DataListBean) item;
                        helper.setText(R.id.Questions_Record_recycler_Title,dataListBean.getTitle());
                        setTextColor("#FF4E50",helper.getView(R.id.Questions_Record_Fen1),"得分："+dataListBean.getUscore());
                        setTextColor("#32B16C",helper.getView(R.id.Questions_Record_Fen2),"总分："+dataListBean.getZscore());
                        helper.setText(R.id.Questions_Record_Time,dataListBean.getIntime());
                    });
                    mvpView.getQuestions_Record_RecyclerView().setAdapter(simpleRecyclerViewAdapter);
                    mvpView.getQuestions_Record_RecyclerView().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));

                    simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
                        RoutePageActivity.getPageActivity(dataListBeans.get(position).getUrl());
                    });
                }else {
                    simpleRecyclerViewAdapter.loadMoreComplete();
                    for (Record.DataListBean datalistBean:o.getData().getDataList()){
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
                }, mvpView.getQuestions_Record_RecyclerView());
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

    public void setTextColor(String color, TextView textView, String str){
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor(color)), 3, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }
}
