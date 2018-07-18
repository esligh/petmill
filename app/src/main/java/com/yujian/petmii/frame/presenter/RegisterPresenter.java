package com.yujian.petmii.frame.presenter;

import com.google.gson.Gson;
import com.yujian.petmii.R;
import com.yujian.petmii.base.ApiFactory;
import com.yujian.petmii.frame.api.RegisterService;
import com.yujian.petmii.frame.contract.RegisterContract;
import com.yujian.petmii.global.Constants;
import com.yujian.petmii.utils.L;
import com.yujian.petmii.utils.MD5Utils;
import com.yujian.petmii.utils.ResourceUtils;
import com.yujian.petmii.utils.RxUtils;

import java.util.HashMap;

import okhttp3.RequestBody;

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
        L.d(Constants.Tag,"register pwd==>"+pwd);
        Gson gson = new Gson();
        HashMap<String,String> paramsMap = new HashMap<>();
        paramsMap.put("username",account);
        paramsMap.put("password", MD5Utils.md5(new StringBuilder(pwd).append("_").append(Constants.Config.MD5_KEY).toString()));
        paramsMap.put("verf_code",vcode);
        String strEntity = gson.toJson(paramsMap);
        L.d(Constants.Tag,"register params==>"+strEntity);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);
        ApiFactory.getInstance().create(RegisterService.class).register(body)
            .compose(RxUtils.oIoMain())
            .doOnSubscribe(d->mView.showProgressDlg(ResourceUtils.getString(R.string.reg_now)))
            .doOnTerminate(()->mView.closeProgressDlg())
            .subscribe(response->{
                mView.onRegisterSuccess();
            },error->mView.onRegisterFailed("error"));
    }
}
