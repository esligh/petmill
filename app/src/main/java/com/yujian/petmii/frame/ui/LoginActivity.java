package com.yujian.petmii.frame.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.databinding.ActivityLoginBinding;
import com.yujian.petmii.frame.contract.LoginContract;
import com.yujian.petmii.frame.presenter.LoginPresenter;
import com.yujian.petmii.utils.ToastUtils;
import com.yujian.petmii.utils.UIToolsUtils;

public class LoginActivity extends BaseActivity<LoginPresenter,ActivityLoginBinding>
                            implements LoginContract.View{
    private ProgressDialog mProgressDlg;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        mPresenter.onAttached();
        UIToolsUtils.setViewPressSelector(mViewBinding.clearIv,
                R.drawable.ic_clean_n,R.drawable.ic_clean_c);

        setListener();
        mViewBinding.setBtnEnable(true);
    }

    private void setListener()
    {
        mViewBinding.pwdEt.setOnFocusChangeListener(((view, b) -> {
            if(b){
                setClearIconVisible(mViewBinding.pwdEt.length() > 0);
            }else{
                setClearIconVisible(false);
            }
        }));

        mViewBinding.clearIv.setOnClickListener(v->mViewBinding.pwdEt.setText(""));

        mViewBinding.pwdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (mViewBinding.pwdEt.isFocused()) {
                    setClearIconVisible(s.length() > 0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mViewBinding.loginBtn.setOnClickListener(v->{
            String number = mViewBinding.phoneNumberEt.getText().toString().trim();
            if(TextUtils.isEmpty(number)){
                ToastUtils.shortShow(R.string.please_input_phone);
                return ;
            }
            String pwd = mViewBinding.pwdEt.getText().toString().trim();
            if(TextUtils.isEmpty(pwd)){
                ToastUtils.shortShow(R.string.please_input_pwd);
                return ;
            }
            mPresenter.doLogin(number,pwd);
        });

        mViewBinding.userRegTv.setOnClickListener(v->{
            UIToolsUtils.closeInputMethod(this);
            startActivity(new Intent(this,RegisterActivity.class));
        });
    }

    private void setClearIconVisible(boolean visible)
    {
        if(visible){
            mViewBinding.clearIv.setVisibility(View.VISIBLE);
        } else {
            mViewBinding.clearIv.setVisibility(View.GONE);
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
    public void onLoginSuccess() {
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void onLoginFailed(String reason) {
        ToastUtils.shortShow("用户名或密码错误");
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
