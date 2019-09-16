package com.cnitpm.z_me.Me;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Custom.Dialog.DialogUtil;
import com.cnitpm.z_common.GlideUtil;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.Model.UserMessage;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SharedPreferencesHelper;
import com.cnitpm.z_common.SimpleFragmentAdapter;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapter1;
import com.cnitpm.z_common.UtilRecyclerAdapter.SimpleRecyclerViewAdapterCallback;
import com.cnitpm.z_me.R;

import java.util.Arrays;
import java.util.List;

public class MePresenter extends BasePresenter<MeView> implements View.OnClickListener {
    private UserMessage userMessage;

    @Override
    public void init() {
        userMessage=SimpleUtils.getUserMessage();
        if(userMessage!=null){
            setView();
            click();
        }
    }

    @Override
    public void setView() {

        int[] ints={R.layout.me_recycler_item1,R.layout.me_recycler_item2};
        List<AllDataState> strings= Arrays.asList(new AllDataState(" 学习系统",0),
                new AllDataState(" 答题记录",1),
                new AllDataState(" 错题记录",1),
                new AllDataState(" 视频缓存",1),
                new AllDataState(" 账户管理",0),
                new AllDataState(" 我的消息",1),
                new AllDataState(" 修改密码",1),
                new AllDataState(" 退出登录",1));
        SimpleRecyclerViewAdapter1 simpleRecyclerViewAdapter1=new SimpleRecyclerViewAdapter1(ints, strings, (helper, item) -> {
            AllDataState allDataState= (AllDataState) item;
            helper.setText(R.id.Me_Recycler_text,(String)allDataState.getData());
            if (allDataState.getItemType()==1){
                int i=R.mipmap.me_recycler_item1;
                int i1=R.mipmap.right;
                switch (helper.getAdapterPosition()){
                    case 1:i=R.mipmap.me_recycler_item2;break;
                    case 2:i=R.mipmap.me_recycler_item1;break;
                    case 3:i=R.mipmap.huancun;break;
                    case 4:i=R.mipmap.me_recycler_item4;break;
                    case 5:i=R.mipmap.me_recycler_item5;break;
                    case 6:i=R.mipmap.me_recycler_item3;break;
                }
                Drawable drawable=mvpView.getThisActivity().getDrawable(i);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                Drawable drawable1=mvpView.getThisActivity().getDrawable(i1);
                drawable1.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                ((TextView)helper.getView(R.id.Me_Recycler_text)).setCompoundDrawables(drawable, null, drawable1, null);
            }else {

            }
        });
        mvpView.getMe_Recycler().setAdapter(simpleRecyclerViewAdapter1);
        mvpView.getMe_Recycler().setLayoutManager(SimpleUtils.getRecyclerLayoutManager1(true,0,false));
        simpleRecyclerViewAdapter1.setOnItemClickListener((adapter, view, position) -> {
            switch (position){
                case 1:
                    RoutePageActivity.getQuestions_RecordActivity();break;
                case 2:
                    RoutePageActivity.getErrorTopicActivity();break;
                case 3:
                    RoutePageActivity.getDownLoadPageActivity();break;
                case 5:
                    RoutePageActivity.getMessageActivity();break;
                case 6:
                    RoutePageActivity.getUpdatePassActivity();break;
                case 7:
                    new DialogUtil().show("退出登录","是否退出当前账户？",mvpView.getActivityContext(),"确定", new DialogUtil.DialogButtonListener() {
                        @Override
                        public void sure() {
                            SimpleUtils.removeUserMessage();
                            mvpView.getMainPageJump().PageJump(0,0);
                            SimpleUtils.setToast("退出登录成功！");
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                    break;
            }
        });

        mvpView.getMe_Name().setText(userMessage.getUVAO().getUsername());
        mvpView.getMe_UserIdentity().setText(userMessage.getSf());
        SimpleUtils.setLog("用户头像"+userMessage.getUVAO().getUserpic());
        if (userMessage.getUVAO().getUserpic()==null||userMessage.getUVAO().getUserpic().equals("null")||userMessage.getUVAO().getUserpic().equals("")){
            GlideUtil.ObjectCircleGlide(mvpView.getThisActivity(),R.mipmap.touxiang,mvpView.getMe_Image());
        }else {
            GlideUtil.ObjectCircleGlide(mvpView.getThisActivity(),userMessage.getUVAO().getUserpic().equals("null"),mvpView.getMe_Image());
        }
    }

    @Override
    public void CloseRequest() {

    }

    private void click(){
        mvpView.getMe_other_item1().setOnClickListener(this);
        mvpView.getMe_other_item2().setOnClickListener(this);
        mvpView.getMe_other_item3().setOnClickListener(this);
        mvpView.getMe_other_item4().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.Me_other_item1) {
            mvpView.getMainPageJump().PageJump(3,0);
        }else if (i == R.id.Me_other_item2) {
            mvpView.getMainPageJump().PageJump(3,1);
        }else if (i == R.id.Me_other_item3) {
            mvpView.getMainPageJump().PageJump(3,2);
        }else if (i == R.id.Me_other_item4) {
           RoutePageActivity.getPageActivity("https://m.cfeks.com/login.aspx?url=/user/Personal.aspx");
            // mvpView.getMainPageJump().PageJump(2,0);
        }
    }

}
