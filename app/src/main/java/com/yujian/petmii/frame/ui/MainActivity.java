package com.yujian.petmii.frame.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.base.CommonAdapter;
import com.yujian.petmii.base.ViewHolder;
import com.yujian.petmii.databinding.ActivityMainBinding;
import com.yujian.petmii.feeder.ui.AddFeederActivity;
import com.yujian.petmii.frame.contract.MainContract;
import com.yujian.petmii.frame.entity.ProductInfo;
import com.yujian.petmii.frame.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter,ActivityMainBinding>
        implements MainContract.View {

    private CommonAdapter<ProductInfo> mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setSupportActionBar(mViewBinding.mainLayout.toolbar);
        mViewBinding.mainLayout.toolbar.setTitleTextColor(Color.WHITE);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mViewBinding.drawLayout, mViewBinding.mainLayout.toolbar, R.string.open, R.string.close);
        mViewBinding.drawLayout.setDrawerListener(toggle);
        toggle.syncState();

        mAdapter = new CommonAdapter<ProductInfo>(R.layout.item_product_info,mPresenter.getProducts()) {
            @Override
            public void convert(ViewHolder holder, ProductInfo item, int position) {
                holder.setImageResource(R.id.product_img,item.logo);
                holder.setTvText(R.id.product_name_tv,item.name);
                holder.setTvText(R.id.extra_tv,item.extra);
            }
        };
        mViewBinding.mainLayout.productsList.setAdapter(mAdapter);
        setListener();
        mPresenter.onAttached();
    }

    private void setListener()
    {
        mViewBinding.mainLayout.productsList.setOnItemClickListener(((adapterView, view, i, l) -> {
            ProductInfo product = (ProductInfo) adapterView.getAdapter().getItem(i);
            mPresenter.attachProduct(product);
        }));

        mViewBinding.navigationView.setNavigationItemSelectedListener(item -> {
            mViewBinding.drawLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (mViewBinding.drawLayout.isDrawerOpen(GravityCompat.START)) {
            mViewBinding.drawLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                startActivity(new Intent(this, AddFeederActivity.class));
                break;
        }
        return true;
    }
}
