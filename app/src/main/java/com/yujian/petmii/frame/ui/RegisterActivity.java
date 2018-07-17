package com.yujian.petmii.frame.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.databinding.ActivityLoginBinding;
import com.yujian.petmii.databinding.ActivityRegisterBinding;
import com.yujian.petmii.frame.contract.LoginContract;
import com.yujian.petmii.frame.contract.RegisterContract;
import com.yujian.petmii.frame.presenter.LoginPresenter;
import com.yujian.petmii.frame.presenter.RegisterPresenter;
import com.yujian.petmii.utils.ToastUtils;

/**
 * 用户注册页面
 * */
public class RegisterActivity extends BaseActivity<RegisterPresenter,ActivityRegisterBinding>
        implements RegisterContract.View {

    private ProgressDialog mProgressDlg;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        mPresenter.onAttached();
        setListener();
    }

    private void setListener()
    {
        mViewBinding.registerBtn.setOnClickListener(v->{
            String account = mViewBinding.phoneNumberEt.getText().toString();
            String code = mViewBinding.verifyCodeEt.getText().toString();
            String pwd = mViewBinding.userPwdEt.getText().toString();
            if(TextUtils.isEmpty(account)){
                return;
            }
            if(TextUtils.isEmpty(code)){
                return;
            }
            if(TextUtils.isEmpty(pwd)){
                return;
            }
            mPresenter.register(account,code,pwd);
        });
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

    @Override
    public void onRegisterSuccess() {
        ToastUtils.shortShow("注册成功");
        finish();
    }

    @Override
    public void onRegisterFailed(String reason) {

    }

    @Override
    public void showProgressDlg(String msg) {
        if(mProgressDlg == null){
            mProgressDlg = new ProgressDialog(this);
            mProgressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDlg.setCancelable(false);
        }
        mProgressDlg.setMessage(msg);
        mProgressDlg.show();
    }

    @Override
    public void closeProgressDlg() {
        if(mProgressDlg != null){
            mProgressDlg.dismiss();
        }
    }
}
