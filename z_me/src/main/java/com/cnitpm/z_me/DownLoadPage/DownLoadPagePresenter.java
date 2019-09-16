package com.cnitpm.z_me.DownLoadPage;

import android.graphics.Color;
import android.graphics.Movie;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.GlideUtil;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_course.Model.VFile;
import com.cnitpm.z_me.R;

import org.litepal.LitePal;

import java.io.File;
import java.util.List;

public class DownLoadPagePresenter extends BasePresenter<DownLoadPageView> implements View.OnClickListener {
    private int selectVideoid=0;
    private boolean isPlay=false;
    private List<VFile> vFiles;
    @Override
    public void init() {
        setView();
        click();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("视频缓存");
        setRecycler(isdownload());
    }

    @Override
    public void CloseRequest() {

    }

    private void setVideoUrl(String url,String title,boolean play){
        mvpView.getDownLoadPage_MyVideoView().setUp(url,title);
        if (play){
            mvpView.getDownLoadPage_MyVideoView().startButton.performClick();
        }
    }

    public void setRecycler(List<VFile> files){
        /**加载视频列表**/
        SimpleRecyclerViewAdapter simpleRecyclerViewAdapter=new SimpleRecyclerViewAdapter(R.layout.downloadpage_recycler_item, mvpView.getActivityContext(), files, (helper, item) -> {
            VFile vFile= (VFile) item;
            helper.setText(R.id.Video_Recycler_Item,vFile.getName());
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
            helper.addOnClickListener(R.id.right).addOnClickListener(R.id.content);
        });

        mvpView.getDownLoadPage_RecyclerView().setAdapter(simpleRecyclerViewAdapter);
        mvpView.getDownLoadPage_RecyclerView().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));
        mvpView.getDownLoadPage_RecyclerView().setLayoutManager(SimpleUtils.getRecyclerLayoutManager(true,0));
        simpleRecyclerViewAdapter.setOnItemChildClickListener((adapter, view, position) -> {

            if (view.getId()==R.id.content){
                /**点击的不是当前item  就播放下一个视频**/
                if (selectVideoid!=position){
                    String url=vFiles.get(position).getPath();
                    setVideoUrl(url,vFiles.get(position).getName(),true);
                    selectVideoid=position;
                }else {
                    /**再次点击了当前视频 提示正在播放**/
                    SimpleUtils.setToast("正在播放此视频");
                }
            }else {
                if(LitePal.deleteAll(VFile.class, "mid = ?" , vFiles.get(position).getMid())>0){
                    deleteFile(vFiles.get(position).getPath());
                    SimpleUtils.setToast("删除成功");
                    vFiles.remove(position);
                }else {
                    SimpleUtils.setToast("删除失败");
                }
                simpleRecyclerViewAdapter.notifyDataSetChanged();
            }
        });



        /**视频的回调事件**/
        mvpView.getDownLoadPage_MyVideoView().setVideoCallBack(new MyVideoView.VideoCallBack() {
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
    }

    public void click(){
        mvpView.getDownLoadPage_MyVideoView().MyVideo_BeiSu.setOnClickListener(this);
        mvpView.getDownLoadPage_MyVideoView().Video_BeiSu_layout.setOnClickListener(this);
        mvpView.getInclude_Title_Close().setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.MyVideo_BeiSu) {
            mvpView.getDownLoadPage_MyVideoView().Video_BeiSu_layout.setVisibility(View.VISIBLE);
            mvpView.getDownLoadPage_MyVideoView().changeUiToPlayingClear();
        }else if(i == R.id.Video_BeiSu_layout){
            mvpView.getDownLoadPage_MyVideoView().Video_BeiSu_layout.setVisibility(View.GONE);
        }else if (i == R.id.Include_Title_Close){
            mvpView.getThisActivity().finish();
        }
    }

    /**查询视频是否下载**/
    private List<VFile> isdownload(){
        vFiles = LitePal.findAll(VFile.class);
        if (vFiles.size()!=0){
            String url=vFiles.get(0).getPath();
            setVideoUrl(url,vFiles.get(0).getName(),false);
        }
        return vFiles;
    }

    public boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
