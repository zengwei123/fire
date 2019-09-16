package com.cnitpm.z_subjects.Chapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_subjects.R;
import com.zzhoujay.richtext.RichText;

@Route(path = "/Subjects/ChapterActivity")
public class ChapterActivity extends MvpActivity<ChapterPresenter> implements ChapterView {
    private RecyclerView Chapter_Recycler;
    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_layout);
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
    protected ChapterPresenter createPresenter() {
        return new ChapterPresenter();
    }

    @Override
    public void getViews() {
        Chapter_Recycler=findViewById(R.id.Chapter_Recycler);
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);
    }

    @Override
    public RecyclerView getChapter_Recycler() {
        return Chapter_Recycler;
    }

    @Override
    public TextView getInclude_Title_Text() {
        return Include_Title_Text;
    }

    @Override
    public ImageView getInclude_Title_Close() {
        return Include_Title_Close;
    }
}
