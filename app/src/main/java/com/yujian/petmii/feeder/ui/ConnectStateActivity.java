package com.yujian.petmii.feeder.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.databinding.ActivityConnectStateBinding;
import com.yujian.petmii.feeder.contract.ConnectStateContract;
import com.yujian.petmii.feeder.presenter.ConnectStatePresenter;

public class ConnectStateActivity extends BaseActivity<ConnectStatePresenter,
        ActivityConnectStateBinding> implements ConnectStateContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_connect_state;
    }

    @Override
    public void initView() {

    }


    @Override
    public Context getContext() {
        return this;
    }
}
