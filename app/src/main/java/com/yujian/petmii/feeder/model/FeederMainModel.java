package com.yujian.petmii.feeder.model;

import com.yujian.petmii.base.BaseModel;
import com.yujian.petmii.feeder.presenter.FeederMainPresenter;

/**
 * Created by lisic on 2018/4/17.
 */

public class FeederMainModel extends BaseModel<FeederMainPresenter>{

    public FeederMainModel(FeederMainPresenter mPresenter) {
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
