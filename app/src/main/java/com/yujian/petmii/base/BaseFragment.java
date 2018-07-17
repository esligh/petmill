package com.yujian.petmii.base;


import android.databinding.ViewDataBinding;


public abstract class BaseFragment<P extends BasePresenter, B extends ViewDataBinding>
        extends DataBindingFragment<B> {

    protected  P mPresenter;

    @Override
    protected void initPresenter() {
        mPresenter = TUtil.getT(this, 0);
        if (mPresenter != null) mPresenter.setView(this);
    }

}
