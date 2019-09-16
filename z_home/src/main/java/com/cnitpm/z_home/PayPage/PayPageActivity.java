package com.cnitpm.z_home.PayPage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cnitpm.z_base.MvpActivity;
import com.cnitpm.z_home.R;
import com.zzhoujay.richtext.RichText;

@Route(path = "/Home/PayPageActivity")
public class PayPageActivity extends MvpActivity<PayPagePresenter> implements PayPageView {
    private TextView Include_Title_Text;
    private ImageView Include_Title_Close;

    private RadioButton Pay_zhifubao;
    private RadioButton Pay_weixin;

    private LinearLayout Pay_zhifubaos;
    private LinearLayout Pay_weixins;

    private Spinner Pay_Spinner;  //付款选项
    private TextView Pay_services;  //服务说明
    private TextView Pay_paytext;  //支付说明
    private TextView Pay_Price;  //支付金额

    private EditText Pay_UserName;  //用户名
    private EditText Pay_Name; //姓名
    private TextView Pay_Determine;  //提交订单
    @Autowired
    public int paytype;    //选择的付款选项id
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**启动页的图片设置为空**/
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paypage_layout);
        ARouter.getInstance().inject(this);
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
    protected PayPagePresenter createPresenter() {
        return new PayPagePresenter();
    }

    @Override
    public void getViews() {
        Pay_zhifubao=findViewById(R.id.Pay_zhifubao);
        Pay_weixin=findViewById(R.id.Pay_weixin);
        Pay_zhifubaos=findViewById(R.id.Pay_zhifubaos);
        Pay_weixins=findViewById(R.id.Pay_weixins);

        Pay_Spinner=findViewById(R.id.Pay_Spinner);
        Pay_services=findViewById(R.id.Pay_services);
        Pay_paytext=findViewById(R.id.Pay_paytext);
        Pay_Price=findViewById(R.id.Pay_Price);

        Pay_UserName=findViewById(R.id.Pay_UserName);
        Pay_Name=findViewById(R.id.Pay_Name);
        Pay_Determine=findViewById(R.id.Pay_Determine);

        Include_Title_Text=findViewById(R.id.Include_Title_Text);
        Include_Title_Close=findViewById(R.id.Include_Title_Close);
    }

    @Override
    public RadioButton getPay_zhifubao() {
        return Pay_zhifubao;
    }

    @Override
    public RadioButton getPay_weixin() {
        return Pay_weixin;
    }

    @Override
    public LinearLayout getPay_zhifubaos() {
        return Pay_zhifubaos;
    }

    @Override
    public LinearLayout getPay_weixins() {
        return Pay_weixins;
    }

    @Override
    public int getpaytype() {
        return paytype;
    }

    @Override
    public Spinner getPay_Spinner() {
        return Pay_Spinner;
    }

    @Override
    public TextView getPay_paytext() {
        return Pay_paytext;
    }

    @Override
    public TextView getPay_services() {
        return Pay_services;
    }

    @Override
    public TextView getPay_Price() {
        return Pay_Price;
    }

    @Override
    public EditText getPay_UserName() {
        return Pay_UserName;
    }

    @Override
    public EditText getPay_Name() {
        return Pay_Name;
    }

    @Override
    public TextView getPay_Determine() {
        return Pay_Determine;
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
