package com.cnitpm.z_course.Course;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapterCallback;
import com.cnitpm.z_course.Model.CourseModel;
import com.cnitpm.z_course.Net.CourseRequestServiceFactory;
import com.cnitpm.z_course.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class CoursePresenter extends BasePresenter<CourseView> implements View.OnClickListener {
    @Override
    public void init() {
        if (SimpleUtils.getUserMessage()!=null){
            setView();
            Click();
        }
    }

    @Override
    public void setView() {
        mvpView.getCourse_item1().setText("技术实务");
        mvpView.getCourse_item2().setText("综合能力");
        mvpView.getCourse_item3().setText("案例分析");


        serRecycler(1);
    }



    @Override
    public void CloseRequest() {

    }

    private void serRecycler(int type){
        CourseRequestServiceFactory.shipin(new RequestObserver.RequestObserverNext<AllDataState<List<CourseModel>>>() {
            @Override
            public void Next(AllDataState<List<CourseModel>> o) {
                SimpleRecyclerViewAdapter simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.course_racycler_item,mvpView.getActivityContext(),o.getData(), (helper, item) -> {
                    CourseModel courseModel= (CourseModel) item;
                    helper.setText(R.id.Course_Title,courseModel.getZsname());
                    if (courseModel.getSpfou()==1){
                        setTEXTVIEW(helper.getView(R.id.Course_Title),courseModel.getZsname(),true);
                    }else {
                        setTEXTVIEW(helper.getView(R.id.Course_Title),courseModel.getZsname(),false);
                    }

                    if (courseModel.getKjfou()==0){
                        helper.setText(R.id.Course_KeJian,"无课件");
                    }else if(courseModel.getKjfou()==1){
                        helper.setText(R.id.Course_KeJian,"课件");
                    }else if(courseModel.getKjfou()==2){
                        helper.setText(R.id.Course_KeJian,"课件未解锁");
                    }

                    if (courseModel.getXtfou()==0){
                        helper.setText(R.id.Course_isKeJian,"无习题");
                    }else if(courseModel.getXtfou()==1){
                        helper.setText(R.id.Course_isKeJian,"习题已完成");
                    }else if(courseModel.getXtfou()==2){
                        helper.setText(R.id.Course_isKeJian,"习题未完成");
                    }else if(courseModel.getXtfou()==3){
                        helper.setText(R.id.Course_isKeJian,"习题未上传");
                    }else if(courseModel.getXtfou()==4){
                        helper.setText(R.id.Course_isKeJian,"习题未解锁");
                    }
                    helper.addOnClickListener(R.id.Course_Title)  //标题
                    .addOnClickListener(R.id.Course_KeJian)   //课件
                    .addOnClickListener(R.id.Course_isKeJian)   //习题
                    .addOnClickListener(R.id.Course_Biao)    //标清
                    .addOnClickListener(R.id.Course_Gao);    //高清
                });
                mvpView.getCourse_Recycler().setAdapter(simpleRecyclerViewAdapter);
                mvpView.getCourse_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager1(true,0,true));
                simpleRecyclerViewAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                       CourseModel courseModel=o.getData().get(position);
                       if (view.getId()==R.id.Course_KeJian){
                           if (courseModel.getKjfou()==1){
                               RoutePageActivity.getPageActivity(courseModel.getKjurl());
                           }else {
                               SimpleUtils.setToast("暂无课件");
                           }
                       }
                       else if(view.getId()==R.id.Course_isKeJian){
                           if (courseModel.getXtfou()==1||courseModel.getXtfou()==2){
                               RoutePageActivity.getPageActivity(courseModel.getXturl());
                           }else {
                               if (courseModel.getXtfou()==0)
                                   SimpleUtils.setToast("暂无习题");
                               if (courseModel.getXtfou()==3)
                                   SimpleUtils.setToast("习题未上传");
                               if (courseModel.getXtfou()==4)
                                   SimpleUtils.setToast("习题未解锁");
                           }
                       }else if(view.getId()==R.id.Course_Biao){
                           SimpleUtils.setLog("vsid"+courseModel.getVsid());
                           RoutePageActivity.getVideoActivity(type,courseModel.getVsid(),0);
                       }else if(view.getId()==R.id.Course_Gao){
                           RoutePageActivity.getVideoActivity(type,courseModel.getVsid(),1);
                       }
                });
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },type);
    }
    private void setTEXTVIEW(TextView textView,String string,boolean b){
        try {
            //1.获取bitmap
            Bitmap bitmap;
            if (b){
                bitmap= BitmapFactory.decodeResource(mvpView.getActivityContext().getResources(),R.mipmap.weikan);
            }else {
                bitmap= BitmapFactory.decodeResource(mvpView.getActivityContext().getResources(),R.mipmap.yikan);
            }
            //2.创建imageSpan
            ImageSpan imageSpan=new ImageSpan(mvpView.getActivityContext(),bitmap);
            //3.创建spannableString
            SpannableString spannableString=new SpannableString("   "+string);//图片的名字
            //4.第二三个参数指代的是图片名字长度hzw start 0   end 3
            spannableString.setSpan(imageSpan,0,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(spannableString,3,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //5.设置文字(然后神奇的显示储一张图片)
            textView.setText(spannableString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void Click(){
        mvpView.getCourse_item1().setOnClickListener(this);
        mvpView.getCourse_item2().setOnClickListener(this);
        mvpView.getCourse_item3().setOnClickListener(this);

        mvpView.getModelTitle_Search().setOnClickListener(this);
        mvpView.getModelTitle_Message().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.Course_item1) {
            mvpView.getCourse_item1().setTextColor(Color.parseColor("#FF4E50"));
            mvpView.getCourse_item2().setTextColor(Color.parseColor("#2b2b2b"));
            mvpView.getCourse_item3().setTextColor(Color.parseColor("#2b2b2b"));
            serRecycler(1);
        }else if(i == R.id.Course_item2){
            mvpView.getCourse_item1().setTextColor(Color.parseColor("#2b2b2b"));
            mvpView.getCourse_item2().setTextColor(Color.parseColor("#FF4E50"));
            mvpView.getCourse_item3().setTextColor(Color.parseColor("#2b2b2b"));
            serRecycler(2);
        }else if(i == R.id.Course_item3){
            mvpView.getCourse_item1().setTextColor(Color.parseColor("#2b2b2b"));
            mvpView.getCourse_item2().setTextColor(Color.parseColor("#2b2b2b"));
            mvpView.getCourse_item3().setTextColor(Color.parseColor("#FF4E50"));
            serRecycler(3);
        }else if (i == R.id.ModelTitle_Message) {
            RoutePageActivity.getMessageActivity();
        }else if (i==R.id.ModelTitle_Search){
            RoutePageActivity.getSearchActivity();
        }
    }

    /**首页page 跳转界面的操作接口**/
    public void JumpPage(int i){
        SimpleUtils.setLog("看看金来没用："+i);
        switch (i){
            case 0: mvpView.getCourse_item1().callOnClick();break;
            case 1: mvpView.getCourse_item2().callOnClick();break;
            case 2: mvpView.getCourse_item3().callOnClick();break;
        }
    }
}
