package com.cnitpm.z_question.Question;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_base.MvpFragment;
import com.cnitpm.z_common.GlideUtil;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleFragmentAdapter;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_question.Model.TrueTopicModel;
import com.cnitpm.z_question.Net.QuestionRequestServiceFactory;
import com.cnitpm.z_question.Question.Page.NewsFragment;
import com.cnitpm.z_question.R;
import com.zzhoujay.richtext.RichText;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class QuestionPresenter extends BasePresenter<QuestionView> implements View.OnClickListener {
    @Override
    public void init() {
        setView();
        click();
    }

    @Override
    public void setView() {
        /**菜单下面的**/
        QuestionRequestServiceFactory.HomePage(mvpView.getSubjects_Title1());
        setTab("技术实务",true);
        setTab("综合能力",false);
        setTab("案例分析",false);

        mvpView.getQuestion_TabLayout().addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView=tab.getCustomView().findViewById(R.id.Question_TabLayout_Item_TextView);
                textView.setBackground(mvpView.getThisActivity().getDrawable(R.drawable.question_style3));
                textView.setTextColor(Color.parseColor("#ffffff"));
                switch (tab.getPosition()){
                    case 0:
                        setRecycler(115, mvpView.getActivityContext());
                        break;
                    case 1:
                        setRecycler(116, mvpView.getActivityContext());
                        break;
                    case 2:
                        setRecycler(117, mvpView.getActivityContext());
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView textView=tab.getCustomView().findViewById(R.id.Question_TabLayout_Item_TextView);
                textView.setBackground(null);
                textView.setTextColor(Color.parseColor("#2b2b2b"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setRecycler(115,null);

        /**下拉刷新**/
        mvpView.getQuestion_SwipeRefreshLayout().setOnRefreshListener(() -> {
            mvpView.getQuestion_TabLayout().removeAllTabs();
            init();
        });

        mvpView.getQuestion_AppBar().addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (verticalOffset >= 0) {
                mvpView.getQuestion_SwipeRefreshLayout().setEnabled(true);
            } else {
                mvpView.getQuestion_SwipeRefreshLayout().setEnabled(false);
            }
        });
    }

    private void click(){
        /**上面4个按钮**/
        mvpView.getQuestion_DayQuestion().setOnClickListener(this);
        mvpView.getQuestion_TrueTopic().setOnClickListener(this);
        mvpView.getQuestion_Parsing().setOnClickListener(this);
        mvpView.getQuestion_Simulation().setOnClickListener(this);
        mvpView.getModelTitle_Search().setOnClickListener(this);
        mvpView.getModelTitle_Message().setOnClickListener(this);
        mvpView.getQuestion_Apply_Submit1().setOnClickListener(this);
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
                    if (helper.getAdapterPosition()==o.getData().size()-1){
                        helper.getView(R.id.ccczzzz).setVisibility(View.GONE);   //最后一个隐藏分割线
                    }
                });
                mvpView.getQuestion_Recycler().setAdapter(simpleRecyclerViewAdapter);
                mvpView.getQuestion_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));
                simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
                    RoutePageActivity.getPageActivity(o.getData().get(position).getExamurl());
                });
                if (mvpView.getQuestion_SwipeRefreshLayout()!=null){
                    mvpView.getQuestion_SwipeRefreshLayout().setRefreshing(false);
                }

                /**上拉加载**/
                simpleRecyclerViewAdapter.setOnLoadMoreListener(() -> simpleRecyclerViewAdapter.loadMoreEnd(), mvpView.getQuestion_Recycler());
                simpleRecyclerViewAdapter.disableLoadMoreIfNotFullPage();
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },context,Type_two);
    }

    /**自定义的底部菜单item**/
    private void setTab(String title,boolean b){
        TabLayout.Tab tab =  mvpView.getQuestion_TabLayout().newTab();
        View view = LayoutInflater.from(mvpView.getActivityContext()).inflate(R.layout.question_tablayout_item, null);
        TextView tv = view.findViewById(R.id.Question_TabLayout_Item_TextView);
        tv.setText(title);
        if (b){
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setBackground(mvpView.getThisActivity().getDrawable(R.drawable.question_style3));
        }else {

        }
        tab.setCustomView(view);
        mvpView.getQuestion_TabLayout().addTab(tab);
    }

    @Override
    public void onClick(View v) {
        int i=v.getId();
        if (i==R.id.Question_DayQuestion){
            RoutePageActivity.getDayPracticeActivity();
        }else if(i==R.id.Question_TrueTopic){
            RoutePageActivity.getTrueTopicActivity();
        }else if(i==R.id.Question_Parsing){
            RoutePageActivity.getParsingActivity();
        }else if(i==R.id.Question_Simulation){
            RoutePageActivity.getSimulationActivity();
        }else if (i == R.id.ModelTitle_Message) {
            RoutePageActivity.getMessageActivity();
        }else if (i==R.id.ModelTitle_Search){
            RoutePageActivity.getSearchActivity();
        }else if(i==R.id.Question_Apply_Submit1){
            RoutePageActivity.getPayPageActivity(1);
        }
    }
}
