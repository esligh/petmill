package com.yujian.petmii.feeder.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.databinding.ActivityWifiConfigBinding;
import com.yujian.petmii.feeder.contract.WifiConfigContract;
import com.yujian.petmii.feeder.presenter.WifiConfigPresenter;

public class WifiConfigActivity extends BaseActivity<WifiConfigPresenter,
        ActivityWifiConfigBinding> implements WifiConfigContract.View{

    @Override
    public int getLayoutId() {
        return R.layout.activity_wifi_config;
    }

    @Override
    public void initView() {

    }

    @Override
    public Context getContext() {
        return null;
    }
}
