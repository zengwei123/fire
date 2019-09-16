package com.cnitpm.z_home.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_base.MvpFragment;
import com.cnitpm.z_common.GlideUtil;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleFragmentAdapter;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter1;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapterCallback;
import com.cnitpm.z_home.Home.Page.NewsFragment;
import com.cnitpm.z_home.Model.HomeHead;
import com.cnitpm.z_home.Model.OptimalModel;
import com.cnitpm.z_home.Model.OptimalModel1;
import com.cnitpm.z_home.Net.HomeRequestServiceFactory;
import com.cnitpm.z_home.R;
import com.zzhoujay.richtext.RichText;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.disposables.Disposable;

public class HomePresenter extends BasePresenter<HomeView> implements View.OnClickListener {
    private SimpleRecyclerViewAdapter simpleRecyclerViewAdapter_item;    //首页的几个item

    @Override
    public void init() {
        setView();
        click();
    }

    @Override
    public void setView() {
        setBannerAndTime();

        /**item**/
        List<String> strings=Arrays.asList("考试指南","考试大纲","技术务实","综合能力","案例分析","每日一练","考试资讯","备考经验","考试中心","培训中心");
        List<Integer> images=Arrays.asList(R.mipmap.home_menu1,R.mipmap.home_menu2,R.mipmap.home_menu3,R.mipmap.home_menu4,R.mipmap.home_menu5,R.mipmap.home_menu6,R.mipmap.home_menu7,R.mipmap.home_menu8,R.mipmap.home_menu9,R.mipmap.home_menu10);
        simpleRecyclerViewAdapter_item=new SimpleRecyclerViewAdapter(R.layout.home_menu_item, mvpView.getActivityContext(), strings, (helper,item)->{
            helper.setText(R.id.Home_Menu_Text,(String)item);
            GlideUtil.displayImage(mvpView.getThisActivity(), images.get(helper.getAdapterPosition()),helper.getView(R.id.Home_Menu_Image));
        });
        mvpView.getHome_Fragment_RecyclerView_Menu().setAdapter(simpleRecyclerViewAdapter_item);
        mvpView.getHome_Fragment_RecyclerView_Menu().setLayoutManager(SimpleUtils.getRecyclerLayoutManager1(false,5,false));
        itemclick(simpleRecyclerViewAdapter_item);

        setTabLayout();
        /**广告的recycler**/
        setOptimal();

        mvpView.getHome_SwipeRefreshLayout().setOnRefreshListener(() -> {
            init();
        });
    }
    @Override
    public void CloseRequest() {

    }

