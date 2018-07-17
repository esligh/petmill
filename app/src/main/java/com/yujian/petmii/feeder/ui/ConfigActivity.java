package com.yujian.petmii.feeder.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.databinding.ActivityConfigBinding;
import com.yujian.petmii.feeder.contract.ConfigContract;
import com.yujian.petmii.feeder.presenter.ConfigPresenter;
import com.yujian.petmii.global.Constants;

public class ConfigActivity extends BaseActivity<ConfigPresenter,ActivityConfigBinding>
    implements ConfigContract.View{

    @Override
    public int getLayoutId() {
        return R.layout.activity_config;
    }

    @Override
    public void initView() {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
