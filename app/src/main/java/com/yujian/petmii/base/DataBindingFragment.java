package com.yujian.petmii.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract  class DataBindingFragment<B extends ViewDataBinding> extends Fragment {

    protected Context mContext;
    protected B mViewBinding;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        mViewBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mContext = getActivity();
        initPresenter();
        initView();
        return mViewBinding.getRoot();
    }

    protected void initPresenter() {}

    public abstract int getLayoutId();

    public abstract void initView();
}
