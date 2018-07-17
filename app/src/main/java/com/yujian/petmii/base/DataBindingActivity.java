package com.yujian.petmii.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

public abstract class DataBindingActivity<B extends ViewDataBinding> extends AppCompatActivity {

    protected Context mContext;
    protected B mViewBinding;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = DataBindingUtil.setContentView(this,getLayoutId());
        mContext = this;
        initPresenter();
        initView();
    }

    protected void initPresenter() {}

    public abstract int getLayoutId();

    public abstract void initView();
}
