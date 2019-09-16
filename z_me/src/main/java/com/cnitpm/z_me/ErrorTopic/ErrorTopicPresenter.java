package com.cnitpm.z_me.ErrorTopic;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapterCallback;
import com.cnitpm.z_me.Model.ErrorTopicModel;
import com.cnitpm.z_me.Net.MeRequestServiceFactory;
import com.cnitpm.z_me.R;
import com.zzhoujay.richtext.RichText;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class ErrorTopicPresenter extends BasePresenter<ErrorTopicView> {
    private SimpleRecyclerViewAdapter simpleRecyclerViewAdapter;
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("错题记录");
        mvpView.getInclude_Title_Close().setOnClickListener(v -> mvpView.getThisActivity().finish());
        setRecycler(1,mvpView.getActivityContext());
    }

    @Override
    public void CloseRequest() {

    }

    public void setRecycler(int page, Context context){
        MeRequestServiceFactory.MyExamError(new RequestObserver.RequestObserverNext<AllDataState<ErrorTopicModel>>() {
            @Override
            public void Next(AllDataState<ErrorTopicModel> o) {
                if (page==1){
                    SimpleUtils.setLog(o.getMessage());
                    List<ErrorTopicModel.DataListBean> dataListBeans=o.getData().getDataList();
                    simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.errortopic_recycler_item, mvpView.getActivityContext(), dataListBeans, (helper, item) -> {
                        ErrorTopicModel.DataListBean dataListBean= (ErrorTopicModel.DataListBean) item;
                        RichText.fromHtml("第 <font color='#FF4E50'>"+(helper.getAdapterPosition()+1)+"</font> 题").into(helper.getView(R.id.ErrorTopic_One));
                        RichText.fromHtml(dataListBean.getTcontent())
                                .autoPlay(true) // gif图片是否自动播放
                                .noImage(false) // 不显示并且不加载图片
                                .into(helper.getView(R.id.ErrorTopic_Content));
                        RichText.fromHtml("我的答案：<font color='#1890FF'>"+dataListBean.getUserAnswer()).into(helper.getView(R.id.ErrorTopic_Answer));
                        RichText.fromHtml("<font color='#F5222D'>[参考答案]"+dataListBean.getTrueAnswer()).into(helper.getView(R.id.ErrorTopic_MyAnswer));
                        helper.setText(R.id.ErrorTopic_Parsing_Text,"");
                        helper.getView(R.id.ErrorTopic_Parsing_Text).setVisibility(View.GONE);
                        SimpleUtils.setViewTypeface(helper.getView(R.id.ErrorTopic_Parsing),"解析\ueac2");
                        SimpleUtils.setViewTypeface(helper.getView(R.id.ErrorTopic_Delete),"\ue872");
                        helper.getView(R.id.ErrorTopic_Parsing).setOnClickListener(v -> {
                            getParsing(helper,dataListBean.getTid());
                        });

                        helper.getView(R.id.ErrorTopic_Delete).setOnClickListener(v -> {
                            DelExamErrors(dataListBeans,mvpView.getActivityContext(),helper.getAdapterPosition(),dataListBean.getId());
                        });
                    });
                    mvpView.getQuestions_Record_RecyclerView().setAdapter(simpleRecyclerViewAdapter);
                    mvpView.getQuestions_Record_RecyclerView().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));
                }else {
                    simpleRecyclerViewAdapter.loadMoreComplete();
                    for (ErrorTopicModel.DataListBean dataListBean:o.getData().getDataList()){
                        simpleRecyclerViewAdapter.addData(dataListBean);
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

    /**加载解析**/
    private void getParsing(BaseViewHolder helper,int tid){
        if (((TextView)helper.getView(R.id.ErrorTopic_Parsing_Text)).getText().toString().trim().equals("")){
            SimpleUtils.setViewTypeface(helper.getView(R.id.ErrorTopic_Parsing),"解析\ueac5");
            (helper.getView(R.id.ErrorTopic_Parsing_Text)).setVisibility(View.VISIBLE);
            MeRequestServiceFactory.GetShitiExplain((helper.getView(R.id.ErrorTopic_Parsing_Text)),tid);
        }else {
            if ((helper.getView(R.id.ErrorTopic_Parsing_Text)).getVisibility()==View.VISIBLE){
                SimpleUtils.setViewTypeface(helper.getView(R.id.ErrorTopic_Parsing),"解析\ueac2");
                (helper.getView(R.id.ErrorTopic_Parsing_Text)).setVisibility(View.GONE);
            }else {
                SimpleUtils.setViewTypeface(helper.getView(R.id.ErrorTopic_Parsing),"解析\ueac5");
                (helper.getView(R.id.ErrorTopic_Parsing_Text)).setVisibility(View.VISIBLE);
            }
        }
    }
    /**删除错题**/
    private void DelExamErrors( List<ErrorTopicModel.DataListBean> dataListBeans,Context context,int position,int id){
       MeRequestServiceFactory.DelExamErrors(new RequestObserver.RequestObserverNext<AllDataState>() {
           @Override
           public void Next(AllDataState o) {
                if (o.getState()==0){
                    dataListBeans.remove(position);
                    simpleRecyclerViewAdapter.notifyDataSetChanged();
                    SimpleUtils.setToast("删除成功");
                }else {
                    SimpleUtils.setToast(o.getMessage());
                }
           }

           @Override
           public void onError() {

           }

           @Override
           public void getDisposable(Disposable d) {

           }
       },context,id);
    }
}
