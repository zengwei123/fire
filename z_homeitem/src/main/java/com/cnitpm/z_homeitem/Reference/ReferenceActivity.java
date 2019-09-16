package com.cnitpm.z_homeitem.Reference;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_homeitem.R;
import com.zzhoujay.richtext.RichText;

@Route(path = "/HomeItem/ReferenceActivity")
public class ReferenceActivity extends MvpActivity<ReferencePresenter> implements ReferenceView {
    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;

    private RecyclerView Reference_Recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reference_layout);
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
    protected ReferencePresenter createPresenter() {
        return new ReferencePresenter();
    }

    @Override
    public void getViews() {
        Reference_Recycler=findViewById(R.id.Reference_Recycler);
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);
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
    public RecyclerView getReference_Recycler() {
        return Reference_Recycler;
    }
}
