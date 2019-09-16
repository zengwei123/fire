package com.cnitpm.z_subjects.JZA;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_subjects.R;
import com.zzhoujay.richtext.RichText;

import retrofit2.http.Field;

/**技术务实 综合能力 案例分析**/
@Route(path = "/Subjects/JZAActivity")
public class JZAActivity extends MvpActivity<JZAPresenter> implements JZAView {
    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;
    private RecyclerView JZA_Recycler;
    private TabLayout JZA_TabLayout;
    @Autowired
    public int DataType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jza_layout);
        ARouter.getInstance().inject(this);
        SimpleUtils.setLog("看看："+getDataType());
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
    protected JZAPresenter createPresenter() {
        return new JZAPresenter();
    }

    @Override
    public void getViews() {
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);
        JZA_Recycler=findViewById(R.id.JZA_Recycler);
        JZA_TabLayout=findViewById(R.id.JZA_TabLayout);
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
    public RecyclerView getJZA_Recycler() {
        return JZA_Recycler;
    }

    @Override
    public int getDataType() {
        return DataType;
    }

    @Override
    public void setDataType(int i) {
        DataType=i;
    }

    @Override
    public TabLayout getJZA_TabLayout() {
        return JZA_TabLayout;
    }
}
