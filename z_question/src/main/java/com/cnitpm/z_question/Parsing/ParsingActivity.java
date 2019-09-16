package com.cnitpm.z_question.Parsing;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_question.R;
import com.zzhoujay.richtext.RichText;

/**历年真题**/
@Route(path = "/Question/ParsingActivity")
public class ParsingActivity extends MvpActivity<ParsingPresenter> implements ParsingView {
    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;
    private RecyclerView Parsing_Recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parsing_layout);
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
    protected ParsingPresenter createPresenter() {
        return new ParsingPresenter();
    }

    @Override
    public void getViews() {
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);
        Parsing_Recycler=findViewById(R.id.Parsing_Recycler);
    }

    @Override
    public RecyclerView getParsing_Recycler() {
        return Parsing_Recycler;
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
