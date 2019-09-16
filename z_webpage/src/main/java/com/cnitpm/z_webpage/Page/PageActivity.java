package com.cnitpm.z_webpage.Page;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_webpage.R;
import com.zzhoujay.richtext.RichText;

@Route(path = "/webpage/PageActivity")
public class PageActivity extends MvpActivity<PagePresenter> implements PageView {
    private WebView Page_WebView;
    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;

    @Autowired
    public String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_layout);
        ARouter.getInstance().inject(this);
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
    protected PagePresenter createPresenter() {
        return new PagePresenter();
    }

    @Override
    public void getViews() {
        Page_WebView=findViewById(R.id.Page_WebView);
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);
    }

    @Override
    public WebView getPage_WebView() {
        return Page_WebView;
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
    public String getUrl() {
        return url;
    }


}
