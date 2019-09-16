package com.cnitpm.fire.Search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cnitpm.fire.R;
import com.cnitpm.z_base.MvpActivity;
import com.zzhoujay.richtext.RichText;

@Route(path = "/Main/SearchActivity")
public class SearchActivity extends MvpActivity<SearchPresenter> implements SearchView {
    private EditText Search_EditText;
    private RecyclerView Search_Recycler;
    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        mvpPresenter.attachView(this);
        getViews();
        mvpPresenter.init();
        RichText.initCacheDir(getActivityContext());

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(getApplication()); // 尽可能早，推荐在Application中初始化

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
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    public void getViews() {
        Search_EditText=findViewById(R.id.Search_EditText);
        Search_Recycler=findViewById(R.id.Search_Recycler);
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);
    }

    @Override
    public EditText getSearch_EditText() {
        return Search_EditText;
    }

    @Override
    public RecyclerView getSearch_Recycler() {
        return Search_Recycler;
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
