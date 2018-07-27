package com.yujian.petmii.frame.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.yujian.petmii.R;
import com.yujian.petmii.base.BaseActivity;
import com.yujian.petmii.base.CommonAdapter;
import com.yujian.petmii.base.ViewHolder;
import com.yujian.petmii.databinding.ActivityMainBinding;
import com.yujian.petmii.feeder.ui.AddFeederActivity;
import com.yujian.petmii.frame.contract.MainContract;
import com.yujian.petmii.frame.entity.ProductInfo;
import com.yujian.petmii.frame.presenter.MainPresenter;
import com.yujian.petmii.global.Session;

public class MainActivity extends BaseActivity<MainPresenter,ActivityMainBinding>
        implements MainContract.View {

    private CommonAdapter<ProductInfo> mAdapter;
    private TextView userNameTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mViewBinding.mainLayout.toolbar.setTitleTextColor(Color.WHITE);
        mViewBinding.mainLayout.toolbar.setTitle("");
        setSupportActionBar(mViewBinding.mainLayout.toolbar);
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
        userNameTv = (TextView) mViewBinding.navigationView.getHeaderView(0).findViewById(R.id.user_name_tv);
        mPresenter.onAttached();
        initUserView();
    }

    private void setListener()
    {
        mViewBinding.mainLayout.productsList.setOnItemClickListener(((adapterView, view, i, l) -> {
            ProductInfo product = (ProductInfo) adapterView.getAdapter().getItem(i);
            mPresenter.attachProduct(product);
        }));

        mViewBinding.navigationView.setNavigationItemSelectedListener(item -> {
            mViewBinding.drawLayout.closeDrawer(GravityCompat.START);
            switch(item.getItemId()){
                case R.id.action_logout:
                    askForLogout();
                    break;
            }
            return true;
        });
    }

    private void initUserView()
    {
        userNameTv.setText(Session.inst().userName);
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

    private void askForLogout()
    {
        AlertDialog dialog = new AlertDialog.Builder(this)
            .setNegativeButton(getString(android.R.string.cancel),null)
            .setPositiveButton(getString(android.R.string.ok), (dlg,witch)->{
                mPresenter.doLogout();
                finish();
            }).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage(getString(R.string.ask_for_logout));
        dialog.show();
    }

}
