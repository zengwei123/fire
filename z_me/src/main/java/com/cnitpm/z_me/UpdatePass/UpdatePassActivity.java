package com.cnitpm.z_me.UpdatePass;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_me.R;
import com.zzhoujay.richtext.RichText;

@Route(path = "/Me/UpdatePassActivity")
public class UpdatePassActivity extends MvpActivity<UpdatePassPresenter> implements UpdatePassView {
    private ImageView Include_Title_Close;
    private TextView Include_Title_Text;

    private EditText UpdatePass_Old;
    private EditText UpdatePass_Nwe1;
    private EditText UpdatePass_Nwe2;
    private TextView UpdatePass_Submits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatepass_layout);
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
    protected UpdatePassPresenter createPresenter() {
        return new UpdatePassPresenter();
    }

    @Override
    public void getViews() {
        Include_Title_Close=findViewById(R.id.Include_Title_Close);
        Include_Title_Text=findViewById(R.id.Include_Title_Text);

        UpdatePass_Old=findViewById(R.id.UpdatePass_Old);
        UpdatePass_Nwe1=findViewById(R.id.UpdatePass_Nwe1);
        UpdatePass_Nwe2=findViewById(R.id.UpdatePass_Nwe2);
        UpdatePass_Submits=findViewById(R.id.UpdatePass_Submits);
    }

    @Override
    public ImageView getInclude_Title_Close() {
        return Include_Title_Close;
    }

    @Override
    public TextView getInclude_Title_Text() {
        return Include_Title_Text;
    }

    @Override
    public EditText getUpdatePass_Old() {
        return UpdatePass_Old;
    }

    @Override
    public EditText getUpdatePass_Nwe1() {
        return UpdatePass_Nwe1;
    }

    @Override
    public EditText getUpdatePass_Nwe2() {
        return UpdatePass_Nwe2;
    }

    @Override
    public TextView getUpdatePass_Submits() {
        return UpdatePass_Submits;
    }
}
