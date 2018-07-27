package com.yujian.petmii.frame.contract;

import com.yujian.petmii.base.BasePresenter;
import com.yujian.petmii.base.BaseView;

/**
 * Created by lisic on 2018/7/17.
 */

public interface RegisterContract {

    interface View extends BaseView
    {
        void onRegisterSuccess();
        void onRegisterFailed(Throwable error);
        void showProgressDlg(String msg);
        void closeProgressDlg();
        void setSmsCodeBtnText(String text);
        void setSmsCodeBtnEnable(boolean isEnable);
        void setGetCodeTimeOut();
    }

    abstract class Presenter extends BasePresenter<View>
    {
        public abstract void register(String account, String code, String pwd);
        public abstract void getSmsCode(String phone,String phoneArea);
    }
}
