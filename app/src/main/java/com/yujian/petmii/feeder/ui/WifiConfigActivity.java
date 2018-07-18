package com.yujian.petmii.feeder.ui;

import android.content.Context;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public Context getContext() {
        return null;
    }
}
