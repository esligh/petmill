package com.yujian.petmii.frame.model;

import com.yujian.petmii.base.BaseModel;
import com.yujian.petmii.frame.presenter.MainPresenter;

/**
 * Created by lisic on 2018/4/6.
 */

public class MainModel extends BaseModel<MainPresenter>{

    public MainModel(MainPresenter mPresenter) {
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
