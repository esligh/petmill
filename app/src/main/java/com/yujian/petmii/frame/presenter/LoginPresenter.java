package com.yujian.petmii.frame.presenter;

import com.yujian.petmii.R;
import com.yujian.petmii.base.ApiFactory;
import com.yujian.petmii.frame.api.SessionService;
import com.yujian.petmii.frame.contract.LoginContract;
import com.yujian.petmii.global.Constants;
import com.yujian.petmii.global.Session;
import com.yujian.petmii.utils.L;
import com.yujian.petmii.utils.MD5Utils;
import com.yujian.petmii.utils.ResourceUtils;
import com.yujian.petmii.utils.RxUtils;
import com.yujian.petmii.utils.ToastUtils;

import org.json.JSONObject;

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
        L.d("login params==> account="+account+",pwd="+pwd);
        ApiFactory.getInstance().create(SessionService.class).login(account,
                MD5Utils.md5(new StringBuilder(pwd).append("_").append(Constants.Config.MD5_KEY).toString()))
          .compose(RxUtils.oIoMain())
          .doOnSubscribe(d->mView.showProgressDlg(ResourceUtils.getString(R.string.login_now)))
          .doOnTerminate(()->mView.closeProgressDlg())
          .subscribe(response->{
              JSONObject obj = new JSONObject(response.string());
              L.d("login result==>"+obj.toString());
              int ret = obj.optInt("ret");
              if( ret == Constants.Ret.SUCCESS){
                  Session.inst().saveSession(obj.optString("repData"));
                  mView.onLoginSuccess();
              }else {
                  ToastUtils.shortShow(obj.optString("repMsg"));
              }
          },error->{
              mView.onLoginFailed(error);
          });
    }
}
