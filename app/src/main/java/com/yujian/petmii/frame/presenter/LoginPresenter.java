package com.yujian.petmii.frame.presenter;

import com.google.gson.Gson;
import com.yujian.petmii.R;
import com.yujian.petmii.base.ApiFactory;
import com.yujian.petmii.frame.api.LoginService;
import com.yujian.petmii.frame.contract.LoginContract;
import com.yujian.petmii.global.Session;
import com.yujian.petmii.utils.L;
import com.yujian.petmii.utils.ResourceUtils;
import com.yujian.petmii.utils.RxUtils;

import java.util.HashMap;

import okhttp3.RequestBody;

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
        Gson gson = new Gson();
        HashMap<String,String> paramsMap= new HashMap<>();
        paramsMap.put("username",account);
        paramsMap.put("password",pwd);
        String strEntity = gson.toJson(paramsMap);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);
        ApiFactory.getInstance().create(LoginService.class).login(body)
          .compose(RxUtils.oIoMain())
          .doOnSubscribe(d->mView.showProgressDlg(ResourceUtils.getString(R.string.login_now)))
          .doOnTerminate(()->mView.closeProgressDlg())
          .subscribe(response->{
              Session.saveSession(response.repData);
              mView.onLoginSuccess();
          },error->{
              L.d("err","doLogin=>"+error.getMessage());
              mView.onLoginFailed("error");
          });
    }
}
