package com.yujian.petmii.frame.model;

import com.yujian.petmii.base.BaseModel;
import com.yujian.petmii.frame.presenter.LoginPresenter;

/**
 * Created by lisic on 2018/4/6.
 */

public class LoginModel extends BaseModel<LoginPresenter>{

    public LoginModel(LoginPresenter mPresenter) {
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
