package com.yujian.petmii.frame.contract;

import com.yujian.petmii.base.BasePresenter;
import com.yujian.petmii.base.BaseView;

/**
 * Created by lisic on 2018/4/6.
 */

public interface LoginContract {
    interface View extends BaseView
    {
        void onLoginSuccess();
        void onLoginFailed(Throwable error);
        void showProgressDlg(String msg);
        void closeProgressDlg();
    }

    abstract class Presenter extends BasePresenter<View>
    {
        public abstract void doLogin(String number,String code);
    }
}
