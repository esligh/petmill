package com.yujian.petmii.feeder.contract;

import android.content.Intent;

import com.yujian.petmii.base.BasePresenter;
import com.yujian.petmii.base.BaseView;

/**
 * Created by lisic on 2018/4/6.
 */

public interface ConfigContract {
    interface View extends BaseView
    {
         Intent getParams();
    }

    abstract class Presenter extends BasePresenter<View>
    {
        public abstract void startConfig();
        public abstract void initDevice(String mac,String ip);
    }


}
