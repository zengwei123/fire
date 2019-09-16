package com.cnitpm.z_me.DownLoadPage;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_me.R;
import com.zzhoujay.richtext.RichText;

import cn.jzvd.Jzvd;

@Route(path = "/Me/DownLoadPageActivity")
public class DownLoadPageActivity extends MvpActivity<DownLoadPagePresenter> implements DownLoadPageView {
    private MyVideoView DownLoadPage_MyVideoView;
    private RecyclerView DownLoadPage_RecyclerView;
    private boolean isHS=true;

    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downloadpage_layout);
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
    protected DownLoadPagePresenter createPresenter() {
        return new DownLoadPagePresenter();
    }

    @Override
    public void getViews() {
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);

        DownLoadPage_MyVideoView=findViewById(R.id.DownLoadPage_MyVideoView);
        DownLoadPage_RecyclerView=findViewById(R.id.DownLoadPage_RecyclerView);

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
    public MyVideoView getDownLoadPage_MyVideoView() {
        return DownLoadPage_MyVideoView;
    }

    @Override
    public RecyclerView getDownLoadPage_RecyclerView() {
        return DownLoadPage_RecyclerView;
    }


    @Override
    public void onBackPressed() {
        if (isHS){
            Jzvd.backPress();
            super.onBackPressed();
        }else{
            getDownLoadPage_MyVideoView().fullscreenButton.callOnClick();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //横竖屏切换时候显示隐藏title
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            DownLoadPage_MyVideoView.titleTextView.setVisibility(View.VISIBLE);
            isHS=false;
        } else {
            DownLoadPage_MyVideoView.titleTextView.setVisibility(View.GONE);
            isHS=true;
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }
}
