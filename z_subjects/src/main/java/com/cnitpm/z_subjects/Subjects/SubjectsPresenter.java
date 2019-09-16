package com.cnitpm.z_subjects.Subjects;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_base.MvpFragment;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleFragmentAdapter;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_subjects.Model.SubjectsModel;
import com.cnitpm.z_subjects.Net.SubjectsRequestServiceFactory;
import com.cnitpm.z_subjects.R;
import com.cnitpm.z_subjects.Subjects.Page.NewsFragment;
import com.zzhoujay.richtext.RichText;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class SubjectsPresenter extends BasePresenter<SubjectsView> implements View.OnClickListener {
    private int Type=527;
    private SimpleRecyclerViewAdapter simpleRecyclerViewAdapter;
    @Override
    public void init() {
        setView();
        click();
    }

    @Override
    public void setView() {
        /**菜单下面的**/
        SubjectsRequestServiceFactory.HomePage(mvpView.getSubjects_Title1());
        setTabPage();
        mvpView.getSubject_SwipeRefreshLayout().setOnRefreshListener(() -> {
            init();
        });
        setRecycler(1,null);

        mvpView.getSubject_AppBar().addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (verticalOffset >= 0) {
                mvpView.getSubject_SwipeRefreshLayout().setEnabled(true);
            } else {
                mvpView.getSubject_SwipeRefreshLayout().setEnabled(false);
            }
        });

    }

    @Override
    public void CloseRequest() {

    }
    /**科目资讯**/
    private void setTabPage(){
        reflex(mvpView.getSubjects_TabLayout());
        mvpView.getSubjects_TabLayout().addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        Type=527;
                        break;
                    case 1: Type=528;
                        break;
                    case 2: Type=529;
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
    public void reflex(final TabLayout tabLayout){
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = SimpleUtils.dip2px(tabLayout.getContext(), 10);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private void setRecycler(int page, Context context){
        SubjectsRequestServiceFactory.GetClassList(new RequestObserver.RequestObserverNext<AllDataState<SubjectsModel>>() {
            @Override
            public void Next(AllDataState<SubjectsModel> o) {
                if (page==1){
                    simpleRecyclerViewAdapter= new SimpleRecyclerViewAdapter(R.layout.subject_news_fragment_item, mvpView.getActivityContext(), o.getData().getDatalist(), (helper, item) -> {
                        SubjectsModel.DatalistBean datalistBean = (SubjectsModel.DatalistBean) item;
                        helper.setText(R.id.Subjects_Page_Fragment_item_TextView, datalistBean.getTitle());
                        helper.setText(R.id.Subjects_Page_Fragment_item_Time, datalistBean.getIntime());
                    });
                    mvpView.getSubjects_RecyclerView().setLayoutManager(SimpleUtils.getRecyclerLayoutManager1(true, 0, true));
                    mvpView.getSubjects_RecyclerView().setAdapter(simpleRecyclerViewAdapter);
                    simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
                        RoutePageActivity.getPageActivity( o.getData().getDatalist().get(position).getUrl());
                    });
                    /**下拉刷新**/
                    if (mvpView.getSubject_SwipeRefreshLayout()!=null){
                        mvpView.getSubject_SwipeRefreshLayout().setRefreshing(false);
                    }
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
                }, mvpView.getSubjects_RecyclerView());
                simpleRecyclerViewAdapter.disableLoadMoreIfNotFullPage();
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },context,Type,page);
    }

    private void click(){
        mvpView.getSubjects_DayPractice().setOnClickListener(this);
        mvpView.getSubjects_Chapter().setOnClickListener(this);
        mvpView.getSubjects_TrueTopic().setOnClickListener(this);
        mvpView.getSubject_Training().setOnClickListener(this);
        mvpView.getHome_Apply_Submit1().setOnClickListener(this);

        mvpView.getModelTitle_Search().setOnClickListener(this);
        mvpView.getModelTitle_Message().setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.Subjects_DayPractice) {
            RoutePageActivity.getDayPracticeActivity();
        } else if (i == R.id.Subjects_Chapter) {
            RoutePageActivity.getChapterActivity();
        }else if (i == R.id.Subjects_TrueTopic) {
            RoutePageActivity.getTrueTopicActivity();
        }else if(i==R.id.Subject_Training){
            RoutePageActivity.getPageActivity("https://m.cfeks.com/peixun/");
        }else if (i == R.id.ModelTitle_Message) {
            RoutePageActivity.getMessageActivity();
        }else if (i==R.id.ModelTitle_Search){
            RoutePageActivity.getSearchActivity();
        } else if(i==R.id.Home_Apply_Submit1){
            RoutePageActivity.getPayPageActivity(1);
        }
    }
    /**首页page 跳转界面的操作接口**/
    public void JumpPage(int i){
        mvpView.getSubjects_TabLayout().getTabAt(i).select();
    }
}
