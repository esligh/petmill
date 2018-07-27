package com.yujian.petmii.frame.presenter;

import android.text.TextUtils;

import com.yujian.petmii.R;
import com.yujian.petmii.base.ApiFactory;
import com.yujian.petmii.frame.api.SessionService;
import com.yujian.petmii.frame.contract.RegisterContract;
import com.yujian.petmii.frame.model.RegisterModel;
import com.yujian.petmii.global.Constants;
import com.yujian.petmii.utils.L;
import com.yujian.petmii.utils.MD5Utils;
import com.yujian.petmii.utils.ResourceUtils;
import com.yujian.petmii.utils.RxUtils;
import com.yujian.petmii.utils.ToastUtils;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lisic on 2018/7/17.
 */

public class RegisterPresenter extends RegisterContract.Presenter{

    private Disposable mTimerDisposable ;
    private RegisterModel mModel;

    @Override
    public void onAttached() {
        mModel = new RegisterModel(this);
        mModel.onAttached();
    }

    @Override
    public void onDetached() {
        mModel.onDetached();
    }

    //获取验证码
    @Override
    public void getSmsCode(String phone,String phoneArea) {
        RxUtils.observableOnIoMain(()->{
            String result = "";

            return result;
        }).doOnSubscribe(disposable -> startTimer())
                .subscribe(data->{
                    if(!TextUtils.isEmpty(data)) {
                        JSONObject rObj = new JSONObject(data);
                        L.d(Constants.Tag,rObj.toString());
                    }
                },error->error.printStackTrace());
    }

    @Override
    public void register(String account, String vcode, String pwd) {
        L.d(Constants.Tag,"register pwd==>"+pwd);
        ApiFactory.getInstance().create(SessionService.class).register(account,
            MD5Utils.md5(new StringBuilder(pwd).append("_").append(Constants.Config.MD5_KEY).toString()), vcode)
            .compose(RxUtils.oIoMain())
            .doOnSubscribe(d->mView.showProgressDlg(ResourceUtils.getString(R.string.reg_now)))
            .doOnTerminate(()->mView.closeProgressDlg())
            .subscribe(response->{
                JSONObject obj = new JSONObject(response.string());
                L.d(Constants.Tag,"reg response==>"+obj.toString());
                if(obj.optInt("ret") == Constants.Ret.SUCCESS){
                    mView.onRegisterSuccess();
                }else{
                    ToastUtils.shortShow(obj.optString("repMsg"));
                }
            },error->mView.onRegisterFailed(error));

        stopTimer();
    }

    private void startTimer()
    {
        Observable.interval(0,1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Long>() {

                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mTimerDisposable = d;
                    mModel.addDisposable(d);
                }

                @Override
                public void onNext(@NonNull Long aLong) {
                    if(aLong >= 60){
                        if(mTimerDisposable != null && !mTimerDisposable.isDisposed()){
                            mTimerDisposable.dispose();
                        }
                        mView.setGetCodeTimeOut();
                    }else{
                        mView.setSmsCodeBtnText(String.format("%ds",60-aLong));
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
    }

    private void stopTimer()
    {
        if(mTimerDisposable != null && !mTimerDisposable.isDisposed()){
            mTimerDisposable.dispose();
        }
        mView.setSmsCodeBtnText(ResourceUtils.getString(R.string.get_sms_code));
        mView.setSmsCodeBtnEnable(true);
    }
}
