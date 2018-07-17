package com.yujian.petmii.frame.presenter;

import com.yujian.petmii.R;
import com.yujian.petmii.base.ApiFactory;
import com.yujian.petmii.frame.api.LoginService;
import com.yujian.petmii.frame.api.RegisterService;
import com.yujian.petmii.frame.contract.RegisterContract;
import com.yujian.petmii.global.Session;
import com.yujian.petmii.utils.ResourceUtils;
import com.yujian.petmii.utils.RxUtils;

/**
 * Created by lisic on 2018/7/17.
 */

public class RegisterPresenter extends RegisterContract.Presenter{

    @Override
    public void onAttached() {

    }

    @Override
    public void onDetached() {

    }

    @Override
    public void register(String account, String vcode, String pwd) {
        ApiFactory.getInstance().create(RegisterService.class).register(account,pwd,vcode)
                .compose(RxUtils.oIoMain())
                .doOnSubscribe(d->mView.showProgressDlg(ResourceUtils.getString(R.string.login_now)))
                .doOnTerminate(()->mView.closeProgressDlg())
                .subscribe(response->{
                    mView.onRegisterSuccess();
                },error->mView.onRegisterFailed("error"));
    }
}
