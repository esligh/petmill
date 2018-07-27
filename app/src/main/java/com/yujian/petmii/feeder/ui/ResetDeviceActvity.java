package com.yujian.petmii.feeder.ui;

import android.content.Context;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.databinding.ActivityResetDeviceBinding;
import com.yujian.petmii.feeder.contract.ResetDeviceContract;
import com.yujian.petmii.feeder.presenter.ResetDevicePresenter;

public class ResetDeviceActvity extends BaseActivity<ResetDevicePresenter,
        ActivityResetDeviceBinding> implements ResetDeviceContract.View{

    @Override
    public int getLayoutId() {
        return R.layout.activity_reset_device;
    }

    @Override
    public void initView() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
