package com.yujian.petmii.base;


import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract  class BaseDialogFragment extends DialogFragment {

    private ViewDataBinding binding;

    private ProgressDialog mWaitProgressDlg;

    protected abstract  int getLayoutId();

    protected abstract void initBinding(ViewDataBinding binding);

    protected abstract void initPresenter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        binding = DataBindingUtil.inflate(
                inflater, getLayoutId(), container, false);
        initBinding(binding);
        initPresenter();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        closeBaseProgressDlg();
    }

    public void closeBaseProgressDlg()
    {
        if(mWaitProgressDlg != null){
            mWaitProgressDlg.dismiss();
        }
    }

    public void showBaseProgressDlg(String msg)
    {
        if(mWaitProgressDlg == null){
            mWaitProgressDlg = new ProgressDialog(getActivity());
            mWaitProgressDlg.setCanceledOnTouchOutside(false);
        }else if(mWaitProgressDlg.isShowing()){
            return;
        }
        mWaitProgressDlg.setMessage(msg);
        mWaitProgressDlg.show();
    }
}
