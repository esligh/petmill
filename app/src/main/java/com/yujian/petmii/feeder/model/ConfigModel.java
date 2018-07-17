package com.yujian.petmii.feeder.model;

import com.yujian.petmii.base.BaseModel;
import com.yujian.petmii.feeder.presenter.ConfigPresenter;

/**
 * Created by lisic on 2018/4/6.
 */

public class ConfigModel extends BaseModel<ConfigPresenter>{
    public ConfigModel(ConfigPresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public void onAttached() {

    }

    @Override
    public void onMainEvent(int what, Object event) {

    }

    @Override
    public void onThreadEvent(int what, Object event) {

    }
}
