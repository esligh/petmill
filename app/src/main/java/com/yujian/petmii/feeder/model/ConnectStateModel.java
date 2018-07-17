package com.yujian.petmii.feeder.model;

import com.yujian.petmii.base.BaseModel;
import com.yujian.petmii.feeder.presenter.ConnectStatePresenter;

/**
 * Created by lisic on 2018/4/9.
 */

public class ConnectStateModel extends BaseModel<ConnectStatePresenter>{

    public ConnectStateModel(ConnectStatePresenter mPresenter) {
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
