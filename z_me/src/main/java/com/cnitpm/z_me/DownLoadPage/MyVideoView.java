package com.cnitpm.z_me.DownLoadPage;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.cnitpm.z_me.R;

import cn.jzvd.JzvdStd;

public class MyVideoView extends JzvdStd {
    public ImageView MyVideo_BeiSu;   //倍速按钮
    public RelativeLayout Video_BeiSu_layout;  //倍速布局
    private VideoCallBack videoCallBack;  //监听回调
    /**倍速按钮**/
    private TextView Video_BeiSu_layout_Speed0_5;
    private TextView Video_BeiSu_layout_Speed1_0;
    private TextView Video_BeiSu_layout_Speed1_5;
    private TextView Video_BeiSu_layout_Speed2_0;
    private float BSpeed=1.0f;


    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setUp(String url, String title) {
        titleTextView.setVisibility(GONE);
        super.setUp(url, title);
    }

    @Override
    public int getLayoutId() {
        return R.layout.video_jz_layout;
    }

    @Override
    public void init(Context context) {
        super.init(context);
        MyVideo_BeiSu=findViewById(R.id.MyVideo_BeiSu);
        Video_BeiSu_layout=findViewById(R.id.Video_BeiSu_layout);
        Video_BeiSu_layout_Speed0_5=findViewById(R.id.Video_BeiSu_layout_Speed0_5);
        Video_BeiSu_layout_Speed1_0=findViewById(R.id.Video_BeiSu_layout_Speed1_0);
        Video_BeiSu_layout_Speed1_5=findViewById(R.id.Video_BeiSu_layout_Speed1_5);
        Video_BeiSu_layout_Speed2_0=findViewById(R.id.Video_BeiSu_layout_Speed2_0);

        Video_BeiSu_layout_Speed0_5.setOnClickListener(this);
        Video_BeiSu_layout_Speed1_0.setOnClickListener(this);
        Video_BeiSu_layout_Speed1_5.setOnClickListener(this);
        Video_BeiSu_layout_Speed2_0.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.Video_BeiSu_layout_Speed0_5) {
            setBSpeed(0.5f);
            Video_BeiSu_layout_Speed0_5.setTextColor(Color.parseColor("#FF4E50"));
        }else if(i == R.id.Video_BeiSu_layout_Speed1_0){
            setBSpeed(1.0f);
            Video_BeiSu_layout_Speed1_0.setTextColor(Color.parseColor("#FF4E50"));
        }else if(i == R.id.Video_BeiSu_layout_Speed1_5){
            setBSpeed(1.5f);
            Video_BeiSu_layout_Speed1_5.setTextColor(Color.parseColor("#FF4E50"));
        }else if(i == R.id.Video_BeiSu_layout_Speed2_0){
            setBSpeed(2.0f);
            Video_BeiSu_layout_Speed2_0.setTextColor(Color.parseColor("#FF4E50"));
        }
    }


    public void setBSpeed(float fs){
        BSpeed=fs;
        mediaInterface.setSpeed(BSpeed);
        Video_BeiSu_layout.setVisibility(GONE);
        Video_BeiSu_layout_Speed0_5.setTextColor(Color.parseColor("#ffffff"));
        Video_BeiSu_layout_Speed1_0.setTextColor(Color.parseColor("#ffffff"));
        Video_BeiSu_layout_Speed1_5.setTextColor(Color.parseColor("#ffffff"));
        Video_BeiSu_layout_Speed2_0.setTextColor(Color.parseColor("#ffffff"));
    }

    @Override/**正常状态**/
    public void changeUiToNormal() {
        super.changeUiToNormal();
        Log.d("zwsss","changeUiToNormal---正常状态（还未开始）"+screen);
    }

    @Override/**准备状态**/
    public void changeUiToPreparing() {
        super.changeUiToPreparing();
        Log.d("zwsss","changeUiToPreparing---准备状态（播放视频加载中）");
    }

    @Override/**播放状态**/
    public void changeUiToPlayingShow() {
        super.changeUiToPlayingShow();
        Log.d("zwsss","changeUiToPlayingShow---播放控件（控件显示）");
        MyVideo_BeiSu.setVisibility(VISIBLE);
    }

    @Override/**播放状态**/
    public void changeUiToPlayingClear() {
        super.changeUiToPlayingClear();
        Log.d("zwsss","changeUiToPlayingClear---播放控件（控件隐藏）");
        MyVideo_BeiSu.setVisibility(INVISIBLE);
    }

    @Override/**播放暂停**/
    public void changeUiToPauseShow() {
        super.changeUiToPauseShow();
        Log.d("zwsss","changeUiToPauseShow---播放暂停");
    }

    @Override
    public void changeUiToPauseClear() {
        super.changeUiToPauseClear();
        Log.d("zwsss","changeUiToPauseClear---？？？");
    }

    @Override/**播放完成**/
    public void changeUiToComplete() {
        super.changeUiToComplete();
        MyVideo_BeiSu.setVisibility(INVISIBLE);
        Video_BeiSu_layout.setVisibility(GONE);
        if (videoCallBack!=null){
            videoCallBack.startComplete();
        }
        Log.d("zwsss","changeUiToComplete---播放完成");
    }

    @Override/**播放错误**/
    public void changeUiToError() {
        super.changeUiToError();
        Log.d("zwsss","changeUiToError---播放错误");
    }

    @Override
    public void onInfo(int what, int extra) {
        super.onInfo(what, extra);
        if (videoCallBack!=null){
            videoCallBack.startVideo(what);
            setBSpeed(1.0f);
            Video_BeiSu_layout_Speed1_0.setTextColor(Color.parseColor("#FF4E50"));
        }
        Log.d("zwsss","onInfo---信息：what("+what+")___extra("+extra+")");
    }

    @Override
    public void onError(int what, int extra) {
        super.onError(what, extra);
        Log.d("zwsss","onError---？？？");
    }


    /**普通状态**/
    @Override
    public void onStateNormal() {
        super.onStateNormal();
        Log.d("zwsss","1");
    }
    /**准备状态**/
    @Override
    public void onStatePreparing() {
        super.onStatePreparing();
        Log.d("zwsss","2");
    }
    /**播放状态**/
    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
        Log.d("zwsss","3");
    }
    /**暂停状态**/
    @Override
    public void onStatePause() {
        super.onStatePause();
        Log.d("zwsss","4");
    }
    /**错误状态**/
    @Override
    public void onStateError() {
        super.onStateError();
        Log.d("zwsss","5");
    }
    /**自动播放状态**/
    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
        Log.d("zwsss","6");
    }

    public VideoCallBack getVideoCallBack() {
        return videoCallBack;
    }

    public void setVideoCallBack(VideoCallBack videoCallBack) {
        this.videoCallBack = videoCallBack;
    }

    public interface VideoCallBack{
        void startVideo(int i);
        void startComplete();
    }
}
