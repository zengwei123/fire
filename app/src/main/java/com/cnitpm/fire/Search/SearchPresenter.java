package com.cnitpm.fire.Search;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.fire.Model.SearchModel;
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

import java.util.List;

import io.reactivex.disposables.Disposable;

public class SearchPresenter extends BasePresenter<SearchView> {
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Close().setOnClickListener(v -> mvpView.getThisActivity().finish());
        mvpView.getInclude_Title_Text().setText("搜索");

        mvpView.getSearch_EditText().setOnEditorActionListener((v, actionId, event) -> {
            if ((event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {
                if (mvpView.getSearch_EditText().getText().toString().equals("")){
                    SimpleUtils.setToast("请输入搜索内容");
                }else {
                    MainRequestServiceFactory.search(new RequestObserver.RequestObserverNext<AllDataState<List<SearchModel>>>() {
                        @Override
                        public void Next(AllDataState<List<SearchModel>> o) {
                            if (o.getState()==0){
                                setRecycler(o.getData());
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
                    },mvpView.getActivityContext(),mvpView.getSearch_EditText().getText().toString());
                }
            }
            return false;
        });
    }


    @Override
    public void CloseRequest() {

    }

    private void setRecycler(List<SearchModel> searchModels){
        SimpleRecyclerViewAdapter simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.search_recycler_item, mvpView.getActivityContext(), searchModels, (helper, item) -> {
            SearchModel searchModel= (SearchModel) item;
            RichText.fromHtml(searchModel.getTitle()).into(helper.getView(R.id.Search_Message));
            helper.setText(R.id.Search_Date,searchModel.getIntime());
            helper.addOnClickListener(R.id.Search_Message).addOnClickListener(R.id.Search_Date);
        });
        /**不知道为什么不触发**/
        simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
            String urls="https://m.cfeks.com"+searchModels.get(position).getUrl();
            RoutePageActivity.getPageActivity(urls);
        });
        simpleRecyclerViewAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            String urls="https://m.cfeks.com"+searchModels.get(position).getUrl();
            RoutePageActivity.getPageActivity(urls);
        });
        mvpView.getSearch_Recycler().setAdapter(simpleRecyclerViewAdapter);
        mvpView.getSearch_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));
    }
}
