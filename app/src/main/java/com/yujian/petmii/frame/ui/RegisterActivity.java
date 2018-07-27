package com.yujian.petmii.frame.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.databinding.ActivityRegisterBinding;
import com.yujian.petmii.frame.contract.RegisterContract;
import com.yujian.petmii.frame.presenter.RegisterPresenter;
import com.yujian.petmii.global.Constants;
import com.yujian.petmii.utils.L;
import com.yujian.petmii.utils.ResourceUtils;
import com.yujian.petmii.utils.ToastUtils;
import com.yujian.petmii.utils.ToolsUtils;
import com.yujian.petmii.utils.UIToolsUtils;

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
        mViewBinding.setCanGetCode(true);
    }

    private void setListener()
    {
        mViewBinding.verifyCodeEt.setOnFocusChangeListener(((view, b) -> {
            if(b) {
                mViewBinding.setShowCodeClear(mViewBinding.verifyCodeEt.length() > 0);
            } else {
                mViewBinding.setShowCodeClear(false);
            }
        }));

        mViewBinding.verifyCodeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (mViewBinding.verifyCodeEt.isFocused()) {
                    mViewBinding.setShowCodeClear(s.length() > 0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mViewBinding.codeClearIv.setOnClickListener(v -> mViewBinding.verifyCodeEt.setText(""));
        mViewBinding.pwdEyeIv.setOnTouchListener((v, event) -> {
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    mViewBinding.userPwdEt.setInputType(InputType.TYPE_CLASS_NUMBER
                            | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mViewBinding.userPwdEt.setSelection(mViewBinding.userPwdEt.getText().length());
                    break;
                case MotionEvent.ACTION_UP:
                    mViewBinding.userPwdEt.setInputType(InputType.TYPE_CLASS_NUMBER
                            | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    mViewBinding.userPwdEt.setSelection(mViewBinding.userPwdEt.getText().length());
                    break;
            }
            return false;
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
            UIToolsUtils.closeInputMethod(this);
            mPresenter.register(account,code,pwd);
        });

        mViewBinding.getCodeBtn.setOnClickListener(v->{
            String phoneNumber = mViewBinding.phoneNumberEt.getText().toString();
            if(TextUtils.isEmpty(phoneNumber)){
                ToastUtils.shortShow(R.string.please_input_phone);
                return ;
            }
            if(!ToolsUtils.isValidPhone(phoneNumber)){
                ToastUtils.shortShow(R.string.error_phone_number);
                return;
            }
            mViewBinding.setCanGetCode(false);
            mPresenter.getSmsCode(phoneNumber,"");
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
    public void onRegisterFailed(Throwable error) {
        L.d(Constants.Tag,"err==>"+error.getMessage());
        closeProgressDlg();
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

    @Override
    public void setSmsCodeBtnText(String text) {
        mViewBinding.getCodeBtn.setText(text);
    }

    @Override
    public void setSmsCodeBtnEnable(boolean isEnable) {
        mViewBinding.setCanGetCode(isEnable);
    }

    @Override
    public void setGetCodeTimeOut() {
        mViewBinding.setCanGetCode(true);
        mViewBinding.getCodeBtn.setText(ResourceUtils.getString(R.string.btn_get_code_try_angin));
    }
}
