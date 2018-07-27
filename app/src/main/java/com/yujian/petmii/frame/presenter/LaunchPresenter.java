package com.yujian.petmii.frame.presenter;

import android.content.Intent;

import com.yujian.petmii.frame.contract.LaunchContract;
import com.yujian.petmii.frame.model.LaunchModel;
import com.yujian.petmii.frame.ui.LoginActivity;
import com.yujian.petmii.frame.ui.MainActivity;
import com.yujian.petmii.global.Session;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Created by lisic on 2018/4/6.
 */

public class LaunchPresenter extends LaunchContract.Presenter{
    private static final int sDelayTime = 2;
    private LaunchModel mModel;

    @Override
    public void onAttached() {
        mModel = new LaunchModel(this);
        mModel.onAttached();
        Observable.timer(sDelayTime, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(n->{
                    if(Session.inst().withSession()){
                        mView.getContext().startActivity(new Intent(mView.getContext(),
                                MainActivity.class));
                    }else{
                        mView.getContext().startActivity(new Intent(mView.getContext(),
                                LoginActivity.class));
                    }
                    mView.finishActivity();
                });
    }

    @Override
    public void onDetached() {
        mModel.onDetached();
    }
}
