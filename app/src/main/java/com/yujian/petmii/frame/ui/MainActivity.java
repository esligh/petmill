package com.yujian.petmii.frame.ui;

import android.content.Context;
import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.base.CommonAdapter;
import com.yujian.petmii.base.ViewHolder;
import com.yujian.petmii.databinding.ActivityMainBinding;
import com.yujian.petmii.frame.contract.MainContract;
import com.yujian.petmii.frame.entity.ProductInfo;
import com.yujian.petmii.frame.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter,ActivityMainBinding>
    implements MainContract.View{

    private CommonAdapter<ProductInfo> mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mPresenter.onAttached();
        mAdapter = new CommonAdapter<ProductInfo>(R.layout.item_product_info,mPresenter.getProducts()) {
            @Override
            public void convert(ViewHolder holder, ProductInfo item, int position) {

            }
        };
        mViewBinding.productsList.setAdapter(mAdapter);
        mViewBinding.productsList.setOnItemClickListener(((adapterView, view, i, l) -> {
            ProductInfo product = (ProductInfo) adapterView.getAdapter().getItem(i);
            mPresenter.attachProduct(product);
        }));
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetached();
    }
}
