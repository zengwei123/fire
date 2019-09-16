package com.cnitpm.z_homeitem.News;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_homeitem.R;
import com.zzhoujay.richtext.RichText;

@Route(path = "/HomeItem/NewsActivity")
public class NewsActivity extends MvpActivity<NewsPresenter> implements NewsView{
    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;

    private RecyclerView News_Recycler;
    private TabLayout News_TabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);
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
    protected NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    public void getViews() {
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);
        News_Recycler=findViewById(R.id.News_Recycler);
        News_TabLayout=findViewById(R.id.News_TabLayout);
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
    public RecyclerView getNews_Recycler() {
        return News_Recycler;
    }

    @Override
    public TabLayout getNews_TabLayout() {
        return News_TabLayout;
    }
}
