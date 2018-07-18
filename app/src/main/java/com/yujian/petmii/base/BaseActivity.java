package com.yujian.petmii.base;

import android.content.pm.ActivityInfo;
import android.databinding.ViewDataBinding;
import android.view.MenuItem;

public abstract class BaseActivity<P extends BasePresenter, B extends ViewDataBinding> extends DataBindingActivity<B>  {

	protected  P mPresenter;

	@Override
	protected void initPresenter() {
		mPresenter = TUtil.getT(this, 0);
		if (mPresenter != null) mPresenter.setView(this);
	}

	@Override
	protected void onResume() {
		 if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
			 setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		 }
		 super.onResume();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case android.R.id.home:
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
