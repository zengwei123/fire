package com.cnitpm.z_question.Simulation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_question.R;
import com.zzhoujay.richtext.RichText;
/**模拟试题**/
@Route(path = "/Question/SimulationActivity")
public class SimulationActivity extends MvpActivity<SimulationPresenter> implements SimulationView {
    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;
    private RecyclerView Simulation_Recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simulation_layout);
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
    protected SimulationPresenter createPresenter() {
        return new SimulationPresenter();
    }

    @Override
    public void getViews() {
        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);
        Simulation_Recycler=findViewById(R.id.Simulation_Recycler);
    }

    @Override
    public RecyclerView getSimulation_Recycler() {
        return Simulation_Recycler;
    }
    @Override
    public TextView getInclude_Title_Text() {
        return Include_Title_Text;
    }

    @Override
    public ImageView getInclude_Title_Close() {
        return Include_Title_Close;
    }

}
