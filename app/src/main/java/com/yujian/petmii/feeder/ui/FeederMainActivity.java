package com.yujian.petmii.feeder.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.base.BasePresenter;
import com.yujian.petmii.databinding.ActivityFeederMainBinding;
import com.yujian.petmii.feeder.presenter.FeederMainPresenter;

public class FeederMainActivity extends BaseActivity<FeederMainPresenter,ActivityFeederMainBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_feeder_main;
    }

    @Override
    public void initView() {

    }
}
