package com.yujian.petmii.frame.presenter;

import com.yujian.petmii.R;
import com.yujian.petmii.base.ApiFactory;
import com.yujian.petmii.frame.api.LoginService;
import com.yujian.petmii.frame.contract.LoginContract;
import com.yujian.petmii.global.Session;
import com.yujian.petmii.utils.ResourceUtils;
import com.yujian.petmii.utils.RxUtils;

/**
 * Created by lisic on 2018/4/6.
 */

public class LoginPresenter extends LoginContract.Presenter{

    @Override
    public void onAttached() {

    }

    @Override
    public void onDetached() {

    }

    @Override
    public void doLogin(String account, String pwd) {
        ApiFactory.getInstance().create(LoginService.class).login(account,pwd)
          .compose(RxUtils.oIoMain())
          .doOnSubscribe(d->mView.showProgressDlg(ResourceUtils.getString(R.string.login_now)))
          .doOnTerminate(()->mView.closeProgressDlg())
          .subscribe(response->{
              Session.saveSession(response.repData);
              mView.onLoginSuccess();
          },error->mView.onLoginFailed("error"));
    }
}
