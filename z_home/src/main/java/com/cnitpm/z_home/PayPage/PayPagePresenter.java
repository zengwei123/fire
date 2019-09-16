package com.cnitpm.z_home.PayPage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_home.Model.pay_inform;
import com.cnitpm.z_home.Net.HomeRequestServiceFactory;
import com.cnitpm.z_home.R;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnUrlClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class PayPagePresenter extends BasePresenter<PayPageView> implements View.OnClickListener {
    private boolean iszw=true;  //支付宝or微信
    private int select=0;
    private List<pay_inform> pay_informs;
    private String uid=null;
    @Override
    public void init() {
        mvpView.getInclude_Title_Text().setText("在线支付");
        mvpView.getInclude_Title_Close().setOnClickListener(v -> mvpView.getThisActivity().finish());
        setView();
        Click();
    }

    @Override
    public void setView() {
        if (SimpleUtils.getUserMessage()!=null){
            mvpView.getPay_UserName().setText(SimpleUtils.getUserMessage().getUVAO().getUsername());
            uid=SimpleUtils.getUserMessage().getUVAO().getUid()+"";
        }
        spinnerRecycler();

        RichText.fromHtml("1、如果快捷支付有限额，请选择网上银行支付。<br/>" +
                                "2、如果超过单笔支付限额，可以分多次进行支付。<br/>" +
                                "3、由于属于电子服务类产品，付款后不再办理退款。<br/>" +
                                "4、如果您在支付中遇到问题，请<a href=''>点击这里</a>联系在线客服")
    .urlClick(url ->{
        RoutePageActivity.getPageActivity("https://shang.qq.com/open_webaio.html?sigt=c2a1fa1d3246eedea5273e5820423c1117b8b9bc7ca75c96242cdb47e4240c754a2d523c3d3a815088fd9bddf34a33e0&sigu=7d9c3d2448480794f8f52e01dde82c63a2a40c76bd19e3f8a48a557df223874fc19550064cf44676&tuin=215970226");
        return true;
    }).into(mvpView.getPay_paytext());
    }

    @Override
    public void CloseRequest() {

    }

    private void Click(){
        mvpView.getPay_weixin().setOnClickListener(this);
        mvpView.getPay_zhifubao().setOnClickListener(this);
        mvpView.getPay_weixins().setOnClickListener(this);
        mvpView.getPay_zhifubaos().setOnClickListener(this);
        mvpView.getPay_Determine().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.Pay_weixin) {
            mvpView.getPay_zhifubao().setChecked(false);
            mvpView.getPay_weixin().setChecked(true);
            iszw=false;
        }else if(i == R.id.Pay_zhifubao){
            mvpView.getPay_zhifubao().setChecked(true);
            mvpView.getPay_weixin().setChecked(false);
            iszw=true;
        }else if(i == R.id.Pay_weixins){
            mvpView.getPay_zhifubao().setChecked(false);
            mvpView.getPay_weixin().setChecked(true);
            iszw=false;
        } else if(i == R.id.Pay_zhifubaos){
            mvpView.getPay_zhifubao().setChecked(true);
            mvpView.getPay_weixin().setChecked(false);
            iszw=true;
        }else if(i==R.id.Pay_Determine){
            if (iszw){
                HomeRequestServiceFactory.alipay(pay_informs.get(select).getType(),
                        mvpView.getPay_UserName().getText().toString().trim(),
                        mvpView.getPay_Name().getText().toString().trim(),uid, mvpView.getActivityContext());
            }else {
                if (isWeChatAppInstalled(mvpView.getActivityContext())){
                    HomeRequestServiceFactory.WxPay(pay_informs.get(select).getType(),
                            mvpView.getPay_UserName().getText().toString().trim(),
                            mvpView.getPay_Name().getText().toString().trim(),uid, mvpView.getActivityContext());
                }else {
                    SimpleUtils.setToast("未安装微信客户端无法支付");
                }
            }
        }
    }

    private void spinnerRecycler(){
        HomeRequestServiceFactory.pay_inform(new RequestObserver.RequestObserverNext<AllDataState<List<pay_inform>>>() {
            @Override
            public void Next(AllDataState<List<pay_inform>> o) {
                pay_informs=o.getData();
                List<String> strings=new ArrayList<>();
                for (int i=0;i<o.getData().size();i++){
                    strings.add(o.getData().get(i).getPayname()+"("+o.getData().get(i).getPrice()+"元)");
                    if (o.getData().get(i).getType()==mvpView.getpaytype()){
                        select=i;
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter(mvpView.getActivityContext(), android.R.layout.simple_spinner_dropdown_item, strings);

                mvpView.getPay_Spinner().setAdapter(adapter);
                mvpView.getPay_Spinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        select=position;
                        mvpView.getPay_services().setText(o.getData().get(position).getServices());
                        RichText.fromHtml("共计：<font color='#ff0000'>￥"+o.getData().get(position).getPrice()+"元</font>").into(mvpView.getPay_Price());
                        mvpView.getPay_Price().setText(o.getData().get(position).getServices());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                mvpView.getPay_Spinner().setSelection(select);
            }

            @Override
            public void onError() {

            }

            @Override
            public void getDisposable(Disposable d) {

            }
        },mvpView.getActivityContext());
    }
    public boolean isWeChatAppInstalled(Context context) {
        IWXAPI api = WXAPIFactory.createWXAPI(context, "Your WeChat AppId");
        if(api.isWXAppInstalled()) {
            return true;
        } else {
            final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
            List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
            if (pinfo != null) {
                for (int i = 0; i < pinfo.size(); i++) {
                    String pn = pinfo.get(i).packageName;
                    if (pn.equalsIgnoreCase("com.tencent.mm")) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
