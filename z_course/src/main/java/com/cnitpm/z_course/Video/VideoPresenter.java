package com.cnitpm.z_course.Video;

import android.graphics.Color;
import android.graphics.Movie;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.GlideUtil;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.MyNotificationUtils;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapterCallback;
import com.cnitpm.z_course.Download.AnyRunnModule;
import com.cnitpm.z_course.Model.VFile;
import com.cnitpm.z_course.Model.VideoModel;
import com.cnitpm.z_course.Net.CourseRequestServiceFactory;
import com.cnitpm.z_course.R;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jzvd.JzvdStd;
import io.reactivex.disposables.Disposable;

public class VideoPresenter extends BasePresenter<VideoView> implements View.OnClickListener {
    private int selectVideoid=0;
    private boolean isPlay=false;
    private SimpleRecyclerViewAdapter simpleRecyclerViewAdapter;
    /**选中map**/
    Map<Integer,String> map=new HashMap<>();
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {
        click();
        setRecycler(mvpView.gettype(),mvpView.getvsid(),mvpView.getgq());
        mvpView.getInclude_Title_Text().setText("");
    }

    @Override
    public void CloseRequest() {

    }

    private void setVideoUrl(String url,String title,boolean play){
        mvpView.getVideo_MyVideoView().setUp(url,title);
        if (play){
            mvpView.getVideo_MyVideoView().startButton.performClick();
        }
    }
    /**播放列表**/
    private void setRecycler(int type,int vsid,int gq){
        CourseRequestServiceFactory.mob_play(new RequestObserver.RequestObserverNext<AllDataState<VideoModel>>() {
            @Override
            public void Next(AllDataState<VideoModel> o) {
                /**设置标题**/
                if (mvpView.getgq()==1){
                    mvpView.getInclude_Title_Text().setText(o.getData().getClassTitle()+"(高清)");
                }else {
                    mvpView.getInclude_Title_Text().setText(o.getData().getClassTitle());
                }
                /**播放的标题**/
                List<String> strings=new ArrayList<>();
                for (int i=0;i<o.getData().getMobno();i++){
                    if (mvpView.getgq()==1){
                        strings.add(o.getData().getClassTitle()+"("+(i+1)+")(高清)");
                    }else {
                        strings.add(o.getData().getClassTitle()+"("+(i+1)+")");
                    }
                }
                /**默认设置第一个视频**/
                String urls="http://"+o.getData().getMediaurl()+"/"+o.getData().getPtorgq()+"/"+o.getData().getFilename()+"1.mp4";
                setVideoUrl(urls,strings.get(selectVideoid),false);
                /**加载视频列表**/
                SimpleRecyclerViewAdapter simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.video_recycler_item, mvpView.getActivityContext(), strings, (helper, item) -> {
                    helper.setText(R.id.Video_Recycler_Item,(String)item);
                    /**当前item是否是选中item**/
                    if (helper.getAdapterPosition()==selectVideoid){
                        /**字体颜色设置为红**/
                        ((TextView)helper.getView(R.id.Video_Recycler_Item)).setTextColor(Color.parseColor("#FF4E50"));
                        /**判断是否再播放 显示图标**/
                        if (!isPlay){
                            GlideUtil.drawableImage(52,R.mipmap.bofang,helper.getView(R.id.Video_Recycler_Item),false);
                        }else {
                            GlideUtil.drawableImage(52,R.mipmap.zanting,helper.getView(R.id.Video_Recycler_Item),false);
                        }
                    }else {
                        /**不是当前item**/
                        ((TextView)helper.getView(R.id.Video_Recycler_Item)).setTextColor(Color.parseColor("#2b2b2b"));
                        GlideUtil.drawableImage(52,R.mipmap.bofang,helper.getView(R.id.Video_Recycler_Item),false);
                    }
                });
                mvpView.getVideo_RecyclerView().setAdapter(simpleRecyclerViewAdapter);
                mvpView.getVideo_RecyclerView().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));
                /**item点击事件**/
                simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
                    /**点击的不是当前item  就播放下一个视频**/
                    if (selectVideoid!=position){
                        String url="http://"+o.getData().getMediaurl()+"/"+o.getData().getPtorgq()+"/"+o.getData().getFilename()+(position+1)+".mp4";
                        setVideoUrl(url,strings.get(selectVideoid),true);
                        selectVideoid=position;
                    }else {
                        /**再次点击了当前视频 提示正在播放**/
                        SimpleUtils.setToast("正在播放此视频");
                    }
                });
                /**视频的回调事件**/
                mvpView.getVideo_MyVideoView().setVideoCallBack(new MyVideoView.VideoCallBack() {
                    /**开始播放**/
                    @Override
                    public void startVideo(int i) {
                        if (i==3){
                            isPlay=true;
                            /**刷新列表**/
                            simpleRecyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                    /**播放完成**/
                    @Override
                    public void startComplete() {
                        isPlay=false;
                        simpleRecyclerViewAdapter.notifyDataSetChanged();
                    }
                });
                /**下载视频的设置**/
                setReyclcerXiaZai(strings,o.getData());
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },mvpView.getActivityContext(),type,vsid,gq);
    }
    /**下载列表**/
    private void setReyclcerXiaZai(List<String> s,VideoModel videoModel){
        simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.video_recycler_item1, mvpView.getActivityContext(), s, (helper, item) -> {
            /**获取生成的id**/
            String mid=null;
            if (mvpView.getgq()==0){
                mid="B"+mvpView.getvsid()+(helper.getAdapterPosition()+1);
            }else {
                mid="G"+mvpView.getvsid()+(helper.getAdapterPosition()+1);
            }
            /**判断是否下载**/
            if (isdownload(mid)){
                /**选中与未选中的样式**/
                if (map.containsKey(helper.getAdapterPosition()+1)){
                    helper.getView(R.id.Video_Recycler_Item_TextId).setBackgroundResource(R.drawable.textid_style2);
                    ((TextView)helper.getView(R.id.Video_Recycler_Item_TextId)).setTextColor(Color.parseColor("#ffffff"));
                }else {
                    helper.getView(R.id.Video_Recycler_Item_TextId).setBackgroundResource(R.drawable.textid_style);
                    ((TextView)helper.getView(R.id.Video_Recycler_Item_TextId)).setTextColor(Color.parseColor("#D9D9D9"));
                }
            }else {
                /**已经下载**/
                helper.getView(R.id.Video_Recycler_Item_TextId).setBackgroundResource(R.drawable.textid_style3);
                ((TextView)helper.getView(R.id.Video_Recycler_Item_TextId)).setTextColor(Color.parseColor("#2b2b2b"));
            }
            helper.setText(R.id.Video_Recycler_Item_TextId,(helper.getAdapterPosition()+1)+"");
        });
        mvpView.getVideo_Recycler_XiaZai().setAdapter(simpleRecyclerViewAdapter);
        mvpView.getVideo_Recycler_XiaZai().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(false,5));
        /**选择需要下载的视频**/
        simpleRecyclerViewAdapter.setOnItemClickListener((adapter, view, position) -> {
            /**获取生成的id**/
            String mid=null;
            if (mvpView.getgq()==0){
                mid="B"+mvpView.getvsid()+(position+1);
            }else {
                mid="G"+mvpView.getvsid()+(position+1);
            }
            if (isdownload(mid)){
                if (map.containsKey(position+1)){
                    map.remove(position+1);
                }else {
                    String url="http://"+videoModel.getMediaurl()+"/"+videoModel.getPtorgq()+"/"+videoModel.getFilename()+(position+1)+".mp4";
                    map.put(position+1,url);
                }
                simpleRecyclerViewAdapter.notifyDataSetChanged();
            }else {
                SimpleUtils.setToast("视频已缓存！无需再次缓存");
            }

        });
    }

    public void click(){
        mvpView.getVideo_MyVideoView().MyVideo_BeiSu.setOnClickListener(this);
        mvpView.getVideo_MyVideoView().Video_BeiSu_layout.setOnClickListener(this);
        mvpView.getInclude_Title_Close().setOnClickListener(this);
        mvpView.getVideo_XiaZai().setOnClickListener(this);
        mvpView.getVideo_Download_layout().setOnClickListener(this);
        mvpView.getVideo_Download_Text().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.MyVideo_BeiSu) {
            mvpView.getVideo_MyVideoView().Video_BeiSu_layout.setVisibility(View.VISIBLE);
            mvpView.getVideo_MyVideoView().changeUiToPlayingClear();
        }else if(i==R.id.Video_BeiSu_layout){
            mvpView.getVideo_MyVideoView().Video_BeiSu_layout.setVisibility(View.GONE);
        }else if (i==R.id.Include_Title_Close){
            mvpView.getThisActivity().finish();
        }else if(i==R.id.Video_XiaZai){
            simpleRecyclerViewAdapter.notifyDataSetChanged();
            mvpView.getVideo_Download_layout().setVisibility(View.VISIBLE);
        }else if (i==R.id.Video_Download_layout){
            mvpView.getVideo_Download_layout().setVisibility(View.GONE);
        }else if(i==R.id.Video_Download_Text){
            if (map.size()>0){
                if (mvpView.getgq()==0){
                    new AnyRunnModule(mvpView.getActivityContext(),map,mvpView.getInclude_Title_Text().getText().toString(),mvpView.getvsid(),true);
                }else {
                    new AnyRunnModule(mvpView.getActivityContext(),map,mvpView.getInclude_Title_Text().getText().toString(),mvpView.getvsid(),false);
                }
                mvpView.getVideo_Download_layout().setVisibility(View.GONE);
            }else {
                SimpleUtils.setToast("暂无选中视频，无法下载");
            }
        }
    }
    /**查询视频是否下载**/
    private boolean isdownload(String mid){
        List<VFile> vFiles = LitePal.where("mid  = ?", mid).find(VFile.class);
        if (vFiles.size()!=0){
            return false;
        }else {
            return true;
        }
    }
}
