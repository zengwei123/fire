package com.cnitpm.fire.Message;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.fire.Model.MessageModel;
import com.cnitpm.fire.Net.MainRequestServiceFactory;
import com.cnitpm.fire.R;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapterCallback;
import com.zzhoujay.richtext.RichText;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class MessagePresenter extends BasePresenter<MessageView> {
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Close().setOnClickListener(v -> mvpView.getThisActivity().finish());
        mvpView.getInclude_Title_Text().setText("会员消息");

        MainRequestServiceFactory.Mymsg(new RequestObserver.RequestObserverNext<AllDataState<MessageModel>>() {
            @Override
            public void Next(AllDataState<MessageModel> o) {
                SimpleRecyclerViewAdapter simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.message_recycler_item, mvpView.getActivityContext(), o.getData().getDataList(), (helper, item) -> {
                    MessageModel.DataList dataList= (MessageModel.DataList) item;
                    RichText.fromHtml(dataList.getTitle()).into(helper.getView(R.id.Message_Recycler_Item_Title));
                    helper.setText(R.id.Message_Recycler_Item_Time,dataList.getIntime());
                });
                simpleRecyclerViewAdapter.setIsNoNetWork(true);
                simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
                    RoutePageActivity.getMessageContentActivity(o.getData().getDataList().get(position).getId());
                });
                mvpView.getMessage_Recycler().setAdapter(simpleRecyclerViewAdapter);
                mvpView.getMessage_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },mvpView.getActivityContext());
    }

    @Override
    public void CloseRequest() {

    }
}
