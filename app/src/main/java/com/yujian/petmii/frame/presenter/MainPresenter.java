package com.yujian.petmii.frame.presenter;

import android.content.Intent;

import com.yujian.petmii.feeder.ui.ConfigActivity;
import com.yujian.petmii.frame.contract.MainContract;
import com.yujian.petmii.frame.entity.ProductInfo;
import com.yujian.petmii.global.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lisic on 2018/4/6.
 */

public class MainPresenter extends MainContract.Presenter{
    private List<ProductInfo> mProducts = new ArrayList<>();

    @Override
    public void onAttached() {

    }

    @Override
    public void onDetached() {

    }

    @Override
    public List<ProductInfo> getProducts() {
        return mProducts;
    }

    @Override
    public void attachProduct(ProductInfo product) {
        Intent intent = new Intent(mView.getContext(), ConfigActivity.class);
        intent.putExtra(Constants.Entity.ProductInfo,product);
        mView.getContext().startActivity(intent);
    }

}
