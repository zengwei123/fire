package com.cnitpm.z_me.UpdatePass;

import com.cnitpm.z_base.BaseActivity;
import com.cnitpm.z_base.BasePresenter;
import com.cnitpm.z_common.Model.AllDataState;
import com.cnitpm.z_common.NET.RequestObserver;
import com.cnitpm.z_common.RoutePage.RoutePageActivity;
import com.cnitpm.z_common.SimpleUtils;
import com.cnitpm.z_me.Net.MeRequestServiceFactory;

import io.reactivex.disposables.Disposable;

public class UpdatePassPresenter extends BasePresenter<UpdatePassView> {
    @Override
    public void init() {
        setView();
    }

    @Override
    public void setView() {
        mvpView.getInclude_Title_Text().setText("修改密码");
        mvpView.getInclude_Title_Close().setOnClickListener(v -> mvpView.getThisActivity().finish());
        mvpView.getUpdatePass_Submits().setOnClickListener(v -> {
            MeRequestServiceFactory.MyPassWord(new RequestObserver.RequestObserverNext<AllDataState>() {
                @Override
                public void Next(AllDataState o) {
                    if (o.getState()==0){
                        SimpleUtils.setToast("密码修改成功，请重新登录");
                        SimpleUtils.removeUserMessage();
                        RoutePageActivity.getMainPage();
                        ((BaseActivity)mvpView.getThisActivity()).finishAllActivity();
                    }else {
                        SimpleUtils.setToast(o.getMessage());
                    }
                }

                @Override
                public void onError() {

                }

                @Override
                public void getDisposable(Disposable d) {

                }
            },mvpView.getActivityContext(),
                    mvpView.getUpdatePass_Old().getText().toString().trim(),
                    mvpView.getUpdatePass_Nwe1().getText().toString().trim(),
                    mvpView.getUpdatePass_Nwe2().getText().toString().trim());
        });
    }

    @Override
    public void CloseRequest() {

    }
}
