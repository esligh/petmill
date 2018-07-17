package com.yujian.petmii.frame.ui;

import android.content.Context;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.databinding.ActivityLaunchBinding;
import com.yujian.petmii.frame.contract.LaunchContract;
import com.yujian.petmii.frame.presenter.LaunchPresenter;

public class LaunchActivity extends BaseActivity<LaunchPresenter,ActivityLaunchBinding>
        implements LaunchContract.View{

    @Override
    public int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    public void initView() {
        mPresenter.onAttached();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetached();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