    /**轮播图和考试报名时间**/
    public void setBannerAndTime(){
        HomeRequestServiceFactory.HomePage(new RequestObserver.RequestObserverNext<AllDataState<List<HomeHead>>>() {
            @Override
            public void Next(AllDataState<List<HomeHead>> o) {
                List<String> strings=new ArrayList<>();
                for (HomeHead homeHead:o.getData()){
                    strings.add(homeHead.getSrc());
                }
                /**轮播图**/
                mvpView.getHome_Fragment_BGABanner().setAdapter((BGABanner.Adapter<ImageView, String>) (banner, itemView, model, position) ->{
                    itemView.setScaleType(ImageView.ScaleType.FIT_XY);
                    GlideUtil.displayImage(mvpView.getThisActivity(),model,itemView);
                });
                mvpView.getHome_Fragment_BGABanner().setData(strings,Arrays.asList("","",""));
                mvpView.getHome_Fragment_BGABanner().setDelegate((banner, itemView, model, position) -> {
                    RoutePageActivity.getPageActivity(o.getData().get(position).getUrl());
                });
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },mvpView.getHome_Apply_Title1());
    }
    /**资讯tab**/
    public void setTabLayout(){
        /**资讯模块**/
        reflex(mvpView.getHome_New_TabLayout());
        List<MvpFragment> fragments=new ArrayList<>();
        NewsFragment newsFragment1=new NewsFragment();
        newsFragment1.setDateType(0);
        NewsFragment newsFragment2=new NewsFragment();
        newsFragment2.setDateType(1);
        NewsFragment newsFragment3=new NewsFragment();
        newsFragment3.setDateType(2);
        NewsFragment newsFragment4=new NewsFragment();
        newsFragment4.setDateType(3);
        fragments.add(newsFragment1);
        fragments.add(newsFragment2);
        fragments.add(newsFragment3);
        fragments.add(newsFragment4);
        mvpView.getHome_New_ViewPage().setOffscreenPageLimit(4);
        mvpView.getHome_New_ViewPage().setAdapter(new SimpleFragmentAdapter(((FragmentActivity)mvpView.getThisActivity()).getSupportFragmentManager(),fragments));

        mvpView.getHome_New_TabLayout().setupWithViewPager(mvpView.getHome_New_ViewPage());
        mvpView.getHome_New_TabLayout().getTabAt(0).setText("最近更新");
        mvpView.getHome_New_TabLayout().getTabAt(1).setText("章节练习");
        mvpView.getHome_New_TabLayout().getTabAt(2).setText("考试练习");
        mvpView.getHome_New_TabLayout().getTabAt(3).setText("推荐专题");

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

    /**优选课程的布局**/
    private void setOptimal(){
        HomeRequestServiceFactory.HomePage3(new RequestObserver.RequestObserverNext<AllDataState<OptimalModel>>() {
            @Override
            public void Next(AllDataState<OptimalModel> o) {
                int[] layouts={R.layout.optimal_recycler_item1,R.layout.optimal_recycler_item2,R.layout.optimal_recycler_item4};
                List<AllDataState> strings1=new ArrayList<>();
                strings1.add(new AllDataState<>("零基础考生",0));
                strings1.add(new AllDataState<>("有基础考生",0));
                for (int i=0;i<o.getData().getDk().size();i++){
                    strings1.add( new AllDataState<>(o.getData().getQk().get(i),1));
                    strings1.add( new AllDataState<>(o.getData().getDk().get(i),1));
                }
                strings1.add(new AllDataState<>(new OptimalModel1("考生可试听部分视频课程，不满意全额退款。", "3年有效期内未过，可享不限次免费重学至通关， 重学享受当年课程" ),2));
                strings1.add(new AllDataState<>(new OptimalModel1("考生可试听部分视频课程，不满意全额退款。", "2019年新版实务、综合、案例三科系统学习课程，哪科不会选哪科学习" ),2));

                SimpleRecyclerViewAdapter1 simpleRecyclerViewAdapter1=new SimpleRecyclerViewAdapter1(layouts, strings1, (helper, item) -> {

                    if(((AllDataState)item).getItemType()==0){
                        if (helper.getAdapterPosition()%2==0){
                            ((TextView)helper.getView(R.id.Optimal_item_TextView)).setTextColor(Color.parseColor("#F33C3A"));
                            helper.setText(R.id.Optimal_item_TextView,(String)((AllDataState)item).getData());
                        }else {
                            ((TextView)helper.getView(R.id.Optimal_item_TextView)).setTextColor(Color.parseColor("#465CC0"));
                            helper.setText(R.id.Optimal_item_TextView,(String)((AllDataState)item).getData());
                        }
                    }


                    else if(((AllDataState)item).getItemType()==1) {

                        if (helper.getAdapterPosition()%2==0){
                            OptimalModel.QkBean optimalModel=(OptimalModel.QkBean)((AllDataState)item).getData();
                            helper.setText(R.id.Optimal_item_Title,optimalModel.getTitle());
                            helper.setText(R.id.Optimal_item_Message,optimalModel.getDes());
                            ((TextView)helper.getView(R.id.Optimal_item_Message)).setTextColor(Color.parseColor("#FB6755"));
                            helper.getView(R.id.Optimal_item_Message).setBackground(mvpView.getActivityContext().getResources().getDrawable(R.drawable.optimal_recycler_item2_drawable1));
                            helper.setText(R.id.Optimal_item_Price,"￥"+optimalModel.getPrice());
                            /**跳到支付页面**/
                            helper.getView(R.id.Optimal_item3_Submit).setOnClickListener(v->{
                                RoutePageActivity.getPayPageActivity(optimalModel.getPaytype());
                            });
                        }else {
                            OptimalModel.DkBean optimalModel=(OptimalModel.DkBean)((AllDataState)item).getData();
                            helper.setText(R.id.Optimal_item_Title,optimalModel.getTitle());
                            helper.setText(R.id.Optimal_item_Message,optimalModel.getDes());
                            ((TextView)helper.getView(R.id.Optimal_item_Message)).setTextColor(Color.parseColor("#465CC0"));
                            helper.getView(R.id.Optimal_item_Message).setBackground(mvpView.getActivityContext().getResources().getDrawable(R.drawable.optimal_recycler_item2_drawable2));
                            helper.setText(R.id.Optimal_item_Price,"￥"+optimalModel.getPrice());
                            /**跳到支付页面**/
                            helper.getView(R.id.Optimal_item3_Submit).setOnClickListener(v->{
                                RoutePageActivity.getPayPageActivity(optimalModel.getPaytype());
                            });
                        }

                    }



                    else if(((AllDataState)item).getItemType()==2) {
                        OptimalModel1 optimalModel1=(OptimalModel1)((AllDataState)item).getData();
                        if (helper.getAdapterPosition()%2==0){
                            helper.getView(R.id.Optimal_item4_Text1).setBackgroundResource(R.drawable.optimal_recycler_item2_drawable4_1);
                            ((TextView)helper.getView(R.id.Optimal_item4_Text1)).setTextColor(Color.parseColor("#FB6755"));
                            String s="通关班";
                            SpannableString spannableString = new SpannableString(optimalModel1.getText2()+s);
                            RoundBackgroundColorSpan colorSpan = new RoundBackgroundColorSpan(Color.parseColor("#FB6755"),Color.parseColor("#FDE2DF"));
                            spannableString.setSpan(colorSpan, optimalModel1.getText2().length(), optimalModel1.getText2().length()+3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                            helper.setText(R.id.Optimal_item4_Text3,spannableString);
                            GlideUtil.displayImage(mvpView.getThisActivity(),R.mipmap.items_4_2,helper.getView(R.id.Optimal_item4_Image1));
                            GlideUtil.displayImage(mvpView.getThisActivity(),R.mipmap.items_4_2,helper.getView(R.id.Optimal_item4_Image2));
                        }else {
                            helper.getView(R.id.Optimal_item4_Text1).setBackgroundResource(R.drawable.optimal_recycler_item2_drawable4_2);
                            ((TextView)helper.getView(R.id.Optimal_item4_Text1)).setTextColor(Color.parseColor("#465CC0"));
                            String s="单科班";
                            SpannableString spannableString = new SpannableString(optimalModel1.getText2()+s);
                            RoundBackgroundColorSpan colorSpan = new RoundBackgroundColorSpan(Color.parseColor("#E2E6F8"),Color.parseColor("#465CC0"));
                            spannableString.setSpan(colorSpan,  optimalModel1.getText2().length(), optimalModel1.getText2().length()+3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                            helper.setText(R.id.Optimal_item4_Text3,spannableString);
                            GlideUtil.displayImage(mvpView.getThisActivity(),R.mipmap.items_4_1,helper.getView(R.id.Optimal_item4_Image1));
                            GlideUtil.displayImage(mvpView.getThisActivity(),R.mipmap.items_4_1,helper.getView(R.id.Optimal_item4_Image2));
                        }
                        helper.setText(R.id.Optimal_item4_Text2,optimalModel1.getText1());
                        ((TextView)helper.getView(R.id.Optimal_item4_Text2)).setTextColor(Color.parseColor("#F53E39"));
                    }

                    mvpView.getHome_SwipeRefreshLayout().setRefreshing(false);
                });
                mvpView.getHome_Optimal_Recycler().setAdapter(simpleRecyclerViewAdapter1);
                mvpView.getHome_Optimal_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager1(false,2,false));
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },mvpView.getActivityContext());


    }

    private void itemclick(SimpleRecyclerViewAdapter simpleRecyclerViewAdapter){
        simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position){
                case 0: RoutePageActivity.getPageActivity("https://m.cfeks.com/kszn.html");break;
                case 1: RoutePageActivity.getPageActivity("https://m.cfeks.com/ksdg.html");break;
                case 2:mvpView.getMainPageJump().PageJump(1,0); break;
                case 3:mvpView.getMainPageJump().PageJump(1,1); break;
                case 4:mvpView.getMainPageJump().PageJump(1,2); break;
                case 5: RoutePageActivity.getDayPracticeActivity();break;
                case 6: RoutePageActivity.getNewsItemActivity();break;
                case 7: RoutePageActivity.getReferenceActivity();break;
                case 8: mvpView.getMainPageJump().PageJump(2,0);break;
                case 9: mvpView.getMainPageJump().PageJump(3,0);break;
            }
        });
    }

    private void click(){
        mvpView.getModelTitle_Message().setOnClickListener(this);
        mvpView.getModelTitle_Search().setOnClickListener(this);
        mvpView.getHome_More_TextView().setOnClickListener(this);

        mvpView.getOptimal_Consulting().setOnClickListener(this);
        mvpView.getOptimal_Sign().setOnClickListener(this);
        mvpView.getHome_Apply_Submit1().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ModelTitle_Message) {
            RoutePageActivity.getMessageActivity();
        }else if (i==R.id.ModelTitle_Search){
            RoutePageActivity.getSearchActivity();
        }else if (i==R.id.Home_More_TextView){
            switch (mvpView.getHome_New_TabLayout().getSelectedTabPosition()){
                case 0:
                    RoutePageActivity.getNewsItemActivity();
                    break;
                case 1:
                    RoutePageActivity.getChapterActivity();
                    break;
                case 2:
                    RoutePageActivity.getTrueTopicActivity();
                    break;
                case 3:
                    RoutePageActivity.getNewsItemActivity();
                    break;
            }
        }else if (i==R.id.Optimal_Consulting){
            RoutePageActivity.getPageActivity("https://m.cfeks.com/livechat.html");
        }
        else if (i==R.id.Optimal_Sign){
            RoutePageActivity.getPayPageActivity(1);
        }
        else if (i==R.id.Home_Apply_Submit1){
            RoutePageActivity.getPayPageActivity(1);
        }
    }
}
