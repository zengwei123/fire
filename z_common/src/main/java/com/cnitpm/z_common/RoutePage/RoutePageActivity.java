package com.cnitpm.z_common.RoutePage;

import android.os.Looper;

import com.airbnb.lottie.animation.content.Content;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cnitpm.z_base.BaseActivity;
import com.cnitpm.z_common.SimpleUtils;

/**
 * 用来管理activity的路由
 */
public class RoutePageActivity {
    /**主界面**/
    public static void getMainPage(){
        ARouter.getInstance().build("/mains/MainPage").navigation();
    }

    /**每日一练**/
    public static void getDayPracticeActivity(){
        ARouter.getInstance().build("/Apage/DayPracticeActivity").navigation();
    }

    /**资讯**/
    public static void getNewsItemActivity(){
        ARouter.getInstance().build("/HomeItem/NewsActivity").navigation();
    }

    /**备考禁言**/
    public static void getReferenceActivity(){
        ARouter.getInstance().build("/HomeItem/ReferenceActivity").navigation();
    }

    /**备考禁言**/
    public static void getParsingActivity(){
        ARouter.getInstance().build("/Question/ParsingActivity").navigation();
    }

    /**历年真题**/
    public static void getTrueTopicActivity(){
        ARouter.getInstance().build("/Question/TrueTopicActivity").navigation();
    }

    /**支付界面**/
    public static void getPayPageActivity(int paytype){
        ARouter.getInstance().build("/Home/PayPageActivity").withInt("paytype",paytype).navigation();
    }

    /**登录界面**/
    public static void getLoginActivity(){
        ARouter.getInstance().build("/Login/LoginActivity").navigation();
    }

    /**忘记密码**/
    public static void getForgetPageActivity(){
        ARouter.getInstance().build("/LR/ForgetPageActivity").navigation();
    }

    /**注册界面**/
    public static void getRegisteredActivity(){
        ARouter.getInstance().build("/LR/RegisteredActivity").navigation();
    }

    /**章节练习**/
    public static void getChapterActivity(){
        ARouter.getInstance().build("/Subjects/ChapterActivity").navigation();
    }

    /**考试页面资讯  科目资讯**/
    public static void getJZAActivity(int DateType){
        ARouter.getInstance().build("/Subjects/JZAActivity")
                .withInt("DataType", DateType).navigation();
    }

    /**模拟试题**/
    public static void getSimulationActivity(){
        ARouter.getInstance().build("/Question/SimulationActivity").navigation();
    }

    /**修改密码**/
    public static void getUpdatePassActivity(){
        ARouter.getInstance().build("/Me/UpdatePassActivity").navigation();
    }

    /**答题记录**/
    public static void getQuestions_RecordActivity(){
        ARouter.getInstance().build("/Me/Questions_RecordActivity").navigation();
    }

    /**错题记录**/
    public static void getErrorTopicActivity(){
        ARouter.getInstance().build("/Me/ErrorTopicActivity").navigation();
    }

    /**会员消息(需要拦截)**/
    public static void getMessageActivity(){
        ARouter.getInstance().build("/Main/MessageActivity")
                .navigation(BaseActivity.getInstance(), new MyNavigationCallback());
    }

    /**会员内容**/
    public static void getMessageContentActivity(int id){
        ARouter.getInstance().build("/Main/MessageContentPage")
                .withInt("id", id)
                .navigation();
    }

    /**搜索界面**/
    public static void getSearchActivity(){
        ARouter.getInstance().build("/Main/SearchActivity").navigation();
    }

    /**h5界面**/
    public static void getPageActivity(String url){
        ARouter.getInstance().build("/webpage/PageActivity")
                .withString("url", url)
                .navigation();

    }

    /**视频播放界面**/
    public static void getVideoActivity(int type,int vsid,int gq){
        ARouter.getInstance().build("/Course/VideoActivity")
                .withInt("type",type)
                .withInt("vsid",vsid)
                .withInt("gq",gq)
                .navigation();
    }

    /**视频播放界面**/
    public static void getDownLoadPageActivity(){
        ARouter.getInstance().build("/Me/DownLoadPageActivity").navigation();
    }



    /**拦截器的回调**/
    private static class MyNavigationCallback implements NavigationCallback{
        @Override
        public void onFound(Postcard postcard) {
        }

        @Override
        public void onLost(Postcard postcard) {
        }

        @Override
        public void onArrival(Postcard postcard) {
        }

        @Override
        public void onInterrupt(Postcard postcard) {
            /***当终端跳转之后 就是需要登录*/
            getLoginActivity();
            Looper.prepare();
            SimpleUtils.setToast("暂未登录，请先登录！");
            Looper.loop();
        }
    }




}
