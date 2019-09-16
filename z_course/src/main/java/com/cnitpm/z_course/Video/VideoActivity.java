package com.cnitpm.z_course.Video;


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_course.R;
import com.zzhoujay.richtext.RichText;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

@Route(path = "/Course/VideoActivity")
public class VideoActivity extends MvpActivity<VideoPresenter> implements VideoView {
    private MyVideoView Video_MyVideoView;
    private RecyclerView Video_RecyclerView;
    private boolean isHS=true;

    private ImageView Video_XiaZai;

    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;

    private RecyclerView Video_Recycler_XiaZai;   //下载列表
    private RelativeLayout Video_Download_layout;   //下载布局
    private TextView Video_Download_Text;   //下载按钮
    @Autowired
    public int type;
    @Autowired
    public int vsid;
    @Autowired
    public int gq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);
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
    protected VideoPresenter createPresenter() {
        return new VideoPresenter();
    }

    @Override
    public void getViews() {
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);

        Video_XiaZai=findViewById(R.id.Video_XiaZai);
        Video_MyVideoView=findViewById(R.id.Video_MyVideoView);
        Video_RecyclerView=findViewById(R.id.Video_RecyclerView);
        Video_Recycler_XiaZai=findViewById(R.id.Video_Recycler_XiaZai);
        Video_Download_layout=findViewById(R.id.Video_Download_layout);

        Video_Download_Text=findViewById(R.id.Video_Download_Text);
    }

    @Override
    public MyVideoView getVideo_MyVideoView() {
        return Video_MyVideoView;
    }

    @Override
    public RecyclerView getVideo_RecyclerView() {
        return Video_RecyclerView;
    }

    @Override
    public ImageView getVideo_XiaZai() {
        return Video_XiaZai;
    }

    @Override
    public RecyclerView getVideo_Recycler_XiaZai() {
        return Video_Recycler_XiaZai;
    }

    @Override
    public RelativeLayout getVideo_Download_layout() {
        return Video_Download_layout;
    }

    @Override
    public TextView getVideo_Download_Text() {
        return Video_Download_Text;
    }

    @Override
    public int gettype() {
        return type;
    }

    @Override
    public int getvsid() {
        return vsid;
    }

    @Override
    public int getgq() {
        return gq;
    }

    @Override
    public void onBackPressed() {
        if (getVideo_Download_layout().getVisibility()==View.VISIBLE){
            getVideo_Download_layout().setVisibility(View.GONE);
        }else if (isHS){
            Jzvd.backPress();
            super.onBackPressed();
        }else{
            getVideo_MyVideoView().fullscreenButton.callOnClick();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //横竖屏切换时候显示隐藏title
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Video_MyVideoView.titleTextView.setVisibility(View.VISIBLE);
            isHS=false;
        } else {
            Video_MyVideoView.titleTextView.setVisibility(View.GONE);
            isHS=true;
        }
        super.onConfigurationChanged(newConfig);
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
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }
}
