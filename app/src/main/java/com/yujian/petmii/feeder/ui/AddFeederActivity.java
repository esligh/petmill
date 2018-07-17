package com.yujian.petmii.feeder.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.databinding.ActivityAddFeederBinding;
import com.yujian.petmii.feeder.presenter.AddFeederPresenter;

public class AddFeederActivity extends BaseActivity<AddFeederPresenter,
        ActivityAddFeederBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_feeder;
    }

    @Override
    public void initView()
    {

    }
}
