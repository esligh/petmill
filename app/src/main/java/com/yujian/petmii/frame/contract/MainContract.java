package com.yujian.petmii.frame.contract;

import com.yujian.petmii.base.BasePresenter;
import com.yujian.petmii.base.BaseView;
import com.yujian.petmii.frame.entity.ProductInfo;

import java.util.List;

/**
 * Created by lisic on 2018/4/6.
 */

public interface MainContract {

    interface View extends BaseView
    {

    }

    abstract class Presenter extends BasePresenter<View>
    {
        public abstract List<ProductInfo> getProducts();
        public abstract void attachProduct(ProductInfo product);
    }
}
