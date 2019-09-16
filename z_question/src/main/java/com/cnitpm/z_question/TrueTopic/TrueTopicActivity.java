package com.cnitpm.z_question.TrueTopic;

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

@Route(path = "/Question/TrueTopicActivity")
public class TrueTopicActivity extends MvpActivity<TrueTopicPresenter> implements TrueTopicView {
    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;
    private TextView TrueTopic_item1;
    private TextView TrueTopic_item2;
    private TextView TrueTopic_item3;
    private RecyclerView TrueTopic_Recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truetopic_layout);
        mvpPresenter.attachView(this);
        getViews();
        mvpPresenter.init();
        RichText.initCacheDir(getActivityContext());
    }

    @Override
    protected TrueTopicPresenter createPresenter() {
        return new TrueTopicPresenter();
    }

    @Override
    public void getViews() {
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);
        TrueTopic_item1=findViewById(R.id.TrueTopic_item1);
        TrueTopic_item2=findViewById(R.id.TrueTopic_item2);
        TrueTopic_item3=findViewById(R.id.TrueTopic_item3);

        TrueTopic_Recycler=findViewById(R.id.TrueTopic_Recycler);
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
    public TextView getInclude_Title_Text() {
        return Include_Title_Text;
    }

    @Override
    public ImageView getInclude_Title_Close() {
        return Include_Title_Close;
    }

    @Override
    public TextView getTrueTopic_item1() {
        return TrueTopic_item1;
    }

    @Override
    public TextView getTrueTopic_item2() {
        return TrueTopic_item2;
    }

    @Override
    public TextView getTrueTopic_item3() {
        return TrueTopic_item3;
    }

    @Override
    public RecyclerView getTrueTopic_Recycler() {
        return TrueTopic_Recycler;
    }
}
