package com.cnitpm.z_common.RoutePage;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.cnitpm.z_common.Model.UserMessage;
import com.cnitpm.z_common.SimpleUtils;

@Interceptor(priority = 8, name = "登录拦截器")
public class PageInterceptor implements IInterceptor {
    private static String[] pages={"/Main/MessageActivity",""};

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        UserMessage userMessage=SimpleUtils.getUserMessage();
        if (userMessage==null){
            for (String s:pages){
                if (postcard.getPath().equals(s)){
                    callback.onInterrupt(new RuntimeException("暂未登录，请先登录！"));
                }
            }
        }
        callback.onContinue(postcard);  // 处理完成，交还控制权
    }

    @Override
    public void init(Context context) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次

    }
}
