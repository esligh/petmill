package com.yujian.petmii.feeder.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.espressif.iot.esptouch.EsptouchTask;
import com.espressif.iot.esptouch.IEsptouchListener;
import com.espressif.iot.esptouch.IEsptouchResult;
import com.espressif.iot.esptouch.IEsptouchTask;
import com.espressif.iot.esptouch.util.ByteUtil;
import com.espressif.iot.esptouch.util.EspNetUtil;
import com.yujian.petmii.PetmiiApplication;
import com.yujian.petmii.core.DeviceConnection;
import com.yujian.petmii.feeder.contract.ConfigContract;
import com.yujian.petmii.global.Constants;
import com.yujian.petmii.utils.L;
import com.yujian.petmii.utils.ResourceUtils;
import com.yujian.petmii.utils.RxUtils;
import com.yujian.petmii.utils.ToastUtils;

import java.lang.ref.WeakReference;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lisic on 2018/4/6.
 */

public class ConfigPresenter extends ConfigContract.Presenter{

    private EsptouchAsyncTask4 mTask;

    @Override
    public void onAttached() {

    }

    @Override
    public void onDetached() {
        if(mTask != null){
            mTask.cancelEsptouch();
        }
    }

    @Override
    public void startConfig() {
        Intent intent = mView.getParams();
        String ssidStr = intent.getStringExtra(Constants.Key.WIFI_SSID);
        String bssidStr = intent.getStringExtra(Constants.Key.WIFI_BSSID);
        String pwdStr = intent.getStringExtra(Constants.Key.WIFI_PWD);

        byte[] ssid = ByteUtil.getBytesByString(ssidStr);
        byte[] password = ByteUtil.getBytesByString(pwdStr);
        byte [] bssid = EspNetUtil.parseBssid2bytes(bssidStr);
        byte[] deviceCount = "1".getBytes();

        if(mTask != null) {
            mTask.cancelEsptouch();
        }
        mTask = new EsptouchAsyncTask4(this);
        mTask.execute(ssid, bssid, password, deviceCount);
    }

    @Override
    public void initDevice(String mac, String ip) {
        DeviceConnection.initConnection(mac,ip);

    }

    private IEsptouchListener myListener = new IEsptouchListener() {

        @Override
        public void onEsptouchResultAdded(final IEsptouchResult result) {
            onEsptoucResultAddedPerform(result);
        }
    };

    private void onEsptoucResultAddedPerform(final IEsptouchResult result) {
        ((AppCompatActivity)(mView.getContext())).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String text = result.getBssid() + " is connected to the wifi";
                ToastUtils.shortShow(text);
            }
        });
    }

    private static class EsptouchAsyncTask4 extends AsyncTask<byte[], Void, List<IEsptouchResult>> {
        private WeakReference<ConfigPresenter> mPresenter;

        private final Object mLock = new Object();
        private ProgressDialog mProgressDialog;
        private AlertDialog mResultDialog;
        private IEsptouchTask mEsptouchTask;

        EsptouchAsyncTask4(ConfigPresenter presenter) {
            mPresenter = new WeakReference<>(presenter);
        }

        void cancelEsptouch() {
            cancel(true);
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
            if (mResultDialog != null) {
                mResultDialog.dismiss();
            }
            if (mEsptouchTask != null) {
                mEsptouchTask.interrupt();
            }
        }

        @Override
        protected void onPreExecute() {
            ConfigPresenter presenter = mPresenter.get();
            mProgressDialog = new ProgressDialog(presenter.mView.getContext());
            mProgressDialog.setMessage("Esptouch is configuring, please wait for a moment...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setOnCancelListener(dialog -> {
                synchronized (mLock) {
                    L.i(Constants.Tag, "progress dialog back pressed canceled");
                    if (mEsptouchTask != null) {
                        mEsptouchTask.interrupt();
                    }
                }
            });
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, ResourceUtils.getString(android.R.string.cancel),
                (dialog, which) -> {
                    synchronized (mLock) {
                        L.i(Constants.Tag, "progress dialog cancel button canceled");
                        if (mEsptouchTask != null) {
                            mEsptouchTask.interrupt();
                        }
                    }
                });
            mProgressDialog.show();
        }

        @Override
        protected List<IEsptouchResult> doInBackground(byte[]... params) {
            ConfigPresenter presenter = mPresenter.get();
            int taskResultCount;
            synchronized (mLock) {
                byte[] apSsid = params[0];
                byte[] apBssid = params[1];
                byte[] apPassword = params[2];
                byte[] deviceCountData = params[3];
                taskResultCount = deviceCountData.length == 0 ? -1 : Integer.parseInt(new String(deviceCountData));
                Context context = PetmiiApplication.getContext();
                mEsptouchTask = new EsptouchTask(apSsid, apBssid, apPassword, null, context);
                mEsptouchTask.setEsptouchListener(presenter.myListener);
            }
            return mEsptouchTask.executeForResults(taskResultCount);
        }

        @Override
        protected void onPostExecute(List<IEsptouchResult> result) {
            ConfigPresenter presenter = mPresenter.get();
            mProgressDialog.dismiss();
            mResultDialog = new AlertDialog.Builder(presenter.mView.getContext())
                    .setPositiveButton(android.R.string.ok, null)
                    .create();
            mResultDialog.setCanceledOnTouchOutside(false);
            if (result == null) {
                mResultDialog.setMessage("Create Esptouch task failed, the esptouch port could be used by other thread");
                mResultDialog.show();
                return;
            }

            IEsptouchResult firstResult = result.get(0);
            if (!firstResult.isCancelled()) {
                int count = 0;
                final int maxDisplayCount = 5;
                if (firstResult.isSuc()) {
                    StringBuilder sb = new StringBuilder();
                    for (IEsptouchResult resultInList : result) {
                        sb.append("Esptouch success, bssid = ")
                                .append(resultInList.getBssid())
                                .append(", InetAddress = ")
                                .append(resultInList.getInetAddress().getHostAddress())
                                .append("\n");
                        presenter.initDevice(resultInList.getBssid(),resultInList.getInetAddress().getHostAddress());
                        count++;
                        if (count >= maxDisplayCount) {
                            break;
                        }
                    }
                    if (count < result.size()) {
                        sb.append("\nthere's ")
                                .append(result.size() - count)
                                .append(" more result(s) without showing\n");
                    }
                    mResultDialog.setMessage(sb.toString());
                } else {
                    mResultDialog.setMessage("Esptouch fail");
                }

                mResultDialog.show();
            }
            presenter.mTask = null;
        }
    }
}
