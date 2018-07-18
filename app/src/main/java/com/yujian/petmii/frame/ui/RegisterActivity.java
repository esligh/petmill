package com.yujian.petmii.frame.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.databinding.ActivityRegisterBinding;
import com.yujian.petmii.frame.contract.RegisterContract;
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
        mViewBinding.setBtnEnable(true);
    }

    private void setListener()
    {
        mViewBinding.verifyCodeEt.setOnFocusChangeListener(((view, b) -> {
            if(b){
                setClearIconVisible(mViewBinding.verifyCodeEt.length() > 0);
            }else{
                setClearIconVisible(false);
            }
        }));

        mViewBinding.verifyCodeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (mViewBinding.verifyCodeEt.isFocused()) {
                    setClearIconVisible(s.length() > 0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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

    private void setClearIconVisible(boolean visible)
    {
        if(visible){
            mViewBinding.codeClearIv.setVisibility(View.VISIBLE);
        } else {
            mViewBinding.codeClearIv.setVisibility(View.GONE);
        }
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
