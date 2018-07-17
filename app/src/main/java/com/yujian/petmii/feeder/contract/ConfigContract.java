package com.yujian.petmii.feeder.contract;

import com.yujian.petmii.base.BasePresenter;
import com.yujian.petmii.base.BaseView;

/**
 * Created by lisic on 2018/4/6.
 */

public interface ConfigContract {
    interface View extends BaseView
    {

    }

    abstract class Presenter extends BasePresenter<View>
    {

    }
}
