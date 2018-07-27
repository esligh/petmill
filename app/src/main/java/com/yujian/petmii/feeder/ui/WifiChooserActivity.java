<<<<<<< HEAD
package com.yujian.petmii.feeder.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.databinding.ActivityWifiChooserBinding;
import com.yujian.petmii.feeder.contract.WifiChooserContract;
import com.yujian.petmii.feeder.presenter.WifiChooserPresenter;
import com.yujian.petmii.frame.entity.WifiInfo;
import com.yujian.petmii.global.Constants;
import com.yujian.petmii.utils.ToastUtils;
import com.yujian.petmii.utils.UIToolsUtils;

public class WifiChooserActivity extends BaseActivity<WifiChooserPresenter,ActivityWifiChooserBinding>
        implements WifiChooserContract.View{

    private ArrayAdapter wifiAdapter;
    private ProgressDialog mProgressDlg;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wifi_chooser;
    }

    @Override
    public void initView() {
        mPresenter.onAttached();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mViewBinding.setBtnEnable(true);
        wifiAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,mPresenter.getWifiList());
        wifiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mViewBinding.wifiSp.setAdapter(wifiAdapter);
        setListener();
        requestLocationPermission();
    }

    private void setListener()
    {
        mViewBinding.pwdEyeIv.setOnTouchListener((v, event) -> {
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    mViewBinding.wifiPwdEt.setInputType(InputType.TYPE_CLASS_NUMBER
                            | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mViewBinding.wifiPwdEt.setSelection(mViewBinding.wifiPwdEt.getText().length());
                    break;
                case MotionEvent.ACTION_UP:
                    mViewBinding.wifiPwdEt.setInputType(InputType.TYPE_CLASS_NUMBER
                            | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    mViewBinding.wifiPwdEt.setSelection(mViewBinding.wifiPwdEt.getText().length());
                    break;
            }
            return false;
        });
        mViewBinding.nextStepBtn.setOnClickListener(v -> {
            WifiInfo wifi = (WifiInfo)mViewBinding.wifiSp.getSelectedItem();
            if(wifi == null || TextUtils.isEmpty(wifi.ssid)){
                ToastUtils.shortShow(R.string.please_choose_wifi);
                return ;
            }
            String wifiPwd = mViewBinding.wifiPwdEt.getText().toString();
            if(TextUtils.isEmpty(wifiPwd)){
                ToastUtils.shortShow(R.string.please_input_pwd);
                return ;
            }
            UIToolsUtils.closeInputMethod(this);
            mPresenter.nextStep(wifi,wifiPwd);
        });
    }

    public void requestLocationPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {}

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        Constants.ReqCode.REQUEST_CODE_ACCESS_COARSE_LOCATION);
            }else{
                mPresenter.startScanWifi();
            }
        }else{
            mPresenter.startScanWifi();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.ReqCode.REQUEST_CODE_ACCESS_COARSE_LOCATION:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // The requested permission is granted.
                    mPresenter.startScanWifi();
                } else{
                    // The user disallowed the requested permission.
                }
                break;

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
    public void refreshList() {
        wifiAdapter.notifyDataSetChanged();
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
=======
package com.yujian.petmii.feeder.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.databinding.ActivityWifiChooserBinding;
import com.yujian.petmii.feeder.contract.WifiChooserContract;
import com.yujian.petmii.feeder.presenter.WifiChooserPresenter;
import com.yujian.petmii.global.Constants;
import com.yujian.petmii.utils.ToastUtils;

public class WifiChooserActivity extends BaseActivity<WifiChooserPresenter,ActivityWifiChooserBinding>
        implements WifiChooserContract.View{

    private ArrayAdapter wifiAdapter;
    private ProgressDialog mProgressDlg;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wifi_chooser;
    }

    @Override
    public void initView() {
        mPresenter.onAttached();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mViewBinding.setBtnEnable(true);
        wifiAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,mPresenter.getSsids());
        wifiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mViewBinding.wifiSp.setAdapter(wifiAdapter);
        setListener();
        requestLocationPermission();
    }

    private void setListener()
    {
        mViewBinding.pwdEyeIv.setOnTouchListener((v, event) -> {
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    mViewBinding.wifiPwdEt.setInputType(InputType.TYPE_CLASS_NUMBER
                            | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mViewBinding.wifiPwdEt.setSelection(mViewBinding.wifiPwdEt.getText().length());
                    break;
                case MotionEvent.ACTION_UP:
                    mViewBinding.wifiPwdEt.setInputType(InputType.TYPE_CLASS_NUMBER
                            | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    mViewBinding.wifiPwdEt.setSelection(mViewBinding.wifiPwdEt.getText().length());
                    break;
            }
            return false;
        });
        mViewBinding.nextStepBtn.setOnClickListener(v -> {
            String ssid = (String)mViewBinding.wifiSp.getSelectedItem();
            if(TextUtils.isEmpty(ssid)){
                ToastUtils.shortShow(R.string.please_choose_wifi);
                return ;
            }
            String wifiPwd = mViewBinding.wifiPwdEt.getText().toString();
            if(TextUtils.isEmpty(wifiPwd)){
                ToastUtils.shortShow(R.string.please_input_pwd);
                return ;
            }
            mPresenter.nextStep(ssid,wifiPwd);
        });
    }

    public void requestLocationPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {}

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        Constants.ReqCode.REQUEST_CODE_ACCESS_COARSE_LOCATION);
            }else{
                mPresenter.startScanWifi();
            }
        }else{
            mPresenter.startScanWifi();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.ReqCode.REQUEST_CODE_ACCESS_COARSE_LOCATION:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // The requested permission is granted.
                    mPresenter.startScanWifi();
                } else{
                    // The user disallowed the requested permission.
                }
                break;

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
    public void refreshList() {
        wifiAdapter.notifyDataSetChanged();
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
>>>>>>> 235af1f2e7dcd142969589321679241a085334a2
