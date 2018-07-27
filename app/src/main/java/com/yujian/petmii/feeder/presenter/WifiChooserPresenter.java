<<<<<<< HEAD
package com.yujian.petmii.feeder.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.yujian.petmii.PetmiiApplication;
import com.yujian.petmii.R;
import com.yujian.petmii.feeder.contract.WifiChooserContract;
import com.yujian.petmii.feeder.ui.ConfigActivity;
import com.yujian.petmii.frame.entity.WifiInfo;
import com.yujian.petmii.global.Constants;
import com.yujian.petmii.utils.L;
import com.yujian.petmii.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fr on 2018/7/18.
 */

public class WifiChooserPresenter extends WifiChooserContract.Presenter{

    private List<WifiInfo> wifiList = new ArrayList<>();
    private WifiManager mWifiManager;

    @Override
    public void onAttached() {
        mWifiManager = (WifiManager) PetmiiApplication.getContext().getSystemService(Context.WIFI_SERVICE);
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        mView.getContext().registerReceiver(mReceiver, filter);
    }

    @Override
    public void onDetached() {
        mView.getContext().unregisterReceiver(mReceiver);
    }

    @Override
    public List<WifiInfo> getWifiList() {
        return wifiList;
    }

    @Override
    public void startScanWifi() {
        mWifiManager.startScan();
        mView.showProgressDlg(ResourceUtils.getString(R.string.search_wifi_now));
    }

    @Override
    public void nextStep(WifiInfo wifi, String pwd) {
        Intent intent = new Intent(mView.getContext(), ConfigActivity.class);
        intent.putExtra(Constants.Key.WIFI_SSID,wifi.ssid);
        intent.putExtra(Constants.Key.WIFI_BSSID,wifi.bssid);
        intent.putExtra(Constants.Key.WIFI_PWD,pwd);
        L.d(Constants.Tag,"open ConfigActivity with ssid="+wifi.ssid+",pwd="+pwd+",bssid="+wifi.bssid);
        mView.getContext().startActivity(intent);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(intent.getAction())) {
                mView.closeProgressDlg();
                List<ScanResult> list = mWifiManager.getScanResults();
                wifiList.clear();
                for(int i=0;i<list.size();i++){
                    ScanResult result = list.get(i);
                    L.d(Constants.Tag,"wifi bssid:"+result.BSSID);
                    if(!TextUtils.isEmpty(result.SSID)) {
                        WifiInfo wifi = new WifiInfo(result.SSID,result.BSSID,result.frequency);
                        wifiList.add(wifi);
                    }
                }
                mView.refreshList();
            }
        }
    };


}
=======
package com.yujian.petmii.feeder.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.yujian.petmii.PetmiiApplication;
import com.yujian.petmii.R;
import com.yujian.petmii.feeder.contract.WifiChooserContract;
import com.yujian.petmii.feeder.ui.ConfigActivity;
import com.yujian.petmii.global.Constants;
import com.yujian.petmii.utils.L;
import com.yujian.petmii.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fr on 2018/7/18.
 */

public class WifiChooserPresenter extends WifiChooserContract.Presenter{


    private List<String> ssidList = new ArrayList<>();
    private WifiManager mWifiMangaer;


    @Override
    public void onAttached() {
        mWifiMangaer = (WifiManager) PetmiiApplication.getContext().getSystemService(Context.WIFI_SERVICE);
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        mView.getContext().registerReceiver(mReceiver, filter);
    }

    @Override
    public void onDetached() {
        mView.getContext().unregisterReceiver(mReceiver);
    }

    @Override
    public List<String> getSsids() {
        return ssidList;
    }

    @Override
    public void startScanWifi() {

        mWifiMangaer.startScan();
        mView.showProgressDlg(ResourceUtils.getString(R.string.search_wifi_now));
    }

    @Override
    public void nextStep(String ssid, String pwd) {
        Intent intent = new Intent(mView.getContext(), ConfigActivity.class);
        intent.putExtra(Constants.Key.WIFI_SSID,ssid);
        intent.putExtra(Constants.Key.WIFI_PWD,pwd);
        L.d(Constants.Tag,"open ConfigActivity with ssid="+ssid+",pwd="+pwd);
        mView.getContext().startActivity(intent);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(intent.getAction())) {
                mView.closeProgressDlg();
                List<ScanResult> list = mWifiMangaer.getScanResults();
                ssidList.clear();
                for(int i=0;i<list.size();i++){
                    ScanResult result = list.get(i);
                    L.d(Constants.Tag,"wifi ssid:"+result.SSID);
                    if(!TextUtils.isEmpty(result.SSID)) {
                        ssidList.add(result.SSID);
                    }
                }
                mView.refreshList();
            }
        }
    };
}
>>>>>>> 235af1f2e7dcd142969589321679241a085334a2
