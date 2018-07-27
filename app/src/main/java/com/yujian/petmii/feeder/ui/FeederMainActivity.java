package com.yujian.petmii.feeder.ui;

import android.view.Menu;
import android.view.MenuItem;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.databinding.ActivityFeederMainBinding;
import com.yujian.petmii.feeder.presenter.FeederMainPresenter;

public class FeederMainActivity extends BaseActivity<FeederMainPresenter,ActivityFeederMainBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_feeder_main;
    }

    @Override
    public void initView() {
        setSupportActionBar(mViewBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.feeder_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_feeder_statistics:
                break;
            case R.id.action_feeder_settings:
                break;
        }
        return true;
    }
}
