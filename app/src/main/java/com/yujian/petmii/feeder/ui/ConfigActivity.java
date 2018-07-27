package com.yujian.petmii.feeder.ui;

import android.content.Context;
import android.content.Intent;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.databinding.ActivityConfigBinding;
import com.yujian.petmii.feeder.contract.ConfigContract;
import com.yujian.petmii.feeder.presenter.ConfigPresenter;

public class ConfigActivity extends BaseActivity<ConfigPresenter,ActivityConfigBinding>
    implements ConfigContract.View{

    @Override
    public int getLayoutId() {
        return R.layout.activity_config;
    }

    @Override
    public void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mPresenter.onAttached();
        mPresenter.startConfig();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetached();
    }

    @Override
    public Intent getParams() {
        return getIntent();
    }
}
