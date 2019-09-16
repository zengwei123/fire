package com.cnitpm.z_common.Custom.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.cnitpm.z_base.BaseActivity;
import com.cnitpm.z_common.R;

/**
 * Created by zengwei on 2019/8/4.
 */

public class LottieDialog {
    private static Dialog  dialog ;
    private static Window dialogWindow;
    private static WindowManager.LayoutParams lp;
    private static View  view;
    public static LottieAnimationView lottieAnimationView1;
    private static Context contexts;
    public static void showDialogView(){
        try {
            //偶然bug 窗口再dialog打开时候就已经关闭了
            dialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void stopDialogView(){
        dialog.dismiss();
    }

    public static void setDialogWindow(Context context){
        if (dialog==null){
            if (context!=null){
                dialog = new Dialog(context, R.style.NormalDialogStyle);
                dialogWindow = dialog.getWindow();
                lp = dialogWindow.getAttributes();

                view = View.inflate(BaseActivity.getInstance(), R.layout.z_dialog_lottie, null);
                lottieAnimationView1=view.findViewById(R.id.animation_view1);
                dialog.setContentView(view);
                dialog.setCanceledOnTouchOutside(true);
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
            }else {
                dialog=null;
            }
        }
    }

    public static Dialog getDialog() {
        return dialog;
    }

    public static void setDialog(Dialog dialog) {
        LottieDialog.dialog = dialog;
    }
}
