package com.cnitpm.z_me.ErrorTopic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_me.R;
import com.zzhoujay.richtext.RichText;
@Route(path = "/Me/ErrorTopicActivity")
public class ErrorTopicActivity extends MvpActivity<ErrorTopicPresenter> implements ErrorTopicView {
    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;
    private RecyclerView Questions_Record_RecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.errortopic_layout);
        mvpPresenter.attachView(this);
        getViews();
        mvpPresenter.init();
        RichText.initCacheDir(getActivityContext());
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public Activity getThisActivity() {
        return this;
    }

    @Override
    protected ErrorTopicPresenter createPresenter() {
        return new ErrorTopicPresenter();
    }

    @Override
    public void getViews() {
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);
        Questions_Record_RecyclerView=findViewById(R.id.Questions_Record_RecyclerView);
    }

    @Override
    public TextView getInclude_Title_Text() {
        return Include_Title_Text;
    }

    @Override
    public ImageView getInclude_Title_Close() {
        return Include_Title_Close;
    }

    @Override
    public RecyclerView getQuestions_Record_RecyclerView() {
        return Questions_Record_RecyclerView;
    }
}
