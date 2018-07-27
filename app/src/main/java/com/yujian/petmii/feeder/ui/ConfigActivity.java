package com.yujian.petmii.feeder.ui;

import android.content.Context;
<<<<<<< HEAD
import android.content.Intent;
=======
>>>>>>> 235af1f2e7dcd142969589321679241a085334a2

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
<<<<<<< HEAD
        mPresenter.onAttached();
        mPresenter.startConfig();
=======
>>>>>>> 235af1f2e7dcd142969589321679241a085334a2
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
