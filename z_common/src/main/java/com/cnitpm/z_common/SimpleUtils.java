package com.cnitpm.z_common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.utils.Utils;
import com.cnitpm.z_base.BaseActivity;
import com.cnitpm.z_common.Custom.CustomLinearLayoutManager;
import com.cnitpm.z_common.Custom.CustomLinearLayoutManager1;
import com.cnitpm.z_common.Custom.Dialog.DialogUtil;
import com.cnitpm.z_common.Model.UserMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zengwei on 2019/7/21.
 * 基础的一些工具
 */

public class SimpleUtils {

    public static String IP="https://m.cfeks.com/";
    /**列表的布局方式**/
    public static RecyclerView.LayoutManager getRecyclerLayoutManager(boolean isLG,int number ){
        if (isLG){
            return new LinearLayoutManager(BaseActivity.getInstance());
        }else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(BaseActivity.getInstance(), number);
            //设置表格，根据position计算在该position处1列占几格数据
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override public int getSpanSize(int position) {
                    return 1;
                }
            });
            return gridLayoutManager;
        }
    }

    /**禁止滑动**/
    public static RecyclerView.LayoutManager getRecyclerLayoutManager1(boolean isLG,int number,boolean noScroll ){
        if (isLG){
            CustomLinearLayoutManager customLinearLayoutManager=new CustomLinearLayoutManager(BaseActivity.getInstance());
            customLinearLayoutManager.setScrollEnabled(noScroll);
            return customLinearLayoutManager;
        }else {
            CustomLinearLayoutManager1 gridLayoutManager = new CustomLinearLayoutManager1(BaseActivity.getInstance(), number);
            //设置表格，根据position计算在该position处1列占几格数据
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override public int getSpanSize(int position) {
                    return 1;
                }
            });
            gridLayoutManager.setScrollEnabled(noScroll);
            return gridLayoutManager;
        }
    }

    /**也是列表的布局方式  瀑布流**/
    public static RecyclerView.LayoutManager getRecyclerLayoutManager(int number){
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(number, StaggeredGridLayoutManager.VERTICAL);
        return layoutManager;
    }

    /**设置字体图标**/
    public static void setViewTypeface(Object View,String str){
        Typeface typeface=Typeface.createFromAsset(BaseActivity.getInstance().getAssets(),"font/icomoon.ttf");
        if (View instanceof TextView){
            ((TextView)View).setTypeface(typeface);
            ((TextView)View).setText(str);
        }else if (View instanceof EditText){
            ((EditText)View).setTypeface(typeface);
            ((EditText)View).setText(str);
        }
    }

    /**获取版本号**/
    public static String getAppVersion(){
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = BaseActivity.getInstance().getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(BaseActivity.getInstance().getPackageName(),0);
            String version = packInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            //出错返回默认1.0版本
            return "1.0";
        }
    }

    /**权限获取**/
    public static void getPermissions(){
        String[] strings={Permission.READ_EXTERNAL_STORAGE, Permission. WRITE_EXTERNAL_STORAGE};
        XXPermissions.with(BaseActivity.getInstance())
//                .permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES) //支持请求6.0悬浮窗权限8.0请求安装权限
                .permission(strings) //不指定权限则自动获取清单中的危险权限
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (!isAll){
                            DialogUtil dialogUtil = new DialogUtil();
                            dialogUtil.show("提示","重要权限未授权部分功将会受限，可能会影响您的使用体验，是否手动开启权限？",BaseActivity.getInstance() ,"设置",new DialogUtil.DialogButtonListener() {
                                @Override
                                public void sure() {
                                    XXPermissions.gotoPermissionSettings(BaseActivity.getInstance());
                                }

                                @Override
                                public void cancel() {
                                }
                            });
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });
    }

    /**获取屏幕的宽高**/
    public static int getWindowSize(boolean b) {
        WindowManager manager = (WindowManager) BaseActivity.getInstance().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        if (b){
            return dm.widthPixels;
        }else {
            return dm.heightPixels;
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**控制 log**/
    public static void setLog(String str){
        Log.d("zengwei123",str);
    }

    /**控制 Toast**/
    public static void setToast(String str){
        Toast.makeText(BaseActivity.getInstance(), str, Toast.LENGTH_SHORT).show();
    }

    public static String code="2f283cd78617ed92e1";
    /**请求参数签名**/
    public static String getSign(LinkedHashMap<String,String> map){
        StringBuilder stringBuilder=new StringBuilder();
        for(String key : map.keySet()){
            stringBuilder.append("code="+ code+"&");
            String value =  map.get(key);
            stringBuilder.append(key+"="+value+"&");
        }
        stringBuilder.setLength(stringBuilder.length()-1);
        return toMD5(stringBuilder.toString());
    }
    public static String toMD5(String text) {
        //获取摘要器 MessageDigest
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            //通过摘要器对字符串的二进制字节数组进行hash计算
            byte[] digest = messageDigest.digest(text.getBytes());

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                //循环每个字符 将计算结果转化为正整数;
                int digestInt = digest[i] & 0xff;
                //将10进制转化为较短的16进制
                String hexString = Integer.toHexString(digestInt);
                //转化结果如果是个位数会省略0,因此判断并补0
                if (hexString.length() < 2) {
                    sb.append(0);
                }
                //将循环结果添加到缓冲区
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //返回整个结果
        return null;
    }

    /**手机格式验证**/
    private static Pattern pattern;
    public static boolean IsPhone(String str){
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (str.length() != 11) {
            return false;
        } else {
            pattern = Pattern.compile(regex);
            Matcher m = pattern.matcher(str);
            return m.matches();
        }
    }

    /**获取用户信息**/
    public static UserMessage getUserMessage(){
        return UserFule.GetUser();
    }

    /**删除用户信息**/
    public static void removeUserMessage(){
        UserFule.RemoveUser();
    }

    /**获取前月份**/
    public static String[][] getMonth(int nub){
        Calendar cal= Calendar.getInstance();
        String[][] ints=new String[nub][2];
        for (int i=0;i<nub;i++){
            if (i==0){
                cal.add(Calendar.MONTH,0);
            }else {
                cal.add(Calendar.MONTH,-1);
            }
            ints[i][0]=cal.get(Calendar.YEAR)+"";
            if (cal.get(Calendar.MONTH)+1<10){
                ints[i][1]="0"+(cal.get(Calendar.MONTH)+1)+"";
            }else {
                ints[i][1]=(cal.get(Calendar.MONTH)+1)+"";
            }
        }
        return ints;
    }
}
