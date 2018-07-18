package com.yujian.petmii.frame.presenter;

import android.content.Intent;

import com.yujian.petmii.R;
import com.yujian.petmii.feeder.ui.FeederMainActivity;
import com.yujian.petmii.frame.contract.MainContract;
import com.yujian.petmii.frame.entity.ProductInfo;
import com.yujian.petmii.global.Constants;
import com.yujian.petmii.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lisic on 2018/4/6.
 */

public class MainPresenter extends MainContract.Presenter{
    private List<ProductInfo> mProducts = new ArrayList<>();

    @Override
    public void onAttached() {
        mProducts.add(new ProductInfo(R.mipmap.ic_launcher,
                ResourceUtils.getString(R.string.feeder),"遇见科技产品"));
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
        Intent intent = new Intent(mView.getContext(), FeederMainActivity.class);
        intent.putExtra(Constants.Entity.ProductInfo,product);
        mView.getContext().startActivity(intent);
    }

}
