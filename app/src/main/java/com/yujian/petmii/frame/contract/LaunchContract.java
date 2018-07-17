package com.yujian.petmii.frame.contract;

import com.yujian.petmii.base.BasePresenter;
import com.yujian.petmii.base.BaseView;

/**
 * Created by lisic on 2018/4/6.
 */

public interface LaunchContract {
    interface View extends BaseView
    {
        void finishActivity();
    }

    abstract class Presenter extends BasePresenter<View>
    {

    }
}
