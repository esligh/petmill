package com.yujian.petmii.feeder.contract;

import com.yujian.petmii.base.BasePresenter;
import com.yujian.petmii.base.BaseView;

import java.util.List;

/**
 * Created by fr on 2018/7/18.
 */

public interface WifiChooserContract {

    interface View extends BaseView
    {
        void showProgressDlg(String msg);
        void closeProgressDlg();
        void refreshList();
    }

    abstract class Presenter extends BasePresenter<WifiChooserContract.View>
    {
        public abstract List<String> getSsids();
        public abstract void startScanWifi();
        public abstract void nextStep(String ssid,String pwd);
    }
}
